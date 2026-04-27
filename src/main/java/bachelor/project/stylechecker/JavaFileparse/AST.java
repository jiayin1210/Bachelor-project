package bachelor.project.stylechecker.JavaFileparse;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import bachelor.project.stylechecker.parse.JavaLexer;
import bachelor.project.stylechecker.parse.JavaParser;

public class AST {
	
	public ParseTree tree;
	public JavaParser parser ;
	
	public AST(String source) {
		
        CharStream input = CharStreams.fromString(source);

        // 2. Lexer erzeugen
        JavaLexer lexer = new JavaLexer(input);

        // 3. Tokens erzeugen
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // 4. Parser erzeugen
        this.parser = new JavaParser(tokens);
        
        this.tree = parser.compilationUnit();
	}
	

}
