// Generated from EvaluatorLexer.g4 by ANTLR 4.7
package de.uni_due.s3.evaluator2.parser.antlr;

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
		LeftParenthesis=1, RightParenthesis=2, ListOpen=3, ListClose=4, Circumflex=5, 
		Plus=6, Minus=7, BooleanNot=8, Multiplication=9, Division=10, Modulus=11, 
		LessThan=12, LessThanOrEqual=13, GreaterThan=14, GreaterThanOrEqual=15, 
		Equal=16, NotEqual=17, BooleanAnd=18, BooleanOr=19, ExerciseVariable=20, 
		FillInVariable=21, Variable=22, True=23, False=24, FunctionName=25, ArgumentSeparator=26, 
		ListArgumentSeparator=27, Integer=28, Float=29, String=30, WS=31;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"LeftParenthesis", "RightParenthesis", "ListOpen", "ListClose", "Circumflex", 
		"Plus", "Minus", "BooleanNot", "Multiplication", "Division", "Modulus", 
		"LessThan", "LessThanOrEqual", "GreaterThan", "GreaterThanOrEqual", "Equal", 
		"NotEqual", "BooleanAnd", "BooleanOr", "ExerciseVariable", "FillInVariable", 
		"Variable", "True", "False", "FunctionName", "ArgumentSeparator", "ListArgumentSeparator", 
		"Integer", "Float", "EscApos", "String", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "'{'", "'}'", "'^'", "'+'", "'-'", "'!'", "'*'", "'/'", 
		"'%'", "'<'", "'<='", "'>'", "'>='", null, "'!='", "'&&'", "'||'", null, 
		null, null, null, null, null, "','", "';'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "LeftParenthesis", "RightParenthesis", "ListOpen", "ListClose", 
		"Circumflex", "Plus", "Minus", "BooleanNot", "Multiplication", "Division", 
		"Modulus", "LessThan", "LessThanOrEqual", "GreaterThan", "GreaterThanOrEqual", 
		"Equal", "NotEqual", "BooleanAnd", "BooleanOr", "ExerciseVariable", "FillInVariable", 
		"Variable", "True", "False", "FunctionName", "ArgumentSeparator", "ListArgumentSeparator", 
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2!\u00d1\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3"+
		"\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\20\3\20\3"+
		"\20\3\21\3\21\3\21\5\21g\n\21\3\22\3\22\3\22\3\23\3\23\3\23\3\24\3\24"+
		"\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\7\25z\n\25\f\25\16\25}\13"+
		"\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\6\26\u0088\n\26\r\26"+
		"\16\26\u0089\3\26\3\26\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3"+
		"\30\5\30\u0098\n\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31"+
		"\5\31\u00a4\n\31\3\32\3\32\7\32\u00a8\n\32\f\32\16\32\u00ab\13\32\3\33"+
		"\3\33\3\34\3\34\3\35\6\35\u00b2\n\35\r\35\16\35\u00b3\3\36\6\36\u00b7"+
		"\n\36\r\36\16\36\u00b8\3\36\3\36\6\36\u00bd\n\36\r\36\16\36\u00be\3\37"+
		"\3\37\3\37\3 \3 \3 \7 \u00c7\n \f \16 \u00ca\13 \3 \3 \3!\3!\3!\3!\3\u00c8"+
		"\2\"\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35"+
		"\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36"+
		";\37=\2? A!\3\2\b\13\2C\\aac|\u00c6\u00c6\u00d8\u00d8\u00de\u00de\u00e6"+
		"\u00e6\u00f8\u00f8\u00fe\u00fe\f\2\62;C\\aac|\u00c6\u00c6\u00d8\u00d8"+
		"\u00de\u00de\u00e6\u00e6\u00f8\u00f8\u00fe\u00fe\3\2\62;\4\2C\\c|\5\2"+
		"\62;C\\c|\5\2\13\f\17\17\"\"\2\u00da\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2"+
		"\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23"+
		"\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2"+
		"\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2"+
		"\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3"+
		"\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\3C\3\2"+
		"\2\2\5E\3\2\2\2\7G\3\2\2\2\tI\3\2\2\2\13K\3\2\2\2\rM\3\2\2\2\17O\3\2\2"+
		"\2\21Q\3\2\2\2\23S\3\2\2\2\25U\3\2\2\2\27W\3\2\2\2\31Y\3\2\2\2\33[\3\2"+
		"\2\2\35^\3\2\2\2\37`\3\2\2\2!f\3\2\2\2#h\3\2\2\2%k\3\2\2\2\'n\3\2\2\2"+
		")q\3\2\2\2+\u0080\3\2\2\2-\u008d\3\2\2\2/\u0097\3\2\2\2\61\u00a3\3\2\2"+
		"\2\63\u00a5\3\2\2\2\65\u00ac\3\2\2\2\67\u00ae\3\2\2\29\u00b1\3\2\2\2;"+
		"\u00b6\3\2\2\2=\u00c0\3\2\2\2?\u00c3\3\2\2\2A\u00cd\3\2\2\2CD\7*\2\2D"+
		"\4\3\2\2\2EF\7+\2\2F\6\3\2\2\2GH\7}\2\2H\b\3\2\2\2IJ\7\177\2\2J\n\3\2"+
		"\2\2KL\7`\2\2L\f\3\2\2\2MN\7-\2\2N\16\3\2\2\2OP\7/\2\2P\20\3\2\2\2QR\7"+
		"#\2\2R\22\3\2\2\2ST\7,\2\2T\24\3\2\2\2UV\7\61\2\2V\26\3\2\2\2WX\7\'\2"+
		"\2X\30\3\2\2\2YZ\7>\2\2Z\32\3\2\2\2[\\\7>\2\2\\]\7?\2\2]\34\3\2\2\2^_"+
		"\7@\2\2_\36\3\2\2\2`a\7@\2\2ab\7?\2\2b \3\2\2\2cd\7?\2\2dg\7?\2\2eg\7"+
		"?\2\2fc\3\2\2\2fe\3\2\2\2g\"\3\2\2\2hi\7#\2\2ij\7?\2\2j$\3\2\2\2kl\7("+
		"\2\2lm\7(\2\2m&\3\2\2\2no\7~\2\2op\7~\2\2p(\3\2\2\2qr\7]\2\2rs\7x\2\2"+
		"st\7c\2\2tu\7t\2\2uv\7?\2\2vw\3\2\2\2w{\t\2\2\2xz\t\3\2\2yx\3\2\2\2z}"+
		"\3\2\2\2{y\3\2\2\2{|\3\2\2\2|~\3\2\2\2}{\3\2\2\2~\177\7_\2\2\177*\3\2"+
		"\2\2\u0080\u0081\7]\2\2\u0081\u0082\7r\2\2\u0082\u0083\7q\2\2\u0083\u0084"+
		"\7u\2\2\u0084\u0085\7?\2\2\u0085\u0087\3\2\2\2\u0086\u0088\t\4\2\2\u0087"+
		"\u0086\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u0087\3\2\2\2\u0089\u008a\3\2"+
		"\2\2\u008a\u008b\3\2\2\2\u008b\u008c\7_\2\2\u008c,\3\2\2\2\u008d\u008e"+
		"\4c|\2\u008e.\3\2\2\2\u008f\u0090\7V\2\2\u0090\u0091\7t\2\2\u0091\u0092"+
		"\7w\2\2\u0092\u0098\7g\2\2\u0093\u0094\7V\2\2\u0094\u0095\7T\2\2\u0095"+
		"\u0096\7W\2\2\u0096\u0098\7G\2\2\u0097\u008f\3\2\2\2\u0097\u0093\3\2\2"+
		"\2\u0098\60\3\2\2\2\u0099\u009a\7H\2\2\u009a\u009b\7c\2\2\u009b\u009c"+
		"\7n\2\2\u009c\u009d\7u\2\2\u009d\u00a4\7g\2\2\u009e\u009f\7H\2\2\u009f"+
		"\u00a0\7C\2\2\u00a0\u00a1\7N\2\2\u00a1\u00a2\7U\2\2\u00a2\u00a4\7G\2\2"+
		"\u00a3\u0099\3\2\2\2\u00a3\u009e\3\2\2\2\u00a4\62\3\2\2\2\u00a5\u00a9"+
		"\t\5\2\2\u00a6\u00a8\t\6\2\2\u00a7\u00a6\3\2\2\2\u00a8\u00ab\3\2\2\2\u00a9"+
		"\u00a7\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\64\3\2\2\2\u00ab\u00a9\3\2\2"+
		"\2\u00ac\u00ad\7.\2\2\u00ad\66\3\2\2\2\u00ae\u00af\7=\2\2\u00af8\3\2\2"+
		"\2\u00b0\u00b2\t\4\2\2\u00b1\u00b0\3\2\2\2\u00b2\u00b3\3\2\2\2\u00b3\u00b1"+
		"\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4:\3\2\2\2\u00b5\u00b7\t\4\2\2\u00b6"+
		"\u00b5\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8\u00b6\3\2\2\2\u00b8\u00b9\3\2"+
		"\2\2\u00b9\u00ba\3\2\2\2\u00ba\u00bc\7\60\2\2\u00bb\u00bd\t\4\2\2\u00bc"+
		"\u00bb\3\2\2\2\u00bd\u00be\3\2\2\2\u00be\u00bc\3\2\2\2\u00be\u00bf\3\2"+
		"\2\2\u00bf<\3\2\2\2\u00c0\u00c1\7^\2\2\u00c1\u00c2\7)\2\2\u00c2>\3\2\2"+
		"\2\u00c3\u00c8\7)\2\2\u00c4\u00c7\5=\37\2\u00c5\u00c7\13\2\2\2\u00c6\u00c4"+
		"\3\2\2\2\u00c6\u00c5\3\2\2\2\u00c7\u00ca\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c8"+
		"\u00c6\3\2\2\2\u00c9\u00cb\3\2\2\2\u00ca\u00c8\3\2\2\2\u00cb\u00cc\7)"+
		"\2\2\u00cc@\3\2\2\2\u00cd\u00ce\t\7\2\2\u00ce\u00cf\3\2\2\2\u00cf\u00d0"+
		"\b!\2\2\u00d0B\3\2\2\2\16\2f{\u0089\u0097\u00a3\u00a9\u00b3\u00b8\u00be"+
		"\u00c6\u00c8\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}