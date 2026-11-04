import java.util.Scanner;

public class CardGames {

	private Scanner input = new Scanner(System.in);

	// Shows the game menu until the user quits
	public void start() {
		boolean running = true;
		while (running) {
			System.out.println("\n=== CARD GAMES ===");
			System.out.println("1. Blackjack");
			System.out.println("2. Quit");
			System.out.print("Choose a game: ");
			String choice = input.nextLine().toLowerCase();

			switch (choice) {
				case "1":
				case "blackjack":
					Blackjack game = new Blackjack(input);
					game.play();
					break;
				case "2":
				case "quit":
				case "q":
					running = false;
					break;
				default:
					System.out.println("Please enter 1 or 2.");
					break;
			}
		}
		System.out.println("Goodbye!");
	}
}