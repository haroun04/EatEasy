package com.EatEasy.Models;
//El primer import se pone * para el @ID
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

//Esto se pone siempre
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private UUID uuid;
    private String name;
    private String email;
    private String password;
    @OneToMany
    @JoinColumn(name = "favoriteRestaurant_id")
    private List<FavoriteRestaurant> favoriteRestaurants;

    @OneToMany
    @JoinColumn(name = "booking_id")
    private List<Booking> bookings;

    @OneToMany
    @JoinColumn(name = "review_id")
    private List<Review> reviews;
}


