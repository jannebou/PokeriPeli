public class Kortti {
    private Maa maa;
    private int arvo;


    //konstruktori
    public Kortti(Maa maa, int arvo) {
        this.maa = maa;
        this.arvo = arvo;
    }

    
    // palauttaa kortin arvon
    public int getArvo(){
        return this.arvo;
    }


    // palauttaa kortin maan
    public Maa getMaa(){
        return this.maa;
    }


    @Override
    public String toString() {
        return maa +  " " + arvo;
    }
}
