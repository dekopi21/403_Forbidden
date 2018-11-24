package controllers;

import controllers.elisisplay.controller.ElisisModelController;
import controllers.elisisplay.lang.StringUtils;
import controllers.elisisplay.model.ElisisModel;
import models.Compte;

import java.util.ArrayList;

public class Comptes extends CRUD {

    /**
     * Classe modèle.
     */
    private static Class<? extends ElisisModel> clazz = Compte.class;

    /**
     * Classe qui effectuera les opérations classiques sur le modèle.
     */
    public static ElisisModelController model = new ElisisModelController(clazz);

    public static ArrayList<Compte> blogs() {
        return new ArrayList<>(models.Compte.findAll());
    }

    public static String addBlog(Compte blog) {
        /*
        if (StringUtils.oneisEmpty(blog.name, blog.description))
            return "Les données entrées sont incorrectes.";

        else {
            if (new models.blog.Blog(Blogs.model.codeGenerated(ElisisModel.defaultCodeLength), blog.name, blog.description).create())
                return "";

            else
                return "Le blog n'a pu être ajouté." +
                        "Réésayez plus tard.";
        }
        */
        return "";
    }

    public static Compte getUser(String connectedName) {
        return new Compte();
    }

    public static boolean exists(String username, String password) {
        return true;
    }

    public static boolean exists(String connectedName) {
        return true;
    }

    public static String getCode(String connectedName) {
        return Compte.find("byLogin",connectedName).toString();
    }
}
