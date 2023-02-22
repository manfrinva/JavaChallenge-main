package jobsity.bowling.service.impl;

import java.util.ArrayList;
import jobsity.bowling.service.FrameService;
import jobsity.bowling.service.LineService;
import org.springframework.beans.factory.annotation.Autowired;
import jobsity.bowling.service.dto.FrameDTO;
import java.util.Map;
import jobsity.bowling.service.dto.LineDTO;
import java.util.List;
import org.springframework.stereotype.Service;

//It is the service implementation relate with lines
@Service
public class LineServiceImpl implements LineService {

  private FrameService frameService;

  @Autowired
  public LineServiceImpl(FrameService frameService) {
    this.frameService = frameService;
  }
  
  @Override
  public List<LineDTO> parse(Map<String,List<String>> ts) {

    if (ts == null || (ts != null && ts.isEmpty())) {
      return null;
    }

    List<LineDTO> lines = new ArrayList<LineDTO>();
    for (Map.Entry<String, List<String>> entry : ts.entrySet()) {
        String playerName = entry.getKey();
        List<String> scores = entry.getValue();
        LineDTO line = new LineDTO(playerName);
        List<FrameDTO> frames = this.frameService.createFrames(scores);
        line.setFrames(frames);
        lines.add(line);
    }
    return lines;
  }
}