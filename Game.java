
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

import java.util.List;
import java.util.Scanner;


public class Game{
    // peliä varten
    private static Raha raha;
    private static KorttiPakka pakka;
    private static double panos;


    public static void main(String[] args) {
        // Ohjelma antaa käynnistyessään pelaajalle pelirahaa 100 euroa.
        raha = new Raha(100);
        while (raha.getRaha() > 0){
            gameLoop();
        }
    }



    public static void gameLoop(){
        System.out.println(raha + ", syötä panoksen määrä: ");
        // kysytään panoksen määrää
        panos = Double.parseDouble(getInput()); 
        
        // vähennetään rahaa ja katsotaan onko riittävästi rahaa,
        if (raha.decRaha(panos) != true) {
            System.out.println("sinulla ei ole tarpeeksi rahaa jatkaaksesi... lopetetaan");
            return;
        }
        
        System.out.println("Jaetaan kortit, kierroksen hinta on: " + panos);
        // luodaan uusi korttipakka
        pakka = new KorttiPakka();
        pakka.luoPakka();
        // Sekoitetaan korttipakkaa
        pakka.sekoitaPakka();
        
        // jaetaan käsi viidellä kortilla
        List<Kortti> hand = pakka.jaa(5);
        
        //tulostetaan kortit
        tulostaKortit(hand);
        System.out.println("syötä pilkulla erotellen mitkä kortit haluat vaihtaa ");
        
        System.out.print("1-5, 0: lopettaa: ");
        // kysytään korttien numeroita
        
        String input = getInput();
        
        if (input.equals("0")){
            System.out.println("Jatketaan");
            //TODO jatka
        }else{
            System.out.println("Vaihdetaan kortit: " + input);
        }
        
        String[] vaihdettavatKortit = input.split(",");
        
        for (String string : vaihdettavatKortit) {
            // otetaan ID talteen
            int id = Integer.parseInt(string) - 1;
            
            // tehdään väliaikainen lista johon jaetaan yksi uusi kortti
            List<Kortti> temp = pakka.jaa(1);
            Kortti uusiKortti = (Kortti) temp.get(0);
            
            // poistetaan vanha kortti kädestä ja lisätään uusi tilalle
            hand.remove(id);
            hand.add(id, uusiKortti);   
        }
        
        tulostaKortit(hand);

        // Tarkistetaan kyseinen käsi, löytyykö voittoa
        KadenTarkistus.check(hand);

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