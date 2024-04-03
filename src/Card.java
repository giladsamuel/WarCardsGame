/**
 * The Card class represents a card with number and shape.
 * It implements the Comparable interface to allow for comparison between cards based on their number.
 */
public class Card implements Comparable<Card> {
    /**
     * The number of the card.
     */
    private final CardNumber number;

    /**
     * The shape of the card.
     */
    private final Shape shape;

    /**
     * The constructor for the Card class.
     * @param number the number of the card.
     * @param shape the shape of the card.
     */
    public Card(CardNumber number, Shape shape) {
        this.number = number;
        this.shape = shape;
    }

    /**
     * This method returns the number of the card.
     * @return the number of the card.
     */
    public CardNumber getNumber() {
        return number;
    }

    /**
     * This method returns the shape of the card.
     * @return the shape of the card.
     */
    public Shape getShape() {
        return shape;
    }

    /**
     * This method compares this card with another card based on their numbers.
     * @param otherCard the other card to compare this card with.
     * @return a negative integer, zero, or a positive integer as this card's number is less than, equal to, or greater than the other card's number.
     */
    @Override
    public int compareTo(Card otherCard) {
        // Compare the card numbers
        return this.number.compareTo(otherCard.number);
    }
}

/**
 * The Shape enum represents the four possible shapes of a card.
 */
enum Shape {
    HEART,
    DIAMOND,
    CLUB,
    SPADE
}

/**
 * The CardNumber enum represents the thirteen possible numbers of a card.
 */
enum CardNumber {
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    TEN,
    JACK,
    QUEEN,
    KING,
    ACE
}