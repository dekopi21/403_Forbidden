package controllers.elisisplay.utils;
import java.util.ArrayList;

/**
 * Created by Bienvenu on 12/07/2018 in demande_d_installation.
 */
public class Utils {

    public static String hashMD5(String toHash)
    {
        return play.libs.Codec.hexMD5(toHash);
    }

    public static ArrayList<Object> inverse(ArrayList<Object> toinverse)
    {
        ArrayList<Object> inverse = new ArrayList<Object>();
        for (int i = toinverse.size() - 1; i >=0 ; i--) {
            inverse.add(toinverse.get(i));
        }
        return inverse;
    }

    public static String booleanStr(boolean value)
    {
        return (value) ? "Oui" : "Non" ;
    }

    public static String generateCode(String prefixe, int suffixe, int nbMax)
    {
        StringBuilder value = new StringBuilder(String.valueOf(suffixe));
        while (value.length() + prefixe.length() < nbMax)
            value.insert(0, "0");
        return prefixe + value;
    }

    public static String generateCode(String prefixe, long suffixe, int nbMax)
    {
        StringBuilder value = new StringBuilder(String.valueOf(suffixe));
        while (value.length() + prefixe.length() < nbMax)
            value.insert(0, "0");
        return prefixe + value;
    }

    public static String generateCode(String prefixe, String suffixe, int nbMax)
    {
        StringBuilder value = new StringBuilder(suffixe);
        while (value.length() + prefixe.length() < nbMax)
            value.insert(0, "0");
        return prefixe + value;
    }
}
