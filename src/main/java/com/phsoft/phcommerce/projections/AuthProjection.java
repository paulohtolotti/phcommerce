package com.phsoft.phcommerce.projections;

public interface AuthProjection {
    Long getUserId(); // as userId
    String getEmail(); // as email
    String getPassword();
    Long getRoleId(); // as roleId
    String getAuthority(); // as authority
}
