package org.emma.curso.springboot.webapp.springboot_web;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:values.properties", encoding = "UTF-8")
// Si tenemos m�s de un path, se pueden agregar con comas
// @PropertySource({"classpath:values.properties",
// "classpath:values2.properties"}
public class ValuesConfig {

}
