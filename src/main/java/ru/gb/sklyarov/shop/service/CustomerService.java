package ru.gb.sklyarov.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.sklyarov.shop.dao.CustomerDao;
import ru.gb.sklyarov.shop.model.Customer;

import java.util.List;

@Service
public class CustomerService {
    private CustomerDao customerRepository;

    @Autowired
    public CustomerService(CustomerDao customerDao) {
        this.customerRepository = customerDao;
    }

    public Customer getById(Long id) {
        return customerRepository.findById(id);
    }

    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

    public void save(Customer customer){
        customerRepository.save(customer);
    }
}
