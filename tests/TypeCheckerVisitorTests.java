import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import nodes.*;
import org.junit.Test;
import org.mockito.Mockito;

public class TypeCheckerVisitorTests {

    @Test
    public void testAdditionNode() {
        TypeCheckerVisitor spyVisitor = getTypeCheckerVisitor();

        NumberNode leftNode = mock(NumberNode.class);
        NumberNode rightNode = mock(NumberNode.class);

        when(leftNode.getType()).thenReturn(new TypeNode("int"));
        when(rightNode.getType()).thenReturn(new TypeNode("int"));

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
    public void testSubtractionNode(){
        TypeCheckerVisitor spyVisitor = getTypeCheckerVisitor();

        NumberNode leftNode = mock(NumberNode.class);
        NumberNode rightNode = mock(NumberNode.class);

        when(leftNode.getType()).thenReturn(new TypeNode("int"));
        when(rightNode.getType()).thenReturn(new TypeNode("int"));

        SubtractionNode node = new SubtractionNode();
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
        TypeCheckerVisitor spyVisitor = getTypeCheckerVisitor();

        //Set up mocks for the left and right values
        NumberNode leftValue = mock(NumberNode.class);
        when(leftValue.getType()).thenReturn(new TypeNode("int"));

        NumberNode rightValue = mock(NumberNode.class);
        when(rightValue.getType()).thenReturn(new TypeNode("int"));

        NegateNode leftNode = mock(NegateNode.class);
        when(leftNode.getInnerNode()).thenReturn(leftValue);

        NegateNode rightNode = mock(NegateNode.class);
        when(rightNode.getInnerNode()).thenReturn(rightValue);

        //Creating an ANDNode with mocked NegateNode instances
        ANDNode node = new ANDNode();
        node.setLeft(leftNode);
        node.setRight(rightNode);

        //Configure the spy to return 'int' when visiting any NegateNode
        doReturn("int").when(spyVisitor).visit(any(NegateNode.class));

        //Execute
        String result = spyVisitor.visit(node);

        //Assert
        assertEquals("int", result);  //Expecting 'int' as the type result for the AND operation

        //Verify that visit was called on both NegateNodes to make sure the interaction has been tested correctly
        verify(spyVisitor, times(2)).visit(any(NegateNode.class));
    }
    
    @Test(expected = WrongTypeException.class)
    public void testAndNodeWithIntAndString() {
        TypeCheckerVisitor spyVisitor = getTypeCheckerVisitor();

        //Set up mocks for the left and right values
        NumberNode leftNode = mock(NumberNode.class);
        when(leftNode.getType()).thenReturn(new TypeNode("int"));

        StringNode rightNode = mock(StringNode.class);
        when(rightNode.getType()).thenReturn(new TypeNode("string"));

        //Creating an ANDNode with mocked NumberNode and StringNode instances
        ANDNode node = new ANDNode();
        node.setLeft(leftNode);
        node.setRight(rightNode);

        spyVisitor.visit(node); //Should throw exception
    }

    @Test
    public void testOrNodeWithBooleans() {
        TypeCheckerVisitor spyVisitor = getTypeCheckerVisitor();

        //Set up mocks, bools are represented as ints
        NumberNode leftValue = mock(NumberNode.class);
        when(leftValue.getType()).thenReturn(new TypeNode("int"));

        NumberNode rightValue = mock(NumberNode.class);
        when(rightValue.getType()).thenReturn(new TypeNode("int"));

        NegateNode leftNode = mock(NegateNode.class);
        when(leftNode.getInnerNode()).thenReturn(leftValue);


        NegateNode rightNode = mock(NegateNode.class);
        when(rightNode.getInnerNode()).thenReturn(rightValue);

        ORNode node = new ORNode();
        node.setLeft(leftNode);
        node.setRight(rightNode);

        //Configuring the spy to return 'int' for the NegateNode visits
        doReturn("int").when(spyVisitor).visit(any(NegateNode.class));

        //Execute
        String result = spyVisitor.visit(node);

        //Assert
        assertEquals("int", result);  //Expecting 'int' as the type result for the OR operation

        //Verify
        verify(spyVisitor, times(2)).visit(any(NegateNode.class));  //Verify that visit was called on both NegateNodes
    }

    @Test
    public void testVisitFunctionCallNodeWithNewKeyword() {
        //Create a real SymbolTable and then a spy of it
        SymbolTable realSymbolTable = new SymbolTable();
        SymbolTable spiedSymbolTable = spy(realSymbolTable);

        //Set behavior on the spy
        when(spiedSymbolTable.checkClass("MyClass")).thenReturn(true);

        //Create the visitor with the spied SymbolTable
        TypeCheckerVisitor visitor = new TypeCheckerVisitor(spiedSymbolTable);

        //Prepare the node
        FunctionCallNode node = new FunctionCallNode();
        IdentifierNode identifierNode = new IdentifierNode();
        identifierNode.setText("MyClass");
        node.setFunction(identifierNode);
        node.setHasNew(true);

        //Execute
        String result = visitor.visit(node);

        //Assert and verify
        assertEquals("MyClass", result);
        verify(spiedSymbolTable).checkClass("MyClass");
    }

    @Test(expected = SymbolUnboundException.class)
    public void testVisitFunctionCallNodeWithNewClassNotFound() {
        SymbolTable symbolTable = mock(SymbolTable.class);
        when(symbolTable.checkClass("UnknownClass")).thenReturn(false);

        FunctionCallNode node = new FunctionCallNode();
        IdentifierNode identifierNode = new IdentifierNode();
        identifierNode.setText("UnknownClass");
        node.setFunction(identifierNode);
        node.setHasNew(true);

        TypeCheckerVisitor visitor = new TypeCheckerVisitor(symbolTable);
        visitor.visit(node); // This should throw SymbolUnboundException
    }

    private static TypeCheckerVisitor getTypeCheckerVisitor() {
        SymbolTable symbolTable = new SymbolTable();
        symbolTable.createOuterSymbolTable();
        TypeCheckerVisitor visitor = new TypeCheckerVisitor(symbolTable);
        TypeCheckerVisitor spyVisitor = Mockito.spy(visitor);
        return spyVisitor;
    }

}
