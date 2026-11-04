import java.util.ArrayList;
import java.util.Scanner;

public class Blackjack {

	// Max hand is 12

	// Constant variables
	private static final String[] SUITS = {"Hearts", "Diamonds", "Clubs", "Spades"};
	private static final String[] VALUES = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
	private static final int[] POINTS = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11};
	private static final int STARTING_BALANCE = 500;

	Scanner scanner = new Scanner(System.in);

	// Current game state
	private Deck deck;
	private Card[] player;
	private Card[] dealer;
	
	private void play() {
		while (true) {
			System.out.println("\n\n\n   BLACKJACK");
			playRound();
		}
	}
	
	private void playRound() {
		deck = new Deck(SUITS, VALUES, POINTS);
		deck.shuffle();
		player = new Card[12];
		dealer = new Card[12];
		
		// Deal to player and dealer
		player[0] = deck.deal();
		dealer[0] = deck.deal();
		player[1] = deck.deal();
		dealer[1] = deck.deal();
		
		System.out.println("The dealer has: " + dealer[0] + " and [HIDDEN]");
		System.out.println("The player has: " + player[0] + " and " + player[1] + (player[0].getPoints() + player[1].getPoints()));
		
		bjCheck();
	}
	
	private void bjCheck() {
		while (dealer[0].getPoints() + dealer[1].getPoints() == 21 || dealer[0].getPoints() + dealer[1].getPoints() == 21) {
			if (dealer[0].getPoints() + dealer[1].getPoints() == 21 && dealer[0].getPoints() + dealer[1].getPoints() == 21) {
				System.out.println("Push!");
				break;
			} else if (dealer[0].getPoints() + dealer[1].getPoints() == 21 && dealer[0].getPoints() + dealer[1].getPoints() != 21) {
				System.out.println("You win!");
				break;
			} else if (dealer[0].getPoints() + dealer[1].getPoints() != 21 && dealer[0].getPoints() + dealer[1].getPoints() == 21) {
				System.out.println("You lose!");
				break;
			} else {
				System.out.println("Would you list to hit or stand? (h/s)");
			}
		}
	}
}
