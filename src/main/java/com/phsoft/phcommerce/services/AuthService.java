package com.phsoft.phcommerce.services;
import com.phsoft.phcommerce.entities.User;
import com.phsoft.phcommerce.services.exception.ForbiddenException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserService userService;

    public AuthService(UserService userService) {
        this.userService = userService;
    }

    public void validateSelfOrAdmin(Long userID) {

        User currentUser = userService.getAuthenticatedUserByEmail();

        if(!currentUser.hasRole("ROLE_ADMIN") && currentUser.getId() != userID) {
            throw new ForbiddenException("Acesso negado");
        }
    }
}
