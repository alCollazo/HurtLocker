package collazo.albert;
import org.junit.Before;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class GroceryItemTest {

    GroceryItem groceryItem;
    Map<String, Integer> priceAndNumberOfOccurrences;

    @Before
    public void setup(){
        priceAndNumberOfOccurrences = new HashMap<>();
        groceryItem = new GroceryItem("bread", priceAndNumberOfOccurrences);
        priceAndNumberOfOccurrences.put("6.66", 6);
        groceryItem.incrementCount("6.66");
    }

    @Test
    public void addPriceTest(){
        groceryItem.addPrice("6.66");
        int expected = 1;
        int actual = priceAndNumberOfOccurrences.get("6.66");
        assertEquals(expected, actual);
    }

    @Test
    public void checkIfPriceExistsTest(){
        boolean expected = true;
        boolean actual = groceryItem.checkIfPriceExists("6.66");
        assertTrue(expected == actual);
    }

    @Test
    public void incrementCountTest(){
        int expected = 7;
        int actual = priceAndNumberOfOccurrences.get("6.66");
        assertEquals(expected, actual);
    }
}
