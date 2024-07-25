package com.emma.curso.springboot.app.springboot_crud.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emma.curso.springboot.app.springboot_crud.entities.User;
import com.emma.curso.springboot.app.springboot_crud.repositories.UserRepository;

/**
 * Esta clase implementa la interfaz UserDetailsService de Spring Security
 * para poder cargar los datos de un usuario en el sistema.
 * 
 * <p>Es importante para la autenticación de los usuarios en el sistema,
 * ya que Spring carga los datos del usuario a través de esta clase y se utiliza
 * para el uso de JWT y la autenticación de los usuarios.
 *
 */

@Service
public class JpaUserDetailsService implements UserDetailsService{

    @Autowired
    private UserRepository repository;

/**
 * Carga los detalles de un usuario basado en el nombre de usuario.
 * Este método es utilizado por Spring Security para obtener los detalles del usuario
 * necesarios para la autenticación y autorización.
 *
 * @param username el nombre de usuario del usuario a cargar.
 * @return un objeto UserDetails que representa al usuario.
 * @throws UsernameNotFoundException si el nombre de usuario no se encuentra en el sistema.
 * 
 * <p>Este objeto UserDetails contiene información como el nombre de usuario, la contraseña,
 * y las autoridades (roles) que se utilizan para verificar los permisos del usuario
 * en el sistema.
 *
 * El método busca el usuario en la base de datos utilizando el repositorio UserRepository.
 * Si el usuario se encuentra, se devuelve un objeto UserDetails.
 * Si el usuario no se encuentra, se lanza una excepción UsernameNotFoundException.
 */
    @Transactional(readOnly =  true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = repository.findByUsername(username);
        if(!userOptional.isPresent()){
            throw new UsernameNotFoundException(String.format("Username %s no existe en el sistema", username));
        }

        User user = userOptional.orElseThrow();
        List<GrantedAuthority> authorities = user.getRoles().stream()
        .map(role -> new SimpleGrantedAuthority(role.getName()))
        .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
        user.getPassword(), 
        user.isEnabled(),
        true,
        true,
        true, 
        authorities);
    }

}
