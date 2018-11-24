package models;

import controllers.elisisplay.model.ElisisModel;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Created by SYS-ERREUR on 22/11/2018.
 */
@Entity
@Table(name = "job")
public class Job extends ElisisModel{
    /**
     *
     */
    public String titreJobs;

    /**
     *
     */
    public String descJobs;

    /**
     *
     */
    public Calendar dateJobs;

    /**
     *
     */
    public boolean etatJob;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "code")
    public Compte compte;

    public Job() {
    }
}
