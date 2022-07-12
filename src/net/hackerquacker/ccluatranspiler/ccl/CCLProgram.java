package net.hackerquacker.ccluatranspiler.ccl;

import net.hackerquacker.ccluatranspiler.AbstractSyntaxTree;

import java.util.ArrayList;
import java.util.List;

public class CCLProgram {

    public static short INDENT_LEVEL = 0;

    private final AbstractSyntaxTree ast;

    private final List<CCLStatement> statements;

    public CCLProgram(AbstractSyntaxTree ast){
        this.ast = ast;
        this.statements = new ArrayList<>();

        this.parse();
    }

    private void parse(){

    }

    public final AbstractSyntaxTree getAST(){
        return this.ast;
    }

    public final List<CCLStatement> getStatements(){
        return this.statements;
    }
}
