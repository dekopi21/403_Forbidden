package controllers.elisisplay.logging;

/**
 * Interface qui définit les méthodes de Logging principales.
 */
public interface ILogger {

    /**
     *  Logger un message avec le Level FINE.
     */
    public void fine();

    /**
     *  Logger un message avec le Level DEBUG.
     */
    public void debug();

    /**
     *  Logger un message avec le Level CONFIG.
     */
    public void config();

    /**
     *  Logger un message avec le Level INFO.
     */
    public void info();

    /**
     *  Logger un message avec le Level WARNING.
     */
    public void warning();

    /**
     *  Logger un message avec le Level SEVERE.
     */
    public void severe();

    /**
     *  Logger un message avec le Level FATALE.
     */
    public void fatal();

    /**
     * Fermer la ressource (support d'enregistrement des logs) de logging.
     */
    public void close();

    /**
     * Utile pour enregistrer les Logs ajoutés mais non enregistrés dans
     * la ressource (support d'enregistrement des logs) de logging.
     */
    public void flush();

    /**
     * Initialiser la ressource (support d'enregistrement des logs) de logging.
     */
    public void init();
}
