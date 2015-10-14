/*
 * Student ID: 18219249 
 * Name: Mohsen Mirhashemi 
 * Campus: PT parramatta Campus 
 * Tutor Name: Indra Class
 * Day: Thursdays Class 
 * Time: 12:00-14:00
 */
public class Team_18219249 implements Comparable<Team_18219249> {

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

  public Team_18219249(String teamName, String teamMascot, String homeGround) {
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

  /*******************************************************************************
   * @method name: update
   * @brief : Update team score
   * @param : int homeScore team score earn at their home's Stadium.
   * @param : int awayScore team score earn at away Stadium.
   * @param : int check check if the match is byes, at home or at away stadium.
   * @retval : None
   *****************************************************************************/
  public void update(int homeScore, int awayScore, int check) {
    if (check == 3) {
      this.numberOfByes += 1;
    } else {
      numberGamePlayeds += 1;
      if (check == 1) {
        this.pointsScoreFor += homeScore;
        this.pointsScoreAgainst += awayScore;
        if (homeScore > awayScore) {
          numberGameWons += 1;
        } else if (homeScore < awayScore) {
          numberGameLosts += 1;
        }
      }
      if (check == 2) {
        this.pointsScoreFor += awayScore;
        this.pointsScoreAgainst += homeScore;
        if (awayScore > homeScore) {
          numberGameWons += 1;
        } else if (awayScore < homeScore) {
          numberGameLosts += 1;
        }
      }
    }
    int numberGameDraws = numberGamePlayeds - (numberGameWons + numberGameLosts);
    totalPoints = 2 * (numberGameWons + numberOfByes) + 1 * numberGameDraws;
  }

  /*******************************************************************************
   * @method name: compareTo
   * @brief : Compare the result with other team to update rank (ladder)
   * @param : Team_18219249 otherTeam input team to be compare with
   * @retval : return 0 if two team's total score are equal, return > 0 if this team's total score
   *         is higher return < 0 if this team's total score is lower than input team.
   *****************************************************************************/
  public int compareTo(Team_18219249 otherTeam) {
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

  /*******************************************************************************
   * @method name: showResult
   * @brief : Show team result until the current round.
   * @param : int currentRound Current round number which user input at startup
   * @param : int[] list is the list of flags to check round is loaded or not
   * @retval None.
   *****************************************************************************/
  public void showResult(int currentRound, int[] list, Fixture_18219249[][] fx) {   
    for (int i = 0; i < currentRound; i++) {
      for (int j = 0; j < 8; j++) {
        if (fx[i][j] != null) {
          if (list[i] == 1) {
            if (teamName.equals(fx[i][j].getHomeTeamName())) {
              fx[i][j].display(true, true);
            }
            if (teamName.equals(fx[i][j].getAwayTeamName())) {
              fx[i][j].display(true, false);
            }
          }else{
            if (teamName.equals(fx[i][j].getHomeTeamName())) {
              fx[i][j].display(false, true);
            }
            if (teamName.equals(fx[i][j].getAwayTeamName())) {
              fx[i][j].display(false, false);
            }
          }

        } else {
          continue;
        }
      }
    }
    System.out.println();
    System.out.println("Total competition poins after " + currentRound + " rounds: " + totalPoints);
    System.out.println();
  }
}
