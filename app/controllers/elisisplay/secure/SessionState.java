package controllers.elisisplay.secure;

/**
 * Etat d'une session utilisateur.
 */
public enum SessionState {

    /**
     * Session non initialisée.
     */
    UNKNOWN,

    /**
     * Session connectée et active.
     */
    CONNECTED,

    /**
     * Session connectée non active.
     */
    DISCONNECTED,

    /**
     * Session conectée, mais verrouillée.
     */
    LOCKED,
}
