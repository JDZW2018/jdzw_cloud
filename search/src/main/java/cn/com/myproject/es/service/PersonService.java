package cn.com.myproject.es.service;

import cn.com.myproject.es.bean.Person;
import cn.com.myproject.es.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: 附近人Service接口实现类
 * User: pdt
 * Date: 2018-01-18 15:00
 */
@Service
public class PersonService implements IPersonService{

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    private static final String PERSON_INDEX_NAME = "person";

    private static final String PERSON_INDEX_TYPE = "person";

    /**
     * 添加附近人
     *
     * @param person
     * @return
     */
    @Override
    public Person addPerson(Person person){
        return personRepository.save(person);
    }

    /**
     * 批量插入附近人
     *
     * @param personList
     */
    @Override
    public void bulkIndex(List<Person> personList){
        int counter = 0;
        try{
            if(!elasticsearchTemplate.indexExists(PERSON_INDEX_NAME)){
                elasticsearchTemplate.createIndex(PERSON_INDEX_TYPE);
                elasticsearchTemplate.putMapping(Person.class);
            }
            List<IndexQuery> queries = new ArrayList<IndexQuery>();
            for (Person person:personList) {
                IndexQuery indexQuery = new IndexQuery();
                indexQuery.setId(person.getId());
                indexQuery.setObject(person);
                indexQuery.setIndexName(PERSON_INDEX_NAME);
                indexQuery.setType(PERSON_INDEX_TYPE);
                //上面的那几步也可以使用IndexQueryBuilder来构建
                //IndexQuery indexQuery = new IndexQueryBuilder().withId(person.getId()).withObject(person).build();
                queries.add(indexQuery);
                if(counter % 500 == 0){
                    // bulk也是ES官方推荐使用的批量插入数据的方法
                    elasticsearchTemplate.bulkIndex(queries);
                    queries.clear();
                    System.out.println("bulkIndex counter : " + counter);
                }
                counter++;
            }
            if(queries.size() > 0){
                elasticsearchTemplate.bulkIndex(queries);
            }
            System.out.println("bulkIndex completed.");
        }catch (Exception e){
            System.out.println("批量插入附近人信息异常："+e.getMessage());
            throw e;
        }
    }

}