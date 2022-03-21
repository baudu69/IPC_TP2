package tcp;

import model.Biblio;

import java.net.*;
import java.io.*;
import java.util.Random;

public class Client {
    final static int port = 9632;
    private static int p = 7;
    private static int g = 5;

    private static Biblio biblio = new Biblio();

    public static void main(String[] args) {

        Socket socket;

        try {
            Thread.sleep(1000);
            InetAddress serveur = InetAddress.getByName("127.0.0.1");
            socket = new Socket(serveur, port);
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

            Random random = new Random();
            int a = random.nextInt(500);

            System.out.println("Reception de B");
            int B = (int)input.readObject();
            System.out.println("B recu");
            System.out.println("Calcul de A");
            int A = biblio.expMod(g, a, p);
            System.out.println("Envoie de A");
            output.writeObject(A);
            System.out.println("A envoye");
            int secretPartage = biblio.expMod(B, a, p);
            System.out.println("Le secret partage est : " + secretPartage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}