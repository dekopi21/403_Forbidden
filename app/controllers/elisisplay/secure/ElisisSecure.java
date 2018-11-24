package controllers.elisisplay.secure;

import controllers.CRUD;
import controllers.Comptes;
import controllers.elisisplay.internationalisation.ApplicationMessages;


import models.Compte;
import org.apache.commons.lang.NotImplementedException;
import play.mvc.Scope;

import static controllers.elisisplay.secure.Profiles.userType;

/**
 * Controlleur qui joue le même rôle en moins bien du module Secure
 * de Play (surtout en matière de sécurisation des cookies).
 */
public abstract class ElisisSecure extends CRUD {

    /**
     * Utilisateur par défaut affecté à l'utilisateur courant lors de l'échec de certaines opérations.
     */
    public static final UserType defaultUserType = UserType.VISITOR;

    /**
     * Etat par défaut de la session.
     */
    public static final SessionState defaultSessionState = SessionState.UNKNOWN;

    /**
     * Permet de récupérer le type de l'utilisateur actuel.
     *
     * @return Le type de compte de l'utilisateur actuel.
     */
    public static UserType actualUserType() {

        return userType(connectedName());

    }

    /**
     * Clé du Cookie stocké dans le navigateur.
     */
    public static final String cookieUsername = "username";

    /**
     * Clé du cookie du type de l'utilisateur stocké dans le navigateur.
     */
    public static final String cookieTypeUser = "typeUser";

    /**
     * Clé du cookie de l'état de la section stocké dans le navigateur.
     */
    public static final String cookieSessionState = "sessionState";

    /**
     * Initialisation de la session.
     */
    public static void initSession() {
        if (session == null)
            session = new Scope.Session();
        else
            session.clear();
    }

    /**
     * Initialise la session si elle n'existe pas.
     */
    public static void initSessionSafe() {
        if (session == null || session.isEmpty())
            session = new Scope.Session();
    }

    /**
     * Efface la session actuelle.
     */
    public static void sessionClear(){
        initSession();
        session.clear();
    }

    /**
     * Efface la session actuelle si elle contient quelque chose.
     */
    public static void sessionClearSafe(){
        if (!session.isEmpty())
            session.clear();
    }

    /**
     * Verifie si un utilisateur est connecté dans une session.
     * @param username Nom de l'utilisateur à vérifier (Login ou Email).
     * @return <code>true</code> si le nom d'utilisateur correspond à l'utilisateur connecté,
     *         <code>false</code> dans le cas contraire.
     */
    public static boolean isConnected(String username) {
        return java.util.Objects.equals(connectedName(), username);
    }


    /**
     * Retourne le nom de l'utilisateur connecté.
     *
     * //TODO Crypter le cookie stocké.
     */
    public static String connectedName() {
        if (connected())
            return session.get(cookieUsername);

        return null;
    }

    /**
     * Retourne le code de l'utilisateur connecté.
     *
     * @return Le code de l'entité représentant l'utilisateur connecté.
     * @throws NotImplementedException //TODO Doit être surchargé.
     */
    public static String connectedCode() {
        return Comptes.getCode(connectedName());
    }

    /**
     * Vérifie si la session est initialisé
     */
    public static boolean sessionIsInit() {
        return session != null;
    }

    /**
     * @see Scope
     * Raccourci pour vérifier si la session actuelle est vide ou pas.
     * @return <code>true</code> si la session est vide.
     *         <code>false</code> si non.
     */
    public static boolean sessionIsEmpty() {
        return session.isEmpty();
    }

    /**
     * Vérifie si il y a un utilisateur connecté dans la session actuelle.
     * @return <code>true</code> Un utilisateur est connecté dans cette session,
     *         <code>false</code> si non.
     */
    public static boolean connected() {
        return typeUserKnown() && session.get(cookieUsername) != null;

    }

    /**
     * @return Retourne l'état de la session actuelle.
     */
    public static SessionState sessionState() {
        return (session.get(cookieSessionState) != null) ?
                SessionState.valueOf(session.get(cookieSessionState)) : defaultSessionState;
    }

    /**
     * Vérifie si un utilisateur est conneccté dans la session actuellement et vérifies si
     * son compte est correct
     * @return <code>true</code> si un compte existe réellement avec ce Login ou Email,
     *      <code>false</code> dans le cas contraire.
     *
     * //TODO A Surchager.
     */
    public static boolean reallyConnected() {
        return (connected() && Comptes.exists(connectedName()));
    }

    /**
     * Vérifie si le type de l'utilisateur actuel est stocké dans la session.
     * @return Le type de l'utilisateur actuel.
     */
    public static boolean typeUserKnown() {
        return sessionIsInit() && session.get(cookieTypeUser) != null;
    }

    /**
     * Permet d'obtenir l'entité représentant l'utilisateur connecté actuellement.
     * @return Entité de l'utilisateur connecté actuellement.
     * @throws NotImplementedException //TODO A surchager.
     */
    public static Compte user() {
        return Comptes.getUser(connectedName());
    }


    /**
     * Permet de determiner le type de l'utilisateur connecté dans la session.
     * @return Le type de l'utilsateur connecté actuellement.
     */
    public static UserType getActualTypeUser() {
        if (typeUserKnown())
            return userType(session.get(cookieTypeUser));

        else
            return ElisisSecure.defaultUserType;
    }

    /**
     * Appelez lors de la connexion.
     *
     *
     */
    public static void onLoggedIn(String username, String password) {
        //Déjà connecté.
        if (reallyConnected()) {
            flash.error(ApplicationMessages.oftenUserConnected());
            render("errors/MultipleAccountViolation.html");
        }

        //Session contenant un login inexistant.
        if (sessionIsInit() && connected() && !reallyConnected())
            session = null;

        //Session initialisée.
        if (sessionIsInit() && !connected())
            session = null;

        //Session vide.
        //A réécrire. Pourquoi ne pas initialiser la session dès les deux actions précédentes ?
        if (session == null)
            session = new Scope.Session();

        //Le compte existe.
        if (exists(username, password)) {
            //TODO Vérifier si une personne n'est pas déjà connecté actuellement avec ce même compte.
            //TODO Enregistrer l'action de connexion de l'utilisateur.
            //TODO Enregistrer l'utilisateur connecté dans le système.
            //TODO Crypter les cookies.

            session.put(cookieUsername, username);
            session.put(cookieTypeUser, Profiles.userTypeToString(typeUser(username)));
            session.put(cookieSessionState, SessionState.CONNECTED.name());

            //Enregistrement système du nouvel utilisateur.


            //Redirection vers la page d'accueil.
            redirect(Profiles.getDefaultUrl(getActualTypeUser()));
        } else {
            flash.error(ApplicationMessages.badUserInfos());
            //Users.signin();
        }
    }

    /**
     * Permettre de retrouver le type de l'utilisateur à partir des informations de son compte.
     * @param username Le nom de l'utilisateur (Login ou Email).
     * @return Le type de de l'utilisateur.
     * @throws NotImplementedException //TODO A surcharger.
     */
    private static UserType typeUser(String username) {
        return userType(username);
    }

    /**
     * Vérifies l'existence d'un Utilisateur dans le système.
     *
     * @param username Le nom de l'utilisateur (Login ou Email)
     * @param password Le mot de passe du compte de l'utilisateur.
     * @return <code>true</code> si le compte exists,
     *         <code>false</code> dans le cas contraire.
     * @throws NotImplementedException //TODO A surcharger.
     */
    private static boolean exists(String username, String password) {
        return Comptes.exists(username, password);
    }

    /**
     * Pour déconnecter et effacer la session.
     */
    public static void onDisconnected() {

        //Session actif et correcte.
        if (reallyConnected()) {

            //Suppression système de l'utilisateur


            //Nettoyage du cache.
            session.remove(cookieUsername);
            session.remove(cookieTypeUser);
            session.remove(cookieSessionState);
            session.put(cookieSessionState, SessionState.DISCONNECTED.name());
            //session.clear();

            //TODO Effectuer certaines actions, et Afficher la page correspondante.
            Profiles.signin();
        }

        //Données de session erronée.
        if (sessionIsInit() && connected()) {
            session.remove(cookieUsername);
            session.remove(cookieTypeUser);
            //session.clear();

            //TODO Effectuer certaines actions, et Afficher la page correspondante.
            Profiles.signin();
        }

        //Session initialisée.
        if (sessionIsInit() && !connected()) {
            //session.clear();

            //TODO Effectuer certaines actions, et Afficher la page correspondante.
            Profiles.signin();
        }

        //Session non initialisée.
        if (!sessionIsInit()) {
            session = new Scope.Session();

            
            //TODO Effectuer certaines actions, et Afficher la page correspondante.
            redirect(StaticUrls.index);
        }
    }
}
