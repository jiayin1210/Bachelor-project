package bachelor.project.stylechecker.processedJavaFile;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.*;

import java.util.ArrayList;
import java.util.List;

public class DetailedParseTreeNode {

    private String nodeType;
    private String text;
    private String ruleName;
    private Integer tokenType;
    private String tokenName;
    private int startLine;
    private int startColumn;
    private int stopLine;
    private int stopColumn;

    private List<DetailedParseTreeNode> children = new ArrayList<>();

    public DetailedParseTreeNode(String nodeType) {
        this.setNodeType(nodeType);
    }

    public void addChild(DetailedParseTreeNode child) {
        children.add(child);
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

	public void setText(String text2) {
		// TODO Auto-generated method stub
		
	}

	public int getStartColumn() {
		return startColumn;
	}

	public void setStartColumn(int startColumn) {
		this.startColumn = startColumn;
	}

	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public int getStopColumn() {
		return stopColumn;
	}

	public void setStopColumn(int stopColumn) {
		this.stopColumn = stopColumn;
	}

	public int getStartLine() {
		return startLine;
	}

	public void setStartLine(int startLine) {
		this.startLine = startLine;
	}

	public Integer getTokenType() {
		return tokenType;
	}

	public void setTokenType(Integer tokenType) {
		this.tokenType = tokenType;
	}

	public String getTokenName() {
		return tokenName;
	}

	public void setTokenName(String tokenName) {
		this.tokenName = tokenName;
	}

	public int getStopLine() {
		return stopLine;
	}

	public void setStopLine(int stopLine) {
		this.stopLine = stopLine;
	}

    // Getter/Setter weggelassen für Kürze
	@Override
	public String toString() {
	    return toString(0);
	}

	private String toString(int indent) {
	    StringBuilder sb = new StringBuilder();

	    // Einrückung
	    sb.append("  ".repeat(indent));

	    // Basis-Info
	    sb.append(nodeType);

	    if (ruleName != null) {
	        sb.append(" [rule=").append(ruleName).append("]");
	    }

	    if (tokenName != null) {
	        sb.append(" [token=").append(tokenName).append("]");
	    }

	    if (text != null) {
	        sb.append(" [text=").append(text).append("]");
	    }

	    sb.append(" (")
	      .append(startLine).append(":").append(startColumn)
	      .append(" - ")
	      .append(stopLine).append(":").append(stopColumn)
	      .append(")");

	    sb.append("\n");

	    // Kinder rekursiv ausgeben
	    for (DetailedParseTreeNode child : children) {
	        sb.append(child.toString(indent + 1));
	    }

	    return sb.toString();
	}
}