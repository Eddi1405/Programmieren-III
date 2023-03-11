package de.trio.imageshare.web.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "data")
public class PictureDaten {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int id;
    @Column(name = "bildname")
    private String bildname;
    @Column(name = "bildpfad")
    private String bildpfad;
    @Column(name = "title")
    private String title;
    @Column(name = "beschreibung")
    private String beschreibung;
    @Column(name = "kategorie")
    private String kategorie;
    @Column(name = "zeit")
    private Integer zeit;
    @Lob
    @Column(name = "bild")
    private byte[] bild;

    public String getBildpfad() {
        return bildpfad;
    }

    public void setBildpfad(String bildpfad) {
        this.bildpfad = bildpfad;
    }

    public byte[] getBild() {
        return bild;
    }

    public void setBild(byte[] bild) {
        this.bild = bild;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBildname() {
        return bildname;
    }

    public void setBildname(String bildname) {
        this.bildname = bildname;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
