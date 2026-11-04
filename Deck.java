public class Deck {

	private Card[] deckOfCards;
	private int nextCard = 0;

	public Deck(String[] suit, String[] value, int[] points) {
		deckOfCards = new Card[suit.length * value.length];

		// Create variable to prevent running check each iteration
		int suitLen = suit.length;
		int valLen = value.length;

		int count = 0;
		for (int s = 0; s < suitLen; s++) {
			for (int n = 0; n < valLen; n++) {
				// Create a new card and add it to the deck
				deckOfCards[count] = new Card(suit[s], value[n], points[n]); // TODO: Fix points[n] to be correct for the card value
				count++;
			}
		}
	}

	// Fisher–Yates shuffle
	public void shuffle() {
		nextCard = 0;
	}

	// Returns the next card
	public Card deal() {
		return deckOfCards[nextCard++];
	}

	public int cardsLeft() {
		return deckOfCards.length - nextCard;
	}
}
