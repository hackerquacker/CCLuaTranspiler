package net.hackerquacker.ccluatranspiler.ccl;

import net.hackerquacker.ccluatranspiler.AbstractSyntaxTree;
import net.hackerquacker.ccluatranspiler.enums.TokenType;
import net.hackerquacker.ccluatranspiler.obj.Token;

import java.util.ArrayList;
import java.util.List;

public class CCLFunction extends CCLStatement{

    private String name;
    private List<CCLVariable> arguments;
    private List<CCLStatement> statements;

    private CCLClass parentClass = null;

    public CCLFunction(CCLProgram state, Token startToken) {
        super(state, startToken);
    }

    @Override
    protected void init() {
        this.arguments = new ArrayList<>();
        this.statements = new ArrayList<>();
    }

    @Override
    protected void parse() {
        if (!this.getToken().equals("func"))
            throw new IllegalStateException("Unexpected keyword " + this.getToken().getToken());

        this.name = this.getAST().next().getToken();

        if (!this.getAST().next().equals(TokenType.OPEN_BRACKET))
            throw new IllegalStateException("Expected ( after function name!");

        Token curToken = this.getAST().next();
        while (!curToken.equals(TokenType.CLOSED_BRACKET)){
            if (curToken.equals(TokenType.IDENTIFIER)) {
                this.arguments.add(new CCLVariable(this.parentClass, curToken.getToken()));
            }
            curToken = this.getAST().next();
        }

        curToken = this.getAST().next();
        if (curToken.equals(TokenType.OPEN_BLOCK)){
            // TODO: read all tokens as statements inside this function
        }
    }

    @Override
    public String asString() {
        String args = "";
        for (CCLVariable arg : this.arguments)
            args += arg + ", ";
        return "(func)" + this.name + "(" + args + ")";
    }

    @Override
    public String asCCL() {
        return null;
    }

    @Override
    public String asLua() {
        return null;
    }
}
