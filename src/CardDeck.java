import java.util.ArrayList;
import java.util.Collections;

/**
 * The CardDeck class represents a deck of cards.
 * It contains an ArrayList of Card objects and provides methods for manipulating the deck.
 */
class CardDeck {
    /**
     * The ArrayList of Card objects that represents the deck of cards.
     */
    private final ArrayList<Card> cards;

    /**
     * The constructor for the CardDeck class.
     * It initializes the cards ArrayList.
     */
    public CardDeck() {
        cards = new ArrayList<>();
    }

    /**
     * A private constructor for the CardDeck class.
     * It is used to create a new CardDeck object with a specific ArrayList of Card objects.
     * @param cards the ArrayList of Card objects to initialize the new CardDeck object with.
     */
    private CardDeck(ArrayList<Card> cards) {
        this.cards = cards;
    }

    /**
     * This method initializes the deck with all possible cards.
     * Creates a full cards set.
     */
    public void initializeDeck() {
        for (CardNumber number : CardNumber.values()) {
            for (Shape shape : Shape.values()) {
                cards.add(new Card(number, shape));
            }
        }
    }

    /**
     * This method shuffles the deck of cards.
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * This method splits the deck into two equal halves and returns them as an array of two CardDeck objects.
     * @return an array of two CardDeck objects representing the two halves of the deck.
     */
    public CardDeck[] splitDeck() {
        int halfSize = cards.size() / 2;
        ArrayList<Card> firstHalf = new ArrayList<>(cards.subList(0, halfSize));
        ArrayList<Card> secondHalf = new ArrayList<>(cards.subList(halfSize, cards.size()));

        return new CardDeck[]{new CardDeck(firstHalf), new CardDeck(secondHalf)};
    }

    /**
     * This method removes and returns the top card of the deck.
     * If the deck is empty, it returns null.
     * @return the top card of the deck, or null if the deck is empty.
     */
    public Card removeTopCard() {
        if (!cards.isEmpty()) {
            return cards.remove(0); // Remove and return the card from the top of the deck
        } else {
            return null; // Return null if the deck is empty
        }
    }

    /**
     * This method removes all cards from the deck.
     */
    public void emptyDeck() {
        cards.clear(); // Remove all cards from the deck
    }

    /**
     * This method adds a card to the bottom of the deck.
     * @param card the Card object to add to the bottom of the deck.
     */
    public void addCardsToBottom(Card card) {
        cards.add(card); // Add the card to the bottom of the deck
    }

    /**
     * This method adds a list of cards to the bottom of the deck.
     * @param cards the ArrayList of Card objects to add to the bottom of the deck.
     */
    public void addCardsToBottom(ArrayList<Card> cards) {
        this.cards.addAll(cards); // Add a list of cards to the bottom of the deck
    }

    /**
     * This method returns the ArrayList of Card objects that represents the deck of cards.
     * @return the ArrayList of Card objects that represents the deck of cards.
     */
    public ArrayList<Card> getCards() {
        return cards;
    }

    /**
     * This method prints all the cards in the deck to the console.
     */
    public void printDeck() {
        for (Card card : cards) {
            System.out.println("Number: " + card.getNumber() + ", Shape: " + card.getShape());
        }
    }
}