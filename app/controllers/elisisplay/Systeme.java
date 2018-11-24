package controllers.elisisplay;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Bienvenu on 02/07/2018 in demande_d_installation.
 */
public final class Systeme {

    /**
     * Utiliser pour générer des nombres aléatoires lors de certaines opérations.
     */
    public static Random random = new Random(100000);

    /**
     * j : Jour
     * s : S
     * m : Minute
     * h : heure
     * M : Mois
     * a : année
     */
    public final static String defaultObjectExpireTime = "90j";

    /**
     * Nom de l'Application/Organisation).
     */
    public final static String appName = "";

    /**
     * Mail prinicipal de l'Application/Organisation.
     */
    public final static String appEmail = "";

    /**
     * Gestionnaire des Messages de sur le formulaire de contact de l'application.
     */
    public static ArrayList<controllers.elisisplay.serial.MessageContact> messagesContact = new ArrayList<>();


}

