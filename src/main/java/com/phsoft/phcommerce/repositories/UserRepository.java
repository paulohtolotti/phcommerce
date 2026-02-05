package com.phsoft.phcommerce.repositories;

import com.phsoft.phcommerce.entities.User;
import com.phsoft.phcommerce.projections.AuthProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    // id user, nome do user, id da role, nome da role -> projection -> user
    @Query(nativeQuery = true, value = "SELECT tb_user.id as userId, tb_user.email, tb_user.password, tb_role.id as roleId, " +
            "tb_role.authority FROM tb_user " +
            "INNER JOIN tb_user_role ON tb_user.id = tb_user_role.user_id " +
            "INNER JOIN tb_role ON tb_role.id = tb_user_role.role_id " +
            "WHERE tb_user.email = :email")
    List<AuthProjection> searchByEmail(String email);
}
