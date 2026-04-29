package main;

import java.io.File;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import bachelor.project.file.ConfigParse.CheckStyleConfigParser;
import bachelor.project.file.ConfigParse.CheckStyleConfigParser.ConfigNode;
import bachelor.project.stylechecker.JavaFileparse.*;
import bachelor.project.stylechecker.processedJavaFile.*;

import bachelor.project.stylechecker.parse.*;
import bachelor.project.stylechecker.processedJavaFile.DetailedParseTreeNode;
import bachelor.project.stylechecker.processedJavaFile.ParseTreeConverter;


public class Test {
    public static void main(String[] args) throws Exception {

        
//    JavaFileContent  c=new JavaFileContent("/Users/jiayindu/Desktop/Projekt/Test.java");
//    processedJavaFileContent c1=new processedJavaFileContent(c);
//
//    System.out.print(c1.root);


    
    //try {
        File configfile = new File("/Users/jiayindu/Desktop/Projekt/TestConfig.xml");
        ConfigNode root = CheckStyleConfigParser.parse(configfile);
        CheckStyleConfigParser.printNodes(root, 0);
//    } catch (Exception e) {
//        e.printStackTrace();
//    }


}}