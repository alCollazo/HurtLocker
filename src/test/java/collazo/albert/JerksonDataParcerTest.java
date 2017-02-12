package collazo.albert;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class JerksonDataParcerTest {

    JerksonDataParcer jerksonDataParcer;
    Map<String, Integer> priceAndNumberOfOccurrences;

    @Before
    public void setup(){
        jerksonDataParcer = new JerksonDataParcer();
        priceAndNumberOfOccurrences = new HashMap<>();
    }

    @Test
    public void splitItemByHashTest(){
        String item = "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##naME:BreaD;price:1.23;type:Food;expiration:1/02/2016##";
        String[] actual = jerksonDataParcer.splitItemByHash(item);
        assertTrue(actual.length == 2);
    }

    @Test
    public void separateNameAndPriceTest(){
        String item = "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##naME:BreaD;price:1.23;type:Food;expiration:1/02/2016##";
        String[] itemsArray = jerksonDataParcer.splitItemByHash(item);
        String[] keyValuePairs = jerksonDataParcer.separateKeyAndValue(itemsArray[0]);
        Assert.assertTrue(keyValuePairs[0].equals("naMe:Milk"));
    }

    @Test
    public void spellCheckTest(){
        String expected = "bread";
        String actual = jerksonDataParcer.doSpellCheck("BreaD");
        assertEquals(expected, actual);
    }

    @Test
    public void checkForNullValueTest(){
        String expected = "Milk";
        String actual = null;
        try{
            actual = jerksonDataParcer.checkForNullValue("naMe:Milk");
        }catch(ValueNotFoundException e){

        }
        assertEquals(expected, actual);
    }

    @Test
    public void checkForNullValueExceptionTest(){
        String actual = null;
        try{
            actual = jerksonDataParcer.checkForNullValue("naMe:");

        }catch(ValueNotFoundException e){
            assertTrue(e.getMessage().equals("Value not found"));
        }
    }

    @Test
    public void checkMapForItemTest(){
        jerksonDataParcer.listOfItems.put("bread", new GroceryItem("bread", priceAndNumberOfOccurrences));
        boolean expected = true;
        boolean actual = jerksonDataParcer.checkMapForItem("bread");
        assertEquals(expected, actual);
    }



}
