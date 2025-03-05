package com.macedo.issdemo;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        ISSServer server = new ISSServer();
        DataHandler handler = new DataHandler(server.getSession());
        ISSClient client = new ISSClient();

        while(true){
            handler.updateTopics();
            TimeUnit.SECONDS.sleep(3);
            client.showISSInfo();
        }
    }
}
