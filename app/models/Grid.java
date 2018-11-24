package models;

import controllers.elisisplay.model.ElisisModel;

import javax.persistence.*;

/**
 * Created by SYS-ERREUR on 22/11/2018.
 */

@Entity
@Table(name = "grid")
public class Grid extends ElisisModel{

    /**
     *
     */
    public String nameGrid;

    /**
     *
     */
    public String labelGrid;

    /**
     *
     */
    public String styleGrid;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "code")
    public Compte compte;

}
