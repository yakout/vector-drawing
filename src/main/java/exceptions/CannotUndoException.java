package exceptions;

/**
 * class undo exception
 * Created by YS team.
 */
public class CannotUndoException extends Exception {
    
    @Override
    public String toString() {
        return "Invalid operation: nothing to be undone!";
    }
}
