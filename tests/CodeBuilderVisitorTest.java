import codegenExample.Card;
import junit.framework.TestCase;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.Pair;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.junit.Test;
import org.mockito.Mockito;
import gen.*;
import nodes.*;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;


import java.lang.annotation.Documented;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
public class CodeBuilderVisitorTest {
    @Test
    public void testTestVisitDefineNodeArrayGlobal() {
        //ArrayList<String> test = new ArrayList<>(){{add("Hej");add("Farvel")}}
        DefineNode defineNode = Mockito.spy(DefineNode.class);
        IdentifierNode identifierNode = Mockito.mock(IdentifierNode.class);
        ExpressionNode expressionNode = Mockito.mock(ExpressionNode.class);
        TypeNode typeNode = Mockito.mock(TypeNode.class);

        //Set the node
        when(defineNode.isArray()).thenReturn(true);
        when(identifierNode.getText()).thenReturn("test");
        when(defineNode.getID()).thenReturn(identifierNode);
        when(defineNode.getValue()).thenReturn(expressionNode);
        //doReturn(typeNode).when(defineNode).getType();
        when(defineNode.getType()).thenReturn(typeNode);
        //when(defineNode.getValue()).th

        CodeBuilderVisitor visitor = Mockito.spy(CodeBuilderVisitor.class);
        doReturn("String").when(visitor).visit(typeNode);
        doReturn("new ArrayList<>(){{add(\"Hej\");add(\"Farvel\")}}").when(visitor).visit(expressionNode);

        String result = visitor.visit(defineNode);

        assertEquals("test = new ArrayList<>(){{add(\"Hej\");add(\"Farvel\")}};", result);
        assertEquals("static ArrayList<String> test;", visitor.getVariables().get(1));
    }
    @Test
    public void testTestVisitDefineNodeVarGlobal() {
        //String test = "Hej";
        DefineNode defineNode = Mockito.spy(DefineNode.class);
        IdentifierNode identifierNode = Mockito.mock(IdentifierNode.class);
        ExpressionNode expressionNode = Mockito.mock(ExpressionNode.class);
        TypeNode typeNode = Mockito.mock(TypeNode.class);

        //Set the node
        when(identifierNode.getText()).thenReturn("test");
        when(defineNode.getID()).thenReturn(identifierNode);
        when(defineNode.getValue()).thenReturn(expressionNode);
        //doReturn(typeNode).when(defineNode).getType();
        when(defineNode.getType()).thenReturn(typeNode);
        //when(defineNode.getValue()).th

        CodeBuilderVisitor visitor = Mockito.spy(CodeBuilderVisitor.class);
        doReturn("String").when(visitor).visit(typeNode);
        doReturn("\"Hej\"").when(visitor).visit(expressionNode);

        String result = visitor.visit(defineNode);

        assertEquals("test = \"Hej\";", result);
        assertEquals("static String test;", visitor.getVariables().get(1));
    }
    @Test
    public void testTestVisitDefineNodeGlobalNoValue() {
        //String test;
        DefineNode defineNode = Mockito.spy(DefineNode.class);
        IdentifierNode identifierNode = Mockito.mock(IdentifierNode.class);
        TypeNode typeNode = Mockito.mock(TypeNode.class);

        //Set the node
        when(identifierNode.getText()).thenReturn("test");
        when(defineNode.getID()).thenReturn(identifierNode);
        //doReturn(typeNode).when(defineNode).getType();
        when(defineNode.getType()).thenReturn(typeNode);
        //when(defineNode.getValue()).th

        CodeBuilderVisitor visitor = Mockito.spy(CodeBuilderVisitor.class);
        doReturn("String").when(visitor).visit(typeNode);

        String result = visitor.visit(defineNode);

        assertEquals("",result);
        assertEquals("static String test;", visitor.getVariables().get(1));
    }
    @Test
    public void testTestVisitDefineNodeArray() {
        //ArrayList<String> test = new ArrayList<>(){{add("Hej");add("Farvel")}}
        DefineNode defineNode = Mockito.spy(DefineNode.class);
        ModifierNode modifierNode = Mockito.mock(ModifierNode.class);
        IdentifierNode identifierNode = Mockito.mock(IdentifierNode.class);
        ExpressionNode expressionNode = Mockito.mock(ExpressionNode.class);
        TypeNode typeNode = Mockito.mock(TypeNode.class);

        //Set the node
        when(defineNode.isArray()).thenReturn(true);
        when(identifierNode.getText()).thenReturn("test");
        when(defineNode.getID()).thenReturn(identifierNode);
        when(defineNode.getValue()).thenReturn(expressionNode);
        when(typeNode.getTypeName()).thenReturn("string");
        when(defineNode.getType()).thenReturn(typeNode);
        when(defineNode.getModi()).thenReturn(modifierNode);

        CodeBuilderVisitor visitor = Mockito.spy(CodeBuilderVisitor.class);
        visitor.setScopeCount(1);

        doReturn("String").when(visitor).visit(typeNode);
        doReturn("").when(visitor).visit(modifierNode);
        doReturn("new ArrayList<>(){{add(\"Hej\");add(\"Farvel\")}}").when(visitor).visit(expressionNode);

        String result = visitor.visit(defineNode);

        assertEquals("ArrayList<String> test = new ArrayList<>(){{add(\"Hej\");add(\"Farvel\")}};", result);
    }
    @Test
    public void testTestVisitDefineNodeVar() {
        //String test = "Hej";
        DefineNode defineNode = Mockito.spy(DefineNode.class);
        ModifierNode modifierNode = Mockito.mock(ModifierNode.class);
        IdentifierNode identifierNode = Mockito.mock(IdentifierNode.class);
        ExpressionNode expressionNode = Mockito.mock(ExpressionNode.class);
        TypeNode typeNode = Mockito.mock(TypeNode.class);

        //Set the node
        when(defineNode.isArray()).thenReturn(false);
        when(identifierNode.getText()).thenReturn("test");
        when(defineNode.getID()).thenReturn(identifierNode);
        when(defineNode.getValue()).thenReturn(expressionNode);
        when(typeNode.getTypeName()).thenReturn("string");
        when(defineNode.getType()).thenReturn(typeNode);
        when(defineNode.getModi()).thenReturn(modifierNode);

        CodeBuilderVisitor visitor = Mockito.spy(CodeBuilderVisitor.class);
        visitor.setScopeCount(1);
        doReturn("String").when(visitor).visit(typeNode);
        doReturn("static ").when(visitor).visit(modifierNode);
        doReturn("\"Hej\"").when(visitor).visit(expressionNode);

        String result = visitor.visit(defineNode);

        assertEquals("static String test = \"Hej\";", result);
    }
    @Test
    public void testTestVisitDefineNodeVarNoValueString() {
        //String test = "Hej";
        DefineNode defineNode = Mockito.spy(DefineNode.class);
        ModifierNode modifierNode = Mockito.mock(ModifierNode.class);
        IdentifierNode identifierNode = Mockito.mock(IdentifierNode.class);
        TypeNode typeNode = Mockito.mock(TypeNode.class);

        //Set the node

        when(defineNode.isArray()).thenReturn(false);
        when(identifierNode.getText()).thenReturn("test");
        when(defineNode.getID()).thenReturn(identifierNode);
        when(typeNode.getTypeName()).thenReturn("string");
        when(defineNode.getType()).thenReturn(typeNode);
        when(defineNode.getModi()).thenReturn(modifierNode);
        //doReturn(typeNode).when(defineNode).getType();

        when(defineNode.getType()).thenReturn(typeNode);
        //when(defineNode.getValue()).th

        CodeBuilderVisitor visitor = Mockito.spy(CodeBuilderVisitor.class);
        visitor.setScopeCount(1);
        doReturn("String").when(visitor).visit(typeNode);
        doReturn("static ").when(visitor).visit(modifierNode);

        String result = visitor.visit(defineNode);

        assertEquals("static String test = \"\";", result);
    }
    @Test
    public void testTestVisitDefineNodeVarNoValueChar() {
        //String test = "Hej";
        DefineNode defineNode = Mockito.spy(DefineNode.class);
        ModifierNode modifierNode = Mockito.mock(ModifierNode.class);
        IdentifierNode identifierNode = Mockito.mock(IdentifierNode.class);
        TypeNode typeNode = Mockito.mock(TypeNode.class);

        //Set the node

        when(defineNode.isArray()).thenReturn(false);
        when(identifierNode.getText()).thenReturn("test");
        when(defineNode.getID()).thenReturn(identifierNode);
        when(typeNode.getTypeName()).thenReturn("char");
        when(defineNode.getType()).thenReturn(typeNode);
        when(defineNode.getModi()).thenReturn(modifierNode);
        //doReturn(typeNode).when(defineNode).getType();

        when(defineNode.getType()).thenReturn(typeNode);
        //when(defineNode.getValue()).th

        CodeBuilderVisitor visitor = Mockito.spy(CodeBuilderVisitor.class);
        visitor.setScopeCount(1);
        doReturn("char").when(visitor).visit(typeNode);
        doReturn("static ").when(visitor).visit(modifierNode);

        String result = visitor.visit(defineNode);

        assertEquals("static char test = '\\0';", result);
    }
    @Test
    public void testTestVisitDefineNodeVarNoValueInt() {
        //String test = "Hej";
        DefineNode defineNode = Mockito.spy(DefineNode.class);
        ModifierNode modifierNode = Mockito.mock(ModifierNode.class);
        IdentifierNode identifierNode = Mockito.mock(IdentifierNode.class);
        TypeNode typeNode = Mockito.mock(TypeNode.class);

        //Set the node
        when(defineNode.isArray()).thenReturn(false);
        when(identifierNode.getText()).thenReturn("test");
        when(defineNode.getID()).thenReturn(identifierNode);
        when(typeNode.getTypeName()).thenReturn("int");
        when(defineNode.getType()).thenReturn(typeNode);
        when(defineNode.getModi()).thenReturn(modifierNode);
        //doReturn(typeNode).when(defineNode).getType();

        when(defineNode.getType()).thenReturn(typeNode);
        //when(defineNode.getValue()).th

        CodeBuilderVisitor visitor = Mockito.spy(CodeBuilderVisitor.class);
        visitor.setScopeCount(1);
        doReturn("int").when(visitor).visit(typeNode);
        doReturn("static ").when(visitor).visit(modifierNode);

        String result = visitor.visit(defineNode);

        assertEquals("static int test = 0;", result);
    }
    @Test
    public void testTestVisitDefineNodeVarNoValueClass() {
        //String test = "Hej";
        DefineNode defineNode = Mockito.spy(DefineNode.class);
        ModifierNode modifierNode = Mockito.mock(ModifierNode.class);
        IdentifierNode identifierNode = Mockito.mock(IdentifierNode.class);
        TypeNode typeNode = Mockito.mock(TypeNode.class);
        //new Pair<String, String> testPair = new Pair<>()M

        //Set the node
        when(defineNode.isArray()).thenReturn(false);
        when(identifierNode.getText()).thenReturn("test");
        when(defineNode.getID()).thenReturn(identifierNode);
        when(typeNode.getTypeName()).thenReturn("Card");
        when(defineNode.getType()).thenReturn(typeNode);
        when(defineNode.getModi()).thenReturn(modifierNode);

        when(defineNode.getType()).thenReturn(typeNode);

        CodeBuilderVisitor visitor = Mockito.spy(CodeBuilderVisitor.class);

        visitor.getClassFields().put("Card", new ArrayList<>());
        visitor.setScopeCount(1);
        visitor.setCurrentClass("Card");
        doReturn("Card").when(visitor).visit(typeNode);
        doReturn("static ").when(visitor).visit(modifierNode);

        String result = visitor.visit(defineNode);

        assertEquals("static Card test = null;", result);
        assertEquals(new ArrayList<Pair<String, String>>(){{add(new Pair<>("Card", "test"));}}, visitor.getClassFields().get("Card"));
    }
    @Test
    public void testTestVisitCardTypeNewClass() {
        CardTypeNode node = Mockito.spy(CardTypeNode.class);
        IdentifierNode identifierNode = Mockito.mock(IdentifierNode.class);
        IdentifierNode defineNodeID = Mockito.mock(IdentifierNode.class);
        DefineNode testField = Mockito.spy(DefineNode.class);
        FunctionDNode testMethod = Mockito.spy(FunctionDNode.class);
        ArrayList<FunctionDNode> mockMethods = new ArrayList<>(){{add(testMethod);}};
        ArrayList<DefineNode> testFields = new ArrayList<>(){{add(testField);}};

        ModifierNode testMethodModi = Mockito.spy(ModifierNode.class);
        when(testMethodModi.getModifier()).thenReturn("static");
        when(testMethod.getModifier()).thenReturn(testMethodModi);
        IdentifierNode testMethodID = Mockito.spy(IdentifierNode.class);
        when(testMethodID.getText()).thenReturn("test");
        when(testMethod.getFunction()).thenReturn(testMethodID);
        FparamsNode fparamsNode = Mockito.mock(FparamsNode.class);
        TypeNode testMethodType = Mockito.spy(TypeNode.class);
        when(testMethod.getReturnType()).thenReturn(testMethodType);
        BlockNode testMethodBlock = Mockito.spy(BlockNode.class);
        when(testMethod.getBlock()).thenReturn(testMethodBlock);
        when(testMethod.getIsAction()).thenReturn(false);

        when(node.getMethods()).thenReturn(mockMethods);
        when(node.getFields()).thenReturn(testFields);
        when(defineNodeID.getText()).thenReturn("value");
        when(testField.getID()).thenReturn(defineNodeID);
        when(node.getIdentifier()).thenReturn(identifierNode);
        when(node.getID()).thenReturn("test");
        when(testMethodType.getTypeName()).thenReturn("int");
        when(testMethod.getParameter()).thenReturn(fparamsNode);

        CodeBuilderVisitor visitor = Mockito.spy(CodeBuilderVisitor.class);
        doReturn("static Integer test(int testParam){}").when(visitor).visit(testMethod);
        doReturn("static ").when(visitor).visit(testMethodModi);
        doReturn("int value = 1;").when(visitor).visit(testField);
        doReturn("int testParam").when(visitor).visit(fparamsNode);
        doReturn("Integer").when(visitor).handleType(any(String.class));


        String result = visitor.visit(node);
        System.out.println(visitor.getClasses().get("Cardtest").close());
        //Find Define node code in the new class
        int fieldIndex = visitor.getClasses().get("Card").getBlock().indexOf("int value = 1;");
        assertNotEquals(-1, fieldIndex);
        assertNotNull(visitor.getClasses().get("Cardtest"));

        int methodIndex = visitor.getClasses().get("Card").getBlock().indexOf("static Integer test(int testParam){");
        assertNotEquals(-1, methodIndex);

        int cardTestIndex = visitor.getClasses().get("Cardtest").getBlock().indexOf("static Integer test(int testParam){");
        assertNotEquals(-1, cardTestIndex);
    }
    @Test
    public void testTestVisitCardTypeAddMethodToAll() {
        CardTypeNode node = Mockito.spy(CardTypeNode.class);
        FunctionDNode testMethod = Mockito.spy(FunctionDNode.class);
        ArrayList<FunctionDNode> mockMethods = new ArrayList<>(){{add(testMethod);}};
        ModifierNode testMethodModi = Mockito.spy(ModifierNode.class);

        when(testMethodModi.getModifier()).thenReturn("static");
        when(testMethod.getModifier()).thenReturn(testMethodModi);
        IdentifierNode testMethodID = Mockito.spy(IdentifierNode.class);
        when(testMethodID.getText()).thenReturn("test");
        when(testMethod.getFunction()).thenReturn(testMethodID);
        FparamsNode fparamsNode = Mockito.mock(FparamsNode.class);
        TypeNode testMethodType = Mockito.spy(TypeNode.class);
        when(testMethod.getReturnType()).thenReturn(testMethodType);
        BlockNode testMethodBlock = Mockito.spy(BlockNode.class);
        when(testMethod.getBlock()).thenReturn(testMethodBlock);
        when(testMethod.getIsAction()).thenReturn(false);

        when(node.getMethods()).thenReturn(mockMethods);
        when(node.getID()).thenReturn("test");
        when(testMethodType.getTypeName()).thenReturn("int");
        when(testMethod.getParameter()).thenReturn(fparamsNode);

        CodeBuilderVisitor visitor = Mockito.spy(CodeBuilderVisitor.class);
        doReturn("static Integer test(int testParam){}").when(visitor).visit(testMethod);
        doReturn("int testParam").when(visitor).visit(fparamsNode);
        doReturn("Integer").when(visitor).handleType(any(String.class));

        String result = visitor.visit(node);
        //Find Define node code in the new class
        int methodIndex = visitor.getClasses().get("Card").getBlock().indexOf("static Integer test(int testParam){");
        assertNotEquals(-1, methodIndex);
    }
    @Test
    public void testTestVisitCardTypeToString() {
        CardTypeNode node = Mockito.spy(CardTypeNode.class);
        FunctionDNode testMethod = Mockito.spy(FunctionDNode.class);
        ArrayList<FunctionDNode> mockMethods = new ArrayList<>(){{add(testMethod);}};
        ModifierNode testMethodModi = Mockito.spy(ModifierNode.class);

        when(testMethodModi.getModifier()).thenReturn("static");
        when(testMethod.getModifier()).thenReturn(testMethodModi);
        IdentifierNode testMethodID = Mockito.spy(IdentifierNode.class);
        when(testMethodID.getText()).thenReturn("toString");
        when(testMethod.getFunction()).thenReturn(testMethodID);
        FparamsNode fparamsNode = Mockito.mock(FparamsNode.class);
        TypeNode testMethodType = Mockito.spy(TypeNode.class);
        when(testMethod.getReturnType()).thenReturn(testMethodType);
        BlockNode testMethodBlock = Mockito.spy(BlockNode.class);
        when(testMethod.getBlock()).thenReturn(testMethodBlock);
        when(testMethod.getIsAction()).thenReturn(false);

        when(node.getMethods()).thenReturn(mockMethods);
        when(testMethodType.getTypeName()).thenReturn("String");
        when(testMethod.getParameter()).thenReturn(fparamsNode);

        CodeBuilderVisitor visitor = Mockito.spy(CodeBuilderVisitor.class);
        //doReturn("static ").when(visitor).visit(testMethodModi);
        doReturn("static String toString(int testParam){}").when(visitor).visit(testMethod);
        doReturn("int testParam").when(visitor).visit(fparamsNode);
        doReturn("String").when(visitor).handleType(any(String.class));

        String result = visitor.visit(node);
        System.out.println(CodeFormatter.formatCode(visitor.getClasses().get("Card").close()));
        //Find Define node code in the new class
        int methodIndex = visitor.getClasses().get("Card").getBlock().indexOf("static String toString(int testParam){");
        assertNotEquals(-1, methodIndex);
    }

    @Test
    public void testFunctionDStaticVisit() {
        //func calSum(int a, int b) -> int { }
        //public int calSum(int a, int b) { return a + b; }
        FparamsNode fparamsNode = Mockito.mock(FparamsNode.class);
        BlockNode blockNode = Mockito.mock(BlockNode.class);
        IdentifierNode identifierNode = Mockito.mock(IdentifierNode.class);
        ModifierNode modifierNode = Mockito.mock(ModifierNode.class);

        CodeBuilderVisitor visitor = Mockito.spy(CodeBuilderVisitor.class);
        visitor.setScopeCount(0);

        TypeNode typeNode = Mockito.spy(TypeNode.class);
        typeNode.setTypeName("int");

        FunctionDNode functionDNode = Mockito.spy(FunctionDNode.class);
        functionDNode.setReturnType(typeNode);

        when(functionDNode.getModifier()).thenReturn(modifierNode);
        when(functionDNode.getFunction()).thenReturn(identifierNode);
        when(functionDNode.getFunction().getText()).thenReturn("calSum");
        when(functionDNode.getParameter()).thenReturn(fparamsNode);
        when(functionDNode.getBlock()).thenReturn(blockNode);
        doReturn("int a, int b").when(visitor).visit(fparamsNode);
        doReturn("return a + b;").when(visitor).visit(blockNode);
        doReturn("").when(visitor).visit(modifierNode);
        String result = visitor.visit(functionDNode);

        assertEquals("static int calSum(int a, int b) {return a + b;}", visitor.getFunctions().get(3));

        /*
        //func calSum(int a, int b) -> int { }
        //public int calSum(int a, int b) { }

        FunctionDNode functionDNode = Mockito.spy(FunctionDNode.class);
        FparamsNode fparamsNode = new FparamsNode();
        IdentifierNode identifierNode = Mockito.spy(IdentifierNode.class);
        identifierNode.setText("calSum");

        when(functionDNode.getParameter()).thenReturn(fparamsNode);
        when(functionDNode.getFunction()).thenReturn(identifierNode);
        CodeBuilderVisitor visitor = Mockito.spy(CodeBuilderVisitor.class);
        visitor.setScopeCount(1);
        functionDNode.setIsAction(true);

        doReturn("int a, int b").when(visitor).visit(fparamsNode);
        String result = visitor.visit(functionDNode);
        */
    }
    @Test
    public void testFunctionDVisit() {
        //func calSum(int a, int b) -> int { }
        //public int calSum(int a, int b) { return a + b; }
        FparamsNode fparamsNode = Mockito.mock(FparamsNode.class);
        BlockNode blockNode = Mockito.mock(BlockNode.class);
        IdentifierNode identifierNode = Mockito.mock(IdentifierNode.class);
        ModifierNode modifierNode = Mockito.mock(ModifierNode.class);

        CodeBuilderVisitor visitor = Mockito.spy(CodeBuilderVisitor.class);
        visitor.setScopeCount(1);

        TypeNode typeNode = Mockito.spy(TypeNode.class);

        FunctionDNode functionDNode = Mockito.spy(FunctionDNode.class);
        functionDNode.setReturnType(typeNode);

        when(functionDNode.getModifier()).thenReturn(modifierNode);
        when(functionDNode.getFunction()).thenReturn(identifierNode);
        when(functionDNode.getFunction().getText()).thenReturn("calSum");
        when(functionDNode.getParameter()).thenReturn(fparamsNode);
        when(functionDNode.getBlock()).thenReturn(blockNode);
        when(functionDNode.getReturnType()).thenReturn(typeNode);
        when(typeNode.getTypeName()).thenReturn("int");

        doReturn("int").when(visitor).handleType(any(String.class));
        doReturn("int a, int b").when(visitor).visit(any(FparamsNode.class));
        doReturn("return a + b;").when(visitor).visit(blockNode);
        doReturn("").when(visitor).visit(modifierNode);
        String result = visitor.visit(functionDNode);

        assertEquals("int calSum(int a, int b) {return a + b;}", result);

    }
    @Test
    public void testFunctionDGameVisit() {
        //func game() -> void { }
        //static public void game() { print("It is your turn"); }
        FparamsNode fparamsNode = Mockito.mock(FparamsNode.class);
        BlockNode blockNode = Mockito.mock(BlockNode.class);
        IdentifierNode identifierNode = Mockito.mock(IdentifierNode.class);

        CodeBuilderVisitor visitor = Mockito.spy(CodeBuilderVisitor.class);
        visitor.setScopeCount(0);

        TypeNode typeNode = Mockito.spy(TypeNode.class);

        FunctionDNode functionDNode = Mockito.spy(FunctionDNode.class);
        functionDNode.setReturnType(typeNode);

        when(functionDNode.getFunction()).thenReturn(identifierNode);
        when(functionDNode.getFunction().getText()).thenReturn("game");
        when(functionDNode.getParameter()).thenReturn(fparamsNode);
        when(functionDNode.getBlock()).thenReturn(blockNode);
        when(functionDNode.getReturnType()).thenReturn(typeNode);
        when(typeNode.getTypeName()).thenReturn("void");

        doReturn("void").when(visitor).handleType(any(String.class));
        doReturn("").when(visitor).visit(any(FparamsNode.class));
        doReturn("print(\"It is your turn\");").when(visitor).visit(blockNode);
        String result = visitor.visit(functionDNode);

        assertEquals("static void game() {print(\"It is your turn\");}", visitor.getGameFunction());

    }
    @Test
    public void testFunctionDEndVisit() {
        //func game() -> void { }
        //static public void game() { print("It is your turn"); }
        FparamsNode fparamsNode = Mockito.mock(FparamsNode.class);
        BlockNode blockNode = Mockito.mock(BlockNode.class);
        IdentifierNode identifierNode = Mockito.mock(IdentifierNode.class);

        CodeBuilderVisitor visitor = Mockito.spy(CodeBuilderVisitor.class);
        visitor.setScopeCount(0);

        TypeNode typeNode = Mockito.spy(TypeNode.class);

        FunctionDNode functionDNode = Mockito.spy(FunctionDNode.class);
        functionDNode.setReturnType(typeNode);

        when(functionDNode.getFunction()).thenReturn(identifierNode);
        when(functionDNode.getFunction().getText()).thenReturn("end");
        when(functionDNode.getParameter()).thenReturn(fparamsNode);
        when(functionDNode.getBlock()).thenReturn(blockNode);
        when(functionDNode.getReturnType()).thenReturn(typeNode);
        when(typeNode.getTypeName()).thenReturn("void");

        doReturn("void").when(visitor).handleType(any(String.class));
        doReturn("").when(visitor).visit(any(FparamsNode.class));
        doReturn("print(\"It is your turn\");").when(visitor).visit(blockNode);
        String result = visitor.visit(functionDNode);

        assertEquals("static void end() {print(\"It is your turn\");System.exit(0); }", visitor.getEndFunction());

    }
    @Test
    public void testFunctionDActionAllowDefinedVisit() {
        FparamsNode fparamsNode = Mockito.mock(FparamsNode.class);
        IdentifierNode identifierNode = Mockito.mock(IdentifierNode.class);
        ExpressionNode expressionNode = Mockito.mock(ExpressionNode.class);
        BlockNode blockNode = Mockito.mock(BlockNode.class);
        ModifierNode modifierNode = Mockito.mock(ModifierNode.class);
        CodeBuilderVisitor visitor = Mockito.spy(CodeBuilderVisitor.class);

        visitor.setScopeCount(1);

        FunctionDNode functionDNode = Mockito.spy(FunctionDNode.class);

        when(functionDNode.getBlock()).thenReturn(blockNode);
        when(functionDNode.getFunction()).thenReturn(identifierNode);
        when(functionDNode.getFunction().getText()).thenReturn("changecolor");
        when(functionDNode.getIsAction()).thenReturn(true);
        when(functionDNode.getParameter()).thenReturn(fparamsNode);
        when(functionDNode.getExpr()).thenReturn(expressionNode);
        when(functionDNode.getModifier()).thenReturn(modifierNode);

        doReturn("").when(visitor).visit(modifierNode);
        doReturn("").when(visitor).visit(blockNode);
        doReturn("Card card, String color").when(visitor).visit(fparamsNode);
        doReturn("\"change to \"+color;").when(visitor).visit(expressionNode);
        String result = visitor.visit(functionDNode);

        assertNotEquals(-1, visitor.getClasses().get("ActionMenu").close().indexOf("if (action.equals(\"changecolor\"))"));
        assertNotEquals(-1, visitor.getClasses().get("ActionMenu").close().indexOf("static String getchangecolorString(Card card, String color)"));
    }

    @Test
    public void testFunctionDActionAllowActionVisit() {
        FparamsNode fparamsNode = Mockito.mock(FparamsNode.class);
        FparamsNode fparamsNode2 = Mockito.mock(FparamsNode.class);
        IdentifierNode identifierNode = Mockito.mock(IdentifierNode.class);
        IdentifierNode identifierNode2 = Mockito.mock(IdentifierNode.class);
        ExpressionNode expressionNode = Mockito.mock(ExpressionNode.class);
        ExpressionNode expressionNode2 = Mockito.mock(ExpressionNode.class);
        BlockNode blockNode = Mockito.mock(BlockNode.class);
        BlockNode blockNode2 = Mockito.mock(BlockNode.class);
        ModifierNode modifierNode = Mockito.mock(ModifierNode.class);
        ModifierNode modifierNode2 = Mockito.mock(ModifierNode.class);
        ClassStringBuilder actionMenu = Mockito.mock(ClassStringBuilder.class);
        StringBuilder block = Mockito.spy(StringBuilder.class);
        CodeBuilderVisitor visitor = Mockito.spy(CodeBuilderVisitor.class);

        visitor.setScopeCount(1);

        FunctionDNode functionDNode = Mockito.spy(FunctionDNode.class);
        FunctionDNode functionDNode2 = Mockito.spy(FunctionDNode.class);

        when(functionDNode.getBlock()).thenReturn(blockNode);
        when(functionDNode2.getBlock()).thenReturn(blockNode2);
        when(actionMenu.getBlock()).thenReturn(block);
        when(block.toString()).thenReturn("static void allowAction(");
        when(functionDNode.getFunction()).thenReturn(identifierNode);
        when(functionDNode2.getFunction()).thenReturn(identifierNode2);
        when(functionDNode.getFunction().getText()).thenReturn("functionText");
        when(functionDNode2.getFunction().getText()).thenReturn("funcText2");
        when(functionDNode.getIsAction()).thenReturn(true);
        when(functionDNode2.getIsAction()).thenReturn(true);
        when(functionDNode.getParameter()).thenReturn(fparamsNode);
        when(functionDNode2.getParameter()).thenReturn(fparamsNode2);
        when(functionDNode.getExpr()).thenReturn(expressionNode);
        when(functionDNode2.getExpr()).thenReturn(expressionNode2);
        when(functionDNode.getModifier()).thenReturn(modifierNode);
        when(functionDNode2.getModifier()).thenReturn(modifierNode2);

        //doReturn(block).when(actionMenu).getBlock();
        doReturn("").when(visitor).visit(modifierNode);
        doReturn("").when(visitor).visit(blockNode);
        doReturn("").when(visitor).visit(fparamsNode);
        doReturn("").when(visitor).visit(blockNode2);
        doReturn("").when(visitor).visit(modifierNode2);
        doReturn("").when(visitor).visit(fparamsNode2);

        doReturn("\"change to \"+color;").when(visitor).visit(expressionNode);
        doReturn("\"change to \"+color;").when(visitor).visit(expressionNode2);
        String preResult = visitor.visit(functionDNode);
        String result = visitor.visit(functionDNode2);


        assertNotEquals(-1, visitor.getClasses().get("ActionMenu").close().indexOf("if (action.equals(\"funcText2\"))"));
        assertNotEquals(-1, visitor.getClasses().get("ActionMenu").close().indexOf("static String getfuncText2String()"));
        assertNotEquals(-1, visitor.getClasses().get("ActionMenu").close().indexOf("if (action.equals(\"functionText\"))"));
        assertNotEquals(-1, visitor.getClasses().get("ActionMenu").close().indexOf("static String getfunctionTextString()"));
    }

    @Test
    public void testClassDEqualsWithString() {
        ClassDNode classDNode = Mockito.mock(ClassDNode.class);
        StringBuilder classD = Mockito.spy(StringBuilder.class);
        CodeBuilderVisitor visitor = Mockito.spy(CodeBuilderVisitor.class);
        Pair<String,String> pair1 = new Pair<>("String","a");
        Pair<String,String> pair2 = new Pair<>("int","b");
        ArrayList<Pair<String,String>> list1 = new ArrayList<>(){{add(pair2);add(pair1);}};
        Hashtable<String,ArrayList<Pair<String,String>>> table = Mockito.spy(Hashtable.class);
        table.put("className",list1);

        IdentifierNode identifierNode = Mockito.mock(IdentifierNode.class);
        BlockNode blockNode = Mockito.mock(BlockNode.class);
        visitor.setScopeCount(1);

        when(classDNode.getBlock()).thenReturn(blockNode);
        when(classDNode.getSuperClass()).thenReturn(identifierNode);
        when(classDNode.getName()).thenReturn(identifierNode);
        doReturn("String a;int b;").when(visitor).visit(blockNode);
        doReturn("className").when(visitor).visit(identifierNode);
        doReturn(list1).when(table).get(anyString());
        doReturn(table).when(visitor).getClassFields();
        String result = visitor.visit(classDNode);


        assertTrue(result.contains("public boolean equals(Object other) {if (other.getClass().equals(super.getClass())) {return (this.b == ((className) other).b && this.a.equals(((className) other).a));}return false;}"));
    }

    @Test
    public void testClassDEqualsWithClass() {
        ClassDNode classDNode = Mockito.mock(ClassDNode.class);
        StringBuilder classD = Mockito.spy(StringBuilder.class);
        CodeBuilderVisitor visitor = Mockito.spy(CodeBuilderVisitor.class);
        Pair<String,String> pair1 = new Pair<>("anotherClass","a");
        Pair<String,String> pair2 = new Pair<>("int","b");
        ArrayList<Pair<String,String>> list1 = new ArrayList<>(){{add(pair2);add(pair1);}};
        Hashtable<String,ArrayList<Pair<String,String>>> table = Mockito.spy(Hashtable.class);
        table.put("className",list1);

        IdentifierNode identifierNode = Mockito.mock(IdentifierNode.class);
        BlockNode blockNode = Mockito.mock(BlockNode.class);
        visitor.setScopeCount(1);

        when(classDNode.getBlock()).thenReturn(blockNode);
        when(classDNode.getSuperClass()).thenReturn(identifierNode);
        when(classDNode.getName()).thenReturn(identifierNode);
        doReturn("anotherClass a;int b;").when(visitor).visit(blockNode);
        doReturn("className").when(visitor).visit(identifierNode);
        doReturn(list1).when(table).get(anyString());
        doReturn(table).when(visitor).getClassFields();
        String result = visitor.visit(classDNode);


        assertTrue(result.contains("public boolean equals(Object other) {if (other.getClass().equals(super.getClass())) {return (this.b == ((className) other).b && this.a.equals(((className) other).a));}return false;}"));
    }

    @Test
    public void testClassDEqualsWithoutFields() {
        ClassDNode classDNode = Mockito.mock(ClassDNode.class);
        StringBuilder classD = Mockito.spy(StringBuilder.class);
        CodeBuilderVisitor visitor = Mockito.spy(CodeBuilderVisitor.class);
        ArrayList<Pair<String,String>> list1 = new ArrayList<>();
        Hashtable<String,ArrayList<Pair<String,String>>> table = Mockito.spy(Hashtable.class);
        table.put("className", list1);

        IdentifierNode identifierNode = Mockito.mock(IdentifierNode.class);
        BlockNode blockNode = Mockito.mock(BlockNode.class);
        visitor.setScopeCount(1);

        when(classDNode.getBlock()).thenReturn(blockNode);
        when(classDNode.getSuperClass()).thenReturn(identifierNode);
        when(classDNode.getName()).thenReturn(identifierNode);
        doReturn("className").when(visitor).visit(identifierNode);
        doReturn(list1).when(table).get(anyString());
        doReturn(table).when(visitor).getClassFields();
        String result = visitor.visit(classDNode);


        assertTrue(result.contains("public boolean equals(Object other) {if (other.getClass().equals(super.getClass())) {return true;}return false;}"));
    }

    @Test
    public void testBoolToInt() {
        CodeBuilderVisitor visitor = Mockito.spy(CodeBuilderVisitor.class);
        String boolExp = "true";
        String result = visitor.boolToInt(boolExp);
        assertEquals("((true) == false ? 0 : 1)", result);
    }

    @Test
    public void testIntToBool() {
        CodeBuilderVisitor visitor = Mockito.spy(CodeBuilderVisitor.class);
        String intExp = "1";
        String result = visitor.intToBool(intExp);
        assertEquals("((1) == 0 ? false : true)", result);
    }

    @Test
    public void tesHandleType() {
        CodeBuilderVisitor visitor = Mockito.spy(CodeBuilderVisitor.class);
        String type = "int";
        String result = visitor.handleType(type);
        assertEquals("int", result);
    }

    @Test
    public void testHandleTypeWithString(){
        CodeBuilderVisitor visitor = Mockito.spy(CodeBuilderVisitor.class);
        String type = "string";
        String result = visitor.handleType(type);
        assertEquals("String", result);
    }

    @Test
    public void testHandleTypeWithArray(){
        CodeBuilderVisitor visitor = Mockito.spy(CodeBuilderVisitor.class);
        String type = "array int";
        String result = visitor.handleType(type);
        assertEquals("Integer", result);
    }

    @Test
    public void testVisitAdditionNode(){
        AdditionNode additionNode = Mockito.mock(AdditionNode.class);
        ExpressionNode left = Mockito.mock(ExpressionNode.class);
        ExpressionNode right = Mockito.mock(ExpressionNode.class);

        CodeBuilderVisitor visitor = Mockito.spy(CodeBuilderVisitor.class);

        when(additionNode.getLeft()).thenReturn(left);
        when(additionNode.getRight()).thenReturn(right);
        doReturn("1").when(visitor).visit(left);
        doReturn("2").when(visitor).visit(right);

        String result = visitor.visit(additionNode);
        assertEquals("1+2", result);
    }

    @Test
    public void testVisitSubtractionNode(){
        SubtractionNode subtractionNode = Mockito.mock(SubtractionNode.class);
        ExpressionNode left = Mockito.mock(ExpressionNode.class);
        ExpressionNode right = Mockito.mock(ExpressionNode.class);

        CodeBuilderVisitor visitor = Mockito.spy(CodeBuilderVisitor.class);

        when(subtractionNode.getLeft()).thenReturn(left);
        when(subtractionNode.getRight()).thenReturn(right);
        doReturn("1").when(visitor).visit(left);
        doReturn("2").when(visitor).visit(right);

        String result = visitor.visit(subtractionNode);
        assertEquals("1-2", result);
    }

    @Test
    public void testVisitMultiplicationNode(){
        MultiplicationNode multiplicationNode = Mockito.mock(MultiplicationNode.class);
        ExpressionNode left = Mockito.mock(ExpressionNode.class);
        ExpressionNode right = Mockito.mock(ExpressionNode.class);

        CodeBuilderVisitor visitor = Mockito.spy(CodeBuilderVisitor.class);

        when(multiplicationNode.getLeft()).thenReturn(left);
        when(multiplicationNode.getRight()).thenReturn(right);
        doReturn("1").when(visitor).visit(left);
        doReturn("2").when(visitor).visit(right);

        String result = visitor.visit(multiplicationNode);
        assertEquals("1*2", result);
    }

    @Test
    public void testVisitDivisionNode(){
        DivisionNode divisionNode = Mockito.mock(DivisionNode.class);
        ExpressionNode left = Mockito.mock(ExpressionNode.class);
        ExpressionNode right = Mockito.mock(ExpressionNode.class);

        CodeBuilderVisitor visitor = Mockito.spy(CodeBuilderVisitor.class);

        when(divisionNode.getLeft()).thenReturn(left);
        when(divisionNode.getRight()).thenReturn(right);
        doReturn("1").when(visitor).visit(left);
        doReturn("2").when(visitor).visit(right);

        String result = visitor.visit(divisionNode);
        assertEquals("1/2", result);
    }

    @Test
    public void testVisitModNode(){
        ModNode modNode = Mockito.mock(ModNode.class);
        ExpressionNode left = Mockito.mock(ExpressionNode.class);
        ExpressionNode right = Mockito.mock(ExpressionNode.class);

        CodeBuilderVisitor visitor = Mockito.spy(CodeBuilderVisitor.class);

        when(modNode.getLeft()).thenReturn(left);
        when(modNode.getRight()).thenReturn(right);
        doReturn("1").when(visitor).visit(left);
        doReturn("2").when(visitor).visit(right);

        String result = visitor.visit(modNode);
        assertEquals("1%2", result);
    }

    @Test
    public void testVisitNegateNode(){
        NegateNode negateNode = Mockito.mock(NegateNode.class);
        ExpressionNode expressionNode = Mockito.mock(ExpressionNode.class);

        CodeBuilderVisitor visitor = Mockito.spy(CodeBuilderVisitor.class);

        when(negateNode.getInnerNode()).thenReturn(expressionNode);
        doReturn("1").when(visitor).visit(expressionNode);

        String result = visitor.visit(negateNode);
        assertEquals("!1", result);
    }

    @Test
    public void testVisitNumberNode(){
        CodeBuilderVisitor visitor = Mockito.spy(CodeBuilderVisitor.class);
        NumberNode node = mock(NumberNode.class);

        doReturn("1").when(visitor).visit(node);

        String result = visitor.visit(node);
        assertEquals("1", result);
    }

    @Test
    public void testVisitStringNode(){
        CodeBuilderVisitor visitor = Mockito.spy(CodeBuilderVisitor.class);
        StringNode node = mock(StringNode.class);

        when(node.getValue()).thenReturn("Hej");

        String result = visitor.visit(node);
        assertEquals("Hej", result);
    }

    @Test
    public void testVisitFloatNode(){
        CodeBuilderVisitor visitor = Mockito.spy(CodeBuilderVisitor.class);
        FloatNode node = mock(FloatNode.class);
        when(node.getValue()).thenReturn(1.0);

        String result = visitor.visit(node);
        assertEquals("1.0f", result);
    }

    @Test
    public void testVisitCharNode(){
        CodeBuilderVisitor visitor = Mockito.spy(CodeBuilderVisitor.class);
        CharNode node = mock(CharNode.class);
        when(node.getValue()).thenReturn(String.valueOf('a'));

        String result = visitor.visit(node);
        assertEquals("a", result);
    }

    @Test
    public void testVisitReturnNode(){
        CodeBuilderVisitor visitor = Mockito.spy(CodeBuilderVisitor.class);
        ReturnNode node = mock(ReturnNode.class);
        ExpressionNode expressionNode = mock(ExpressionNode.class);

        when(node.getInnerNode()).thenReturn(expressionNode);
        doReturn("1").when(visitor).visit(expressionNode);

        String result = visitor.visit(node);
        assertEquals("return 1", result);
    }

    @Test
    public void testVisitBreakNode(){
        CodeBuilderVisitor visitor = Mockito.spy(CodeBuilderVisitor.class);
        BreakNode node = mock(BreakNode.class);

        String result = visitor.visit(node);
        assertEquals("break", result);
    }

    @Test
    public void testVisitContinueNode(){
        CodeBuilderVisitor visitor = Mockito.spy(CodeBuilderVisitor.class);
        ContinueNode node = mock(ContinueNode.class);

        String result = visitor.visit(node);
        assertEquals("continue", result);
    }

    @Test
    public void testVisitArrayNode(){
        CodeBuilderVisitor visitor = Mockito.spy(CodeBuilderVisitor.class);
        ArrayNode node = mock(ArrayNode.class);
        ExpressionsNode expressionNode = mock(ExpressionsNode.class);

        when(node.getInnerNode()).thenReturn(expressionNode);
        doReturn("4").when(visitor).visit(expressionNode);

        String result = visitor.visit(node);
        assertEquals("new ArrayList<>() {{add(4);}}", result);
    }

    @Test
    public void testVisitArrayAccessNode(){
        CodeBuilderVisitor visitor = Mockito.spy(CodeBuilderVisitor.class);
        ArrayAccessNode node = mock(ArrayAccessNode.class);
        IdentifierNode identifierNode = mock(IdentifierNode.class);
        ExpressionNode expressionNode = mock(ExpressionNode.class);

        when(node.getArray()).thenReturn(identifierNode);
        when(node.getIndex()).thenReturn(expressionNode);
        doReturn("array").when(visitor).visit(identifierNode);
        doReturn("4").when(visitor).visit(expressionNode);

        String result = visitor.visit(node);
        assertEquals("array.get(4)", result);
    }

    @Test
    public void testVisitAssignmentNode(){
        CodeBuilderVisitor visitor = Mockito.spy(CodeBuilderVisitor.class);
        AssignmentNode node = mock(AssignmentNode.class);
        IdentifierNode identifierNode = mock(IdentifierNode.class);
        ExpressionNode expressionNode = mock(ExpressionNode.class);

        when(node.getLeft()).thenReturn(identifierNode);
        when(node.getRight()).thenReturn(expressionNode);
        doReturn("a").when(visitor).visit(identifierNode);
        doReturn("4").when(visitor).visit(expressionNode);

        String result = visitor.visit(node);
        assertEquals("a = 4", result);
    }

    @Test
    public void testVisitFparamNode(){
        CodeBuilderVisitor visitor = Mockito.spy(CodeBuilderVisitor.class);
        FparamNode node = mock(FparamNode.class);
        TypeNode typeNode = mock(TypeNode.class);
        IdentifierNode identifierNode = mock(IdentifierNode.class);

        when(node.getType()).thenReturn(typeNode);
        when(node.getIdentifier()).thenReturn(identifierNode);
        doReturn("int").when(visitor).visit(typeNode);
        doReturn("a").when(visitor).visit(identifierNode);

        String result = visitor.visit(node);
        assertEquals("int a", result);
    }

    @Test
    public void testVisitModifierNode(){
        CodeBuilderVisitor visitor = Mockito.spy(CodeBuilderVisitor.class);
        ModifierNode node = mock(ModifierNode.class);

        when(node.getModifier()).thenReturn("static");

        String result = visitor.visit(node);
        assertEquals("static ", result);
    }

    @Test
    public void testVisitTypeNode(){
        CodeBuilderVisitor visitor = Mockito.spy(CodeBuilderVisitor.class);
        TypeNode node = mock(TypeNode.class);

        when(node.getTypeName()).thenReturn("int");

        String result = visitor.visit(node);
        assertEquals("int", result);
    }
}