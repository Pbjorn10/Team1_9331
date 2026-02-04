package main.java.com.team1.ecommerce.server.logging;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class ServerLogger {
    public static void info(String message){
        log("INFO",message);
    }
    public static void error(String message){
        log("ERROR", message);
    }

    private static synchronized void log(String level, String message){
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.println("[" + level + "] " + message);
        System.out.println("[" + time + "] " + message);
    }
}
