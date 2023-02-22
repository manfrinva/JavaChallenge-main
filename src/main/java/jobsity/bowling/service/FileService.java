package jobsity.bowling.service;


import java.util.List;

/**
 * It provides the methods to load the bowling file with results.
 */
public interface FileService {

    /**
    * It laod a file and generate the stream with each line loaded.
    * @@param fileName to load
    * @@return List with lines in the file
    */
    public List<String> load(String fileName);
}