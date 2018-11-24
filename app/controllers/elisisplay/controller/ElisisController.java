package controllers.elisisplay.controller;

import controllers.elisisplay.secure.ApplicationSection;
import controllers.elisisplay.secure.Profiles;
import play.mvc.Before;

/**
 * Un controleur Play avec quelques fonctionnalités en plus.
 * <p>
 * Est différent de <code>ElisisModelController</code> par le
 * fait que ses principales actions s'opérent sur les vues.
 */
public class ElisisController {

    /**
     * Utilisez par la classe fille pour définir la section accessible de l'application par ce controlleur.
     */
    @SuppressWarnings("WeakerAccess")
    private ApplicationSection mySection = null;

    /**
     * Sécurise l'accès aux Vues rendues par cette page.
     *
     * @return <code>true</code> si l'utilisateur peut accèder à la vue correspondante.
     * <p>
     * Ce retour n'est utile que pour ne pas avoir pour signature à cette méthode,
     * le "public static void" qui est utilisé dans les controlleurs pour
     * retourner les vues.
     */
    public boolean secure() {
        return Profiles.canSafelyAccess(mySection);
    }

    /**
     * Méthode de classe qui sécurise l'accès à une vue suivant la section
     * qui lui est passé entre paramêtres.
     *
     * @return <code>true</code> si l'utilisateur peut accèder à la vue correspondante.
     * <p>
     * Ce retour n'est utile que pour ne pas avoir pour signature à cette méthode,
     * le "public static void" qui est utilisé dans les controlleurs pour
     * retourner les vues.
     */
    public static boolean secure(ApplicationSection applicationSection) {
        return Profiles.canSafelyAccess(applicationSection);
    }

    /**
     * Securiser toutes les actions dans ce controlleur.
     * <p>
     * //TODO Voir si cela peut avoir un impact sur la performance.
     */
    //@Before
    public void secureAll() {
        secure();
    }

    /**
     * Section du controlleur.
     * <p>
     * Doit être surchargée.
     *
     * @return La section de ce controlleur.
     */
    public ApplicationSection mySection() {
        return mySection;
    }

    public ElisisController(ApplicationSection controllerSection) {
        this.mySection = controllerSection;
    }

}
