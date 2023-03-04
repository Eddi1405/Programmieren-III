package de.trio.imageshare.web.indexData.IndexModel;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "data")
public class Data {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    @Column(name ="bildpfad")
    private String bildpfad;
    @Column(name ="title")
    private String title;
    @Column(name ="beschreibung")
    private String beschreibung;
    @Column(name ="kategorie")
    private String kategorie;
    @Column(name ="zeit")
    private Integer zeit;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBildpfad() {
        return bildpfad;
    }

    public void setBildpfad(String bildpfad) {
        this.bildpfad = bildpfad;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getKategorie() {
        return kategorie;
    }

    public void setKategorie(String kategorie) {
        this.kategorie = kategorie;
    }

    public Integer getZeit() {
        return zeit;
    }

    public void setZeit(Integer zeit) {
        this.zeit = zeit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
