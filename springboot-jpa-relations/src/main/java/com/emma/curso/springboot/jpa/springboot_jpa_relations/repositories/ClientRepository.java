package com.emma.curso.springboot.jpa.springboot_jpa_relations.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.emma.curso.springboot.jpa.springboot_jpa_relations.entities.Client;

public interface ClientRepository extends CrudRepository<Client, Long>{

    @Query("select c from Client c left join fetch c.addresses a where c.id = ?1")
    Optional<Client> findOneWithAddresses(Long id);

    @Query("select c from Client c left join fetch c.invoices where c.id = ?1")
    Optional<Client> findOneWithInvoices(Long id);

    @Query("select c from Client c left join fetch c.invoices left join fetch c.addresses where c.id = ?1")
    Optional<Client> findOne(Long id);
}
