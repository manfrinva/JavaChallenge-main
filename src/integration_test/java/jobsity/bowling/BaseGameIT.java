package jobsity.bowling;

import jobsity.bowling.service.impl.ScoreServiceImpl;
import jobsity.bowling.service.impl.ValidationServiceImpl;
import jobsity.bowling.service.impl.LineServiceImpl;
import jobsity.bowling.service.impl.ThrowServiceImpl;
import jobsity.bowling.service.impl.FrameServiceImpl;
import jobsity.bowling.service.impl.FileServiceImpl;
import jobsity.bowling.service.impl.DashboardServiceImpl;
import jobsity.bowling.service.DashboardService;
import jobsity.bowling.service.ScoreService;
import org.junit.runner.RunWith;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
    DashboardServiceImpl.class,
    FileServiceImpl.class,
    FrameServiceImpl.class,
    ThrowServiceImpl.class,
    LineServiceImpl.class,
    ValidationServiceImpl.class,
    ScoreServiceImpl.class})

public  abstract class BaseGameIT {

  @Autowired
  protected DashboardService dashboardService;

  @Autowired
  protected ScoreService scoreService;
}