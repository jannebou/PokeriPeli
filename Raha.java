public class Raha {
    private double raha;


    @Override
    public String toString() {
        return "jäjellä on: " + getRaha();
    }

    public Raha(double raha) {
        this.raha = raha;
    }

    public double getRaha() {
        return raha;
    }

    public void setRaha(double raha) {
        this.raha = raha;
    }


    public boolean decRaha(double amount){
        if (amount > raha){
            return false;
        }else{
            this.raha -= amount;
            return true;
        }
    }

    public void incRaha(double amount){
        this.raha += amount;
    }

    
    
}
