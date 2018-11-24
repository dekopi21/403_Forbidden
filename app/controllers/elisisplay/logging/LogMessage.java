package controllers.elisisplay.logging;

import java.io.Serializable;
import java.util.GregorianCalendar;

/**
 * Objet Message utilisé pour faciliter le Logging.
 */
public class LogMessage implements Serializable{

    public static final long serialVersionUID = 1350092951346723538L;

    public Level level;

    public String message;

    public String stackMessage;

    public GregorianCalendar dateMessage;

    public LogMessage(Level level, String message, Throwable throwable) {
        this.level = level;
        this.message = message;
        this.stackMessage = throwable.getLocalizedMessage();
        this.dateMessage = new GregorianCalendar();
    }

    public LogMessage(Level level, String message) {
        this.level = level;
        this.message = message;
        this.dateMessage = new GregorianCalendar();
    }

    public LogMessage(TypeLogMessageError typeError, Throwable throwable)
    {
        this.level = typeError.getLevel();
        this.message = typeError.getMessage();
        this.dateMessage = new GregorianCalendar();
        this.stackMessage = throwable.getLocalizedMessage();
    }

    public LogMessage(TypeLogMessageError typeError)
    {
        this.level = typeError.getLevel();
        this.message = typeError.getMessage();
        this.dateMessage = new GregorianCalendar();
    }
}
