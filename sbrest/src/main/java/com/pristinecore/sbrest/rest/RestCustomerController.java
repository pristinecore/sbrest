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
package com.pristinecore.sbrest.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.pristinecore.sbrest.domain.Customer;
import com.pristinecore.sbrest.service.CustomerService;

/**
 * RestCustomerController class
 * 
 * @author Brient Oh
 */
@RestController
public class RestCustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	/**
	 * Retrieve All customers
	 * 
	 * @return ResponseEntity<List<Customer>>
	 */
	@RequestMapping(value = "/customer", method = RequestMethod.GET) 
	public ResponseEntity<List<Customer>> listAllCustomers() {
		final List<Customer> allCustomers = customerService.findAllCustomers();
		if (allCustomers.isEmpty()) {
			return new ResponseEntity<List<Customer>>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<Customer>>(allCustomers, HttpStatus.OK);
	}

	/**
	 * Retrieve a single customer
	 * 
	 * @param id customer ID
	 * @return ResponseEntity<Customer>
	 */
	@RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
	public ResponseEntity<Customer> getCustomer(@PathVariable("id") final Long id) {
		final Customer fetchedCustomer = customerService.findById(id);
		if (fetchedCustomer == null) {
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Customer>(fetchedCustomer, HttpStatus.OK);
	}

	/**
	 * Create a customer
	 * 
	 * @param customer object to be created
	 * @param ucBuilder UriComponentBuilder
	 * @return ResponseEntity<Void>
	 */
	@RequestMapping(value = "/customer", method = RequestMethod.POST)
	public ResponseEntity<Void> createCustomer(@RequestBody final Customer customer,
			final UriComponentsBuilder ucBuilder) {
		
		if (customerService.isCustomerExist(customer)) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		final Customer savedCustomer = customerService.saveCustomer(customer);
		
		final HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/customer/{id}").buildAndExpand(savedCustomer.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	/**
	 * Update a customer
	 * 
	 * @param id customer ID to be updated
	 * @param customer source Customer object to be updated
	 * @return ResponseEntity<Customer>
	 */
	@RequestMapping(value = "/customer/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Customer> updateCustomer(@PathVariable("id") final Long id,
			@RequestBody final Customer customer) {
		final Customer updatedCustomer = customerService.updateCustomer(id, customer);
		
		if (updatedCustomer == null) {
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Customer>(updatedCustomer, HttpStatus.OK);
	}

	/**
	 * Patch a customer
	 * 
	 * @param id customer ID to be patched
	 * @param customer Customer object to be patched
	 * @return ResponseEntity<Customer>
	 */
	@RequestMapping(value = "/customer/{id}", method = RequestMethod.PATCH)
	public ResponseEntity<Customer> patchCustomer(@PathVariable("id") final Long id,
			@RequestBody final Customer customer) {
		final Customer patchedCustomer = customerService.patchCustomer(id, customer);
		
		if (patchedCustomer == null) {
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Customer>(patchedCustomer, HttpStatus.OK);
	}

	/**
	 * Delete a customer
	 * 
	 * @param id customer ID to be deleted
	 * @return ResponseEntity<Void>
	 */
	@RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteCustomer(@PathVariable("id") final Long id) {
		Boolean deleteResult = customerService.deleteCustomer(id);
		
		if (deleteResult == null || !deleteResult) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
