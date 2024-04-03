/**
 * The Model class represents the state of the game.
 * It contains all the necessary information about the game, such as the user's name, the state of the war, the result of the comparison,
 * the main deck of cards, the user's deck of cards, the PC's deck of cards, the user's card, the PC's card, the user's card information,
 * and the PC's card information.
 */
public class Model {
    /**
     * The name of the user.
     */
    String userName;

    /**
     * A boolean value that indicates whether a war has been declared.
     */
    boolean warDeclared = false;

    /**
     * A string that contains the result of the comparison between the user's card and the PC's card.
     */
    String compareResultMessage;

    /**
     * The game main deck of cards.
     */
    CardDeck mainDeck = new CardDeck();

    /**
     * The user's deck of cards.
     */
    CardDeck userDeck = new CardDeck();

    /**
     * The PC's deck of cards.
     */
    CardDeck pcDeck = new CardDeck();

    /**
     * The user's drawn card.
     */
    Card userCard;

    /**
     * The PC's drawn card.
     */
    Card pcCard;

    /**
     * A string that contains information about the user's card.
     */
    String userCardInfo = "";

    /**
     * A string that contains information about the PC's card.
     */
    String pcCardInfo = "";
}
