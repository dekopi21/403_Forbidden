package controllers.elisisplay.logging;

import controllers.elisisplay.serial.ElisisSerial;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Objet utilisé pour le logging.
 */
public class LoggerObject extends ElisisSerial implements Serializable{

    public static final long serialVersionUID = 1350042881346723571L;

    /**
     *
     */
    protected ArrayList<LogMessage> logMessages = new ArrayList<>();

    public ArrayList<LogMessage> getLogMessages() {
        return logMessages;
    }

    /**
     *
     */
    public void init(){
        if (logMessages == null)
            logMessages = new ArrayList<>();
    }

    /**
     *
     * @param logMessage
     */
    public void addLogMessage(LogMessage logMessage)
    {
        init();
        logMessages.add(logMessage);
    }

    /**
     *
     * @param logMessageCollection
     */
    public void addAllLogMessage(Collection<LogMessage> logMessageCollection)
    {
        init();
        logMessages.addAll(logMessageCollection);
    }
}
