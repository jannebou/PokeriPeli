public class Kortti {
    private Maa maa;
    private int arvo;

    public Kortti(Maa maa, int arvo) {
        this.maa = maa;
        this.arvo = arvo;
    }

    public int getArvo(){
        return this.arvo;
    }

    public Maa getMaa(){
        return this.maa;
    }


    @Override
    public String toString() {
        return maa +  " " + arvo;
    }
}
