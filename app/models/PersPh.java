package models;

import controllers.elisisplay.model.ElisisModel;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Created by SYS-ERREUR on 22/11/2018.
 */

@Entity
@Table(name = "personne")
public class PersPh extends ElisisModel{
    /**
     *
     */
    public char sexe;

    /**
     *
     */
    public Calendar datenaissance;

    /**
     *
     */
    public String niveau;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "code")
    public Profil profil;

    public PersPh() {
    }
}
