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
		BooleanOr=16, ExerciseVariable=17, FillInVariable=18, StandardVariable=19, 
		FunctionName=20, Quote=21, ArgumentSeparator=22, Integer=23, Float=24, 
		String=25, WS=26;
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
		"BooleanOr", "ExerciseVariable", "FillInVariable", "StandardVariable", 
		"FunctionName", "Quote", "ArgumentSeparator", "Integer", "Float", "String", 
		"WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "'+'", "'-'", "'!'", "'*'", "'/'", "'%'", "'<'", "'<='", 
		"'>'", "'>='", null, "'!='", "'&&'", "'||'", null, null, null, null, "'''", 
		"','"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "LeftParenthesis", "RightParenthesis", "Plus", "Minus", "BooleanNot", 
		"Multiplication", "Division", "Modulus", "LessThan", "LessThanOrEqual", 
		"GreaterThan", "GreaterThanOrEqual", "Equal", "NotEqual", "BooleanAnd", 
		"BooleanOr", "ExerciseVariable", "FillInVariable", "StandardVariable", 
		"FunctionName", "Quote", "ArgumentSeparator", "Integer", "Float", "String", 
		"WS"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\34\u00c7\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7"+
		"\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\r\3\r\3\r\3\16\3"+
		"\16\3\16\5\16U\n\16\3\17\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3\21\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\7\22h\n\22\f\22\16\22k\13\22\3\22"+
		"\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\6\23v\n\23\r\23\16\23w\3\23\3"+
		"\23\3\24\3\24\3\24\3\24\3\24\7\24\u0081\n\24\f\24\16\24\u0084\13\24\3"+
		"\24\3\24\3\25\3\25\7\25\u008a\n\25\f\25\16\25\u008d\13\25\3\26\3\26\3"+
		"\27\3\27\3\30\6\30\u0094\n\30\r\30\16\30\u0095\3\30\3\30\6\30\u009a\n"+
		"\30\r\30\16\30\u009b\3\30\5\30\u009f\n\30\3\31\6\31\u00a2\n\31\r\31\16"+
		"\31\u00a3\3\31\3\31\6\31\u00a8\n\31\r\31\16\31\u00a9\3\31\3\31\6\31\u00ae"+
		"\n\31\r\31\16\31\u00af\3\31\3\31\6\31\u00b4\n\31\r\31\16\31\u00b5\3\31"+
		"\5\31\u00b9\n\31\3\32\3\32\7\32\u00bd\n\32\f\32\16\32\u00c0\13\32\3\32"+
		"\3\32\3\33\3\33\3\33\3\33\3\u00be\2\34\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21"+
		"\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30"+
		"/\31\61\32\63\33\65\34\3\2\b\13\2C\\aac|\u00c6\u00c6\u00d8\u00d8\u00de"+
		"\u00de\u00e6\u00e6\u00f8\u00f8\u00fe\u00fe\f\2\62;C\\aac|\u00c6\u00c6"+
		"\u00d8\u00d8\u00de\u00de\u00e6\u00e6\u00f8\u00f8\u00fe\u00fe\3\2\62;\4"+
		"\2C\\c|\5\2\62;C\\c|\5\2\13\f\17\17\"\"\2\u00d4\2\3\3\2\2\2\2\5\3\2\2"+
		"\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2"+
		"\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3"+
		"\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3"+
		"\2\2\2\2\65\3\2\2\2\3\67\3\2\2\2\59\3\2\2\2\7;\3\2\2\2\t=\3\2\2\2\13?"+
		"\3\2\2\2\rA\3\2\2\2\17C\3\2\2\2\21E\3\2\2\2\23G\3\2\2\2\25I\3\2\2\2\27"+
		"L\3\2\2\2\31N\3\2\2\2\33T\3\2\2\2\35V\3\2\2\2\37Y\3\2\2\2!\\\3\2\2\2#"+
		"_\3\2\2\2%n\3\2\2\2\'{\3\2\2\2)\u0087\3\2\2\2+\u008e\3\2\2\2-\u0090\3"+
		"\2\2\2/\u009e\3\2\2\2\61\u00b8\3\2\2\2\63\u00ba\3\2\2\2\65\u00c3\3\2\2"+
		"\2\678\7*\2\28\4\3\2\2\29:\7+\2\2:\6\3\2\2\2;<\7-\2\2<\b\3\2\2\2=>\7/"+
		"\2\2>\n\3\2\2\2?@\7#\2\2@\f\3\2\2\2AB\7,\2\2B\16\3\2\2\2CD\7\61\2\2D\20"+
		"\3\2\2\2EF\7\'\2\2F\22\3\2\2\2GH\7>\2\2H\24\3\2\2\2IJ\7>\2\2JK\7?\2\2"+
		"K\26\3\2\2\2LM\7@\2\2M\30\3\2\2\2NO\7@\2\2OP\7?\2\2P\32\3\2\2\2QR\7?\2"+
		"\2RU\7?\2\2SU\7?\2\2TQ\3\2\2\2TS\3\2\2\2U\34\3\2\2\2VW\7#\2\2WX\7?\2\2"+
		"X\36\3\2\2\2YZ\7(\2\2Z[\7(\2\2[ \3\2\2\2\\]\7~\2\2]^\7~\2\2^\"\3\2\2\2"+
		"_`\7]\2\2`a\7x\2\2ab\7c\2\2bc\7t\2\2cd\7?\2\2de\3\2\2\2ei\t\2\2\2fh\t"+
		"\3\2\2gf\3\2\2\2hk\3\2\2\2ig\3\2\2\2ij\3\2\2\2jl\3\2\2\2ki\3\2\2\2lm\7"+
		"_\2\2m$\3\2\2\2no\7]\2\2op\7r\2\2pq\7q\2\2qr\7u\2\2rs\7?\2\2su\3\2\2\2"+
		"tv\t\4\2\2ut\3\2\2\2vw\3\2\2\2wu\3\2\2\2wx\3\2\2\2xy\3\2\2\2yz\7_\2\2"+
		"z&\3\2\2\2{|\7%\2\2|}\7}\2\2}~\3\2\2\2~\u0082\t\5\2\2\177\u0081\t\6\2"+
		"\2\u0080\177\3\2\2\2\u0081\u0084\3\2\2\2\u0082\u0080\3\2\2\2\u0082\u0083"+
		"\3\2\2\2\u0083\u0085\3\2\2\2\u0084\u0082\3\2\2\2\u0085\u0086\7\177\2\2"+
		"\u0086(\3\2\2\2\u0087\u008b\t\5\2\2\u0088\u008a\t\6\2\2\u0089\u0088\3"+
		"\2\2\2\u008a\u008d\3\2\2\2\u008b\u0089\3\2\2\2\u008b\u008c\3\2\2\2\u008c"+
		"*\3\2\2\2\u008d\u008b\3\2\2\2\u008e\u008f\7)\2\2\u008f,\3\2\2\2\u0090"+
		"\u0091\7.\2\2\u0091.\3\2\2\2\u0092\u0094\t\4\2\2\u0093\u0092\3\2\2\2\u0094"+
		"\u0095\3\2\2\2\u0095\u0093\3\2\2\2\u0095\u0096\3\2\2\2\u0096\u009f\3\2"+
		"\2\2\u0097\u0099\7)\2\2\u0098\u009a\t\4\2\2\u0099\u0098\3\2\2\2\u009a"+
		"\u009b\3\2\2\2\u009b\u0099\3\2\2\2\u009b\u009c\3\2\2\2\u009c\u009d\3\2"+
		"\2\2\u009d\u009f\7)\2\2\u009e\u0093\3\2\2\2\u009e\u0097\3\2\2\2\u009f"+
		"\60\3\2\2\2\u00a0\u00a2\t\4\2\2\u00a1\u00a0\3\2\2\2\u00a2\u00a3\3\2\2"+
		"\2\u00a3\u00a1\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00a7"+
		"\7\60\2\2\u00a6\u00a8\t\4\2\2\u00a7\u00a6\3\2\2\2\u00a8\u00a9\3\2\2\2"+
		"\u00a9\u00a7\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00b9\3\2\2\2\u00ab\u00ad"+
		"\7)\2\2\u00ac\u00ae\t\4\2\2\u00ad\u00ac\3\2\2\2\u00ae\u00af\3\2\2\2\u00af"+
		"\u00ad\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00b3\7\60"+
		"\2\2\u00b2\u00b4\t\4\2\2\u00b3\u00b2\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5"+
		"\u00b3\3\2\2\2\u00b5\u00b6\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7\u00b9\7)"+
		"\2\2\u00b8\u00a1\3\2\2\2\u00b8\u00ab\3\2\2\2\u00b9\62\3\2\2\2\u00ba\u00be"+
		"\7)\2\2\u00bb\u00bd\13\2\2\2\u00bc\u00bb\3\2\2\2\u00bd\u00c0\3\2\2\2\u00be"+
		"\u00bf\3\2\2\2\u00be\u00bc\3\2\2\2\u00bf\u00c1\3\2\2\2\u00c0\u00be\3\2"+
		"\2\2\u00c1\u00c2\7)\2\2\u00c2\64\3\2\2\2\u00c3\u00c4\t\7\2\2\u00c4\u00c5"+
		"\3\2\2\2\u00c5\u00c6\b\33\2\2\u00c6\66\3\2\2\2\21\2Tiw\u0082\u008b\u0095"+
		"\u009b\u009e\u00a3\u00a9\u00af\u00b5\u00b8\u00be\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}