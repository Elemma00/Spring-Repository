package com.emma.curso.springboot.jpa.springboot_jpa_relations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.emma.curso.springboot.jpa.springboot_jpa_relations.entities.Address;
import com.emma.curso.springboot.jpa.springboot_jpa_relations.entities.Client;
import com.emma.curso.springboot.jpa.springboot_jpa_relations.entities.Invoice;
import com.emma.curso.springboot.jpa.springboot_jpa_relations.repositories.ClientRepository;
import com.emma.curso.springboot.jpa.springboot_jpa_relations.repositories.InvoiceRepository;

@SpringBootApplication
public class SpringbootJpaRelationsApplication implements CommandLineRunner {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private InvoiceRepository invoiceRespository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaRelationsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		oneToMany();
	}

	@Transactional
	public void manyToOne(){
		
		Client client = new Client("Emma", "Faúndez");
		clientRepository.save(client);

		Invoice invoice = new Invoice("Computer", 1000L);
		invoice.setClient(client);
		Invoice invoiceDB = invoiceRespository.save(invoice);
		System.out.println(invoiceDB);
	}

	@Transactional
	public void manyToOneFindByIdClient(){
		
		Optional<Client> optionalClient = clientRepository.findById(1L);
		if(optionalClient.isPresent()){
			Client client = optionalClient.orElseThrow();
			Invoice invoice = new Invoice("Compras de oficina", 2000L);
			invoice.setClient(client);
			Invoice invoiceDB = invoiceRespository.save(invoice);
			System.out.println(invoiceDB);
		}
	}

	@Transactional
	public void oneToMany(){
		Client client = new Client("Fran", "Moras");

		Address address1 = new Address("Calle 1", 123);
		Address address2 = new Address("Calle 2", 456);

		client.getAddresses().add(address1);
		client.getAddresses().add(address2);

		clientRepository.save(client);
		System.out.println(client);
	}
}
