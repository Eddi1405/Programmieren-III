package de.trio.imageshare.web.entities;

import jakarta.persistence.*;

/**
 * Diese Klasse gibt die Informationen einer Role vor.
 * Die Annotation @Entity gibt an, dass es sich um eine JPA-Entity-Klasse handelt.
 * Die Annotation @Table gibt den Tabellennamen in der Datenbank an, auf die sich diese Entity bezieht.
 * Die Annotation @Id gibt an, dass das Feld id der Primärschlüssel der Tabelle ist.
 * Die Annotation @GeneratedValue gibt an, dass der Primärschlüssel automatisch generiert wird.
 */
@Entity
@Table(name = "user")
public class RoleDaten {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "users")
    private String users;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }
}