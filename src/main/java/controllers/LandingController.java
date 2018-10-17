package controllers;

import spark.Request;
import spark.Response;

import java.util.Optional;

public class LandingController {

    public static String respond(Request req, Response res) {
        String role = Optional.ofNullable((String) req.session().attribute("role")).orElse("guest");

        switch (role){
            case "admin":
                res.redirect("/admin");
            case "user":
                res.redirect("/user");
            case "guest":
                res.redirect("/login");
        }
        return null;
    }

}
