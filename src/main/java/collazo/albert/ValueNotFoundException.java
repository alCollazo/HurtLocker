package collazo.albert;

/**
 * Created by albertcollazo on 2/11/17.
 */
public class ValueNotFoundException extends Exception {

    public static int counter = 0;

    public ValueNotFoundException(String message){
        super(message);
        counter++;
    }

    public static String formattedErrorOutput(){
        return "Errors\t\t\t\tseen: " +counter+ " times";
    }
}
