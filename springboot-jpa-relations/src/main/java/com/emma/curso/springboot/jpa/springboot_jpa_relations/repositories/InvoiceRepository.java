package com.emma.curso.springboot.jpa.springboot_jpa_relations.repositories;

import org.springframework.data.repository.CrudRepository;

import com.emma.curso.springboot.jpa.springboot_jpa_relations.entities.Invoice;

public interface InvoiceRepository extends CrudRepository<Invoice, Long> {

}
 