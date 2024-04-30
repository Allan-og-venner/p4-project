package gen;// Generated from C:/Users/sinag/IdeaProjects/p4-project/src/Expr.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class ExprLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		EQ=1, GT=2, LT=3, NOT=4, EQEQ=5, LTEQ=6, GTEQ=7, NOTEQ=8, AND=9, OR=10, 
		PLUS=11, MINUS=12, MULT=13, DIV=14, MOD=15, L_PAREN=16, R_PAREN=17, L_CBRACKET=18, 
		R_CBRACKET=19, L_BRACKET=20, R_BRACKET=21, SEMICOLON=22, COMMA=23, COLON=24, 
		PERIOD=25, KEY_NEW=26, KEY_CLASS=27, KEY_FUNC=28, KEY_EXTENDS=29, KEY_BREAK=30, 
		KEY_RETURN=31, KEY_CONTINUE=32, KEY_IN=33, KEY_FOR=34, KEY_WHILE=35, KEY_IF=36, 
		KEY_LOOP=37, KEY_STATIC=38, KEY_RETURNTYPE_ARROW=39, KEY_NULL=40, KEY_ACTION=41, 
		KEY_CARDTYPE=42, KEY_SETUP=43, TYPE_INT=44, TYPE_FLOAT=45, TYPE_CHAR=46, 
		TYPE_STRING=47, TYPE_VOID=48, CHAR=49, STRING=50, NUMERAL=51, FLOAT=52, 
		IDENTIFIER=53, BLOCK_COMMENT=54, WS=55, NEWLINE=56;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"EQ", "GT", "LT", "NOT", "EQEQ", "LTEQ", "GTEQ", "NOTEQ", "AND", "OR", 
			"PLUS", "MINUS", "MULT", "DIV", "MOD", "L_PAREN", "R_PAREN", "L_CBRACKET", 
			"R_CBRACKET", "L_BRACKET", "R_BRACKET", "SEMICOLON", "COMMA", "COLON", 
			"PERIOD", "KEY_NEW", "KEY_CLASS", "KEY_FUNC", "KEY_EXTENDS", "KEY_BREAK", 
			"KEY_RETURN", "KEY_CONTINUE", "KEY_IN", "KEY_FOR", "KEY_WHILE", "KEY_IF", 
			"KEY_LOOP", "KEY_STATIC", "KEY_RETURNTYPE_ARROW", "KEY_NULL", "KEY_ACTION", 
			"KEY_CARDTYPE", "KEY_SETUP", "TYPE_INT", "TYPE_FLOAT", "TYPE_CHAR", "TYPE_STRING", 
			"TYPE_VOID", "CHAR", "STRING", "NUMERAL", "FLOAT", "IDENTIFIER", "BLOCK_COMMENT", 
			"WS", "NEWLINE"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'='", "'>'", "'<'", "'!'", "'=='", "'<='", "'>='", "'!='", "'&&'", 
			"'||'", "'+'", "'-'", "'*'", "'/'", "'%'", "'('", "')'", "'{'", "'}'", 
			"'['", "']'", "';'", "','", "':'", "'.'", "'new'", "'class'", "'func'", 
			"'extends'", "'break'", "'return'", "'continue'", "'in'", "'for'", "'while'", 
			"'if'", "'loop'", "'static'", "'->'", "'null'", "'action'", "'cardType'", 
			"'setup'", "'int'", "'float'", "'char'", "'string'", "'void'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "EQ", "GT", "LT", "NOT", "EQEQ", "LTEQ", "GTEQ", "NOTEQ", "AND", 
			"OR", "PLUS", "MINUS", "MULT", "DIV", "MOD", "L_PAREN", "R_PAREN", "L_CBRACKET", 
			"R_CBRACKET", "L_BRACKET", "R_BRACKET", "SEMICOLON", "COMMA", "COLON", 
			"PERIOD", "KEY_NEW", "KEY_CLASS", "KEY_FUNC", "KEY_EXTENDS", "KEY_BREAK", 
			"KEY_RETURN", "KEY_CONTINUE", "KEY_IN", "KEY_FOR", "KEY_WHILE", "KEY_IF", 
			"KEY_LOOP", "KEY_STATIC", "KEY_RETURNTYPE_ARROW", "KEY_NULL", "KEY_ACTION", 
			"KEY_CARDTYPE", "KEY_SETUP", "TYPE_INT", "TYPE_FLOAT", "TYPE_CHAR", "TYPE_STRING", 
			"TYPE_VOID", "CHAR", "STRING", "NUMERAL", "FLOAT", "IDENTIFIER", "BLOCK_COMMENT", 
			"WS", "NEWLINE"
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


	public ExprLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Expr.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u00008\u0176\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002"+
		"\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002"+
		"\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002"+
		"\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002"+
		"\u001b\u0007\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002"+
		"\u001e\u0007\u001e\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007"+
		"!\u0002\"\u0007\"\u0002#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007"+
		"&\u0002\'\u0007\'\u0002(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007"+
		"+\u0002,\u0007,\u0002-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u0007"+
		"0\u00021\u00071\u00022\u00072\u00023\u00073\u00024\u00074\u00025\u0007"+
		"5\u00026\u00076\u00027\u00077\u0001\u0000\u0001\u0000\u0001\u0001\u0001"+
		"\u0001\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b"+
		"\u0001\b\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\u000b\u0001\u000b"+
		"\u0001\f\u0001\f\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000f\u0001"+
		"\u000f\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0012\u0001"+
		"\u0012\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0015\u0001"+
		"\u0015\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0001\u0018\u0001"+
		"\u0018\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u001a\u0001"+
		"\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001b\u0001"+
		"\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001c\u0001\u001c\u0001"+
		"\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001"+
		"\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001"+
		"\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001"+
		"\u001e\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001"+
		"\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001 \u0001 \u0001 \u0001"+
		"!\u0001!\u0001!\u0001!\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\""+
		"\u0001#\u0001#\u0001#\u0001$\u0001$\u0001$\u0001$\u0001$\u0001%\u0001"+
		"%\u0001%\u0001%\u0001%\u0001%\u0001%\u0001&\u0001&\u0001&\u0001\'\u0001"+
		"\'\u0001\'\u0001\'\u0001\'\u0001(\u0001(\u0001(\u0001(\u0001(\u0001(\u0001"+
		"(\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001"+
		"*\u0001*\u0001*\u0001*\u0001*\u0001*\u0001+\u0001+\u0001+\u0001+\u0001"+
		",\u0001,\u0001,\u0001,\u0001,\u0001,\u0001-\u0001-\u0001-\u0001-\u0001"+
		"-\u0001.\u0001.\u0001.\u0001.\u0001.\u0001.\u0001.\u0001/\u0001/\u0001"+
		"/\u0001/\u0001/\u00010\u00010\u00030\u012e\b0\u00010\u00010\u00011\u0001"+
		"1\u00051\u0134\b1\n1\f1\u0137\t1\u00011\u00011\u00012\u00042\u013c\b2"+
		"\u000b2\f2\u013d\u00013\u00033\u0141\b3\u00013\u00013\u00053\u0145\b3"+
		"\n3\f3\u0148\t3\u00013\u00013\u00013\u00053\u014d\b3\n3\f3\u0150\t3\u0003"+
		"3\u0152\b3\u00014\u00014\u00054\u0156\b4\n4\f4\u0159\t4\u00015\u00015"+
		"\u00015\u00015\u00055\u015f\b5\n5\f5\u0162\t5\u00015\u00015\u00015\u0001"+
		"5\u00015\u00016\u00046\u016a\b6\u000b6\f6\u016b\u00016\u00016\u00017\u0004"+
		"7\u0171\b7\u000b7\f7\u0172\u00017\u00017\u0003\u0135\u014e\u0160\u0000"+
		"8\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006"+
		"\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e"+
		"\u001d\u000f\u001f\u0010!\u0011#\u0012%\u0013\'\u0014)\u0015+\u0016-\u0017"+
		"/\u00181\u00193\u001a5\u001b7\u001c9\u001d;\u001e=\u001f? A!C\"E#G$I%"+
		"K&M\'O(Q)S*U+W,Y-[.]/_0a1c2e3g4i5k6m7o8\u0001\u0000\u0005\u0001\u0000"+
		"09\u0002\u0000AZaz\u0005\u0000--09AZ__az\u0002\u0000\t\t  \u0002\u0000"+
		"\n\n\r\r\u0180\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000"+
		"\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000"+
		"\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000"+
		"\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000"+
		"\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000"+
		"\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000"+
		"\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000"+
		"\u0000\u001d\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000\u0000\u0000"+
		"\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000\u0000%"+
		"\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000\u0000\u0000\u0000)\u0001"+
		"\u0000\u0000\u0000\u0000+\u0001\u0000\u0000\u0000\u0000-\u0001\u0000\u0000"+
		"\u0000\u0000/\u0001\u0000\u0000\u0000\u00001\u0001\u0000\u0000\u0000\u0000"+
		"3\u0001\u0000\u0000\u0000\u00005\u0001\u0000\u0000\u0000\u00007\u0001"+
		"\u0000\u0000\u0000\u00009\u0001\u0000\u0000\u0000\u0000;\u0001\u0000\u0000"+
		"\u0000\u0000=\u0001\u0000\u0000\u0000\u0000?\u0001\u0000\u0000\u0000\u0000"+
		"A\u0001\u0000\u0000\u0000\u0000C\u0001\u0000\u0000\u0000\u0000E\u0001"+
		"\u0000\u0000\u0000\u0000G\u0001\u0000\u0000\u0000\u0000I\u0001\u0000\u0000"+
		"\u0000\u0000K\u0001\u0000\u0000\u0000\u0000M\u0001\u0000\u0000\u0000\u0000"+
		"O\u0001\u0000\u0000\u0000\u0000Q\u0001\u0000\u0000\u0000\u0000S\u0001"+
		"\u0000\u0000\u0000\u0000U\u0001\u0000\u0000\u0000\u0000W\u0001\u0000\u0000"+
		"\u0000\u0000Y\u0001\u0000\u0000\u0000\u0000[\u0001\u0000\u0000\u0000\u0000"+
		"]\u0001\u0000\u0000\u0000\u0000_\u0001\u0000\u0000\u0000\u0000a\u0001"+
		"\u0000\u0000\u0000\u0000c\u0001\u0000\u0000\u0000\u0000e\u0001\u0000\u0000"+
		"\u0000\u0000g\u0001\u0000\u0000\u0000\u0000i\u0001\u0000\u0000\u0000\u0000"+
		"k\u0001\u0000\u0000\u0000\u0000m\u0001\u0000\u0000\u0000\u0000o\u0001"+
		"\u0000\u0000\u0000\u0001q\u0001\u0000\u0000\u0000\u0003s\u0001\u0000\u0000"+
		"\u0000\u0005u\u0001\u0000\u0000\u0000\u0007w\u0001\u0000\u0000\u0000\t"+
		"y\u0001\u0000\u0000\u0000\u000b|\u0001\u0000\u0000\u0000\r\u007f\u0001"+
		"\u0000\u0000\u0000\u000f\u0082\u0001\u0000\u0000\u0000\u0011\u0085\u0001"+
		"\u0000\u0000\u0000\u0013\u0088\u0001\u0000\u0000\u0000\u0015\u008b\u0001"+
		"\u0000\u0000\u0000\u0017\u008d\u0001\u0000\u0000\u0000\u0019\u008f\u0001"+
		"\u0000\u0000\u0000\u001b\u0091\u0001\u0000\u0000\u0000\u001d\u0093\u0001"+
		"\u0000\u0000\u0000\u001f\u0095\u0001\u0000\u0000\u0000!\u0097\u0001\u0000"+
		"\u0000\u0000#\u0099\u0001\u0000\u0000\u0000%\u009b\u0001\u0000\u0000\u0000"+
		"\'\u009d\u0001\u0000\u0000\u0000)\u009f\u0001\u0000\u0000\u0000+\u00a1"+
		"\u0001\u0000\u0000\u0000-\u00a3\u0001\u0000\u0000\u0000/\u00a5\u0001\u0000"+
		"\u0000\u00001\u00a7\u0001\u0000\u0000\u00003\u00a9\u0001\u0000\u0000\u0000"+
		"5\u00ad\u0001\u0000\u0000\u00007\u00b3\u0001\u0000\u0000\u00009\u00b8"+
		"\u0001\u0000\u0000\u0000;\u00c0\u0001\u0000\u0000\u0000=\u00c6\u0001\u0000"+
		"\u0000\u0000?\u00cd\u0001\u0000\u0000\u0000A\u00d6\u0001\u0000\u0000\u0000"+
		"C\u00d9\u0001\u0000\u0000\u0000E\u00dd\u0001\u0000\u0000\u0000G\u00e3"+
		"\u0001\u0000\u0000\u0000I\u00e6\u0001\u0000\u0000\u0000K\u00eb\u0001\u0000"+
		"\u0000\u0000M\u00f2\u0001\u0000\u0000\u0000O\u00f5\u0001\u0000\u0000\u0000"+
		"Q\u00fa\u0001\u0000\u0000\u0000S\u0101\u0001\u0000\u0000\u0000U\u010a"+
		"\u0001\u0000\u0000\u0000W\u0110\u0001\u0000\u0000\u0000Y\u0114\u0001\u0000"+
		"\u0000\u0000[\u011a\u0001\u0000\u0000\u0000]\u011f\u0001\u0000\u0000\u0000"+
		"_\u0126\u0001\u0000\u0000\u0000a\u012b\u0001\u0000\u0000\u0000c\u0131"+
		"\u0001\u0000\u0000\u0000e\u013b\u0001\u0000\u0000\u0000g\u0151\u0001\u0000"+
		"\u0000\u0000i\u0153\u0001\u0000\u0000\u0000k\u015a\u0001\u0000\u0000\u0000"+
		"m\u0169\u0001\u0000\u0000\u0000o\u0170\u0001\u0000\u0000\u0000qr\u0005"+
		"=\u0000\u0000r\u0002\u0001\u0000\u0000\u0000st\u0005>\u0000\u0000t\u0004"+
		"\u0001\u0000\u0000\u0000uv\u0005<\u0000\u0000v\u0006\u0001\u0000\u0000"+
		"\u0000wx\u0005!\u0000\u0000x\b\u0001\u0000\u0000\u0000yz\u0005=\u0000"+
		"\u0000z{\u0005=\u0000\u0000{\n\u0001\u0000\u0000\u0000|}\u0005<\u0000"+
		"\u0000}~\u0005=\u0000\u0000~\f\u0001\u0000\u0000\u0000\u007f\u0080\u0005"+
		">\u0000\u0000\u0080\u0081\u0005=\u0000\u0000\u0081\u000e\u0001\u0000\u0000"+
		"\u0000\u0082\u0083\u0005!\u0000\u0000\u0083\u0084\u0005=\u0000\u0000\u0084"+
		"\u0010\u0001\u0000\u0000\u0000\u0085\u0086\u0005&\u0000\u0000\u0086\u0087"+
		"\u0005&\u0000\u0000\u0087\u0012\u0001\u0000\u0000\u0000\u0088\u0089\u0005"+
		"|\u0000\u0000\u0089\u008a\u0005|\u0000\u0000\u008a\u0014\u0001\u0000\u0000"+
		"\u0000\u008b\u008c\u0005+\u0000\u0000\u008c\u0016\u0001\u0000\u0000\u0000"+
		"\u008d\u008e\u0005-\u0000\u0000\u008e\u0018\u0001\u0000\u0000\u0000\u008f"+
		"\u0090\u0005*\u0000\u0000\u0090\u001a\u0001\u0000\u0000\u0000\u0091\u0092"+
		"\u0005/\u0000\u0000\u0092\u001c\u0001\u0000\u0000\u0000\u0093\u0094\u0005"+
		"%\u0000\u0000\u0094\u001e\u0001\u0000\u0000\u0000\u0095\u0096\u0005(\u0000"+
		"\u0000\u0096 \u0001\u0000\u0000\u0000\u0097\u0098\u0005)\u0000\u0000\u0098"+
		"\"\u0001\u0000\u0000\u0000\u0099\u009a\u0005{\u0000\u0000\u009a$\u0001"+
		"\u0000\u0000\u0000\u009b\u009c\u0005}\u0000\u0000\u009c&\u0001\u0000\u0000"+
		"\u0000\u009d\u009e\u0005[\u0000\u0000\u009e(\u0001\u0000\u0000\u0000\u009f"+
		"\u00a0\u0005]\u0000\u0000\u00a0*\u0001\u0000\u0000\u0000\u00a1\u00a2\u0005"+
		";\u0000\u0000\u00a2,\u0001\u0000\u0000\u0000\u00a3\u00a4\u0005,\u0000"+
		"\u0000\u00a4.\u0001\u0000\u0000\u0000\u00a5\u00a6\u0005:\u0000\u0000\u00a6"+
		"0\u0001\u0000\u0000\u0000\u00a7\u00a8\u0005.\u0000\u0000\u00a82\u0001"+
		"\u0000\u0000\u0000\u00a9\u00aa\u0005n\u0000\u0000\u00aa\u00ab\u0005e\u0000"+
		"\u0000\u00ab\u00ac\u0005w\u0000\u0000\u00ac4\u0001\u0000\u0000\u0000\u00ad"+
		"\u00ae\u0005c\u0000\u0000\u00ae\u00af\u0005l\u0000\u0000\u00af\u00b0\u0005"+
		"a\u0000\u0000\u00b0\u00b1\u0005s\u0000\u0000\u00b1\u00b2\u0005s\u0000"+
		"\u0000\u00b26\u0001\u0000\u0000\u0000\u00b3\u00b4\u0005f\u0000\u0000\u00b4"+
		"\u00b5\u0005u\u0000\u0000\u00b5\u00b6\u0005n\u0000\u0000\u00b6\u00b7\u0005"+
		"c\u0000\u0000\u00b78\u0001\u0000\u0000\u0000\u00b8\u00b9\u0005e\u0000"+
		"\u0000\u00b9\u00ba\u0005x\u0000\u0000\u00ba\u00bb\u0005t\u0000\u0000\u00bb"+
		"\u00bc\u0005e\u0000\u0000\u00bc\u00bd\u0005n\u0000\u0000\u00bd\u00be\u0005"+
		"d\u0000\u0000\u00be\u00bf\u0005s\u0000\u0000\u00bf:\u0001\u0000\u0000"+
		"\u0000\u00c0\u00c1\u0005b\u0000\u0000\u00c1\u00c2\u0005r\u0000\u0000\u00c2"+
		"\u00c3\u0005e\u0000\u0000\u00c3\u00c4\u0005a\u0000\u0000\u00c4\u00c5\u0005"+
		"k\u0000\u0000\u00c5<\u0001\u0000\u0000\u0000\u00c6\u00c7\u0005r\u0000"+
		"\u0000\u00c7\u00c8\u0005e\u0000\u0000\u00c8\u00c9\u0005t\u0000\u0000\u00c9"+
		"\u00ca\u0005u\u0000\u0000\u00ca\u00cb\u0005r\u0000\u0000\u00cb\u00cc\u0005"+
		"n\u0000\u0000\u00cc>\u0001\u0000\u0000\u0000\u00cd\u00ce\u0005c\u0000"+
		"\u0000\u00ce\u00cf\u0005o\u0000\u0000\u00cf\u00d0\u0005n\u0000\u0000\u00d0"+
		"\u00d1\u0005t\u0000\u0000\u00d1\u00d2\u0005i\u0000\u0000\u00d2\u00d3\u0005"+
		"n\u0000\u0000\u00d3\u00d4\u0005u\u0000\u0000\u00d4\u00d5\u0005e\u0000"+
		"\u0000\u00d5@\u0001\u0000\u0000\u0000\u00d6\u00d7\u0005i\u0000\u0000\u00d7"+
		"\u00d8\u0005n\u0000\u0000\u00d8B\u0001\u0000\u0000\u0000\u00d9\u00da\u0005"+
		"f\u0000\u0000\u00da\u00db\u0005o\u0000\u0000\u00db\u00dc\u0005r\u0000"+
		"\u0000\u00dcD\u0001\u0000\u0000\u0000\u00dd\u00de\u0005w\u0000\u0000\u00de"+
		"\u00df\u0005h\u0000\u0000\u00df\u00e0\u0005i\u0000\u0000\u00e0\u00e1\u0005"+
		"l\u0000\u0000\u00e1\u00e2\u0005e\u0000\u0000\u00e2F\u0001\u0000\u0000"+
		"\u0000\u00e3\u00e4\u0005i\u0000\u0000\u00e4\u00e5\u0005f\u0000\u0000\u00e5"+
		"H\u0001\u0000\u0000\u0000\u00e6\u00e7\u0005l\u0000\u0000\u00e7\u00e8\u0005"+
		"o\u0000\u0000\u00e8\u00e9\u0005o\u0000\u0000\u00e9\u00ea\u0005p\u0000"+
		"\u0000\u00eaJ\u0001\u0000\u0000\u0000\u00eb\u00ec\u0005s\u0000\u0000\u00ec"+
		"\u00ed\u0005t\u0000\u0000\u00ed\u00ee\u0005a\u0000\u0000\u00ee\u00ef\u0005"+
		"t\u0000\u0000\u00ef\u00f0\u0005i\u0000\u0000\u00f0\u00f1\u0005c\u0000"+
		"\u0000\u00f1L\u0001\u0000\u0000\u0000\u00f2\u00f3\u0005-\u0000\u0000\u00f3"+
		"\u00f4\u0005>\u0000\u0000\u00f4N\u0001\u0000\u0000\u0000\u00f5\u00f6\u0005"+
		"n\u0000\u0000\u00f6\u00f7\u0005u\u0000\u0000\u00f7\u00f8\u0005l\u0000"+
		"\u0000\u00f8\u00f9\u0005l\u0000\u0000\u00f9P\u0001\u0000\u0000\u0000\u00fa"+
		"\u00fb\u0005a\u0000\u0000\u00fb\u00fc\u0005c\u0000\u0000\u00fc\u00fd\u0005"+
		"t\u0000\u0000\u00fd\u00fe\u0005i\u0000\u0000\u00fe\u00ff\u0005o\u0000"+
		"\u0000\u00ff\u0100\u0005n\u0000\u0000\u0100R\u0001\u0000\u0000\u0000\u0101"+
		"\u0102\u0005c\u0000\u0000\u0102\u0103\u0005a\u0000\u0000\u0103\u0104\u0005"+
		"r\u0000\u0000\u0104\u0105\u0005d\u0000\u0000\u0105\u0106\u0005T\u0000"+
		"\u0000\u0106\u0107\u0005y\u0000\u0000\u0107\u0108\u0005p\u0000\u0000\u0108"+
		"\u0109\u0005e\u0000\u0000\u0109T\u0001\u0000\u0000\u0000\u010a\u010b\u0005"+
		"s\u0000\u0000\u010b\u010c\u0005e\u0000\u0000\u010c\u010d\u0005t\u0000"+
		"\u0000\u010d\u010e\u0005u\u0000\u0000\u010e\u010f\u0005p\u0000\u0000\u010f"+
		"V\u0001\u0000\u0000\u0000\u0110\u0111\u0005i\u0000\u0000\u0111\u0112\u0005"+
		"n\u0000\u0000\u0112\u0113\u0005t\u0000\u0000\u0113X\u0001\u0000\u0000"+
		"\u0000\u0114\u0115\u0005f\u0000\u0000\u0115\u0116\u0005l\u0000\u0000\u0116"+
		"\u0117\u0005o\u0000\u0000\u0117\u0118\u0005a\u0000\u0000\u0118\u0119\u0005"+
		"t\u0000\u0000\u0119Z\u0001\u0000\u0000\u0000\u011a\u011b\u0005c\u0000"+
		"\u0000\u011b\u011c\u0005h\u0000\u0000\u011c\u011d\u0005a\u0000\u0000\u011d"+
		"\u011e\u0005r\u0000\u0000\u011e\\\u0001\u0000\u0000\u0000\u011f\u0120"+
		"\u0005s\u0000\u0000\u0120\u0121\u0005t\u0000\u0000\u0121\u0122\u0005r"+
		"\u0000\u0000\u0122\u0123\u0005i\u0000\u0000\u0123\u0124\u0005n\u0000\u0000"+
		"\u0124\u0125\u0005g\u0000\u0000\u0125^\u0001\u0000\u0000\u0000\u0126\u0127"+
		"\u0005v\u0000\u0000\u0127\u0128\u0005o\u0000\u0000\u0128\u0129\u0005i"+
		"\u0000\u0000\u0129\u012a\u0005d\u0000\u0000\u012a`\u0001\u0000\u0000\u0000"+
		"\u012b\u012d\u0005\'\u0000\u0000\u012c\u012e\t\u0000\u0000\u0000\u012d"+
		"\u012c\u0001\u0000\u0000\u0000\u012d\u012e\u0001\u0000\u0000\u0000\u012e"+
		"\u012f\u0001\u0000\u0000\u0000\u012f\u0130\u0005\'\u0000\u0000\u0130b"+
		"\u0001\u0000\u0000\u0000\u0131\u0135\u0005\"\u0000\u0000\u0132\u0134\t"+
		"\u0000\u0000\u0000\u0133\u0132\u0001\u0000\u0000\u0000\u0134\u0137\u0001"+
		"\u0000\u0000\u0000\u0135\u0136\u0001\u0000\u0000\u0000\u0135\u0133\u0001"+
		"\u0000\u0000\u0000\u0136\u0138\u0001\u0000\u0000\u0000\u0137\u0135\u0001"+
		"\u0000\u0000\u0000\u0138\u0139\u0005\"\u0000\u0000\u0139d\u0001\u0000"+
		"\u0000\u0000\u013a\u013c\u0007\u0000\u0000\u0000\u013b\u013a\u0001\u0000"+
		"\u0000\u0000\u013c\u013d\u0001\u0000\u0000\u0000\u013d\u013b\u0001\u0000"+
		"\u0000\u0000\u013d\u013e\u0001\u0000\u0000\u0000\u013ef\u0001\u0000\u0000"+
		"\u0000\u013f\u0141\u0003e2\u0000\u0140\u013f\u0001\u0000\u0000\u0000\u0140"+
		"\u0141\u0001\u0000\u0000\u0000\u0141\u0142\u0001\u0000\u0000\u0000\u0142"+
		"\u0146\u0005.\u0000\u0000\u0143\u0145\u0007\u0000\u0000\u0000\u0144\u0143"+
		"\u0001\u0000\u0000\u0000\u0145\u0148\u0001\u0000\u0000\u0000\u0146\u0144"+
		"\u0001\u0000\u0000\u0000\u0146\u0147\u0001\u0000\u0000\u0000\u0147\u0152"+
		"\u0001\u0000\u0000\u0000\u0148\u0146\u0001\u0000\u0000\u0000\u0149\u014a"+
		"\u0003e2\u0000\u014a\u014e\u0005.\u0000\u0000\u014b\u014d\u0007\u0000"+
		"\u0000\u0000\u014c\u014b\u0001\u0000\u0000\u0000\u014d\u0150\u0001\u0000"+
		"\u0000\u0000\u014e\u014f\u0001\u0000\u0000\u0000\u014e\u014c\u0001\u0000"+
		"\u0000\u0000\u014f\u0152\u0001\u0000\u0000\u0000\u0150\u014e\u0001\u0000"+
		"\u0000\u0000\u0151\u0140\u0001\u0000\u0000\u0000\u0151\u0149\u0001\u0000"+
		"\u0000\u0000\u0152h\u0001\u0000\u0000\u0000\u0153\u0157\u0007\u0001\u0000"+
		"\u0000\u0154\u0156\u0007\u0002\u0000\u0000\u0155\u0154\u0001\u0000\u0000"+
		"\u0000\u0156\u0159\u0001\u0000\u0000\u0000\u0157\u0155\u0001\u0000\u0000"+
		"\u0000\u0157\u0158\u0001\u0000\u0000\u0000\u0158j\u0001\u0000\u0000\u0000"+
		"\u0159\u0157\u0001\u0000\u0000\u0000\u015a\u015b\u0005/\u0000\u0000\u015b"+
		"\u015c\u0005*\u0000\u0000\u015c\u0160\u0001\u0000\u0000\u0000\u015d\u015f"+
		"\t\u0000\u0000\u0000\u015e\u015d\u0001\u0000\u0000\u0000\u015f\u0162\u0001"+
		"\u0000\u0000\u0000\u0160\u0161\u0001\u0000\u0000\u0000\u0160\u015e\u0001"+
		"\u0000\u0000\u0000\u0161\u0163\u0001\u0000\u0000\u0000\u0162\u0160\u0001"+
		"\u0000\u0000\u0000\u0163\u0164\u0005*\u0000\u0000\u0164\u0165\u0005/\u0000"+
		"\u0000\u0165\u0166\u0001\u0000\u0000\u0000\u0166\u0167\u00065\u0000\u0000"+
		"\u0167l\u0001\u0000\u0000\u0000\u0168\u016a\u0007\u0003\u0000\u0000\u0169"+
		"\u0168\u0001\u0000\u0000\u0000\u016a\u016b\u0001\u0000\u0000\u0000\u016b"+
		"\u0169\u0001\u0000\u0000\u0000\u016b\u016c\u0001\u0000\u0000\u0000\u016c"+
		"\u016d\u0001\u0000\u0000\u0000\u016d\u016e\u00066\u0001\u0000\u016en\u0001"+
		"\u0000\u0000\u0000\u016f\u0171\u0007\u0004\u0000\u0000\u0170\u016f\u0001"+
		"\u0000\u0000\u0000\u0171\u0172\u0001\u0000\u0000\u0000\u0172\u0170\u0001"+
		"\u0000\u0000\u0000\u0172\u0173\u0001\u0000\u0000\u0000\u0173\u0174\u0001"+
		"\u0000\u0000\u0000\u0174\u0175\u00067\u0001\u0000\u0175p\u0001\u0000\u0000"+
		"\u0000\f\u0000\u012d\u0135\u013d\u0140\u0146\u014e\u0151\u0157\u0160\u016b"+
		"\u0172\u0002\u0000\u0001\u0000\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}