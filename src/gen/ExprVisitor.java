package gen;// Generated from /Users/simonthordal/IdeaProjects/p4-project/src/Expr.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ExprParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ExprVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ExprParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(ExprParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(ExprParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(ExprParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecl(ExprParser.DeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#defin}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefin(ExprParser.DefinContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#assign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign(ExprParser.AssignContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#cardType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCardType(ExprParser.CardTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#cardMethod}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCardMethod(ExprParser.CardMethodContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#cardField}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCardField(ExprParser.CardFieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#fdecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFdecl(ExprParser.FdeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#fparams}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFparams(ExprParser.FparamsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#fparam}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFparam(ExprParser.FparamContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#cdecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCdecl(ExprParser.CdeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCall(ExprParser.CallContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommand(ExprParser.CommandContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#control}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitControl(ExprParser.ControlContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#loop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoop(ExprParser.LoopContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#ifthen}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfthen(ExprParser.IfthenContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(ExprParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#relation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelation(ExprParser.RelationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#arith}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArith(ExprParser.ArithContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(ExprParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactor(ExprParser.FactorContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(ExprParser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#accessibleObject}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAccessibleObject(ExprParser.AccessibleObjectContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#exprs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprs(ExprParser.ExprsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(ExprParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#modifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModifier(ExprParser.ModifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#access}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAccess(ExprParser.AccessContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#accessing}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAccessing(ExprParser.AccessingContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#array}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArray(ExprParser.ArrayContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#arrayAccess}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayAccess(ExprParser.ArrayAccessContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#method}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethod(ExprParser.MethodContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#accessibleValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAccessibleValue(ExprParser.AccessibleValueContext ctx);
}