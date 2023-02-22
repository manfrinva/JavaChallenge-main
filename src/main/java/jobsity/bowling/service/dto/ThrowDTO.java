package jobsity.bowling.service.dto;

//ThrowDTO contains the score and number of player throw.
public class ThrowDTO {
  public final static int FIRST_THROW  = 1;
  public final static int SECOND_THROW = 2;
  public final static int THIRD_THROW  = 3;
  public final static String FAULT_SCORE = "F";
  public final static int INVALID_SCORE = -1;
  public final static int NUMBER_OF_THROWS_FOR_LAST_FRAME = 3;
  public final static int STRIKE_SCORE = 10;
  public final static String STRIKE_SCORE_STR = "10";
  public final static int MAX_SCORE_PER_THROW = 10;
  public final static int THROWS_PER_OPEN_FRAME = 2;
  public final static int THROWS_PER_SPARK = 2;
  public final static int THRWOS_PER_STRIKE =1;
  private String score;
  private int index;

  public ThrowDTO() {}

  public ThrowDTO(String score,int index) {
    this.score = score;
    this.index = index;
  }
	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

  @Override
  public String toString() {
    return String.format("Throw(index:%s, score:%s)", this.index, this.score);
  }
}