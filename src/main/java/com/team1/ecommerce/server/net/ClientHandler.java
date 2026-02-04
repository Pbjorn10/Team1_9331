package main.java.com.team1.ecommerce.server.net;

import main.java.com.team1.ecommerce.server.logging.ServerLogger;

import java.io.*;
import java.net.Socket;
public class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void run() {
        try(BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
            String req;
            while((req = in.readLine()) !=null){
                ServerLogger.info( req);

                out.println(req);

            }
        }catch(IOException e){
            ServerLogger.error("Client disconnected.");
        }finally{
            try{
                clientSocket.close();
            }catch(IOException e ){
                e.printStackTrace();
            }
        }
    }
}
