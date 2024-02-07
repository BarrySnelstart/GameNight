package nl.novi.gamenight.exceptions;

/*TODO Generic exception handler*/
public record GameExceptions(String exceptionDetails){
    public static final GameExceptions IdNotFoundInDatabase = new GameExceptions("Id not found in database");
}
