package net.hackerquacker.ccluatranspiler.ccl;

import net.hackerquacker.ccluatranspiler.obj.Token;

public class CCLVariableAssign extends CCLStatement{

    private CCLVariable variable;

    public CCLVariableAssign(CCLProgram state, Token startToken) {
        super(state, startToken);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void parse() {
        if (!(this.getToken().equals("var") || this.getToken().equals("const")))
            throw new IllegalStateException("Unexpected keyword " + this.getToken().getToken());

        String name = this.getAST().next().getToken();
        this.variable = new CCLVariable(null, name);

        if (this.getToken().equals("const"))
            this.variable.setConstant(true);

        if (!this.getAST().next().getToken().equals("="))
            throw new IllegalStateException("Expected = after identifier!");

        // TODO: parse assign statement
        while(!this.getAST().next().equals(";")) {}
    }

    @Override
    public String asString() {
        return "Variable{" + this.getVariable().getName() + ", isConst=" + this.getVariable().isConstant() + "}";
    }

    @Override
    public String asCCL() {
        return null;
    }

    @Override
    public String asLua() {
        return null;
    }

    public CCLVariable getVariable(){
        return this.variable;
    }
}
