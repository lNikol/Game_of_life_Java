package FileService;

import World.World;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileService {
    String fileName;
    public FileService(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
    }

    public void writeToLog(String info, short width, short height) throws IOException {
        try (FileWriter fw = new FileWriter(fileName, false)){
            fw.write("World: height: "+height+", width: "+width + "\n" + info);
            //fw.close();
        } catch (IOException e){
            System.out.println(e);
        }
    }
}
