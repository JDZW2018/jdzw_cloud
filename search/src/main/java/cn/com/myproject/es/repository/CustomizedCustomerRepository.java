package cn.com.myproject.es.repository;

import cn.com.myproject.es.bean.Customer;

public interface CustomizedCustomerRepository {
    Customer save(Customer customer);
}
