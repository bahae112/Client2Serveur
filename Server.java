import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        int port = 12345; // Port d'écoute

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Serveur en attente de connexions sur le port " + port);

            for (int i = 1; i <= 2; i++) { // Accepter exactement 2 clients
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client " + i + " connecté : " + clientSocket.getInetAddress());

                // Flux d'entrée et de sortie
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("Message reçu du client " + i + " : " + message);

                    if (message.equalsIgnoreCase("fin")) {
                        System.out.println("Fin de la connexion demandée par le client " + i + ".");
                        break;
                    }

                    // Répondre au client
                    out.println("Message bien reçu : " + message);
                }

                System.out.println("Déconnexion du client " + i + ".");
                in.close();
                out.close();
                clientSocket.close();
            }

            System.out.println("Les deux clients ont été servis. Fermeture du serveur.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
