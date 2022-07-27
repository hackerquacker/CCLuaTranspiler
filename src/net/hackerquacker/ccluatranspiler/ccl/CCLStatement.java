package net.hackerquacker.ccluatranspiler.ccl;

import net.hackerquacker.ccluatranspiler.AbstractSyntaxTree;
import net.hackerquacker.ccluatranspiler.enums.TokenType;
import net.hackerquacker.ccluatranspiler.obj.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * This class is the interface for all CCL Program Objects.
 */
public abstract class CCLStatement {

    private final Token startToken;   // the starting token for this statement
    private final CCLProgram state;     // the program state

    public CCLStatement(CCLProgram state, Token startToken){
        this.startToken = startToken;
        this.state = state;

        this.init();
        this.parse();
    }

    protected abstract void init();

    protected abstract void parse();

    /**
     * This method implements the String representation of this object. This will be printed in the debug to see
     * if this object is being read correctly.
     */
    public abstract String asString();

    /**
     * This method implements the CCL representation of this object. This will be printed to show the object is being
     * interpreted correctly.
     */
    public abstract String asCCL();

    /**
     * This method implements the LUA representation of this object. This will be the resulting transpiled code.
     */
    public abstract String asLua();

    /**
     * Returns the program state
     */
    protected final CCLProgram getState(){
        return this.state;
    }

    /**
     * Returns the current token
     */
    protected final Token getToken(){
        return this.startToken;
    }

    protected final AbstractSyntaxTree getAST(){
        return this.state.getAST();
    }

    @Override public String toString(){
        return this.asString();
    }


    public static final List<CCLStatement> parseStatements(CCLProgram program, boolean innerStatement){
        List<CCLStatement> statements = new ArrayList<>();
        Stack<Token> tokenStack = new Stack<>();

        while (program.getAST().hasNext()){
            Token token = program.getAST().next();
            if (innerStatement && token.equals(TokenType.OPEN_BLOCK))
                tokenStack.push(token);

            if (token.equals("func")){
                statements.add(new CCLFunction(program, token));
            }

            if (token.equals("var") || token.equals("const")){
                // TODO: implement Variable definition class
                statements.add(new CCLVariableAssign(program, token));
            }

            if (token.equals("class")){
                // TODO: implement classes
            }

            if (innerStatement && token.equals(TokenType.CLOSED_BLOCK))
                tokenStack.pop();

            if (innerStatement && tokenStack.empty())
                break;
        }

        return statements;
    }
}
