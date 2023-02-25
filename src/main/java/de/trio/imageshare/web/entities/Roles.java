package de.trio.imageshare.web.entities;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Setter
@Getter

public class Roles {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;
    private String name;
}
