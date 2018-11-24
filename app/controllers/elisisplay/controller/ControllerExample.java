package controllers.elisisplay.controller;

import controllers.elisisplay.secure.ApplicationSection;

/**
 * Un exemple de controlleur par défaut utilisant l'architecture de ElisisPlay.
 */
public class ControllerExample {

    /**
     * Classe du modèle de ce controlleur.
     */
    //private static Class<? extends ElisisModel> clazz = ModelExample.class;

    /**
     * Section de ce controlleur.
     */
    private static ApplicationSection section = ApplicationSection.PUBLIC;

    /**
     * Classe qui effectuera certaines actions de base du controlleur (sécurité...).
     */
    public static ElisisController controller = new ElisisController(section);

    /**
     * Classe qui effectuera les opérations classiques sur le modèle.
     */
    //public static ElisisModelController model = new ElisisModelController(clazz);


}
