package gen;// Generated from /Users/simonthordal/IdeaProjects/p4-project/src/Expr.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class ExprParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		EQ=1, GT=2, LT=3, NOT=4, EQEQ=5, LTEQ=6, GTEQ=7, NOTEQ=8, AND=9, OR=10, 
		PLUS=11, MINUS=12, MULT=13, DIV=14, MOD=15, L_PAREN=16, R_PAREN=17, L_CBRACKET=18, 
		R_CBRACKET=19, L_BRACKET=20, R_BRACKET=21, SEMICOLON=22, COMMA=23, PERIODE=24, 
		KEY_NEW=25, KEY_CLASS=26, KEY_FUNC=27, KEY_EXTENDS=28, KEY_BREAK=29, KEY_RETURN=30, 
		KEY_CONTINUE=31, KEY_IN=32, KEY_FOR=33, KEY_WHILE=34, KEY_IF=35, KEY_LOOP=36, 
		KEY_STATIC=37, KEY_RETURNTYPE_ARROW=38, KEY_NULL=39, TYPE_INT=40, TYPE_FLOAT=41, 
		TYPE_CHAR=42, TYPE_STRING=43, TYPE_VOID=44, CHAR=45, STRING=46, NUMERAL=47, 
		FLOAT=48, IDENTIFIER=49, BLOCK_COMMENT=50, WS=51, NEWLINE=52;
	public static final int
		RULE_prog = 0, RULE_block = 1, RULE_statement = 2, RULE_decl = 3, RULE_defin = 4, 
		RULE_assign = 5, RULE_fdecl = 6, RULE_fparam = 7, RULE_cdecl = 8, RULE_call = 9, 
		RULE_command = 10, RULE_control = 11, RULE_loop = 12, RULE_ifthen = 13, 
		RULE_expr = 14, RULE_logic = 15, RULE_relation = 16, RULE_arith = 17, 
		RULE_term = 18, RULE_factor = 19, RULE_value = 20, RULE_accessibleObject = 21, 
		RULE_exprs = 22, RULE_type = 23, RULE_modifier = 24, RULE_array = 25, 
		RULE_arrayAccess = 26, RULE_classAccess = 27, RULE_accessibleValue = 28;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "block", "statement", "decl", "defin", "assign", "fdecl", "fparam", 
			"cdecl", "call", "command", "control", "loop", "ifthen", "expr", "logic", 
			"relation", "arith", "term", "factor", "value", "accessibleObject", "exprs", 
			"type", "modifier", "array", "arrayAccess", "classAccess", "accessibleValue"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'='", "'>'", "'<'", "'!'", "'=='", "'<='", "'>='", "'!='", "'&&'", 
			"'||'", "'+'", "'-'", "'*'", "'/'", "'%'", "'('", "')'", "'{'", "'}'", 
			"'['", "']'", "';'", "','", "'.'", "'new'", "'class'", "'func'", "'extends'", 
			"'break'", "'return'", "'continue'", "'in'", "'for'", "'while'", "'if'", 
			"'loop'", "'static'", "'->'", "'null'", "'int'", "'float'", "'char'", 
			"'string'", "'void'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "EQ", "GT", "LT", "NOT", "EQEQ", "LTEQ", "GTEQ", "NOTEQ", "AND", 
			"OR", "PLUS", "MINUS", "MULT", "DIV", "MOD", "L_PAREN", "R_PAREN", "L_CBRACKET", 
			"R_CBRACKET", "L_BRACKET", "R_BRACKET", "SEMICOLON", "COMMA", "PERIODE", 
			"KEY_NEW", "KEY_CLASS", "KEY_FUNC", "KEY_EXTENDS", "KEY_BREAK", "KEY_RETURN", 
			"KEY_CONTINUE", "KEY_IN", "KEY_FOR", "KEY_WHILE", "KEY_IF", "KEY_LOOP", 
			"KEY_STATIC", "KEY_RETURNTYPE_ARROW", "KEY_NULL", "TYPE_INT", "TYPE_FLOAT", 
			"TYPE_CHAR", "TYPE_STRING", "TYPE_VOID", "CHAR", "STRING", "NUMERAL", 
			"FLOAT", "IDENTIFIER", "BLOCK_COMMENT", "WS", "NEWLINE"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Expr.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ExprParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode EOF() { return getToken(ExprParser.EOF, 0); }
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitProg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitProg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		try {
			setState(62);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NOT:
			case PLUS:
			case MINUS:
			case L_PAREN:
			case L_CBRACKET:
			case KEY_CLASS:
			case KEY_FUNC:
			case KEY_BREAK:
			case KEY_RETURN:
			case KEY_CONTINUE:
			case KEY_IF:
			case KEY_LOOP:
			case KEY_STATIC:
			case TYPE_INT:
			case TYPE_FLOAT:
			case TYPE_CHAR:
			case TYPE_STRING:
			case TYPE_VOID:
			case CHAR:
			case STRING:
			case NUMERAL:
			case FLOAT:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(58);
				block();
				setState(59);
				match(EOF);
				}
				break;
			case EOF:
				enterOuterAlt(_localctx, 2);
				{
				setState(61);
				match(EOF);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BlockContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_block);
		try {
			setState(68);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(64);
				statement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(65);
				statement();
				setState(66);
				block();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public DeclContext decl() {
			return getRuleContext(DeclContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(ExprParser.SEMICOLON, 0); }
		public AssignContext assign() {
			return getRuleContext(AssignContext.class,0);
		}
		public CommandContext command() {
			return getRuleContext(CommandContext.class,0);
		}
		public ControlContext control() {
			return getRuleContext(ControlContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_statement);
		try {
			setState(81);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(70);
				decl();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(71);
				expr();
				setState(72);
				match(SEMICOLON);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(74);
				assign();
				setState(75);
				match(SEMICOLON);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(77);
				command();
				setState(78);
				match(SEMICOLON);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(80);
				control();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclContext extends ParserRuleContext {
		public DefinContext defin() {
			return getRuleContext(DefinContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(ExprParser.SEMICOLON, 0); }
		public FdeclContext fdecl() {
			return getRuleContext(FdeclContext.class,0);
		}
		public CdeclContext cdecl() {
			return getRuleContext(CdeclContext.class,0);
		}
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_decl);
		try {
			setState(88);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(83);
				defin();
				setState(84);
				match(SEMICOLON);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(86);
				fdecl();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(87);
				cdecl();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DefinContext extends ParserRuleContext {
		public ModifierContext modifier() {
			return getRuleContext(ModifierContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(ExprParser.IDENTIFIER, 0); }
		public TerminalNode EQ() { return getToken(ExprParser.EQ, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode L_BRACKET() { return getToken(ExprParser.L_BRACKET, 0); }
		public TerminalNode NUMERAL() { return getToken(ExprParser.NUMERAL, 0); }
		public TerminalNode R_BRACKET() { return getToken(ExprParser.R_BRACKET, 0); }
		public DefinContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defin; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterDefin(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitDefin(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitDefin(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefinContext defin() throws RecognitionException {
		DefinContext _localctx = new DefinContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_defin);
		try {
			setState(109);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(90);
				modifier();
				setState(91);
				type();
				setState(92);
				match(IDENTIFIER);
				setState(93);
				match(EQ);
				setState(94);
				expr();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(96);
				modifier();
				setState(97);
				type();
				setState(98);
				match(IDENTIFIER);
				setState(99);
				match(L_BRACKET);
				setState(100);
				match(NUMERAL);
				setState(101);
				match(R_BRACKET);
				setState(102);
				match(EQ);
				setState(103);
				expr();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(105);
				modifier();
				setState(106);
				type();
				setState(107);
				match(IDENTIFIER);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AssignContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(ExprParser.EQ, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ArrayAccessContext arrayAccess() {
			return getRuleContext(ArrayAccessContext.class,0);
		}
		public ClassAccessContext classAccess() {
			return getRuleContext(ClassAccessContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(ExprParser.IDENTIFIER, 0); }
		public AssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitAssign(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitAssign(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignContext assign() throws RecognitionException {
		AssignContext _localctx = new AssignContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_assign);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(111);
				arrayAccess();
				}
				break;
			case 2:
				{
				setState(112);
				classAccess();
				}
				break;
			case 3:
				{
				setState(113);
				match(IDENTIFIER);
				}
				break;
			}
			setState(116);
			match(EQ);
			setState(117);
			expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FdeclContext extends ParserRuleContext {
		public ModifierContext modifier() {
			return getRuleContext(ModifierContext.class,0);
		}
		public TerminalNode KEY_FUNC() { return getToken(ExprParser.KEY_FUNC, 0); }
		public TerminalNode IDENTIFIER() { return getToken(ExprParser.IDENTIFIER, 0); }
		public TerminalNode L_PAREN() { return getToken(ExprParser.L_PAREN, 0); }
		public FparamContext fparam() {
			return getRuleContext(FparamContext.class,0);
		}
		public TerminalNode R_PAREN() { return getToken(ExprParser.R_PAREN, 0); }
		public TerminalNode KEY_RETURNTYPE_ARROW() { return getToken(ExprParser.KEY_RETURNTYPE_ARROW, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode L_CBRACKET() { return getToken(ExprParser.L_CBRACKET, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode R_CBRACKET() { return getToken(ExprParser.R_CBRACKET, 0); }
		public FdeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fdecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterFdecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitFdecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitFdecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FdeclContext fdecl() throws RecognitionException {
		FdeclContext _localctx = new FdeclContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_fdecl);
		try {
			setState(142);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(119);
				modifier();
				setState(120);
				match(KEY_FUNC);
				setState(121);
				match(IDENTIFIER);
				setState(122);
				match(L_PAREN);
				setState(123);
				fparam();
				setState(124);
				match(R_PAREN);
				setState(125);
				match(KEY_RETURNTYPE_ARROW);
				setState(126);
				type();
				setState(127);
				match(L_CBRACKET);
				setState(128);
				block();
				setState(129);
				match(R_CBRACKET);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(131);
				modifier();
				setState(132);
				match(KEY_FUNC);
				setState(133);
				match(IDENTIFIER);
				setState(134);
				match(L_PAREN);
				setState(135);
				match(R_PAREN);
				setState(136);
				match(KEY_RETURNTYPE_ARROW);
				setState(137);
				type();
				setState(138);
				match(L_CBRACKET);
				setState(139);
				block();
				setState(140);
				match(R_CBRACKET);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FparamContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(ExprParser.IDENTIFIER, 0); }
		public TerminalNode COMMA() { return getToken(ExprParser.COMMA, 0); }
		public FparamContext fparam() {
			return getRuleContext(FparamContext.class,0);
		}
		public FparamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fparam; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterFparam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitFparam(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitFparam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FparamContext fparam() throws RecognitionException {
		FparamContext _localctx = new FparamContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_fparam);
		try {
			setState(152);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(144);
				type();
				setState(145);
				match(IDENTIFIER);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(147);
				type();
				setState(148);
				match(IDENTIFIER);
				setState(149);
				match(COMMA);
				setState(150);
				fparam();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CdeclContext extends ParserRuleContext {
		public TerminalNode KEY_CLASS() { return getToken(ExprParser.KEY_CLASS, 0); }
		public List<TerminalNode> IDENTIFIER() { return getTokens(ExprParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(ExprParser.IDENTIFIER, i);
		}
		public TerminalNode L_CBRACKET() { return getToken(ExprParser.L_CBRACKET, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode R_CBRACKET() { return getToken(ExprParser.R_CBRACKET, 0); }
		public TerminalNode KEY_EXTENDS() { return getToken(ExprParser.KEY_EXTENDS, 0); }
		public CdeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cdecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterCdecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitCdecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitCdecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CdeclContext cdecl() throws RecognitionException {
		CdeclContext _localctx = new CdeclContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_cdecl);
		try {
			setState(168);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(154);
				match(KEY_CLASS);
				setState(155);
				match(IDENTIFIER);
				setState(156);
				match(L_CBRACKET);
				setState(157);
				block();
				setState(158);
				match(R_CBRACKET);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(160);
				match(KEY_CLASS);
				setState(161);
				match(IDENTIFIER);
				setState(162);
				match(KEY_EXTENDS);
				setState(163);
				match(IDENTIFIER);
				setState(164);
				match(L_CBRACKET);
				setState(165);
				block();
				setState(166);
				match(R_CBRACKET);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CallContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(ExprParser.IDENTIFIER, 0); }
		public TerminalNode L_PAREN() { return getToken(ExprParser.L_PAREN, 0); }
		public ExprsContext exprs() {
			return getRuleContext(ExprsContext.class,0);
		}
		public TerminalNode R_PAREN() { return getToken(ExprParser.R_PAREN, 0); }
		public CallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_call; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CallContext call() throws RecognitionException {
		CallContext _localctx = new CallContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_call);
		try {
			setState(178);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(170);
				match(IDENTIFIER);
				setState(171);
				match(L_PAREN);
				setState(172);
				exprs();
				setState(173);
				match(R_PAREN);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(175);
				match(IDENTIFIER);
				setState(176);
				match(L_PAREN);
				setState(177);
				match(R_PAREN);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CommandContext extends ParserRuleContext {
		public TerminalNode KEY_RETURN() { return getToken(ExprParser.KEY_RETURN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode KEY_BREAK() { return getToken(ExprParser.KEY_BREAK, 0); }
		public TerminalNode KEY_CONTINUE() { return getToken(ExprParser.KEY_CONTINUE, 0); }
		public CommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_command; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitCommand(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitCommand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CommandContext command() throws RecognitionException {
		CommandContext _localctx = new CommandContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_command);
		try {
			setState(184);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case KEY_RETURN:
				enterOuterAlt(_localctx, 1);
				{
				setState(180);
				match(KEY_RETURN);
				setState(181);
				expr();
				}
				break;
			case KEY_BREAK:
				enterOuterAlt(_localctx, 2);
				{
				setState(182);
				match(KEY_BREAK);
				}
				break;
			case KEY_CONTINUE:
				enterOuterAlt(_localctx, 3);
				{
				setState(183);
				match(KEY_CONTINUE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ControlContext extends ParserRuleContext {
		public LoopContext loop() {
			return getRuleContext(LoopContext.class,0);
		}
		public IfthenContext ifthen() {
			return getRuleContext(IfthenContext.class,0);
		}
		public ControlContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_control; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterControl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitControl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitControl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ControlContext control() throws RecognitionException {
		ControlContext _localctx = new ControlContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_control);
		try {
			setState(188);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case KEY_LOOP:
				enterOuterAlt(_localctx, 1);
				{
				setState(186);
				loop();
				}
				break;
			case KEY_IF:
				enterOuterAlt(_localctx, 2);
				{
				setState(187);
				ifthen();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LoopContext extends ParserRuleContext {
		public TerminalNode KEY_LOOP() { return getToken(ExprParser.KEY_LOOP, 0); }
		public TerminalNode KEY_WHILE() { return getToken(ExprParser.KEY_WHILE, 0); }
		public TerminalNode L_PAREN() { return getToken(ExprParser.L_PAREN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode R_PAREN() { return getToken(ExprParser.R_PAREN, 0); }
		public TerminalNode L_CBRACKET() { return getToken(ExprParser.L_CBRACKET, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode R_CBRACKET() { return getToken(ExprParser.R_CBRACKET, 0); }
		public TerminalNode KEY_FOR() { return getToken(ExprParser.KEY_FOR, 0); }
		public TerminalNode IDENTIFIER() { return getToken(ExprParser.IDENTIFIER, 0); }
		public TerminalNode KEY_IN() { return getToken(ExprParser.KEY_IN, 0); }
		public LoopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_loop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterLoop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitLoop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitLoop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LoopContext loop() throws RecognitionException {
		LoopContext _localctx = new LoopContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_loop);
		try {
			setState(208);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(190);
				match(KEY_LOOP);
				setState(191);
				match(KEY_WHILE);
				setState(192);
				match(L_PAREN);
				setState(193);
				expr();
				setState(194);
				match(R_PAREN);
				setState(195);
				match(L_CBRACKET);
				setState(196);
				block();
				setState(197);
				match(R_CBRACKET);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(199);
				match(KEY_LOOP);
				setState(200);
				match(KEY_FOR);
				setState(201);
				match(IDENTIFIER);
				setState(202);
				match(KEY_IN);
				setState(203);
				expr();
				setState(204);
				match(L_CBRACKET);
				setState(205);
				block();
				setState(206);
				match(R_CBRACKET);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IfthenContext extends ParserRuleContext {
		public TerminalNode KEY_IF() { return getToken(ExprParser.KEY_IF, 0); }
		public TerminalNode L_PAREN() { return getToken(ExprParser.L_PAREN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode R_PAREN() { return getToken(ExprParser.R_PAREN, 0); }
		public TerminalNode L_CBRACKET() { return getToken(ExprParser.L_CBRACKET, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode R_CBRACKET() { return getToken(ExprParser.R_CBRACKET, 0); }
		public IfthenContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifthen; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterIfthen(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitIfthen(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitIfthen(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfthenContext ifthen() throws RecognitionException {
		IfthenContext _localctx = new IfthenContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_ifthen);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(210);
			match(KEY_IF);
			setState(211);
			match(L_PAREN);
			setState(212);
			expr();
			setState(213);
			match(R_PAREN);
			setState(214);
			match(L_CBRACKET);
			setState(215);
			block();
			setState(216);
			match(R_CBRACKET);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public FactorContext factor() {
			return getRuleContext(FactorContext.class,0);
		}
		public ArithContext arith() {
			return getRuleContext(ArithContext.class,0);
		}
		public RelationContext relation() {
			return getRuleContext(RelationContext.class,0);
		}
		public LogicContext logic() {
			return getRuleContext(LogicContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_expr);
		try {
			setState(222);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(218);
				factor();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(219);
				arith(0);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(220);
				relation();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(221);
				logic();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LogicContext extends ParserRuleContext {
		public RelationContext relation() {
			return getRuleContext(RelationContext.class,0);
		}
		public TerminalNode AND() { return getToken(ExprParser.AND, 0); }
		public LogicContext logic() {
			return getRuleContext(LogicContext.class,0);
		}
		public TerminalNode OR() { return getToken(ExprParser.OR, 0); }
		public LogicContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logic; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterLogic(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitLogic(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitLogic(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LogicContext logic() throws RecognitionException {
		LogicContext _localctx = new LogicContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_logic);
		try {
			setState(233);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(224);
				relation();
				setState(225);
				match(AND);
				setState(226);
				logic();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(228);
				relation();
				setState(229);
				match(OR);
				setState(230);
				logic();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(232);
				relation();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RelationContext extends ParserRuleContext {
		public ArithContext arith() {
			return getRuleContext(ArithContext.class,0);
		}
		public TerminalNode LT() { return getToken(ExprParser.LT, 0); }
		public RelationContext relation() {
			return getRuleContext(RelationContext.class,0);
		}
		public TerminalNode LTEQ() { return getToken(ExprParser.LTEQ, 0); }
		public TerminalNode EQEQ() { return getToken(ExprParser.EQEQ, 0); }
		public TerminalNode GTEQ() { return getToken(ExprParser.GTEQ, 0); }
		public TerminalNode GT() { return getToken(ExprParser.GT, 0); }
		public TerminalNode NOTEQ() { return getToken(ExprParser.NOTEQ, 0); }
		public RelationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterRelation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitRelation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitRelation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelationContext relation() throws RecognitionException {
		RelationContext _localctx = new RelationContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_relation);
		try {
			setState(260);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(235);
				arith(0);
				setState(236);
				match(LT);
				setState(237);
				relation();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(239);
				arith(0);
				setState(240);
				match(LTEQ);
				setState(241);
				relation();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(243);
				arith(0);
				setState(244);
				match(EQEQ);
				setState(245);
				relation();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(247);
				arith(0);
				setState(248);
				match(GTEQ);
				setState(249);
				relation();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(251);
				arith(0);
				setState(252);
				match(GT);
				setState(253);
				relation();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(255);
				arith(0);
				setState(256);
				match(NOTEQ);
				setState(257);
				relation();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(259);
				arith(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArithContext extends ParserRuleContext {
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public ArithContext arith() {
			return getRuleContext(ArithContext.class,0);
		}
		public TerminalNode PLUS() { return getToken(ExprParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(ExprParser.MINUS, 0); }
		public ArithContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arith; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterArith(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitArith(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitArith(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArithContext arith() throws RecognitionException {
		return arith(0);
	}

	private ArithContext arith(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ArithContext _localctx = new ArithContext(_ctx, _parentState);
		ArithContext _prevctx = _localctx;
		int _startState = 34;
		enterRecursionRule(_localctx, 34, RULE_arith, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(263);
			term(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(273);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(271);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
					case 1:
						{
						_localctx = new ArithContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_arith);
						setState(265);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(266);
						match(PLUS);
						setState(267);
						term(0);
						}
						break;
					case 2:
						{
						_localctx = new ArithContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_arith);
						setState(268);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(269);
						match(MINUS);
						setState(270);
						term(0);
						}
						break;
					}
					} 
				}
				setState(275);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TermContext extends ParserRuleContext {
		public FactorContext factor() {
			return getRuleContext(FactorContext.class,0);
		}
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public TerminalNode MULT() { return getToken(ExprParser.MULT, 0); }
		public TerminalNode DIV() { return getToken(ExprParser.DIV, 0); }
		public TerminalNode MOD() { return getToken(ExprParser.MOD, 0); }
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		return term(0);
	}

	private TermContext term(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		TermContext _localctx = new TermContext(_ctx, _parentState);
		TermContext _prevctx = _localctx;
		int _startState = 36;
		enterRecursionRule(_localctx, 36, RULE_term, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(277);
			factor();
			}
			_ctx.stop = _input.LT(-1);
			setState(290);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(288);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
					case 1:
						{
						_localctx = new TermContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_term);
						setState(279);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(280);
						match(MULT);
						setState(281);
						factor();
						}
						break;
					case 2:
						{
						_localctx = new TermContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_term);
						setState(282);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(283);
						match(DIV);
						setState(284);
						factor();
						}
						break;
					case 3:
						{
						_localctx = new TermContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_term);
						setState(285);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(286);
						match(MOD);
						setState(287);
						factor();
						}
						break;
					}
					} 
				}
				setState(292);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FactorContext extends ParserRuleContext {
		public TerminalNode L_PAREN() { return getToken(ExprParser.L_PAREN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode R_PAREN() { return getToken(ExprParser.R_PAREN, 0); }
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public TerminalNode NOT() { return getToken(ExprParser.NOT, 0); }
		public FactorContext factor() {
			return getRuleContext(FactorContext.class,0);
		}
		public TerminalNode MINUS() { return getToken(ExprParser.MINUS, 0); }
		public TerminalNode PLUS() { return getToken(ExprParser.PLUS, 0); }
		public FactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_factor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterFactor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitFactor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitFactor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FactorContext factor() throws RecognitionException {
		FactorContext _localctx = new FactorContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_factor);
		try {
			setState(304);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case L_PAREN:
				enterOuterAlt(_localctx, 1);
				{
				setState(293);
				match(L_PAREN);
				setState(294);
				expr();
				setState(295);
				match(R_PAREN);
				}
				break;
			case L_CBRACKET:
			case CHAR:
			case STRING:
			case NUMERAL:
			case FLOAT:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(297);
				value();
				}
				break;
			case NOT:
				enterOuterAlt(_localctx, 3);
				{
				setState(298);
				match(NOT);
				setState(299);
				factor();
				}
				break;
			case MINUS:
				enterOuterAlt(_localctx, 4);
				{
				setState(300);
				match(MINUS);
				setState(301);
				factor();
				}
				break;
			case PLUS:
				enterOuterAlt(_localctx, 5);
				{
				setState(302);
				match(PLUS);
				setState(303);
				factor();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ValueContext extends ParserRuleContext {
		public TerminalNode NUMERAL() { return getToken(ExprParser.NUMERAL, 0); }
		public TerminalNode FLOAT() { return getToken(ExprParser.FLOAT, 0); }
		public TerminalNode IDENTIFIER() { return getToken(ExprParser.IDENTIFIER, 0); }
		public CallContext call() {
			return getRuleContext(CallContext.class,0);
		}
		public ArrayContext array() {
			return getRuleContext(ArrayContext.class,0);
		}
		public TerminalNode STRING() { return getToken(ExprParser.STRING, 0); }
		public TerminalNode CHAR() { return getToken(ExprParser.CHAR, 0); }
		public ClassAccessContext classAccess() {
			return getRuleContext(ClassAccessContext.class,0);
		}
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_value);
		try {
			setState(314);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(306);
				match(NUMERAL);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(307);
				match(FLOAT);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(308);
				match(IDENTIFIER);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(309);
				call();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(310);
				array();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(311);
				match(STRING);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(312);
				match(CHAR);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(313);
				classAccess();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AccessibleObjectContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(ExprParser.IDENTIFIER, 0); }
		public CallContext call() {
			return getRuleContext(CallContext.class,0);
		}
		public ArrayAccessContext arrayAccess() {
			return getRuleContext(ArrayAccessContext.class,0);
		}
		public AccessibleObjectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_accessibleObject; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterAccessibleObject(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitAccessibleObject(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitAccessibleObject(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AccessibleObjectContext accessibleObject() throws RecognitionException {
		AccessibleObjectContext _localctx = new AccessibleObjectContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_accessibleObject);
		try {
			setState(319);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(316);
				match(IDENTIFIER);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(317);
				call();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(318);
				arrayAccess();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprsContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(ExprParser.COMMA, 0); }
		public ExprsContext exprs() {
			return getRuleContext(ExprsContext.class,0);
		}
		public ExprsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterExprs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitExprs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitExprs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprsContext exprs() throws RecognitionException {
		ExprsContext _localctx = new ExprsContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_exprs);
		try {
			setState(326);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(321);
				expr();
				setState(322);
				match(COMMA);
				setState(323);
				exprs();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(325);
				expr();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeContext extends ParserRuleContext {
		public TerminalNode TYPE_INT() { return getToken(ExprParser.TYPE_INT, 0); }
		public TerminalNode TYPE_FLOAT() { return getToken(ExprParser.TYPE_FLOAT, 0); }
		public TerminalNode TYPE_CHAR() { return getToken(ExprParser.TYPE_CHAR, 0); }
		public TerminalNode TYPE_STRING() { return getToken(ExprParser.TYPE_STRING, 0); }
		public TerminalNode TYPE_VOID() { return getToken(ExprParser.TYPE_VOID, 0); }
		public TerminalNode IDENTIFIER() { return getToken(ExprParser.IDENTIFIER, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(328);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 597034813882368L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ModifierContext extends ParserRuleContext {
		public TerminalNode KEY_STATIC() { return getToken(ExprParser.KEY_STATIC, 0); }
		public ModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_modifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitModifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitModifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ModifierContext modifier() throws RecognitionException {
		ModifierContext _localctx = new ModifierContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_modifier);
		try {
			setState(332);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case KEY_STATIC:
				enterOuterAlt(_localctx, 1);
				{
				setState(330);
				match(KEY_STATIC);
				}
				break;
			case KEY_FUNC:
			case TYPE_INT:
			case TYPE_FLOAT:
			case TYPE_CHAR:
			case TYPE_STRING:
			case TYPE_VOID:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArrayContext extends ParserRuleContext {
		public TerminalNode L_CBRACKET() { return getToken(ExprParser.L_CBRACKET, 0); }
		public TerminalNode R_CBRACKET() { return getToken(ExprParser.R_CBRACKET, 0); }
		public ExprsContext exprs() {
			return getRuleContext(ExprsContext.class,0);
		}
		public ArrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_array; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitArray(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitArray(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayContext array() throws RecognitionException {
		ArrayContext _localctx = new ArrayContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_array);
		try {
			setState(340);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(334);
				match(L_CBRACKET);
				setState(335);
				match(R_CBRACKET);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(336);
				match(L_CBRACKET);
				setState(337);
				exprs();
				setState(338);
				match(R_CBRACKET);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArrayAccessContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(ExprParser.IDENTIFIER, 0); }
		public TerminalNode L_BRACKET() { return getToken(ExprParser.L_BRACKET, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode R_BRACKET() { return getToken(ExprParser.R_BRACKET, 0); }
		public ArrayAccessContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayAccess; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterArrayAccess(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitArrayAccess(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitArrayAccess(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayAccessContext arrayAccess() throws RecognitionException {
		ArrayAccessContext _localctx = new ArrayAccessContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_arrayAccess);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(342);
			match(IDENTIFIER);
			setState(343);
			match(L_BRACKET);
			setState(344);
			expr();
			setState(345);
			match(R_BRACKET);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ClassAccessContext extends ParserRuleContext {
		public AccessibleObjectContext accessibleObject() {
			return getRuleContext(AccessibleObjectContext.class,0);
		}
		public List<TerminalNode> PERIODE() { return getTokens(ExprParser.PERIODE); }
		public TerminalNode PERIODE(int i) {
			return getToken(ExprParser.PERIODE, i);
		}
		public List<AccessibleValueContext> accessibleValue() {
			return getRuleContexts(AccessibleValueContext.class);
		}
		public AccessibleValueContext accessibleValue(int i) {
			return getRuleContext(AccessibleValueContext.class,i);
		}
		public ClassAccessContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classAccess; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterClassAccess(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitClassAccess(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitClassAccess(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassAccessContext classAccess() throws RecognitionException {
		ClassAccessContext _localctx = new ClassAccessContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_classAccess);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(347);
			accessibleObject();
			setState(352);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(348);
					match(PERIODE);
					setState(349);
					accessibleValue();
					}
					} 
				}
				setState(354);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AccessibleValueContext extends ParserRuleContext {
		public CallContext call() {
			return getRuleContext(CallContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(ExprParser.IDENTIFIER, 0); }
		public AccessibleValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_accessibleValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterAccessibleValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitAccessibleValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitAccessibleValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AccessibleValueContext accessibleValue() throws RecognitionException {
		AccessibleValueContext _localctx = new AccessibleValueContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_accessibleValue);
		try {
			setState(357);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(355);
				call();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(356);
				match(IDENTIFIER);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 17:
			return arith_sempred((ArithContext)_localctx, predIndex);
		case 18:
			return term_sempred((TermContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean arith_sempred(ArithContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 3);
		case 1:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean term_sempred(TermContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 4);
		case 3:
			return precpred(_ctx, 3);
		case 4:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u00014\u0168\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0003\u0000?\b\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0003\u0001E\b\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0003\u0002R\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0003\u0003Y\b\u0003\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004"+
		"n\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005s\b\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0003\u0006\u008f\b\u0006\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003"+
		"\u0007\u0099\b\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0003\b\u00a9"+
		"\b\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0003"+
		"\t\u00b3\b\t\u0001\n\u0001\n\u0001\n\u0001\n\u0003\n\u00b9\b\n\u0001\u000b"+
		"\u0001\u000b\u0003\u000b\u00bd\b\u000b\u0001\f\u0001\f\u0001\f\u0001\f"+
		"\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0003\f\u00d1\b\f\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0003\u000e\u00df\b\u000e\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0003\u000f\u00ea\b\u000f\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0003\u0010\u0105\b\u0010"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0005\u0011\u0110\b\u0011\n\u0011"+
		"\f\u0011\u0113\t\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0005\u0012\u0121\b\u0012\n\u0012\f\u0012\u0124"+
		"\t\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0003"+
		"\u0013\u0131\b\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0003\u0014\u013b\b\u0014\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0003\u0015\u0140\b\u0015\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0003\u0016\u0147\b\u0016\u0001"+
		"\u0017\u0001\u0017\u0001\u0018\u0001\u0018\u0003\u0018\u014d\b\u0018\u0001"+
		"\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0003"+
		"\u0019\u0155\b\u0019\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001"+
		"\u001a\u0001\u001b\u0001\u001b\u0001\u001b\u0005\u001b\u015f\b\u001b\n"+
		"\u001b\f\u001b\u0162\t\u001b\u0001\u001c\u0001\u001c\u0003\u001c\u0166"+
		"\b\u001c\u0001\u001c\u0000\u0002\"$\u001d\u0000\u0002\u0004\u0006\b\n"+
		"\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.0246"+
		"8\u0000\u0001\u0002\u0000(,11\u0180\u0000>\u0001\u0000\u0000\u0000\u0002"+
		"D\u0001\u0000\u0000\u0000\u0004Q\u0001\u0000\u0000\u0000\u0006X\u0001"+
		"\u0000\u0000\u0000\bm\u0001\u0000\u0000\u0000\nr\u0001\u0000\u0000\u0000"+
		"\f\u008e\u0001\u0000\u0000\u0000\u000e\u0098\u0001\u0000\u0000\u0000\u0010"+
		"\u00a8\u0001\u0000\u0000\u0000\u0012\u00b2\u0001\u0000\u0000\u0000\u0014"+
		"\u00b8\u0001\u0000\u0000\u0000\u0016\u00bc\u0001\u0000\u0000\u0000\u0018"+
		"\u00d0\u0001\u0000\u0000\u0000\u001a\u00d2\u0001\u0000\u0000\u0000\u001c"+
		"\u00de\u0001\u0000\u0000\u0000\u001e\u00e9\u0001\u0000\u0000\u0000 \u0104"+
		"\u0001\u0000\u0000\u0000\"\u0106\u0001\u0000\u0000\u0000$\u0114\u0001"+
		"\u0000\u0000\u0000&\u0130\u0001\u0000\u0000\u0000(\u013a\u0001\u0000\u0000"+
		"\u0000*\u013f\u0001\u0000\u0000\u0000,\u0146\u0001\u0000\u0000\u0000."+
		"\u0148\u0001\u0000\u0000\u00000\u014c\u0001\u0000\u0000\u00002\u0154\u0001"+
		"\u0000\u0000\u00004\u0156\u0001\u0000\u0000\u00006\u015b\u0001\u0000\u0000"+
		"\u00008\u0165\u0001\u0000\u0000\u0000:;\u0003\u0002\u0001\u0000;<\u0005"+
		"\u0000\u0000\u0001<?\u0001\u0000\u0000\u0000=?\u0005\u0000\u0000\u0001"+
		">:\u0001\u0000\u0000\u0000>=\u0001\u0000\u0000\u0000?\u0001\u0001\u0000"+
		"\u0000\u0000@E\u0003\u0004\u0002\u0000AB\u0003\u0004\u0002\u0000BC\u0003"+
		"\u0002\u0001\u0000CE\u0001\u0000\u0000\u0000D@\u0001\u0000\u0000\u0000"+
		"DA\u0001\u0000\u0000\u0000E\u0003\u0001\u0000\u0000\u0000FR\u0003\u0006"+
		"\u0003\u0000GH\u0003\u001c\u000e\u0000HI\u0005\u0016\u0000\u0000IR\u0001"+
		"\u0000\u0000\u0000JK\u0003\n\u0005\u0000KL\u0005\u0016\u0000\u0000LR\u0001"+
		"\u0000\u0000\u0000MN\u0003\u0014\n\u0000NO\u0005\u0016\u0000\u0000OR\u0001"+
		"\u0000\u0000\u0000PR\u0003\u0016\u000b\u0000QF\u0001\u0000\u0000\u0000"+
		"QG\u0001\u0000\u0000\u0000QJ\u0001\u0000\u0000\u0000QM\u0001\u0000\u0000"+
		"\u0000QP\u0001\u0000\u0000\u0000R\u0005\u0001\u0000\u0000\u0000ST\u0003"+
		"\b\u0004\u0000TU\u0005\u0016\u0000\u0000UY\u0001\u0000\u0000\u0000VY\u0003"+
		"\f\u0006\u0000WY\u0003\u0010\b\u0000XS\u0001\u0000\u0000\u0000XV\u0001"+
		"\u0000\u0000\u0000XW\u0001\u0000\u0000\u0000Y\u0007\u0001\u0000\u0000"+
		"\u0000Z[\u00030\u0018\u0000[\\\u0003.\u0017\u0000\\]\u00051\u0000\u0000"+
		"]^\u0005\u0001\u0000\u0000^_\u0003\u001c\u000e\u0000_n\u0001\u0000\u0000"+
		"\u0000`a\u00030\u0018\u0000ab\u0003.\u0017\u0000bc\u00051\u0000\u0000"+
		"cd\u0005\u0014\u0000\u0000de\u0005/\u0000\u0000ef\u0005\u0015\u0000\u0000"+
		"fg\u0005\u0001\u0000\u0000gh\u0003\u001c\u000e\u0000hn\u0001\u0000\u0000"+
		"\u0000ij\u00030\u0018\u0000jk\u0003.\u0017\u0000kl\u00051\u0000\u0000"+
		"ln\u0001\u0000\u0000\u0000mZ\u0001\u0000\u0000\u0000m`\u0001\u0000\u0000"+
		"\u0000mi\u0001\u0000\u0000\u0000n\t\u0001\u0000\u0000\u0000os\u00034\u001a"+
		"\u0000ps\u00036\u001b\u0000qs\u00051\u0000\u0000ro\u0001\u0000\u0000\u0000"+
		"rp\u0001\u0000\u0000\u0000rq\u0001\u0000\u0000\u0000st\u0001\u0000\u0000"+
		"\u0000tu\u0005\u0001\u0000\u0000uv\u0003\u001c\u000e\u0000v\u000b\u0001"+
		"\u0000\u0000\u0000wx\u00030\u0018\u0000xy\u0005\u001b\u0000\u0000yz\u0005"+
		"1\u0000\u0000z{\u0005\u0010\u0000\u0000{|\u0003\u000e\u0007\u0000|}\u0005"+
		"\u0011\u0000\u0000}~\u0005&\u0000\u0000~\u007f\u0003.\u0017\u0000\u007f"+
		"\u0080\u0005\u0012\u0000\u0000\u0080\u0081\u0003\u0002\u0001\u0000\u0081"+
		"\u0082\u0005\u0013\u0000\u0000\u0082\u008f\u0001\u0000\u0000\u0000\u0083"+
		"\u0084\u00030\u0018\u0000\u0084\u0085\u0005\u001b\u0000\u0000\u0085\u0086"+
		"\u00051\u0000\u0000\u0086\u0087\u0005\u0010\u0000\u0000\u0087\u0088\u0005"+
		"\u0011\u0000\u0000\u0088\u0089\u0005&\u0000\u0000\u0089\u008a\u0003.\u0017"+
		"\u0000\u008a\u008b\u0005\u0012\u0000\u0000\u008b\u008c\u0003\u0002\u0001"+
		"\u0000\u008c\u008d\u0005\u0013\u0000\u0000\u008d\u008f\u0001\u0000\u0000"+
		"\u0000\u008ew\u0001\u0000\u0000\u0000\u008e\u0083\u0001\u0000\u0000\u0000"+
		"\u008f\r\u0001\u0000\u0000\u0000\u0090\u0091\u0003.\u0017\u0000\u0091"+
		"\u0092\u00051\u0000\u0000\u0092\u0099\u0001\u0000\u0000\u0000\u0093\u0094"+
		"\u0003.\u0017\u0000\u0094\u0095\u00051\u0000\u0000\u0095\u0096\u0005\u0017"+
		"\u0000\u0000\u0096\u0097\u0003\u000e\u0007\u0000\u0097\u0099\u0001\u0000"+
		"\u0000\u0000\u0098\u0090\u0001\u0000\u0000\u0000\u0098\u0093\u0001\u0000"+
		"\u0000\u0000\u0099\u000f\u0001\u0000\u0000\u0000\u009a\u009b\u0005\u001a"+
		"\u0000\u0000\u009b\u009c\u00051\u0000\u0000\u009c\u009d\u0005\u0012\u0000"+
		"\u0000\u009d\u009e\u0003\u0002\u0001\u0000\u009e\u009f\u0005\u0013\u0000"+
		"\u0000\u009f\u00a9\u0001\u0000\u0000\u0000\u00a0\u00a1\u0005\u001a\u0000"+
		"\u0000\u00a1\u00a2\u00051\u0000\u0000\u00a2\u00a3\u0005\u001c\u0000\u0000"+
		"\u00a3\u00a4\u00051\u0000\u0000\u00a4\u00a5\u0005\u0012\u0000\u0000\u00a5"+
		"\u00a6\u0003\u0002\u0001\u0000\u00a6\u00a7\u0005\u0013\u0000\u0000\u00a7"+
		"\u00a9\u0001\u0000\u0000\u0000\u00a8\u009a\u0001\u0000\u0000\u0000\u00a8"+
		"\u00a0\u0001\u0000\u0000\u0000\u00a9\u0011\u0001\u0000\u0000\u0000\u00aa"+
		"\u00ab\u00051\u0000\u0000\u00ab\u00ac\u0005\u0010\u0000\u0000\u00ac\u00ad"+
		"\u0003,\u0016\u0000\u00ad\u00ae\u0005\u0011\u0000\u0000\u00ae\u00b3\u0001"+
		"\u0000\u0000\u0000\u00af\u00b0\u00051\u0000\u0000\u00b0\u00b1\u0005\u0010"+
		"\u0000\u0000\u00b1\u00b3\u0005\u0011\u0000\u0000\u00b2\u00aa\u0001\u0000"+
		"\u0000\u0000\u00b2\u00af\u0001\u0000\u0000\u0000\u00b3\u0013\u0001\u0000"+
		"\u0000\u0000\u00b4\u00b5\u0005\u001e\u0000\u0000\u00b5\u00b9\u0003\u001c"+
		"\u000e\u0000\u00b6\u00b9\u0005\u001d\u0000\u0000\u00b7\u00b9\u0005\u001f"+
		"\u0000\u0000\u00b8\u00b4\u0001\u0000\u0000\u0000\u00b8\u00b6\u0001\u0000"+
		"\u0000\u0000\u00b8\u00b7\u0001\u0000\u0000\u0000\u00b9\u0015\u0001\u0000"+
		"\u0000\u0000\u00ba\u00bd\u0003\u0018\f\u0000\u00bb\u00bd\u0003\u001a\r"+
		"\u0000\u00bc\u00ba\u0001\u0000\u0000\u0000\u00bc\u00bb\u0001\u0000\u0000"+
		"\u0000\u00bd\u0017\u0001\u0000\u0000\u0000\u00be\u00bf\u0005$\u0000\u0000"+
		"\u00bf\u00c0\u0005\"\u0000\u0000\u00c0\u00c1\u0005\u0010\u0000\u0000\u00c1"+
		"\u00c2\u0003\u001c\u000e\u0000\u00c2\u00c3\u0005\u0011\u0000\u0000\u00c3"+
		"\u00c4\u0005\u0012\u0000\u0000\u00c4\u00c5\u0003\u0002\u0001\u0000\u00c5"+
		"\u00c6\u0005\u0013\u0000\u0000\u00c6\u00d1\u0001\u0000\u0000\u0000\u00c7"+
		"\u00c8\u0005$\u0000\u0000\u00c8\u00c9\u0005!\u0000\u0000\u00c9\u00ca\u0005"+
		"1\u0000\u0000\u00ca\u00cb\u0005 \u0000\u0000\u00cb\u00cc\u0003\u001c\u000e"+
		"\u0000\u00cc\u00cd\u0005\u0012\u0000\u0000\u00cd\u00ce\u0003\u0002\u0001"+
		"\u0000\u00ce\u00cf\u0005\u0013\u0000\u0000\u00cf\u00d1\u0001\u0000\u0000"+
		"\u0000\u00d0\u00be\u0001\u0000\u0000\u0000\u00d0\u00c7\u0001\u0000\u0000"+
		"\u0000\u00d1\u0019\u0001\u0000\u0000\u0000\u00d2\u00d3\u0005#\u0000\u0000"+
		"\u00d3\u00d4\u0005\u0010\u0000\u0000\u00d4\u00d5\u0003\u001c\u000e\u0000"+
		"\u00d5\u00d6\u0005\u0011\u0000\u0000\u00d6\u00d7\u0005\u0012\u0000\u0000"+
		"\u00d7\u00d8\u0003\u0002\u0001\u0000\u00d8\u00d9\u0005\u0013\u0000\u0000"+
		"\u00d9\u001b\u0001\u0000\u0000\u0000\u00da\u00df\u0003&\u0013\u0000\u00db"+
		"\u00df\u0003\"\u0011\u0000\u00dc\u00df\u0003 \u0010\u0000\u00dd\u00df"+
		"\u0003\u001e\u000f\u0000\u00de\u00da\u0001\u0000\u0000\u0000\u00de\u00db"+
		"\u0001\u0000\u0000\u0000\u00de\u00dc\u0001\u0000\u0000\u0000\u00de\u00dd"+
		"\u0001\u0000\u0000\u0000\u00df\u001d\u0001\u0000\u0000\u0000\u00e0\u00e1"+
		"\u0003 \u0010\u0000\u00e1\u00e2\u0005\t\u0000\u0000\u00e2\u00e3\u0003"+
		"\u001e\u000f\u0000\u00e3\u00ea\u0001\u0000\u0000\u0000\u00e4\u00e5\u0003"+
		" \u0010\u0000\u00e5\u00e6\u0005\n\u0000\u0000\u00e6\u00e7\u0003\u001e"+
		"\u000f\u0000\u00e7\u00ea\u0001\u0000\u0000\u0000\u00e8\u00ea\u0003 \u0010"+
		"\u0000\u00e9\u00e0\u0001\u0000\u0000\u0000\u00e9\u00e4\u0001\u0000\u0000"+
		"\u0000\u00e9\u00e8\u0001\u0000\u0000\u0000\u00ea\u001f\u0001\u0000\u0000"+
		"\u0000\u00eb\u00ec\u0003\"\u0011\u0000\u00ec\u00ed\u0005\u0003\u0000\u0000"+
		"\u00ed\u00ee\u0003 \u0010\u0000\u00ee\u0105\u0001\u0000\u0000\u0000\u00ef"+
		"\u00f0\u0003\"\u0011\u0000\u00f0\u00f1\u0005\u0006\u0000\u0000\u00f1\u00f2"+
		"\u0003 \u0010\u0000\u00f2\u0105\u0001\u0000\u0000\u0000\u00f3\u00f4\u0003"+
		"\"\u0011\u0000\u00f4\u00f5\u0005\u0005\u0000\u0000\u00f5\u00f6\u0003 "+
		"\u0010\u0000\u00f6\u0105\u0001\u0000\u0000\u0000\u00f7\u00f8\u0003\"\u0011"+
		"\u0000\u00f8\u00f9\u0005\u0007\u0000\u0000\u00f9\u00fa\u0003 \u0010\u0000"+
		"\u00fa\u0105\u0001\u0000\u0000\u0000\u00fb\u00fc\u0003\"\u0011\u0000\u00fc"+
		"\u00fd\u0005\u0002\u0000\u0000\u00fd\u00fe\u0003 \u0010\u0000\u00fe\u0105"+
		"\u0001\u0000\u0000\u0000\u00ff\u0100\u0003\"\u0011\u0000\u0100\u0101\u0005"+
		"\b\u0000\u0000\u0101\u0102\u0003 \u0010\u0000\u0102\u0105\u0001\u0000"+
		"\u0000\u0000\u0103\u0105\u0003\"\u0011\u0000\u0104\u00eb\u0001\u0000\u0000"+
		"\u0000\u0104\u00ef\u0001\u0000\u0000\u0000\u0104\u00f3\u0001\u0000\u0000"+
		"\u0000\u0104\u00f7\u0001\u0000\u0000\u0000\u0104\u00fb\u0001\u0000\u0000"+
		"\u0000\u0104\u00ff\u0001\u0000\u0000\u0000\u0104\u0103\u0001\u0000\u0000"+
		"\u0000\u0105!\u0001\u0000\u0000\u0000\u0106\u0107\u0006\u0011\uffff\uffff"+
		"\u0000\u0107\u0108\u0003$\u0012\u0000\u0108\u0111\u0001\u0000\u0000\u0000"+
		"\u0109\u010a\n\u0003\u0000\u0000\u010a\u010b\u0005\u000b\u0000\u0000\u010b"+
		"\u0110\u0003$\u0012\u0000\u010c\u010d\n\u0002\u0000\u0000\u010d\u010e"+
		"\u0005\f\u0000\u0000\u010e\u0110\u0003$\u0012\u0000\u010f\u0109\u0001"+
		"\u0000\u0000\u0000\u010f\u010c\u0001\u0000\u0000\u0000\u0110\u0113\u0001"+
		"\u0000\u0000\u0000\u0111\u010f\u0001\u0000\u0000\u0000\u0111\u0112\u0001"+
		"\u0000\u0000\u0000\u0112#\u0001\u0000\u0000\u0000\u0113\u0111\u0001\u0000"+
		"\u0000\u0000\u0114\u0115\u0006\u0012\uffff\uffff\u0000\u0115\u0116\u0003"+
		"&\u0013\u0000\u0116\u0122\u0001\u0000\u0000\u0000\u0117\u0118\n\u0004"+
		"\u0000\u0000\u0118\u0119\u0005\r\u0000\u0000\u0119\u0121\u0003&\u0013"+
		"\u0000\u011a\u011b\n\u0003\u0000\u0000\u011b\u011c\u0005\u000e\u0000\u0000"+
		"\u011c\u0121\u0003&\u0013\u0000\u011d\u011e\n\u0002\u0000\u0000\u011e"+
		"\u011f\u0005\u000f\u0000\u0000\u011f\u0121\u0003&\u0013\u0000\u0120\u0117"+
		"\u0001\u0000\u0000\u0000\u0120\u011a\u0001\u0000\u0000\u0000\u0120\u011d"+
		"\u0001\u0000\u0000\u0000\u0121\u0124\u0001\u0000\u0000\u0000\u0122\u0120"+
		"\u0001\u0000\u0000\u0000\u0122\u0123\u0001\u0000\u0000\u0000\u0123%\u0001"+
		"\u0000\u0000\u0000\u0124\u0122\u0001\u0000\u0000\u0000\u0125\u0126\u0005"+
		"\u0010\u0000\u0000\u0126\u0127\u0003\u001c\u000e\u0000\u0127\u0128\u0005"+
		"\u0011\u0000\u0000\u0128\u0131\u0001\u0000\u0000\u0000\u0129\u0131\u0003"+
		"(\u0014\u0000\u012a\u012b\u0005\u0004\u0000\u0000\u012b\u0131\u0003&\u0013"+
		"\u0000\u012c\u012d\u0005\f\u0000\u0000\u012d\u0131\u0003&\u0013\u0000"+
		"\u012e\u012f\u0005\u000b\u0000\u0000\u012f\u0131\u0003&\u0013\u0000\u0130"+
		"\u0125\u0001\u0000\u0000\u0000\u0130\u0129\u0001\u0000\u0000\u0000\u0130"+
		"\u012a\u0001\u0000\u0000\u0000\u0130\u012c\u0001\u0000\u0000\u0000\u0130"+
		"\u012e\u0001\u0000\u0000\u0000\u0131\'\u0001\u0000\u0000\u0000\u0132\u013b"+
		"\u0005/\u0000\u0000\u0133\u013b\u00050\u0000\u0000\u0134\u013b\u00051"+
		"\u0000\u0000\u0135\u013b\u0003\u0012\t\u0000\u0136\u013b\u00032\u0019"+
		"\u0000\u0137\u013b\u0005.\u0000\u0000\u0138\u013b\u0005-\u0000\u0000\u0139"+
		"\u013b\u00036\u001b\u0000\u013a\u0132\u0001\u0000\u0000\u0000\u013a\u0133"+
		"\u0001\u0000\u0000\u0000\u013a\u0134\u0001\u0000\u0000\u0000\u013a\u0135"+
		"\u0001\u0000\u0000\u0000\u013a\u0136\u0001\u0000\u0000\u0000\u013a\u0137"+
		"\u0001\u0000\u0000\u0000\u013a\u0138\u0001\u0000\u0000\u0000\u013a\u0139"+
		"\u0001\u0000\u0000\u0000\u013b)\u0001\u0000\u0000\u0000\u013c\u0140\u0005"+
		"1\u0000\u0000\u013d\u0140\u0003\u0012\t\u0000\u013e\u0140\u00034\u001a"+
		"\u0000\u013f\u013c\u0001\u0000\u0000\u0000\u013f\u013d\u0001\u0000\u0000"+
		"\u0000\u013f\u013e\u0001\u0000\u0000\u0000\u0140+\u0001\u0000\u0000\u0000"+
		"\u0141\u0142\u0003\u001c\u000e\u0000\u0142\u0143\u0005\u0017\u0000\u0000"+
		"\u0143\u0144\u0003,\u0016\u0000\u0144\u0147\u0001\u0000\u0000\u0000\u0145"+
		"\u0147\u0003\u001c\u000e\u0000\u0146\u0141\u0001\u0000\u0000\u0000\u0146"+
		"\u0145\u0001\u0000\u0000\u0000\u0147-\u0001\u0000\u0000\u0000\u0148\u0149"+
		"\u0007\u0000\u0000\u0000\u0149/\u0001\u0000\u0000\u0000\u014a\u014d\u0005"+
		"%\u0000\u0000\u014b\u014d\u0001\u0000\u0000\u0000\u014c\u014a\u0001\u0000"+
		"\u0000\u0000\u014c\u014b\u0001\u0000\u0000\u0000\u014d1\u0001\u0000\u0000"+
		"\u0000\u014e\u014f\u0005\u0012\u0000\u0000\u014f\u0155\u0005\u0013\u0000"+
		"\u0000\u0150\u0151\u0005\u0012\u0000\u0000\u0151\u0152\u0003,\u0016\u0000"+
		"\u0152\u0153\u0005\u0013\u0000\u0000\u0153\u0155\u0001\u0000\u0000\u0000"+
		"\u0154\u014e\u0001\u0000\u0000\u0000\u0154\u0150\u0001\u0000\u0000\u0000"+
		"\u01553\u0001\u0000\u0000\u0000\u0156\u0157\u00051\u0000\u0000\u0157\u0158"+
		"\u0005\u0014\u0000\u0000\u0158\u0159\u0003\u001c\u000e\u0000\u0159\u015a"+
		"\u0005\u0015\u0000\u0000\u015a5\u0001\u0000\u0000\u0000\u015b\u0160\u0003"+
		"*\u0015\u0000\u015c\u015d\u0005\u0018\u0000\u0000\u015d\u015f\u00038\u001c"+
		"\u0000\u015e\u015c\u0001\u0000\u0000\u0000\u015f\u0162\u0001\u0000\u0000"+
		"\u0000\u0160\u015e\u0001\u0000\u0000\u0000\u0160\u0161\u0001\u0000\u0000"+
		"\u0000\u01617\u0001\u0000\u0000\u0000\u0162\u0160\u0001\u0000\u0000\u0000"+
		"\u0163\u0166\u0003\u0012\t\u0000\u0164\u0166\u00051\u0000\u0000\u0165"+
		"\u0163\u0001\u0000\u0000\u0000\u0165\u0164\u0001\u0000\u0000\u0000\u0166"+
		"9\u0001\u0000\u0000\u0000\u001c>DQXmr\u008e\u0098\u00a8\u00b2\u00b8\u00bc"+
		"\u00d0\u00de\u00e9\u0104\u010f\u0111\u0120\u0122\u0130\u013a\u013f\u0146"+
		"\u014c\u0154\u0160\u0165";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}