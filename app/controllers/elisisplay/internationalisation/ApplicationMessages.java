package controllers.elisisplay.internationalisation;

import controllers.elisisplay.Elisis;

/**
 * Définit les messages affichés suivant le language actuel de l'application.
 *
 * Pilier de l'internalisation.
 */

public class ApplicationMessages {

    /**
     * @return Le message de violation de l'accès à une section.
     */
    public static String sectionAccessViolation()
    {
        switch (Elisis.actualLanguage)
        {
            case FRANCAIS:
                return StaticMessages.accountViolationFr;

            default:
                return StaticMessages.accountViolationFr;
        }
    }

    /**
     * @return Le message indiquant qu'un utilisateur est déjà connecté.
     */
    public static String oftenUserConnected()
    {
        switch (Elisis.actualLanguage)
        {
            case FRANCAIS:
                return StaticMessages.oftenUserConnectedFr;

            default:
                return StaticMessages.oftenUserConnectedFr;
        }
    }

    /**
     * @return Le message indiquant qu'un utilisateur est déjà connecté.
     */
    public static String badUserInfos()
    {
        switch (Elisis.actualLanguage)
        {
            case FRANCAIS:
                return StaticMessages.userBadInfosFr;

            default:
                return StaticMessages.userBadInfosFr;
        }
    }
}
