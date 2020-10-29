package dices.exceptions;


public class PlayerNotFoundException extends RuntimeException
{
    public PlayerNotFoundException(Long id)
    {
        super("Could not find Player with ID: " + id);
    }
}
