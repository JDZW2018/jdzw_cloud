package cn.com.myproject.es.controller;

import cn.com.myproject.es.bean.Customer;
import cn.com.myproject.es.repository.CustomizedCustomerRepository;
import cn.com.myproject.es.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;
    @Autowired
    private CustomizedCustomerRepository customizedCustomerRepository;

    @RequestMapping("/save")
    public Customer save(String firstName,String lastName) {
        Customer customer = new Customer();
        customer.setId(UUID.randomUUID().toString());
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        return customerService.save(customer);
    }

    @RequestMapping("/save1")
    public Customer save1(String firstName,String lastName) {
        Customer customer = new Customer();
        customer.setId(UUID.randomUUID().toString());
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        return customizedCustomerRepository.save(customer);
    }

    @RequestMapping("/get")
    public Customer get(String id) {
        return customerService.findOne(id);
    }
}
