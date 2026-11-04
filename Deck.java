public class Deck {

	private Card[] deckOfCards;
	private int nextCard = 0;

	public Deck(String[] suit, String[] value, int[] points) {
		deckOfCards = new Card[suit.length * value.length];

		int count = 0;
		for (int s = 0; s < suit.length; s++) {
			for (int n = 0; n < value.length; n++) {
				// Create a new card and add it to the deck
				deckOfCards[count] = new Card(suit[s], value[n], points[n]); // TODO: Fix points[n] to be correct for the card value
				count++;
			}
		}
	}

	// Fisher–Yates shuffle
	public void shuffle() { // Found on stackoverflow.com/questions/1519736/random-shuffling-of-an-array
		// Move through deck, place one randomly selected card at each position
		for (int i = deckOfCards.length - 1; i > 0; i--) {
			// Choose random pos from the part of the deck not yet shuffled
			int j = (int) (Math.random() * (i + 1));
			// Keep the card at i while the two positions are switched
			Card temp = deckOfCards[i];
			deckOfCards[i] = deckOfCards[j];
			deckOfCards[j] = temp;
		}

		nextCard = 0;
	}

	// Returns the next card
	public Card deal() {
		if (nextCard >= deckOfCards.length) {
			return null;	// Ran into error
		}
		return deckOfCards[nextCard++];
	}

	public int cardsLeft() {
		return deckOfCards.length - nextCard;
	}
}