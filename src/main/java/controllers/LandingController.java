package controllers;

import spark.Request;
import spark.Response;

import java.util.Optional;

public class LandingController {

    public static String respond(Request req, Response res) {
        String role = Optional.ofNullable((String) req.session().attribute("role")).orElse("guest");

        switch (role){
            case "admin":
                return showAdminLanding(req, res);
            case "user":
                return showUserLanding(req, res);
            case "guest":
                return showLoginPage(req, res);
        }
        return null;
    }

    private static String showLoginPage(Request req, Response res) {
        res.redirect("/login");
        return null;
    }

    private static String showUserLanding(Request req, Response res) {
        res.redirect("/user");
        return null;
    }

    private static String showAdminLanding(Request req, Response res) {
        res.redirect("/admin");
        return null;
    }


}
