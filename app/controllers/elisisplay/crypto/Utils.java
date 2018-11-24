package controllers.elisisplay.crypto;

import java.util.Objects;

import static controllers.elisisplay.utils.Utils.hashMD5;

/**
 * Created by Bienvenu on 01/09/2018 in controllers.elisisplay.
 */
public class Utils {

    public static boolean isHashMd5(String compare, String hashed) {
        return Objects.equals(hashMD5(compare), hashed);
    }

    public static String hashMd5(String toHash)
    {
        return play.libs.Codec.hexMD5(toHash);
    }
}
