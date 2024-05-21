
import gen.*;
import nodes.*;
import org.antlr.v4.runtime.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
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
            typeChecker.visit(ast);

            CodeBuilderVisitor codeBuilderVisitor = new CodeBuilderVisitor();
            String finalCode = codeBuilderVisitor.visitStart(ast);
            String finalfinalCode = CodeFormatter.formatCode(finalCode);
            //System.out.println(finalfinalCode);

            String clipsDirPath = userDir + "/src/CLIPS";
            String filePath = clipsDirPath + "/Main.java";

            File clipsDir = new File(clipsDirPath);
            if (clipsDir.exists()) {
                deleteDirectory(clipsDir);
            }
            new File(clipsDirPath).mkdirs();

            FileWriter myWriter = new FileWriter(filePath);
            myWriter.write("package CLIPS;\n" + finalfinalCode);
            myWriter.close();

            ProcessBuilder compileProcessBuilder = new ProcessBuilder("javac", "-d", clipsDirPath, filePath);
            compileProcessBuilder.directory(new File(userDir));
            Process compileProcess = compileProcessBuilder.start();
            compileProcess.waitFor();
        } catch (Exception e){
            System.err.println(e.getMessage());
            //e.printStackTrace();
        }
    }

    private static void deleteDirectory(File directory) {
        File[] allContents = directory.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                if (file.isDirectory()) {
                    deleteDirectory(file);
                } else {
                    file.delete();
                }
            }
        }
        directory.delete();
    }
}
