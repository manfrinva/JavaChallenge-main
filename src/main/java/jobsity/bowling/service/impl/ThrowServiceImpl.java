package jobsity.bowling.service.impl;

import jobsity.bowling.service.ThrowService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ThrowServiceImpl implements ThrowService {

  //This is used to log information about this class.
  protected Logger logger = LoggerFactory.getLogger(ThrowServiceImpl.class);

  public Map<String,List<String>>  parse(List<String> lines) {

    if (lines == null || (lines != null && lines.isEmpty()) ) {
      logger.debug(String.format("The lines to parse is null or empty"));
      return null;
    }

    logger.debug(String.format("The number of lines loaded are:%s",lines.size()));

    Map<String,List<String>> throwz = new LinkedHashMap<String, List<String>>();

    for (int i=0; i<lines.size(); i++) {

      String line = lines.get(i);

      String[] record = line.split("	");
      if (record != null && record.length == 2) {
        String playerName = record[0];
        String scoreThrow = record[1];
        throwz = this.addThrow(throwz, playerName, scoreThrow);

      } else {
        logger.error(String.format("The line number:%s is not parsable:%s",(i+1),line));
        return null;
      }
    }
    return throwz;
  }


  public Map<String,List<String>> addThrow(Map<String,List<String>> ts, String playerName, String score) {

    if (this.isPlayer(ts, playerName)) {
      List<String> scores = ts.get(playerName);
      scores.add(score);
    } else {
      List<String> scores = new ArrayList<String>();
      scores.add(score);
      ts.put(playerName, scores);
    }
    return ts;
  }

  /**
   * It verifies if exist already a player name in current map of throws
   * @param ts, throws in which is saving the players names and throws
   * @param playerName to veifies
   * @return boolean validation status.
   */
  public boolean isPlayer(Map<String,List<String>> ts, String playerName) {
    
    if (ts == null) {
      return Boolean.FALSE;
    }
    return ts.containsKey(playerName);
  }
}