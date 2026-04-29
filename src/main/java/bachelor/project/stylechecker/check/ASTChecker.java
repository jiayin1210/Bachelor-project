package bachelor.project.stylechecker.check;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import bachelor.project.stylechecker.processedJavaFile.*;


public class ASTChecker {
	
	List<Check> checks;
	Map<Integer,List<Check>> checksByNodeType;
	
	ASTChecker(){};
	
	
	public void walk(DetailedParseTreeNode ast) {
	    if (ast == null) {
	        return;
	    }

	    // aktuelle Node prüfen
	    notifyChecks(ast);

	    // Kinder durchlaufen
	    for (DetailedParseTreeNode child : ast.getChildren()) {
	        walk(child);
	    }
	}

   private void notifyChecks(DetailedParseTreeNode ast) {
    Integer NodeType = ast.getNodeType();

    if (NodeType == null) {
        return;
    }
    
// Ein Dictionary zu implementieren , dabei Checks mit dem Typ verbunden
    List<Check> checks = checksByNodeType.get(NodeType);

    if (checks != null) {
        for (Check check : checks) {
            check.visitNode(ast);
        }
    }
}

}
