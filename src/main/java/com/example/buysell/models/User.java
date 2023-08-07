package com.example.buysell.models;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data

public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;
    @Column (name = "email")
    private String email;
    @Column (name = "phone_number")
    private String phoneNumber;
    @Column (name = "name")
    private String name;
    @Column (name = "activate")
    private boolean activate;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id")
    private Image avatar;
    @Column (name = "password", length = 1000)
    private String password;
    @ElementCollection (targetClass = Role.class, fetch = FetchType.EAGER)

    @CollectionTable (name ="user_role",
            joinColumns = @JoinColumn(name = "user_id"))
    private Set <Role> roles = new HashSet<>();
    private LocalDateTime dateOfCreated;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }


    @PrePersist
    private void init(){
        dateOfCreated = LocalDateTime.now();
    }
}
