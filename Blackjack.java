import java.util.ArrayList;
import java.util.Scanner;

public class Blackjack {

	// Constant variables
	private static final String[] SUITS = {"Hearts", "Diamonds", "Clubs", "Spades"};
	private static final String[] VALUES = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
	private static final int[] POINTS = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11};
	private static final int STARTING_BALANCE = 500;

	// Current game state
	private Deck deck;
	private ArrayList<Card> player;
	private ArrayList<Card> dealer;

	private Scanner input;
	private int balance;	// player's money
	private int bet;		// current round's wager

	// Uses the Scanner in CardGames
	public Blackjack(Scanner input) {
		this.input = input;
		balance = STARTING_BALANCE;
	}

	// Play the game until the player quits or runs out of money
	public void play() {
		clearScreen();
		while (true) {
			System.out.println("=== BLACKJACK ===");
			System.out.println("Blackjack pays 3:2.");
			// Check if player quit
			if (!placeBet()) {
				break;
			}
			clearScreen();
			playRound();
			System.out.println("Balance: $" + balance);

			// End the game if player is broke
			if (balance <= 0) {
				System.out.println("\nYou're out of money! Game over.");
				break;
			}

		}
		System.out.println("You leave with $" + balance + ".");
		System.out.println("Returning to main menu...");
	}

	private void clearScreen() {
		System.out.print("\033[H\033[2J"); // I do not understand this, just know it works
		System.out.flush();
	}

	private boolean placeBet() {
		while (true) {
			System.out.print("\nBalance: $" + balance + ". Enter your bet (1-" + balance + ") or q to quit: $");	// Show the user their balance and ask for a bet
			String line = input.nextLine().;
			// Return false so the game loop can stop without playing a round
			if (line.equalsIgnoreCase("q")) {
				return false;
			}
			// Convert text to number
			try {
				int amount = Integer.parseInt(line);	// Figured out what a wrapper class is! Converts a string to an int.
				// Must be at least 1
				if (amount < 1) {
					System.out.println("Your bet must be at least $1.");
				// No overdrafting
				} else if (amount > balance) {
					System.out.println("You only have $" + balance + ".");
				} else {
					// Begin
					bet = amount;
					return true;
				
				}
			} catch (NumberFormatException e) {
				System.out.println("Please enter a valid number.");
			}
		}
	}

	// Money results of a round
	private void win(int amount) {
		// Add the bet to the balance (or 1.5x for blackjack)
		balance += amount;
		System.out.println("You win $" + amount + "!");
	}

	private void lose() {
		// Subtract the bet from the balance
		balance -= bet;
		System.out.println("You lose $" + bet + ".");
	}

	private void push() {
		// No change
		System.out.println("Your bet of $" + bet + " is returned.");
	}

	private void playRound() {
		// Create and shuffle new deck
		deck = new Deck(SUITS, VALUES, POINTS);
		deck.shuffle();
		player = new ArrayList<Card>();
		dealer = new ArrayList<Card>();

		// Deal two cards to each side
		player.add(deck.deal());
		dealer.add(deck.deal());
		player.add(deck.deal());
		dealer.add(deck.deal());

		// Show cards
		System.out.println("\n\n\n--- New round (bet: $" + bet + ") ---");
		System.out.println("Dealer shows: " + dealer.get(0) + " and [hidden]");
		System.out.println("Your hand: " + handToString(player) + " (total " + handValue(player) + ")");

		// Blackjack check
		boolean playerBJ = handValue(player) == 21;
		boolean dealerBJ = handValue(dealer) == 21;
		if (playerBJ || dealerBJ) {
			System.out.println("\nDealer's hand: " + handToString(dealer) + " (total " + handValue(dealer) + ")");
			if (playerBJ && dealerBJ) {
				System.out.println("Both have Blackjack. Push (tie).");
				push();
			} else if (playerBJ) {
				System.out.print("Blackjack! ");
				win(bet * 3 / 2);
			} else {
				System.out.print("Dealer has Blackjack. ");
				lose();
			}
			return;
		}

		// Player's turn
		while (true) {
			System.out.print("\n(h)it or (s)tand? ");
			String choice = input.nextLine().toLowerCase();

			// Draw a card
			if (choice.equals("h") || choice.equals("hit")) {
				Card c = deck.deal();
				player.add(c);
				System.out.println("You drew: " + c);
				System.out.println("Your hand: " + handToString(player) + " (total " + handValue(player) + ")");

				if (handValue(player) > 21) {
					// Player busts
					System.out.print("Bust! ");
					lose();
					return;
				}
				if (handValue(player) == 21) {
					// Prevent user from going past 21
					System.out.println("You have 21!");
					break;
				}
			} else if (choice.equals("s") || choice.equals("stand")) {
				// Let dealer play
				break;
			} else {
				System.out.println("Please type h or s.");
			}
		}

		// Dealer hits until 17 or more
		System.out.println("\nDealer reveals: " + handToString(dealer) + " (total " + handValue(dealer) + ")");
		while (handValue(dealer) < 17) {
			Card c = deck.deal();
			dealer.add(c);
			System.out.println("Dealer draws: " + c + " (total " + handValue(dealer) + ")");
		}

		// Results
		int p = handValue(player);
		int d = handValue(dealer);
		System.out.println("\n\n\nYou: " + p + "   Dealer: " + d);
		// Compare totals
		if (d > 21) {
			System.out.print("Dealer busts! ");
			win(bet);
		} else if (p > d) {
			win(bet);
		} else if (p < d) {
			System.out.print("Dealer wins. ");
			lose();
		} else {
			System.out.println("Push (tie).");
			push();
		}
	}

	// Aces count as 11 unless that would bust, then as 1
	private int handValue(ArrayList<Card> hand) {
		int total = 0;
		int aces = 0;
		for (Card c : hand) {
			total += c.getPoints();
			if (c.getValue().equals("A")) {
				aces++;
			}
		}
		// Change value of aces
		while (total > 21 && aces > 0) {
			total -= 10;
			aces--;
		}
		return total;
	}

	private String handToString(ArrayList<Card> hand) {
		String s = "";
		// Join the cards into one string
		for (int i = 0; i < hand.size(); i++) {
			if (i > 0) {
				s += ", ";
			}
			s += hand.get(i);
		}
		return s;
	}
}