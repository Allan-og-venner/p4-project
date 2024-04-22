package gen;// Generated from C:/Users/sinag/IdeaProjects/p4-project/src/Expr.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ExprParser}.
 */
public interface ExprListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ExprParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(ExprParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(ExprParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(ExprParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(ExprParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(ExprParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(ExprParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#decl}.
	 * @param ctx the parse tree
	 */
	void enterDecl(ExprParser.DeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#decl}.
	 * @param ctx the parse tree
	 */
	void exitDecl(ExprParser.DeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#defin}.
	 * @param ctx the parse tree
	 */
	void enterDefin(ExprParser.DefinContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#defin}.
	 * @param ctx the parse tree
	 */
	void exitDefin(ExprParser.DefinContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#assign}.
	 * @param ctx the parse tree
	 */
	void enterAssign(ExprParser.AssignContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#assign}.
	 * @param ctx the parse tree
	 */
	void exitAssign(ExprParser.AssignContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#fdecl}.
	 * @param ctx the parse tree
	 */
	void enterFdecl(ExprParser.FdeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#fdecl}.
	 * @param ctx the parse tree
	 */
	void exitFdecl(ExprParser.FdeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#fparams}.
	 * @param ctx the parse tree
	 */
	void enterFparams(ExprParser.FparamsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#fparams}.
	 * @param ctx the parse tree
	 */
	void exitFparams(ExprParser.FparamsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#fparam}.
	 * @param ctx the parse tree
	 */
	void enterFparam(ExprParser.FparamContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#fparam}.
	 * @param ctx the parse tree
	 */
	void exitFparam(ExprParser.FparamContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#cdecl}.
	 * @param ctx the parse tree
	 */
	void enterCdecl(ExprParser.CdeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#cdecl}.
	 * @param ctx the parse tree
	 */
	void exitCdecl(ExprParser.CdeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#call}.
	 * @param ctx the parse tree
	 */
	void enterCall(ExprParser.CallContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#call}.
	 * @param ctx the parse tree
	 */
	void exitCall(ExprParser.CallContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#command}.
	 * @param ctx the parse tree
	 */
	void enterCommand(ExprParser.CommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#command}.
	 * @param ctx the parse tree
	 */
	void exitCommand(ExprParser.CommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#control}.
	 * @param ctx the parse tree
	 */
	void enterControl(ExprParser.ControlContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#control}.
	 * @param ctx the parse tree
	 */
	void exitControl(ExprParser.ControlContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#loop}.
	 * @param ctx the parse tree
	 */
	void enterLoop(ExprParser.LoopContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#loop}.
	 * @param ctx the parse tree
	 */
	void exitLoop(ExprParser.LoopContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#ifthen}.
	 * @param ctx the parse tree
	 */
	void enterIfthen(ExprParser.IfthenContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#ifthen}.
	 * @param ctx the parse tree
	 */
	void exitIfthen(ExprParser.IfthenContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(ExprParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(ExprParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#relation}.
	 * @param ctx the parse tree
	 */
	void enterRelation(ExprParser.RelationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#relation}.
	 * @param ctx the parse tree
	 */
	void exitRelation(ExprParser.RelationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#arith}.
	 * @param ctx the parse tree
	 */
	void enterArith(ExprParser.ArithContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#arith}.
	 * @param ctx the parse tree
	 */
	void exitArith(ExprParser.ArithContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(ExprParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(ExprParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(ExprParser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(ExprParser.FactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(ExprParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(ExprParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#accessibleObject}.
	 * @param ctx the parse tree
	 */
	void enterAccessibleObject(ExprParser.AccessibleObjectContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#accessibleObject}.
	 * @param ctx the parse tree
	 */
	void exitAccessibleObject(ExprParser.AccessibleObjectContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#exprs}.
	 * @param ctx the parse tree
	 */
	void enterExprs(ExprParser.ExprsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#exprs}.
	 * @param ctx the parse tree
	 */
	void exitExprs(ExprParser.ExprsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(ExprParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(ExprParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#modifier}.
	 * @param ctx the parse tree
	 */
	void enterModifier(ExprParser.ModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#modifier}.
	 * @param ctx the parse tree
	 */
	void exitModifier(ExprParser.ModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#array}.
	 * @param ctx the parse tree
	 */
	void enterArray(ExprParser.ArrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#array}.
	 * @param ctx the parse tree
	 */
	void exitArray(ExprParser.ArrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#arrayAccess}.
	 * @param ctx the parse tree
	 */
	void enterArrayAccess(ExprParser.ArrayAccessContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#arrayAccess}.
	 * @param ctx the parse tree
	 */
	void exitArrayAccess(ExprParser.ArrayAccessContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#classAccess}.
	 * @param ctx the parse tree
	 */
	void enterClassAccess(ExprParser.ClassAccessContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#classAccess}.
	 * @param ctx the parse tree
	 */
	void exitClassAccess(ExprParser.ClassAccessContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#accessibleValue}.
	 * @param ctx the parse tree
	 */
	void enterAccessibleValue(ExprParser.AccessibleValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#accessibleValue}.
	 * @param ctx the parse tree
	 */
	void exitAccessibleValue(ExprParser.AccessibleValueContext ctx);
}