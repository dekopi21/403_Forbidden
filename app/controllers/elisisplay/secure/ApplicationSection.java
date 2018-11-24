package controllers.elisisplay.secure;

/**
 * Définit les différentes sections de l'application découpées suivant qui peut y accéder.
 *
 * Doit être modifier ou réimplémenter.
 */
public enum ApplicationSection {
    /**
     * Section d'administration du site.
     */
    ADMIN,

    /**
     * Section publique de l'application , accessible à tout le monde.
     */
    PUBLIC,

    /**
     * Pages de Connexion/Inscription et autres sur avec des opérations d'authentification.
     */
    AUTHENTIFICATION,

    /**
     * Section Editeur du site.
     */
    EDITOR,

    /**
     * Affiche les informations spécifiques de debug.
     *
     * Utilisées pendant la période de développement.
     */
    DEBUG,

    /**
     * Section de développemnt de l'applicaiton.
     */
    DEV
}
