import codegenExample.Card;
import junit.framework.TestCase;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.Pair;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.mockito.Mockito;
import gen.*;
import nodes.*;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;


import java.lang.annotation.Documented;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
public class CodeBuilderVisitorTest extends TestCase {

    public void testVisitStart() {
    }

    public void testVisit() {
    }

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

        assertEquals("test = \"Hej\";",result );
        assertEquals("static String test;", visitor.getVariables().get(1));
    }
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

        assertEquals("static String test = \"Hej\";",result );
    }
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

        assertEquals("static String test = \"\";",result );
    }
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

        assertEquals("static char test = '\\0';",result );
    }
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

        assertEquals("static int test = 0;",result );
    }
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

    public void testTestVisit4() {
    }

    public void testTestVisit5() {
    }

    public void testTestVisit6() {
    }

    public void testTestVisit7() {
    }

    public void testTestVisit8() {
    }

    public void testTestVisit9() {
    }

    public void testTestVisit10() {
    }

    public void testTestVisit11() {
    }

    public void testTestVisit12() {
    }

    public void testTestVisit13() {
    }

    public void testTestVisit14() {
    }

    public void testTestVisit15() {
    }

    public void testTestVisit16() {
    }

    public void testTestVisit17() {
    }

    public void testTestVisit18() {
    }

    public void testTestVisit19() {
    }

    public void testTestVisit20() {
    }

    public void testTestVisit21() {
    }

    public void testTestVisit22() {
    }

    public void testTestVisit23() {
    }

    public void testTestVisit24() {
    }

    public void testTestVisit25() {
    }

    public void testTestVisit26() {
    }

    public void testTestVisit27() {
    }

    public void testTestVisit28() {
    }

    public void testTestVisit29() {
    }

    public void testTestVisit30() {
    }

    public void testTestVisit31() {
    }

    public void testTestVisit32() {
    }

    public void testTestVisit33() {
    }

    public void testTestVisit34() {
    }

    public void testTestVisit35() {
    }

    public void testTestVisit36() {
    }

    public void testTestVisit37() {
    }

    public void testTestVisit38() {
    }

    public void testTestVisit39() {
    }
}