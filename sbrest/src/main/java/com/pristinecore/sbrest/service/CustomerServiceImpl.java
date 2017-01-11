/*******************************************************************************
 * Copyright 2016 Brient Oh @ Pristine Core
 * boh@pristinecore.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.pristinecore.sbrest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pristinecore.sbrest.domain.Customer;
import com.pristinecore.sbrest.repository.CustomerRepository;

/**
 * CustomerService Implementation
 *
 * @author Brient Oh
 */
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer findById(final Long customerId) {
		return customerRepository.findOne(customerId);
	}

	@Override
	public List<Customer> findAllCustomers() {
		return (List<Customer>) customerRepository.findAll();
	}

	@Override
	public Customer saveCustomer(final Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Boolean isCustomerExist(final Customer customer) {
		if (customer.getId() != null) {
			final Customer existingCustomer = customerRepository.findOne(customer.getId());
			if (existingCustomer == null) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

	@Override
	public Customer updateCustomer(final Long id, final Customer customer) {
		
		final Customer fetchedCustomer = customerRepository.findOne(id);
		if (fetchedCustomer == null) {
			return null;
		}
		fetchedCustomer.setFirstname(customer.getFirstname());
		fetchedCustomer.setLastname(customer.getLastname());
		
		customerRepository.save(fetchedCustomer);
		
		return fetchedCustomer;
	}

	@Override
	public Customer patchCustomer(Long id, Customer customer) {

		final Customer fetchedCustomer = customerRepository.findOne(id);
		if (fetchedCustomer == null) {
			return null;
		}
		
		if (customer.getFirstname() != null) {
			fetchedCustomer.setFirstname(customer.getFirstname());
		}
		
		if (customer.getLastname() != null) {
			fetchedCustomer.setLastname(customer.getLastname());
		}
		
		customerRepository.save(fetchedCustomer);
		
		return fetchedCustomer;
	}

	@Override
	public Boolean deleteCustomer(final Long id) {
		final Customer fetchedCustomer = customerRepository.findOne(id);
		if (fetchedCustomer == null) {
			return false;
		} else {
			customerRepository.delete(fetchedCustomer);
			return true;
		}
	}

}
