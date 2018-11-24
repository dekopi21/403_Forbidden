package models;

import controllers.elisisplay.model.ElisisModel;

import javax.persistence.*;
import java.util.GregorianCalendar;

/**
 * Created by SYS-ERREUR on 22/11/2018.
 */

@Entity
@Table(name = "posts")
public class Post extends ElisisModel{
    /**
     *
     */
    public String textPost;

    /**
     *
     */
    public boolean etatPost;
    /**
     *
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "code")
    public Compte compte;

    public Post() {
    }
}
