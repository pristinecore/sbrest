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

import com.pristinecore.sbrest.domain.Customer;

/**
 * CustomerService interface
 * 
 * @author Brient Oh
 */
public interface CustomerService {

	/**
	 * To return a customer object fetched by ID
	 * 
	 * @param customerId customer ID
	 * @return Customer object
	 */
	Customer findById(Long customerId);
	
	/**
	 * @return the list of all customers
	 */
	List<Customer> findAllCustomers();
	
	/**
	 * @param customer Customer entity to be saved
	 */
	Customer saveCustomer(Customer customer);
	
	/**
	 * @param customer Customer entity to check existence
	 * @return true if exist; otherwise, return false
	 */
	Boolean isCustomerExist(Customer customer);
	
	/**
	 * @param id customer ID to be updated
	 * @param customer updated customer entity
	 * @return updated customer entity
	 */
	Customer updateCustomer(Long id, Customer customer);
	
	/**
	 * @param id customer ID to be updated
	 * @param customer updated customer entity
	 * @return patched customer entity
	 */
	Customer patchCustomer(Long id, Customer customer);
	
	/**
	 * @param id customer ID to be deleted
	 * @return true, if deleted; otherwise, return false
	 */
	Boolean deleteCustomer(Long id);
	
}
