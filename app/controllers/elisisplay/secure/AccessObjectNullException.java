package controllers.elisisplay.secure;

/**
 * Created by Bienvenu on 27/08/2018 in controllers.elisisplay.
 */
public class AccessObjectNullException extends NullPointerException {

    private static final long serialVersionUID = 4162710183389028782L;

    /**
     * Contruit un {@code AccessObjectNullException} avec aucun message.
     */
    public AccessObjectNullException() {
        super();
    }

    /**
     * Construit un {@code AccessObjectNullException} avec le
     * message spécifié.
     *
     * @param   s   Le message de l'exception.
     */
    public AccessObjectNullException(String s) {
        super(s);
    }
}
