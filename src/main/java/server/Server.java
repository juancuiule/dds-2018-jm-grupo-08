package server;

import spark.debug.DebugScreen;

public class Server {
    public static void main(String[] args) {
        Router.init();
        DebugScreen.enableDebugScreen();
    }


}
