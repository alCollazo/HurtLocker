package collazo.albert;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static jdk.nashorn.internal.objects.NativeString.toLowerCase;

public class JerksonDataParcer {

    Map<String, GroceryItem> listOfItems = new HashMap<>();

    public String[] splitItemByHash(String item){
        String[] itemsArray = item.split("##");
        return itemsArray;
    }

    public String[] separateKeyAndValue(String item){
        String[] keyAndValue = item.split("[^a-zA-Z0-9:./]");
        return keyAndValue;
    }

    public void addKeyAndValueToMap(String input){
        String[] itemsArray = splitItemByHash(input);
        for(int i = 0; i < itemsArray.length; i++){
            String[] item = separateKeyAndValue(itemsArray[i]);
            addItemToMap(item[0], item[1]);
        }
    }

    public String spellCheck(String itemName){

        Matcher matcher = Pattern.compile("0", Pattern.CASE_INSENSITIVE).matcher(itemName);
        matcher.find();
        return matcher.replaceAll("o");
    }

    public String doSpellCheck(String name){
        if(name != null){
            name = spellCheck(toLowerCase(name));
        }
        return name;
    }

    public String checkForNullValue(String value) throws ValueNotFoundException{
        Pattern pattern = Pattern.compile("[^:]*$");
        Matcher hasValue = pattern.matcher(value);
        hasValue.find();

        Matcher noValue = Pattern.compile("^$").matcher(hasValue.group());
        if (noValue.find()){
            throw new ValueNotFoundException("Value not found");
        }
        return hasValue.group();
    }

    public boolean checkMapForItem(String name){
        return listOfItems.containsKey(name);
    }

    public void addItemToMap(String item, String itemPrice){
        Map<String, Integer> priceAndNumberOfOccurrences = new HashMap<>();
        String name;
        String price = null;
        try{
            name = checkForNullValue(item);
            price = checkForNullValue(itemPrice);
        }catch (ValueNotFoundException e){
            name = null;
        }
        if(name != null && price != null){
            name = doSpellCheck(name);
            if(!checkMapForItem(name)){
                priceAndNumberOfOccurrences.put(price,0);
                GroceryItem groceryItem = new GroceryItem(name, priceAndNumberOfOccurrences);
                listOfItems.put(name,groceryItem);
            }
        }
        addPriceToGroceryItem(name,price);
    }

    public void addPriceToGroceryItem(String name, String price){

        if(price != null && name != null ){
            if(listOfItems.get(name).checkIfPriceExists(price))
                listOfItems.get(name).incrementCount(price);
            else
                listOfItems.get(name).addPrice(price);
        }
    }

    public void error(){
        System.out.println(ValueNotFoundException.formattedErrorOutput());
    }

    public void printMap(){
        for(HashMap.Entry<String, GroceryItem> entry : listOfItems.entrySet()){
            System.out.println(entry.getValue().formattedOutput());
        }
    }
}
