package de.trio.imageshare.web.Repository;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class RoleDaten {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name ="name")
    private String name;
    @Column(name ="users")
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