package models;

import controllers.elisisplay.model.ElisisModel;

import javax.persistence.*;

/**
 * Created by SYS-ERREUR on 22/11/2018.
 */
@Entity
@Table(name = "categorie")
public class Categorie extends ElisisModel {
    /**
     *
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "code")
    public Compte compte;


    /**
     *
     */
    @Column(nullable = false)
    public String nomCategorie;

    /**
     *
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "code")
    public Job job;

    /**
     *
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "code")
    public New New;

    /**
     *
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "code")
    public Ouvrage ouvrage;

    public Categorie() {
    }
}
