package bachelor.project.stylechecker.JavaFileparse;

import java.io.IOException;


public class JavaFileContent {

	
	public Content content;
	public AST ast;
	
	public JavaFileContent(String filePath) throws IOException{
		content= new Content(filePath);
		ast= new AST(content.JavaFileAsString);
	};
}
