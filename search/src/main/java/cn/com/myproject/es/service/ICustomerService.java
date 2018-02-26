package cn.com.myproject.es.service;

import cn.com.myproject.es.bean.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

public interface ICustomerService {

    Customer save(Customer customer);

    Customer findOne(String id);



}
