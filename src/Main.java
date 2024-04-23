import gen.*;
import nodes.*;
import org.antlr.v4.runtime.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
            TypeCheckerVisitor typeChecker = new TypeCheckerVisitor();
            System.out.println(typeChecker.visit(ast));

            //BuildASTVisitor.printVisitedNodes();

        } catch (Exception e){
            System.err.print(e.getMessage());
            //e.printStackTrace();
        }

    }

}