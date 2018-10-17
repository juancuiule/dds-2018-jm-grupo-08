package server;

import controllers.LandingController;

import static spark.Spark.get;

public class Server {
    public static void main(String[] args) {
        get("/", LandingController::respond);
    }
}
