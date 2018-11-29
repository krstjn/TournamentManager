package project.service.Interfaces;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;

public interface IAuthenticationService {
    Authentication getAuthentication();

    Collection<SimpleGrantedAuthority> getAuthorities();

    boolean isAuthenticated();

    String getUsername();

    boolean isAdmin();

}
