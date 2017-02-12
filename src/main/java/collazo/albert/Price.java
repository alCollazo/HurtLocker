package collazo.albert;

/**
 * Created by albertcollazo on 2/12/17.
 */
public class Price {
    public String price;
    public int occurrences;

    public Price(String price){
        this.price = price;
        increment();
    }

    public void increment(){
        this.occurrences++;
    }

    @Override
    public String toString(){
     return "Price{" +
             "price =' " + price + '\'' +
            ", occurrences = " + occurrences +
            '}';
    }
}
