package net.hackerquacker.ccluatranspiler.ccl;

import net.hackerquacker.ccluatranspiler.AbstractSyntaxTree;

import java.util.ArrayList;
import java.util.List;

public class CCLProgram {

    public static short INDENT_LEVEL = 0;

    private final AbstractSyntaxTree ast;

    private List<CCLStatement> statements;

    public CCLProgram(AbstractSyntaxTree ast){
        this.ast = ast;
        this.statements = new ArrayList<>();

        this.parse();
    }

    private void parse(){
        this.statements = CCLStatement.parseStatements(this, false);
    }

    public final AbstractSyntaxTree getAST(){
        return this.ast;
    }

    public final List<CCLStatement> getStatements(){
        return this.statements;
    }


    @Override public String toString(){
        String str = "";
        for (CCLStatement statement : this.statements){
            str += statement + "\n";
        }
        return str;
    }
}
