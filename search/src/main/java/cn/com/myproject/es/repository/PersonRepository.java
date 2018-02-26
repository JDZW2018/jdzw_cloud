package cn.com.myproject.es.repository;

import cn.com.myproject.es.bean.Person;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Description: 附近人DAO接口
 * User: pdt
 * Date: 2018-01-18 14:34
 */
public interface PersonRepository extends ElasticsearchRepository<Person,String>{



}