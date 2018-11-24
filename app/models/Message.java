package models;

import controllers.elisisplay.model.ElisisModel;

import javax.persistence.*;
import java.util.GregorianCalendar;

/**
 * Created by SYS-ERREUR on 22/11/2018.
 */
@Entity
@Table(name = "message")
public class Message  extends ElisisModel {
    /**
     *
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "code")
    public Compte compte;

    /**
     *
     */
    public String expMessage;

    /**
     *
     */
    public String descMessage;

    /**
     *
     */
    @Column(nullable = false, unique = true)
    public String titreMessage;

    /**
     *
     */
    public GregorianCalendar dateExpMessage;

    /**
     *
     */
    public String textMessage;

    /**
     *
     */
    public boolean etatMessage;

}
