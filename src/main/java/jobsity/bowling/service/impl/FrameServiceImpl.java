package jobsity.bowling.service.impl;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import jobsity.bowling.util.ThrowUtil;
import jobsity.bowling.service.FrameService;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import jobsity.bowling.service.dto.ThrowDTO;
import jobsity.bowling.service.dto.FrameDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class FrameServiceImpl implements FrameService {

  //This is used to log information about it.
  protected Logger logger = LoggerFactory.getLogger(FrameServiceImpl.class);

  @Override
  public FrameDTO createFrame(int number, List<String> scores) {
    FrameDTO frame       = new FrameDTO(number);

    List<ThrowDTO>ts = IntStream.range(0, scores.size())
      .mapToObj(i -> new ThrowDTO(scores.get(i),i))
      .collect(Collectors.toList());
    frame.setThrowz(ts);
    return frame;
  }

  @Override
  public boolean isLastFrame(int currentFrame) {
    return currentFrame == FrameDTO.LAST_FRAME;
  }

  @Override
  public boolean isStrike(String score) {
    return ThrowDTO.STRIKE_SCORE_STR.equals(score);
  }

  @Override
  public boolean isStrike(FrameDTO frame) {

    if (frame == null) {
      return Boolean.FALSE;
    }

    List<ThrowDTO> trows = frame.getThrowz();

    if (trows == null || (trows != null && trows.isEmpty()) ) {
      return Boolean.FALSE;
    }

    int throwsNumber = trows.size();
    if (throwsNumber != 1) {
      return Boolean.FALSE;
    }

    int score = this.sumirezeThrows(trows, throwsNumber);

    if (ThrowDTO.MAX_SCORE_PER_THROW == score) {
      return Boolean.TRUE;
    }

    return Boolean.FALSE;
  }

  public boolean isSparkFrame(FrameDTO frame) {

    if (frame == null) {
      return Boolean.FALSE;
    }

    List<ThrowDTO> trows = frame.getThrowz();

    if (trows == null || (trows != null && trows.isEmpty())) {
      return Boolean.FALSE;
    }

    int throwsNumber = trows.size();


    int score = this.sumirezeThrows(trows, throwsNumber);

    if (ThrowDTO.MAX_SCORE_PER_THROW != score) {
      return Boolean.FALSE;
    }
    return Boolean.TRUE;
  }

  @Override
  public boolean isOpenFrame(FrameDTO frame) {

      if (frame == null) {
        return Boolean.FALSE;
      }

      List<ThrowDTO> trows = frame.getThrowz();

      if (trows == null || (trows != null && trows.isEmpty())) {
        return Boolean.FALSE;
      }

      int throwsNumber = trows.size();

      if (throwsNumber != ThrowDTO.THROWS_PER_OPEN_FRAME) {
        return Boolean.FALSE;
      }

      int score = this.sumirezeThrows(trows, throwsNumber);

      if (score >= 0  || score < ThrowDTO.MAX_SCORE_PER_THROW) {
          return Boolean.TRUE;
      }

      return Boolean.FALSE;
  }

  public int sumirezeThrows(List<ThrowDTO> trows,int throwsNumber) {

      if (trows == null || (trows != null && trows.isEmpty())) {
        return 0;
      }
      // it is not possible create a sublist that is bigger than trows size.
      if (throwsNumber > trows.size()) {
        return -1;
      }

      return trows.subList(0, throwsNumber)
          .stream().map(t -> ThrowUtil.getScoreInt(t.getScore()))
          .reduce(0,Integer::sum);
  }

  /**
   * Get the frame score
   * @param scores values of throws.
   * @return frame score calculated.
  */
  private int getFrameScore(List<String> scores) {

    if (scores == null || (scores != null && scores.isEmpty())) {
      return 0;
    }

    return scores.stream()
           .map( s -> ThrowUtil.getScoreInt(s)).reduce(0, Integer::sum);
  }

  private List<String> createScores(String[] scores) {
    return Arrays.asList(scores);
  }

  @Override
  public List<FrameDTO> createFrames(List<String> scores) {
    List<FrameDTO> frames = new ArrayList<FrameDTO>();

    if (scores == null || (scores != null && scores.isEmpty())) {
      return frames;
    }

    int currentFrame = 1;
    for (int i=0; i<scores.size(); i++) {
      String score = scores.get(i);

      if (this.isLastFrame(currentFrame)) {
        int secondIndex = i+1;
        int thirdIndex  = i+2;
        String secondScore = scores.get(secondIndex);
        String thirdScore  = scores.get(thirdIndex);
        List<String> scs = this.createScores(new String[]{score, secondScore, thirdScore});

        FrameDTO frame = this.createFrame(currentFrame,scs);
        int frameScore = this.getFrameScore(scs);
        frame.setScore(frameScore);
        frames.add(frame);
        i = i+2;
      } else if(this.isStrike(score)) {
        List<String> scs= this.createScores(new String[]{score});
        int frameScore = this.getFrameScore(scs);
        FrameDTO frame = this.createFrame(currentFrame,scs);
        frame.setScore(frameScore);
        frames.add(frame);
      } else {

        int secondIndex = i+1;
        String secondScore = scores.get(secondIndex);
        List<String >scs =Arrays.asList(new String[]{score, secondScore});
        int frameScore = this.getFrameScore(scs);
        FrameDTO frame = this.createFrame(currentFrame,scs);
        frame.setScore(frameScore);
        frames.add(frame);
        i++; // because I created a new throw with that score.
      }
      currentFrame++;
    }
    return frames;
  }
}