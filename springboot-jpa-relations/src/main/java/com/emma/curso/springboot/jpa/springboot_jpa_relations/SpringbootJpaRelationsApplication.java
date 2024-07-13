package com.emma.curso.springboot.jpa.springboot_jpa_relations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.emma.curso.springboot.jpa.springboot_jpa_relations.entities.Address;
import com.emma.curso.springboot.jpa.springboot_jpa_relations.entities.Client;
import com.emma.curso.springboot.jpa.springboot_jpa_relations.entities.ClientDetails;
import com.emma.curso.springboot.jpa.springboot_jpa_relations.entities.Invoice;
import com.emma.curso.springboot.jpa.springboot_jpa_relations.repositories.ClientDetailsRepository;
import com.emma.curso.springboot.jpa.springboot_jpa_relations.repositories.ClientRepository;
import com.emma.curso.springboot.jpa.springboot_jpa_relations.repositories.InvoiceRepository;

@SpringBootApplication
public class SpringbootJpaRelationsApplication implements CommandLineRunner {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private InvoiceRepository invoiceRespository;

	@Autowired
	private ClientDetailsRepository clientDetailsRepository;	

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaRelationsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		oneToOne();
	}

	@Transactional
	public void oneToOne(){

		Client client = new Client("Rosamel", "Fierro");
		clientRepository.save(client);

		ClientDetails clientDetails = new ClientDetails(true, 1000);
		clientDetails.setClient(client);
		clientDetailsRepository.save(clientDetails);
	}

	@Transactional
	public void oneToManyInvoiceFindById(){
		Optional<Client> optionalClient = clientRepository.findOne(2L);
		optionalClient.ifPresent(c -> {
			Invoice invoice1 = new Invoice("Compras de la casa", 5000L);
			Invoice invoice2 = new Invoice("Compras de la oficina", 10000L);

			c.addInvoice(invoice1).addInvoice(invoice2);

			clientRepository.save(c);

			System.out.println(c);

			Optional<Client> optionalClient2 = clientRepository.findOne(2L);
			optionalClient2.ifPresent(client -> {
				client.getAddresses().clear();
				clientRepository.save(client);
				System.out.println(client);
			});	
		});
	}

	@Transactional
	public void oneToManyInvoiceBidirectional() {
		Client client = new Client("Fran", "Moras");

		Invoice invoice1 = new Invoice("Compras de la casa", 5000L);
		Invoice invoice2 = new Invoice("Compras de la oficina", 10000L);

		client.addInvoice(invoice1).addInvoice(invoice2);	

		clientRepository.save(client);
		System.out.println(client);
	}

	@Transactional
	public void removeAddressesFindById() {
		Optional<Client> optionalClient = clientRepository.findById(2L);
		optionalClient.ifPresent(client -> {

			Address address1 = new Address("Calle 1", 123);
			Address address2 = new Address("Calle 2", 456);

			client.addAddress(address1).addAddress(address2);

			Client clientDB = clientRepository.save(client);
			System.out.println(clientDB);

			Optional<Client> optionalClient2 = clientRepository.findOne(2L);
			optionalClient2.ifPresent(c -> {
				c.getAddresses().clear();
				clientRepository.save(c);
				System.out.println(c);
			});
		});

	}

	@Transactional
	public void removeAddress() {
		Client client = new Client("Fran", "Moras");

		Address address1 = new Address("Calle 1", 123);
		Address address2 = new Address("Calle 2", 456);

		client.getAddresses().add(address1);
		client.getAddresses().add(address2);

		clientRepository.save(client);

		Optional<Client> optionalClient = clientRepository.findById(3L);
		optionalClient.ifPresent(c ->{
			c.getAddresses().remove(address1);
			clientRepository.save(c);
			System.out.println(c);
		});
	}


	@Transactional
	public void manyToOne() {

		Client client = new Client("Emma", "Faúndez");
		clientRepository.save(client);

		Invoice invoice = new Invoice("Computer", 1000L);
		invoice.setClient(client);
		Invoice invoiceDB = invoiceRespository.save(invoice);
		System.out.println(invoiceDB);
	}

	@Transactional
	public void manyToOneFindByIdClient() {

		Optional<Client> optionalClient = clientRepository.findById(1L);
		if (optionalClient.isPresent()) {
			Client client = optionalClient.orElseThrow();
			Invoice invoice = new Invoice("Compras de oficina", 2000L);
			invoice.setClient(client);
			Invoice invoiceDB = invoiceRespository.save(invoice);
			System.out.println(invoiceDB);
		}
	}

	@Transactional
	public void oneToMany() {
		Client client = new Client("Fran", "Moras");

		Address address1 = new Address("Calle 1", 123);
		Address address2 = new Address("Calle 2", 456);

		client.getAddresses().add(address1);
		client.getAddresses().add(address2);

		clientRepository.save(client);
		System.out.println(client);
	}

	@Transactional
	public void oneToManyFindById() {
		Optional<Client> optionalClient = clientRepository.findById(2L);
		optionalClient.ifPresent(client -> {

			Address address1 = new Address("Calle 1", 123);
			Address address2 = new Address("Calle 2", 456);

			client.addAddress(address1).addAddress(address2);

			Client clientDB = clientRepository.save(client);
			System.out.println(clientDB);
		});

	}

	
	
}
