package bachelor.project.stylechecker.JavaFileparse;

import java.io.IOException;
import java.util.List;

public class Content {
	
	public String JavaFileAsString;
	public  List<String> JavaFileLines;
	
	Content(String filePath) throws IOException{

		this.JavaFileAsString=JavaFileReader.readJavaFileAsString(filePath);
		this.JavaFileLines=JavaFileReader.readJavaFileLineByLine(filePath);
	}

}
