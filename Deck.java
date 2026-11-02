public class Deck {
	
	// Collection of cards
	Card[] deckOfCards; // Declare deck
	
	public Deck(String[] suit, String[] value, int[] points) {
		// Contstructor -- need to initialize
		// Goes through each suit one at a time
		int count = 0;
		for (int s = 0; s < suit.length; s++) {
			// Goes through every number for the current suit
			for (int n = 0; n < value.length; n++) {
				deckOfCards[count] = new Card(suit[s], value[n], points[n]); // Points needs to be changed to account for individual card.
				count++;
			}
		}
	}
	
	public void Shuffle() {
		
	}
	
	public void Deal() {
		
	}
}
