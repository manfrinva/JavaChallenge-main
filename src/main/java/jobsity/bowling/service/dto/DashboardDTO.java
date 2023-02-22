package jobsity.bowling.service.dto;

import java.util.List;

//DashboardDTO contains information with bowling lines
public class DashboardDTO {

  private List<LineDTO> lines;

  private boolean valid;

  public DashboardDTO() {

  }

  public DashboardDTO(List<LineDTO> lines) {
    this.lines = lines;
  }
	
	public List<LineDTO> getLines() {
		return lines;
	}

	public void setLines(List<LineDTO> lines) {
		this.lines = lines;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

  public String toString() {
    StringBuffer buffer = new StringBuffer();
    for (LineDTO l:this.lines) {
      buffer.append(String.format("%s\n",l));
    }
    return buffer.toString();
  }
}