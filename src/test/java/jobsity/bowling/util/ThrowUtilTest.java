package jobsity.bowling.util;

import jobsity.bowling.service.dto.ThrowDTO;
import org.junit.Test;
import org.junit.Assert;

public class ThrowUtilTest {

  @Test
  public void getScoreInt() {

    String  ok = "10";
    String  f  = "F";
    String nok = "badscore";
    String nullSCore = null;

    int okScore  = ThrowUtil.getScoreInt(ok);
    int fScore   = ThrowUtil.getScoreInt(f);
    int nokScore = ThrowUtil.getScoreInt(nok);
    int nullScore = ThrowUtil.getScoreInt(nullSCore);

    Assert.assertTrue(okScore == 10);
    Assert.assertTrue(fScore == 0);
    Assert.assertTrue(nokScore == ThrowDTO.INVALID_SCORE);
    Assert.assertTrue(nullScore == ThrowDTO.INVALID_SCORE);
  }

  @Test
  public void isFaultScore() {
    String ok = "F";
    String nok = "isNotF";
    String nullString = null;

    boolean isOk = ThrowUtil.isFaultScore(ok);
    boolean isNok = ThrowUtil.isFaultScore(nok);
    boolean isNull = ThrowUtil.isFaultScore(nullString);

    Assert.assertTrue(isOk);
    Assert.assertFalse(isNok);
    Assert.assertFalse(isNok);
    Assert.assertFalse(isNull);
  }
}