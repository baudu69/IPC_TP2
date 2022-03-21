package model;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Biblio {

    /**
     * @param a nombre a inverser
     * @param p modulo
     * @return p^-1[n]
     */
    public Integer invModulo(int a, int p) {
        int[] euclideEtendu = euclideEtendu(a, p);
        if (euclideEtendu[0] == 1) {
            while (euclideEtendu[1] < 0) {
                euclideEtendu[1] += p;
            }
            return euclideEtendu[1];
        } else {
            return null;
        }
    }

    /**
     * Algo euclide etendu
     *
     * @return [r, u, v]
     */
    public int[] euclideEtendu(int a, int b) {
        int r = a;
        int rPrime = b;
        int u = 1;
        int v = 0;
        int uPrime = 0;
        int vPrime = 1;
        int q;
        int rs;
        int us;
        int vs;
        while (rPrime != 0) {
            q = r / rPrime;
            rs = r;
            us = u;
            vs = v;
            r = rPrime;
            u = uPrime;
            v = vPrime;
            rPrime = rs - (q * rPrime);
            uPrime = us - q * uPrime;
            vPrime = vs - q * vPrime;
        }
        return new int[]{r, u, v};
    }

    public boolean isPrimeBetween(int a, int b) {
        return euclideEtendu(a, b)[0] == 1;
    }

    public boolean isPrime(int a) {
        if (a < 2) {
            return false;
        }
        for (int i = 2; i < a / 2; i++) {
            if ((a % i) == 0) {
                return false;
            }
        }
        return true;
    }

    public List<Integer> diviseurPremier(int a) {
        List<Integer> liste = listeEntierPremier(a);
        liste.stream().filter().ma
        for (int i = 0; i < liste.size(); i++) {
            if (a % liste.get(i) != 0) {
                liste.remove(i);
            }
        }
        return liste;
    }

    public int expMod(int baseInt, int baseE, int baseM) {
        BigInteger base = BigInteger.valueOf(baseInt);
        BigInteger e = BigInteger.valueOf(baseE);
        BigInteger m = BigInteger.valueOf(baseM);
        BigInteger result = BigInteger.ONE;
        base = base.mod(m);
        for (int idx = 0; idx < e.bitLength(); ++idx) {
            if (e.testBit(idx)) {
                result = result.multiply(base).mod(m);
            }
            base = base.multiply(base).mod(m);
        }
        return result.intValue();
    }

    public List<Integer> listeEntierPremier(int n) {
        Integer[] tableau = IntStream.range(0,n).boxed().toArray(Integer[]::new);
        for (int i = 2; i < n; i++) {
            if (tableau[i] != 1) {
                int multi = 2;
                while (multi * i < n) {
                    tableau[multi * i] = 1;
                    multi++;
                }
            }
        }
        List<Integer> listePremier = new ArrayList<>();
        for (int i = 2; i < n; i++) {
            if (tableau[i] == 0) {
                listePremier.add(i);
            }
        }
        return listePremier;
    }

    public int generateur(int p) {
        if (isPrime(p)) {
            Map<Integer, Integer> elemPrimitifs;
            Random r = new Random();
            int a;

            do {
                do {
                    a = r.nextInt(p * 2);
                } while (!isPrimeBetween(a, p));

                elemPrimitifs = new HashMap<>();
                for (int i = 0; i <= p-2; i++) {
                    int e = expMod(a, i, p);

                    if (!elemPrimitifs.containsKey(e)) {
                        elemPrimitifs.put(e, i);
                    } else {
                        break;
                    }
                }
            } while (elemPrimitifs.size() < p-1);

            return a;
        }
        throw new IllegalArgumentException("[Générateur] p non premier");
    }
}
