/*
 * Student ID:  18219249
 * Name:        Mohsen Mirhashemi
 * Campus:      PT parramatta Campus
 * Tutor Name:  Indra 
 * Class Day:   Thursdays
 * Class Time:  12:00-14:00
 */

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Fixture_18219249 {
  private int matchNumber;
  private int roundNumber;
  private String homeTeamName;
  private String awayTeamName;
  private Date matchDate;
  private String matchVenue;
  private Date kickoffTime;
  private int homeTeamScore;
  private int awayTeamScore;

  public Fixture_18219249(int matchNumber, int roundNumber, String homeName, String awayName, String Venue,
      Date kickoffTime, Date date) {
    this.matchNumber = matchNumber;
    this.roundNumber = roundNumber;
    this.homeTeamName = homeName;
    this.awayTeamName = awayName;
    this.matchVenue = Venue;
    this.kickoffTime = kickoffTime;
    this.matchDate = date;
  }


  public int getMatchNumber() {
    return matchNumber;
  }

  public void setMatchNumber(int matchNumber) {
    this.matchNumber = matchNumber;
  }

  public int getRoundNumber() {
    return roundNumber;
  }

  public void setRoundNumber(int roundNumber) {
    this.roundNumber = roundNumber;
  }

  public String getHomeTeamName() {
    return homeTeamName;
  }

  public void setHomeTeamName(String homeTeamName) {
    this.homeTeamName = homeTeamName;
  }

  public String getAwayTeamName() {
    return awayTeamName;
  }

  public void setAwayTeamName(String awayTeamName) {
    this.awayTeamName = awayTeamName;
  }

  public Date getMatchDate() {
    return matchDate;
  }

  public void setMatchDate(Date matchDate) {
    this.matchDate = matchDate;
  }

  public String getMatchVenue() {
    return matchVenue;
  }

  public void setMatchVenue(String matchVenue) {
    this.matchVenue = matchVenue;
  }

  public Date getKickoffTime() {
    return kickoffTime;
  }

  public void setKickoffTime(Date kickoffTime) {
    this.kickoffTime = kickoffTime;
  }

  public int getHomeTeamScore() {
    return homeTeamScore;
  }

  public void setHomeTeamScore(int homeTeamScore) {
    this.homeTeamScore = homeTeamScore;
  }

  public int getAwayTeamScore() {
    return awayTeamScore;
  }

  public void setAwayTeamScore(int awayTeamScore) {
    this.awayTeamScore = awayTeamScore;
  }

  public void display(boolean isHomeTeam) {
    final DateFormat df2 = new SimpleDateFormat("dd/MM/yy");
    String date = df2.format(this.getMatchDate());
    if (isHomeTeam) {
      String status;
      if (homeTeamScore > awayTeamScore) {
        status = "Win";
      } else {
        status = "Loss";
      }
      System.out.println(roundNumber + "\t" + date + "\t" + awayTeamName + "\t" + status + "\t"
          + homeTeamScore + "-" + awayTeamScore);
    } else {
      String status;
      if (homeTeamScore > awayTeamScore) {
        status = "Loss";
      } else {
        status = "Win";
      }
      System.out.println(roundNumber + "\t" + date + "\t" + homeTeamName + "\t" + status + "\t"
          + homeTeamScore + "-" + awayTeamScore);
    }

  }

}
