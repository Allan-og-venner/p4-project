import p4-project.gen.*;

class Main {
    public static void main(String[] args) {
        ExprLexer lexer = new ExprLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JavaParser parser = new JavaParser(tokens);
        JavaParser.CompilationUnitContext tree = parser.compilationUnit(); // parse a compilationUnit

        MyListener extractor = new MyListener(parser);
        ParseTreeWalker.DEFAULT.walk(extractor, tree); // initiate walk of tree with listener in use of default walker
    }


}