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

		do {
			Scanner scan = new Scanner(System.in);
			System.out.print("The current round now:   ");
			currentRound = scan.nextInt();
		} while (currentRound < 1 || currentRound > LIST_TEAMS_SIZE);

		for (int i = 1; i <= currentRound; i++) {
			String fileName = "Resources/Round" + i + ".txt";
			File file = new File(fileName);
			if (file.exists()) {
				readRoundFiles(file, i);
				updateToTeam(i);
				updateRankListTeam();
			} else {
				System.out.println("File: Round" + i + " not exist. Please update it!");
			}
		}
	
	

		// Test read round file
		
		  for (int i = 0; i < ROUNDS_MAX; i++) { System.out.println(
		  "ROUND " + (i + 1) + " Matches "); for (int j = 0; j < MATCHS_PER_ROUND_MAX; j++) {
		  final DateFormat df1 = new SimpleDateFormat("HH:mm"); 
		  final DateFormat df2 = new SimpleDateFormat("dd/MM/yy"); 
		  String date = df2.format(listRound[i][j].getMatchDate());
		  String time = df1.format(listRound[i][j].getKickoffTime()); 
		  System.out.println(date + "\t" + listRound[i][j].getRoundNumber() + "\t" +listRound[i][j].getHomeTeamName() 
		  + "\t" +	 listRound[i][j].getAwayTeamName() + "\t" +
		  listRound[i][j].getMatchVenue() + "\t" +
		  listRound[i][j].getHomeTeamScore() + "\t" +
		  listRound[i][j].getAwayTeamScore() + "\t" + time); }
		  System.out.println("\n\n"); }
		 
		// End of test read round file


		
		
		do {
			// clearScreen();
			showMain();
			System.out.print("Please enter your choice: ");			
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
				displayLadder();
				Thread.sleep(15000);
				break;
			case 4:				
				displayTeamResults(currentRound);
				Thread.sleep(30000);
				break;
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

	// ========================================================================|
	// --------------------------------------------------------Function showMain------------------------------------------------------------------------------|
	// ========================================================================|

	public static void showMain() {
		System.out.println("------------------------Main menu------------------------\n");
		System.out.println("[1]. Display Match Schedule                          \n");
		System.out.println("[2]. Enter Round Results                              \n");
		System.out.println("[3]. Display Ladder                                     \n");
		System.out.println("[4]. Team Results                                      \n");
		System.out.println("[5]. Exit Program                                       \n");
		System.out.println("--------------------------------------------------------------\n");
	}

	// ========================================================================|
	// --------------------------------------------------------Function displayMatchScheduleChoices-----------------------------------------------------|
	// ========================================================================|
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

	// ========================================================================|
	// --------------------------------------------------------Function readTeam-----------------------------------------------------------------------------|
	// ========================================================================|
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
			if (i != LIST_TEAMS_SIZE) {
				System.out.println("File input error. Systems are going down ...");
				System.exit(1);
			} else
				System.out.println("Read file ok");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// ========================================================================|
	// --------------------------------------------------------Function readFixture----------------------------------------------------------------------------|
	// ========================================================================|
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

	// ========================================================================|
	// --------------------------------------------------------Function readRoundFiles-----------------------------------------------------------------------|
	// ========================================================================|
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

	// ========================================================================|
	// -----------------------------------------------------------------Function updateToTeam-------------------------------------------------------------|
	// ========================================================================|
	public static void updateToTeam(int roundNumber) {
		for (int i = 0; i < LIST_TEAMS_SIZE; i++) {
			for (int j = 0; j < MATCHS_PER_ROUND_MAX; j++) {			
				if (listTeam[i].getTeamName().equals(listRound[roundNumber - 1][j].getHomeTeamName())) {					
					listTeam[i].update(listRound[roundNumber - 1][j].getHomeTeamScore(),
							listRound[roundNumber - 1][j].getAwayTeamScore(), true);
				}
				if (listTeam[i].getTeamName().equals(listRound[roundNumber - 1][j].getAwayTeamName())) {					
					listTeam[i].update(listRound[roundNumber - 1][j].getHomeTeamScore(),
							listRound[roundNumber - 1][j].getAwayTeamScore(), false);
				}
			}
		}
	}

	// ========================================================================|
	// --------------------------------------------------------Function getNumberRounds------------------------------------------------------------------|
	// ========================================================================|
	public static int getNumberRounds(Fixture[] fx) {
		int i = 1;
		for (int j = 0; j < fx.length - 1; j++) {
			if (fx[j].getRoundNumber() != fx[j + 1].getRoundNumber()) {
				i++;
			}
		}
		return i;
	}

	// ========================================================================|
	// --------------------------------------------------------------Function loadToRound--------------------------------------------------------------------|
	// ========================================================================|
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

	// ========================================================================|
	// --------------------------------------------------------Function showAllRounds------------------------------------------------------------------------|
	// ========================================================================|
	public static void showAllRounds() {
		for (int i = 0; i < ROUNDS_MAX; i++) {
			System.out.println("ROUND " + (i + 1) + " Matches ");
			for (int j = 0; j < MATCHS_PER_ROUND_MAX; j++) {
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

	// ========================================================================|
	// ----------------------------------------------------------Function showOneRound--------------------------------------------------------------------|
	// ========================================================================|
	public static void showOneRound(int roundNumber) {
		System.out.println("ROUND " + roundNumber + " Matches ");
		for (int i = 0; i < MATCHS_PER_ROUND_MAX; i++) {
			final DateFormat df1 = new SimpleDateFormat("HH:mm");
			final DateFormat df2 = new SimpleDateFormat("dd/MM/yy");
			String date = df2.format(listRound[roundNumber - 1][i].getMatchDate());
			String time = df1.format(listRound[roundNumber - 1][i].getKickoffTime());
			System.out.println(date + "\t" + listRound[roundNumber - 1][i].getHomeTeamName() + "\t"
					+ listRound[roundNumber - 1][i].getAwayTeamName() + "\t"
					+ listRound[roundNumber - 1][i].getMatchVenue() + "\t" + time);
		}
	}
	
	
	
	// ========================================================================|
		// --------------------------------------------------------Function updateRankListTeam---------------------------------------------------------------|
		// ========================================================================|
		public static void updateRankListTeam(){
			//Insert Sort
			/*for(int i = 1; i < LIST_TEAMS_SIZE; i++){			
				int index = i;
				while(index > 0 && (listTeam[index - 1].compareTo(listTeam[index]) > 0) ){				
				    Team temp = listTeam[index];
				    listTeam[index] = listTeam[index -1];
				    listTeam[index - 1] = temp;
				    index--;
				}			
			}*/
			
			//Buble Sort
			for(int i = 0; i < LIST_TEAMS_SIZE; i++){
				for(int j = LIST_TEAMS_SIZE - 1; j > 0; j--){
					if((listTeam[j].compareTo(listTeam[j-1])) > 0 ){
						Team temp = listTeam[j];
						listTeam[j] = listTeam[j - 1];
						listTeam[j - 1] = temp;
					}
				}
			}	
			for(int k = 0; k < LIST_TEAMS_SIZE; k++){
				listTeam[k].updateRank(listTeam);
			}
		}
	
     
		
		// ========================================================================|
		// -----------------------------------------------------------------------Function testName---------------------------------------------------------------|
		// ========================================================================|
		public static int testName(String teamName){
			for(int i = 0; i < LIST_TEAMS_SIZE; i++){
				if(teamName.equals(listTeam[i].getTeamName())){
					return i;
				}
			}
			return -1;
		}
				
		
		
	// ========================================================================|
	// --------------------------------------------------------Function displayMatchSchedule---------------------------------------------------------------|
	// ========================================================================|
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
				} while (roundNumber < 1 || roundNumber > LIST_TEAMS_SIZE);
				showOneRound(roundNumber);
				break;
			default:
				System.out.println("Please enter the choice between 1 and 2");
				Thread.sleep(2000);
				break;
			}
		} while ((choices != 1) && (choices != 2));
	}
	
	
	// ========================================================================|
	// --------------------------------------------------------Function displayTeamResults-----------------------------------------------------------------|
	// ========================================================================|
	public static void displayTeamResults(int currentRound){
		String teamName;		
		int position;
		do {
			clearScreen();			
			System.out.println("Please enter name of the Team you want to see: ");
			Scanner scan = new Scanner(System.in);
			teamName = scan.nextLine();	
			//System.out.println("Ten cua team la: " + teamName);
			position = testName(teamName);
			//System.out.println("Vi tri cua team la: " + (position + 1));
		} while (position == -1);
		//System.out.println("Vong hien tai: " + currentRound);	
		System.out.println("Vi tri cua team la: " + (position + 1));
		System.out.println("listTeam[position].getTeamName la: " + listTeam[position].getTeamName());
		listTeam[position].showResult(currentRound, listRound);		
	}
	
	
	
	// ========================================================================|
	// ------------------------------------------------------------Function displayLadder---------------------------------------------------------------------|
	// ========================================================================|
	public static void displayLadder(){
		for (Team t : listTeam) {
			System.out.println(t.getRank()+ "\t"+ t.getTeamName() + "\t" + t.getNumberGamePlayeds() + "\t" + t.getNumberGameWons() + "\t" +  t.getNumberGameLosts() + "\t" + t.getNumberOfByes() + "\t"
					+ t.getPointsScoreFor() +  "\t" + t.getPointsScoreAgainst() +  "\t"
					+ t.getTotalPoints());
		}
	}
	
	
	
	
	
	
}
