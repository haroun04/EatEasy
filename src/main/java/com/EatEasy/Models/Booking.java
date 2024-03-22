package com.EatEasy.Models;
//El primer import se pone * para el @ID
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;
    private Integer numberDiners;

    @CreatedDate
    private LocalDateTime createdAt;

    @CreatedDate
    private LocalDateTime reservedAt;

    @ManyToOne
    private User user;

    @ManyToOne
    private Restaurant restaurant;

}