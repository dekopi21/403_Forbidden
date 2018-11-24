package controllers.elisisplay.io;

import play.Play;

import java.io.*;

/**
 * Created by Bienvenu on 27/08/2018 in controllers.elisisplay.
 */
public class Serialization {

    /**
     * Désérialiser un objet d'un fichier.
     *
     * @param serFile Le fichier contenant l'objet sérialisé.
     * @return L'objet désérialisé.
     */
    public static Object deserialise(File serFile) {
        try {
            ObjectInputStream deserialiser = new ObjectInputStream(new FileInputStream(serFile));
            return deserialiser.readObject();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Désérialiser un objet d'un fichier.
     *
     * @param absolutePath Le chemin absolu vers le fichier dans lequel l'objet a été sérialisé.
     * @return L'objet désérialisé.
     */
    public static Object deserialise(String absolutePath) {
        File serFile = new File(absolutePath);
        try {
            ObjectInputStream deserialiser = new ObjectInputStream(new FileInputStream(serFile));

            //Fermer le flux avant de renvoyer l'objet déserialisé.
            Object _return = deserialiser.readObject();
            deserialiser.close();
            return _return;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Désérialiser un objet d'un fichier à partir d'une application Play.
     *
     * @param relativePlayPath Le chemin relatif (partant de la racine de l'application Play)
     *                         vers le fichier dans lequel l'objet a été sérialisé.
     * @return L'objet désérialisé.
     */
    public static Object playdeserialise(String relativePlayPath) {
        File serFile = new File(Play.applicationPath, relativePlayPath);

        //TODO : Revue - Est ce une mauvaise idée ?
        return deserialise(serFile.getAbsolutePath());
    }

    /**
     * Sérialiser un fichier.
     *
     * @param serFile Le fichier dans lequel sérialiser l'objet.
     * @param serial  L'objet à sérialiser.
     */
    public static void serialise(File serFile, Object serial) {
        try {
            ObjectOutputStream serialiser = new ObjectOutputStream(new FileOutputStream(serFile));
            serialiser.writeObject(serial);
            serialiser.close();
        } catch (FileNotFoundException fnfe) {
            try {
                if (serFile.createNewFile())
                    serialise(serFile, serial);
            } catch (IOException ignored) {
            }
        } catch (Exception ignored) {
        }
    }

    /**
     * Sérialiser un fichier dans une application Play.
     *
     * @param relativePlayPath Le chemin relatif (partant de la racine de l'application Play)
     *                         vers le fichier dans lequel l'objet a été sérialisé.
     * @param serial           L'objet à sérialiser.
     */
    public static void playserialise(String relativePlayPath, Object serial) {
        File serFile = new File(Play.applicationPath, relativePlayPath);
        try {
            if (!serFile.exists()) {
                if (serFile.canWrite() && serFile.createNewFile())
                    serialise(serFile, serial);
            } else
                serialise(serFile, serial);
        } catch (Exception ignored) {
        }
    }


}
