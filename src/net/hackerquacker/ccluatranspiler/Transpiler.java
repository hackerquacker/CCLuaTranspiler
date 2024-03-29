package net.hackerquacker.ccluatranspiler;

import net.hackerquacker.ccluatranspiler.ccl.CCLProgram;
import net.hackerquacker.ccluatranspiler.obj.LexerToken;
import net.hackerquacker.ccluatranspiler.obj.Token;

public class Transpiler {

    public Transpiler(){

        // Test code

        String testCode = "/*This is a test\nOf a multiline comment*/ \nconst constTest = \"Hello World!\";\nfunc main(args: String, int args2)->String {\n\tfunc test2(){print(\"Hello World!\");}\n\tvar test = \"Hello World!\";\n\tif(test == \"Hello World\" && test != 3){\n\t\tprint(test);\n\t}\n}";

        Lexer lexer = new Lexer(testCode);
        AbstractSyntaxTree ast = new AbstractSyntaxTree(lexer);

        System.out.println(testCode);

        for(Token t : ast.getTokens()){
            System.out.println(t);
        }

        CCLProgram program = new CCLProgram(ast);

        System.out.println(program);
    }

    /** Main Entrypoint for the CCLuaTranspiler Program */
    public static void main(String args[]){
        Transpiler transpiler = new Transpiler();
    }
}
