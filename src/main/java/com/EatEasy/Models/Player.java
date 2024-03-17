package com.EatEasy.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;
    private String name;
    private Integer number;
    //Se establece la relacion de muchos jugadorespara  un equipo
    @ManyToOne
    private User team;
}
// No se mete el team_Id por que ya se tiene como inicializado en el private Team team