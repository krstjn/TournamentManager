package project.service;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import project.service.Interfaces.IAuthenticationService;

import java.util.Collection;

@Service
public class AuthenticationService implements IAuthenticationService {


    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public Collection<SimpleGrantedAuthority> getAuthorities() {
        return (Collection<SimpleGrantedAuthority>)getAuthentication().getAuthorities();
    }

    @Override
    public boolean isAuthenticated() {
        boolean isAuthenticated = false;
        if (!(getAuthentication() instanceof AnonymousAuthenticationToken)) {
            isAuthenticated = true;
        }
        return isAuthenticated;
    }

    @Override
    public String getUsername(){
        return getAuthentication().getName();
    }


}
