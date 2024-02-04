package com.centre.models;

import jakarta.persistence.*;

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
    @Column(name = "date_cours")
    private String date_cours;
    @Column(name = "heure")
    private String heure;
    @Column(name = "duree")
    private float duree;
    @Column(name = "niveau_requis")
    private int niveau_requis;
    @Column(name = "nbr_cavalier_max")
    private int nbr_cavalier_max;
    @Column(name = "prof")
    private String prof;
    @ManyToMany
    @JoinTable(
            name = "cours_cavalier",
            joinColumns = @JoinColumn(name = "id_cours"),
            inverseJoinColumns = @JoinColumn(name = "id_cav")
    )
    private List<Cavalier> cavaliers;

    public Cours(){}

    public Cours(int id_cours, String discipline, String date_cours, String heure, float duree, int niveau_requis, int nbr_cavalier_max, String prof, List<Cavalier> cavaliers) {
        this.id_cours = id_cours;
        this.discipline = discipline;
        this.date_cours = date_cours;
        this.heure = heure;
        this.duree = duree;
        this.niveau_requis = niveau_requis;
        this.nbr_cavalier_max = nbr_cavalier_max;
        this.prof = prof;
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

    public String getDate_cours() {
        return date_cours;
    }

    public void setDate_cours(String date_cours) {
        this.date_cours = date_cours;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public float getDuree() {
        return duree;
    }

    public void setDuree(float duree) {
        this.duree = duree;
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

    public String getProf() {
        return prof;
    }

    public void setProf(String prof) {
        this.prof = prof;
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
        return Float.compare(duree, cours.duree) == 0 && niveau_requis == cours.niveau_requis && nbr_cavalier_max == cours.nbr_cavalier_max && Objects.equals(discipline, cours.discipline) && Objects.equals(date_cours, cours.date_cours) && Objects.equals(heure, cours.heure) && Objects.equals(prof, cours.prof) && Objects.equals(cavaliers, cours.cavaliers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_cours, discipline, date_cours, heure, duree, niveau_requis, nbr_cavalier_max, prof, cavaliers);
    }

    @Override
    public String toString() {
        return "Cours{" +
                "discipline='" + discipline + '\'' +
                ", date_cours='" + date_cours + '\'' +
                ", heure='" + heure + '\'' +
                ", duree=" + duree +
                ", niveau_requis=" + niveau_requis +
                ", nbr_cavalier_max=" + nbr_cavalier_max +
                ", prof='" + prof + '\'' +
                ", cavaliers=" + cavaliers +
                '}';
    }
}
