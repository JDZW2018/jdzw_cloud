package cn.com.myproject.es.repository;

import cn.com.myproject.es.bean.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

@Component
public class CustomizedCustomerRepositoryImpl implements CustomizedCustomerRepository {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private ElasticsearchRepository<Customer,String> elasticsearchRepository;

    @Override
    public Customer save(Customer customer) {
        return  elasticsearchRepository.save(customer);
    }
}
