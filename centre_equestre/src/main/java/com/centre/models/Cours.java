package com.centre.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Entity
@Table(name = "cours")
public class Cours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cours")
    private int id_cours;
    @Column(name = "discipline")
    private String discipline;
    @Column(name = "date")
    private String date;
    @Column(name = "heure")
    private String heure;
    @Column(name = "duree_cours")
    private String duree_cours;
    @Column(name = "niveau_requis")
    private int niveau_requis;
    @Column(name = "nbr_cavalier_max")
    private int nbr_cavalier_max;
    @Column(name = "moniteur")
    private String motineur;
    @ManyToMany
    @JoinTable(
            name = "cours_cavalier",
            joinColumns = @JoinColumn(name = "id_cours"),
            inverseJoinColumns = @JoinColumn(name = "id_cav")
    )
    private List<Cavalier> cavaliers = new ArrayList<>();

    public Cours(){}

    public Cours(String discipline, String date, String heure, String duree_cours, int niveau_requis, int nbr_cavalier_max, String motineur, List<Cavalier> cavaliers) {
        this.discipline = discipline;
        this.date = date;
        this.heure = heure;
        this.duree_cours = duree_cours;
        this.niveau_requis = niveau_requis;
        this.nbr_cavalier_max = nbr_cavalier_max;
        this.motineur = motineur;
        this.cavaliers = cavaliers;
    }

    public int getId_cours() {
        return id_cours;
    }

    public void setId_cours(int id_cours) {
        this.id_cours = id_cours;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getDuree_cours() {
        return duree_cours;
    }

    public void setDuree_cours(String duree_cours) {
        this.duree_cours = duree_cours;
    }

    public int getNiveau_requis() {
        return niveau_requis;
    }

    public void setNiveau_requis(int niveau_requis) {
        this.niveau_requis = niveau_requis;
    }

    public int getNbr_cavalier_max() {
        return nbr_cavalier_max;
    }

    public void setNbr_cavalier_max(int nbr_cavalier_max) {
        this.nbr_cavalier_max = nbr_cavalier_max;
    }

    public String getMotineur() {
        return motineur;
    }

    public void setMotineur(String motineur) {
        this.motineur = motineur;
    }

    public List<Cavalier> getCavaliers() {
        return cavaliers;
    }

    public void setcavaliers(List<Cavalier> cavaliers) {
        this.cavaliers = cavaliers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cours cours = (Cours) o;
        return niveau_requis == cours.niveau_requis &&
                nbr_cavalier_max == cours.nbr_cavalier_max &&
                Objects.equals(discipline, cours.discipline) &&
                Objects.equals(date, cours.date) &&
                Objects.equals(heure, cours.heure) &&
                Objects.equals(motineur, cours.motineur) &&
                Objects.equals(cavaliers, cours.cavaliers) &&
                Objects.equals(duree_cours, cours.duree_cours);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_cours, discipline, date, heure, duree_cours, niveau_requis, nbr_cavalier_max, motineur, cavaliers);
    }

    @Override
    public String toString() {
        return "Cours{" +
                "discipline='" + discipline + '\'' +
                ", date='" + date + '\'' +
                ", heure='" + heure + '\'' +
                ", duree_cours=" + duree_cours +
                ", niveau_requis=" + niveau_requis +
                ", nbr_cavalier_max=" + nbr_cavalier_max +
                ", motineur='" + motineur + '\'' +
                ", cavaliers=" + cavaliers +
                '}';
    }
}
