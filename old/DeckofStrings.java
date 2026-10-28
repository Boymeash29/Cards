// Name: Asher Boymel
// Date: 10/27/2026
// File: DeckofStrings.java

public class DeckofStrings {

    String[] num = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
    String[] suits = {"C", "D", "H", "S"};

    // Builds 52 cards via nested loops
    public DeckofStrings() {
        String[] cards = new String[52]; // Create string with 52 elements
        int count = 0;

        // Goes through each suit one at a time
        for (int s = 0; s < suits.length; s++) {

            // Goes through every number for the current suit
            for (int n = 0; n < num.length; n++) {

                // Concatenate the num + of + suit into one string
                cards[count] = num[n] + " of " + suits[s];
                System.out.println(cards[count]);
                count++;
            }
        }
    }
}