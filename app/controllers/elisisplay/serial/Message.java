package controllers.elisisplay.serial;

import java.io.Serializable;

/**
 * Created by Bienvenu on 22/06/2018 in demande_d_installation.
 * Message que peuvent s'échanger tous les utilisateurs de la plateforme à tout moment.
 * Non persisté dans la base de données.
 */
public class Message implements Serializable {

    public static final String templateCreatingAccountCOmptePEInExists = "Nous n'avons pu vérifier votre compte PE." +
            "\n Les informations entrées sont probablement incorrectes." +
            "\n Réessayez d'associer le compte PE ultérieurement.";

    public String contenu;

    public boolean afficher;

    public boolean lu;

    public String codeDestinataire;

    public String codeEnvoyeur;

    public boolean afficherEnvoyeur;

    //TODO Serialize the message when he is build.
    public Message(String contenu, boolean afficher, boolean lu, String codeDestinataire, String codeEnvoyeur, boolean afficherEnvoyeur) {
        this.contenu = contenu;
        this.afficher = afficher;
        this.lu = lu;
        this.codeDestinataire = codeDestinataire;
        this.codeEnvoyeur = codeEnvoyeur;
        this.afficherEnvoyeur = afficherEnvoyeur;
    }
}
