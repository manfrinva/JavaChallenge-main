package jobsity.bowling.util;

import jobsity.bowling.service.dto.ThrowDTO;

public  class ThrowUtil  {

  /***
  * It is the numeric representation of current score.
  * If is not a valid score it should returns -1,
  * @param score to convert
  * @return int, numeric convertion of curret string score.
  */

  public static int getScoreInt(String score) {
    try {
      if (ThrowDTO.FAULT_SCORE.equals(score)) {
        return 0;
      }
      
      return Integer.parseInt(score);
    } catch(Exception e) {
      return ThrowDTO.INVALID_SCORE;
    }
  }

  //It determines if is a frame fault score
  public static boolean isFaultScore(String score) {
    return ThrowDTO.FAULT_SCORE.equals(score);
  }
}