package dices.exceptions;

public class GameNotFoundException extends RuntimeException
{
    public GameNotFoundException(Long id)
    {
        super("Could not find collar with ID: " + id);
    }
}
