package models;

import controllers.elisisplay.model.ElisisModel;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Created by SYS-ERREUR on 22/11/2018.
 */
@Entity
@Table(name = "article")
public class Article  extends ElisisModel {
    /**
     *
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "code")
    public Compte compte;
    /**
     * nom d'un article
     */
    public String nomArt;

    /**
     * description d'un article
     */
    public String descrArt;

    /**
     * date de poste de l'Aeticle
     */
    @Column(name = "date_publication")
    public Calendar datePublication;

    /**
     * image d'un article
     */
    public String imageArt;
    public enum etatArt{

    }
    public Article() {
    }
}
