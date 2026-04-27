package bachelor.project.stylechecker.JavaFileparse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class JavaFileReader {

    public static String readJavaFileAsString(String filePath) throws IOException {
        return Files.readString(Path.of(filePath));
    }

    public static List<String> readJavaFileLineByLine(String filePath) throws IOException {
        return Files.readAllLines(Path.of(filePath));
    }
}