package controllers.elisisplay.controller;

import controllers.CRUD;
import controllers.elisisplay.model.ElisisModel;
import controllers.elisisplay.utils.Utils;
import play.db.jpa.GenericModel;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Représente les actions effectués sur un modèle par son controlleur.
 * <p>
 * Par défaut dans Play, le controlleur d'un modèle a le même nom avec un 's" à la fin.
 * <p>
 * Est différent de <code>ElisisController</code> par le
 * fait que ses principales actions s'effectuent sur le modèle.
 */

public class ElisisModelController extends CRUD {

    /**
     * Classe Modèle|Entité sur lequel les opérations CRUD se feront.
     */
    private Class<? extends ElisisModel> clazz;

    /**
     * Dernier code généré.
     * <p>
     * Correspondant au code de la dernière entité créée de ce modèle.
     */
    private String lastCode;

    /**
     * Construit Le Controleur Modèle à partir de la classe Entité.
     *
     * @param clazz Classe du Modèle|Entité..
     */
    public ElisisModelController(Class<? extends ElisisModel> clazz) {
        this.clazz = clazz;
    }

    /**
     * Supprimez une entité de la classe paramêtre T.
     *
     * @param code Attribut Code de l'entité.
     * @return <code>true</code> si l'entité a été supprimé,
     * <code>false</code> dans le cas contraire.
     */
    public boolean _delete(String code) {
        try {
            return ((ElisisModel) (read(code))).delete() != null;
        } catch (Exception ignored) {
            return false;
        }
    }

    ;

    /**
     * Supprimez une entité de la classe paramêtre T.
     *
     * @param code Attribut Code de l'entité.
     * @param <T>  Classe modèle de l'entité.
     * @return L'entité supprimé.
     */
    public <T extends ElisisModel> T __delete(String code) {
        return ((T) (read(code))).delete();
    }

    ;

    /**
     * Génère le code à attribué à la prochaine entité à enregistrer de ce modèle.
     *
     * @param prefixe Premiers caractères du code.
     * @param nbMax   Nombre maximum de caractères de l'attribut code.
     * @return Le code de la prochaine entité à enregistrer pour ce modèle.
     */
    public String codeGenerated(String prefixe, int nbMax) {
        long count = count();
        String code = "";

        do {
            code = Utils.generateCode(prefixe, ++count, nbMax);
        }
        while (read(code) != null);

        return code;
    }

    /**
     * Génère le code à attribué à la prochaine entité à enregistrer de ce modèle.
     *
     * @param nbMax Nombre maximum de caractères de l'attribut code.
     * @return Le code de la prochaine entité à enregistrer pour ce modèle.
     */
    public String codeGenerated(int nbMax) {
        try {
            //Génère une erreur si une méthode abbvr() n'existe pas dans la classe clazz.
            //Faire en sorte que la méthode mère abbvr() dans ElisisModel soit uilisée dans ce cas.
            return codeGenerated((String) invokeNoParametersMethod("abbvr", clazz.newInstance()), nbMax);
        } catch (InstantiationException | IllegalAccessException ignored) {
            return "" + nbMax;
        }

    }

    /**
     * Lire l'entité dont le code est passé en paramêtre.
     *
     * @param code Code de l'entité (attribut).
     * @return L'entité dont le code est correspondant au paramêtre {@code code}.
     */
    public Object read(String code) {
        return readUnic("code", code);
    }

    /**
     * Lire l'entité dont l'attribut et sa valeur sont passés en paramêtre.
     *
     * @param attribut Attribut à lire de l'entité.
     * @param value    Valeur de l'attribut pour l'entité à lire.
     * @return L'entité dont la valeur de l'attribut correspondant au paramêtre {@value}.
     */
    public Object readUnic(String attribut, String value) {
        return readUnic(attribut, (Object) value);
    }

    /**
     * Lire l'entité dont l'attribut et sa valeur sont passés en paramêtre.
     *
     * @param attribut Attribut à lire de l'entité.
     * @param value    Valeur de l'attribut pour l'entité à lire.
     * @return L'entité dont la valeur de l'attribut correspondant au paramêtre {@value}.
     */
    public Object readUnic(String attribut, Object value) {
        try {
            Method method = clazz.getDeclaredMethod("find", String.class, Object[].class);

            //Objet(s) à envoyer à la méthode find.
            Object[] objects = new Object[1];
            objects[0] = value;

            GenericModel.JPAQuery result = (GenericModel.JPAQuery) method.invoke(null, attribut, objects);
            return result.first();
        } catch (NullPointerException | NoSuchMethodException | IllegalAccessException | InvocationTargetException ignored) {
        }
        return null;
    }

    /**
     * Lire les entité dont l'attribut et sa valeur sont passés en paramêtre.
     *
     * @param attribut Attribut à lire de l'entité.
     * @param value    Valeur de l'attribut pour l'entité à lire.
     * @return Les entité dont la valeur de l'attribut correspond au paramêtre {@value}.
     */
    public <T extends ElisisModel> ArrayList<T> readAll(String attribut, String value) {
        return readAll(attribut, (Object) value);
    }

    /**
     * Lire les entité dont l'attribut et sa valeur sont passés en paramêtre.
     *
     * @param attribut Attribut à lire de l'entité.
     * @param value    Valeur de l'attribut pour l'entité à lire.
     * @return Les entité dont la valeur de l'attribut correspond au paramêtre {@value}.
     */
    public <T extends ElisisModel> ArrayList<T> readAll(String attribut, String value, int nbMax) {
        return readAll(attribut, (Object) value, nbMax);
    }

    /**
     * Lire les entité dont l'attribut et sa valeur sont passés en paramêtre.
     *
     * @param attribut Attribut à lire de l'entité.
     * @param value    Valeur de l'attribut pour l'entité à lire.
     * @return Les entité dont la valeur de l'attribut correspond au paramêtre {@value}.
     */
    public <T extends ElisisModel> ArrayList<T> readAll(String attribut, Object value) {
        try {
            Method method = clazz.getDeclaredMethod("find", String.class, Object[].class);

            //Objet(s) à envoyer à la méthode find.
            Object[] objects = new Object[1];
            objects[0] = value;

            GenericModel.JPAQuery result = (GenericModel.JPAQuery) method.invoke(null, attribut, objects);
            return new ArrayList<T>(result.fetch());
        } catch (NullPointerException | NoSuchMethodException | IllegalAccessException | InvocationTargetException ignored) {
        }
        return null;
    }

    /**
     * Lire les entité dont l'attribut et sa valeur sont passés en paramêtre.
     *
     * @param attribut Attribut à lire de l'entité.
     * @param value    Valeur de l'attribut pour l'entité à lire.
     * @return Les entité dont la valeur de l'attribut correspond au paramêtre {@value}.
     */
    public <T extends ElisisModel> ArrayList<T> readAll(String attribut, Object value, int nbMax) {
        try {
            Method method = clazz.getDeclaredMethod("find", String.class, Object[].class);

            //Objet(s) à envoyer à la méthode find.
            Object[] objects = new Object[1];
            objects[0] = value;

            GenericModel.JPAQuery result = (GenericModel.JPAQuery) method.invoke(null, attribut, objects);
            return new ArrayList<T>(result.fetch(nbMax));
        } catch (NullPointerException | NoSuchMethodException | IllegalAccessException | InvocationTargetException ignored) {
        }
        return null;
    }

    /**
     * Persister un nouvel objet dans la base de données.
     *
     * @param model Objet à persister dans la base de données.
     * @return L'entité dont la valeur de l'attribut correspondant au paramêtre {@value}.
     */
    //Does'nt work. Must debug this.
    public <T extends ElisisModel> boolean create(T model) {
        try {
            Method method = clazz.getDeclaredMethod("create");

            return (boolean) method.invoke(model);
        } catch (NullPointerException | NoSuchMethodException | IllegalAccessException | InvocationTargetException ignored) {
        }
        return false;
    }

    /**
     * Persister un nouvel objet dans la base de données.
     *
     * @param model Objet à persister dans la base de données.
     * @return L'entité dont la valeur de l'attribut correspondant au paramêtre {@value}.
     */
    public <T extends ElisisModel> boolean save(T model) {
        if (exists(model.code))
            return update(model);

        return create(model);
    }

    /**
     * Mettre à jour (modifier) une entité.
     *
     * @param model Objet modifié à mettre à jour.
     * @return L'entité dont la valeur de l'attribut correspondant au paramêtre {@value}.
     */
    public <T extends ElisisModel> boolean update(T model) {
        try {
            Method method = clazz.getDeclaredMethod("save");

            return (boolean) method.invoke(model);
        } catch (NullPointerException | NoSuchMethodException | IllegalAccessException | InvocationTargetException ignored) {
        }
        return false;
    }

    /**
     * Mettre à jour (modifier) une entité.
     *
     * @param model Objet modifié à mettre à jour.
     * @return L'entité dont la valeur de l'attribut correspondant au paramêtre {@value}.
     */
    public <T extends ElisisModel> boolean edit(T model) {
        return update(model);
    }

    /**
     * Obtenir le nombre d'entités enregistrés dans la base de données pour
     * cette classe modèle.
     *
     * @return Le nombre d'enregistrements de ce modèle dans la base de
     * données.
     */
    public long count() {
        try {
            return (long) invokeNoParametersMethod("count");
        } catch (Exception ignored) {
            return -1;
        }
    }

    /**
     * Appeler une méthode de la classe modèle qui ne prend aucun paramêtre.
     *
     * @param methodName Nom de la méthode.
     * @return L'objet retourné par la méthode.
     */
    public Object invokeNoParametersMethod(String methodName) {
        try {
            Method method = clazz.getDeclaredMethod(methodName, (Class<?>[]) null);
            return method.invoke(null, (Object[]) null);
        } catch (NullPointerException | NoSuchMethodException | IllegalAccessException | InvocationTargetException ignored) {
        }
        return null;
    }

    /**
     * Appeler une méthode de la classe modèle qui ne prend aucun paramêtre.
     *
     * @param methodName Nom de la méthode.
     * @param object     Objet sur lequel cette méthode sera appelé.
     * @return L'objet retourné par la méthode.
     */
    public <T extends ElisisModel> Object invokeNoParametersMethod(String methodName, T object) {
        try {
            Method method = clazz.getDeclaredMethod(methodName, (Class<?>[]) null);
            return method.invoke(object, (Object[]) null);
        } catch (NullPointerException | NoSuchMethodException | IllegalAccessException | InvocationTargetException ignored) {
        }
        return null;
    }

    /**
     * Lire l'entité dont le code hashé en Md5 est passé en paramêtre.
     * <p>
     * Est utile dans le navigateur pour ne pas afficher des données trop suggestives.
     *
     * @param <T> Classe modèle de l'entité.
     * @return L'entité dont le code hashé en Md5 est égale au paramêtre
     * {@code code}.
     */
    public <T extends ElisisModel> T readByHashMd5(String hashCode) {
        try {
            List<T> all = (List<T>) invokeNoParametersMethod("findAll");

            ArrayList<ElisisModel> models = new ArrayList<ElisisModel>(all);
            for (ElisisModel model :
                    models) {
                if (controllers.elisisplay.crypto.Utils.isHashMd5(model.getCode(), hashCode))
                    return (T) model;
            }
            return null;
        } catch (Exception ignored) {
            return null;
        }
    }

    /**
     * Lire la dernière entité créée de ce modèle.
     *
     * @param <T> Classe modèle de l'entité.
     * @return La dernière entité modèle la classe modèle {@code T}.
     */
    public <T extends ElisisModel> T readLast() {

        try {
            return (T) read(lastCode);
        } catch (Exception ignored) {
            return null;
        }
    }

    /**
     * Permet de tester l'existence d'un code sans retourner (lire)
     * la valeur de l'entité à laquelle il correspond.
     *
     * @param code Valeur du code dont il faut tester l'existence.
     * @param <T>  Classe modèle de l'entité
     * @return <code>true</code> si l'entité existe,
     * <code>false</code> dans le cas contraire.
     */
    public <T extends ElisisModel> boolean exists(String code) {
        return read(code) != null;
    }

    /**
     * Permet de tester l'existence d'un code sans retourner (lire)
     * la valeur de l'entité à laquelle il correspond.
     *
     * @param field Attribut du modèle dont la valeur est passé en paramêtre.
     * @param value Valeur de l'attribut pour l'entité à lire.
     * @param <T>   Classe modèle de l'entité
     * @return <code>true</code> si l'entité existe,
     * <code>false</code> dans le cas contraire.
     */
    public <T extends ElisisModel> boolean exists(String field, String value) {
        return readUnic(field, value) != null;
    }

    /**
     * Permet de tester l'existence d'un code sans retourner (lire)
     * la valeur de l'entité à laquelle il correspond.
     *
     * @param field Attribut du modèle dont la valeur est passé en paramêtre.
     * @param value Valeur de l'attribut pour l'entité à lire.
     * @param <T>   Classe modèle de l'entité
     * @return <code>true</code> si l'entité existe,
     * <code>false</code> dans le cas contraire.
     */
    public <T extends ElisisModel> boolean exists(String field, Object value) {
        return readUnic(field, value) != null;
    }


    /**
     * @return Le code du dernier enregistrement de ce modèle.
     */
    public String getLastCode() {
        return lastCode;
    }
}
