package de.trio.imageshare.web.entities;

import de.trio.imageshare.web.entities.RoleDaten;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;
/**
 * Diese Klasse gibt die Informationen eines Users vor.
 * Die Annotation @Entity gibt an, dass es sich um eine JPA-Entity-Klasse handelt.
 * Die Annotation @Table gibt den Tabellennamen in der Datenbank an, auf die sich diese Entity bezieht.
 * Die Annotation @Id gibt an, dass das Feld id der Primärschlüssel der Tabelle ist.
 * Die Annotation @GeneratedValue gibt an, dass der Primärschlüssel automatisch generiert wird.
 * Es gibt auch Validierungsannotationen wie @NotBlank, @Size und @Email, um sicherzustellen, dass die Eingabe des Benutzers validiert wird.
 */
@Entity
@Table(name = "users")
public class UserDaten {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 20)
    private String password;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    @Column
    private String role = "user";


    /**
     * Erstellt eine Join Table mit einer ManytoMany Beziehung, um die Rollen eines Users zu speichern.
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleDaten> roles = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<RoleDaten> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDaten> roles) {
        this.roles = roles;
    }

}

