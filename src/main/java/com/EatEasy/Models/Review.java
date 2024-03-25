package com.EatEasy.Models;
//El primer import se pone * para el @ID
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;
    private String comment;
    private Integer assessment;
    private LocalDateTime createdAt;

    @ManyToOne
    private User user;

    @ManyToOne
    private Restaurant restaurant;

    @ManyToOne
    private Owner owner;




}
