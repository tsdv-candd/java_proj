import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
	private static Fixture[] listFixture;
	private static Fixture[][] listRound;

	public static void main(String[] argas) throws InterruptedException, ParseException {
		int choices;
		listTeam = new Team[16];
		listFixture = new Fixture[208];
		listRound = new Fixture[26][8];
		readTeam(listTeam);
		readFixture(listFixture);
		loadToRound(listRound, listFixture);
		showAllRounds(listRound);
		// Test show data from listRound

		

		// Test read data from Teams.txt file
		/*
		 * for (Team t : listTeam) { System.out.println(t.getTeamName() + "\t\t"
		 * + t.getTeamMascot() + "\t" + t.getHomeGround()); }
		 */
		/* int numberRound = getNumberRounds(listFixture); */
		/*
		 * int [] rounds = new int [27]; rounds[0] = numberRound; for(int j = 1;
		 * j < numberRound; j++ ){ rounds[j] = j; } int i = 0; for(int j = 1; j
		 * < numberRound; j++){
		 * 
		 * } for(; i < listFixture.length; i++){ final DateFormat df1 = new
		 * SimpleDateFormat("HH:mm"); final DateFormat df2 = new
		 * SimpleDateFormat("dd/MM/yy"); String date =
		 * df2.format(m.getMatchDate()); String time =
		 * df1.format(m.getKickoffTime());
		 * 
		 * 
		 * }
		 */

		do {
			// clearScreen();
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
				Scanner scanner = new Scanner(line);
				scanner.useDelimiter(",");
				if (scanner.hasNext()) {
					String name = scanner.next();
					String mascot = scanner.next();
					String home = scanner.next();
					// System.out.println(name + "\t" + mascot + "\t" + home);
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

	public static void readFixture(Fixture[] fx) throws ParseException {

		try {
			File f = new File("Resources/Fixtures1.txt");
			Scanner input = new Scanner(new FileInputStream(f));
			int i = 0;
			while (input.hasNextLine()) {
				String line = input.nextLine();
				if (line.trim() != "") {
					String matchs[] = line.split(",");
					final DateFormat df1 = new SimpleDateFormat("HH:mm");
					final DateFormat df2 = new SimpleDateFormat("dd/MM/yy");
					int roundNumber = Integer.parseInt(matchs[0]);
					int matchNumber = Integer.parseInt(matchs[1]);					
					String homeTeamName = matchs[2].toString();
					String awayTeamName = matchs[3].toString();
					String venue = matchs[4].toString();
					String kickoff = matchs[5].toString();
					Date kickoffTime = df1.parse(kickoff);
					String date = matchs[6].toString();
					Date matchDate = df2.parse(date);

					fx[i] = new Fixture(matchNumber, roundNumber, homeTeamName, awayTeamName, venue, kickoffTime,
							matchDate);
				}
				i++;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static int getNumberRounds(Fixture[] fx) {
		int i = 1;
		for (int j = 0; j < fx.length - 1; j++) {
			if (fx[j].getRoundNumber() != fx[j + 1].getRoundNumber()) {
				i++;
			}
		}
		return i;
	}

	public static void loadToRound(Fixture[][] rounds, Fixture[] fx) {
		/*for (int j = 0; j < 2; j++) {
			int i = 0;
			for (int k = 0; k < fx.length; k++) {
				if (fx[k].getRoundNumber() == (j + 1)) {
					rounds[j][i] = fx[k];
					i++;
				}
			}
		}*/
		
		for(int k = 0; k < fx.length; k++){
			rounds[fx[k].getRoundNumber() - 1][fx[k].getMatchNumber() - 1] = fx[k];
			
		}
	}
	
	
	// Function for display all rounds match schedule
	public static void showAllRounds(Fixture[][] rounds){
		for (int i = 0; i < 26; i++) {
			System.out.println("ROUND " + (i + 1) + " Matches ");
			for (int j = 0; j < 8; j++) {
				final DateFormat df1 = new SimpleDateFormat("HH:mm");
				final DateFormat df2 = new SimpleDateFormat("dd/MM/yy");
				String date = df2.format(rounds[i][j].getMatchDate());
				String time = df1.format(rounds[i][j].getKickoffTime());
				System.out.println(date + "\t" + rounds[i][j].getHomeTeamName() + "\t"
						+ rounds[i][j].getAwayTeamName() + "\t" + rounds[i][j].getMatchVenue() + "\t" + time);
			}
			System.out.println("\n\n");
		}
	}
	
	// Function for display one round match schedule when user select
	public static void showAllRounds(Fixture[][] rounds, int roundNumber){
		
	}

}
