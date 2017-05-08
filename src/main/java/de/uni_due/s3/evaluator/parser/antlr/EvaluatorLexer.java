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
		FunctionName=20, FunctionOpen=21, FunctionClose=22, ArgumentSeparator=23, 
		Quote=24, Integer=25, Float=26, Text=27, WS=28;
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
		"FunctionName", "FunctionOpen", "FunctionClose", "ArgumentSeparator", 
		"Quote", "Integer", "Float", "Text", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, "'+'", "'-'", "'!'", "'*'", "'/'", "'%'", "'<'", "'<='", 
		"'>'", "'>='", null, "'!='", "'&&'", "'||'", null, null, null, null, null, 
		null, "','", "'''"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "LeftParenthesis", "RightParenthesis", "Plus", "Minus", "BooleanNot", 
		"Multiplication", "Division", "Modulus", "LessThan", "LessThanOrEqual", 
		"GreaterThan", "GreaterThanOrEqual", "Equal", "NotEqual", "BooleanAnd", 
		"BooleanOr", "ExerciseVariable", "FillInVariable", "StandardVariable", 
		"FunctionName", "FunctionOpen", "FunctionClose", "ArgumentSeparator", 
		"Quote", "Integer", "Float", "Text", "WS"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\36\u00b7\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\3\2\3\2\3\3\3\3\3\4\3\4"+
		"\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\13\3\f\3"+
		"\f\3\r\3\r\3\r\3\16\3\16\3\16\5\16Y\n\16\3\17\3\17\3\17\3\20\3\20\3\20"+
		"\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\7\22l\n\22\f\22"+
		"\16\22o\13\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\6\23z\n\23"+
		"\r\23\16\23{\3\23\3\23\3\24\3\24\3\24\3\24\3\24\7\24\u0085\n\24\f\24\16"+
		"\24\u0088\13\24\3\24\3\24\3\25\3\25\7\25\u008e\n\25\f\25\16\25\u0091\13"+
		"\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\6\32\u009c\n\32\r\32"+
		"\16\32\u009d\3\33\6\33\u00a1\n\33\r\33\16\33\u00a2\3\33\3\33\6\33\u00a7"+
		"\n\33\r\33\16\33\u00a8\3\34\3\34\7\34\u00ad\n\34\f\34\16\34\u00b0\13\34"+
		"\3\34\3\34\3\35\3\35\3\35\3\35\3\u00ae\2\36\3\3\5\4\7\5\t\6\13\7\r\b\17"+
		"\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+"+
		"\27-\30/\31\61\32\63\33\65\34\67\359\36\3\2\t\6\2C\\aac|\uffff\uffff\7"+
		"\2\62;C\\aac|\uffff\uffff\3\2\62;\4\2C\\c|\5\2\62;C\\c|\3\2C|\5\2\13\f"+
		"\17\17\"\"\2\u00bf\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13"+
		"\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2"+
		"\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2"+
		"!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3"+
		"\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2"+
		"\29\3\2\2\2\3;\3\2\2\2\5=\3\2\2\2\7?\3\2\2\2\tA\3\2\2\2\13C\3\2\2\2\r"+
		"E\3\2\2\2\17G\3\2\2\2\21I\3\2\2\2\23K\3\2\2\2\25M\3\2\2\2\27P\3\2\2\2"+
		"\31R\3\2\2\2\33X\3\2\2\2\35Z\3\2\2\2\37]\3\2\2\2!`\3\2\2\2#c\3\2\2\2%"+
		"r\3\2\2\2\'\177\3\2\2\2)\u008b\3\2\2\2+\u0092\3\2\2\2-\u0094\3\2\2\2/"+
		"\u0096\3\2\2\2\61\u0098\3\2\2\2\63\u009b\3\2\2\2\65\u00a0\3\2\2\2\67\u00aa"+
		"\3\2\2\29\u00b3\3\2\2\2;<\7*\2\2<\4\3\2\2\2=>\7+\2\2>\6\3\2\2\2?@\7-\2"+
		"\2@\b\3\2\2\2AB\7/\2\2B\n\3\2\2\2CD\7#\2\2D\f\3\2\2\2EF\7,\2\2F\16\3\2"+
		"\2\2GH\7\61\2\2H\20\3\2\2\2IJ\7\'\2\2J\22\3\2\2\2KL\7>\2\2L\24\3\2\2\2"+
		"MN\7>\2\2NO\7?\2\2O\26\3\2\2\2PQ\7@\2\2Q\30\3\2\2\2RS\7@\2\2ST\7?\2\2"+
		"T\32\3\2\2\2UV\7?\2\2VY\7?\2\2WY\7?\2\2XU\3\2\2\2XW\3\2\2\2Y\34\3\2\2"+
		"\2Z[\7#\2\2[\\\7?\2\2\\\36\3\2\2\2]^\7(\2\2^_\7(\2\2_ \3\2\2\2`a\7~\2"+
		"\2ab\7~\2\2b\"\3\2\2\2cd\7]\2\2de\7x\2\2ef\7c\2\2fg\7t\2\2gh\7?\2\2hi"+
		"\3\2\2\2im\t\2\2\2jl\t\3\2\2kj\3\2\2\2lo\3\2\2\2mk\3\2\2\2mn\3\2\2\2n"+
		"p\3\2\2\2om\3\2\2\2pq\7_\2\2q$\3\2\2\2rs\7]\2\2st\7r\2\2tu\7q\2\2uv\7"+
		"u\2\2vw\7?\2\2wy\3\2\2\2xz\t\4\2\2yx\3\2\2\2z{\3\2\2\2{y\3\2\2\2{|\3\2"+
		"\2\2|}\3\2\2\2}~\7_\2\2~&\3\2\2\2\177\u0080\7%\2\2\u0080\u0081\7}\2\2"+
		"\u0081\u0082\3\2\2\2\u0082\u0086\t\5\2\2\u0083\u0085\t\6\2\2\u0084\u0083"+
		"\3\2\2\2\u0085\u0088\3\2\2\2\u0086\u0084\3\2\2\2\u0086\u0087\3\2\2\2\u0087"+
		"\u0089\3\2\2\2\u0088\u0086\3\2\2\2\u0089\u008a\7\177\2\2\u008a(\3\2\2"+
		"\2\u008b\u008f\t\7\2\2\u008c\u008e\t\6\2\2\u008d\u008c\3\2\2\2\u008e\u0091"+
		"\3\2\2\2\u008f\u008d\3\2\2\2\u008f\u0090\3\2\2\2\u0090*\3\2\2\2\u0091"+
		"\u008f\3\2\2\2\u0092\u0093\7*\2\2\u0093,\3\2\2\2\u0094\u0095\7+\2\2\u0095"+
		".\3\2\2\2\u0096\u0097\7.\2\2\u0097\60\3\2\2\2\u0098\u0099\7)\2\2\u0099"+
		"\62\3\2\2\2\u009a\u009c\t\4\2\2\u009b\u009a\3\2\2\2\u009c\u009d\3\2\2"+
		"\2\u009d\u009b\3\2\2\2\u009d\u009e\3\2\2\2\u009e\64\3\2\2\2\u009f\u00a1"+
		"\t\4\2\2\u00a0\u009f\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a2"+
		"\u00a3\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a6\7\60\2\2\u00a5\u00a7\t"+
		"\4\2\2\u00a6\u00a5\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a8"+
		"\u00a9\3\2\2\2\u00a9\66\3\2\2\2\u00aa\u00ae\7)\2\2\u00ab\u00ad\13\2\2"+
		"\2\u00ac\u00ab\3\2\2\2\u00ad\u00b0\3\2\2\2\u00ae\u00af\3\2\2\2\u00ae\u00ac"+
		"\3\2\2\2\u00af\u00b1\3\2\2\2\u00b0\u00ae\3\2\2\2\u00b1\u00b2\7)\2\2\u00b2"+
		"8\3\2\2\2\u00b3\u00b4\t\b\2\2\u00b4\u00b5\3\2\2\2\u00b5\u00b6\b\35\2\2"+
		"\u00b6:\3\2\2\2\f\2Xm{\u0086\u008f\u009d\u00a2\u00a8\u00ae\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}