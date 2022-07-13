package net.hackerquacker.ccluatranspiler.ccl;

import net.hackerquacker.ccluatranspiler.AbstractSyntaxTree;
import net.hackerquacker.ccluatranspiler.obj.Token;

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
        while (this.ast.hasNext()){
            Token token = this.ast.next();

            if (token.equals("func")){
                this.statements.add(new CCLFunction(this, token));
            }

            if (token.equals("var") || token.equals("const")){
                // TODO: implement Variable definition class
            }

            if (token.equals("class")){
                // TODO: implement classes
            }
        }
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
