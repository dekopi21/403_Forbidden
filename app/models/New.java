package models;

import controllers.elisisplay.model.ElisisModel;

import javax.annotation.Resource;
import javax.persistence.*;
import java.util.Calendar;

/**
 * Created by SYS-ERREUR on 22/11/2018.
 */
@Entity
@Table(name = "news")
public class New  extends ElisisModel{

    /**
     *
     */
    public String titreNew;

    /**
     *
     */
    public String descNew;

    /**
     *
     */
    public String textNew;

    /**
     *
     */
    public Calendar dateNew;

    /**
     *
     */
    public boolean etatNew;

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
    public Categorie categorie;

    public New() {
    }
}
