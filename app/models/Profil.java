package models;

import controllers.elisisplay.model.ElisisModel;

import javax.persistence.*;

/**
 * Created by SYS-ERREUR on 22/11/2018.
 */
@Entity
@Table(name = "profile")
public class Profil extends ElisisModel{

    /**
     *
     */
    public String designation1;

    /**
     *
     */
    public String designation2;

    /**
     *
     */
    public String adresse;

    /**
     *
     */
    public String srcweb;

    /**
     *
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "code")
    public Compte compte;


    public Profil() {
    }
}
