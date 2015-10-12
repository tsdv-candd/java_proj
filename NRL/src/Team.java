
public class Team implements Comparable<Team> {

  private String teamName;
  private String teamMascot;
  private String homeGround;
  private int rank;
  private int numberGamePlayeds;
  private int numberGameWons;
  private int numberGameLosts;
  private int numberOfByes;
  private int pointsScoreFor;
  private int pointsScoreAgainst;
  private int totalPoints;

  public Team(String teamName, String teamMascot, String homeGround) {
    this.teamName = teamName;
    this.teamMascot = teamMascot;
    this.homeGround = homeGround;
  }

  public String getTeamName() {
    return teamName;
  }

  public void setTeamName(String teamName) {
    this.teamName = teamName;
  }

  public String getTeamMascot() {
    return teamMascot;
  }

  public void setTeamMascot(String teamMascot) {
    this.teamMascot = teamMascot;
  }

  public String getHomeGround() {
    return homeGround;
  }

  public void setHomeGround(String homeGround) {
    this.homeGround = homeGround;
  }

  public int getRank() {
    return rank;
  }

  public void setRank(int rank) {
    this.rank = rank;
  }

  public int getNumberGamePlayeds() {
    return numberGamePlayeds;
  }

  public void setNumberGamePlayeds(int numberGamePlayeds) {
    this.numberGamePlayeds = numberGamePlayeds;
  }

  public int getNumberGameWons() {
    return numberGameWons;
  }

  public void setNumberGameWons(int numberGameWons) {
    this.numberGameWons = numberGameWons;
  }

  public int getNumberGameLosts() {
    return numberGameLosts;
  }

  public void setNumberGameLosts(int numberGameLosts) {
    this.numberGameLosts = numberGameLosts;
  }

  public int getNumberOfByes() {
    return numberOfByes;
  }

  public void setNumberOfByes(int numberOfByes) {
    this.numberOfByes = numberOfByes;
  }

  public int getPointsScoreFor() {
    return pointsScoreFor;
  }

  public void setPointsScoreFor(int pointsScoreFor) {
    this.pointsScoreFor = pointsScoreFor;
  }

  public int getPointsScoreAgainst() {
    return pointsScoreAgainst;
  }

  public void setPointsScoreAgainst(int pointsScoreAgainst) {
    this.pointsScoreAgainst = pointsScoreAgainst;
  }

  public int getTotalPoints() {
    return totalPoints;
  }

  public void setTotalPoints(int totalPoints) {
    this.totalPoints = totalPoints;
  }

  public void update(int homeScore, int awayScore, boolean isHomeTeam) {
    numberGamePlayeds += 1;
    if (isHomeTeam) {
      this.setPointsScoreFor(homeScore);
      this.setPointsScoreAgainst(awayScore);
      if (homeScore > awayScore) {
        numberGameWons += 1;
      } else {
        numberGameLosts += 1;
      }
    } else {
      this.setPointsScoreFor(awayScore);
      this.setPointsScoreAgainst(homeScore);
      if (awayScore > homeScore) {
        numberGameWons += 1;
      } else {
        numberGameLosts += 1;
      }
    }
    totalPoints += numberGameWons * 2;
    numberOfByes = numberGamePlayeds - (numberGameWons + numberGameLosts);

  }

  @Override
  public int compareTo(Team otherTeam) {
    if (totalPoints == otherTeam.totalPoints) {
      int temp1 = pointsScoreFor - pointsScoreAgainst;
      int temp2 = otherTeam.pointsScoreFor - otherTeam.pointsScoreAgainst;
      if (temp1 == temp2) {
        if (pointsScoreFor == otherTeam.pointsScoreFor) {
          return teamName.compareToIgnoreCase(otherTeam.teamName);
        } else {
          return (pointsScoreFor - otherTeam.pointsScoreFor);
        }
      } else {
        return (temp1 - temp2);
      }
    } else {
      return (totalPoints - otherTeam.totalPoints);
    }

  }

  public void updateRank(Team[] t) {
    int i = 0;
    while (!teamName.equals(t[i].getTeamName())) {
      i++;
    }
    this.setRank(i + 1);
  }

  public void showResult(int currentRound, Fixture[][] fx) {
    for (int i = 0; i < currentRound; i++) {
      for (int j = 0; j < 8; j++) {
        if (teamName.equals(fx[i][j].getHomeTeamName())) {
          fx[i][j].display(true);
        }
        if (teamName.equals(fx[i][j].getAwayTeamName())) {
          fx[i][j].display(false);
        }
      }
    }
    System.out.println("Total competition poins after " + currentRound + " rounds: " + totalPoints);
  }



}
