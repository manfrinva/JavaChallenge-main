package jobsity.bowling.service.impl;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;
import org.junit.Test;
import org.junit.Assert;

public class ThrowServiceImplTest {

    private ThrowServiceImpl throwService = new ThrowServiceImpl();


    @Test
    public void isOKParse() {
      String[] ls = new String[]{"Jeff	10","Jeff	7","Jeff	3","Jeff	9",
      "Jeff	0","Jeff	10","Jeff	0","Jeff	8","Jeff	8","Jeff	2","Jeff	F",
      "Jeff	6", "Jeff	10","Jeff	10","Jeff	10","Jeff	8","Jeff	1"};

      List<String> lines = Arrays.asList(ls);
      Map<String,List<String>>  ts = throwService.parse(lines);

      Assert.assertNotNull(ts);
      Assert.assertTrue(ts.containsKey("Jeff"));

      List<String> scores = ts.get("Jeff");
      Assert.assertNotNull(scores);
      Assert.assertTrue(scores.size() == ls.length);
    }

    @Test
    public void emptyLinesToParse() {
      List<String> lines = new ArrayList<String>();
      Map<String,List<String>>  ts = throwService.parse(lines);
      Assert.assertNull(ts);
    }

    @Test
    public void notParseableLines() {
      String[] ls = new String[]{"Jeff	10","Jeff	7","Jeff"};
      List<String> lines = Arrays.asList(ls);
      Map<String,List<String>>  ts = throwService.parse(lines);
      Assert.assertNull(ts);
    }

    @Test
    public void addThrow() {

    }

    @Test
    public void isPlayerWithNullThrows() {
        Map<String,List<String>> ts = null;
        Assert.assertFalse(this.throwService.isPlayer(ts,"randomPlayer"));
    }

    @Test
    public void playerNameInMap() {
      Map<String,List<String>> ts =  new LinkedHashMap<String, List<String>>();
      List<String> emptyScores = new ArrayList<String>();
      String emptyScoresPlayerName = "emptyPlayer";
      ts.put(emptyScoresPlayerName, emptyScores);
    }

    @Test
    public void playerNameNotInMap() {
      Map<String,List<String>> ts =  new LinkedHashMap<String, List<String>>();
      List<String> emptyScores = new ArrayList<String>();
      String emptyScoresPlayerName = "emptyPlayer";
      ts.put(emptyScoresPlayerName, emptyScores);

      String anotherPlayerName  = "anotherPlayer";
      Assert.assertFalse(this.throwService.isPlayer(ts,anotherPlayerName));
    }

    @Test
    public void playerNameInMapWithEmptyScores() {
      Map<String,List<String>> ts =  new LinkedHashMap<String, List<String>>();
      ts.put("Juan",null);
      ts.put("Andrea",null);
      Assert.assertTrue(this.throwService.isPlayer(ts,"Andrea"));
    }

}