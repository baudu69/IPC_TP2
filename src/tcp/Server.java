package tcp;

import model.Biblio;

import java.net.*;
import java.io.*;
import java.util.Random;

public class Server extends Thread {

    final static int port = 9632;
    private final Socket socket;

    private final int p = 7;
    private final int g = 5;

    private static Biblio biblio = new Biblio();

    public static void main(String[] args) {
        try {
            ServerSocket socketServeur = new ServerSocket(port);
            System.out.println("Lancement du serveur");
            while (true) {
                Socket socketClient = socketServeur.accept();
                Server t = new Server(socketClient);
                t.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Server(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        traitements();
    }

    public void traitements() {
        try {
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            Random random = new Random();
            int b = random.nextInt(500);
            int B = biblio.expMod(g, b, p);
            System.out.println("B="+B);
            System.out.println("Envoie de B");
            System.out.println("B envoye");
            output.writeObject(B);
            System.out.println("Attente de A");
            int A = (int)input.readObject();
            System.out.println("A="+A);
            int secretPartage = biblio.expMod(A, b, p);
            System.out.println("Le secret partage est : " + secretPartage);
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
