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
		LeftParenthesis=1, RightParenthesis=2, SetOpen=3, SetClose=4, Circumflex=5, 
		Plus=6, Minus=7, BooleanNot=8, Multiplication=9, Division=10, Modulus=11, 
		LessThan=12, LessThanOrEqual=13, GreaterThan=14, GreaterThanOrEqual=15, 
		Equal=16, NotEqual=17, BooleanAnd=18, BooleanOr=19, ExerciseVariable=20, 
		FillInVariable=21, Variable=22, FunctionName=23, ArgumentSeparator=24, 
		SetArgumentSeparator=25, Integer=26, Float=27, String=28, WS=29;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"LeftParenthesis", "RightParenthesis", "SetOpen", "SetClose", "Circumflex", 
		"Plus", "Minus", "BooleanNot", "Multiplication", "Division", "Modulus", 
		"LessThan", "LessThanOrEqual", "GreaterThan", "GreaterThanOrEqual", "Equal", 
		"NotEqual", "BooleanAnd", "BooleanOr", "ExerciseVariable", "FillInVariable", 
		"Variable", "FunctionName", "ArgumentSeparator", "SetArgumentSeparator", 
		"Integer", "Float", "String", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "'{'", "'}'", "'^'", "'+'", "'-'", "'!'", "'*'", "'/'", 
		"'%'", "'<'", "'<='", "'>'", "'>='", null, "'!='", "'&&'", "'||'", null, 
		null, null, null, "','", "';'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "LeftParenthesis", "RightParenthesis", "SetOpen", "SetClose", "Circumflex", 
		"Plus", "Minus", "BooleanNot", "Multiplication", "Division", "Modulus", 
		"LessThan", "LessThanOrEqual", "GreaterThan", "GreaterThanOrEqual", "Equal", 
		"NotEqual", "BooleanAnd", "BooleanOr", "ExerciseVariable", "FillInVariable", 
		"Variable", "FunctionName", "ArgumentSeparator", "SetArgumentSeparator", 
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\37\u00b1\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\3\2\3\2\3\3\3"+
		"\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3"+
		"\f\3\f\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3\21"+
		"\5\21a\n\21\3\22\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\7\25t\n\25\f\25\16\25w\13\25\3\25\3\25\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\3\26\6\26\u0082\n\26\r\26\16\26\u0083\3\26\3"+
		"\26\3\27\3\27\3\30\3\30\7\30\u008c\n\30\f\30\16\30\u008f\13\30\3\31\3"+
		"\31\3\32\3\32\3\33\6\33\u0096\n\33\r\33\16\33\u0097\3\34\6\34\u009b\n"+
		"\34\r\34\16\34\u009c\3\34\3\34\6\34\u00a1\n\34\r\34\16\34\u00a2\3\35\3"+
		"\35\7\35\u00a7\n\35\f\35\16\35\u00aa\13\35\3\35\3\35\3\36\3\36\3\36\3"+
		"\36\3\u00a8\2\37\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31"+
		"\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65"+
		"\34\67\359\36;\37\3\2\t\13\2C\\aac|\u00c6\u00c6\u00d8\u00d8\u00de\u00de"+
		"\u00e6\u00e6\u00f8\u00f8\u00fe\u00fe\f\2\62;C\\aac|\u00c6\u00c6\u00d8"+
		"\u00d8\u00de\u00de\u00e6\u00e6\u00f8\u00f8\u00fe\u00fe\3\2\62;\3\2c|\4"+
		"\2C\\c|\5\2\62;C\\c|\5\2\13\f\17\17\"\"\2\u00b8\2\3\3\2\2\2\2\5\3\2\2"+
		"\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2"+
		"\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3"+
		"\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3"+
		"\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\3=\3\2\2\2\5?\3"+
		"\2\2\2\7A\3\2\2\2\tC\3\2\2\2\13E\3\2\2\2\rG\3\2\2\2\17I\3\2\2\2\21K\3"+
		"\2\2\2\23M\3\2\2\2\25O\3\2\2\2\27Q\3\2\2\2\31S\3\2\2\2\33U\3\2\2\2\35"+
		"X\3\2\2\2\37Z\3\2\2\2!`\3\2\2\2#b\3\2\2\2%e\3\2\2\2\'h\3\2\2\2)k\3\2\2"+
		"\2+z\3\2\2\2-\u0087\3\2\2\2/\u0089\3\2\2\2\61\u0090\3\2\2\2\63\u0092\3"+
		"\2\2\2\65\u0095\3\2\2\2\67\u009a\3\2\2\29\u00a4\3\2\2\2;\u00ad\3\2\2\2"+
		"=>\7*\2\2>\4\3\2\2\2?@\7+\2\2@\6\3\2\2\2AB\7}\2\2B\b\3\2\2\2CD\7\177\2"+
		"\2D\n\3\2\2\2EF\7`\2\2F\f\3\2\2\2GH\7-\2\2H\16\3\2\2\2IJ\7/\2\2J\20\3"+
		"\2\2\2KL\7#\2\2L\22\3\2\2\2MN\7,\2\2N\24\3\2\2\2OP\7\61\2\2P\26\3\2\2"+
		"\2QR\7\'\2\2R\30\3\2\2\2ST\7>\2\2T\32\3\2\2\2UV\7>\2\2VW\7?\2\2W\34\3"+
		"\2\2\2XY\7@\2\2Y\36\3\2\2\2Z[\7@\2\2[\\\7?\2\2\\ \3\2\2\2]^\7?\2\2^a\7"+
		"?\2\2_a\7?\2\2`]\3\2\2\2`_\3\2\2\2a\"\3\2\2\2bc\7#\2\2cd\7?\2\2d$\3\2"+
		"\2\2ef\7(\2\2fg\7(\2\2g&\3\2\2\2hi\7~\2\2ij\7~\2\2j(\3\2\2\2kl\7]\2\2"+
		"lm\7x\2\2mn\7c\2\2no\7t\2\2op\7?\2\2pq\3\2\2\2qu\t\2\2\2rt\t\3\2\2sr\3"+
		"\2\2\2tw\3\2\2\2us\3\2\2\2uv\3\2\2\2vx\3\2\2\2wu\3\2\2\2xy\7_\2\2y*\3"+
		"\2\2\2z{\7]\2\2{|\7r\2\2|}\7q\2\2}~\7u\2\2~\177\7?\2\2\177\u0081\3\2\2"+
		"\2\u0080\u0082\t\4\2\2\u0081\u0080\3\2\2\2\u0082\u0083\3\2\2\2\u0083\u0081"+
		"\3\2\2\2\u0083\u0084\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0086\7_\2\2\u0086"+
		",\3\2\2\2\u0087\u0088\t\5\2\2\u0088.\3\2\2\2\u0089\u008d\t\6\2\2\u008a"+
		"\u008c\t\7\2\2\u008b\u008a\3\2\2\2\u008c\u008f\3\2\2\2\u008d\u008b\3\2"+
		"\2\2\u008d\u008e\3\2\2\2\u008e\60\3\2\2\2\u008f\u008d\3\2\2\2\u0090\u0091"+
		"\7.\2\2\u0091\62\3\2\2\2\u0092\u0093\7=\2\2\u0093\64\3\2\2\2\u0094\u0096"+
		"\t\4\2\2\u0095\u0094\3\2\2\2\u0096\u0097\3\2\2\2\u0097\u0095\3\2\2\2\u0097"+
		"\u0098\3\2\2\2\u0098\66\3\2\2\2\u0099\u009b\t\4\2\2\u009a\u0099\3\2\2"+
		"\2\u009b\u009c\3\2\2\2\u009c\u009a\3\2\2\2\u009c\u009d\3\2\2\2\u009d\u009e"+
		"\3\2\2\2\u009e\u00a0\7\60\2\2\u009f\u00a1\t\4\2\2\u00a0\u009f\3\2\2\2"+
		"\u00a1\u00a2\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a38\3"+
		"\2\2\2\u00a4\u00a8\7)\2\2\u00a5\u00a7\13\2\2\2\u00a6\u00a5\3\2\2\2\u00a7"+
		"\u00aa\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a9\u00ab\3\2"+
		"\2\2\u00aa\u00a8\3\2\2\2\u00ab\u00ac\7)\2\2\u00ac:\3\2\2\2\u00ad\u00ae"+
		"\t\b\2\2\u00ae\u00af\3\2\2\2\u00af\u00b0\b\36\2\2\u00b0<\3\2\2\2\13\2"+
		"`u\u0083\u008d\u0097\u009c\u00a2\u00a8\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}