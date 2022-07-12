package net.hackerquacker.ccluatranspiler.ccl;

import net.hackerquacker.ccluatranspiler.AbstractSyntaxTree;
import net.hackerquacker.ccluatranspiler.obj.Token;

/**
 * This class is the interface for all CCL Program Objects.
 */
public abstract class CCLStatement {

    private final Token startToken;   // the starting token for this statement
    private final CCLProgram state;     // the program state

    public CCLStatement(CCLProgram state, Token startToken){
        this.startToken = startToken;
        this.state = state;

        this.parse();
    }

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
}
