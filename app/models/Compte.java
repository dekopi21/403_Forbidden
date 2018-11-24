package models;

import controllers.elisisplay.model.ElisisModel;

import javax.persistence.*;

/**
 * Created by SYS-ERREUR on 22/11/2018.
 */

@Entity
@Table(name = "compte")
public class Compte extends ElisisModel {

    /**
     *
     */
    public String pseudo;

    /**
     *
     */
    public String email;

    /**
     *
     */
    public String password;

    /**
     *
     */
    public boolean etatCompte;

    /**
     *
     */

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "code")
    public Profil profil;

    public Compte() {
    }

    public Compte(String code, String pseudo, String email, String password, boolean etatCompte, Profil profil) {
        this.code = code;
        this.pseudo = pseudo;
        this.email = email;
        this.password = password;
        this.etatCompte = etatCompte;
        this.profil = profil;
    }

    public String abbvr() {
        return "CPT";
    }
}
