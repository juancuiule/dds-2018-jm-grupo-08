package server;

import controllers.LandingController;
import controllers.LoginController;

import static spark.Spark.get;
import static spark.Spark.post;

public class Server {
    public static void main(String[] args) {
        get("/", LandingController::respond);
        get("/login", LoginController::respond);
        post("/login", LoginController::react);
    }
}
