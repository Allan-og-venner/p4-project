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
import static org.mockito.Mockito.*;
public class CodeBuilderVisitorTest extends TestCase {

    public void testVisitStart() {
    }

    public void testVisit() {
    }

    public void testTestVisitDefineNodeArrayGlobal() {
        //ArrayList<String> test = new ArrayList<>(){{add("Hej");add("Farvel")}}
        DefineNode defineNode = Mockito.spy(DefineNode.class);
        ModifierNode modifierNode = Mockito.mock(ModifierNode.class);
        IdentifierNode identifierNode = new IdentifierNode();
        ExpressionNode expressionNode = Mockito.mock(ExpressionNode.class);
        TypeNode typeNode = Mockito.mock(TypeNode.class);

        //Set the node
        defineNode.setArray(true);
        identifierNode.setText("test");
        defineNode.setID(identifierNode);
        defineNode.setValue(expressionNode);
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
        ModifierNode modifierNode = Mockito.mock(ModifierNode.class);
        IdentifierNode identifierNode = new IdentifierNode();
        ExpressionNode expressionNode = Mockito.mock(ExpressionNode.class);
        TypeNode typeNode = Mockito.mock(TypeNode.class);

        //Set the node
        identifierNode.setText("test");
        defineNode.setID(identifierNode);
        defineNode.setValue(expressionNode);
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
        ModifierNode modifierNode = Mockito.mock(ModifierNode.class);
        IdentifierNode identifierNode = new IdentifierNode();
        ExpressionNode expressionNode = Mockito.mock(ExpressionNode.class);
        TypeNode typeNode = Mockito.mock(TypeNode.class);

        //Set the node
        identifierNode.setText("test");
        defineNode.setID(identifierNode);
        //doReturn(typeNode).when(defineNode).getType();
        when(defineNode.getType()).thenReturn(typeNode);
        //when(defineNode.getValue()).th

        CodeBuilderVisitor visitor = Mockito.spy(CodeBuilderVisitor.class);
        doReturn("String").when(visitor).visit(typeNode);

        String result = visitor.visit(defineNode);

        assertEquals("",result );
        assertEquals("static String test;", visitor.getVariables().get(1));
    }
    public void testTestVisitDefineNodeArray() {
        //ArrayList<String> test = new ArrayList<>(){{add("Hej");add("Farvel")}}
        DefineNode defineNode = Mockito.spy(DefineNode.class);
        ModifierNode modifierNode = Mockito.mock(ModifierNode.class);
        IdentifierNode identifierNode = new IdentifierNode();
        ExpressionNode expressionNode = Mockito.mock(ExpressionNode.class);
        TypeNode typeNode = Mockito.mock(TypeNode.class);
        TypeNode typeNode2 = Mockito.spy(TypeNode.class);

        //Set the node
        defineNode.setArray(true);
        identifierNode.setText("test");
        defineNode.setID(identifierNode);
        defineNode.setValue(expressionNode);
        typeNode2.setTypeName("string");
        defineNode.setType(typeNode2);
        //doReturn(typeNode).when(defineNode).getType();
        when(defineNode.getType()).thenReturn(typeNode);
        when(defineNode.getModi()).thenReturn(modifierNode);
        //when(defineNode.getValue()).th

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
        IdentifierNode identifierNode = new IdentifierNode();
        ExpressionNode expressionNode = Mockito.mock(ExpressionNode.class);
        TypeNode typeNode = Mockito.mock(TypeNode.class);
        TypeNode typeNode2 = Mockito.mock(TypeNode.class);

        //Set the node

        defineNode.setArray(false);
        identifierNode.setText("test");
        defineNode.setID(identifierNode);
        defineNode.setValue(expressionNode);
        typeNode2.setTypeName("string");
        defineNode.setType(typeNode2);
        when(defineNode.getModi()).thenReturn(modifierNode);
        //doReturn(typeNode).when(defineNode).getType();

        when(defineNode.getType()).thenReturn(typeNode);
        //when(defineNode.getValue()).th

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
        IdentifierNode identifierNode = new IdentifierNode();
        ExpressionNode expressionNode = Mockito.mock(ExpressionNode.class);
        TypeNode typeNode = Mockito.mock(TypeNode.class);
        TypeNode typeNode2 = Mockito.spy(TypeNode.class);

        //Set the node

        defineNode.setArray(false);
        identifierNode.setText("test");
        defineNode.setID(identifierNode);
        typeNode2.setTypeName("string");
        defineNode.setType(typeNode2);
        when(defineNode.getModi()).thenReturn(modifierNode);
        //doReturn(typeNode).when(defineNode).getType();

        when(defineNode.getType()).thenReturn(typeNode2);
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
        IdentifierNode identifierNode = new IdentifierNode();
        ExpressionNode expressionNode = Mockito.mock(ExpressionNode.class);
        TypeNode typeNode = Mockito.mock(TypeNode.class);
        TypeNode typeNode2 = Mockito.spy(TypeNode.class);

        //Set the node

        defineNode.setArray(false);
        identifierNode.setText("test");
        defineNode.setID(identifierNode);
        typeNode2.setTypeName("char");
        defineNode.setType(typeNode2);
        when(defineNode.getModi()).thenReturn(modifierNode);
        //doReturn(typeNode).when(defineNode).getType();

        when(defineNode.getType()).thenReturn(typeNode2);
        //when(defineNode.getValue()).th

        CodeBuilderVisitor visitor = Mockito.spy(CodeBuilderVisitor.class);
        visitor.setScopeCount(1);
        doReturn("char").when(visitor).visit(typeNode2);
        doReturn("static ").when(visitor).visit(modifierNode);

        String result = visitor.visit(defineNode);

        assertEquals("static char test = '\\0';",result );
    }
    public void testTestVisitDefineNodeVarNoValueInt() {
        //String test = "Hej";
        DefineNode defineNode = Mockito.spy(DefineNode.class);
        ModifierNode modifierNode = Mockito.mock(ModifierNode.class);
        IdentifierNode identifierNode = new IdentifierNode();
        ExpressionNode expressionNode = Mockito.mock(ExpressionNode.class);
        TypeNode typeNode = Mockito.mock(TypeNode.class);
        TypeNode typeNode2 = Mockito.spy(TypeNode.class);

        //Set the node

        defineNode.setArray(false);
        identifierNode.setText("test");
        defineNode.setID(identifierNode);
        typeNode2.setTypeName("int");
        defineNode.setType(typeNode2);
        when(defineNode.getModi()).thenReturn(modifierNode);
        //doReturn(typeNode).when(defineNode).getType();

        when(defineNode.getType()).thenReturn(typeNode2);
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
        IdentifierNode identifierNode = new IdentifierNode();
        ExpressionNode expressionNode = Mockito.mock(ExpressionNode.class);
        TypeNode typeNode = Mockito.mock(TypeNode.class);
        TypeNode typeNode2 = Mockito.spy(TypeNode.class);
        //new Pair<String, String> testPair = new Pair<>()M

        //Set the node
        defineNode.setArray(false);
        identifierNode.setText("test");
        defineNode.setID(identifierNode);
        typeNode2.setTypeName("Card");
        defineNode.setType(typeNode2);
        when(defineNode.getModi()).thenReturn(modifierNode);
        //doReturn(typeNode).when(defineNode).getType();

        when(defineNode.getType()).thenReturn(typeNode2);
        //when(defineNode.getValue()).th

        CodeBuilderVisitor visitor = Mockito.spy(CodeBuilderVisitor.class);
        visitor.setScopeCount(1);
        visitor.setCurrentClass("Card");
        doReturn("Card").when(visitor).visit(typeNode);
        doReturn("static ").when(visitor).visit(modifierNode);

        String result = visitor.visit(defineNode);

        assertEquals("static Card test = null;",result );
        assertEquals(visitor.getClassFields().get("Card"), "a");
    }





    public void testTestVisit1() {
    }

    public void testTestVisit2() {
    }

    public void testTestVisit3() {
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