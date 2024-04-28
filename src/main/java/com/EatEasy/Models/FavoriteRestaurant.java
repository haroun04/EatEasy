package com.EatEasy.Models;
//El primer import se pone * para el @ID
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.EatEasy.Models.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteRestaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    private User user;
    @JsonIgnore
    @ManyToOne
    private Restaurant restaurant;
}
