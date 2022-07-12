package net.hackerquacker.ccluatranspiler;

import net.hackerquacker.ccluatranspiler.obj.LexerToken;
import net.hackerquacker.ccluatranspiler.obj.Token;

import java.util.ArrayList;
import java.util.List;

public class AbstractSyntaxTree {

   private final List<Token> tokens = new ArrayList<>();
   private int token = 0;

   public AbstractSyntaxTree(Lexer lexer){
       for (LexerToken lt : lexer.getTokens()) {
           Token token = lt.getToken();

           if (!tokens.isEmpty())
               this.tokens.get(this.tokens.size() - 1).setNext(token);

           this.tokens.add(token);
       }
   }

   public List<Token> getTokens(){
       return this.tokens;
   }
}
