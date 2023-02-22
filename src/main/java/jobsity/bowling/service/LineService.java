package jobsity.bowling.service;

import java.util.Map;
import jobsity.bowling.service.dto.LineDTO;
import java.util.List;

public interface LineService {

  /**
  * It parses the throws grouped by player
  * @param throws, it is a map where key is the player name and list is the socores releted to it.
  * @return a list of lines
  */
  public List<LineDTO> parse(Map<String,List<String>> trows);

}