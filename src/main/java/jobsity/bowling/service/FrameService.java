package jobsity.bowling.service;

import java.util.List;
import jobsity.bowling.service.dto.FrameDTO;


public interface FrameService {

    /**
    * It creates a frame with n number of throws, according with scores
    * @param number
    * @param scores it's represent the score for each throw
    */
    public FrameDTO createFrame(int number, List<String> scores);

    /**
     * It Creates a list of frames according with list of scores.
    */
    public List<FrameDTO> createFrames(List<String> scores);

    /**
    * It verifies if is the last frame in the game.
    * @@param currentFrame, frame index to check
    * @return valdation status.
    */
    public boolean isLastFrame(int currentFrame);

    /**
    * It verifies if score is a  strike throw.
    * @param score to validate
    * @return validation status
    */
    public boolean isStrike(String score);

    /**
    * It verifies if a frame is a sttrike
    * @param frame to check
    * @return status validation.
    */
    public boolean isStrike(FrameDTO frame);

    /**
    * It verifies if a frame is a spark
    * @param frame to check
    * @return status validation.
    */
    public boolean isSparkFrame(FrameDTO frame);

    /**
    * It verifies if a open frame
    * @param frame to check
    * @return status validation.
    */
    public boolean isOpenFrame(FrameDTO frame);
}