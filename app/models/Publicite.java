package models;

import controllers.elisisplay.model.ElisisModel;

import javax.persistence.*;

/**
 * Created by SYS-ERREUR on 22/11/2018.
 */
@Entity
@Table(name = "publicite")
public class Publicite extends ElisisModel{

    /**
     *
     */
    public String titrePub;

    /**
     *
     */
    public String descPub;

    /**
     *
     */
    public boolean etatpub;

    /**
     *
     */
    public String image;

    /**
     *
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "code")
    public Categorie categorie;

    public Publicite() {
    }
}
