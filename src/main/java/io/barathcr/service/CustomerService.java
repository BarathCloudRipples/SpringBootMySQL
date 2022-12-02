package io.barathcr.service;

import io.barathcr.data.Customer;

import java.util.List;

public interface CustomerService {

    Customer saveCustomer(Customer customer);
    String updateCustomer(final Long customerId, final Customer customer);
    String deleteCustomer(final Long customerId);
    List<Customer> getAllCustomers();
    Customer getCustomerById(final Long customerId);
}
