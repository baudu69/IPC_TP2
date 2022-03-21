import model.Biblio;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class index {
    public static void main(String[] args) throws IOException {
        System.out.println("----------------TP2 : Cryptogrqphie asymetrique ------------------------");
        boiteAOutils();

    }

    public static void boiteAOutils() throws IOException {
        System.out.println("***************** Exercice 1 : Boite a outils ****************");
        InversionModulaire();
        System.out.println();
        listeEntierPremier();
        System.out.println();
        generateur();
        System.out.println();
        testIsPrime();
    }

    public static void InversionModulaire() {
        System.out.println("-- Inversion Modulaire : --");
        int p=365;
        int n=16;
        System.out.println("p="+p+", n="+n);
        Biblio biblio = new Biblio();
        System.out.println("Inverse : " + biblio.invModulo(p,n));
    }

    public static void listeEntierPremier() throws IOException {
        System.out.println("-- Liste des entiers premiers --");
        Biblio biblio = new Biblio();
        FileOutputStream outputStream = new FileOutputStream("nbrPremiers.txt");
        byte[] strToBytes = biblio.listeEntierPremier(1000000).toString().getBytes();
        outputStream.write(strToBytes);
        outputStream.close();
        System.out.println("La liste des entiers premiers est enregistre dans le fichier nbrePremiers.txt");
    }

    public static void generateur() {
        int p = 163567;
        System.out.println("-- Générateur-- ");
        Biblio biblio = new Biblio();
        System.out.println("Dans Z/"+ p + "Z : " + biblio.generateur(p));
    }

    public static void testIsPrime() {
        System.out.println("-- Test si nombre est premier --");
        Biblio biblio = new Biblio();
        for (int i = 0; i < 10; i++) {
            System.out.print("i="+i+": " + biblio.isPrime(i)+ ", ");
        }
    }
}
