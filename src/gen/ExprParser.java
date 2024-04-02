// Generated from C:/Users/sinag/IdeaProjects/p4-project/src/Expr.g4 by ANTLR 4.13.1
package gen;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

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
		RULE_assign = 5, RULE_fdecl = 6, RULE_fparam = 7, RULE_aparam = 8, RULE_cdecl = 9, 
		RULE_call = 10, RULE_command = 11, RULE_control = 12, RULE_loop = 13, 
		RULE_ifthen = 14, RULE_expr = 15, RULE_logic = 16, RULE_relation = 17, 
		RULE_arith = 18, RULE_term = 19, RULE_factor = 20, RULE_value = 21, RULE_acessibleValue = 22, 
		RULE_values = 23, RULE_type = 24, RULE_modifier = 25, RULE_array = 26, 
		RULE_arrayAccess = 27, RULE_classAccess = 28;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "block", "statement", "decl", "defin", "assign", "fdecl", "fparam", 
			"aparam", "cdecl", "call", "command", "control", "loop", "ifthen", "expr", 
			"logic", "relation", "arith", "term", "factor", "value", "acessibleValue", 
			"values", "type", "modifier", "array", "arrayAccess", "classAccess"
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
		public TerminalNode IDENTIFIER() { return getToken(ExprParser.IDENTIFIER, 0); }
		public TerminalNode EQ() { return getToken(ExprParser.EQ, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
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
			setState(111);
			match(IDENTIFIER);
			setState(112);
			match(EQ);
			setState(113);
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
			setState(138);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(115);
				modifier();
				setState(116);
				match(KEY_FUNC);
				setState(117);
				match(IDENTIFIER);
				setState(118);
				match(L_PAREN);
				setState(119);
				fparam();
				setState(120);
				match(R_PAREN);
				setState(121);
				match(KEY_RETURNTYPE_ARROW);
				setState(122);
				type();
				setState(123);
				match(L_CBRACKET);
				setState(124);
				block();
				setState(125);
				match(R_CBRACKET);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(127);
				modifier();
				setState(128);
				match(KEY_FUNC);
				setState(129);
				match(IDENTIFIER);
				setState(130);
				match(L_PAREN);
				setState(131);
				match(R_PAREN);
				setState(132);
				match(KEY_RETURNTYPE_ARROW);
				setState(133);
				type();
				setState(134);
				match(L_CBRACKET);
				setState(135);
				block();
				setState(136);
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
			setState(148);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(140);
				type();
				setState(141);
				match(IDENTIFIER);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(143);
				type();
				setState(144);
				match(IDENTIFIER);
				setState(145);
				match(COMMA);
				setState(146);
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
	public static class AparamContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(ExprParser.COMMA, 0); }
		public AparamContext aparam() {
			return getRuleContext(AparamContext.class,0);
		}
		public AparamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aparam; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterAparam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitAparam(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitAparam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AparamContext aparam() throws RecognitionException {
		AparamContext _localctx = new AparamContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_aparam);
		try {
			setState(155);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(150);
				expr();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(151);
				expr();
				setState(152);
				match(COMMA);
				setState(153);
				aparam();
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
		enterRule(_localctx, 18, RULE_cdecl);
		try {
			setState(171);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(157);
				match(KEY_CLASS);
				setState(158);
				match(IDENTIFIER);
				setState(159);
				match(L_CBRACKET);
				setState(160);
				block();
				setState(161);
				match(R_CBRACKET);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(163);
				match(KEY_CLASS);
				setState(164);
				match(IDENTIFIER);
				setState(165);
				match(KEY_EXTENDS);
				setState(166);
				match(IDENTIFIER);
				setState(167);
				match(L_CBRACKET);
				setState(168);
				block();
				setState(169);
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
		public AparamContext aparam() {
			return getRuleContext(AparamContext.class,0);
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
		enterRule(_localctx, 20, RULE_call);
		try {
			setState(181);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(173);
				match(IDENTIFIER);
				setState(174);
				match(L_PAREN);
				setState(175);
				aparam();
				setState(176);
				match(R_PAREN);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(178);
				match(IDENTIFIER);
				setState(179);
				match(L_PAREN);
				setState(180);
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
		enterRule(_localctx, 22, RULE_command);
		try {
			setState(187);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case KEY_RETURN:
				enterOuterAlt(_localctx, 1);
				{
				setState(183);
				match(KEY_RETURN);
				setState(184);
				expr();
				}
				break;
			case KEY_BREAK:
				enterOuterAlt(_localctx, 2);
				{
				setState(185);
				match(KEY_BREAK);
				}
				break;
			case KEY_CONTINUE:
				enterOuterAlt(_localctx, 3);
				{
				setState(186);
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
		enterRule(_localctx, 24, RULE_control);
		try {
			setState(191);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case KEY_LOOP:
				enterOuterAlt(_localctx, 1);
				{
				setState(189);
				loop();
				}
				break;
			case KEY_IF:
				enterOuterAlt(_localctx, 2);
				{
				setState(190);
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
		public List<TerminalNode> IDENTIFIER() { return getTokens(ExprParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(ExprParser.IDENTIFIER, i);
		}
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
		enterRule(_localctx, 26, RULE_loop);
		try {
			setState(211);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(193);
				match(KEY_LOOP);
				setState(194);
				match(KEY_WHILE);
				setState(195);
				match(L_PAREN);
				setState(196);
				expr();
				setState(197);
				match(R_PAREN);
				setState(198);
				match(L_CBRACKET);
				setState(199);
				block();
				setState(200);
				match(R_CBRACKET);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(202);
				match(KEY_LOOP);
				setState(203);
				match(KEY_FOR);
				setState(204);
				match(IDENTIFIER);
				setState(205);
				match(KEY_IN);
				setState(206);
				match(IDENTIFIER);
				setState(207);
				match(L_CBRACKET);
				setState(208);
				block();
				setState(209);
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
		enterRule(_localctx, 28, RULE_ifthen);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(213);
			match(KEY_IF);
			setState(214);
			match(L_PAREN);
			setState(215);
			expr();
			setState(216);
			match(R_PAREN);
			setState(217);
			match(L_CBRACKET);
			setState(218);
			block();
			setState(219);
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
		public LogicContext logic() {
			return getRuleContext(LogicContext.class,0);
		}
		public RelationContext relation() {
			return getRuleContext(RelationContext.class,0);
		}
		public ArithContext arith() {
			return getRuleContext(ArithContext.class,0);
		}
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
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
		enterRule(_localctx, 30, RULE_expr);
		try {
			setState(225);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(221);
				logic();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(222);
				relation();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(223);
				arith();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(224);
				value();
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
		enterRule(_localctx, 32, RULE_logic);
		try {
			setState(236);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(227);
				relation();
				setState(228);
				match(AND);
				setState(229);
				logic();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(231);
				relation();
				setState(232);
				match(OR);
				setState(233);
				logic();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(235);
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
		enterRule(_localctx, 34, RULE_relation);
		try {
			setState(263);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(238);
				arith();
				setState(239);
				match(LT);
				setState(240);
				relation();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(242);
				arith();
				setState(243);
				match(LTEQ);
				setState(244);
				relation();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(246);
				arith();
				setState(247);
				match(EQEQ);
				setState(248);
				relation();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(250);
				arith();
				setState(251);
				match(GTEQ);
				setState(252);
				relation();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(254);
				arith();
				setState(255);
				match(GT);
				setState(256);
				relation();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(258);
				arith();
				setState(259);
				match(NOTEQ);
				setState(260);
				relation();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(262);
				arith();
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
		public TerminalNode PLUS() { return getToken(ExprParser.PLUS, 0); }
		public ArithContext arith() {
			return getRuleContext(ArithContext.class,0);
		}
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
		ArithContext _localctx = new ArithContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_arith);
		try {
			setState(274);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(265);
				term();
				setState(266);
				match(PLUS);
				setState(267);
				arith();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(269);
				term();
				setState(270);
				match(MINUS);
				setState(271);
				arith();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(273);
				term();
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
	public static class TermContext extends ParserRuleContext {
		public FactorContext factor() {
			return getRuleContext(FactorContext.class,0);
		}
		public TerminalNode MULT() { return getToken(ExprParser.MULT, 0); }
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
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
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_term);
		try {
			setState(289);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(276);
				factor();
				setState(277);
				match(MULT);
				setState(278);
				term();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(280);
				factor();
				setState(281);
				match(DIV);
				setState(282);
				term();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(284);
				factor();
				setState(285);
				match(MOD);
				setState(286);
				term();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(288);
				factor();
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
		enterRule(_localctx, 40, RULE_factor);
		try {
			setState(302);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case L_PAREN:
				enterOuterAlt(_localctx, 1);
				{
				setState(291);
				match(L_PAREN);
				setState(292);
				expr();
				setState(293);
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
				setState(295);
				value();
				}
				break;
			case NOT:
				enterOuterAlt(_localctx, 3);
				{
				setState(296);
				match(NOT);
				setState(297);
				factor();
				}
				break;
			case MINUS:
				enterOuterAlt(_localctx, 4);
				{
				setState(298);
				match(MINUS);
				setState(299);
				factor();
				}
				break;
			case PLUS:
				enterOuterAlt(_localctx, 5);
				{
				setState(300);
				match(PLUS);
				setState(301);
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
		enterRule(_localctx, 42, RULE_value);
		try {
			setState(312);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(304);
				match(NUMERAL);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(305);
				match(FLOAT);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(306);
				match(IDENTIFIER);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(307);
				call();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(308);
				array();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(309);
				match(STRING);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(310);
				match(CHAR);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(311);
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
	public static class AcessibleValueContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(ExprParser.IDENTIFIER, 0); }
		public CallContext call() {
			return getRuleContext(CallContext.class,0);
		}
		public ArrayAccessContext arrayAccess() {
			return getRuleContext(ArrayAccessContext.class,0);
		}
		public AcessibleValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_acessibleValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterAcessibleValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitAcessibleValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitAcessibleValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AcessibleValueContext acessibleValue() throws RecognitionException {
		AcessibleValueContext _localctx = new AcessibleValueContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_acessibleValue);
		try {
			setState(317);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(314);
				match(IDENTIFIER);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(315);
				call();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(316);
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
	public static class ValuesContext extends ParserRuleContext {
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(ExprParser.COMMA, 0); }
		public ValuesContext values() {
			return getRuleContext(ValuesContext.class,0);
		}
		public ValuesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_values; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterValues(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitValues(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitValues(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValuesContext values() throws RecognitionException {
		ValuesContext _localctx = new ValuesContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_values);
		try {
			setState(324);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(319);
				value();
				setState(320);
				match(COMMA);
				setState(321);
				values();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(323);
				value();
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
		enterRule(_localctx, 48, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(326);
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
		enterRule(_localctx, 50, RULE_modifier);
		try {
			setState(330);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case KEY_STATIC:
				enterOuterAlt(_localctx, 1);
				{
				setState(328);
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
		public ValuesContext values() {
			return getRuleContext(ValuesContext.class,0);
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
		enterRule(_localctx, 52, RULE_array);
		try {
			setState(338);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(332);
				match(L_CBRACKET);
				setState(333);
				match(R_CBRACKET);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(334);
				match(L_CBRACKET);
				setState(335);
				values();
				setState(336);
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
		enterRule(_localctx, 54, RULE_arrayAccess);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(340);
			match(IDENTIFIER);
			setState(341);
			match(L_BRACKET);
			setState(342);
			expr();
			setState(343);
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
		public AcessibleValueContext acessibleValue() {
			return getRuleContext(AcessibleValueContext.class,0);
		}
		public List<TerminalNode> PERIODE() { return getTokens(ExprParser.PERIODE); }
		public TerminalNode PERIODE(int i) {
			return getToken(ExprParser.PERIODE, i);
		}
		public List<CallContext> call() {
			return getRuleContexts(CallContext.class);
		}
		public CallContext call(int i) {
			return getRuleContext(CallContext.class,i);
		}
		public List<TerminalNode> IDENTIFIER() { return getTokens(ExprParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(ExprParser.IDENTIFIER, i);
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
		enterRule(_localctx, 56, RULE_classAccess);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(345);
			acessibleValue();
			setState(350); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(350);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
				case 1:
					{
					setState(346);
					match(PERIODE);
					setState(347);
					call();
					}
					break;
				case 2:
					{
					setState(348);
					match(PERIODE);
					setState(349);
					match(IDENTIFIER);
					}
					break;
				}
				}
				setState(352); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==PERIODE );
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

	public static final String _serializedATN =
		"\u0004\u00014\u0163\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
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
		"n\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006\u008b\b\u0006"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0003\u0007\u0095\b\u0007\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0003\b\u009c\b\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0003\t\u00ac\b\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0003\n\u00b6\b\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0003\u000b\u00bc\b\u000b\u0001\f\u0001\f\u0003\f\u00c0\b\f\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0003"+
		"\r\u00d4\b\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0003\u000f\u00e2\b\u000f\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0003\u0010\u00ed\b\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0003\u0011\u0108\b\u0011\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0003\u0012\u0113\b\u0012\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0003\u0013"+
		"\u0122\b\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0003\u0014\u012f\b\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0003\u0015\u0139\b\u0015"+
		"\u0001\u0016\u0001\u0016\u0001\u0016\u0003\u0016\u013e\b\u0016\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0003\u0017\u0145\b\u0017"+
		"\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019\u0003\u0019\u014b\b\u0019"+
		"\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a"+
		"\u0003\u001a\u0153\b\u001a\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b"+
		"\u0001\u001b\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c"+
		"\u0004\u001c\u015f\b\u001c\u000b\u001c\f\u001c\u0160\u0001\u001c\u0000"+
		"\u0000\u001d\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016"+
		"\u0018\u001a\u001c\u001e \"$&(*,.02468\u0000\u0001\u0002\u0000(,11\u017a"+
		"\u0000>\u0001\u0000\u0000\u0000\u0002D\u0001\u0000\u0000\u0000\u0004Q"+
		"\u0001\u0000\u0000\u0000\u0006X\u0001\u0000\u0000\u0000\bm\u0001\u0000"+
		"\u0000\u0000\no\u0001\u0000\u0000\u0000\f\u008a\u0001\u0000\u0000\u0000"+
		"\u000e\u0094\u0001\u0000\u0000\u0000\u0010\u009b\u0001\u0000\u0000\u0000"+
		"\u0012\u00ab\u0001\u0000\u0000\u0000\u0014\u00b5\u0001\u0000\u0000\u0000"+
		"\u0016\u00bb\u0001\u0000\u0000\u0000\u0018\u00bf\u0001\u0000\u0000\u0000"+
		"\u001a\u00d3\u0001\u0000\u0000\u0000\u001c\u00d5\u0001\u0000\u0000\u0000"+
		"\u001e\u00e1\u0001\u0000\u0000\u0000 \u00ec\u0001\u0000\u0000\u0000\""+
		"\u0107\u0001\u0000\u0000\u0000$\u0112\u0001\u0000\u0000\u0000&\u0121\u0001"+
		"\u0000\u0000\u0000(\u012e\u0001\u0000\u0000\u0000*\u0138\u0001\u0000\u0000"+
		"\u0000,\u013d\u0001\u0000\u0000\u0000.\u0144\u0001\u0000\u0000\u00000"+
		"\u0146\u0001\u0000\u0000\u00002\u014a\u0001\u0000\u0000\u00004\u0152\u0001"+
		"\u0000\u0000\u00006\u0154\u0001\u0000\u0000\u00008\u0159\u0001\u0000\u0000"+
		"\u0000:;\u0003\u0002\u0001\u0000;<\u0005\u0000\u0000\u0001<?\u0001\u0000"+
		"\u0000\u0000=?\u0005\u0000\u0000\u0001>:\u0001\u0000\u0000\u0000>=\u0001"+
		"\u0000\u0000\u0000?\u0001\u0001\u0000\u0000\u0000@E\u0003\u0004\u0002"+
		"\u0000AB\u0003\u0004\u0002\u0000BC\u0003\u0002\u0001\u0000CE\u0001\u0000"+
		"\u0000\u0000D@\u0001\u0000\u0000\u0000DA\u0001\u0000\u0000\u0000E\u0003"+
		"\u0001\u0000\u0000\u0000FR\u0003\u0006\u0003\u0000GH\u0003\u001e\u000f"+
		"\u0000HI\u0005\u0016\u0000\u0000IR\u0001\u0000\u0000\u0000JK\u0003\n\u0005"+
		"\u0000KL\u0005\u0016\u0000\u0000LR\u0001\u0000\u0000\u0000MN\u0003\u0016"+
		"\u000b\u0000NO\u0005\u0016\u0000\u0000OR\u0001\u0000\u0000\u0000PR\u0003"+
		"\u0018\f\u0000QF\u0001\u0000\u0000\u0000QG\u0001\u0000\u0000\u0000QJ\u0001"+
		"\u0000\u0000\u0000QM\u0001\u0000\u0000\u0000QP\u0001\u0000\u0000\u0000"+
		"R\u0005\u0001\u0000\u0000\u0000ST\u0003\b\u0004\u0000TU\u0005\u0016\u0000"+
		"\u0000UY\u0001\u0000\u0000\u0000VY\u0003\f\u0006\u0000WY\u0003\u0012\t"+
		"\u0000XS\u0001\u0000\u0000\u0000XV\u0001\u0000\u0000\u0000XW\u0001\u0000"+
		"\u0000\u0000Y\u0007\u0001\u0000\u0000\u0000Z[\u00032\u0019\u0000[\\\u0003"+
		"0\u0018\u0000\\]\u00051\u0000\u0000]^\u0005\u0001\u0000\u0000^_\u0003"+
		"\u001e\u000f\u0000_n\u0001\u0000\u0000\u0000`a\u00032\u0019\u0000ab\u0003"+
		"0\u0018\u0000bc\u00051\u0000\u0000cd\u0005\u0014\u0000\u0000de\u0005/"+
		"\u0000\u0000ef\u0005\u0015\u0000\u0000fg\u0005\u0001\u0000\u0000gh\u0003"+
		"\u001e\u000f\u0000hn\u0001\u0000\u0000\u0000ij\u00032\u0019\u0000jk\u0003"+
		"0\u0018\u0000kl\u00051\u0000\u0000ln\u0001\u0000\u0000\u0000mZ\u0001\u0000"+
		"\u0000\u0000m`\u0001\u0000\u0000\u0000mi\u0001\u0000\u0000\u0000n\t\u0001"+
		"\u0000\u0000\u0000op\u00051\u0000\u0000pq\u0005\u0001\u0000\u0000qr\u0003"+
		"\u001e\u000f\u0000r\u000b\u0001\u0000\u0000\u0000st\u00032\u0019\u0000"+
		"tu\u0005\u001b\u0000\u0000uv\u00051\u0000\u0000vw\u0005\u0010\u0000\u0000"+
		"wx\u0003\u000e\u0007\u0000xy\u0005\u0011\u0000\u0000yz\u0005&\u0000\u0000"+
		"z{\u00030\u0018\u0000{|\u0005\u0012\u0000\u0000|}\u0003\u0002\u0001\u0000"+
		"}~\u0005\u0013\u0000\u0000~\u008b\u0001\u0000\u0000\u0000\u007f\u0080"+
		"\u00032\u0019\u0000\u0080\u0081\u0005\u001b\u0000\u0000\u0081\u0082\u0005"+
		"1\u0000\u0000\u0082\u0083\u0005\u0010\u0000\u0000\u0083\u0084\u0005\u0011"+
		"\u0000\u0000\u0084\u0085\u0005&\u0000\u0000\u0085\u0086\u00030\u0018\u0000"+
		"\u0086\u0087\u0005\u0012\u0000\u0000\u0087\u0088\u0003\u0002\u0001\u0000"+
		"\u0088\u0089\u0005\u0013\u0000\u0000\u0089\u008b\u0001\u0000\u0000\u0000"+
		"\u008as\u0001\u0000\u0000\u0000\u008a\u007f\u0001\u0000\u0000\u0000\u008b"+
		"\r\u0001\u0000\u0000\u0000\u008c\u008d\u00030\u0018\u0000\u008d\u008e"+
		"\u00051\u0000\u0000\u008e\u0095\u0001\u0000\u0000\u0000\u008f\u0090\u0003"+
		"0\u0018\u0000\u0090\u0091\u00051\u0000\u0000\u0091\u0092\u0005\u0017\u0000"+
		"\u0000\u0092\u0093\u0003\u000e\u0007\u0000\u0093\u0095\u0001\u0000\u0000"+
		"\u0000\u0094\u008c\u0001\u0000\u0000\u0000\u0094\u008f\u0001\u0000\u0000"+
		"\u0000\u0095\u000f\u0001\u0000\u0000\u0000\u0096\u009c\u0003\u001e\u000f"+
		"\u0000\u0097\u0098\u0003\u001e\u000f\u0000\u0098\u0099\u0005\u0017\u0000"+
		"\u0000\u0099\u009a\u0003\u0010\b\u0000\u009a\u009c\u0001\u0000\u0000\u0000"+
		"\u009b\u0096\u0001\u0000\u0000\u0000\u009b\u0097\u0001\u0000\u0000\u0000"+
		"\u009c\u0011\u0001\u0000\u0000\u0000\u009d\u009e\u0005\u001a\u0000\u0000"+
		"\u009e\u009f\u00051\u0000\u0000\u009f\u00a0\u0005\u0012\u0000\u0000\u00a0"+
		"\u00a1\u0003\u0002\u0001\u0000\u00a1\u00a2\u0005\u0013\u0000\u0000\u00a2"+
		"\u00ac\u0001\u0000\u0000\u0000\u00a3\u00a4\u0005\u001a\u0000\u0000\u00a4"+
		"\u00a5\u00051\u0000\u0000\u00a5\u00a6\u0005\u001c\u0000\u0000\u00a6\u00a7"+
		"\u00051\u0000\u0000\u00a7\u00a8\u0005\u0012\u0000\u0000\u00a8\u00a9\u0003"+
		"\u0002\u0001\u0000\u00a9\u00aa\u0005\u0013\u0000\u0000\u00aa\u00ac\u0001"+
		"\u0000\u0000\u0000\u00ab\u009d\u0001\u0000\u0000\u0000\u00ab\u00a3\u0001"+
		"\u0000\u0000\u0000\u00ac\u0013\u0001\u0000\u0000\u0000\u00ad\u00ae\u0005"+
		"1\u0000\u0000\u00ae\u00af\u0005\u0010\u0000\u0000\u00af\u00b0\u0003\u0010"+
		"\b\u0000\u00b0\u00b1\u0005\u0011\u0000\u0000\u00b1\u00b6\u0001\u0000\u0000"+
		"\u0000\u00b2\u00b3\u00051\u0000\u0000\u00b3\u00b4\u0005\u0010\u0000\u0000"+
		"\u00b4\u00b6\u0005\u0011\u0000\u0000\u00b5\u00ad\u0001\u0000\u0000\u0000"+
		"\u00b5\u00b2\u0001\u0000\u0000\u0000\u00b6\u0015\u0001\u0000\u0000\u0000"+
		"\u00b7\u00b8\u0005\u001e\u0000\u0000\u00b8\u00bc\u0003\u001e\u000f\u0000"+
		"\u00b9\u00bc\u0005\u001d\u0000\u0000\u00ba\u00bc\u0005\u001f\u0000\u0000"+
		"\u00bb\u00b7\u0001\u0000\u0000\u0000\u00bb\u00b9\u0001\u0000\u0000\u0000"+
		"\u00bb\u00ba\u0001\u0000\u0000\u0000\u00bc\u0017\u0001\u0000\u0000\u0000"+
		"\u00bd\u00c0\u0003\u001a\r\u0000\u00be\u00c0\u0003\u001c\u000e\u0000\u00bf"+
		"\u00bd\u0001\u0000\u0000\u0000\u00bf\u00be\u0001\u0000\u0000\u0000\u00c0"+
		"\u0019\u0001\u0000\u0000\u0000\u00c1\u00c2\u0005$\u0000\u0000\u00c2\u00c3"+
		"\u0005\"\u0000\u0000\u00c3\u00c4\u0005\u0010\u0000\u0000\u00c4\u00c5\u0003"+
		"\u001e\u000f\u0000\u00c5\u00c6\u0005\u0011\u0000\u0000\u00c6\u00c7\u0005"+
		"\u0012\u0000\u0000\u00c7\u00c8\u0003\u0002\u0001\u0000\u00c8\u00c9\u0005"+
		"\u0013\u0000\u0000\u00c9\u00d4\u0001\u0000\u0000\u0000\u00ca\u00cb\u0005"+
		"$\u0000\u0000\u00cb\u00cc\u0005!\u0000\u0000\u00cc\u00cd\u00051\u0000"+
		"\u0000\u00cd\u00ce\u0005 \u0000\u0000\u00ce\u00cf\u00051\u0000\u0000\u00cf"+
		"\u00d0\u0005\u0012\u0000\u0000\u00d0\u00d1\u0003\u0002\u0001\u0000\u00d1"+
		"\u00d2\u0005\u0013\u0000\u0000\u00d2\u00d4\u0001\u0000\u0000\u0000\u00d3"+
		"\u00c1\u0001\u0000\u0000\u0000\u00d3\u00ca\u0001\u0000\u0000\u0000\u00d4"+
		"\u001b\u0001\u0000\u0000\u0000\u00d5\u00d6\u0005#\u0000\u0000\u00d6\u00d7"+
		"\u0005\u0010\u0000\u0000\u00d7\u00d8\u0003\u001e\u000f\u0000\u00d8\u00d9"+
		"\u0005\u0011\u0000\u0000\u00d9\u00da\u0005\u0012\u0000\u0000\u00da\u00db"+
		"\u0003\u0002\u0001\u0000\u00db\u00dc\u0005\u0013\u0000\u0000\u00dc\u001d"+
		"\u0001\u0000\u0000\u0000\u00dd\u00e2\u0003 \u0010\u0000\u00de\u00e2\u0003"+
		"\"\u0011\u0000\u00df\u00e2\u0003$\u0012\u0000\u00e0\u00e2\u0003*\u0015"+
		"\u0000\u00e1\u00dd\u0001\u0000\u0000\u0000\u00e1\u00de\u0001\u0000\u0000"+
		"\u0000\u00e1\u00df\u0001\u0000\u0000\u0000\u00e1\u00e0\u0001\u0000\u0000"+
		"\u0000\u00e2\u001f\u0001\u0000\u0000\u0000\u00e3\u00e4\u0003\"\u0011\u0000"+
		"\u00e4\u00e5\u0005\t\u0000\u0000\u00e5\u00e6\u0003 \u0010\u0000\u00e6"+
		"\u00ed\u0001\u0000\u0000\u0000\u00e7\u00e8\u0003\"\u0011\u0000\u00e8\u00e9"+
		"\u0005\n\u0000\u0000\u00e9\u00ea\u0003 \u0010\u0000\u00ea\u00ed\u0001"+
		"\u0000\u0000\u0000\u00eb\u00ed\u0003\"\u0011\u0000\u00ec\u00e3\u0001\u0000"+
		"\u0000\u0000\u00ec\u00e7\u0001\u0000\u0000\u0000\u00ec\u00eb\u0001\u0000"+
		"\u0000\u0000\u00ed!\u0001\u0000\u0000\u0000\u00ee\u00ef\u0003$\u0012\u0000"+
		"\u00ef\u00f0\u0005\u0003\u0000\u0000\u00f0\u00f1\u0003\"\u0011\u0000\u00f1"+
		"\u0108\u0001\u0000\u0000\u0000\u00f2\u00f3\u0003$\u0012\u0000\u00f3\u00f4"+
		"\u0005\u0006\u0000\u0000\u00f4\u00f5\u0003\"\u0011\u0000\u00f5\u0108\u0001"+
		"\u0000\u0000\u0000\u00f6\u00f7\u0003$\u0012\u0000\u00f7\u00f8\u0005\u0005"+
		"\u0000\u0000\u00f8\u00f9\u0003\"\u0011\u0000\u00f9\u0108\u0001\u0000\u0000"+
		"\u0000\u00fa\u00fb\u0003$\u0012\u0000\u00fb\u00fc\u0005\u0007\u0000\u0000"+
		"\u00fc\u00fd\u0003\"\u0011\u0000\u00fd\u0108\u0001\u0000\u0000\u0000\u00fe"+
		"\u00ff\u0003$\u0012\u0000\u00ff\u0100\u0005\u0002\u0000\u0000\u0100\u0101"+
		"\u0003\"\u0011\u0000\u0101\u0108\u0001\u0000\u0000\u0000\u0102\u0103\u0003"+
		"$\u0012\u0000\u0103\u0104\u0005\b\u0000\u0000\u0104\u0105\u0003\"\u0011"+
		"\u0000\u0105\u0108\u0001\u0000\u0000\u0000\u0106\u0108\u0003$\u0012\u0000"+
		"\u0107\u00ee\u0001\u0000\u0000\u0000\u0107\u00f2\u0001\u0000\u0000\u0000"+
		"\u0107\u00f6\u0001\u0000\u0000\u0000\u0107\u00fa\u0001\u0000\u0000\u0000"+
		"\u0107\u00fe\u0001\u0000\u0000\u0000\u0107\u0102\u0001\u0000\u0000\u0000"+
		"\u0107\u0106\u0001\u0000\u0000\u0000\u0108#\u0001\u0000\u0000\u0000\u0109"+
		"\u010a\u0003&\u0013\u0000\u010a\u010b\u0005\u000b\u0000\u0000\u010b\u010c"+
		"\u0003$\u0012\u0000\u010c\u0113\u0001\u0000\u0000\u0000\u010d\u010e\u0003"+
		"&\u0013\u0000\u010e\u010f\u0005\f\u0000\u0000\u010f\u0110\u0003$\u0012"+
		"\u0000\u0110\u0113\u0001\u0000\u0000\u0000\u0111\u0113\u0003&\u0013\u0000"+
		"\u0112\u0109\u0001\u0000\u0000\u0000\u0112\u010d\u0001\u0000\u0000\u0000"+
		"\u0112\u0111\u0001\u0000\u0000\u0000\u0113%\u0001\u0000\u0000\u0000\u0114"+
		"\u0115\u0003(\u0014\u0000\u0115\u0116\u0005\r\u0000\u0000\u0116\u0117"+
		"\u0003&\u0013\u0000\u0117\u0122\u0001\u0000\u0000\u0000\u0118\u0119\u0003"+
		"(\u0014\u0000\u0119\u011a\u0005\u000e\u0000\u0000\u011a\u011b\u0003&\u0013"+
		"\u0000\u011b\u0122\u0001\u0000\u0000\u0000\u011c\u011d\u0003(\u0014\u0000"+
		"\u011d\u011e\u0005\u000f\u0000\u0000\u011e\u011f\u0003&\u0013\u0000\u011f"+
		"\u0122\u0001\u0000\u0000\u0000\u0120\u0122\u0003(\u0014\u0000\u0121\u0114"+
		"\u0001\u0000\u0000\u0000\u0121\u0118\u0001\u0000\u0000\u0000\u0121\u011c"+
		"\u0001\u0000\u0000\u0000\u0121\u0120\u0001\u0000\u0000\u0000\u0122\'\u0001"+
		"\u0000\u0000\u0000\u0123\u0124\u0005\u0010\u0000\u0000\u0124\u0125\u0003"+
		"\u001e\u000f\u0000\u0125\u0126\u0005\u0011\u0000\u0000\u0126\u012f\u0001"+
		"\u0000\u0000\u0000\u0127\u012f\u0003*\u0015\u0000\u0128\u0129\u0005\u0004"+
		"\u0000\u0000\u0129\u012f\u0003(\u0014\u0000\u012a\u012b\u0005\f\u0000"+
		"\u0000\u012b\u012f\u0003(\u0014\u0000\u012c\u012d\u0005\u000b\u0000\u0000"+
		"\u012d\u012f\u0003(\u0014\u0000\u012e\u0123\u0001\u0000\u0000\u0000\u012e"+
		"\u0127\u0001\u0000\u0000\u0000\u012e\u0128\u0001\u0000\u0000\u0000\u012e"+
		"\u012a\u0001\u0000\u0000\u0000\u012e\u012c\u0001\u0000\u0000\u0000\u012f"+
		")\u0001\u0000\u0000\u0000\u0130\u0139\u0005/\u0000\u0000\u0131\u0139\u0005"+
		"0\u0000\u0000\u0132\u0139\u00051\u0000\u0000\u0133\u0139\u0003\u0014\n"+
		"\u0000\u0134\u0139\u00034\u001a\u0000\u0135\u0139\u0005.\u0000\u0000\u0136"+
		"\u0139\u0005-\u0000\u0000\u0137\u0139\u00038\u001c\u0000\u0138\u0130\u0001"+
		"\u0000\u0000\u0000\u0138\u0131\u0001\u0000\u0000\u0000\u0138\u0132\u0001"+
		"\u0000\u0000\u0000\u0138\u0133\u0001\u0000\u0000\u0000\u0138\u0134\u0001"+
		"\u0000\u0000\u0000\u0138\u0135\u0001\u0000\u0000\u0000\u0138\u0136\u0001"+
		"\u0000\u0000\u0000\u0138\u0137\u0001\u0000\u0000\u0000\u0139+\u0001\u0000"+
		"\u0000\u0000\u013a\u013e\u00051\u0000\u0000\u013b\u013e\u0003\u0014\n"+
		"\u0000\u013c\u013e\u00036\u001b\u0000\u013d\u013a\u0001\u0000\u0000\u0000"+
		"\u013d\u013b\u0001\u0000\u0000\u0000\u013d\u013c\u0001\u0000\u0000\u0000"+
		"\u013e-\u0001\u0000\u0000\u0000\u013f\u0140\u0003*\u0015\u0000\u0140\u0141"+
		"\u0005\u0017\u0000\u0000\u0141\u0142\u0003.\u0017\u0000\u0142\u0145\u0001"+
		"\u0000\u0000\u0000\u0143\u0145\u0003*\u0015\u0000\u0144\u013f\u0001\u0000"+
		"\u0000\u0000\u0144\u0143\u0001\u0000\u0000\u0000\u0145/\u0001\u0000\u0000"+
		"\u0000\u0146\u0147\u0007\u0000\u0000\u0000\u01471\u0001\u0000\u0000\u0000"+
		"\u0148\u014b\u0005%\u0000\u0000\u0149\u014b\u0001\u0000\u0000\u0000\u014a"+
		"\u0148\u0001\u0000\u0000\u0000\u014a\u0149\u0001\u0000\u0000\u0000\u014b"+
		"3\u0001\u0000\u0000\u0000\u014c\u014d\u0005\u0012\u0000\u0000\u014d\u0153"+
		"\u0005\u0013\u0000\u0000\u014e\u014f\u0005\u0012\u0000\u0000\u014f\u0150"+
		"\u0003.\u0017\u0000\u0150\u0151\u0005\u0013\u0000\u0000\u0151\u0153\u0001"+
		"\u0000\u0000\u0000\u0152\u014c\u0001\u0000\u0000\u0000\u0152\u014e\u0001"+
		"\u0000\u0000\u0000\u01535\u0001\u0000\u0000\u0000\u0154\u0155\u00051\u0000"+
		"\u0000\u0155\u0156\u0005\u0014\u0000\u0000\u0156\u0157\u0003\u001e\u000f"+
		"\u0000\u0157\u0158\u0005\u0015\u0000\u0000\u01587\u0001\u0000\u0000\u0000"+
		"\u0159\u015e\u0003,\u0016\u0000\u015a\u015b\u0005\u0018\u0000\u0000\u015b"+
		"\u015f\u0003\u0014\n\u0000\u015c\u015d\u0005\u0018\u0000\u0000\u015d\u015f"+
		"\u00051\u0000\u0000\u015e\u015a\u0001\u0000\u0000\u0000\u015e\u015c\u0001"+
		"\u0000\u0000\u0000\u015f\u0160\u0001\u0000\u0000\u0000\u0160\u015e\u0001"+
		"\u0000\u0000\u0000\u0160\u0161\u0001\u0000\u0000\u0000\u01619\u0001\u0000"+
		"\u0000\u0000\u001a>DQXm\u008a\u0094\u009b\u00ab\u00b5\u00bb\u00bf\u00d3"+
		"\u00e1\u00ec\u0107\u0112\u0121\u012e\u0138\u013d\u0144\u014a\u0152\u015e"+
		"\u0160";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}