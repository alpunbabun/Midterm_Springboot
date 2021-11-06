package com.example.midterm;

import com.example.midterm.ENTITY.Customer;
import com.example.midterm.REPOSITORY.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Customer customer = customerRepository.findByUsername(username);
        if(customer ==null) {
            throw new UsernameNotFoundException("User Not Found");
        }
        return new CustomUserDetails(customer);
    }
}
