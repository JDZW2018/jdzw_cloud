package com.lanwon;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.lanwon.model.Person;
import com.lanwon.processor.BatchConfiguration;
import com.lanwon.processor.JobCompletionNotificationListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.annotation.BeforeJob;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    @Autowired
    public StepBuilderFactory stepBuilderFactory;

//    BatchConfiguration b=new BatchConfiguration();
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(JobCompletionNotificationListener listener)
    {

        return "111111111111";
    }

//    @Bean
//    public Job importUserJob() {
//        return
//    }
}
