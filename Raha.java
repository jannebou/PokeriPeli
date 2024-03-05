public class Raha {
    private double raha;


    @Override
    public String toString() {
        return "jäljellä on: " + getRaha();
    }


    //konstruktori
    public Raha(double raha) {
        this.raha = raha;
    }


    // palauttaa paljonko rahaa on
    public double getRaha() {
        return raha;
    }

    
    // rahan asettaminen
    public void setRaha(double raha) {
        this.raha = raha;
    }


    // rahan vähennystä varten
    public boolean decRaha(double amount){
        if (amount > raha){
            return false;
        }else{
            this.raha -= amount;
            return true;
        }
    }


    // rahan lisääminen
    public void incRaha(double amount){
        this.raha += amount;
    }
}
