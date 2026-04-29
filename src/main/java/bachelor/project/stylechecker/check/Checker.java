package bachelor.project.stylechecker.check;

import  bachelor.project.stylechecker.processedJavaFile.*;

public class Checker {
	
	
	
	ASTChecker astchecker;
	
	//processedJavaFileContent filecontent; als variable danach übergegebn
	void check(processedJavaFileContent filecontent) {
		
		ASTCheck(filecontent.root);
		
	};
	
	void ASTCheck(DetailedParseTreeNode tree) {
		astchecker.walk(tree);
		
		
	};
;
}
