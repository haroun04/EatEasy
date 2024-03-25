package com.EatEasy.Models;
//El primer import se pone * para el @ID
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;
    private String name;
    private String location;
    private String foodStyle;
    private String timetable;
    private Integer capacity;
    private String phoneNumber;

    @OneToMany(mappedBy = "restaurant")
    private List<Image> images;

    @OneToMany(mappedBy = "restaurant")
    private List<Booking> bookings;

    @OneToMany(mappedBy = "restaurant")
    private List<Review> reviews;

    @OneToMany(mappedBy = "restaurant")
    private List<FavoriteRestaurant> favoriteRestaurants;

    @ManyToOne
    private Owner owner;

}
