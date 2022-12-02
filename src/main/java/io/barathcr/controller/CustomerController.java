package io.barathcr.controller;

import io.barathcr.data.Customer;
import io.barathcr.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Resource(name = "customerService")
    private CustomerService customerService;


    @GetMapping("/list")
    public List<Customer> getCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping("/list/{id}")
    public Customer getCustomer(@PathVariable Long id){
        return customerService.getCustomerById(id);
    }

    @PostMapping("/create")
    public Customer saveCustomer(final @RequestBody Customer customerData){
        return customerService.saveCustomer(customerData);
    }

    @PutMapping("/update/{id}")
	public String updateCustomer(@PathVariable Long id, final @RequestBody Customer customerData){
        return customerService.updateCustomer(id, customerData);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id){
        return customerService.deleteCustomer(id);
    }

}
