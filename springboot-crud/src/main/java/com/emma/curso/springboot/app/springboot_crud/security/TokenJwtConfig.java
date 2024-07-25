package com.emma.curso.springboot.app.springboot_crud.security;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;

/**
 * Clase que contiene la configuración de la llave secreta y el prefijo del token  
 * para la generación y validación de los tokens JWT.
 */
 
public class TokenJwtConfig {
    
    public static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
    public static final String PREFIX_TOKEN = "Bearer ";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String CONTENT_TYPE = "application/json";

}
