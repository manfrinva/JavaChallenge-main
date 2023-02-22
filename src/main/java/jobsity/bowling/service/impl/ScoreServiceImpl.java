package jobsity.bowling.service.impl;

import jobsity.bowling.util.ThrowUtil;
import jobsity.bowling.service.dto.ThrowDTO;
import jobsity.bowling.service.FrameService;
import org.springframework.beans.factory.annotation.Autowired;
import jobsity.bowling.service.dto.FrameDTO;
import java.util.ArrayList;
import jobsity.bowling.service.dto.LineDTO;
import java.util.List;
import jobsity.bowling.service.ScoreService;
import jobsity.bowling.service.dto.DashboardDTO;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


//This class is in charge to calculate the score of dashboard
@Service
public class ScoreServiceImpl  implements ScoreService {

  protected Logger logger = LoggerFactory.getLogger(ScoreServiceImpl.class);
  //Frame service to determine the type of frame
  protected FrameService fs;

  @Autowired
  public ScoreServiceImpl(final FrameService fs) {
    this.fs = fs;
  }

  @Override
  public DashboardDTO calculate(DashboardDTO dashboard) {

    List<LineDTO> lines = dashboard.getLines();

    for (LineDTO line:lines) {
      List<FrameDTO> frames = line.getFrames();
      frames = this.calculateScore(frames);
    }

    return dashboard;
  }

  @Override
  public List<FrameDTO> calculateScore(List<FrameDTO> frames) {

      if (frames == null || (frames != null && frames.isEmpty())) {
        return null;
      }

      int lastFrameResult = 0;

      for (FrameDTO frame:frames) {
        int result = 0;
        if (fs.isLastFrame(frame.getNumber())) {
          result = this.calculateOpenFrame(frames, frame,lastFrameResult);
        }

        else if (fs.isStrike(frame)) {
          result = this.calculateStrikeFrame(frames, frame,lastFrameResult);
        }

        else if(fs.isSparkFrame(frame)) {
          result = this.calculateSparkFrame(frames, frame,lastFrameResult);
        }

        else if (fs.isOpenFrame(frame)) {
          result = this.calculateOpenFrame(frames, frame,lastFrameResult);
        }
        lastFrameResult = result;
        frame.setResult(result);
      }
      return frames;
  }

  /**
  * It calculates the score result for spark frame
  * @param frames
  * @param cf, current frame to calculate the score result
  * @param beforeResult
  */
  public int calculateSparkFrame(List<FrameDTO> frames, FrameDTO cf, int beforeResult) {
    return this.calculateFrame(frames, cf, 1, beforeResult);
  }

  /**
  * It calculates the score result for strike frame
  * @param frames
  * @param cf, current frame to calculate the score result
  * @param beforeResult
  */
  public int calculateStrikeFrame(List<FrameDTO> frames,FrameDTO cf, int beforeResult) {
    return this.calculateFrame(frames, cf, 2, beforeResult);
  }

  /**
  * It calculates the score result for open frame
  * @param frames
  * @param cf, current frame to calculate the score result
  * @param beforeResult
  */
  public int calculateOpenFrame(List<FrameDTO> frames,FrameDTO f, int beforeResult) {
    if (isNotCalculableFrame(frames,f)) {
      return 0;
    }
    return f.getNumber() == 1 ? f.getScore() : f.getScore() + beforeResult;
  }

  /**
  * It checks if is not calculable the current frame, it means if  null
  * @param frames to checks
  * @param cf, current frame
  * @return validation status
  */
  public boolean isNotCalculableFrame(List<FrameDTO> frames,FrameDTO cf) {
    return  (frames == null || (frames != null && frames.isEmpty()) || cf == null );
  }

  /**
  * It calculate the score for specific frame
  * @param frames
  * @param cf, current frame to calculate the final score
  */
  public int calculateFrame(List<FrameDTO> frames,FrameDTO cf, int offset, int beforeResult) {

    if (this.isNotCalculableFrame(frames, cf)) {
      return 0;
    }

    int currentFrame = cf.getNumber();

    if (currentFrame <0 || currentFrame > FrameDTO.MAX_NUMBER_OF_FRAMES || offset < 0 ){
      return 0;
    }

    List<ThrowDTO> ts = new ArrayList<ThrowDTO>(2);
    int framesSize = frames.size();

    //  get the throws from frames;
    for (int i=currentFrame; i<(currentFrame+offset); i++) {

      if (i == framesSize) {
        break;
      }

      FrameDTO f = frames.get(i);
      if (ts.size() == offset) {
        break;
      }

      List<ThrowDTO> cts = f.getThrowz();
      ts.addAll(cts);

    }
    // sumarize the next painfalls scores
    int nextResults = ts.subList(0,offset)
            .stream()
            .map(t -> ThrowUtil.getScoreInt(t.getScore())).reduce(0, Integer::sum);

    if (cf.getNumber() == 1) {
      return (cf.getScore() + nextResults);
    }

    return cf.getScore() +  nextResults + beforeResult;
  }
}