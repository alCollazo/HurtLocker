package collazo.albert;

import java.util.Map;

/**
 * Created by albertcollazo on 2/8/17.
 */
public class GroceryItem {
    String name;
    public int numberOfItemOccurrences;
    public Map<String, Integer>priceAndNumberOfOccurrences;

    public GroceryItem(String name, Map<String, Integer> priceAndNumberOfOccurrences) {
        this.name = name;
        this.priceAndNumberOfOccurrences = priceAndNumberOfOccurrences;
    }

    @Override
    public String toString(){
        return "GrocerItem{" +
                "name =' " + name + '\'' +
                ", numberOfItemOccurrences = " + numberOfItemOccurrences +
                ", priceAndNumberOfPriceOccurrences = " + priceAndNumberOfOccurrences +
                '}';
    }

    public void addPrice(String price){
        priceAndNumberOfOccurrences.put(price, 1);
        numberOfItemOccurrences++;
    }

    public boolean checkIfPriceExists(String price){
        if(priceAndNumberOfOccurrences.containsKey(price))
            return true;
        else
            return false;
    }

    public void incrementCount(String price){
        priceAndNumberOfOccurrences.put(price,(priceAndNumberOfOccurrences.get(price)+1));
        numberOfItemOccurrences++;
    }

    public String formattedOutput(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("name:\t"+name)
                .append("\t\tseen: "+numberOfItemOccurrences+" times\n")
                .append("=============\t\t=============\n");
        for(Map.Entry<String,Integer>entry : priceAndNumberOfOccurrences.entrySet()){
            stringBuilder.append("Price:\t"+entry.getKey())
                    .append("\t\tseen: "+entry.getValue()+" times\n")
                    .append("-------------\t\t-------------\n");
        }
        return stringBuilder.toString();
    }

}
