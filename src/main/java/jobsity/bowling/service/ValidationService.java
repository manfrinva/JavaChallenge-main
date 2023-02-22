package jobsity.bowling.service;

import jobsity.bowling.service.dto.ThrowDTO;
import jobsity.bowling.service.dto.FrameDTO;
import jobsity.bowling.service.dto.DashboardDTO;


public interface ValidationService {

  /**
  * It verifies that is a valid game loaded in the  dashboard
  * @param dashboard to validate
  * @return validation status
  */
  public boolean isValid(DashboardDTO dashboard);

  /**
  * It verifies if frame is valid
  * @param frame to validate
  * @return validation status
  */
  public boolean isValidFrame(FrameDTO frame);

  /**
  * It verifies if a throw is valid
  * @param trow to validate
  * @return validation status
  */
  public boolean isValidThrow(ThrowDTO trow);

}