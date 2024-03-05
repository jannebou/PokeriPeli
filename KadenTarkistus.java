import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KadenTarkistus {

    // kertoo löytyykö kädestä jotain
    private static String kädessäOn;

    
    // kertoimet
    // Värisuora = 30
    private static int straightFlush = 30;

    // Neloset = 15
    private static int fourOfAKind = 15;

    // Täyskäsi = 10
    private static int fullHouse = 10;

    // Väri = 5
    private static int flush = 5;

    // Suora = 4
    private static int staight = 4;

    // Kolmoset = 3
    private static int triplets = 3;
    
    // 2 paria = 3
    private static int pair = 3;
    

    // palauttaa mitä tutkitusta kädestä löytyi
    public static String getKädenVoitto() {
        return "Kädestä löytyi: " + kädessäOn;
    }


    public static void main(String[] args) {      

        // TESTI käden tutkimista varten
        ArrayList<Kortti> lista = new ArrayList<>();
        lista.add(new Kortti(Maa.HERTTA, 1));
        lista.add(new Kortti(Maa.HERTTA, 2));
        lista.add(new Kortti(Maa.HERTTA, 3));
        lista.add(new Kortti(Maa.HERTTA, 4));
        lista.add(new Kortti(Maa.HERTTA, 5));


        // System.out.println(check4StraightFlush(lista));
        // System.out.println(check4AmountOfPair(2,lista));
        // System.out.println(check4FullHouse(lista));
        // System.out.println(check4Flush(lista));
        // System.out.println(check4Straight(lista));
        // System.out.println(check4Triplet(lista));
        // System.out.println(check4Pair(lista)); 
    }


    // Tarkistetaan käsi suurimmasta kertoimesta pienempään ja palautetaan sen kerroin.
    public static int check(List<Kortti> hand){

        // värisuora
        if (check4StraightFlush(hand)){
            kädessäOn = "Värisuora";
            return straightFlush;} // 30 


        // neloset
        else if (check4AmountOfPair(4,hand)){
            kädessäOn = "Neloset";
            return fourOfAKind;} // 15


        // täyskäsi
        else if (check4FullHouse(hand)){
            kädessäOn = "Täyskäsi";
            return fullHouse;} // 10


        // väri
        else if (check4Flush(hand)){
            kädessäOn = "Väri";
            return flush;} // 5


        // suora
        else if (check4Straight(hand)){
            kädessäOn = "Suora";
            return staight;} // 4


        // kolmoset
        else if (check4AmountOfPair(3,hand)){
            kädessäOn = "Kolmoset";
            return triplets;} // 3


        // pari
        else if (check4AmountOfPair(2,hand)){
            kädessäOn = "Pari";
            return pair;} // 3


        // ei voittoa
        else{
            kädessäOn = "Ei mitään";
            return 0;}
    }

    // VÄRISUORA
    public static boolean check4StraightFlush(List<Kortti> hand){
        return check4Straight(hand) && check4Flush(hand);
    }


    // PARIT 2,3,4
    public static boolean check4AmountOfPair(int amount, List<Kortti> hand){
       
        // luodaan Hashmap nykyisen käden arvoilla
        HashMap<Integer, Integer> tempMap = new HashMap<Integer,Integer>();
        for (Kortti kortti : hand) {
            tempMap.put(kortti.getArvo(), 0);
        }


        // laitetaan ylös montako kertaa samaa numeroa löytyy kädestä
        // jos löytyy tarvittava määrä pareja palautetaan true
        for (Kortti kortti : hand) {
            tempMap.put(kortti.getArvo(), tempMap.get(kortti.getArvo()) + 1);
            if (tempMap.get(kortti.getArvo()) >= amount){
                return true;
            }
        }


        // ei löytynyt saman arvoista korttia
        return false;
    }
    

    // TÄYSKÄSI
    // katsotaan onko täyskäsi
    public static boolean check4FullHouse(List<Kortti> hand){

        // Täyskäsi, eli Full House, tarkoittaa sitä, että kädessä on kolmoset ja pari.
        return check4AmountOfPair(3,hand) && check4AmountOfPair(2,hand);
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

            // haetaan kortin arvo ja katsotaan ,
            // onko seuraava kortin arvo sama kuin nykyinen kortti + 1 tai - 1
            tempKorttiArvo = hand.get(id).getArvo();
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
}
