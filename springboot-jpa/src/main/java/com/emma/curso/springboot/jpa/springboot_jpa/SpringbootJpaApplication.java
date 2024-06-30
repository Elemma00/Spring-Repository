package com.emma.curso.springboot.jpa.springboot_jpa;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.emma.curso.springboot.jpa.springboot_jpa.entities.Person;
import com.emma.curso.springboot.jpa.springboot_jpa.repositories.PersonRepository;


@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner {

	@Autowired
	private PersonRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		personalizedQueries2();
	}

	@Transactional(readOnly = true)
	public void personalizedQueries2(){
		System.out.println("consulta que puebla y devuelve un objeto entity de una instancia personalizada");
		List<Person> persons = repository.findAllObjectPersonPersonalized();
		persons.forEach(System.out::println);
	}

	@Transactional(readOnly = true)
	public void personalizedQueries(){
		Scanner scanner =  new Scanner(System.in);
		System.out.println("Ingrese el id de la persona");
		Long id = scanner.nextLong();
		scanner.close();
		String name = repository.getNameById(id);
		String fullName = repository.getFullNameById(id);
		System.out.println(name);
		System.out.println(fullName);
	}

	@Transactional
	public void delete2(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el id de la persona a eliminar");
		Long id = scanner.nextLong();

		Optional<Person> personOptional = repository.findById(id);
		personOptional.ifPresent(repository::delete);
	}
	

	@Transactional
	public void delete(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el id de la persona a eliminar");
		Long id = scanner.nextLong();
		repository.deleteById(id);
		scanner.close();
		repository.findAll().forEach(System.out::println);
	}

	@Transactional
	public void update(){

		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el id de la persona a actualizar");
		Long id = scanner.nextLong();

		Optional<Person> personOptional = repository.findById(id);
		personOptional.ifPresent(person -> {
			System.out.println(person);
			System.out.println("Ingrese el lenguaje de programación");
			String programmingLanguage = scanner.next();
			person.setProgrammingLanguage(programmingLanguage);
			Person personDB = repository.save(person);
			System.out.println(personDB);
		});

		scanner.close();

	}

	@Transactional
	public void create() {
		Scanner scanner = new Scanner(System.in);
		String name = scanner.nextLine();
		String lastName = scanner.nextLine();
		String programmingLanguage = scanner.nextLine();
		scanner.close();
		Person person = new Person(null, name, lastName, programmingLanguage);

		Person personNew = repository.save(person);
		System.out.println(personNew);

		repository.findById(personNew.getId()).ifPresent(System.out::println);	

	}

	@Transactional(readOnly = true)
	public void findOne() {
		repository.findById(1L).ifPresent(System.out::println);
		repository.findByNameIgnoreCase("pepe").ifPresent(System.out::println);
		repository.findByNameStartingWithIgnoreCase("pe").ifPresent(System.out::println);
	}

	public void list() {
		// List<Person> persons = (List<Person>) repository.findAll();
		// List<Person> persons = repository.findByProgrammingLanguage("Java");
		// List<Person> persons = repository.buscarByProgrammingLanguague("Java");
		List<Person> persons = repository.findByProgrammingLanguageAndName("Java", "Andres");

		persons.stream().forEach(person -> System.out.println(person));

		List<Object[]> personsValues = repository.obtenerPersonData();
		personsValues.stream().forEach(p -> System.out.println(p[0] + " es experto en " + p[1]));
	}

}
