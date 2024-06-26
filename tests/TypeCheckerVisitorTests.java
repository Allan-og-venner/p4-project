import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import nodes.*;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Stack;


public class TypeCheckerVisitorTests {
    private static TypeCheckerVisitor getTypeCheckerVisitor() {
        SymbolTable symbolTable = new SymbolTable();
        symbolTable.createOuterSymbolTable();
        TypeCheckerVisitor visitor = new TypeCheckerVisitor(symbolTable);
        TypeCheckerVisitor spyVisitor = Mockito.spy(visitor);
        return spyVisitor;
    }

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
    public void testMultiplicationNode(){
        TypeCheckerVisitor spyVisitor = getTypeCheckerVisitor();

        NumberNode leftNode = mock(NumberNode.class);
        NumberNode rightNode = mock(NumberNode.class);

        when(leftNode.getType()).thenReturn(new TypeNode("int"));
        when(rightNode.getType()).thenReturn(new TypeNode("int"));

        MultiplicationNode node = new MultiplicationNode();
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
    public void testMultiplicationNodeWithIntAndFloat(){
        TypeCheckerVisitor spyVisitor = getTypeCheckerVisitor();

        NumberNode leftNode = mock(NumberNode.class);
        NumberNode rightNode = mock(NumberNode.class);

        when(leftNode.getType()).thenReturn(new TypeNode("int"));
        when(rightNode.getType()).thenReturn(new TypeNode("float"));

        MultiplicationNode node = new MultiplicationNode();
        node.setLeft(leftNode);
        node.setRight(rightNode);

        //Use spy to simulate visit calls
        doReturn("int").when(spyVisitor).visit(leftNode);
        doReturn("float").when(spyVisitor).visit(rightNode);

        //Execute
        String result = spyVisitor.visit(node);

        //Verify
        verify(spyVisitor).visit(leftNode);
        verify(spyVisitor).visit(rightNode);
        assertEquals("float", result);
    }

    @Test(expected = WrongTypeException.class)
    public void testMultiplicationNodeError() {
        TypeCheckerVisitor spyVisitor = getTypeCheckerVisitor();

        NumberNode leftNode = mock(NumberNode.class);
        NumberNode rightNode = mock(NumberNode.class);

        when(leftNode.getType()).thenReturn(new TypeNode("int"));
        when(rightNode.getType()).thenReturn(new TypeNode("string"));

        MultiplicationNode node = new MultiplicationNode();
        node.setLeft(leftNode);
        node.setRight(rightNode);

        //Use spy to simulate visit calls
        doReturn("int").when(spyVisitor).visit(leftNode);
        doReturn("string").when(spyVisitor).visit(rightNode);

        //Execute
        spyVisitor.visit(node);
    }

    @Test
    public void testDivisionNode(){
        TypeCheckerVisitor spyVisitor = getTypeCheckerVisitor();

        NumberNode leftNode = mock(NumberNode.class);
        NumberNode rightNode = mock(NumberNode.class);

        when(leftNode.getType()).thenReturn(new TypeNode("int"));
        when(rightNode.getType()).thenReturn(new TypeNode("int"));

        DivisionNode node = new DivisionNode();
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
    public void testDivisionNodeWithIntAndFloat(){
        TypeCheckerVisitor spyVisitor = getTypeCheckerVisitor();

        NumberNode leftNode = mock(NumberNode.class);
        NumberNode rightNode = mock(NumberNode.class);

        when(leftNode.getType()).thenReturn(new TypeNode("int"));
        when(rightNode.getType()).thenReturn(new TypeNode("float"));

        DivisionNode node = new DivisionNode();
        node.setLeft(leftNode);
        node.setRight(rightNode);

        //Use spy to simulate visit calls
        doReturn("int").when(spyVisitor).visit(leftNode);
        doReturn("float").when(spyVisitor).visit(rightNode);

        //Execute
        String result = spyVisitor.visit(node);

        //Verify
        verify(spyVisitor).visit(leftNode);
        verify(spyVisitor).visit(rightNode);
        assertEquals("float", result);
    }

    @Test(expected = WrongTypeException.class)
    public void testDivisionNodeError() {
        TypeCheckerVisitor spyVisitor = getTypeCheckerVisitor();

        NumberNode leftNode = mock(NumberNode.class);
        NumberNode rightNode = mock(NumberNode.class);

        when(leftNode.getType()).thenReturn(new TypeNode("int"));
        when(rightNode.getType()).thenReturn(new TypeNode("string"));

        DivisionNode node = new DivisionNode();
        node.setLeft(leftNode);
        node.setRight(rightNode);

        //Use spy to simulate visit calls
        doReturn("int").when(spyVisitor).visit(leftNode);
        doReturn("string").when(spyVisitor).visit(rightNode);

        //Execute
        spyVisitor.visit(node);
    }

    @Test
    public void testNegateNode(){
        TypeCheckerVisitor spyVisitor = getTypeCheckerVisitor();

        NumberNode innerNode = mock(NumberNode.class);
        when(innerNode.getType()).thenReturn(new TypeNode("int"));

        NegateNode node = new NegateNode();
        node.setInnerNode(innerNode);

        //Use spy to simulate visit calls
        doReturn("int").when(spyVisitor).visit(innerNode);

        //Execute
        String result = spyVisitor.visit(node);

        //Verify
        verify(spyVisitor).visit(innerNode);
        assertEquals("int", result);
    }

    @Test(expected = WrongTypeException.class)
    public void testNegateNodeError() {
        TypeCheckerVisitor spyVisitor = getTypeCheckerVisitor();

        NumberNode innerNode = mock(NumberNode.class);
        when(innerNode.getType()).thenReturn(new TypeNode("string"));

        NegateNode node = new NegateNode();
        node.setInnerNode(innerNode);

        //Use spy to simulate visit calls
        doReturn("string").when(spyVisitor).visit(innerNode);

        //Execute
        spyVisitor.visit(node);
    }

    @Test
    public void testModNode(){
        TypeCheckerVisitor spyVisitor = getTypeCheckerVisitor();

        NumberNode leftNode = mock(NumberNode.class);
        NumberNode rightNode = mock(NumberNode.class);

        when(leftNode.getType()).thenReturn(new TypeNode("int"));
        when(rightNode.getType()).thenReturn(new TypeNode("int"));

        ModNode node = new ModNode();
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
    public void testNegativeNode(){
        TypeCheckerVisitor spyVisitor = getTypeCheckerVisitor();

        NumberNode innerNode = mock(NumberNode.class);
        when(innerNode.getType()).thenReturn(new TypeNode("int"));

        NegativeNode node = new NegativeNode();
        node.setInnerNode(innerNode);

        //Use spy to simulate visit calls
        doReturn("int").when(spyVisitor).visit(innerNode);

        //Execute
        String result = spyVisitor.visit(node);

        //Verify
        verify(spyVisitor).visit(innerNode);
        assertEquals("int", result);
    }

    @Test(expected = WrongTypeException.class)
    public void testNegativeNodeError(){
        TypeCheckerVisitor spyVisitor = getTypeCheckerVisitor();

        NumberNode innerNode = mock(NumberNode.class);
        when(innerNode.getType()).thenReturn(new TypeNode("string"));

        NegativeNode node = new NegativeNode();
        node.setInnerNode(innerNode);

        //Use spy to simulate visit calls
        doReturn("string").when(spyVisitor).visit(innerNode);

        //Execute
        spyVisitor.visit(node);
    }

    //test blocknode

    @Test
    public void testFloatNode(){
        TypeCheckerVisitor spyVisitor = getTypeCheckerVisitor();

        FloatNode node = mock(FloatNode.class);
        when(node.getType()).thenReturn(new TypeNode("float"));

        String result = spyVisitor.visit(node);

        assertEquals("float", result);
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
    public void testEQEQNode(){
        TypeCheckerVisitor spyVisitor = getTypeCheckerVisitor();

        NumberNode leftNode = mock(NumberNode.class);
        NumberNode rightNode = mock(NumberNode.class);

        when(leftNode.getType()).thenReturn(new TypeNode("int"));
        when(rightNode.getType()).thenReturn(new TypeNode("int"));

        EQEQNode node = new EQEQNode();
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

    @Test(expected = WrongTypeException.class)
    public void testEQEQNodeError(){
        TypeCheckerVisitor spyVisitor = getTypeCheckerVisitor();

        NumberNode leftNode = mock(NumberNode.class);
        NumberNode rightNode = mock(NumberNode.class);

        when(leftNode.getType()).thenReturn(new TypeNode("int"));
        when(rightNode.getType()).thenReturn(new TypeNode("string"));

        EQEQNode node = new EQEQNode();
        node.setLeft(leftNode);
        node.setRight(rightNode);

        //Use spy to simulate visit calls
        doReturn("int").when(spyVisitor).visit(leftNode);
        doReturn("string").when(spyVisitor).visit(rightNode);

        //Execute
        spyVisitor.visit(node);
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

    @Test
    public void testVisitFunctionCallNodeWithoutNewKeyword(){
        ExpressionsNode paramNode = mock(ExpressionsNode.class);
        FunctionCallNode node = mock(FunctionCallNode.class);
        IdentifierNode identifierNode = mock(IdentifierNode.class);

        when(node.getHasNew()).thenReturn(false);
        when(identifierNode.getText()).thenReturn("myFunction");
        when(node.getParameter()).thenReturn(paramNode);
        when(node.getFunction()).thenReturn(identifierNode);


        SymbolTable symbolTable = mock(SymbolTable.class);
        when(symbolTable.fLookup("myFunction")).thenReturn("void");

        TypeCheckerVisitor visitor = getTypeCheckerVisitor();
        visitor.getSymbolTables().peek().addFunction("myFunction", "void");
        doReturn(" ").when(visitor).visit(eq(paramNode), (ArrayList<String>) any(ArrayList.class));
        String result = visitor.visit(node);

        assertEquals("void", result);
    }

    @Test(expected = SymbolUnboundException.class)
    public void testVisitFunctionCallNodeWithoutNewKeywordError(){
        SymbolTable symbolTable = mock(SymbolTable.class);
        when(symbolTable.fLookup("myFunction")).thenReturn("null");
        FunctionCallNode node = new FunctionCallNode();
        IdentifierNode identifierNode = new IdentifierNode();
        identifierNode.setText("myFunction");
        node.setFunction(identifierNode);
        node.setHasNew(false);

        TypeCheckerVisitor visitor = new TypeCheckerVisitor(symbolTable);
        String result = visitor.visit(node);
    }

   // @Test
   /* public void testCardTypeFields() {
        CardTypeNode node = mock(CardTypeNode.class);
        DefineNode defineNode = mock(DefineNode.class);
        TypeNode typeNode = mock(TypeNode.class);
        IdentifierNode identifierNode = mock(IdentifierNode.class);
        ArrayList<DefineNode> defineNodes = new ArrayList<>(){{add(defineNode);}};
        SymbolTable symbolTable = spy(SymbolTable.class);

        when(node.getFields()).thenReturn(defineNodes);
        when(defineNode.getType()).thenReturn(typeNode);
        when(defineNode.getID()).thenReturn(identifierNode);
        when(typeNode.getTypeName()).thenReturn("int");
        when(identifierNode.getText()).thenReturn("value");



        TypeCheckerVisitor visitor = spy(TypeCheckerVisitor.class);

        String result = visitor.visit(node);

        assertEquals(visitor.getSymbolTables().peek().getCTable().get("Card").vLookup("value"), "int");

    }*/
    @Test
    public void testCardTypeFieldsError1() {
        CardTypeNode node = mock(CardTypeNode.class);
        DefineNode defineNode = mock(DefineNode.class);
        TypeNode typeNode = mock(TypeNode.class);
        IdentifierNode identifierNode = mock(IdentifierNode.class);
        ArrayList<DefineNode> defineNodes = new ArrayList<>(){{add(defineNode);}};
        SymbolTable symbolTable = spy(SymbolTable.class);

        when(node.getFields()).thenReturn(defineNodes);
        when(defineNode.getType()).thenReturn(typeNode);
        when(defineNode.getID()).thenReturn(identifierNode);
        when(typeNode.getTypeName()).thenReturn("beboop");
        when(identifierNode.getText()).thenReturn("value");



        TypeCheckerVisitor visitor = spy(TypeCheckerVisitor.class);

        try {
            visitor.visit(node);
            fail("Not throw");
        }catch (Exception e){
            assertThat(e, instanceOf(TypeNotFoundException.class));

        }
    }
    @Test
    public void testCardTypeFieldsError2() {
        CardTypeNode node = mock(CardTypeNode.class);
        DefineNode defineNode = mock(DefineNode.class);
        DefineNode defineNode2 = mock(DefineNode.class);
        TypeNode typeNode = mock(TypeNode.class);
        TypeNode typeNode2 = mock(TypeNode.class);
        IdentifierNode identifierNode = mock(IdentifierNode.class);
        ArrayList<DefineNode> defineNodes = new ArrayList<>(){{add(defineNode);add(defineNode2);}};

        when(node.getFields()).thenReturn(defineNodes);
        when(defineNode.getType()).thenReturn(typeNode);
        when(defineNode.getID()).thenReturn(identifierNode);
        when(defineNode2.getType()).thenReturn(typeNode2);
        when(defineNode2.getID()).thenReturn(identifierNode);
        when(typeNode.getTypeName()).thenReturn("int");
        when(typeNode2.getTypeName()).thenReturn("string");
        when(identifierNode.getText()).thenReturn("value");



        TypeCheckerVisitor visitor = spy(TypeCheckerVisitor.class);
        try {
            visitor.visit(node);
            fail("Not throw");
        }catch (Exception e){
            assertThat(e, instanceOf(WrongTypeException.class));

        }
    }
    @Test
    public void testCardTypeFieldsError3() {
        CardTypeNode node = mock(CardTypeNode.class);
        DefineNode defineNode = mock(DefineNode.class);
        DefineNode defineNode2 = mock(DefineNode.class);
        TypeNode typeNode = mock(TypeNode.class);
        IdentifierNode identifierNode = mock(IdentifierNode.class);
        ArrayList<DefineNode> defineNodes = new ArrayList<>(){{add(defineNode);add(defineNode2);}};

        when(node.getFields()).thenReturn(defineNodes);
        when(defineNode.getType()).thenReturn(typeNode);
        when(defineNode.getID()).thenReturn(identifierNode);
        when(defineNode2.getType()).thenReturn(typeNode);
        when(defineNode2.getID()).thenReturn(identifierNode);
        when(typeNode.getTypeName()).thenReturn("int");
        when(identifierNode.getText()).thenReturn("value");



        TypeCheckerVisitor visitor = spy(TypeCheckerVisitor.class);
        try {
            visitor.visit(node);
            fail("Not throw");
        }catch (Exception e){
            assertThat(e, instanceOf(DuplicateDefinitionException.class));

        }
    }
   /* @Test
    public void testCardTypeMethods(){
        CardTypeNode node = mock(CardTypeNode.class);
        FunctionDNode functionDNode = mock(FunctionDNode.class);
        IdentifierNode identifierNode = mock(IdentifierNode.class);
        ArrayList<FunctionDNode> functionDNodes = new ArrayList<>(){{add(functionDNode);}};
        FparamsNode fparamsNode = mock(FparamsNode.class);
        BlockNode blockNode = mock(BlockNode.class);
        TypeNode typeNode = mock(TypeNode.class);

        when(node.getMethods()).thenReturn(functionDNodes);
        when(functionDNode.getFunction()).thenReturn(identifierNode);
        when(identifierNode.getText()).thenReturn("drawCards");
        when(functionDNode.getParameter()).thenReturn(fparamsNode);
        when(functionDNode.getBlock()).thenReturn(blockNode);
        when(functionDNode.getReturnType()).thenReturn(typeNode);
        when(typeNode.getTypeName()).thenReturn("int");



        TypeCheckerVisitor visitor = spy(TypeCheckerVisitor.class);
        doReturn("int").when(visitor).visit(fparamsNode);
        doReturn("int").when(visitor).visit(blockNode);
        String result = visitor.visit(node);

        assertEquals("int,int", visitor.getSymbolTables().peek().getCTable().get("Card").fLookup("drawCards"));

    }*/
    @Test
    public void testCardTypeMethodsError1(){
        CardTypeNode node = mock(CardTypeNode.class);
        FunctionDNode functionDNode = mock(FunctionDNode.class);
        FunctionDNode functionDNode2 = mock(FunctionDNode.class);
        IdentifierNode identifierNode = mock(IdentifierNode.class);
        IdentifierNode identifierNode2 = mock(IdentifierNode.class);
        ArrayList<FunctionDNode> functionDNodes = new ArrayList<>(){{add(functionDNode); add(functionDNode2);}};
        FparamsNode fparamsNode = mock(FparamsNode.class);
        FparamsNode fparamsNode2 = mock(FparamsNode.class);
        BlockNode blockNode = mock(BlockNode.class);
        BlockNode blockNode2 = mock(BlockNode.class);
        TypeNode typeNode = mock(TypeNode.class);
        TypeNode typeNode2 = mock(TypeNode.class);

        when(node.getMethods()).thenReturn(functionDNodes);
        when(functionDNode.getFunction()).thenReturn(identifierNode);
        when(identifierNode.getText()).thenReturn("drawCards");
        when(functionDNode.getParameter()).thenReturn(fparamsNode);
        when(functionDNode.getBlock()).thenReturn(blockNode);
        when(functionDNode.getReturnType()).thenReturn(typeNode);
        when(typeNode.getTypeName()).thenReturn("int");

        when(functionDNode2.getFunction()).thenReturn(identifierNode2);
        when(identifierNode2.getText()).thenReturn("drawCards");
        when(functionDNode2.getParameter()).thenReturn(fparamsNode2);
        when(functionDNode2.getBlock()).thenReturn(blockNode2);
        when(functionDNode2.getReturnType()).thenReturn(typeNode2);
        when(typeNode2.getTypeName()).thenReturn("string");

        TypeCheckerVisitor visitor = spy(TypeCheckerVisitor.class);
        doReturn("").when(visitor).visit(fparamsNode);
        doReturn("int").when(visitor).visit(blockNode);
        doReturn("string").when(visitor).visit(fparamsNode2);
        doReturn("int").when(visitor).visit(blockNode2);

        //visi
        try {
            visitor.visit(node);
            fail("Not thrown");
        }catch (Exception e){
            assertThat(e, instanceOf(WrongTypeException.class));
        }


    }
    @Test
    public void testCardTypeMethodsError2(){
        CardTypeNode node = mock(CardTypeNode.class);
        FunctionDNode functionDNode = mock(FunctionDNode.class);
        FunctionDNode functionDNode2 = mock(FunctionDNode.class);
        IdentifierNode identifierNode = mock(IdentifierNode.class);
        IdentifierNode identifierNode2 = mock(IdentifierNode.class);
        ArrayList<FunctionDNode> functionDNodes = new ArrayList<>(){{add(functionDNode); add(functionDNode2);}};
        FparamsNode fparamsNode = mock(FparamsNode.class);
        FparamsNode fparamsNode2 = mock(FparamsNode.class);
        BlockNode blockNode = mock(BlockNode.class);
        BlockNode blockNode2 = mock(BlockNode.class);
        TypeNode typeNode = mock(TypeNode.class);
        TypeNode typeNode2 = mock(TypeNode.class);

        when(node.getMethods()).thenReturn(functionDNodes);
        when(functionDNode.getFunction()).thenReturn(identifierNode);
        when(identifierNode.getText()).thenReturn("drawCards");
        when(functionDNode.getParameter()).thenReturn(fparamsNode);
        when(functionDNode.getBlock()).thenReturn(blockNode);
        when(functionDNode.getReturnType()).thenReturn(typeNode);
        when(typeNode.getTypeName()).thenReturn("string");

        when(functionDNode2.getFunction()).thenReturn(identifierNode2);
        when(identifierNode2.getText()).thenReturn("drawCards");
        when(functionDNode2.getParameter()).thenReturn(fparamsNode2);
        when(functionDNode2.getBlock()).thenReturn(blockNode2);
        when(functionDNode2.getReturnType()).thenReturn(typeNode2);
        when(typeNode2.getTypeName()).thenReturn("string");

        TypeCheckerVisitor visitor = spy(TypeCheckerVisitor.class);
        doReturn("string").when(visitor).visit(fparamsNode);
        doReturn("string").when(visitor).visit(blockNode);
        doReturn("string").when(visitor).visit(fparamsNode2);
        doReturn("string").when(visitor).visit(blockNode2);

        //visi
        try {
            visitor.visit(node);
            fail("Not thrown");
        }catch (Exception e){
            assertThat(e, instanceOf(DuplicateDefinitionException.class));
        }


    }

    @Test
    public void testFornode(){
        ForNode forNode = spy(ForNode.class);
        NumberNode expressionNode = mock(NumberNode.class);
        BlockNode blockNode = mock(BlockNode.class);
        IdentifierNode identifierNode = mock(IdentifierNode.class);

        when(forNode.getArray()).thenReturn(expressionNode);
        when(forNode.getBlock()).thenReturn(blockNode);
        when(forNode.getIterator()).thenReturn(identifierNode);
        when(identifierNode.getText()).thenReturn("value");

        TypeCheckerVisitor visitor = spy(TypeCheckerVisitor.class);
        doReturn("array int").when(visitor).visit(expressionNode);
        doReturn("int").when(visitor).visit(blockNode);

        String result = visitor.visit(forNode);
        assertEquals("int", result);
        assertEquals("int", forNode.getType().getTypeName());
    }
    @Test
    public void testIfNode(){
        IfNode ifNode = spy(IfNode.class);
        LessThanNode expressionNode = mock(LessThanNode.class);
        BlockNode blockNode = mock(BlockNode.class);

        when(ifNode.getCondition()).thenReturn(expressionNode);
        when(ifNode.getBlock()).thenReturn(blockNode);

        TypeCheckerVisitor visitor = spy(TypeCheckerVisitor.class);
        doReturn("int").when(visitor).visit(expressionNode);
        doReturn("int").when(visitor).visit(blockNode);

        String result = visitor.visit(ifNode);
        assertEquals("int", result);
        assertEquals("int", ifNode.getType().getTypeName());

    }
    @Test
    public void testWhileNode(){
        WhileNode whileNode = spy(WhileNode.class);
        LessThanNode expressionNode = mock(LessThanNode.class);
        BlockNode blockNode = mock(BlockNode.class);

        when(whileNode.getCondition()).thenReturn(expressionNode);
        when(whileNode.getBlock()).thenReturn(blockNode);

        TypeCheckerVisitor visitor = spy(TypeCheckerVisitor.class);
        doReturn("int").when(visitor).visit(expressionNode);
        doReturn("int").when(visitor).visit(blockNode);

        String result = visitor.visit(whileNode);
        assertEquals("int", result);
        assertEquals("int", whileNode.getType().getTypeName());

    }

    @Test
    public void testArrayAccessNode(){
        ArrayAccessNode forNode = spy(ArrayAccessNode.class);
        NumberNode expressionNode = mock(NumberNode.class);

        when(forNode.getArray()).thenReturn(expressionNode);

        TypeCheckerVisitor visitor = spy(TypeCheckerVisitor.class);
        doReturn("array int").when(visitor).visit(expressionNode);

        String result = visitor.visit(forNode);
        assertEquals("int", result);
        assertEquals("int", forNode.getType().getTypeName());
    }

    @Test
    public void testArrayNode(){
        ArrayNode arrayNode = spy(ArrayNode.class);
        ExpressionsNode expressionsNode = mock(ExpressionsNode.class);

        when(arrayNode.getInnerNode()).thenReturn(expressionsNode);

        TypeCheckerVisitor visitor = spy(TypeCheckerVisitor.class);
        doReturn("int").when(visitor).visit(expressionsNode);

        String result = visitor.visit(arrayNode);
        assertEquals("array int", result);
        assertEquals("array int", arrayNode.getType().getTypeName());
    }

    @Test
    public void testIdentifierNodeClass(){
        IdentifierNode node = spy(IdentifierNode.class);

        when(node.getText()).thenReturn("Player");

        TypeCheckerVisitor visitor = spy(TypeCheckerVisitor.class);

        String result = visitor.visit(node);
        assertEquals("Class Player", result);
        assertEquals("Class Player", node.getType().getTypeName());

    }
    /*@Test
    public void testIdentifierNode(){
        IdentifierNode node = spy(IdentifierNode.class);

        when(node.getText()).thenReturn("value");

        TypeCheckerVisitor visitor = spy(TypeCheckerVisitor.class);
        visitor.getSymbolTables().peek().getVTable().put("value", "int");
        String result = visitor.visit(node);
        assertEquals("int", result);
        assertEquals("int", node.getType().getTypeName());

    }*/

    @Test
    public void testDefinenode(){
        DefineNode node = spy(DefineNode.class);
        TypeNode typeNode = mock(TypeNode.class);
        IdentifierNode identifierNode = mock(IdentifierNode.class);
        NumberNode expressionNode = mock(NumberNode.class);

        when(node.isArray()).thenReturn(false);
        when(node.getType()).thenReturn(typeNode);
        when(typeNode.getTypeName()).thenReturn("int");
        when(node.getID()).thenReturn(identifierNode);
        when(identifierNode.getText()).thenReturn("value");
        when(node.getValue()).thenReturn(expressionNode);

        TypeCheckerVisitor visitor = spy(TypeCheckerVisitor.class);
        doReturn("int").when(visitor).visit(expressionNode);

        String result = visitor.visit(node);

        assertEquals("int", result);
        assertEquals("int", node.getType().getTypeName());


    }
    @Test
    public void testDefinenodeWithTable(){
        DefineNode node = spy(DefineNode.class);
        TypeNode typeNode = mock(TypeNode.class);
        IdentifierNode identifierNode = mock(IdentifierNode.class);
        SymbolTable symbolTable = spy(SymbolTable.class);
        symbolTable.createOuterSymbolTable();
        ModifierNode modifierNode = mock(ModifierNode.class);

        when(node.isArray()).thenReturn(false);
        when(node.getType()).thenReturn(typeNode);
        when(typeNode.getTypeName()).thenReturn("int");
        when(node.getID()).thenReturn(identifierNode);
        when(identifierNode.getText()).thenReturn("value");
        when(node.getModi()).thenReturn(modifierNode);

        TypeCheckerVisitor visitor = spy(TypeCheckerVisitor.class);
        doReturn("static ").when(visitor).visit(modifierNode);

        String result = visitor.visit(node, symbolTable);

        assertEquals("void", result);
        assertEquals("int", node.getType().getTypeName());
        assertEquals("int", symbolTable.vLookup("static value"));



    }

    @Test(expected = IllegalTypeException.class)
    public void testDefinenodeError1(){
        DefineNode node = spy(DefineNode.class);
        TypeNode typeNode = mock(TypeNode.class);
        IdentifierNode identifierNode = mock(IdentifierNode.class);
        NumberNode expressionNode = mock(NumberNode.class);

        when(node.isArray()).thenReturn(false);
        when(node.getType()).thenReturn(typeNode);
        when(typeNode.getTypeName()).thenReturn("void");
        when(node.getID()).thenReturn(identifierNode);
        when(identifierNode.getText()).thenReturn("value");
        when(node.getValue()).thenReturn(expressionNode);

        TypeCheckerVisitor visitor = spy(TypeCheckerVisitor.class);
        doReturn("int").when(visitor).visit(expressionNode);


        visitor.visit(node);




    }

   /* @Test(expected = DuplicateDefinitionException.class)
    public void testDefinenodeError2(){
        DefineNode node = spy(DefineNode.class);
        TypeNode typeNode = mock(TypeNode.class);
        IdentifierNode identifierNode = mock(IdentifierNode.class);
        NumberNode expressionNode = mock(NumberNode.class);

        when(node.isArray()).thenReturn(false);
        when(node.getType()).thenReturn(typeNode);
        when(typeNode.getTypeName()).thenReturn("int");
        when(node.getID()).thenReturn(identifierNode);
        when(identifierNode.getText()).thenReturn("value");
        when(node.getValue()).thenReturn(expressionNode);

        TypeCheckerVisitor visitor = spy(TypeCheckerVisitor.class);
        doReturn("int").when(visitor).visit(expressionNode);
        visitor.getSymbolTables().peek().addValue("value", "int");


        visitor.visit(node);




    }*/

    @Test(expected = WrongTypeException.class)
    public void testDefinenodeError3(){
        DefineNode node = spy(DefineNode.class);
        TypeNode typeNode = mock(TypeNode.class);
        IdentifierNode identifierNode = mock(IdentifierNode.class);
        NumberNode expressionNode = mock(NumberNode.class);

        when(node.isArray()).thenReturn(false);
        when(node.getType()).thenReturn(typeNode);
        when(typeNode.getTypeName()).thenReturn("sss");
        when(node.getID()).thenReturn(identifierNode);
        when(identifierNode.getText()).thenReturn("value");
        when(node.getValue()).thenReturn(expressionNode);

        TypeCheckerVisitor visitor = spy(TypeCheckerVisitor.class);
        doReturn("int").when(visitor).visit(expressionNode);


        visitor.visit(node);




    }

    @Test(expected = DuplicateDefinitionException.class)
    public void testDefinenodeError4WithTable(){
        DefineNode node = spy(DefineNode.class);
        TypeNode typeNode = mock(TypeNode.class);
        IdentifierNode identifierNode = mock(IdentifierNode.class);
        NumberNode expressionNode = mock(NumberNode.class);
        SymbolTable symbolTable = spy(SymbolTable.class);

        when(node.isArray()).thenReturn(false);
        when(node.getType()).thenReturn(typeNode);
        when(typeNode.getTypeName()).thenReturn("int");
        when(node.getID()).thenReturn(identifierNode);
        when(identifierNode.getText()).thenReturn("value");
        when(node.getValue()).thenReturn(expressionNode);

        TypeCheckerVisitor visitor = spy(TypeCheckerVisitor.class);
        doReturn("int").when(visitor).visit(expressionNode);
        doReturn(true).when(symbolTable).checkInnerV(any(String.class));
        //visitor.getSymbolTables().peek().addValue("value", "int");


        visitor.visit(node, symbolTable);




    }

   /* @Test
    public void testClassD(){
        ClassDNode node = spy(ClassDNode.class);
        IdentifierNode identifierNode = mock(IdentifierNode.class);
        IdentifierNode identifierNode2 = mock(IdentifierNode.class);
        BlockNode blockNode = mock(BlockNode.class);
        SymbolTable symbolTable = spy(SymbolTable.class);

        when(node.getName()).thenReturn(identifierNode);
        when(node.getSuperClass()).thenReturn(identifierNode2);
        when(identifierNode.getText()).thenReturn("Dog");
        when(identifierNode2.getText()).thenReturn("Animal");
        when(node.getBlock()).thenReturn(blockNode);

        TypeCheckerVisitor visitor = spy(TypeCheckerVisitor.class);
        visitor.getSymbolTables().peek().addClass("Animal","Object");
        visitor.getSymbolTables().peek().addClassSymbols("Animal",new SymbolTable());
        doReturn("").when(visitor).visit(eq(blockNode), any(SymbolTable.class));
        visitor.visit(node);

        assertNotNull(visitor.getSymbolTables().peek().cLookup("Dog"));

    }
    @Test(expected = IllegalTypeException.class)
    public void testClassDError1(){
        ClassDNode node = spy(ClassDNode.class);
        IdentifierNode identifierNode = mock(IdentifierNode.class);
        IdentifierNode identifierNode2 = mock(IdentifierNode.class);
        BlockNode blockNode = mock(BlockNode.class);
        SymbolTable symbolTable = spy(SymbolTable.class);

        when(node.getName()).thenReturn(identifierNode);
        when(node.getSuperClass()).thenReturn(identifierNode2);
        when(identifierNode.getText()).thenReturn("Card");
        when(identifierNode2.getText()).thenReturn("Animal");
        when(node.getBlock()).thenReturn(blockNode);

        TypeCheckerVisitor visitor = spy(TypeCheckerVisitor.class);
        visitor.getSymbolTables().peek().addClass("Animal","Object");
        visitor.getSymbolTables().peek().addClassSymbols("Animal",new SymbolTable());
        doReturn("").when(visitor).visit(eq(blockNode), any(SymbolTable.class));
        visitor.visit(node);


    }
    @Test(expected = DuplicateDefinitionException.class)
    public void testClassDError2(){
        ClassDNode node = spy(ClassDNode.class);
        IdentifierNode identifierNode = mock(IdentifierNode.class);
        IdentifierNode identifierNode2 = mock(IdentifierNode.class);
        BlockNode blockNode = mock(BlockNode.class);
        SymbolTable symbolTable = spy(SymbolTable.class);

        when(node.getName()).thenReturn(identifierNode);
        when(node.getSuperClass()).thenReturn(identifierNode2);
        when(identifierNode.getText()).thenReturn("Animal");
        when(identifierNode2.getText()).thenReturn("Animal");
        when(node.getBlock()).thenReturn(blockNode);

        TypeCheckerVisitor visitor = spy(TypeCheckerVisitor.class);
        visitor.getSymbolTables().peek().addClass("Animal","Object");
        visitor.getSymbolTables().peek().addClassSymbols("Animal",new SymbolTable());
        doReturn("").when(visitor).visit(eq(blockNode), any(SymbolTable.class));
        visitor.visit(node);


    }
    @Test(expected = SymbolUnboundException.class)
    public void testClassDError3(){
        ClassDNode node = spy(ClassDNode.class);
        IdentifierNode identifierNode = mock(IdentifierNode.class);
        IdentifierNode identifierNode2 = mock(IdentifierNode.class);
        BlockNode blockNode = mock(BlockNode.class);
        SymbolTable symbolTable = spy(SymbolTable.class);

        when(node.getName()).thenReturn(identifierNode);
        when(node.getSuperClass()).thenReturn(identifierNode2);
        when(identifierNode.getText()).thenReturn("Dog");
        when(identifierNode2.getText()).thenReturn("");
        when(node.getBlock()).thenReturn(blockNode);

        TypeCheckerVisitor visitor = spy(TypeCheckerVisitor.class);
        visitor.getSymbolTables().peek().addClass("Animal","Object");
        visitor.getSymbolTables().peek().addClassSymbols("Animal",new SymbolTable());
        doReturn("").when(visitor).visit(eq(blockNode), any(SymbolTable.class));
        visitor.visit(node);


    }*/

    @Test
    public void testClassAccessClassStatic(){
        //Test the type of the method being accessed
        ClassAccessNode node = spy(ClassAccessNode.class);
        ValueNode valueNode = mock(ValueNode.class);
        IdentifierNode identifierNode = mock(IdentifierNode.class);
        IdentifierNode funcIdenti = mock(IdentifierNode.class);
        FunctionCallNode functionCallNode = mock(FunctionCallNode.class);
        ArrayAccessNode arrayAccessNode = mock(ArrayAccessNode.class);
        ArrayList<ValueNode> valueNodes = new ArrayList<>(){{add(functionCallNode);}};
        SymbolTable testTable = spy(SymbolTable.class);
        testTable.createOuterSymbolTable();

        when(node.getObject()).thenReturn(valueNode);
        when(node.getValue()).thenReturn(valueNodes);
        when(functionCallNode.getFunction()).thenReturn(funcIdenti);
        when(funcIdenti.getText()).thenReturn("Bark");
        when(identifierNode.getText()).thenReturn("Køn");

        TypeCheckerVisitor visitor = spy(TypeCheckerVisitor.class);
        visitor.getSymbolTables().peek().addClassSymbols("Dog", testTable);

        visitor.getSymbolTables().peek().findClassSymbolTable("Dog").addFunction("static Bark","void");
        //visitor.getSymbolTables().peek().findClassSymbolTable("Dog").addValue("static Køn","string");
        doReturn("Class Dog").when(visitor).visit(valueNode);

        String result = visitor.visit(node);

        assertEquals("void",result);
        assertEquals("void", node.getType().getTypeName());

    }
    @Test
    public void testClassAccessClassStatic2(){
        //Test the type of the identifier of the method
        ClassAccessNode node = spy(ClassAccessNode.class);
        ValueNode valueNode = mock(ValueNode.class);
        IdentifierNode identifierNode = mock(IdentifierNode.class);
        IdentifierNode funcIdenti = mock(IdentifierNode.class);
        FunctionCallNode functionCallNode = mock(FunctionCallNode.class);
        ArrayAccessNode arrayAccessNode = mock(ArrayAccessNode.class);
        ArrayList<ValueNode> valueNodes = new ArrayList<>(){{add(identifierNode);}};
        SymbolTable testTable = spy(SymbolTable.class);
        testTable.createOuterSymbolTable();

        when(node.getObject()).thenReturn(valueNode);
        when(node.getValue()).thenReturn(valueNodes);
        when(functionCallNode.getFunction()).thenReturn(funcIdenti);
        when(funcIdenti.getText()).thenReturn("Bark");
        when(identifierNode.getText()).thenReturn("Køn");

        TypeCheckerVisitor visitor = spy(TypeCheckerVisitor.class);
        visitor.getSymbolTables().peek().addClassSymbols("Dog", testTable);

        //visitor.getSymbolTables().peek().findClassSymbolTable("Dog").addFunction("static Bark","void");
        visitor.getSymbolTables().peek().findClassSymbolTable("Dog").addValue("static Køn","string");
        doReturn("Class Dog").when(visitor).visit(valueNode);

        String result = visitor.visit(node);

        assertEquals("string",result);
        assertEquals("string", node.getType().getTypeName());

    }
    @Test
    public void testClassAccessClassMethod(){
        //Test the type of the identifier of the method without static modifier
        ClassAccessNode node = spy(ClassAccessNode.class);
        ValueNode valueNode = mock(ValueNode.class);
        IdentifierNode identifierNode = mock(IdentifierNode.class);
        IdentifierNode funcIdenti = mock(IdentifierNode.class);
        FunctionCallNode functionCallNode = mock(FunctionCallNode.class);
        ArrayAccessNode arrayAccessNode = mock(ArrayAccessNode.class);
        ArrayList<ValueNode> valueNodes = new ArrayList<>(){{add(functionCallNode);}};
        SymbolTable testTable = spy(SymbolTable.class);
        testTable.createOuterSymbolTable();
        ExpressionsNode paramNode = mock(ExpressionsNode.class);

        when(node.getObject()).thenReturn(valueNode);
        when(node.getValue()).thenReturn(valueNodes);
        when(functionCallNode.getFunction()).thenReturn(funcIdenti);
        when(funcIdenti.getText()).thenReturn("Bark");
        when(identifierNode.getText()).thenReturn("Køn");
        when(functionCallNode.getParameter()).thenReturn(paramNode);

        TypeCheckerVisitor visitor = spy(TypeCheckerVisitor.class);
        visitor.getSymbolTables().peek().addClass("Dog");
        visitor.getSymbolTables().peek().addClassSymbols("Dog", testTable);

        visitor.getSymbolTables().peek().findClassSymbolTable("Dog").addFunction("Bark","void,");
        //visitor.getSymbolTables().peek().findClassSymbolTable("Dog").addValue("Køn","string");
        doReturn("Dog").when(visitor).visit(valueNode);
        doReturn(" ").when(visitor).visit((ExpressionsNode) eq(paramNode), (ArrayList<String>) any(ArrayList.class));

        String result = visitor.visit(node);

        assertEquals("void",result);
        assertEquals("void", node.getType().getTypeName());

    }
    @Test
    public void testClassAccessClassField(){
        //Test the type of the identifier without static modifier
        ClassAccessNode node = spy(ClassAccessNode.class);
        ValueNode valueNode = mock(ValueNode.class);
        IdentifierNode identifierNode = mock(IdentifierNode.class);
        IdentifierNode funcIdenti = mock(IdentifierNode.class);
        FunctionCallNode functionCallNode = mock(FunctionCallNode.class);
        ArrayAccessNode arrayAccessNode = mock(ArrayAccessNode.class);
        ArrayList<ValueNode> valueNodes = new ArrayList<>(){{add(identifierNode);}};
        SymbolTable testTable = spy(SymbolTable.class);
        testTable.createOuterSymbolTable();

        when(node.getObject()).thenReturn(valueNode);
        when(node.getValue()).thenReturn(valueNodes);
        when(functionCallNode.getFunction()).thenReturn(funcIdenti);
        when(funcIdenti.getText()).thenReturn("Bark");
        when(identifierNode.getText()).thenReturn("Køn");

        TypeCheckerVisitor visitor = spy(TypeCheckerVisitor.class);
        visitor.getSymbolTables().peek().addClass("Dog");
        visitor.getSymbolTables().peek().addClassSymbols("Dog", testTable);

        //visitor.getSymbolTables().peek().findClassSymbolTable("Dog").addFunction("Bark","void,");
        visitor.getSymbolTables().peek().findClassSymbolTable("Dog").addValue("Køn","string");
        doReturn("Dog").when(visitor).visit(valueNode);

        String result = visitor.visit(node);

        assertEquals("string",result);
        assertEquals("string", node.getType().getTypeName());

    }
    @Test
    public void testClassAccessClassArray(){
        ClassAccessNode node = spy(ClassAccessNode.class);
        ValueNode valueNode = mock(ValueNode.class);
        IdentifierNode identifierNode = mock(IdentifierNode.class);
        IdentifierNode funcIdenti = mock(IdentifierNode.class);
        FunctionCallNode functionCallNode = mock(FunctionCallNode.class);
        ArrayAccessNode arrayAccessNode = mock(ArrayAccessNode.class);
        ArrayList<ValueNode> valueNodes = new ArrayList<>(){{add(arrayAccessNode);}};
        SymbolTable testTable = spy(SymbolTable.class);
        testTable.createOuterSymbolTable();

        when(node.getObject()).thenReturn(valueNode);
        when(node.getValue()).thenReturn(valueNodes);
        when(functionCallNode.getFunction()).thenReturn(funcIdenti);
        when(funcIdenti.getText()).thenReturn("Bark");
        when(identifierNode.getText()).thenReturn("Køn");

        TypeCheckerVisitor visitor = spy(TypeCheckerVisitor.class);
        visitor.getSymbolTables().peek().addClass("Dog");
        visitor.getSymbolTables().peek().addClassSymbols("Dog", testTable);

        //visitor.getSymbolTables().peek().findClassSymbolTable("Dog").addFunction("Bark","void,");
        //visitor.getSymbolTables().peek().findClassSymbolTable("Dog").addValue("Køn","string");
        doReturn("Dog").when(visitor).visit(valueNode);
        doReturn("int").when(visitor).visit(arrayAccessNode);
        String result = visitor.visit(node);

        assertEquals("int",result);
        assertEquals("int", node.getType().getTypeName());

    }
    @Test(expected = TypeNotClassException.class)
    public void testClassAccessWithStaticError(){
        //Test the type of the method being accessed
        ClassAccessNode node = spy(ClassAccessNode.class);
        ValueNode valueNode = mock(ValueNode.class);
        IdentifierNode identifierNode = mock(IdentifierNode.class);
        IdentifierNode funcIdenti = mock(IdentifierNode.class);
        FunctionCallNode functionCallNode = mock(FunctionCallNode.class);
        ArrayAccessNode arrayAccessNode = mock(ArrayAccessNode.class);
        ArrayList<ValueNode> valueNodes = new ArrayList<>(){{add(functionCallNode);}};
        SymbolTable testTable = spy(SymbolTable.class);
        testTable.createOuterSymbolTable();

        when(node.getObject()).thenReturn(valueNode);
        when(node.getValue()).thenReturn(valueNodes);
        when(functionCallNode.getFunction()).thenReturn(funcIdenti);
        when(funcIdenti.getText()).thenReturn("Bark");
        when(identifierNode.getText()).thenReturn("Køn");

        TypeCheckerVisitor visitor = spy(TypeCheckerVisitor.class);

        doReturn("Class Dog").when(visitor).visit(valueNode);

        String result = visitor.visit(node);
    }

    @Test(expected = NoStaticFieldException.class)
    public void testClassAccessClassStaticError(){
        ClassAccessNode node = spy(ClassAccessNode.class);
        ValueNode valueNode = mock(ValueNode.class);
        IdentifierNode identifierNode = mock(IdentifierNode.class);
        IdentifierNode funcIdenti = mock(IdentifierNode.class);
        FunctionCallNode functionCallNode = mock(FunctionCallNode.class);
        ArrayList<ValueNode> valueNodes = new ArrayList<>(){{add(identifierNode);}};
        SymbolTable testTable = spy(SymbolTable.class);
        testTable.createOuterSymbolTable();

        when(node.getObject()).thenReturn(valueNode);
        when(node.getValue()).thenReturn(valueNodes);
        when(functionCallNode.getFunction()).thenReturn(funcIdenti);

        TypeCheckerVisitor visitor = spy(TypeCheckerVisitor.class);
        visitor.getSymbolTables().peek().addClassSymbols("Dog", testTable);

        //visitor.getSymbolTables().peek().findClassSymbolTable("Dog").addFunction("static Bark","void");
        visitor.getSymbolTables().peek().findClassSymbolTable("Dog").addValue("static Køn","string");
        doReturn("Class Dog").when(visitor).visit(valueNode);

        String result = visitor.visit(node);

    }

    @Test(expected = SymbolUnboundException.class)
    public void testClassAccessClassMethodError(){

        ClassAccessNode node = spy(ClassAccessNode.class);
        ValueNode valueNode = mock(ValueNode.class);
        IdentifierNode identifierNode = mock(IdentifierNode.class);
        IdentifierNode funcIdenti = mock(IdentifierNode.class);
        FunctionCallNode functionCallNode = mock(FunctionCallNode.class);
        ArrayList<ValueNode> valueNodes = new ArrayList<>(){{add(functionCallNode);}};
        SymbolTable testTable = spy(SymbolTable.class);
        testTable.createOuterSymbolTable();

        when(node.getObject()).thenReturn(valueNode);
        when(node.getValue()).thenReturn(valueNodes);
        when(functionCallNode.getFunction()).thenReturn(funcIdenti);
        when(funcIdenti.getText()).thenReturn("Bark");
        when(identifierNode.getText()).thenReturn("Køn");

        TypeCheckerVisitor visitor = spy(TypeCheckerVisitor.class);
        visitor.getSymbolTables().peek().addClass("Dog");
        visitor.getSymbolTables().peek().addClassSymbols("Dog", testTable);

        //visitor.getSymbolTables().peek().findClassSymbolTable("Dog").addFunction("Bark","void,");
        visitor.getSymbolTables().peek().findClassSymbolTable("Dog").addValue("Køn","string");
        doReturn("Dog").when(visitor).visit(valueNode);

        String result = visitor.visit(node);

    }

    @Test(expected = WrongTypeException.class)
    public void testClassAccessClassTypeWrongError(){
        ClassAccessNode node = spy(ClassAccessNode.class);
        ValueNode valueNode = mock(ValueNode.class);
        IdentifierNode identifierNode = mock(IdentifierNode.class);
        IdentifierNode funcIdenti = mock(IdentifierNode.class);
        FunctionCallNode functionCallNode = mock(FunctionCallNode.class);
        ArrayAccessNode arrayAccessNode = mock(ArrayAccessNode.class);
        ArrayList<ValueNode> valueNodes = new ArrayList<>(){{add(identifierNode);}};
        SymbolTable testTable = spy(SymbolTable.class);
        testTable.createOuterSymbolTable();

        when(node.getObject()).thenReturn(valueNode);
        when(node.getValue()).thenReturn(valueNodes);
        when(functionCallNode.getFunction()).thenReturn(funcIdenti);
        when(funcIdenti.getText()).thenReturn("Bark");
        when(identifierNode.getText()).thenReturn("Køn");

        TypeCheckerVisitor visitor = spy(TypeCheckerVisitor.class);
        //visitor.getSymbolTables().peek().addClass("Dog");
        visitor.getSymbolTables().peek().addClassSymbols("Dog", testTable);

        //visitor.getSymbolTables().peek().findClassSymbolTable("Dog").addFunction("Bark","void,");
        visitor.getSymbolTables().peek().findClassSymbolTable("Dog").addValue("Køn","string");
        doReturn("Dog").when(visitor).visit(valueNode);

        String result = visitor.visit(node);
    }

    @Test(expected = TypeNotClassException.class)
    public void testClassAccessWithStaticError2(){
        ClassAccessNode node = spy(ClassAccessNode.class);
        ValueNode valueNode = mock(ValueNode.class);
        IdentifierNode identifierNode = mock(IdentifierNode.class);
        IdentifierNode funcIdenti = mock(IdentifierNode.class);
        FunctionCallNode functionCallNode = mock(FunctionCallNode.class);
        ArrayAccessNode arrayAccessNode = mock(ArrayAccessNode.class);
        ArrayList<ValueNode> valueNodes = new ArrayList<>(){{add(functionCallNode);}};
        SymbolTable testTable = spy(SymbolTable.class);
        testTable.createOuterSymbolTable();

        when(node.getObject()).thenReturn(valueNode);
        when(node.getValue()).thenReturn(valueNodes);
        when(functionCallNode.getFunction()).thenReturn(funcIdenti);
        when(funcIdenti.getText()).thenReturn("Bark");
        when(identifierNode.getText()).thenReturn("Køn");

        TypeCheckerVisitor visitor = spy(TypeCheckerVisitor.class);
        visitor.getSymbolTables().peek().addClass("Dog");
        //visitor.getSymbolTables().peek().addClassSymbols("Dog", testTable);

        //visitor.getSymbolTables().peek().findClassSymbolTable("Dog").addFunction("Bark","void,");
        //visitor.getSymbolTables().peek().findClassSymbolTable("Dog").addValue("Køn","string");
        doReturn("Dog").when(visitor).visit(valueNode);

        String result = visitor.visit(node);
    }

    @Test(expected = SymbolUnboundException.class)
    public void testClassAccessFunctionReturnNull(){
        ClassAccessNode node = spy(ClassAccessNode.class);
        ValueNode valueNode = mock(ValueNode.class);
        IdentifierNode identifierNode = mock(IdentifierNode.class);
        IdentifierNode funcIdenti = mock(IdentifierNode.class);
        FunctionCallNode functionCallNode = mock(FunctionCallNode.class);

        ArrayList<ValueNode> valueNodes = new ArrayList<>(){{add(identifierNode);}};
        SymbolTable testTable = spy(SymbolTable.class);
        testTable.createOuterSymbolTable();

        when(node.getObject()).thenReturn(valueNode);
        when(node.getValue()).thenReturn(valueNodes);
        when(functionCallNode.getFunction()).thenReturn(funcIdenti);
        when(funcIdenti.getText()).thenReturn("Bark");
        when(identifierNode.getText()).thenReturn("Køn");

        TypeCheckerVisitor visitor = spy(TypeCheckerVisitor.class);
        visitor.getSymbolTables().peek().addClass("Dog");
        visitor.getSymbolTables().peek().addClassSymbols("Dog", testTable);

        //visitor.getSymbolTables().peek().findClassSymbolTable("Dog").addFunction("Bark",",");
        //visitor.getSymbolTables().peek().findClassSymbolTable("Dog").addValue("Køn","null");
        doReturn("Dog").when(visitor).visit(valueNode);

        String result = visitor.visit(node);
    }

    @Test
    public void testVisitExpressionsNode(){
        ExpressionsNode node = spy(ExpressionsNode.class);
        NumberNode numberNode = mock(NumberNode.class);

        when(node.getLeft()).thenReturn(numberNode);
        when(node.getRight()).thenReturn(null);

        TypeCheckerVisitor visitor = spy(TypeCheckerVisitor.class);
        doReturn("int").when(visitor).visit(numberNode);

        String result = visitor.visit(node);

        assertEquals("int", result);
    }

    @Test(expected = WrongTypeException.class)
    public void testVisitExpressionsNodeError(){
        ExpressionsNode node = spy(ExpressionsNode.class);
        NumberNode numberNode = mock(NumberNode.class);
        ArrayList<String> stringArrayList = new ArrayList<>(){{add("int");}};

        when(node.getLeft()).thenReturn(numberNode);
        when(node.getRight()).thenReturn(null);

        when(node.getLeft()).thenReturn(numberNode);
        when(node.getRight()).thenReturn(null);

        TypeCheckerVisitor visitor = spy(TypeCheckerVisitor.class);

        String result = visitor.visit(node, stringArrayList);
    }

    @Test
    public void testVisitExpressionsNodeWithRightNode(){
        ExpressionsNode node = spy(ExpressionsNode.class);
        NumberNode numberNode = mock(NumberNode.class);
        ExpressionsNode expressionsNode = mock(ExpressionsNode.class);
        ArrayList<String> stringArrayList = new ArrayList<>(){{add("int"); add("int");}};

        when(node.getRight()).thenReturn(expressionsNode);
        when(node.getLeft()).thenReturn(numberNode);

        when(node.getRight()).thenReturn(expressionsNode);
        when(node.getLeft()).thenReturn(numberNode);
        TypeCheckerVisitor visitor = spy(TypeCheckerVisitor.class);
        doReturn("int").when(visitor).visit(numberNode);
        doReturn("int").when(visitor).visit(expressionsNode, stringArrayList);

        String result = visitor.visit(node, stringArrayList);

        assertEquals("int", result);
        assertEquals("int", node.getType().getTypeName());
    }







}
