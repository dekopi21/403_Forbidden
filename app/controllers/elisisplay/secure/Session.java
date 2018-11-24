package controllers.elisisplay.secure;

import controllers.CRUD;
import controllers.elisisplay.lang.StringUtils;

/**
 * Gère les opérations spécifiques sur la section.
 */
public class Session extends CRUD {

    public static boolean flash(boolean isError, String error, String success)
    {
        if (isError)
            flash.error(error);

        else
            flash.success(success);

        return isError;
    }

    public static boolean flashError(String error, String success) {
        flash(!StringUtils.isEmpty(error), error, success);

        return !StringUtils.isEmpty(error);
    }

    public static boolean flashSuccess(String error, String success) {
        flash(StringUtils.isEmpty(success), error, success);

        return StringUtils.isEmpty(success);
    }
}
