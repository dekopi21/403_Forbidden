package controllers.elisisplay.serial;


import controllers.elisisplay.Systeme;

import java.io.Serializable;

/**
 * Created by Bienvenu on 29/06/2018 in demande_d_installation.
 */
public class MessageContact implements Serializable {

    private static long nbMessages;

    private String codeMessage;

    private String nom;

    private String email;

    private String sujet;

    private String contenu;

    public MessageContact() {
    }

    public MessageContact(String email, String nom, String contenu, String sujet)
    {
        this.email = email;
        this.nom = nom;
        this.contenu = contenu;
        this.sujet = sujet;
        nbMessages = Systeme.messagesContact.size();
        this.codeMessage = "MC" + nbMessages;
        Systeme.messagesContact.add(this);
    }

    public String getCodeMessage() {
        return codeMessage;
    }

    public void setCodeMessage(String codeMessage) {
        this.codeMessage = codeMessage;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
}
