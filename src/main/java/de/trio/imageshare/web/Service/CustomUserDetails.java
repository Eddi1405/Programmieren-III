package de.trio.imageshare.web.Service;

import java.util.Collection;

import de.trio.imageshare.web.entities.UserDaten;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

    private UserDaten userDaten;

    public CustomUserDetails(UserDaten userDaten) {
        this.userDaten = userDaten;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return userDaten.getPassword();
    }

    @Override
    public String getUsername() {
        return userDaten.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
