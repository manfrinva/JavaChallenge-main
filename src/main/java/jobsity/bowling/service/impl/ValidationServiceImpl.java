package jobsity.bowling.service.impl;

import jobsity.bowling.util.ThrowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import jobsity.bowling.service.FrameService;
import jobsity.bowling.service.dto.ThrowDTO;
import jobsity.bowling.service.dto.FrameDTO;
import jobsity.bowling.service.dto.LineDTO;
import java.util.List;
import jobsity.bowling.service.dto.DashboardDTO;
import org.springframework.stereotype.Service;
import jobsity.bowling.service.ValidationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class  ValidationServiceImpl implements ValidationService {

  private FrameService frameService;

  //This is used to log information about it.
  protected Logger logger = LoggerFactory.getLogger(ValidationServiceImpl.class);

  @Autowired
  public ValidationServiceImpl(final FrameService frameService) {
    this.frameService = frameService;
  }
  
  @Override
  public boolean isValid(DashboardDTO dashboard) {
    List<LineDTO> lines = dashboard.getLines();

    for (LineDTO l:lines) {
        List<FrameDTO> frames = l.getFrames();
        for (FrameDTO f:frames) {
          boolean isValidFrame = this.isValidFrame(f);
          if (!isValidFrame) {
            return Boolean.FALSE;
          }
        }
    }

    return Boolean.TRUE;
  }
  
  @Override
  public boolean isValidFrame(FrameDTO frame) {

    if (frame == null) {
      return Boolean.FALSE;
    }

    List<ThrowDTO> trows = frame.getThrowz();

    if (trows == null  || (trows != null && trows.isEmpty())) {
      return Boolean.FALSE;
    }

    int throwsNumber    = trows.size();
    int frameNumber     = frame.getNumber();
    boolean isLastFrame = this.frameService.isLastFrame(frameNumber);
    boolean isStrikeFrame = this.frameService.isStrike(frame);
    //boolean isSparkFrame  = this.frameService.isSparkFrame(frame);
    boolean isOpenFrame   = this.frameService.isOpenFrame(frame);

    /*
    logger.debug(String.format("throwsNumber:%s, frameNumber:%s, isLastFrame:%s, isStrikeFrame:%s, isSparkFrame:%s, isOpenFrame:%s",
      throwsNumber, frameNumber,isLastFrame,isStrikeFrame, isSparkFrame,isOpenFrame));
      */

    if (!(frameNumber>0) && !(frameNumber<=FrameDTO.MAX_NUMBER_OF_FRAMES)) {
        logger.error(String.format("The frame number :%s is not valid", frameNumber));
      return Boolean.FALSE;
    }

    if (!isLastFrame && throwsNumber == ThrowDTO.NUMBER_OF_THROWS_FOR_LAST_FRAME) {
      logger.error(String.format("The frame %s is not the last frame but have more than two throws", frameNumber));
      return Boolean.FALSE;
    }

    if (!isStrikeFrame && throwsNumber == ThrowDTO.THRWOS_PER_STRIKE) {
      logger.error(String.format("The frame %s is not strike but have just one throw", frameNumber));
      return Boolean.FALSE;
    }

    /*
    if (isSparkFrame && throwsNumber != ThrowDTO.THROWS_PER_SPARK) {
      logger.error(String.format("The frame %s is spark but have throws diferents to two ", frameNumber));
      return Boolean.FALSE;
    }*/

    if (isOpenFrame && throwsNumber != ThrowDTO.THROWS_PER_OPEN_FRAME) {
      logger.error(String.format("The frame %s is an open frame but have throws diferents to two ", frameNumber));
      return Boolean.FALSE;
    }

    for (ThrowDTO t:trows) {

      boolean isValidThrow = this.isValidThrow(t);

      if (!isValidThrow) {
          return Boolean.FALSE;
      }
    }

    return Boolean.TRUE;
  }
  
  @Override
  public boolean isValidThrow(ThrowDTO trow) {

    if (trow == null) {
      return Boolean.FALSE;
    }

    int score = ThrowUtil.getScoreInt(trow.getScore());

    if (score < 0 || score > 10) {
      return Boolean.FALSE;
    }

    return Boolean.TRUE;
  }

}