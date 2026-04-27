package main;

import java.io.File;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import bachelor.project.stylechecker.JavaFileparse.*;
import bachelor.project.stylechecker.processedJavaFile.*;

import bachelor.project.stylechecker.parse.*;
import bachelor.project.stylechecker.processedJavaFile.DetailedParseTreeNode;
import bachelor.project.stylechecker.processedJavaFile.ParseTreeConverter;


public class Parser {
    public static void main(String[] args) throws Exception {

        
JavaFileContent  c=new JavaFileContent("/Users/jiayindu/Desktop/Projekt/Test.java");
processedJavaFileContent c1=new processedJavaFileContent(c);

System.out.print(c1.root);


 

        
        

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