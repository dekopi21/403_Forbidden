package controllers.elisisplay.logging;

/**
 * Types de messages affichés à l'utlisateur.
 *
 * Favorise l'internalisation.
 */
public enum TypeLogMessageError {
    UNKNOWERROR(Level.DEBUG, "UNKNOWERROR", "Erreur inconnue."),
    NOERROR(Level.FINE, "NOERROR", ""),
    FATALERROR(Level.FATAL, "FATALERROR", "Une erreur fatale s'est produite."),
    FILENOTFOUNDERROR(Level.SEVERE, "FILENOTFOUNDERROR", "Ce fichier n'existe pas."),
    PERFORMIOACTIONERROR(Level.SEVERE, "PERFORMIOACTIONERROR", "L'action sur le système de fichiers a échoué."),
    CLASSNOTFOUNDERROR(Level.DEBUG, "CLASSNOTFOUNDERROR", "La classe n'a pas été trouvé."),
    PERSISTENCEERROR(Level.DEBUG, "PERSISTENCEERROR", "Une erreur de persistence s'est produite."),
    SAVINGENTITYERROR(Level.DEBUG, "SAVINGENTITYERROR", "L'entité n'a pu être persisté."),
    JPAQUERYERROR(Level.DEBUG, "JPAQUERYERROR", "L'exécution de la requête a échoué."),
    UPDATEENTITYERROR(Level.DEBUG, "UPDATEENTITYERROR", "Une erreur s'est produite lors de la mise à jour de cette entité."),
    DELETEENTITYERROR(Level.DEBUG, "DELETEENTITYERROR", "Une erreur s'est produite lors de la suppression de cette entité."),
    JOBPERFORMINGERROR(Level.DEBUG, "JOBPERFORMINGERROR", "Une erreur s'est produite lors de l'execution d'une tâche."),
    CACHEACCESSERROR(Level.DEBUG, "CACHEACCESSERROR", "Une erreur s'est produite lors de l'accès à une donnée dans le cache."),
    EMAILCHECKINGERROR(Level.DEBUG, "EMAILCHECKINGERROR", "Une erreur s'est produite lors d'une opération sur cette adresse Mail.");

    TypeLogMessageError(Level level, String name, String message)
    {
        this.level = level;
        this.name = name;
        this.message = message;
    }

    private String name;

    private Level level;

    private String message;

    public String getName() {
        return name;
    }

    public Level getLevel() {
        return level;
    }

    public String getMessage() {
        return message;
    }
}
