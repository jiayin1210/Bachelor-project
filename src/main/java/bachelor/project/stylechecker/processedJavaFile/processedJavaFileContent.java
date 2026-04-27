package bachelor.project.stylechecker.processedJavaFile;

import bachelor.project.stylechecker.JavaFileparse.*;

public class processedJavaFileContent {

	public DetailedParseTreeNode root;
	public Content content;
	
	public processedJavaFileContent(JavaFileContent javafilecontent) {
		
		this.content=javafilecontent.content;
		
		ParseTreeConverter converter= new ParseTreeConverter(javafilecontent.ast.parser);
		root=converter.convert(javafilecontent.ast.tree);
		};
	

}
