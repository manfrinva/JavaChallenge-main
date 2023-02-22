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
public class ZeroGameIT extends BaseGameIT  {

  protected Logger logger = LoggerFactory.getLogger(ZeroGameIT.class);

  @Test
  public void processZeroScore() {
    File perfectFile = new File(this.getClass().getResource("/zeros.txt").getFile());
    String pathFile  = String.format("%s",perfectFile.getAbsolutePath().toString());

    DashboardDTO dashboard = this.dashboardService.load(pathFile);
    Assert.assertNotNull(dashboard);
    Assert.assertTrue(dashboard.isValid());

    dashboard = this.scoreService.calculate(dashboard);
    Assert.assertNotNull(dashboard);

    LineDTO zeroLine      = dashboard.getLines().get(0);
    Assert.assertNotNull(zeroLine);

    List<FrameDTO> zeroFrames = zeroLine.getFrames();
    Assert.assertNotNull(zeroFrames);

    FrameDTO zeroLastFrame  = zeroFrames.get(FrameDTO.MAX_NUMBER_OF_FRAMES - 1);
    Assert.assertNotNull(zeroLastFrame);

    int expectedScore = 0;
    Assert.assertTrue(zeroLastFrame.getResult() == expectedScore);
  }

}