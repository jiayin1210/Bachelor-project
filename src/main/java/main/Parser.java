package main;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import bachelor.project.stylechecker.parse.*;
import bachelor.project.stylechecker.processedJavaFile.DetailedParseTreeNode;
import bachelor.project.stylechecker.processedJavaFile.ParseTreeConverter;


public class Parser {
    public static void main(String[] args) throws Exception {
        String source =
                "public class Hello {\n" +
                "    public static void main(String[] args) {\n" +
                "        System.out.println(\"Hallo Welt\");\n" +
                "    }\n" +
                "}";

        // 1. Eingabe in einen CharStream laden
        CharStream input = CharStreams.fromString(source);

        // 2. Lexer erzeugen
        JavaLexer lexer = new JavaLexer(input);

        // 3. Tokens erzeugen
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // 4. Parser erzeugen
        JavaParser parser = new JavaParser(tokens);

        // Optional: Fehler direkt auf stderr ausgeben
        parser.removeErrorListeners();
        parser.addErrorListener(new DiagnosticErrorListener());

        // 5. Startregel aufrufen
        // Bei der Java-Grammatik ist häufig "compilationUnit" die Startregel
        ParseTree tree = parser.compilationUnit();
        


        ParseTreeConverter converter = new ParseTreeConverter(parser);
        DetailedParseTreeNode detailedTree = converter.convert(tree);
        System.out.print(detailedTree);
        
        

        // 6. Baum als Klammerstruktur ausgeben


    }
    public static void printAllNodes(ParseTree node, int depth) {
        String indent = "  ".repeat(depth);

        if (node instanceof ParserRuleContext ctx) {
            Token start = ctx.getStart();
            Token stop = ctx.getStop();

            System.out.println(indent
                + "RULE: " + ctx.getClass().getSimpleName()
                + " | text=" + ctx.getText()
                + " | startLine=" + start.getLine()
                + " | startCol=" + start.getCharPositionInLine()
                + " | endLine=" + stop.getLine()
                + " | endCol=" + stop.getCharPositionInLine()
            );
        }

        else if (node instanceof TerminalNode terminal) {
            Token token = terminal.getSymbol();

            System.out.println(indent
                + "TOKEN: " + token.getText()
                + " | type=" + token.getType()
                + " | line=" + token.getLine()
                + " | col=" + token.getCharPositionInLine()
            );
        }

        for (int i = 0; i < node.getChildCount(); i++) {
            printAllNodes(node.getChild(i), depth + 1);
        }
    }
}