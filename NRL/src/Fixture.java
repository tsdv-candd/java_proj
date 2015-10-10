import java.util.Date;

public class Fixture {
     private int matchNumber;
     private int roundNumber;
     private String homeTeamName;
     private String awayTeamName;
     private Date matchDate;
     private String matchVenue;
     private Date kickoffTime;
     private int homeTeamScore;
     private int awayTeamScore;
     
     public Fixture(int matchNumber, int roundNumber, String homeName, String awayName, String Venue, Date kickoffTime, Date date){
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
         
     
     
}
