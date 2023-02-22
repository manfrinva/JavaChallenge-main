package jobsity.bowling.service.dto;

import java.util.List;

//It contains the information related with a bowling frame
public class FrameDTO {
  public static final int MAX_NUMBER_OF_FRAMES = 10;  
  public static final int FIRST_FRAME = 1;
  public final static int LAST_FRAME = 10;
  public final static String LAST_FRAME_STR = "10";
  private int number;
  private List<ThrowDTO> throwz;
  private int score;
  private int result;
  public FrameDTO() {}

  public FrameDTO(int number) {
    this.number = number;
  }

  public FrameDTO(int number,List<ThrowDTO> trows) {
    this.number = number;
    this.throwz = trows;
  }

  @Override
  public String toString() {
    StringBuffer buffer = new StringBuffer();
    buffer.append(String.format("Frame number:%s, Score:%s\n",this.number,this.score));
    for (ThrowDTO t:this.throwz) {
      buffer.append(String.format("\t%s\n", t));
    }
    buffer.append(String.format("\tTotal:%s\n\n",this.result));
    return buffer.toString();
  }

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public List<ThrowDTO> getThrowz() {
		return throwz;
	}

	public void setThrowz(List<ThrowDTO> throwz) {
		this.throwz = throwz;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}
}