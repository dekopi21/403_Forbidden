package models;

import controllers.elisisplay.model.ElisisModel;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Calendar;

/**
 * Created by SYS-ERREUR on 22/11/2018.
 */
@Entity
@Table(name = "ouvrages")
public class Ouvrage extends ElisisModel{

    /**
     *
     */
    public String titreOuvrage;

    /**
     *
     */
    public String descOuvrage;

    /**
     *
     */
    public Calendar dateOuvrage;
}
