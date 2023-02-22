package jobsity.bowling.service;

import java.util.List;
import java.util.Map;


public interface ThrowService {

  /**
  * It parses a list of lines, grouping the lines and generating a map in which
  * each register is a player name and related scores.
  * @param lines to parese
  * @return map grouped, map(key, value) -> map(playerName,list of scores)
  */
  public Map<String,List<String>> parse(List<String> lines);

  /**
  * It add a new throw to specific player
  * @param ts is the current grouped map of throws
  * @param playerName, is the player to add the new throw
  * @param score related to the throw
  * @return map grouped with the new throw, map(key, value) -> map(playerName,list of scores)
  */
  public Map<String,List<String>> addThrow(Map<String,List<String>> ts, String playerName, String score);
}