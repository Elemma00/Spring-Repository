package com.emma.curso.springboot.jpa.springboot_jpa_relations.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastname;

    // Un cliente, muchas direcciones
    // con el atributo cascade = CascadeType.ALL, le decimos a JPA que cuando se
    // elimine un cliente, elimine también todas las direcciones asociadas a ese
    // cliente
    // y con el atributo orphanRemoval = true, le decimos a JPA que cuando se
    // elimine una dirección que no esté asociada a ningún cliente,
    // también se elimine de la base de datos.
    //@JoinColumn(name = "client_id")

    /*
     * NOTA: no se recomienda usar FetchType.EAGER en relaciones OneToMany, ya que
     * esto puede traer problemas de rendimiento, ya que al traer un cliente, se
     * traerán todas las direcciones asociadas a ese cliente, lo cual puede ser
     * costoso en términos de recursos. Mejor usar FetchType.LAZY y traer las
     * direcciones de manera perezosa, es decir, solo cuando se necesiten.
     * 
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
        name = "tbl_clientes_direcciones", 
        joinColumns = @JoinColumn(name = "id_cliente"), 
        inverseJoinColumns = @JoinColumn(name = "id_direcciones"), 
        uniqueConstraints = @UniqueConstraint(columnNames = {"id_direcciones"}))
    private Set<Address> addresses;


    //Un cliente, muchas facturas
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "client")
    private Set<Invoice> invoices;

    public Client() {
        addresses = new HashSet<>();
        invoices = new HashSet<>();
    }

    public Client(String name, String lastname) {
        this();
        this.name = name;
        this.lastname = lastname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public Set<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(Set<Invoice> invoices) {
        this.invoices = invoices;
    }

    public Client addInvoice(Invoice invoice) {
        invoices.add(invoice);
        invoice.setClient(this);
        return this;
    }

    public Client addAddress(Address address) {
        addresses.add(address);
        return this;
    }

    @Override
    public String toString() {
        return "{id=" + id +
                ", name=" + name + 
                ", lastname=" + lastname + 
                ", addresses=" + addresses + 
                ", invoices="+ invoices + "}";
    }

}
