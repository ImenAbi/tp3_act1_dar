package imen_abid_TP3;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

public class serveur3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ServerSocket serverSocket = null;
        AtomicInteger clientCounter = new AtomicInteger(0);

        try {
            serverSocket = new ServerSocket(1234);
            System.out.println("Serveur en attente de connexions...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                int clientNumber = clientCounter.incrementAndGet();
                System.out.println("Nouvelle connexion du client " + clientNumber + " depuis " + clientSocket.getRemoteSocketAddress());

                // Créer un thread pour gérer la communication avec le client
                ClientHandler clientHandler = new ClientHandler(clientSocket, clientNumber);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket;
    private int clientNumber;

    public ClientHandler(Socket clientSocket, int clientNumber) {
        this.clientSocket = clientSocket;
        this.clientNumber = clientNumber;
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());

            // Envoyer à chaque client son ordre de communication
            outputStream.writeInt(clientNumber);
            outputStream.flush();

            // Attendre les opérations spécifiques au client ici...

            // Fermer les flux et la connexion avec le client
            inputStream.close();
            outputStream.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	

	

}
