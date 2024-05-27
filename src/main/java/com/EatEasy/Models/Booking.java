package com.EatEasy.Models;
//El primer import se pone * para el @ID
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import com.EatEasy.Models.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Date;
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

    @JsonIgnore
    @ManyToOne
    private User user;

    @CreatedDate
    private LocalDateTime reservedAt;

    //@JsonIgnore
    @ManyToOne
    private Restaurant restaurant;

}