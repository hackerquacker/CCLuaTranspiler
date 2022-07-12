package net.hackerquacker.ccluatranspiler;

import net.hackerquacker.ccluatranspiler.enums.TokenType;
import net.hackerquacker.ccluatranspiler.obj.LexerToken;
import net.hackerquacker.ccluatranspiler.obj.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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

       this.build();
   }

   private void build(){
       Stack<Token> tokenStack = new Stack<>();

       Token lastToken = null;

       for (Token t : this.tokens){
           if (t.getType() == TokenType.OPEN_BRACKET)
               tokenStack.push(lastToken);
           else if (t.getType() == TokenType.CLOSED_BRACKET || t.getType() == TokenType.CLOSED_BLOCK){
               if (tokenStack.empty())
                   throw new IllegalStateException("Mismatched open and closing brackets");
               tokenStack.pop();
           }

           if (!tokenStack.empty())
               t.setParent(tokenStack.peek());

           if (t.getType() == TokenType.OPEN_BLOCK)
               tokenStack.push(t);

           if (lastToken != null)
               lastToken.setNext(t);

           lastToken = t;
       }

       if (!tokenStack.empty()){
           //if (lastToken != null)
               throw new IllegalStateException("Mismatched open and closing brackets!");
       }

       if (lastToken != null)
           lastToken.setNext(null);
   }

   public List<Token> getTokens(){
       return this.tokens;
   }

   public Token next(){
       if (this.token >= this.tokens.size())
           throw new ArrayIndexOutOfBoundsException("End of token list");

       return this.tokens.get(this.token++);
   }

   public void reset(){
       this.token = 0;
   }

   public boolean hasNext(){
       return (this.token < this.tokens.size());
   }

   public void back(){
       if (this.token > 0)
           this.token--;
   }
}
