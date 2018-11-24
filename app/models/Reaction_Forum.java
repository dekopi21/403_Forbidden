package models;

import controllers.elisisplay.model.ElisisModel;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Calendar;

/**
 * Created by SYS-ERREUR on 22/11/2018.
 */
public class Reaction_Forum extends ElisisModel{

    /**
     *
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "code")
    public Compte compte;

    /**
     *
     */
    public String textReactionForum;

    /**
     *
     */
    public boolean etatReactionForum;

    /**
     *
     */
    public Calendar dateReactionForum;

    /**
     *
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "code")
    public Forum forum;
}
