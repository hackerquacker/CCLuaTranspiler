package net.hackerquacker.ccluatranspiler.ccl;

import net.hackerquacker.ccluatranspiler.obj.Token;

public class CCLFunctionCall extends CCLStatement{



    public CCLFunctionCall(CCLProgram state, Token startToken) {
        super(state, startToken);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void parse() {

    }

    @Override
    public String asString() {
        return null;
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
