package com.EatEasy.Models;
//El primer import se pone * para el @ID
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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
    private String country;
    private String stadium;
    private Boolean champions;
    private LocalDateTime tochampionsTimestamp;
    //Se establece la relacion de un equipo para muchos jugadores
    @OneToMany
    private List<Player> players;
    @OneToMany
    private List<Staff> staffs;
}
//El siguiente es el repositories


//Al utilizar el LocalDateTime no hace falta hacer en el repository