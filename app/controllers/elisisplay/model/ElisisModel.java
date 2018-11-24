package controllers.elisisplay.model;

import play.db.jpa.Model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * Classe de base des modèles|entités.
 * <p>
 * TODO : Etudier la possibilité et l'utilité de générer automatiquement les
 * controleurs (avec une implémentation basique) des modèles
 * héritant de cette classe.
 */
@MappedSuperclass
public class ElisisModel extends Model implements IElisisModel, Serializable {

    /**
     * Clé de l'entité utilisé en remplacement de l'id automatique
     * de Play, jugé trop imprévisible.
     */
    @Column(unique = true, length = defaultCodeLength, nullable = false)
    public String code;

    /**
     * Contructeur public sans paramêtres.
     */
    public ElisisModel() {
    }

    public String getCode() {
        return code;
    }

    /**
     * Abbréviation utilisée lors de la génération du code de l'entité.
     * @return L'abbréviation du nom de cette entité.
     */
    public String abbvr() {
        return "EM";
    }

    @Transient
    public static final byte defaultCodeLength = 15;
}
