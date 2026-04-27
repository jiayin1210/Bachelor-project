package bachelor.project.stylechecker.processedJavaFile;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import bachelor.project.stylechecker.parse.*;

public class ParseTreeConverter {

    private final Parser parser;

    public ParseTreeConverter(Parser parser) {
        this.parser = parser;
    }

    public DetailedParseTreeNode convert(ParseTree tree) {
        return convertNode(tree);
    }

    private DetailedParseTreeNode convertNode(ParseTree tree) {

        if (tree instanceof ParserRuleContext context) {
            DetailedParseTreeNode node = new DetailedParseTreeNode("RULE");

            int ruleIndex = context.getRuleIndex();
            String ruleName = parser.getRuleNames()[ruleIndex];

            node.setRuleName(ruleName);
            node.setText(context.getText());

            Token start = context.getStart();
            Token stop = context.getStop();

            if (start != null) {
                node.setStartLine(start.getLine());
                node.setStartColumn(start.getCharPositionInLine());
            }

            if (stop != null) {
                node.setStopLine(stop.getLine());
                node.setStopColumn(stop.getCharPositionInLine());
            }

            for (int i = 0; i < tree.getChildCount(); i++) {
                node.addChild(convertNode(tree.getChild(i)));
            }

            return node;
        }

        if (tree instanceof TerminalNode terminalNode) {
            Token token = terminalNode.getSymbol();

            DetailedParseTreeNode node = new DetailedParseTreeNode("TOKEN");

            node.setText(token.getText());
            node.setTokenType(token.getType());
            node.setTokenName(parser.getVocabulary().getSymbolicName(token.getType()));
            node.setStartLine(token.getLine());
            node.setStartColumn(token.getCharPositionInLine());
            node.setStopLine(token.getLine());
            node.setStopColumn(
                token.getCharPositionInLine() + token.getText().length()
            );

            return node;
        }

        if (tree instanceof ErrorNode errorNode) {
            Token token = errorNode.getSymbol();

            DetailedParseTreeNode node = new DetailedParseTreeNode("ERROR");

            node.setText(token.getText());
            node.setTokenType(token.getType());
            node.setTokenName(parser.getVocabulary().getSymbolicName(token.getType()));
            node.setStartLine(token.getLine());
            node.setStartColumn(token.getCharPositionInLine());

            return node;
        }

        DetailedParseTreeNode node = new DetailedParseTreeNode("UNKNOWN");
        node.setText(tree.getText());
        return node;
    }
}