package jobsity.bowling.service;

import jobsity.bowling.service.dto.DashboardDTO;

public interface DashboardService {

 /**
  * It creates a dashboard realted to file
  * @param fileName to load
  * @return DashboardDTO paresed.
  */
 public DashboardDTO load(String fileName);
  /**
  * It determines if dashboard loaded from file is valid.
  * @param dashboard to validate
  * @return validation status.
  */
  public boolean  isValid(DashboardDTO dashboard);
}