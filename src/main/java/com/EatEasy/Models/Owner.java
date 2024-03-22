package com.EatEasy.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;
    private String name;
    private String email;
    private String password;

    @OneToMany
    @JoinTable(name = "owner_restaurant")
    private List<Restaurant> restaurants;

    @OneToMany
    @JoinTable(name = "owner_review")
    private List<Review> reviews;

}
