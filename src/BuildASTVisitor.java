import java.util.ArrayList;
import java.util.List;

import gen.*;
import nodes.*;

public class BuildASTVisitor extends ExprBaseVisitor<BlockNode> {

    // Save the visited nodes in a list to use for testing purposes
    private static List<String> visitedNodes = new ArrayList<>();
    // Method to add nodes to the list
    public void addVisitedNode(String node) {
        visitedNodes.add(node);
    }
    // Getter for the list
    public static List<String> getVisitedNodes() {
        return visitedNodes;
    }

    // Print all nodes for debugging
    public static void printVisitedNodes() {
        for (String node : visitedNodes) {
            System.out.println(node);
        }
    }

    @Override
    public BlockNode visitProg(ExprParser.ProgContext context) {
        addVisitedNode("Visited prog node");
        return visitBlock(context.block());
    }

    @Override
    public BlockNode visitBlock(ExprParser.BlockContext context) {
        addVisitedNode("Visited block node");
        BlockNode node = new BlockNode();

        node.getLineNumberFromContext(context);
        // Check if the Block node contains a statement node
        if (context.statement() != null) {
            // If there is a statement, visit it and set the statement in the BlockNode
            node.setStatement(visitStatement(context.statement()));

            // Check if there is a nested block within the current Block node
            if (context.block() != null) {
                // If there is a nested block, visit it and set it as part of the BlockNode
                node.setBlocks(visitBlock(context.block()));
            }
        } else {
            // If there is no statement or block in this context, throw an exception to signal unsupported operation
            throw new UnsupportedOperationException(
                context.getStart().getLine() + " Operation not supported (block node)"
            );
        }
        return node;
    }

    @Override
    public StatementNode visitStatement(ExprParser.StatementContext context) {
        addVisitedNode("Visited statement node");

        // Check if the statement contains a declaration
        if (context.decl() != null) {
            // Visit the declaration node and associate it with a DeclarationNode instance
            DeclarationNode node = visitDecl(context.decl());
            node.getLineNumberFromContext(context);
            // Return the processed DeclarationNode
            return node;
        // Check if the statement contains a function call
        } else if (context.call() != null) {
            // Visit the function call node, cast to FunctionCallNode, and associate it with a FunctionCallNode instance
            FunctionCallNode node = (FunctionCallNode) visitCall(context.call());
            node.getLineNumberFromContext(context);
            // Return the processed FunctionCallNode
            return node;
        // Check if the statement contains a method call
        } else if (context.method() != null) {
            // Visit the method call node and associate it with a ClassAccessNode instance
            ClassAccessNode node = visitMethod(context.method());
            node.getLineNumberFromContext(context);
            // Return the processed ClassAccessNode
            return node;
        // Check if the statement contains an assignment
        } else if (context.assign() != null) {
            // Visit the assignment node and associate it with an AssignmentNode instance
            AssignmentNode node = visitAssign(context.assign());
            node.getLineNumberFromContext(context);
            // Return the processed AssignmentNode
            return node;
        // Check if the statement contains a command
        } else if (context.command() != null) {
            // Visit the command node and associate it with a CommandNode instance
            CommandNode node = visitCommand(context.command());
            node.getLineNumberFromContext(context);
            // Return the processed CommandNode
            return node;
        // Check if the statement contains control flow statements (if, loop while and loop for)
        } else if (context.control() != null) {
            // Visit the control flow node and associate it with a ControlNode instance
            ControlNode node = visitControl(context.control());
            node.getLineNumberFromContext(context);
            // Return the processed ControlNode
            return node;
        } else {
            // If no valid statement type is found, throw an exception to signal unsupported operation
            throw new UnsupportedOperationException(
                context.getStart().getLine() + " Operation not supported (statement node)"
            );
        }
    }

    @Override
    public ControlNode visitControl(ExprParser.ControlContext context) {
        addVisitedNode("Visited control node");

        // Check if the control structure is a loop (for, while)
        if (context.loop() != null) {
            // Visit the loop node and associate it with a LoopNode instance
            LoopNode node = visitLoop(context.loop());
            node.getLineNumberFromContext(context);
            // Return the processed LoopNode
            return node;
        // Check if the control structure is an if-then conditional statement
        } else if (context.ifthen() != null) {
            // Visit the if-then node and associate it with an IfNode instance
            IfNode node = visitIfthen(context.ifthen());
            node.getLineNumberFromContext(context);
            // Return the processed IfNode
            return node;
        } else {
            // If no valid control structure type is found, throw an exception to signal unsupported operation
            throw new UnsupportedOperationException(
                context.getStart().getLine() + " Operation not supported (control node)"
            );
        }
    }

    @Override
    public IfNode visitIfthen(ExprParser.IfthenContext context) {
        addVisitedNode("Visited if-then node");
        IfNode node = new IfNode();

        // Check if the if-then statement contains a condition expression
        if (context.expr() != null) {
            // Visit the expression node and set it as the condition of the IfNode
            node.setCondition(visitExpr(context.expr()));

            // Visit the associated block node and set it as the block of the IfNode
            node.setBlock(visitBlock(context.block()));
            node.getLineNumberFromContext(context);
            // Return the constructed IfNode object representing the if-then statement
            return node;
        } else {
            // If no valid condition expression is found, throw an exception to signal unsupported operation
            throw new UnsupportedOperationException(
                context.getStart().getLine() + " Operation not supported (if-then node)"
            );
        }
    }

    @Override
    public LoopNode visitLoop(ExprParser.LoopContext context) {
        addVisitedNode("Visited loop node");

        // Check if the loop is a while loop
        if (context.KEY_WHILE() != null) {
            WhileNode node = new WhileNode();
            // Visit the expression node to get the condition of the while loop
            node.setCondition(visitExpr(context.expr()));

            // Visit the associated block node and set it as the block of the while loop
            node.setBlock(visitBlock(context.block()));
            node.getLineNumberFromContext(context);
            // Return the constructed WhileNode object representing the while loop
            return node;
        } else if (context.KEY_FOR() != null) {
            ForNode node = new ForNode();

            // Create an IdentifierNode to represent the iterator variable in the for loop
            IdentifierNode iNode = new IdentifierNode();
            iNode.setText(context.IDENTIFIER().getText());
            node.setIterator(iNode);

            // Visit the expression node to get the array or iterable over which the for loop will iterate
            node.setArray(visitExpr(context.expr()));

            // Visit the associated block node and set it as the block of the for loop
            node.setBlock(visitBlock(context.block()));
            node.getLineNumberFromContext(context);
            // Return the constructed ForNode object representing the for loop
            return node;
        } else {
            // If no valid loop type is found, throw an exception to signal unsupported operation
            throw new UnsupportedOperationException(
                context.getStart().getLine() + " Operation not supported (loop node)"
            );
        }
    }

    @Override
    public CommandNode visitCommand(ExprParser.CommandContext context) {
        addVisitedNode("Visited command node");

        // Check if the command is a return statement
        if (context.KEY_RETURN() != null) {
            ReturnNode node = new ReturnNode();
            // Visit the expression node to determine what value will be returned
            node.setInnerNode(visitExpr(context.expr()));
            node.getLineNumberFromContext(context);
            // Return the constructed ReturnNode object representing the return command
            return node;

        // Check if the command is a break statement
        } else if (context.KEY_BREAK() != null) {
            BreakNode node = new BreakNode();
            node.getLineNumberFromContext(context);
            // Return the constructed BreakNode object representing the break command
            return node;

        // Check if the command is a continue statement
        } else if (context.KEY_CONTINUE() != null) {
            ContinueNode node = new ContinueNode();
            node.getLineNumberFromContext(context);
            // Return the constructed ContinueNode object representing the continue command
            return node;
        } else {
            // If no valid command type is found, throw an exception to signal unsupported operation
            throw new UnsupportedOperationException(
                context.getStart().getLine() + " Operation not supported (command node)"
            );
        }
    }

    @Override
    public AssignmentNode visitAssign(ExprParser.AssignContext context) {
        addVisitedNode("Visited assign node");
        AssignmentNode node = new AssignmentNode();

        // Check if the assignment statement contains a value (left-hand side)
        if (context.value() != null) {
            // Visit the value node to retrieve the left side of the assignment
            node.setLeft(visitValue(context.value()));

            // Check if the assignment statement also contains an expression (right-hand side)
            if (context.expr() != null) {
                // Visit the expression node to retrieve the right side of the assignment
                node.setRight(visitExpr(context.expr()));
            }

            node.getLineNumberFromContext(context);
            // Return the constructed AssignmentNode object representing the assignment operation
            return node;
        } else {
            // If no value is found, throw an exception to signal unsupported operation
            throw new UnsupportedOperationException(
                context.getStart().getLine() + " Operation not supported (assign node)"
            );
        }
    }

    @Override
    public DeclarationNode visitDecl(ExprParser.DeclContext context) {
        addVisitedNode("Visited declaration node");

        // Check if the declaration is a variable definition
        if (context.defin() != null) {
            // Visit the definition node to process and retrieve the variable definition
            DefineNode node = visitDefin(context.defin());
            node.getLineNumberFromContext(context);
            // Return the constructed DefineNode object representing the variable definition
            return node;

        // Check if the declaration is a function definition
        } else if (context.fdecl() != null) {
            // Visit the function declaration node to process and retrieve the function definition
            FunctionDNode node = visitFdecl(context.fdecl());
            node.getLineNumberFromContext(context);
            // Return the constructed FunctionDNode object representing the function definition
            return node;

        // Check if the declaration is a class definition
        } else if (context.cdecl() != null) {
            // Visit the class declaration node to process and retrieve the class definition
            ClassDNode node = visitCdecl(context.cdecl());
            node.getLineNumberFromContext(context);
            // Return the constructed ClassDNode object representing the class definition
            return node;

        // Check if the declaration is a card type (custom type) definition
        } else if (context.cardType() != null) {
            // Visit the card type node to process and retrieve the custom type definition
            CardTypeNode node = visitCardType(context.cardType());
            node.getLineNumberFromContext(context);
            // Return the constructed CardTypeNode object representing the custom type definition
            return node;
        } else {
            // If no valid declaration type is found, throw an exception to signal unsupported operation
            throw new UnsupportedOperationException(
                context.getStart().getLine() + " Operation not supported (declaration node)"
            );
        }
    }

    @Override
    public ModifierNode visitModifier(ExprParser.ModifierContext context) {
        addVisitedNode("Visited modifier node");
        ModifierNode node = new ModifierNode();

        // Check if the modifier keyword is "static"
        if (context.KEY_STATIC() != null) {
            // Retrieve the keyword text and set it as the modifier for this node
            node.setModifier(context.KEY_STATIC().getText());
        }

        node.getLineNumberFromContext(context);
        // Return the constructed ModifierNode object representing the modifier
        return node;
    }

    @Override
    public DefineNode visitDefin(ExprParser.DefinContext context) {
        addVisitedNode("Visited define node");
        DefineNode node = new DefineNode();

        // Process and set the modifier ("static") for the definition
        node.setModi(visitModifier(context.modifier()));

        // Check if both a type and identifier are present in the definition
        if (context.type() != null && context.IDENTIFIER() != null) {
            // Visit the type node to determine the data type of the variable
            node.setType(visitType(context.type()));

            // Create a new IdentifierNode to hold the variable's name
            IdentifierNode text = new IdentifierNode();
            text.setText(context.IDENTIFIER().getText());
            node.setID(text);

            // Check if the variable is an array (bracket notation used)
            if (context.L_BRACKET() != null && context.R_BRACKET() != null) {
                node.setArray(true);
            }

            // Check if the variable is initialized with an expression
            if (context.expr() != null) {
                // Visit the expression node to process the initialization value
                node.setValue(visitExpr(context.expr()));
            }
        } else {
            // Throw an exception if either the type or identifier is missing
            throw new UnsupportedOperationException(
                context.getStart().getLine() + " Operation not supported (define node)"
            );
        }

        node.getLineNumberFromContext(context);
        // Return the constructed DefineNode object representing the variable definition
        return node;
    }

    @Override
    public TypeNode visitType(ExprParser.TypeContext context) {
        addVisitedNode("Visited type node");

        TypeNode node = new TypeNode();
        // Check if the type is an integer type
        if (context.TYPE_INT() != null) {
            node.setTypeName(context.TYPE_INT().getText());

        // Check if the type is a float type
        } else if (context.TYPE_FLOAT() != null) {
            node.setTypeName(context.TYPE_FLOAT().getText());

        // Check if the type is a string type
        } else if (context.TYPE_STRING() != null) {
            node.setTypeName(context.TYPE_STRING().getText());

        // Check if the type is a character type
        } else if (context.TYPE_CHAR() != null) {
            node.setTypeName(context.TYPE_CHAR().getText());

        // Check if the type is a void type (for functions with no return value)
        } else if (context.TYPE_VOID() != null) {
            node.setTypeName(context.TYPE_VOID().getText());

        // Check if the type is a custom identifier (user-defined)
        } else if (context.IDENTIFIER() != null) {
            node.setTypeName(context.IDENTIFIER().getText());
        } else {
            // If no recognized type is found, throw an exception to signal unsupported operation
            throw new UnsupportedOperationException(
                context.getStart().getLine() + " Operation not supported (type node)"
            );
        }

        node.getLineNumberFromContext(context);
        // Return the constructed TypeNode object representing the data type
        return node;
    }

    @Override
    public FunctionDNode visitFdecl(ExprParser.FdeclContext context) {
        addVisitedNode("Visited functionDeclaration node");
        FunctionDNode node = new FunctionDNode();

        // Check if the function is an "action" type
        if (context.KEY_ACTION() != null) {
            node.setIsAction(true);
            // Visit the expression that defines the action function's behavior
            node.setExpr(visitExpr(context.expr()));
        } else {
            node.setIsAction(false);
            // Process the function's modifier ("static")
            node.setModifier(visitModifier(context.modifier()));
        }

        // Ensure both the return type and function name are present
        if (context.type() != null && context.IDENTIFIER() != null) {
            // Set the return type of the function
            node.setReturnType(visitType(context.type()));

            // Create a new IdentifierNode to hold the function's name
            IdentifierNode text = new IdentifierNode();
            text.setText(context.IDENTIFIER().getText());
            node.setFunction(text);

            // Check if the function has parameters, and process them if present
            if (context.fparams() != null) {
                node.setParameter(visitFparams(context.fparams()));
            }

            // Check if the function has an implementation block, and process it if present
            if (context.block() != null) {
                node.setBlock(visitBlock(context.block()));
            }
        } else {
            // Throw an exception if the return type or identifier is missing
            throw new UnsupportedOperationException(
                context.getStart().getLine() + " Operation not supported (functionDeclaration node)"
            );
        }

        node.getLineNumberFromContext(context);
        // Return the constructed FunctionDNode object representing the function definition
        return node;
    }

    @Override
    public FparamsNode visitFparams(ExprParser.FparamsContext context) {
        addVisitedNode("Visited functionParameters node");
        FparamsNode node = new FparamsNode();

        // Check if there is a leftmost parameter to be processed
        if (context.fparam() != null) {
            // Process the leftmost parameter and set it as the left child node
            node.setLeft(visitFparam(context.fparam()));

            // Check if there are further parameters to be processed
            if (context.fparams() != null) {
                // Recursively visit the remaining parameters and set them as the right child node
                node.setRight(visitFparams(context.fparams()));
            }
        } else {
            // Throw an exception if no parameters are specified (invalid syntax)
            throw new UnsupportedOperationException(
                context.getStart().getLine() + " Operation not supported (functionParameters node)"
            );
        }

        node.getLineNumberFromContext(context);
        // Return the constructed FparamsNode object representing the function parameters
        return node;
    }

    @Override
    public FparamNode visitFparam(ExprParser.FparamContext context) {
        addVisitedNode("Visited functionParam node");
        FparamNode node = new FparamNode();

        // Check if both the parameter type and identifier are present
        if (context.type() != null && context.IDENTIFIER() != null) {
            // Process and set the type of the parameter
            node.setType(visitType(context.type()));

            IdentifierNode text = new IdentifierNode();
            text.setText(context.IDENTIFIER().getText());
            node.setIdentifier(text);
        } else {
            // Throw an exception if the parameter's type or identifier is missing
            throw new UnsupportedOperationException(
                context.getStart().getLine() + " Operation not supported (functionParam node)"
            );
        }

        node.getLineNumberFromContext(context);
        // Return the constructed FparamNode object representing the function parameter
        return node;
    }

    @Override
    public ClassDNode visitCdecl(ExprParser.CdeclContext context) {
        addVisitedNode("Visited classDeclaration node");

        // Check if the class name (first identifier) is present
        if (context.IDENTIFIER(0) != null) {
            ClassDNode node = new ClassDNode();
            IdentifierNode text = new IdentifierNode();

            text.setText(context.IDENTIFIER(0).getText());
            node.setName(text);

            // Check if the class has an implementation block, and process it if present
            if (context.block() != null) {
                node.setBlock(visitBlock(context.block()));
            }

            // Check if the class extends another class (has a superclass)
            if (context.KEY_EXTENDS() != null) {
                // Create another IdentifierNode for the superclass name
                IdentifierNode text1 = new IdentifierNode();
                text1.setText(context.IDENTIFIER(1).getText());
                node.setSuperClass(text1);
            }

            node.getLineNumberFromContext(context);
            // Return the constructed ClassDNode object representing the class declaration
            return node;
        } else {
            // Throw an exception if the class name is missing
            throw new UnsupportedOperationException(
                context.getStart().getLine() + " Operation not supported (classDeclaration node)"
            );
        }
    }

    @Override
    public ExpressionNode visitExpr(ExprParser.ExprContext context) {
        addVisitedNode("Visited expression node");

        // Check if this is an AND logical operation
        if (context.AND() != null) {
            InfixExpressionNode node = new ANDNode();
            // Recursively set the left operand by visiting another expression node
            node.setLeft(visitExpr(context.expr()));
            // Set the right operand by visiting a relation node
            node.setRight(visitRelation(context.relation()));
            node.getLineNumberFromContext(context);
            // Return the fully constructed ANDNode
            return node;

        // Check if this is an OR logical operation
        } else if (context.OR() != null) {
            InfixExpressionNode node = new ORNode();
            // Recursively set the left operand by visiting another expression node
            node.setLeft(visitExpr(context.expr()));
            // Set the right operand by visiting a relation node
            node.setRight(visitRelation(context.relation()));
            node.getLineNumberFromContext(context);
            // Return the fully constructed ORNode
            return node;

        // If it's not AND or OR, treat it as a relational expression
        } else if (context.relation() != null) {
            // Visit and retrieve the relational expression node
            ExpressionNode node = visitRelation(context.relation());
            node.getLineNumberFromContext(context);
            // Return the relational expression node
            return node;
        } else {
            // If none of the above cases are met, throw an unsupported operation
            throw new UnsupportedOperationException(
                context.getStart().getLine() + " Operation not supported (expression node)"
            );
        }
    }

    @Override
    public ExpressionNode visitArith(ExprParser.ArithContext context) {
        addVisitedNode("Visited arithmetic node");

        // Check if the arithmetic expression uses the '+' operator
        if (context.PLUS() != null) {
            InfixExpressionNode node = new AdditionNode();

            // Recursively process the left operand by visiting another arithmetic node
            node.setLeft(visitArith(context.arith()));

            // Process the right operand by visiting a term node
            node.setRight(visitTerm(context.term()));
            node.getLineNumberFromContext(context);
            // Return the constructed AdditionNode
            return node;

        // Check if the arithmetic expression uses the '-' operator
        } else if (context.MINUS() != null) {
            InfixExpressionNode node = new SubtractionNode();

            // Recursively process the left operand by visiting another arithmetic node
            node.setLeft(visitArith(context.arith()));

            // Process the right operand by visiting a term node
            node.setRight(visitTerm(context.term()));
            node.getLineNumberFromContext(context);
            // Return the constructed SubtractionNode
            return node;

        // If it's not '+' or '-', handle it as a single term (operand)
        } else if (context.term() != null) {
            ExpressionNode node = visitTerm(context.term());

            node.getLineNumberFromContext(context);
            // Return the single term expression node
            return node;

        // If none of the cases are satisfied, throw an unsupported operation exception
        } else {
            throw new UnsupportedOperationException(
                context.getStart().getLine() + " Operation not supported (arithmetic node)"
            );
        }
    }

    @Override
    public ExpressionNode visitRelation(ExprParser.RelationContext context) {
        addVisitedNode("Visited relation node");

        // Check if the comparison expression uses the '<' operator
        if (context.LT() != null) {
            InfixExpressionNode node = new LessThanNode();

            // Recursively process the left operand by visiting another relation node
            node.setLeft(visitRelation(context.relation()));

            // Process the right operand by visiting an arithmetic node
            node.setRight(visitArith(context.arith()));
            node.getLineNumberFromContext(context);
            // Return the constructed LessThanNode
            return node;

        // Check if the comparison expression uses the '<=' operator
        } else if (context.LTEQ() != null) {
            InfixExpressionNode node = new LTEQNode();

            // Process the left operand
            node.setLeft(visitRelation(context.relation()));

            // Process the right operand
            node.setRight(visitArith(context.arith()));
            node.getLineNumberFromContext(context);
            // Return the constructed LTEQNode
            return node;

        // Check if the comparison expression uses the '==' operator
        } else if (context.EQEQ() != null) {
            // Create an EQEQNode to represent this comparison
            InfixExpressionNode node = new EQEQNode();

            // Process the left operand
            node.setLeft(visitRelation(context.relation()));

            // Process the right operand
            node.setRight(visitArith(context.arith()));
            node.getLineNumberFromContext(context);
            // Return the constructed EQEQNode
            return node;

        // Check if the comparison expression uses the '>' operator
        } else if (context.GT() != null) {
            InfixExpressionNode node = new GreaterThanNode();

            // Process the left operand
            node.setLeft(visitRelation(context.relation()));

            // Process the right operand
            node.setRight(visitArith(context.arith()));
            node.getLineNumberFromContext(context);
            // Return the constructed GreaterThanNode
            return node;

        // Check if the comparison expression uses the '>=' operator
        } else if (context.GTEQ() != null) {
            InfixExpressionNode node = new GTEQNode();

            // Process the left operand
            node.setLeft(visitRelation(context.relation()));

            // Process the right operand
            node.setRight(visitArith(context.arith()));
            node.getLineNumberFromContext(context);
            // Return the constructed GTEQNode
            return node;

        // Check if the comparison expression uses the '!=' operator
        } else if (context.NOTEQ() != null) {
            InfixExpressionNode node = new NOTEQNode();

            // Process the left operand
            node.setLeft(visitArith(context.arith()));

            // Process the right operand
            node.setRight(visitRelation(context.relation()));
            node.getLineNumberFromContext(context);
            // Return the constructed NOTEQNode
            return node;

        // If it is not a comparison operator, handle it as a single arithmetic operand
        } else if (context.arith() != null) {
            // Visit and retrieve the arithmetic node
            return visitArith(context.arith());

        // If none of the cases are satisfied, throw an unsupported operation exception
        } else {
            throw new UnsupportedOperationException(
                context.getStart().getLine() + " Operation not supported (relation node)"
            );
        }
    }

    @Override
    public ExpressionNode visitTerm(ExprParser.TermContext context) {
        addVisitedNode("Visited term node");

        // Check if the expression uses the '*' (multiplication) operator
        if (context.MULT() != null) {
            InfixExpressionNode node = new MultiplicationNode();

            // Process the left operand by visiting the factor node
            node.setLeft(visitFactor(context.factor()));

            // Process the right operand by recursively visiting the term node
            node.setRight(visitTerm(context.term()));
            node.getLineNumberFromContext(context);
            // Return the constructed MultiplicationNode
            return node;

        // Check if the expression uses the '/' (division) operator
        } else if (context.DIV() != null) {
            InfixExpressionNode node = new DivisionNode();

            // Process the left operand
            node.setLeft(visitFactor(context.factor()));

            // Process the right operand
            node.setRight(visitTerm(context.term()));
            node.getLineNumberFromContext(context);
            // Return the constructed DivisionNode
            return node;

        // Check if the expression uses the '%' (modulus) operator
        } else if (context.MOD() != null) {
            InfixExpressionNode node = new ModNode();

            // Process the left operand
            node.setLeft(visitFactor(context.factor()));

            // Process the right operand
            node.setRight(visitTerm(context.term()));
            node.getLineNumberFromContext(context);
            // Return the constructed ModNode
            return node;

        // If no arithmetic operator is used, process the term as a single factor
        } else if (context.factor() != null) {
            // Visit and retrieve the factor node
            ExpressionNode node = visitFactor(context.factor());

            node.getLineNumberFromContext(context);
            // Return the factor node as an expression
            return node;
        } else {
            // If none of the cases are satisfied, throw an unsupported operation exception
            throw new UnsupportedOperationException(
                context.getStart().getLine() + " Operation not supported (term node)"
            );
        }
    }

    @Override
    public ExpressionNode visitFactor(ExprParser.FactorContext context) {
        addVisitedNode("Visited factor node");

        // Check if the factor is an expression enclosed in parentheses: (expr)
        if (context.L_PAREN() != null && context.R_PAREN() != null) {
            // Visit and process the inner expression node
            ExpressionNode node = visitExpr(context.expr());

            node.getLineNumberFromContext(context);
            // Return the inner expression node as the factor
            return node;

        // Check if the factor has a unary '+' operator, then returning the same factor
        } else if (context.PLUS() != null) {
            // Recursively visit and return the inner factor node
            return visitFactor(context.factor());

        // Check if the factor has a unary '-' operator, indicating negation
        } else if (context.MINUS() != null) {
            NegativeNode node = new NegativeNode();

            // Set the inner factor node to be negated
            node.setInnerNode(visitFactor(context.factor()));
            node.getLineNumberFromContext(context);
            // Return the constructed NegativeNode
            return node;

        // Check if the factor has a 'NOT' operator, representing logical negation
        } else if (context.NOT() != null) {
            NegateNode node = new NegateNode();

            // Set the inner factor node to be logically negated
            node.setInnerNode(visitFactor(context.factor()));
            node.getLineNumberFromContext(context);
            // Return the constructed NegateNode
            return node;

        // Check if the factor directly represents a value
        } else if (context.value() != null) {
            // Visit and retrieve the value node
            ValueNode node = visitValue(context.value());

            node.getLineNumberFromContext(context);
            // Return the value node as an expression
            return node;

        } else {
            // If none of the cases are satisfied, throw an unsupported operation exception
            throw new UnsupportedOperationException(
                context.getStart().getLine() + " Operation not supported (factor node)"
            );
        }
    }

    @Override
    public ValueNode visitCall(ExprParser.CallContext context) {
        addVisitedNode("Visited call node");
        FunctionCallNode node = new FunctionCallNode();

        // Check and set if the call is a constructor
        node.setHasNew(context.KEY_NEW() != null);

        // Verify that the function name (identifier) is present
        if (context.IDENTIFIER() != null) {
            IdentifierNode NameNode = new IdentifierNode();
            NameNode.setText(context.IDENTIFIER().getText());

            // Set the function name in the FunctionCallNode
            node.setFunction(NameNode);

            // If the function call includes parameters, process them
            if (context.exprs() != null) {
                // Visit and set the parameters in the FunctionCallNode
                node.setParameter(visitExprs(context.exprs()));
            }
        } else {
            // If no function identifier is found, throw an exception with the line number
            throw new UnsupportedOperationException(
                context.getStart().getLine() + " Operation not supported (call node)"
            );
        }

        node.getLineNumberFromContext(context);
        // Return the constructed FunctionCallNode
        return node;
    }

    @Override
    public ExpressionsNode visitExprs(ExprParser.ExprsContext context) {
        addVisitedNode("Visited expressions node");
        ExpressionsNode node = new ExpressionsNode();

        // Set the left side of the expression node to the current expression
        node.setLeft(visitExpr(context.expr()));

        // Check if there are more expressions in the list
        if (context.exprs() != null) {
            // Recursively visit the remaining expressions and set them to the right side
            node.setRight(visitExprs(context.exprs()));
        } else {
            // If there are no further expressions, set the right side to null
            node.setRight(null);
        }

        node.getLineNumberFromContext(context);
        // Return the constructed ExpressionsNode
        return node;
    }

    @Override
    public ValueNode visitValue(ExprParser.ValueContext context) {
        addVisitedNode("Visited value node");

        // Handle function calls
        if (context.call() != null) {
            // Visit the call node and get the resulting value node
            ValueNode node = visitCall(context.call());

            node.getLineNumberFromContext(context);
            return node;

        // Handle arrays
        } else if (context.array() != null) {
            // Visit the array node and get the resulting value node
            ArrayNode node = visitArray(context.array());

            node.getLineNumberFromContext(context);
            return node;

        // Handle field access
        } else if (context.access() != null) {
            // Visit the access node and get the resulting value node
            ValueNode node = visitAccess(context.access());

            node.getLineNumberFromContext(context);
            return node;

        // Handle numeral literals
        } else if (context.NUMERAL() != null) {
            // Create a new NumberNode and parse the numeral value
            NumberNode node = new NumberNode();
            node.setValue(Double.parseDouble(context.NUMERAL().getText()));

            node.getLineNumberFromContext(context);
            return node;

        // Handle float literals
        } else if (context.FLOAT() != null) {
            // Create a new FloatNode and parse the float value
            NumberNode node = new FloatNode();
            node.setValue(Double.parseDouble(context.FLOAT().getText()));

            node.getLineNumberFromContext(context);
            return node;

        // Handle string literals
        } else if (context.STRING() != null) {
            // Create a new StringNode and set the string value
            StringNode node = new StringNode();
            node.setValue(context.STRING().getText());

            node.getLineNumberFromContext(context);
            return node;

        // Handle character literals
        } else if (context.CHAR() != null) {
            // Create a new CharNode and set the character value
            CharNode node = new CharNode();
            node.setValue(context.CHAR().getText());

            node.getLineNumberFromContext(context);
            return node;

        // Handle identifiers
        } else if (context.IDENTIFIER() != null) {
            // Create a new IdentifierNode and set the identifier text
            IdentifierNode node = new IdentifierNode();
            node.setText(context.IDENTIFIER().getText());

            node.getLineNumberFromContext(context);
            return node;
        } else {
            // If no known value type is found, throw an unsupported operation exception
            throw new UnsupportedOperationException(context.getStart().getLine() + " Operation not supported (value node)");
        }
    }

    @Override
    public ArrayNode visitArray(ExprParser.ArrayContext context) {
        addVisitedNode("Visited array node");
        ArrayNode node = new ArrayNode();

        // If the array has expressions (elements), visit them
        if (context.exprs() != null) {
            // Set the inner node with the parsed expressions
            node.setInnerNode(visitExprs(context.exprs()));
        } else {
            // If no expressions are found, set the inner node to null
            node.setInnerNode(null);
        }

        node.getLineNumberFromContext(context);
        // Return the constructed ArrayNode
        return node;
    }

    public ClassAccessNode visitMethod(ExprParser.MethodContext context) {
        addVisitedNode("Visited method node");
        ClassAccessNode node = new ClassAccessNode();

        // Visit the object being accessed and set it in the ClassAccessNode
        node.setObject(visitAccessibleObject(context.accessibleObject()));

        // Initialize an empty list to store accessible values associated with the class
        node.setValue(new ArrayList<>());

        // Iterate through each accessible value in the parse tree and visit them
        for (ExprParser.AccessibleValueContext i : context.accessibleValue()) {
            // Add the parsed accessible value to the node's list
            node.getValue().add(visitAccessibleValue(i));
        }

        // Finally, visit the method call context and add it as an accessible value
        node.getValue().add(visitCall(context.call()));

        node.getLineNumberFromContext(context);
        // Return the constructed ClassAccessNode
        return node;
    }

    @Override
    public ValueNode visitAccessibleObject(ExprParser.AccessibleObjectContext context) {
        addVisitedNode("Visited accessibleObject node");

        // Check if the accessible object is a function call
        if (context.call() != null) {
            // Visit the call node and return a ValueNode representing the function call
            ValueNode node = visitCall(context.call());

            node.getLineNumberFromContext(context);
            return node;

        // Check if the accessible object is an array access
        } else if (context.arrayAccess() != null) {
            // Visit the array access node and return an ArrayAccessNode representing the array access
            ArrayAccessNode node = visitArrayAccess(context.arrayAccess());

            node.getLineNumberFromContext(context);
            return node;

        // Check if the accessible object is an identifier (variable)
        } else if (context.IDENTIFIER() != null) {
            // Create a new IdentifierNode to represent the identifier and set its text
            IdentifierNode nameNode = new IdentifierNode();
            nameNode.setText(context.IDENTIFIER().getText());

            nameNode.getLineNumberFromContext(context);
            return nameNode;

        } else {
            // Throw an exception if the accessible object isn't one of the expected types
            throw new UnsupportedOperationException(context.getStart().getLine() + " Operation not supported (accessibleObject node)");
        }
    }

    @Override
    public ValueNode visitAccess(ExprParser.AccessContext context) {
        addVisitedNode("Visited access node");

        // Get the number of "accessing" nodes (representing individual access steps like field access or array indexing)
        int accesses = context.accessing().size();

        // Check if there's only one access step, and it's an array access
        if (context.accessing(0).L_BRACKET() != null && accesses == 1) {
            // Extract the first (and only) "accessing" context, representing an array access
            ExprParser.AccessingContext arrayContext = context.accessing(0);

            // Create and populate an ArrayAccessNode
            ArrayAccessNode node = new ArrayAccessNode();
            node.setArray(visitAccessibleObject(context.accessibleObject()));  // Set the array being accessed
            node.setIndex(visitExpr(arrayContext.expr()));  // Set the index being accessed in the array

            node.getLineNumberFromContext(context);
            return node;
        } else {
            // Otherwise, treat it as a class access (or a combination of field access and array indexing)
            ClassAccessNode node = new ClassAccessNode();
            node.setObject(visitAccessibleObject(context.accessibleObject()));  // Set the initial object being accessed

            // Variable to hold the last accessed ValueNode, which is initially the object
            ValueNode lastNode = node.getObject();
            // Iterate through each accessing step to build the chain
            for (int i = 0; i < accesses; i++) {
                ExprParser.AccessingContext ctx = context.accessing(i);
                // If there is another period, make a new ValueNode for the ClassAccessNode
                if (ctx.PERIOD() != null) {
                    // Create a new node representing the accessed field or method
                    ValueNode objectNode = visitAccessibleValue(ctx.accessibleValue());
                    lastNode = objectNode;
                    node.getValue().add(objectNode);  // Add it to the class access chain
                
                //If there is an array access, change the latest ValueNode to be an ArrayAccessNode (array indexing)
                } else if (ctx.L_BRACKET() != null) {
                    // Create and populate an ArrayAccessNode
                    ArrayAccessNode arrayNode = new ArrayAccessNode();

                    // Check if there are preceding accesses in the chain
                    if (node.getValue().size() > 0) {
                        arrayNode.setArray(lastNode);  // Set the previous access node as the array
                        arrayNode.setIndex(visitExpr(ctx.expr()));  // Set the index expression for the array access
                        node.getValue().set(node.getValue().size() - 1, arrayNode);  // Replace the last access with the array node
                    } else {
                        // If there are no preceding accesses, set the base object as the array
                        arrayNode.setArray(node.getObject());
                        arrayNode.setIndex(visitExpr(ctx.expr()));
                        node.setObject(arrayNode);
                    }
                } else {
                    // Throw an exception if the access pattern is not supported
                    throw new UnsupportedOperationException(context.getStart().getLine() + " Operation not supported (access node)");
                }
            }

            node.getLineNumberFromContext(context);
            return node;
        }
    }

    @Override
    public ArrayAccessNode visitArrayAccess(ExprParser.ArrayAccessContext context) {
        addVisitedNode("Visited arrayAccess node");
        ArrayAccessNode node = new ArrayAccessNode();

        // Create an IdentifierNode for the array name and set it in the ArrayAccessNode
        IdentifierNode nameNode = new IdentifierNode();
        nameNode.setText(context.IDENTIFIER().getText());
        node.setArray(nameNode);

        // If there is an index expression, visit it and set it in the node
        if (context.expr() != null) {
            node.setIndex(visitExpr(context.expr()));
        } else {
            node.setIndex(null);  // Set the index to null if it doesn't exist
        }

        node.getLineNumberFromContext(context);
        return node;
    }

    @Override
    public ValueNode visitAccessibleValue(ExprParser.AccessibleValueContext context) {
        addVisitedNode("Visited accessibleValue node");

        // If the value is a function call, visit it and return the corresponding node
        if (context.call() != null) {
            ValueNode node = visitCall(context.call());
            node.getLineNumberFromContext(context);
            return node;

        // If the value is an identifier, create an IdentifierNode for it
        } else if (context.IDENTIFIER() != null) {
            IdentifierNode node = new IdentifierNode();
            node.setText(context.IDENTIFIER().getText());
            node.getLineNumberFromContext(context);
            return node;
        } else {
            // Throw an exception if the value is unsupported
            throw new UnsupportedOperationException(context.getStart().getLine() + " Operation not supported (accessibleValue node)");
        }
    }

    @Override
    public CardTypeNode visitCardType(ExprParser.CardTypeContext context) {
        addVisitedNode("Visited cardType node");
        CardTypeNode node = new CardTypeNode();

        // If a string ID is present, set it in the CardTypeNode
        if (context.STRING() != null) {
            node.setID(context.STRING().getText());
        }

        // If an identifier is present, create and set an IdentifierNode for it
        if (context.IDENTIFIER() != null) {
            IdentifierNode identifierNode = new IdentifierNode();
            identifierNode.setText(context.IDENTIFIER().getText());
            identifierNode.getLineNumberFromContext(context);
            node.setIdentifier(identifierNode);
        }

        // Loop through all card methods and add them to the CardTypeNode
        for (int i = 0; i < context.cardMethod().size(); i++) {
            FunctionDNode methodNode = new FunctionDNode();
            
            // Create an IdentifierNode for the method name
            IdentifierNode identifierNode = new IdentifierNode();
            identifierNode.setText(context.cardMethod(i).IDENTIFIER().getText());
            identifierNode.getLineNumberFromContext(context);
            methodNode.setFunction(identifierNode);
            
            // Set the method modifier, return type, and block
            methodNode.setModifier(new ModifierNode());
            methodNode.setReturnType(visitType(context.cardMethod(i).type()));
            methodNode.setBlock(visitBlock(context.cardMethod(i).block()));
            
            // If function parameters are available, set them in the FunctionDNode
            if (context.cardMethod(i).fparams() != null) {
                methodNode.setParameter(visitFparams(context.cardMethod(i).fparams()));
            }
            
            // Add the method to the CardTypeNode
            node.getMethods().add(methodNode);
        }

        // Loop through all card fields and add them to the CardTypeNode
        for (int i = 0; i < context.cardField().size(); i++) {
            DefineNode fieldNode = new DefineNode();
            
            // Create an IdentifierNode for the field name
            IdentifierNode identifierNode = new IdentifierNode();
            identifierNode.setText(context.cardField(i).IDENTIFIER().getText());
            identifierNode.getLineNumberFromContext(context);
            fieldNode.setID(identifierNode);
            
            // Set the field's modifier and type
            fieldNode.setModi(new ModifierNode());
            fieldNode.setType(visitType(context.cardField(i).type()));
            
            // Add the field to the CardTypeNode
            node.getFields().add(fieldNode);
        }

        // Return the fully constructed CardTypeNode
        return node;
    }
}