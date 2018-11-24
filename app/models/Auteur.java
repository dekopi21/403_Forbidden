package models;

import controllers.elisisplay.model.ElisisModel;

import javax.persistence.*;

/**
 * Created by SYS-ERREUR on 22/11/2018.
 */

@Entity
@Table(name = "auteur")
public class Auteur extends ElisisModel{
    /**
     *
     */
    public String nomAuteur;

    /**
     *
     */
    public String prenomAuteur;

    /**
     *
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "code")
    public Ouvrage ouvrage;

    public Auteur() {
    }
}
