public class Card {
	
	private String suit;	// Hearts, diamond etc
	private String value;	// 2-10, J, Q, K, A
	private int points;		// 1-14
	
	/**
	 * Constructor for a Card.
	 *
	 * @param s		Suit of card.
	 * @param v		Value of card.
	 * @param p		Point value of card.
	 */
	public Card(String s, String v, int p) {
		suit = s;
		value = v;
		points = p;
	}
	
	public String getSuit() { return suit; }
	
	public String getValue() { return value; }
	
	public int getPoints() { return points; }
}
