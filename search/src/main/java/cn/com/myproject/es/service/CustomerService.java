package cn.com.myproject.es.service;

import cn.com.myproject.es.bean.Customer;
import cn.com.myproject.es.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements  ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer findOne(String id) {
        return customerRepository.findById(id).orElse(null);
    }


}
