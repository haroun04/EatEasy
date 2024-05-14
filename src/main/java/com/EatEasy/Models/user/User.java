package com.EatEasy.Models.user;

import com.EatEasy.Models.Booking;
import com.EatEasy.Models.FavoriteRestaurant;
import com.EatEasy.Models.Review;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user_")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String profilePicture;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<FavoriteRestaurant> favoriteRestaurants;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Booking> bookings;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Review> reviews;

    public User(String name, String email ,String password, String profilePicture) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.profilePicture = profilePicture;
    }
    // En esta lo que hace es que llama a la clase role que es la que tenemos que va a haber un usuario admin y un manager

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
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
