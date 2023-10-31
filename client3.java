package imen_abid_TP3;

import java.io.*;
import java.net.*;

public class client3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		try {
            Socket socket = new Socket("localhost", 1234);
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            // Recevoir l'ordre de communication du serveur
            int clientNumber = inputStream.readInt();
            System.out.println("Vous êtes le client " + clientNumber);

            // Effectuer d'autres opérations spécifiques au client ici...

            // Fermer les flux et la connexion avec le serveur
            inputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
		

}
