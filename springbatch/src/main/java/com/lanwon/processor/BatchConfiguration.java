package com.lanwon.processor;
import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.lanwon.model.Person;

/***
 * batch 注入
 * **/
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public DataSource dataSource;

    // tag::readerwriterprocessor[]

    @Bean
    public FlatFileItemReader<Person> reader() {
        FlatFileItemReader<Person> reader = new FlatFileItemReader<Person>();
        reader.setResource(new ClassPathResource("sample-data.csv"));
        reader.setLineMapper(new DefaultLineMapper<Person>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] { "firstName", "lastName" });
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {{
                setTargetType(Person.class);
            }});
        }});
        return reader;
    }
//    FlatFileItemReader 的主要作用就是读入一个文件，返回一组bean，他要配置的最主要的组件：
//            【1】 resource 文件在什么地方
//【2】 lineMapper 即 这个组件将文件的每一行组装成一个bean。
//
//    其中lineMapper 又是由  lineTokenizer 和 fieldSetMapper
//【1】 lineTokenizer 作用是将文件的每一行分解成一个FieldSet
//【2】 fieldSetMapper 又以 FieldSet 组成一个bean 或者一组记录。

    @Bean
    public PersonItemProcessor processor() {
        return new PersonItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Person> writer() {
        JdbcBatchItemWriter<Person> writer = new JdbcBatchItemWriter<Person>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Person>());
        writer.setSql("INSERT INTO people (first_name, last_name) VALUES (:firstName, :lastName)");
        writer.setDataSource(dataSource);
        return writer;
    }
    // end::readerwriterprocessor[]

    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener) {
        return jobBuilderFactory.get("importUserJob")//获取job
                .incrementer(new RunIdIncrementer())//
                .listener(listener)//
                .flow(step1())
                .end()
                .build();
    }

    @Bean
    public Step step1() {
        System.out.println("1111111111111111111111");
        return stepBuilderFactory.get("step1")
                .<Person, Person> chunk(15).reader(reader())//读取文件流（数据）
                .processor(processor())//处理数据
                .writer(writer())//写入数据
                .build();
    }

    //-----------------------------------------------------------------------------------------------

    @Bean
    public Step step2() {
        System.out.println("2222222");
        return stepBuilderFactory.get("step2")
                .<Person, Person> chunk(10).reader(reader())//读取文件流（数据）
                .processor(processor())//处理数据
                .writer(writer())//写入数据
                .build();

    }

        @Bean
    public Job updateUserJob(JobCompletionNotificationListener listener) {
        return jobBuilderFactory.get("updateUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(updateStep())
                .end()
                .build();
    }

    @Bean
    public FlatFileItemReader<Person> reader1() {
        FlatFileItemReader<Person> reader = new FlatFileItemReader<Person>();
        reader.setResource(new ClassPathResource("sample.csv"));
        reader.setLineMapper(new DefaultLineMapper<Person>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] { "firstName", "lastName" });
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {{
                setTargetType(Person.class);
            }});
        }});
        return reader;
    }


    @Bean
    public Step updateStep() {
        System.out.println("3333333");
        return stepBuilderFactory.get("updateStep")
                .<Person, Person>chunk(10)
                .reader(reader1())
                .processor(processor())
                .writer(updateWriter())
                .build();
    }

    @Bean
    public JdbcBatchItemWriter<Person> updateWriter() {
        JdbcBatchItemWriter<Person> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        //writer.setSql("UPDATE people SET first_name = (:firstName), WHERE first_name = (:firstName)");
        writer.setSql("delete from people where first_name in (:firstName)");
        //writer.setSql("INSERT INTO people (first_name, last_name) VALUES (:firstName, :lastName)");
        writer.setDataSource(dataSource);
        return writer;
    }


}

