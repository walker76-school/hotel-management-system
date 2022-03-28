/*
 * Filename: UserPrincipal.java
 * Author: Andrew Walker
 */

package edu.baylor.ecs.hms.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.baylor.ecs.hms.model.auth.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Principal of the user
 *
 * @author Andrew Walker
 */
@AllArgsConstructor
public class UserPrincipal implements UserDetails {
    private Long id;

    private String username;

    @JsonIgnore
    private String password;

    private String firstName;

    private String lastName;

    private String email;

    private Long age;

    private String phoneNumber;

    private Collection<? extends GrantedAuthority> authorities;

    /**
     * Creates Principal from User
     * @param user user
     * @return Principal from User
     */
    public static UserPrincipal create(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getName().name())
        ).collect(Collectors.toList());


        return new UserPrincipal(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getAge(),
                user.getPhoneNumber(),
                authorities
        );
    }

    /**
     * Get ID
     * @return ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Get username
     * @return username
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Get password
     * @return password
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Get authorities
     * @return authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Long getAge() {
        return age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Return if account is non expire
     * @return if account is non expire
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Return if account is non locked
     * @return if account is non locked
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Return if credentials are non expired
     * @return if credentials are non expired
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Return if account is enabled
     * @return if account is enabled
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * {@inheritDoc}
     * @param o other UserPrincipal
     * @return if equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(id, that.id);
    }

    /**
     * {@inheritDoc}
     * @return hash of UserPrincipal
     */
    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
