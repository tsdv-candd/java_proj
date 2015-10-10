
public class Team {
	
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
	
	public Team( String teamName, String teamMascot, String homeGround){
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
}
