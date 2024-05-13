import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import nodes.*;
import org.junit.Test;
import org.mockito.Mockito;

public class TypeCheckerVisitorTests {

    @Test
    public void testAdditionNode() {
        //Set up
        SymbolTable symbolTable = new SymbolTable();
        TypeCheckerVisitor visitor = new TypeCheckerVisitor(symbolTable);
        TypeCheckerVisitor spyVisitor = spy(visitor);

        NumberNode leftNode = new NumberNode();
        NumberNode rightNode = new NumberNode();


        leftNode.setType(new TypeNode("int"));
        rightNode.setType(new TypeNode("int"));

        AdditionNode node = new AdditionNode();
        node.setLeft(leftNode);
        node.setRight(rightNode);

        //Use spy to simulate visit calls
        doReturn("int").when(spyVisitor).visit(leftNode);
        doReturn("int").when(spyVisitor).visit(rightNode);

        //Execute
        String result = spyVisitor.visit(node);

        //Verify
        verify(spyVisitor).visit(leftNode);
        verify(spyVisitor).visit(rightNode);
        assertEquals("int", result);
    }

    @Test
    public void testAndNodeWithBooleans() {
        SymbolTable symbolTable = new SymbolTable();
        symbolTable.createOuterSymbolTable();  //Ensuring types are defined
        TypeCheckerVisitor visitor = new TypeCheckerVisitor(symbolTable);
        TypeCheckerVisitor spyVisitor = Mockito.spy(visitor);

        NumberNode leftValue = new NumberNode();
        leftValue.setValue(1);  //Simulating boolean true

        NumberNode rightValue = new NumberNode();
        rightValue.setValue(0);  //Simulating boolean false

        NegateNode leftNode = new NegateNode();
        leftNode.setInnerNode(leftValue);

        NegateNode rightNode = new NegateNode();
        rightNode.setInnerNode(rightValue);

        ANDNode node = new ANDNode();
        node.setLeft(leftNode);
        node.setRight(rightNode);

        //Configuring the spy to return 'int' for the NegateNode visits
        doReturn("int").when(spyVisitor).visit(any(NegateNode.class));

        //Execute
        String result = spyVisitor.visit(node);
        assertEquals("int", result);  // Expecting 'int' as the type result for AND operation

        //Verify
        verify(spyVisitor, times(2)).visit(any(NegateNode.class));
    }


    @Test(expected = WrongTypeException.class)
    public void testAndNodeWithIntAndString() {
        SymbolTable symbolTable = new SymbolTable();
        symbolTable.createOuterSymbolTable();
        TypeCheckerVisitor visitor = new TypeCheckerVisitor(symbolTable);

        NumberNode leftNode = new NumberNode();
        leftNode.setValue(5);

        StringNode rightNode = new StringNode();
        rightNode.setValue("test");

        ANDNode node = new ANDNode();
        node.setLeft(leftNode);
        node.setRight(rightNode);

        visitor.visit(node); //Should throw exception
    }

}
