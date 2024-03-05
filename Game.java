// Harjoitustyön kuvaus

// Ohjelma antaa käynnistyessään pelaajalle pelirahaa 100 euroa.
// Tämän jälkeen ohjelma jakaa pelaajalle pokerikäden. Kierros maksaa X euroa
// Halutessaan pelaaja saa vaihtaa 1-4 korttia.
// Tämän jälkeen ohjelma jakaa pelaajalle jäljellä olevasta pakasta uudet kortit.
// Kun toinen jako on tehty, ohjelma tarkistaa pelaajan korteista löytyykö seuraavia yhdistelmiä ja maksaa pelaajalle N euroa käden hyvyyden mukaan:
//  kaksi paria
//  kolmoset
//  suora
//  väri
//  täyskäsi
//  neloset
//  värisuora
// Peli jatkuu niin kauan, kun pelaaja pyytää uutta jakoa, tai pelaajan rahat loppuu

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Game{
    private static Raha raha;
    private static KorttiPakka pakka;
    private static double panos;

    public static void main(String[] args) {
        // Ohjelma antaa käynnistyessään pelaajalle pelirahaa 100 euroa ja lopettaa pelin jos rahat loppuu.
        raha = new Raha(100);
        while (raha.getRaha() > 0){
            gameLoop();
        }
    }



    public static void gameLoop(){

        // tulostetaan pelaajalle pelirahan määrä ja kysytään panoksen määrää,
        // samalla katsotaan virhesyötteet ja onko pelaajalla tarpeeksi rahaa pelamiseen.
        System.out.println(raha + ", syötä panoksen määrä: ");
        try {
            panos = Double.parseDouble(getInput());
            if (panos < 0){
                System.out.println("panos ei voi olla negatiivinen");
                return;
            }
            if (raha.decRaha(panos) != true) {
                System.out.println("liian suuri panos, rahasi ei riitä");
                return;
            }
        } catch (Exception e) {
            System.out.println("syötä vain numeroita");
            return;
        }
        

        // luodaan uusi pakka, sekoitetaan ja jaetaan se, lopuksi tulostetaan jaettut kortit
        System.out.println("Jaetaan kortit, kierroksen hinta on: " + panos);
        pakka = new KorttiPakka();
        pakka.luoPakka();
        pakka.sekoitaPakka();
        List<Kortti> hand = pakka.jaa(5);
        tulostaKortit(hand);
        

        // kysytään pelaajalta mitkä kortit pelaaja haluaa vaihtaa
        System.out.println("syötä pilkulla erotellen mitkä kortit haluat vaihtaa ");
        System.out.print("1-5, 0: jatkaa: ");
        List<String> vaihdettavatKortit = Arrays.asList(getInput().split(","));
 

        // jos vaihdettavia kortteja on enemmän kuin sallitun, 
        // kysytään pelaajalta niin kauan, että pelaaja antaa oikean syötteen
        while (vaihdettavatKortit.size() > 4) {
            if (vaihdettavatKortit.size() > 4) {
                System.out.println("syötä pilkulla erotellen mitkä kortit haluat vaihtaa (max. 4 korttia) ");
                System.out.print("1-5, 0: jatkaa: ");
                vaihdettavatKortit = Arrays.asList(getInput().split(","));
            }else{
                break;
            }
        }


        // jos pelaaja on syöttänyt 0, peli jatkuu ilman korttien vaihtoa
        // muuten vaihtaa valitut kortit kortin id:n mukaan
        // lopuksi tulostaa uudet kortit
        if (vaihdettavatKortit.get(0).equals("0")){
            System.out.println("Jatketaan ilman korttien vaihtoa");
        }
        else{
            for (String string : vaihdettavatKortit) {

                // otetaan ID talteen ja tehdään väliaikainen lista johon jaetaan yksi uusi kortti
                int id = Integer.parseInt(string) - 1;
                List<Kortti> temp = pakka.jaa(1);
                Kortti uusiKortti = (Kortti) temp.get(0);
                        

                // poistetaan vanha kortti kädestä ja lisätään uusi tilalle
                hand.remove(id);
                hand.add(id, uusiKortti);   
            }
            tulostaKortit(hand);
        }


        // Tarkistetaan kyseinen käsi, löytyykö voittoa, jos löytyy,
        // tulostetaan mitä kädestä löytyi ja paljon voittokerroin oli
        System.out.println();
        int voittokerroin = KadenTarkistus.check(hand);
        System.out.println(KadenTarkistus.getKädenVoitto() + ", Kerroin on: " + voittokerroin);
        raha.incRaha(voittokerroin * panos);
    }



    // Funktio jolla saadaan käyttäjän syöte
    public static String getInput(){
        System.out.print("-> ");
        Scanner lukija = new Scanner(System.in);
        String input = lukija.nextLine();
        return input;
    }

    // tulostetaan kortit annetussa listassa
    public static void tulostaKortit(List<Kortti> lista) {
        System.out.println();
        System.out.println("Tässä korttisi");
        int id = 1;
        for (Object object : lista) {
            System.out.println(id + " : " + object);
            id++;
        }
    }


}