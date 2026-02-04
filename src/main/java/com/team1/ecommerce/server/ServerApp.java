package main.java.com.team1.ecommerce.server;
import main.java.com.team1.ecommerce.server.net.SocketServer;
import java.util.Scanner;

public class ServerApp {

    public static void main(String[] args){
        int port = 8080;
        SocketServer server = new SocketServer(port);

        new Thread(() -> server.start()).start();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Type stop to shutdown");

        while(true){
            String command = scanner.nextLine();
            if("stop".equalsIgnoreCase(command)){
                server.stop();
                break;
            }
        }
        scanner.close();
        System.exit(0);
    }
}
