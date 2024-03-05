import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.sound.midi.Soundbank;

public class KadenTarkistus {
    // kertoimet
    // Värisuora = 30
    // Neloset = 15
    // Täyskäsi = 10
    // Väri = 5
    // Suora = 4
    // Kolmoset = 3
    // 2 paria = 3

    private static int straightFlush = 30;
    private static int fourOfAKind = 15;
    private static int fullHouse = 10;
    private static int flush = 5;
    private static int staight = 4;
    private static int triplets = 3;
    private static int pair = 3;

    public static void main(String[] args) {
        // TESTI käden tutkimista varten
        ArrayList<Kortti> lista = new ArrayList<>();
        lista.add(new Kortti(Maa.HERTTA, 2));
        lista.add(new Kortti(Maa.RISTI, 2));
        lista.add(new Kortti(Maa.HERTTA, 3));
        lista.add(new Kortti(Maa.HERTTA, 2));
        lista.add(new Kortti(Maa.HERTTA, 3));

        // System.out.println(check4FullHouse(lista));
        // System.out.println(check4Flush(lista));
        // System.out.println(check4Straight(lista));
        // System.out.println(check4Triplet(lista));
        // System.out.println(check4Pair(lista)); 
    }


    public static void check(List<Kortti> hand){
        check4FullHouse(hand); // 10
        check4Flush(hand); // 5
        check4Straight(hand); // 4
        check4Triplet(hand); // 3
        check4Pair(hand); // 3
    }

    // NELOSET
    // katsotaan onko neloset
    public static boolean check4fourOfAKind(List<Kortti> hand){
        // neloset, eli four of a kind, tarkoittaa sitä, että kädessä on neljä samaa numeroa





        return false;
    }


    // TÄYSKÄSI
    // katsotaan onko täyskäsi
    public static boolean check4FullHouse(List<Kortti> hand){
        // Täyskäsi, eli Full House, tarkoittaa sitä, että kädessä on kolmoset ja pari.
        if (check4Triplet(hand) && check4Pair(hand)){
            return true;
        }else{
            return false;
        }
    }



    // VÄRI
    // katsotaan onko kädessä väri
    public static boolean check4Flush(List<Kortti> hand){
        // Väri, eli Flush, tarkoittaa sitä, että kaikki pelaajan kädessä olevat kortit ovat samaa maata
        HashMap<Maa, Integer> tempMap = new HashMap<>();
        for (Kortti kortti : hand) {
            tempMap.put(kortti.getMaa(), 0);
        } 

        for (Kortti kortti : hand) {
            tempMap.put(kortti.getMaa(), tempMap.get(kortti.getMaa()) + 1);
            if (tempMap.get(kortti.getMaa()) == 5){
                return true;
            }
        }
        return false;
    }


    // SUORA
    // katsotaan onko kädessä suoraa
    public static boolean check4Straight(List<Kortti> hand){
        // väliaikais muuttujat
        int id = 0;
        int amount = 0;
        int tempKorttiArvo;

        for (Kortti kortti : hand){
            // haetaan kortin arvo
            tempKorttiArvo = hand.get(id).getArvo();
            // katsotaan onko onko seuraava kortin arvo sama kuin nykyinen kortti + 1 tai - 1
            if (tempKorttiArvo == kortti.getArvo() + 1 || tempKorttiArvo == kortti.getArvo() - 1){
                id++;
                amount++;
            }
        }

        // jos kortteja on 5 (4), niin on suora
        if (amount == 4){
            return true;
        }else{
            return false;
        }

    }


    // KOLMOSET
    // katsotaan onko kädessä kolme samaa numeroa
    public static boolean check4Triplet(List<Kortti> hand){
        HashMap<Integer, Integer> tempMap = new HashMap<Integer,Integer>();

        // luodaan Hashmap nykyisen käden arvoilla
        for (Kortti kortti : hand) {
            tempMap.put(kortti.getArvo(), 0);
        }
        // laitetaan ylös montako kertaa samaa numeroa löytyy kädestä
        for (Kortti kortti : hand) {
            tempMap.put(kortti.getArvo(), tempMap.get(kortti.getArvo()) + 1);
            if (tempMap.get(kortti.getArvo()) >= 3){
                return true;
            }
        }
        // ei löytynyt kolmea saman arvoista korttia
        return false;
    }


    // PARI
    // Katsotaan onko kädessä yhtään paria
    public static boolean check4Pair(List<Kortti> hand){
        // tehdään väliaikais lista johon lisätä korttien arvot
        List<Integer> tempList = new ArrayList<>();

        for (Kortti kortti : hand) {
            // jos samaa arvoa löytyy jo listasta niin sitten pelaajalla on pari
            if (tempList.contains(kortti.getArvo())){
                return true;
            }
            // jos ei löydy lisätään väliaikais listaan
            else{
                tempList.add(kortti.getArvo());
            }
        }
        // ei löytynyt paria
        return false;
    }
}
