/*
 * Student ID: 18219249 Name: Mohsen Mirhashemi Campus: PT parramatta Campus Tutor Name: Indra Class
 * Day: Thursdays Class Time: 12:00-14:00
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
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
public class Main_18219249 {
  /**
   * @param argas
   * @throws InterruptedException
   * @throws IOException
   */

  final static int LIST_TEAMS_SIZE = 16;
  final static int MATCHS_MAX = 208;
  final static int MATCHS_PER_ROUND_MAX = 8;
  final static int ROUNDS_MAX = 26;
  private static Team_18219249[] listTeam;
  private static Fixture_18219249[] listFixture;
  private static int[] list;
  private static Fixture_18219249[][] listRound;


  public static void main(String[] argas) throws IOException, ParseException, InterruptedException {

    int currentRound;
    int numberOfMatchs;
    listTeam = new Team_18219249[LIST_TEAMS_SIZE];
    listFixture = new Fixture_18219249[MATCHS_MAX];
    listRound = new Fixture_18219249[ROUNDS_MAX][MATCHS_PER_ROUND_MAX];
    list = new int[ROUNDS_MAX];

    readTeam();
    numberOfMatchs = readFixture();
    loadToRound(numberOfMatchs);


    do {
      Scanner scan = new Scanner(System.in);
      System.out.print("The current round now:   ");
      currentRound = scan.nextInt();
    } while (currentRound < 1 || currentRound > LIST_TEAMS_SIZE);

    startupProcess(currentRound);
    mainFunctions(currentRound);
  }


  public static void startupProcess(int currentRound) {
    setList(currentRound);
    for (int i = 1; i <= currentRound; i++) {
      String fileName = "Resources/Round" + i + ".txt";
      File file = new File(fileName);
      if (file.exists()) {
        list[i - 1] = 1;
        readRoundFiles(file, i);
        updateToTeam(i);
        updateRankListTeam();
      } else {
        System.out.println("File: Round" + i + " not loaded . Please update it!");
      }
    }
  }


  public static void mainFunctions(int currentRound) throws IOException, InterruptedException {
    int choices;
    do {
      showMain();
      System.out.print("Please enter your choice: ");
      Scanner in = new Scanner(System.in);
      choices = in.nextInt();
      switch (choices) {
        case 1:
          displayMatchSchedule();
          break;
        case 2:
          enterRoundResults(currentRound);
          break;
        case 3:
          displayLadder();
          break;
        case 4:
          displayTeamResults(currentRound);
          break;
        case 5:
          System.out.println("Program exited");
          break;
        default:
          System.out.println("Please enter the choice between 1 and 4");
          break;
      }
    } while (choices != 5);
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
    System.out.println("---------------Display Match Schedule----------------\n");
    System.out.println("[1]. Display all Rounds                                 \n");
    System.out.println("[2]. Display one Round                                \n");
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
          listTeam[i] = new Team_18219249(name, mascot, home);
        }
        i++;
      }
      if (i != LIST_TEAMS_SIZE) {
        System.out.println("File input error. Systems are going down ...");
        System.exit(1);
      }      
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

  }


  public static int readFixture() throws ParseException {
    int i = 0;
    try {
      File f = new File("Resources/Fixtures.txt");
      Scanner input = new Scanner(new FileInputStream(f));
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

          listFixture[i] = new Fixture_18219249(matchNumber, roundNumber, homeTeamName,
              awayTeamName, venue, kickoffTime, matchDate);
        }
        i++;
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return i;
  }


  public static void readRoundFiles(File f, int roundNumber) {

    try {
      Scanner input = new Scanner(new FileInputStream(f));
      int i = 0;
      while (input.hasNextLine()) {
        String line = input.nextLine();
        if (line.trim() != "") {
          String lines[] = line.split(",");
          int matchNumber = Integer.parseInt(lines[0]);
          int score1 = Integer.parseInt(lines[1]);
          int score2 = Integer.parseInt(lines[2]);
          listRound[roundNumber - 1][i].setHomeTeamScore(score1);
          listRound[roundNumber - 1][i].setAwayTeamScore(score2);
        }
        i++;
      }

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }


  public static int getNumberMatchsOfRound(int roundNumber) {
    int numberMatchs = 0;
    for (int i = 0; i < MATCHS_PER_ROUND_MAX; i++) {
      if (listRound[roundNumber - 1][i] != null) {
        numberMatchs++;
      }
    }
    return numberMatchs;
  }


  public static void updateToTeam(int roundNumber) {
    int numberOfMatchs = getNumberMatchsOfRound(roundNumber);
    for (int i = 0; i < LIST_TEAMS_SIZE; i++) {
      int flag = 0;
      for (int j = 0; j < numberOfMatchs; j++) {
        if (listTeam[i].getTeamName().equals(listRound[roundNumber - 1][j].getHomeTeamName())) {
          flag++;
          listTeam[i].update(listRound[roundNumber - 1][j].getHomeTeamScore(),
              listRound[roundNumber - 1][j].getAwayTeamScore(), 1);
        }
        if (listTeam[i].getTeamName().equals(listRound[roundNumber - 1][j].getAwayTeamName())) {
          flag++;
          listTeam[i].update(listRound[roundNumber - 1][j].getHomeTeamScore(),
              listRound[roundNumber - 1][j].getAwayTeamScore(), 2);
        }
      }
      if (flag == 0) {
        listTeam[i].update(0, 0, 3);
      }
    }
  }


  public static int getNumberRounds(Fixture_18219249[] fx) {
    int i = 1;
    for (int j = 0; j < fx.length - 1; j++) {
      if (fx[j].getRoundNumber() != fx[j + 1].getRoundNumber()) {
        i++;
      }
    }
    return i;
  }


  public static void loadToRound(int numOfMatchs) {
    int i = 0;
    listRound[listFixture[0].getRoundNumber() - 1][i] = listFixture[0];
    for (int k = 1; k < numOfMatchs; k++) {
      if (listFixture[k].getRoundNumber() == listFixture[k - 1].getRoundNumber()) {
        i++;
        listRound[listFixture[k].getRoundNumber() - 1][i] = listFixture[k];
      } else {
        i = 0;
        listRound[listFixture[k].getRoundNumber() - 1][i] = listFixture[k];
      }
    }

  }



  public static void showAllRounds() {
    for (int i = 0; i < ROUNDS_MAX; i++) {
      showOneRound(i + 1);
    }
  }


  public static void showOneRound(int roundNumber) {
    System.out.println("ROUND " + roundNumber + " Matches ");
    String leftAlignFormat = "%-10s\t%-20s\t%-20s\t%-20s\t%-15s%n";
    System.out.format(leftAlignFormat, "Date", "Home Team", "Away Team", "Venue", "Kick off time");
    System.out.format(
        "______________________________________________________________________________________________________%n");
    for (int i = 0; i < MATCHS_PER_ROUND_MAX; i++) {
      final DateFormat df1 = new SimpleDateFormat("HH:mm");
      final DateFormat df2 = new SimpleDateFormat("dd/MM/yy");
      if (listRound[roundNumber - 1][i] == null) {
        continue;
      } else {
        String date = df2.format(listRound[roundNumber - 1][i].getMatchDate());
        String time = df1.format(listRound[roundNumber - 1][i].getKickoffTime());
        System.out.format(leftAlignFormat, date, listRound[roundNumber - 1][i].getHomeTeamName(),
            listRound[roundNumber - 1][i].getAwayTeamName(),
            listRound[roundNumber - 1][i].getMatchVenue(), time);
      }
    }
    System.out.println();
    System.out.println();
  }



  public static void updateRankListTeam() {
    for (int i = 0; i < LIST_TEAMS_SIZE; i++) {
      for (int j = LIST_TEAMS_SIZE - 1; j > 0; j--) {
        if ((listTeam[j].compareTo(listTeam[j - 1])) > 0) {
          Team_18219249 temp = listTeam[j];
          listTeam[j] = listTeam[j - 1];
          listTeam[j - 1] = temp;
        }
      }
    }
    for (int k = 0; k < LIST_TEAMS_SIZE; k++) {
      listTeam[k].updateRank(listTeam);
    }
  }


  public static String[] getRoundResults(int roundNumber) {
    String[] roundResult = new String[MATCHS_PER_ROUND_MAX];
    int score1, score2;
    int numberOfMatchs = getNumberMatchsOfRound(roundNumber);
    int matchNumber = listRound[roundNumber - 1][0].getMatchNumber();
    Scanner scan = new Scanner(System.in);
    for (int i = 0; i < numberOfMatchs; i++, matchNumber++) {
      System.out.println("The Match number  " + matchNumber + " between "
          + listRound[roundNumber - 1][i].getHomeTeamName() + " - "
          + listRound[roundNumber - 1][i].getAwayTeamName());
      System.out
          .print("Enter the score for " + listRound[roundNumber - 1][i].getHomeTeamName() + " :");
      score1 = scan.nextInt();
      System.out
          .print("Enter the score for " + listRound[roundNumber - 1][i].getAwayTeamName() + " :");
      score2 = scan.nextInt();
      roundResult[i] = (matchNumber) + "," + score1 + "," + score2;
    }
    return roundResult;
  }

  public static void setList(int currentRound) {
    for (int i = 0; i < ROUNDS_MAX; i++) {
      list[i] = 0;
    }
  }



  public static int testName(String teamName) {
    for (int i = 0; i < LIST_TEAMS_SIZE; i++) {
      if (teamName.equals(listTeam[i].getTeamName())) {
        return i;
      }
    }
    return -1;
  }

  public static void displayMatchSchedule() throws InterruptedException {
    int choices;
    do {
      displayMatchScheduleChoices();
      System.out.print("Please enter your choice: ");
      Scanner in = new Scanner(System.in);
      choices = in.nextInt();
      switch (choices) {
        case 1:
          showAllRounds();
          break;
        case 2:
          int roundNumber = -1;
          do {
            System.out.print("Please enter the round you want [1-26]: ");
            Scanner in1 = new Scanner(System.in);
            roundNumber = in1.nextInt();
          } while (roundNumber < 1 || roundNumber > LIST_TEAMS_SIZE);
          showOneRound(roundNumber);
          break;
        default:
          System.out.print("Please enter the choice between 1 and 2");
          Thread.sleep(2000);
          break;
      }
    } while ((choices != 1) && (choices != 2));
  }



  public static void enterRoundResults(int currentRound) throws IOException {
    String[] content = new String[MATCHS_PER_ROUND_MAX];
    int flag = 0;
    for (int i = 0; i < currentRound; i++) {
      if (list[i] == 0) {
        flag = 1;
        File file = new File("Resources/Round" + (i + 1) + ".txt");
        showOneRound(i + 1);
        System.out.println("");
        content = getRoundResults(i + 1);
        try {
          BufferedWriter fw = new BufferedWriter(new FileWriter(file));
          for (int k = 0; k < getNumberMatchsOfRound(i + 1) - 1; k++) {
            fw.write(content[k]);
            fw.newLine();
          }
          fw.write(content[getNumberMatchsOfRound(i + 1) - 1]);
          fw.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
        readRoundFiles(file, i + 1);
        updateToTeam(i + 1);
        updateRankListTeam();
      }
    }
    if (flag == 0) {
      System.out.println("All round's results are up to date and loaded! \n");
    }
  }


  public static void displayLadder() {
    String leftAlignFormat = " %-4d\t%-20s\t%-4d\t%-4d\t%-4d\t%-4d\t%-4d\t%-4d\t%-4d\t%-4d%n";
    System.out.println();
    System.out.println(" Pos\tTeam\t\t\tP\tW\tL\tD\tB\tF\tA\tPts");
    System.out.format(
        "____________________________________________________________________________________________%n");

    for (Team_18219249 t : listTeam) {
      System.out.format(leftAlignFormat, t.getRank(), t.getTeamName(), t.getNumberGamePlayeds(),
          t.getNumberGameWons(), t.getNumberGameLosts(),
          (t.getNumberGamePlayeds() - (t.getNumberGameWons() + t.getNumberGameLosts())),
          t.getNumberOfByes(), t.getPointsScoreFor(), t.getPointsScoreAgainst(),
          t.getTotalPoints());
      System.out.format(
          "____________________________________________________________________________________________%n");
    }
    System.out.println();
    System.out.println();
  }



  public static void displayTeamResults(int currentRound) {
    String teamName;
    int position;
    do {
      System.out.print("Please enter name of the Team you want to see: ");
      Scanner scan = new Scanner(System.in);
      teamName = scan.nextLine();
      position = testName(teamName);
    } while (position == -1);
    System.out.println(listTeam[position].getTeamName() + " " + listTeam[position].getHomeGround());
    if (currentRound == 1) {
      System.out.println("Match Result for rounds 1");
    } else {
      System.out.println("Match Results for rounds 1 to " + currentRound);
      System.out.println("Round\tDate\t\t\tTeam Played\t\tW/L/D\t\tScore");
      System.out.format(
          "_______________________________________________________________________________%n");
    }

    listTeam[position].showResult(currentRound, listRound);
  }


}
