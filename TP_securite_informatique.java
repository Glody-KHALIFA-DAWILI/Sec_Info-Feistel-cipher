/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp_securite_informatique;

/**
 *
 * @author Glody KHALIFA DAWILI
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.util.List;

public class TP_securite_informatique {

    /**
     * @param args the command line arguments
     */
    public static int[] inver(int[] pi) {
        int[] Tempo = new int[pi.length];
        for (int i = 0; i < pi.length; i++) {
            int j = 0;
            boolean bl = false;
            do {
                if (pi[j] == i) {
                    bl = true;
                } else {
                    j++;
                }
            } while (!bl);
            Tempo[i] = j;
        }
        return Tempo;
    }

    public static int[] generKey(int[] text, int[] la_permutation) {
        int index = 0;
        int[] tempo = new int[text.length];
        for (int i = 0; i < la_permutation.length; i++) {
            index = la_permutation[i];
            tempo[i] = text[index];
         //K[i]=K[index];

        }
        text = tempo;
        return text;
    }

    public static List<int[]> DivKey_K(int[] tab) {
        List<int[]> list = new ArrayList<>();
        int taille = tab.length;
        int longeurK1 = tab.length/2;
        int longeurK2 = taille - longeurK1;
        int[] K1 = new int[longeurK1];
        int[] K2 = new int[longeurK2];

        for (int i = 0; i < longeurK1; i++) {

            K1[i] = tab[i];
        }

        for (int i = longeurK1; i < taille; i++) {

            K2[i - longeurK1] = tab[i];

        }
        list.add(K1);
        list.add(K2);
        return list;
    }

    public static int[] ET(int[] A, int[] B) {
        int[] Tempo = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            Tempo[i] = (A[i] == 1 && B[i] == 1) ? 1 : 0;
        }
        return Tempo;
    }

    public static int[] OU(int[] A, int[] B) {
        int[] Tempo = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            Tempo[i] = (A[i] == 1 || B[i] == 1) ? 1 : 0;
        }
        return Tempo;
    }

    public static int[] OUex(int[] A, int[] B) {
        int[] Tempo = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            Tempo[i] = (A[i] == B[i]) ? 0 : 1;
        }
        return Tempo;
    }

    public static int[] Trasnsform_Mot_Tab(String mot) {
        int[] Tab = new int[mot.length()];
        for (int i = 0; i < Tab.length; i++) {
            char a = mot.charAt(i);
            Tab[i] = Character.getNumericValue(a);
        }
        return Tab;
    }

    public static List<int[]> Generation_de_Cle() {
        List<int[]> Keys = new ArrayList<>();
        int a;
        int[] H = {6, 5, 2, 7, 4, 1, 3, 0};
        for (int i = 0; i < H.length; i++) {
            System.out.println("La valeur de la fonction position" + i + " est:" + H[i]);
        }

        Scanner valeurCle = new Scanner(System.in);
        int[] K = {0, 1, 1, 0, 1, 1, 0, 1};
        int[] tempo;
//
        K = generKey(K, H);
        //
        int taille = K.length;
        int longeurK1 = K.length / 2;
        int longeurK2 = taille - longeurK1;
        int[] K1 = new int[longeurK1];
        int[] K2 = new int[longeurK2];
        //
        K1 = DivKey_K(K).get(0);
        K2 = DivKey_K(K).get(1);
        //
        System.out.println("Clé avec la fonction H appliqué est : " + Arrays.toString(K));
        System.out.println("la Première clé générée est : " + Arrays.toString(K1));
        System.out.println("la Deuxième clé générée est : " + Arrays.toString(K2));

        //Application des  opérations logiques sur les deux portion de la clé
        int[] K11 = new int[K1.length];
        int[] K12 = new int[K1.length];
        //
        K11 = OUex(K1, K2);
        K12 = ET(K1, K2);
        //Application du decalage 
        tempo = new int[K11.length];
        for (int i = 2; i < K12.length; i++) {
            tempo[i - 2] = K11[i];
        }
        tempo[K12.length - 1] = K11[1];
        tempo[K11.length - 2] = K11[0];
        K11 = tempo;
        tempo = new int[K11.length];
        System.out.println("La Première clé K1 générée est : " + Arrays.toString(K11));
        //
        a = K12[K12.length - 1];
        //
        tempo[0] = a;
        System.out.println("Le Mot de gauche K1: " + Arrays.toString(K11));
        for (int i = 0; i < K12.length - 1; i++) {
            tempo[i + 1] = K12[i];
        }
        K12 = tempo;
        //
        Keys.add(K11);
        Keys.add(K12);
        return Keys;
    }
    //
    //
    public static void Cryptage_de_Mot() {
        Scanner sc = new Scanner(System.in);
        int[] mot = {0, 1, 1, 0, 1, 1, 1, 0};
        int[] pi = {4, 6, 0, 2, 7, 3, 1, 5};
        int[] tempo;
        String str;
        System.out.println("Entre votre mot en binaire contenant 8 caractaire (Ex: 10111001) : ");
        str = sc.nextLine();
        //
        mot = Trasnsform_Mot_Tab(str);
        //
        System.out.println("Entre votre la permutation? allant de 0 à 7  (Ex: 12345670) : ");
        str = sc.nextLine();
        //
        pi = Trasnsform_Mot_Tab(str);
        //
        System.out.println("pi = " + Arrays.toString(pi));
        System.out.println("mot = " + Arrays.toString(mot));

        //application de la fonction de permutation et Round 1
        mot = generKey(mot, pi);
        System.out.println("Mot : " + Arrays.toString(mot));
        int[] Go, G1, G2; //G0 est le premier G0, G1
        int[] Do, D1, D2;
        int[] p = {2, 0, 1, 3};

        Go = DivKey_K(mot).get(0);
        Do = DivKey_K(mot).get(1);

        System.out.println("Mot de gauche Go: " + Arrays.toString(Go));
        System.out.println("Mot de droite D0: " + Arrays.toString(Do));

        int[] K1 = Generation_de_Cle().get(0);
        int[] K2 = Generation_de_Cle().get(1);

        System.out.println("K1: " + Arrays.toString(K1));
        System.out.println("K2: " + Arrays.toString(K2));
        D1 = OUex(generKey(Go, p), K1);
        G1 = OUex(Do, OU(Go, K1));

        System.out.println("Le Mot de gauche de G1 est : " + Arrays.toString(G1));
        System.out.println("Le Mot de droite de D1 est : " + Arrays.toString(D1));

        //Application d ela permutaion et Round 2
        D2 = OUex((generKey(G1, p)), K2);
        G2 = OUex(D1, OU(G1, K2));
        System.out.println("Le Mot de gauche de G2 est : " + Arrays.toString(G2));
        System.out.println("Le Mot de droite de D2 est : " + Arrays.toString(D2));

        //Concatenation de G2 et D2
        int[] C = new int[D2.length + G2.length];

        System.arraycopy(G2, 0, C, 0, G2.length);
        System.arraycopy(D2, 0, C, G2.length, D2.length);

        System.out.println("C = " + Arrays.toString(C));
        tempo = new int[pi.length];
        for (int i = 0; i < pi.length; i++) {
            int j = 0;
            boolean bl = false;
            do {
                if (pi[j] == i) {
                    bl = true;
                } else {
                    j++;
                }
            } while (!bl);
            tempo[i] = j;
        }
        int[] pi1 = tempo;

        System.out.println("pi1 = " + Arrays.toString(pi1));

        C = generKey(C, pi1);
        System.out.println("Le Mot Crypter est " + Arrays.toString(C));
    }

    public static void Decryptage_de_Mot() {
        Scanner sc = new Scanner(System.in);
        int[] mot = {0, 1, 1, 0, 1, 1, 1, 0};
        int[] pi = {4, 6, 0, 2, 7, 3, 1, 5};
        int[] tempo;
        String str;
        System.out.println("Entre la valeur à décrypté en binaire de 8 caractaire : ");
        str = sc.nextLine();

        mot = Trasnsform_Mot_Tab(str);

        System.out.println("Entre la valeur de la permutation : ");
        str = sc.nextLine();

        pi = Trasnsform_Mot_Tab(str);

        //application de la fonction de permutation et Round 1
        mot = generKey(mot, pi);
        System.out.println("Mot : " + Arrays.toString(mot));
        int[] Go, G1, G2; //G0 est le premier G0, G1
        int[] Do, D1, D2;
        int[] p = {2, 0, 1, 3};

        Go = DivKey_K(mot).get(0);
        Do = DivKey_K(mot).get(1);

        int[] K1 = Generation_de_Cle().get(0);
        int[] K2 = Generation_de_Cle().get(1);

        tempo = new int[p.length];
        for (int i = 0; i < p.length; i++) {
            int j = 0;
            boolean bl = false;
            do {
                if (p[j] == i) {
                    bl = true;
                } else {
                    j++;
                }
            } while (!bl);
            tempo[i] = j;
        }
        int[] p1 = tempo;

        G1 = generKey(OUex(Do, K2), p1);
        D1 = OUex(Go, OU(G1, K2));

        System.out.println("Le Mot de gauche de G1 est : " + Arrays.toString(G1));
        System.out.println("Le Mot de droite de D1 est : " + Arrays.toString(D1));

        //Application d ela permutaion et Round 2
        G2 = generKey(OUex(D1, K1), p1);
        D2 = OUex(G1, OU(G2, K1));
        System.out.println("Le Mot de gauche de G2 est : " + Arrays.toString(G2));
        System.out.println("Le Mot de droite de D2 est : " + Arrays.toString(D2));

        //Concatenation de G2 et D2
        int[] C = new int[D2.length + G2.length];

        System.arraycopy(G2, 0, C, 0, G2.length);
        System.arraycopy(D2, 0, C, G2.length, D2.length);

        System.out.println("C = " + Arrays.toString(C));
        tempo = new int[pi.length];
        for (int i = 0; i < pi.length; i++) {
            int j = 0;
            boolean bl = false;
            do {
                if (pi[j] == i) {
                    bl = true;
                } else {
                    j++;
                }
            } while (!bl);
            tempo[i] = j;
        }
        int[] pi1 = tempo;

        System.out.println("pi1 = " + Arrays.toString(pi1));

        C = generKey(C, pi1);
        System.out.println("Le Mot Decrypter est: " + Arrays.toString(C));
    }

    public static void main(String[] args) {
        Scanner x = new Scanner(System.in);
        int choix = 0;

        System.out.println(" copy right Glody KHALIFA **** TP DE SECURITE INFORMATIQUE***** ");
        System.out.println("================ ================= ============= ===============");
        int fin = 1;
        do {
            System.out.println(" Menu ");
            System.out.println("-------");
            System.out.println(" 1. cryptage du mot binaire à 8 bit");
            System.out.println(" 2. Dechiffrage du mot binaire à 8 bit ");
            System.out.println("\n");
            do {
                System.out.println("\t Faite Votre choix entre 1 et 2");
                choix = x.nextInt();
            } while (choix <= 0);

            if (choix == 1) {
                Cryptage_de_Mot();
            } else {
                Decryptage_de_Mot();
            }
            System.out.println(" voulez-vous continuer? (1 ou 2) ");
            do {
                System.out.println("(si oui, tapper  1, sinon tapper 2)");
                fin = x.nextInt();
            } while (fin <= 0);
        } while (fin == 1);
    }

}
