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


public class PerfectGameIT extends BaseGameIT {

  protected Logger logger = LoggerFactory.getLogger(PerfectGameIT.class);


  @Test
  public void processPerfectScore() {
    File perfectFile = new File(this.getClass().getResource("/perfect.txt").getFile());
    String pathFile  = String.format("%s",perfectFile.getAbsolutePath().toString());

    DashboardDTO dashboard = this.dashboardService.load(pathFile);
    Assert.assertNotNull(dashboard);
    Assert.assertTrue(dashboard.isValid());

    dashboard = this.scoreService.calculate(dashboard);
    Assert.assertNotNull(dashboard);
    LineDTO jeffLine      = dashboard.getLines().get(0);
    Assert.assertNotNull(jeffLine);

    List<FrameDTO> frames = jeffLine.getFrames();
    Assert.assertNotNull(frames);

    FrameDTO lastFrame    = frames.get(FrameDTO.MAX_NUMBER_OF_FRAMES - 1);
    Assert.assertNotNull(lastFrame);

    int expectedJeffScore = 300;
    Assert.assertTrue(lastFrame.getResult() == expectedJeffScore);
  }

}