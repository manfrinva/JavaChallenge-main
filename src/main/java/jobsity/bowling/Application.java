package jobsity.bowling;

import org.springframework.context.ConfigurableApplicationContext;
import jobsity.bowling.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;

import jobsity.bowling.service.DashboardService;
import jobsity.bowling.service.dto.DashboardDTO;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//Application class is the entry point of whole system.
@ComponentScan("jobsity.bowling")
@SpringBootApplication
public class Application implements CommandLineRunner {

  protected Logger logger = LoggerFactory.getLogger(Application.class);

  @Autowired
  private DashboardService dashboardService;

  @Autowired
  private ScoreService scoreService;

  @Autowired
  private ConfigurableApplicationContext context;

  public static void main(String[] args) {

      SpringApplication app = new SpringApplication(Application.class);
      app.setBannerMode(Banner.Mode.OFF);
      app.run(args);
  }

  @Override
  public void run(String... args) throws Exception {

      if (args.length == 1) {
        String fileName = args[0];

        this.showDashboard(fileName);
      } else {
        logger.error("Wrong number of parameters, muest just one, the bowling scores path");
        logger.debug("java -jar bowling-.0.0-SNAPSHOT.jar '/Users/cristian/Downloads/scores .txt'");
      }
      context.close();
  }

  private void showDashboard(String fileName) {
    DashboardDTO dashboard =  this.dashboardService.load(fileName);

    if (dashboard == null) {
      logger.error(String.format("It is not possible to create a dashboard  from file game:%s", fileName));
      return;
    }


    if (!dashboard.isValid()) {
      logger.debug("The dashboard is NOT valid!");
      return;
    }

    dashboard = this.scoreService.calculate(dashboard);
    logger.debug(dashboard.toString());

  }

}