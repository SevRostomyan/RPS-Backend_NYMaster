package com.rps.app.User;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.SEQUENCE;

@ToString//Lomboks annotationer för att automatiskt lägga till dessa
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_entity")
//Entity/tabell för user där username och password ska sparas.
public class UserEntity {
    @Id
    @SequenceGenerator( name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)  //Genererar ID
    @GeneratedValue( strategy = SEQUENCE, generator = "user_sequence") //ID-n är ej upprepande
    @Column(name = "id")
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;
}
