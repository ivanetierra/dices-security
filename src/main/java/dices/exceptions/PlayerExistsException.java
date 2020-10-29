package dices.exceptions;


public class PlayerExistsException extends RuntimeException{

    public PlayerExistsException(String name) { super("Player: " + name + " already exists");}
}

