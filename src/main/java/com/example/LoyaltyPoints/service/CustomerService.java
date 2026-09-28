package com.example.LoyaltyPoints.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.LoyaltyPoints.model.Customer;
import com.example.LoyaltyPoints.model.Tier;
import com.example.LoyaltyPoints.repository.CustomerRepository;
import com.example.LoyaltyPoints.repository.TierRepository;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final TierRepository tierRepository;

    public CustomerService(CustomerRepository customerRepository,
                           TierRepository tierRepository) {
        this.customerRepository = customerRepository;
        this.tierRepository = tierRepository;
    }

    
    public Customer createCustomer(Customer customer) {

        Tier silverTier = tierRepository.findByName("SILVER")
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.INTERNAL_SERVER_ERROR,
                                "SILVER tier not found"
                        ));

        customer.setLifetimePoints(0L);
        customer.setAvailablePoints(0L);
        customer.setTier(silverTier);

        return customerRepository.save(customer);
    }

    
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Customer not found with id: " + id
                        ));
    }

    
    public Customer updateCustomer(Long id, Customer updatedCustomer) {

        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Customer not found with id: " + id
                        ));

        existingCustomer.setName(updatedCustomer.getName());
        existingCustomer.setEmail(updatedCustomer.getEmail());

     

        return customerRepository.save(existingCustomer);
    }

    
    public void deleteCustomer(Long id) {

        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Customer not found with id: " + id
                        ));

        customerRepository.delete(existingCustomer);
    }
}