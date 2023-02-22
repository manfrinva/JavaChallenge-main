package jobsity.bowling.service.impl;


import java.util.stream.Collectors;
import java.util.List;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;
import java.util.stream.Stream;
import jobsity.bowling.service.FileService;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//This service is usded to charge the bowling game file
@Service
public class FileServiceImpl implements FileService {

  //This is used to log information in whole class.
  protected Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

  //{@inheritDoc}
  public List<String> load(String fileName) {
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			return stream.collect(Collectors.toList());
		} catch (IOException e) {
      this.logger.error(String.format("Error trying to load the file:%s \n %s",
        fileName,e.getMessage()));
			return null;
		}
  }
}