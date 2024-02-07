package com.centre.models;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;
@Entity
@Table(name = "cavalier")
public class Cavalier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cav")
    private int id_cav;

    @Column(name="nom", nullable=false)
    private String nom;

    @Column(name="prenom", nullable=false)
    private String prenom;

    @Column(name="mail", nullable=false)
    private String mail;

    @Column(name="date_naiss", nullable=false)
    private String date_naiss;

    @Column(name="niveau", nullable=false)
    private int niveau;

    @ManyToMany
    @JoinTable(
            name = "cours_cavalier",
            joinColumns = @JoinColumn(name = "id_cav"),
            inverseJoinColumns = @JoinColumn(name = "id_cours")
    )
    private List<Cours> cours;

    public Cavalier(String nom, String prenom, String mail, String date_naiss, int niveau, List<Cours> cours) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.date_naiss = date_naiss;
        this.niveau = niveau;
        this.cours = cours;
    }

    public Cavalier(){}

    public long getId_cav() {
        return id_cav;
    }

    public void setId_cav(int id_cav) {
        this.id_cav = id_cav;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDate_naiss() {
        return date_naiss;
    }

    public void setDate_naiss(String date_naiss) {
        this.date_naiss = date_naiss;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public List<Cours> getCours() {
        return cours;
    }

    public void setCours(List<Cours> cours) {
        this.cours = cours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cavalier cavalier = (Cavalier) o;
        return niveau == cavalier.niveau && Objects.equals(nom, cavalier.nom) && Objects.equals(prenom, cavalier.prenom) && Objects.equals(mail, cavalier.mail) && Objects.equals(date_naiss, cavalier.date_naiss) && Objects.equals(cours, cavalier.cours);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_cav, nom, prenom, mail, date_naiss, niveau, cours);
    }

    @Override
    public String toString() {
        return "Cavalier{" +
                "id ='" + id_cav + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", mail='" + mail + '\'' +
                ", date_naiss='" + date_naiss + '\'' +
                ", niveau=" + niveau +
                ", cours=" + cours +
                '}';
    }
}
