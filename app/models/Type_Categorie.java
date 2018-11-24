package models;

import controllers.elisisplay.model.ElisisModel;

import javax.persistence.*;

/**
 * Created by SYS-ERREUR on 22/11/2018.
 */

@Entity
@Table
public class Type_Categorie extends ElisisModel{

    /**
     *
     */
    public String nomTypeCategorie;

    /**
     *
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "code")
    public Categorie categorie;
}
