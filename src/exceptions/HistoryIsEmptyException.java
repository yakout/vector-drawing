package exceptions;

/**
 * class HistoryEmpty exception
 * Created by YS team
 */
public class HistoryIsEmptyException extends Exception {
    @Override
    public String toString() {
        return "Invalid operation: History is empty!";
    }
}
