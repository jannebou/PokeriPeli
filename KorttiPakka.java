import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KorttiPakka {
    private ArrayList<Kortti> pakka;

    public KorttiPakka() {

    }


    public void luoPakka(){
        this.pakka = new ArrayList<>();
        for (Maa maa: Maa.values()) {
            for (int j = 1; j < 13; j++) {
                Kortti k = new Kortti(maa, j);
                this.pakka.add(k);
            }
        }
    }

    public void sekoitaPakka(){
        Collections.shuffle(pakka);
    }

    public List<Kortti> jaa(int n)
    { // palautetaan jaettu käsi
        List<Kortti> kasi = new ArrayList<>(this.pakka.subList(0, n));
        // this.pakka.subList(0, n);
        
        // poistetaan kortit pakasta
        this.pakka.removeAll(kasi);

        return kasi;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Kortti kortti : pakka) {
            s.append(kortti.toString());
            s.append("\n");
        }

        return s.toString();
    }
    
}
