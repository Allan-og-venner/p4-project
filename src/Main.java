import gen.*;
import nodes.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        try {
            String userDir = System.getProperty("user.dir");
            CharStream in = CharStreams.fromFileName(userDir+"/src/code.txt");
            ExprLexer lexer = new ExprLexer(in);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            ExprParser parser = new ExprParser(tokens);
            ExprParser.ProgContext tree = parser.prog();
            BlockNode ast = new BuildASTVisitor().visitProg(tree);
            EvaluateExpressionVisitor visitor = new EvaluateExpressionVisitor();
            System.out.println("AST output: " + visitor.visit(ast));
        }catch (Exception e){
            System.out.print(e.getMessage());
        }

    }

}