package jobsity.bowling;

import jobsity.bowling.service.dto.DashboardDTO;
import jobsity.bowling.service.dto.LineDTO;
import jobsity.bowling.service.dto.FrameDTO;
import org.junit.Test;
import org.junit.Assert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;

public class TwoPlayerGameIT extends BaseGameIT{

  protected Logger logger = LoggerFactory.getLogger(TwoPlayerGameIT.class);

  @Test
  public void processTwoPlyers() {
    File perfectFile = new File(this.getClass().getResource("/scores.txt").getFile());
    String pathFile  = String.format("%s",perfectFile.getAbsolutePath().toString());

    DashboardDTO dashboard = this.dashboardService.load(pathFile);
    Assert.assertNotNull(dashboard);
    Assert.assertTrue(dashboard.isValid());

    dashboard = this.scoreService.calculate(dashboard);
    Assert.assertNotNull(dashboard);
    LineDTO jeffLine      = dashboard.getLines().get(0);
    LineDTO johnLine      = dashboard.getLines().get(1);

    Assert.assertNotNull(jeffLine);
    Assert.assertNotNull(johnLine);

    List<FrameDTO> jeffFrames = jeffLine.getFrames();
    List<FrameDTO> johnFrames = johnLine.getFrames();

    Assert.assertNotNull(jeffFrames);
    Assert.assertNotNull(johnFrames);


    FrameDTO jeffLastFrame  = jeffFrames.get(FrameDTO.MAX_NUMBER_OF_FRAMES - 1);
    FrameDTO johnLastFrame  = johnFrames.get(FrameDTO.MAX_NUMBER_OF_FRAMES - 1);

    Assert.assertNotNull(jeffLastFrame);
    Assert.assertNotNull(johnLastFrame);

    int expectedJeffScore = 167;
    int expectedJhonScore = 151;

    Assert.assertTrue(jeffLastFrame.getResult() == expectedJeffScore);
    Assert.assertTrue(johnLastFrame.getResult() == expectedJhonScore);
  }

}