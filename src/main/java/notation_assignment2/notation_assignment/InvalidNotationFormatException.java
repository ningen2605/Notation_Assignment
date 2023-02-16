package notation_assignment2.notation_assignment;

public class InvalidNotationFormatException extends Exception{
    public InvalidNotationFormatException(String invalidNotation) {
        super(invalidNotation);
    }
}
