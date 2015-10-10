import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Mason
 *
 */
public class Main {
	/**
	 * @param argas
	 * @throws InterruptedException
	 * @throws IOException
	 */

	private static Team[] listTeam;

	public static void main(String[] argas) throws InterruptedException {
		int choices;
		listTeam = new Team[16];
		readTeam(listTeam);
		for (Team t : listTeam) {
			System.out.println(t.getTeamName() + "\t\t" + t.getTeamMascot() + "\t" + t.getHomeGround());
		}
		do {
			clearScreen();
			showMain();
			System.out.println("Please enter your choice: ");
			Scanner in = new Scanner(System.in);
			choices = in.nextInt();
			switch (choices) {
			case 1:
				System.out.println("Do the method 1\n");
				Thread.sleep(5000);
				break;
			case 2:
				System.out.println("Do the method 2\n");
				Thread.sleep(5000);
				break;
			case 3:
				System.out.println("Do the method 3\n");
				Thread.sleep(5000);
				break;
			case 4:
				System.out.println("Do the method 4\n");
				Thread.sleep(5000);
			case 5:
				System.out.println("Program is exiting...");
				Thread.sleep(5000);
				break;
			default:
				System.out.println("Please enter the choice between 1 and 4");
				Thread.sleep(10000);
				break;
			}
		} while (choices != 5);
		clearScreen();
		System.exit(0);

	}

	public static void showMain() {
		System.out.println("------------------------Main menu------------------------\n");
		System.out.println("[1]. Display Match Schedule                          \n");
		System.out.println("[2]. Enter Round Results                              \n");
		System.out.println("[3]. Display Ladder                                     \n");
		System.out.println("[4]. Team Results                                      \n");
		System.out.println("[5]. Exit Program                                       \n");
		System.out.println("--------------------------------------------------------------\n");
	}

	public static void clearScreen() {
		for (int i = 0; i < 1000; i++) {
			System.out.println();
		}

	}

	public static void readTeam(Team[] t) {

		try {
			File f = new File("Resources/Teams.txt");
			Scanner input = new Scanner(new FileInputStream(f));
			int i = 0;
			while (input.hasNextLine()) {
				String line = input.nextLine();
				System.out.println("---" + line);
				Scanner scanner = new Scanner(line);
				scanner.useDelimiter(",");
				if (scanner.hasNext()) {
					String name = scanner.next();
					String mascot = scanner.next();
					String home = scanner.next();
					System.out.println(name + "\t" + mascot + "\t" + home);
					t[i] = new Team(name, mascot, home);
				}
				i++;
			}

			// Hanle exeption the number lines of input greater than 16
			if (i != 16) {
				System.out.println("File input error. Systems are going down ...");
				System.exit(1);
			} else
				System.out.println("Read file ok");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
