package com.bookmyshow.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {


    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private  String fname;

    private  String lname;

    private LocalDate dob;

    @Column(unique = true)
    private String  email;

    private String password;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Booking> booking = new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private  List<AtmCard> atmCards=new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Payment>payments=new ArrayList<>();


    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role",referencedColumnName = "id"))
    private Set<Role> roles = new HashSet<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

        return authorities;
    }

    @Override
    public String getUsername() {
        return this.email;
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



