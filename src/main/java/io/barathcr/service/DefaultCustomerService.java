package io.barathcr.service;

import io.barathcr.data.Customer;
import io.barathcr.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service("customerService")
public class DefaultCustomerService implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public Customer saveCustomer(Customer customer) {
        Customer customerModel = populateCustomerData(customer);
        return customerRepository.save(customerModel);
    }

    @Override
    public String updateCustomer(Long customerId, Customer customer) {
        Customer customerdata = this.getCustomerById(customerId);
        System.out.println(customerdata.toString());
        customerdata = updateCustomerData(customerdata, customer);
        System.out.println(customerdata.toString());

        int flag = customerRepository.updateById(customerdata.getFirstName(), customerdata.getLastName(), customerdata.getEmail(), customerdata.getUpdatedTime(), customerId);
        if(flag == 1)
        {
            return "Customer id[" + customerId + "] created successfully.";
        }
        else
        {
            return "Customer not found";
        }
    }

    @Override
    public String deleteCustomer(Long customerId) {
        Customer customer = this.getCustomerById(customerId);
        if(customer == null)
        {
            return "Customer not found";
        }
        customerRepository.deleteById(customerId);
        return "Customer id[" + customerId + "] deleted successfully.";
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        List<Customer> customerList = customerRepository.findAll();
        customerList.forEach(customer -> {
            customers.add(populateCustomerData(customer));
        });
        return customers;
    }

    @Override
    public Customer getCustomerById(Long customerId) {
        return populateCustomerData( customerRepository.findById(customerId).orElseThrow(() -> new EntityNotFoundException("Customer not found")));
    }


    public Customer populateCustomerData(final Customer customer){
        Customer customerData = new Customer();
        customerData.setId(customer.getId());
        customerData.setFirstName(customer.getFirstName());
        customerData.setLastName(customer.getLastName());
        customerData.setEmail(customer.getEmail());
        customerData.setCreatedTime(customer.getCreatedTime());
        customerData.setUpdatedTime(customer.getUpdatedTime());
        return  customerData;
    }

    public Customer updateCustomerData(final Customer customerData, final Customer customer){
        
        if(customer.getFirstName() != null)
            customerData.setFirstName(customer.getFirstName());
        System.out.println(customerData.getFirstName());
        if(customer.getLastName() != null)
            customerData.setLastName(customer.getLastName());
        if(customer.getEmail() != null)
            customerData.setEmail(customer.getEmail());
        customerData.setUpdatedTime(LocalDateTime.now());
        return  customerData;
    }

}
