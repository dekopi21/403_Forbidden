package controllers.elisisplay.secure;

import controllers.CRUD;
import controllers.elisisplay.controller.NonDefinedControllerSectionException;

import java.util.Arrays;

/**
 * Gestion des profils des utilisateurs.
 *
 * //TODO Rendre cette classe la plus générique possible.
 */
public class Profiles extends CRUD {

    /**
     * Determine si un utilisateur peut accéder à une section (page) de l'application.
     * @param userType Le type de l'utilidateur.
     * @param applicationSection La section (page) concernée.
     * @return <code>true</code> si l'utilisateur peut y accéder,
     *         <code>false</code> dans le cas contraire.
     */
    public static boolean canAccess(UserType userType, ApplicationSection applicationSection) throws AccessObjectNullException {
        if (userType == null || applicationSection == null)
            throw new AccessObjectNullException();
        switch (userType) {
            case SUPADMIN:
                //A revoir. Le super admin ne devrait pas avoir les autorisations pour les
                //sections Dev et Debug.
                return true;

            case ADMIN:
                switch (applicationSection) {
                    case PUBLIC:
                    case EDITOR:
                    case ADMIN:
                        return true;

                    default:
                        return false;
                }

            case VISITOR:
                switch (applicationSection) {
                    case PUBLIC:
                    case AUTHENTIFICATION:
                        return true;

                    default:
                        return false;
                }

            case DEV:
                // Utile pour naviguer entre les différentes sections sans utiliser le jeu de déconnexion/reconnexion.
                return true;

            case EDITOR:
                switch (applicationSection) {
                    case PUBLIC:
                    case EDITOR:
                        return true;

                    default:
                        return false;
                }
            case MAINTAINER:
                switch (applicationSection) {
                    case PUBLIC:
                    case EDITOR:
                    case ADMIN:
                    case AUTHENTIFICATION:
                        return true;

                    default:
                        return false;
                }
            default:
                return false;
        }
    }

    /**
     * Gère l'accès à une section de l'application par l'utilisateur connecté actuellement.
     *
     * Doit être appeler avant l'accès à toutes les pages sécurisées de l'application.
     *
     * @param applicationSection Section de l'application à laquelle l'utilisateur veut accéder.
     * @return <code>true</code> si l'utilisateur peut accéder à cette section,
     *         <code>false</code> dans le cas contraire
     */
    public static boolean canSafelyAccess(ApplicationSection applicationSection) throws NonDefinedControllerSectionException {
        if (applicationSection == null)
            throw new NonDefinedControllerSectionException();
        boolean ok = true;
        try {
            if (!canAccess(ElisisSecure.actualUserType(), applicationSection)) {
                ok = false;
                //TODO Enregistrer l'erreur et afficher la vue ou le message approprié.
                render("errors/AccessViolation.html");
            }
            //Programmation défensive. Si une erreur se produit lors des actions précédentes.
        } catch (Exception e) {
            ok = false;
            //TODO Enregistrer l'erreur et afficher la vue ou le message approprié.
        }
        return ok;

    }

    public static String userTypeToString(UserType userType) {
        return userType.name();
    }

    /**
     * Retourne le type de l'utilisateur dont le nom est passé en
     * paramêtre.
     * @see Enum
     * @param userTypeName Le nom de la constance de l'énumération du
     *                     type d'utilisateur.
     * @return L'énumération du type d'utilisateur.
     * @throws IllegalArgumentException, si le nom ne correspond pas exactement
     * au nom de la constance de l'énumération.
     * @throws NullPointerException si {@code userTypeName} est nul.
     */
    public static UserType userType(String userTypeName) {
        try {
            return UserType.valueOf(userTypeName);
        }
        catch (Exception e)
        {
            return (UserType.values().length > 0) ? Arrays.asList(UserType.values()).get(0) : null;
        }
    }

    /**
     * Retourne l'url par défaut suivant l'utilisateur.
     *
     * @param userType Type de l'utilisateur.
     * @return L'url par défaut de cet utilisateur.
     */
    public static String getDefaultUrl(UserType userType) {

        if (userType == null)
            return StaticUrls.index;

        switch (userType) {
            case DEV:
                return StaticUrls.editorIndex;
            case SUPADMIN:
                return StaticUrls.editorIndex;
            case ADMIN:
                return StaticUrls.editorIndex;
            case VISITOR:
                return StaticUrls.index;
            case EDITOR:
                return StaticUrls.editorIndex;
            case MAINTAINER:
                return StaticUrls.editorIndex;
            default:
                return StaticUrls.index;
        }
    }

    /**
     * Retourne l'Url par défaut de l'utilisateur actuel.
     * @return l'Url par défaut de l'utilisateur connecté.
     */
    public static String getDefaultUrl() {
        return getDefaultUrl(ElisisSecure.actualUserType());
    }

    /**
     * Accéder à la page par défaut de l'utilisateur actuel.
     */
    public static void goToDefaultUrl() {
        redirect(getDefaultUrl());
    }

    /**
     * Page de connexion.
     *
     * //TODO Sécuriser cette page. Un utilisateur déjà connecté est renvoyé vers sa page par défaut lorsqu'il essai
     * //d'y accéder.
     */
    public static void signin() {
        /*
        Users.signin();
        */
    }

    /**
     * Page d'inscription.
     *
     * //TODO Sécuriser cette page. Un utilisateur déjà connecté est renvoyé vers sa page par défaut lorsqu'il essai
     * //d'y accéder.
     */
    public static void signup() {
        /*
        Users.signup();
        */
    }

    public static boolean secure() {
        return Profiles.canSafelyAccess(ApplicationSection.AUTHENTIFICATION);
    }


}
