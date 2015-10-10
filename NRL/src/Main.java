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

	final static int LIST_TEAMS_SIZE = 16;
	final static int MATCHS_MAX = 208;
	final static int MATCHS_PER_ROUND_MAX = 8;
	final static int ROUNDS_MAX = 26;
	private static Team[] listTeam;
	private static Fixture[] listFixture;
	/* private static Fixture[] listMatch; */
	private static Fixture[][] listRound;

	public static void main(String[] argas) throws InterruptedException, ParseException {
		int choices;
		int currentRound;
		listTeam = new Team[LIST_TEAMS_SIZE];
		listFixture = new Fixture[MATCHS_MAX];
		listRound = new Fixture[ROUNDS_MAX][MATCHS_PER_ROUND_MAX];
		readTeam();
		readFixture();
		loadToRound();
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
			Scanner scan = new Scanner(System.in);
			System.out.println("The current round now:   \n");
			currentRound = scan.nextInt();
		} while (currentRound < 1 || currentRound > 26);

		for (int i = 1; i <= currentRound; i++) {
			String fileName = "Resources/Round" + i + ".txt";
			File file = new File(fileName);
			if (file.exists()) {
				readRoundFiles(file, i);
			} else {
				System.out.println("File: Round" + i + " not exist. Please update it!");
			}
		}

		// Test read round file
		for (int i = 0; i < 26; i++) {
			System.out.println("ROUND " + (i + 1) + " Matches ");
			for (int j = 0; j < 8; j++) {
				final DateFormat df1 = new SimpleDateFormat("HH:mm");
				final DateFormat df2 = new SimpleDateFormat("dd/MM/yy");
				String date = df2.format(listRound[i][j].getMatchDate());
				String time = df1.format(listRound[i][j].getKickoffTime());
				System.out.println(date + "\t" + listRound[i][j].getHomeTeamName() + "\t"
						+ listRound[i][j].getAwayTeamName() + "\t" + listRound[i][j].getMatchVenue() + "\t"
						+ listRound[i][j].getHomeTeamScore() + "\t" + listRound[i][j].getAwayTeamScore()+ "\t" + time);
			}
			System.out.println("\n\n");
		}
		// End of test

		do {
			// clearScreen();
			showMain();
			System.out.println("Please enter your choice: ");
			Scanner in = new Scanner(System.in);
			choices = in.nextInt();
			switch (choices) {
			case 1:
				displayMatchSchedule();
				Thread.sleep(20000);
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

	public static void displayMatchScheduleChoices() {
		System.out.println("------------------------Display Match Schedule------------------------\n");
		System.out.println("[1]. Display all Rounds                                                 \n");
		System.out.println("[2]. Display one Round                                                \n");
	}

	public static void clearScreen() {
		for (int i = 0; i < 1000; i++) {
			System.out.println();
		}

	}

	public static void readTeam() {

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
					listTeam[i] = new Team(name, mascot, home);
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

	public static void readFixture() throws ParseException {

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

					listFixture[i] = new Fixture(matchNumber, roundNumber, homeTeamName, awayTeamName, venue,
							kickoffTime, matchDate);
				}
				i++;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void readRoundFiles(File f, int roundNumber) {
		try {
			Scanner input = new Scanner(new FileInputStream(f));
			while (input.hasNextLine()) {
				String line = input.nextLine();
				if (line.trim() != "") {
					String rounds[] = line.split(",");
					int matchNumber = Integer.parseInt(rounds[0]);
					int score1 = Integer.parseInt(rounds[1]);
					int score2 = Integer.parseInt(rounds[2]);
					listRound[roundNumber - 1][matchNumber - 1].setHomeTeamScore(score1);
					listRound[roundNumber - 1][matchNumber - 1].setAwayTeamScore(score2);
				}
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

	public static void loadToRound() {
		/*
		 * for (int j = 0; j < 2; j++) { int i = 0; for (int k = 0; k <
		 * fx.length; k++) { if (fx[k].getRoundNumber() == (j + 1)) {
		 * rounds[j][i] = fx[k]; i++; } } }
		 */

		for (int k = 0; k < listFixture.length; k++) {
			listRound[listFixture[k].getRoundNumber() - 1][listFixture[k].getMatchNumber() - 1] = listFixture[k];

		}
	}

	// Function for display all rounds match schedule
	public static void showAllRounds() {
		for (int i = 0; i < 26; i++) {
			System.out.println("ROUND " + (i + 1) + " Matches ");
			for (int j = 0; j < 8; j++) {
				final DateFormat df1 = new SimpleDateFormat("HH:mm");
				final DateFormat df2 = new SimpleDateFormat("dd/MM/yy");
				String date = df2.format(listRound[i][j].getMatchDate());
				String time = df1.format(listRound[i][j].getKickoffTime());
				System.out.println(date + "\t" + listRound[i][j].getHomeTeamName() + "\t"
						+ listRound[i][j].getAwayTeamName() + "\t" + listRound[i][j].getMatchVenue() + "\t" + time);
			}
			System.out.println("\n\n");
		}
	}

	// Function for display one round match schedule when user select
	public static void showOneRound(int roundNumber) {
		System.out.println("ROUND " + roundNumber + " Matches ");
		for (int i = 0; i < 8; i++) {
			final DateFormat df1 = new SimpleDateFormat("HH:mm");
			final DateFormat df2 = new SimpleDateFormat("dd/MM/yy");
			String date = df2.format(listRound[roundNumber - 1][i].getMatchDate());
			String time = df1.format(listRound[roundNumber - 1][i].getKickoffTime());
			System.out.println(date + "\t" + listRound[roundNumber - 1][i].getHomeTeamName() + "\t"
					+ listRound[roundNumber - 1][i].getAwayTeamName() + "\t"
					+ listRound[roundNumber - 1][i].getMatchVenue() + "\t" + time);
		}
	}

	// Use case 1
	public static void displayMatchSchedule() throws InterruptedException {
		int choices;
		do {
			clearScreen();
			displayMatchScheduleChoices();
			System.out.println("Please enter your choice: ");
			Scanner in = new Scanner(System.in);
			choices = in.nextInt();
			switch (choices) {
			case 1:
				showAllRounds();
				break;
			case 2:
				int roundNumber = -1;
				do {
					System.out.println("Please enter the round you want [1-26]: ");
					Scanner in1 = new Scanner(System.in);
					roundNumber = in1.nextInt();
				} while (roundNumber < 1 || roundNumber > 26);
				showOneRound(roundNumber);
				break;
			default:
				System.out.println("Please enter the choice between 1 and 2");
				Thread.sleep(2000);
				break;
			}
		} while ((choices != 1) && (choices != 2));
	}

}
