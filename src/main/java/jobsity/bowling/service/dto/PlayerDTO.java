package jobsity.bowling.service.dto;

//It contains the information related with bowling player.
public class PlayerDTO {

  private String name;

  public PlayerDTO(String name) {
    this.name = name;
  }
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

  public String toString() {
    return String.format("Player:%s",this.name);
  }
}