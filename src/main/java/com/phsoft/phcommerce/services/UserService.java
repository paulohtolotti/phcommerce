package com.phsoft.phcommerce.services;

import com.phsoft.phcommerce.entities.Role;
import com.phsoft.phcommerce.projections.AuthProjection;
import com.phsoft.phcommerce.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.phsoft.phcommerce.entities.User;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<AuthProjection> queryResult = repository.searchByEmail(username);

        if(queryResult.isEmpty()) throw new UsernameNotFoundException("Credenciais inválidas");

        User user = new User();

        user.setId(queryResult.getFirst().getUserId());
        user.setEmail(queryResult.getFirst().getEmail());
        user.setPassword(queryResult.getFirst().getPassword());

        queryResult.forEach( x -> user.addRole(new Role(x.getRoleId(),x.getAuthority())));

        System.out.println(user);
        for(Role r : user.getRoles()) {
            System.out.println(r);
        }
        return user;
    }

}
