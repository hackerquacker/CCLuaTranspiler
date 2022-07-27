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

    private String returnType;

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
            String name = null;
            String type = null;
            if (curToken.equals(TokenType.IDENTIFIER)) {
                if (curToken.next().equals(TokenType.IDENTIFIER)){
                    type = curToken.getToken();
                    curToken = this.getAST().next();
                    name = curToken.getToken();
                }else name = curToken.getToken();
            }
            curToken = this.getAST().next();

            if (curToken.equals(TokenType.TYPE_DEF)){
                if (type == null) {
                    curToken = this.getAST().next();
                    // curToken = type of variable
                    System.err.println("Variable typing isnt supported in this version... ignoring");
                    type = curToken.getToken();

                    curToken = this.getAST().next();
                }else throw new IllegalStateException("Doubly defined type for variable " + name);
            }

            if (name != null){
                this.arguments.add(new CCLVariable(this.parentClass, name, type));
            }
        }


        // Check if this function has a return type (Currently Unsupported)
        if (curToken.next().equals(TokenType.TYPE_DEF)){
            curToken = this.getAST().next();
            curToken = this.getAST().next();

            // curToken = type of function
            this.returnType = curToken.getToken();

            System.err.println("Function return type isn't supported in this version... ignoring");
        }

        //curToken = this.getAST().next();
        //if (curToken.equals(TokenType.OPEN_BLOCK)){
            // TODO: read all tokens as statements inside this function
        //}

        this.statements = CCLStatement.parseStatements(this.getState(), true);

    }

    @Override
    public String asString() {
        String args = "";
        for (CCLVariable arg : this.arguments)
            args += arg + ", ";
        return "("+(this.returnType != null ? this.returnType : "func")+")" + this.name + "(" + args + ")";
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
