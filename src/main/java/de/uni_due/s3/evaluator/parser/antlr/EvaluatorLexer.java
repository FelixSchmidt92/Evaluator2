package de.uni_due.s3.evaluator.parser.antlr;

// Generated from EvaluatorLexer.g4 by ANTLR 4.7
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class EvaluatorLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		LeftParenthesis=1, RightParenthesis=2, Plus=3, Minus=4, BooleanNot=5, 
		Multiplication=6, Division=7, Modulus=8, LessThan=9, LessThanOrEqual=10, 
		GreaterThan=11, GreaterThanOrEqual=12, Equal=13, NotEqual=14, BooleanAnd=15, 
		BooleanOr=16, ExerciseVariable=17, FillInVariable=18, FunctionName=19, 
		ArgumentSeparator=20, Integer=21, Float=22, String=23, WS=24;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"LeftParenthesis", "RightParenthesis", "Plus", "Minus", "BooleanNot", 
		"Multiplication", "Division", "Modulus", "LessThan", "LessThanOrEqual", 
		"GreaterThan", "GreaterThanOrEqual", "Equal", "NotEqual", "BooleanAnd", 
		"BooleanOr", "ExerciseVariable", "FillInVariable", "FunctionName", "ArgumentSeparator", 
		"Integer", "Float", "String", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "'+'", "'-'", "'!'", "'*'", "'/'", "'%'", "'<'", "'<='", 
		"'>'", "'>='", null, "'!='", "'&&'", "'||'", null, null, null, "','"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "LeftParenthesis", "RightParenthesis", "Plus", "Minus", "BooleanNot", 
		"Multiplication", "Division", "Modulus", "LessThan", "LessThanOrEqual", 
		"GreaterThan", "GreaterThanOrEqual", "Equal", "NotEqual", "BooleanAnd", 
		"BooleanOr", "ExerciseVariable", "FillInVariable", "FunctionName", "ArgumentSeparator", 
		"Integer", "Float", "String", "WS"
	};
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


	public EvaluatorLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "EvaluatorLexer.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\32\u00d5\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t"+
		"\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\16\5\16Q\n\16"+
		"\3\17\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\7\22d\n\22\f\22\16\22g\13\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\7\22s\n\22\f\22\16\22v\13\22\3\22\3\22\5\22"+
		"z\n\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\6\23\u0083\n\23\r\23\16\23\u0084"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\6\23\u0090\n\23\r\23\16"+
		"\23\u0091\3\23\3\23\5\23\u0096\n\23\3\24\3\24\7\24\u009a\n\24\f\24\16"+
		"\24\u009d\13\24\3\25\3\25\3\26\6\26\u00a2\n\26\r\26\16\26\u00a3\3\26\3"+
		"\26\6\26\u00a8\n\26\r\26\16\26\u00a9\3\26\5\26\u00ad\n\26\3\27\6\27\u00b0"+
		"\n\27\r\27\16\27\u00b1\3\27\3\27\6\27\u00b6\n\27\r\27\16\27\u00b7\3\27"+
		"\3\27\6\27\u00bc\n\27\r\27\16\27\u00bd\3\27\3\27\6\27\u00c2\n\27\r\27"+
		"\16\27\u00c3\3\27\5\27\u00c7\n\27\3\30\3\30\7\30\u00cb\n\30\f\30\16\30"+
		"\u00ce\13\30\3\30\3\30\3\31\3\31\3\31\3\31\3\u00cc\2\32\3\3\5\4\7\5\t"+
		"\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23"+
		"%\24\'\25)\26+\27-\30/\31\61\32\3\2\b\13\2C\\aac|\u00c6\u00c6\u00d8\u00d8"+
		"\u00de\u00de\u00e6\u00e6\u00f8\u00f8\u00fe\u00fe\f\2\62;C\\aac|\u00c6"+
		"\u00c6\u00d8\u00d8\u00de\u00de\u00e6\u00e6\u00f8\u00f8\u00fe\u00fe\3\2"+
		"\62;\4\2C\\c|\5\2\62;C\\c|\5\2\13\f\17\17\"\"\2\u00e5\2\3\3\2\2\2\2\5"+
		"\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2"+
		"\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33"+
		"\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2"+
		"\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\3"+
		"\63\3\2\2\2\5\65\3\2\2\2\7\67\3\2\2\2\t9\3\2\2\2\13;\3\2\2\2\r=\3\2\2"+
		"\2\17?\3\2\2\2\21A\3\2\2\2\23C\3\2\2\2\25E\3\2\2\2\27H\3\2\2\2\31J\3\2"+
		"\2\2\33P\3\2\2\2\35R\3\2\2\2\37U\3\2\2\2!X\3\2\2\2#y\3\2\2\2%\u0095\3"+
		"\2\2\2\'\u0097\3\2\2\2)\u009e\3\2\2\2+\u00ac\3\2\2\2-\u00c6\3\2\2\2/\u00c8"+
		"\3\2\2\2\61\u00d1\3\2\2\2\63\64\7*\2\2\64\4\3\2\2\2\65\66\7+\2\2\66\6"+
		"\3\2\2\2\678\7-\2\28\b\3\2\2\29:\7/\2\2:\n\3\2\2\2;<\7#\2\2<\f\3\2\2\2"+
		"=>\7,\2\2>\16\3\2\2\2?@\7\61\2\2@\20\3\2\2\2AB\7\'\2\2B\22\3\2\2\2CD\7"+
		">\2\2D\24\3\2\2\2EF\7>\2\2FG\7?\2\2G\26\3\2\2\2HI\7@\2\2I\30\3\2\2\2J"+
		"K\7@\2\2KL\7?\2\2L\32\3\2\2\2MN\7?\2\2NQ\7?\2\2OQ\7?\2\2PM\3\2\2\2PO\3"+
		"\2\2\2Q\34\3\2\2\2RS\7#\2\2ST\7?\2\2T\36\3\2\2\2UV\7(\2\2VW\7(\2\2W \3"+
		"\2\2\2XY\7~\2\2YZ\7~\2\2Z\"\3\2\2\2[\\\7]\2\2\\]\7x\2\2]^\7c\2\2^_\7t"+
		"\2\2_`\7?\2\2`a\3\2\2\2ae\t\2\2\2bd\t\3\2\2cb\3\2\2\2dg\3\2\2\2ec\3\2"+
		"\2\2ef\3\2\2\2fh\3\2\2\2ge\3\2\2\2hz\7_\2\2ij\7)\2\2jk\7]\2\2kl\7x\2\2"+
		"lm\7c\2\2mn\7t\2\2no\7?\2\2op\3\2\2\2pt\t\2\2\2qs\t\3\2\2rq\3\2\2\2sv"+
		"\3\2\2\2tr\3\2\2\2tu\3\2\2\2uw\3\2\2\2vt\3\2\2\2wx\7_\2\2xz\7)\2\2y[\3"+
		"\2\2\2yi\3\2\2\2z$\3\2\2\2{|\7]\2\2|}\7r\2\2}~\7q\2\2~\177\7u\2\2\177"+
		"\u0080\7?\2\2\u0080\u0082\3\2\2\2\u0081\u0083\t\4\2\2\u0082\u0081\3\2"+
		"\2\2\u0083\u0084\3\2\2\2\u0084\u0082\3\2\2\2\u0084\u0085\3\2\2\2\u0085"+
		"\u0086\3\2\2\2\u0086\u0096\7_\2\2\u0087\u0088\7)\2\2\u0088\u0089\7]\2"+
		"\2\u0089\u008a\7r\2\2\u008a\u008b\7q\2\2\u008b\u008c\7u\2\2\u008c\u008d"+
		"\7?\2\2\u008d\u008f\3\2\2\2\u008e\u0090\t\4\2\2\u008f\u008e\3\2\2\2\u0090"+
		"\u0091\3\2\2\2\u0091\u008f\3\2\2\2\u0091\u0092\3\2\2\2\u0092\u0093\3\2"+
		"\2\2\u0093\u0094\7_\2\2\u0094\u0096\7)\2\2\u0095{\3\2\2\2\u0095\u0087"+
		"\3\2\2\2\u0096&\3\2\2\2\u0097\u009b\t\5\2\2\u0098\u009a\t\6\2\2\u0099"+
		"\u0098\3\2\2\2\u009a\u009d\3\2\2\2\u009b\u0099\3\2\2\2\u009b\u009c\3\2"+
		"\2\2\u009c(\3\2\2\2\u009d\u009b\3\2\2\2\u009e\u009f\7.\2\2\u009f*\3\2"+
		"\2\2\u00a0\u00a2\t\4\2\2\u00a1\u00a0\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3"+
		"\u00a1\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00ad\3\2\2\2\u00a5\u00a7\7)"+
		"\2\2\u00a6\u00a8\t\4\2\2\u00a7\u00a6\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9"+
		"\u00a7\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab\u00ad\7)"+
		"\2\2\u00ac\u00a1\3\2\2\2\u00ac\u00a5\3\2\2\2\u00ad,\3\2\2\2\u00ae\u00b0"+
		"\t\4\2\2\u00af\u00ae\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00af\3\2\2\2\u00b1"+
		"\u00b2\3\2\2\2\u00b2\u00b3\3\2\2\2\u00b3\u00b5\7\60\2\2\u00b4\u00b6\t"+
		"\4\2\2\u00b5\u00b4\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b7"+
		"\u00b8\3\2\2\2\u00b8\u00c7\3\2\2\2\u00b9\u00bb\7)\2\2\u00ba\u00bc\t\4"+
		"\2\2\u00bb\u00ba\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd\u00bb\3\2\2\2\u00bd"+
		"\u00be\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf\u00c1\7\60\2\2\u00c0\u00c2\t"+
		"\4\2\2\u00c1\u00c0\3\2\2\2\u00c2\u00c3\3\2\2\2\u00c3\u00c1\3\2\2\2\u00c3"+
		"\u00c4\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5\u00c7\7)\2\2\u00c6\u00af\3\2"+
		"\2\2\u00c6\u00b9\3\2\2\2\u00c7.\3\2\2\2\u00c8\u00cc\7)\2\2\u00c9\u00cb"+
		"\13\2\2\2\u00ca\u00c9\3\2\2\2\u00cb\u00ce\3\2\2\2\u00cc\u00cd\3\2\2\2"+
		"\u00cc\u00ca\3\2\2\2\u00cd\u00cf\3\2\2\2\u00ce\u00cc\3\2\2\2\u00cf\u00d0"+
		"\7)\2\2\u00d0\60\3\2\2\2\u00d1\u00d2\t\7\2\2\u00d2\u00d3\3\2\2\2\u00d3"+
		"\u00d4\b\31\2\2\u00d4\62\3\2\2\2\24\2Pety\u0084\u0091\u0095\u009b\u00a3"+
		"\u00a9\u00ac\u00b1\u00b7\u00bd\u00c3\u00c6\u00cc\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}