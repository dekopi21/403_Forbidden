package models;

import controllers.elisisplay.model.ElisisModel;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by SYS-ERREUR on 22/11/2018.
 */
public class Forum extends ElisisModel{
    /**
     *
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "code")
    public Compte compte;

    /**
     *
     */
    public String titreForum;

    /**
     *
     */
    public String descForum;

    /**
     *
     */
    public boolean etatForum;

    /**
     *
     */
    public Calendar dateForum;

    public Forum() {
    }
}
