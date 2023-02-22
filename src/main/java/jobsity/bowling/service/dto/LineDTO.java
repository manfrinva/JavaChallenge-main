package jobsity.bowling.service.dto;

import java.util.ArrayList;
import java.util.List;

//This is the representation of line with contains the player and score frames information.
public class LineDTO {
  //Frames in the line 
  private List<FrameDTO> frames;
  //Player information

  private PlayerDTO player;

  public LineDTO() {

  }

  public LineDTO(String playerName) {
    this.player = new PlayerDTO(playerName);
  }

  public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append(String.format("\nLine:%s\n", this.player));
      for (FrameDTO f:this.frames) {
        buffer.append(String.format("\t%s",f));
      }
      return buffer.toString();
  }

  public List<Integer> getFramesScore() {
    List<Integer> as = new ArrayList<Integer>();
    for (FrameDTO f:this.frames) {
      as.add(f.getScore());
    }
    return as;
  }
	

	public List<FrameDTO> getFrames() {
		return frames;
	}

	public void setFrames(List<FrameDTO> frames) {
		this.frames = frames;
	}


	public PlayerDTO getPlayer() {
		return player;
	}

	public void setPlayer(PlayerDTO player) {
		this.player = player;
	}
}