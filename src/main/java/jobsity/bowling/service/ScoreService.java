package jobsity.bowling.service;

import jobsity.bowling.service.dto.FrameDTO;
import java.util.List;
import jobsity.bowling.service.dto.DashboardDTO;

/**
 * ScoreBusinessService expose the logic to calculate the score in the current match.
 */
public interface ScoreService {

  /**
  * It calculates the score related with bowling dasboard
  * @@param dashboard with information related to throws and players
  * @return it returned a dashboard with scores.
  */
  public DashboardDTO calculate(DashboardDTO dashboard);

  /**
  * It calculate the score for all framees
  * @@param frames to calculate the scores
  */
  public List<FrameDTO> calculateScore(List<FrameDTO> frames);

}