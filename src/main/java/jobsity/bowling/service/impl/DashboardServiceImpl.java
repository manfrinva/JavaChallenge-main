package jobsity.bowling.service.impl;

import jobsity.bowling.service.ValidationService;
import jobsity.bowling.service.LineService;
import jobsity.bowling.service.ThrowService;
import jobsity.bowling.service.FileService;
import jobsity.bowling.service.DashboardService;
import jobsity.bowling.service.dto.LineDTO;
import java.util.Map;
import java.util.List;
import jobsity.bowling.service.dto.DashboardDTO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class DashboardServiceImpl implements DashboardService {

  @Autowired
  protected FileService fileService;

  @Autowired
  protected ThrowService throwService;

  @Autowired
  protected LineService lineServie;

  @Autowired
  protected ValidationService validationService;
  
  //This is used to log information about it.
  protected Logger logger = LoggerFactory.getLogger(DashboardServiceImpl.class);

  //{@inheritDoc}
  @Override
  public DashboardDTO load(String fileName) {
    logger.debug(String.format("Starting to load the file:%s", fileName));
    if (fileName == null  ||  (fileName != null && fileName.isEmpty())) {
      return null;
    }

    List<String> lines = this.fileService.load(fileName);

    if (lines == null || (lines != null && lines.isEmpty())) {
      return null;
    }

    Map<String,List<String>> trows = this.throwService.parse(lines);

    if (trows ==  null || (trows != null && trows.isEmpty()) ) {
      logger.error(String.format("The throws group by player is null or empty "));
      return null;
    }

    List<LineDTO> linesDTO = this.lineServie.parse(trows);

    if (linesDTO == null || (lines != null && lines.isEmpty())) {
      logger.error(String.format("The lines are null or empty after trying to parse the throws"));
      return null;
    }

    DashboardDTO dashboard = new DashboardDTO(linesDTO);
    boolean isValid = this.isValid(dashboard);
    dashboard.setValid(isValid);
    return dashboard;
  }

   @Override
   public boolean isValid(DashboardDTO dashboard) {
     return this.validationService.isValid(dashboard);
   }
}