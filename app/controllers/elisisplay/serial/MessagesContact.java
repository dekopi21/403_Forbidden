package controllers.elisisplay.serial;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Bienvenu on 21/08/2018 in demande_d_installation.
 */
public class MessagesContact implements Serializable{

    public static final long serialVersionUID = 1350092881346723512L;

    public ArrayList<MessageContact> messagesContacts = new ArrayList<>();
}
