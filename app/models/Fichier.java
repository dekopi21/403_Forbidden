package models;

import controllers.elisisplay.model.ElisisModel;

import javax.persistence.*;

/**
 * Created by SYS-ERREUR on 22/11/2018.
 */

@Entity
@Table(name = "fichiers")
public class Fichier extends ElisisModel{

    /**
     *
     */
    public String nomFichier;

    /**
     *
     */
    public double taille;

    /**
     *
     */
    public String artVersion;

    /**
     *
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "code")
    public Compte compte;

    /**
     *
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "code")
    public Ouvrage ouvrage;


    /**
     *
     */
    public Fichier() {
    }
}
