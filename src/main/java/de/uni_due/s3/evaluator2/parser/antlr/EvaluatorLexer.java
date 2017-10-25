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
		FillInVariable=21, Variable=22, True=23, False=24, E=25, I=26, Infinity=27, 
		Pi=28, FunctionName=29, ArgumentSeparator=30, ListArgumentSeparator=31, 
		Integer=32, Float=33, String=34, WS=35;
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
		"Variable", "True", "False", "E", "I", "Infinity", "Pi", "FunctionName", 
		"ArgumentSeparator", "ListArgumentSeparator", "Integer", "Float", "String", 
		"WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "'{'", "'}'", "'^'", "'+'", "'-'", "'!'", "'*'", "'/'", 
		"'%'", "'<'", "'<='", "'>'", "'>='", null, "'!='", "'&&'", "'||'", null, 
		null, null, null, null, "'E'", "'I'", "'INFINITY'", "'PI'", null, "','", 
		"';'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "LeftParenthesis", "RightParenthesis", "ListOpen", "ListClose", 
		"Circumflex", "Plus", "Minus", "BooleanNot", "Multiplication", "Division", 
		"Modulus", "LessThan", "LessThanOrEqual", "GreaterThan", "GreaterThanOrEqual", 
		"Equal", "NotEqual", "BooleanAnd", "BooleanOr", "ExerciseVariable", "FillInVariable", 
		"Variable", "True", "False", "E", "I", "Infinity", "Pi", "FunctionName", 
		"ArgumentSeparator", "ListArgumentSeparator", "Integer", "Float", "String", 
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2%\u00e3\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3"+
		"\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\16\3"+
		"\17\3\17\3\20\3\20\3\20\3\21\3\21\3\21\5\21m\n\21\3\22\3\22\3\22\3\23"+
		"\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\7\25"+
		"\u0080\n\25\f\25\16\25\u0083\13\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\3\26\6\26\u008e\n\26\r\26\16\26\u008f\3\26\3\26\3\27\3\27\3\30\3"+
		"\30\3\30\3\30\3\30\3\30\3\30\3\30\5\30\u009e\n\30\3\31\3\31\3\31\3\31"+
		"\3\31\3\31\3\31\3\31\3\31\3\31\5\31\u00aa\n\31\3\32\3\32\3\33\3\33\3\34"+
		"\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\36\3\36\7\36"+
		"\u00be\n\36\f\36\16\36\u00c1\13\36\3\37\3\37\3 \3 \3!\6!\u00c8\n!\r!\16"+
		"!\u00c9\3\"\6\"\u00cd\n\"\r\"\16\"\u00ce\3\"\3\"\6\"\u00d3\n\"\r\"\16"+
		"\"\u00d4\3#\3#\7#\u00d9\n#\f#\16#\u00dc\13#\3#\3#\3$\3$\3$\3$\3\u00da"+
		"\2%\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35"+
		"\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36"+
		";\37= ?!A\"C#E$G%\3\2\b\13\2C\\aac|\u00c6\u00c6\u00d8\u00d8\u00de\u00de"+
		"\u00e6\u00e6\u00f8\u00f8\u00fe\u00fe\f\2\62;C\\aac|\u00c6\u00c6\u00d8"+
		"\u00d8\u00de\u00de\u00e6\u00e6\u00f8\u00f8\u00fe\u00fe\3\2\62;\4\2C\\"+
		"c|\5\2\62;C\\c|\5\2\13\f\17\17\"\"\2\u00ec\2\3\3\2\2\2\2\5\3\2\2\2\2\7"+
		"\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2"+
		"\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2"+
		"\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2"+
		"\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2"+
		"\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2"+
		"\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\3I\3\2\2\2\5K\3\2\2\2\7M"+
		"\3\2\2\2\tO\3\2\2\2\13Q\3\2\2\2\rS\3\2\2\2\17U\3\2\2\2\21W\3\2\2\2\23"+
		"Y\3\2\2\2\25[\3\2\2\2\27]\3\2\2\2\31_\3\2\2\2\33a\3\2\2\2\35d\3\2\2\2"+
		"\37f\3\2\2\2!l\3\2\2\2#n\3\2\2\2%q\3\2\2\2\'t\3\2\2\2)w\3\2\2\2+\u0086"+
		"\3\2\2\2-\u0093\3\2\2\2/\u009d\3\2\2\2\61\u00a9\3\2\2\2\63\u00ab\3\2\2"+
		"\2\65\u00ad\3\2\2\2\67\u00af\3\2\2\29\u00b8\3\2\2\2;\u00bb\3\2\2\2=\u00c2"+
		"\3\2\2\2?\u00c4\3\2\2\2A\u00c7\3\2\2\2C\u00cc\3\2\2\2E\u00d6\3\2\2\2G"+
		"\u00df\3\2\2\2IJ\7*\2\2J\4\3\2\2\2KL\7+\2\2L\6\3\2\2\2MN\7}\2\2N\b\3\2"+
		"\2\2OP\7\177\2\2P\n\3\2\2\2QR\7`\2\2R\f\3\2\2\2ST\7-\2\2T\16\3\2\2\2U"+
		"V\7/\2\2V\20\3\2\2\2WX\7#\2\2X\22\3\2\2\2YZ\7,\2\2Z\24\3\2\2\2[\\\7\61"+
		"\2\2\\\26\3\2\2\2]^\7\'\2\2^\30\3\2\2\2_`\7>\2\2`\32\3\2\2\2ab\7>\2\2"+
		"bc\7?\2\2c\34\3\2\2\2de\7@\2\2e\36\3\2\2\2fg\7@\2\2gh\7?\2\2h \3\2\2\2"+
		"ij\7?\2\2jm\7?\2\2km\7?\2\2li\3\2\2\2lk\3\2\2\2m\"\3\2\2\2no\7#\2\2op"+
		"\7?\2\2p$\3\2\2\2qr\7(\2\2rs\7(\2\2s&\3\2\2\2tu\7~\2\2uv\7~\2\2v(\3\2"+
		"\2\2wx\7]\2\2xy\7x\2\2yz\7c\2\2z{\7t\2\2{|\7?\2\2|}\3\2\2\2}\u0081\t\2"+
		"\2\2~\u0080\t\3\2\2\177~\3\2\2\2\u0080\u0083\3\2\2\2\u0081\177\3\2\2\2"+
		"\u0081\u0082\3\2\2\2\u0082\u0084\3\2\2\2\u0083\u0081\3\2\2\2\u0084\u0085"+
		"\7_\2\2\u0085*\3\2\2\2\u0086\u0087\7]\2\2\u0087\u0088\7r\2\2\u0088\u0089"+
		"\7q\2\2\u0089\u008a\7u\2\2\u008a\u008b\7?\2\2\u008b\u008d\3\2\2\2\u008c"+
		"\u008e\t\4\2\2\u008d\u008c\3\2\2\2\u008e\u008f\3\2\2\2\u008f\u008d\3\2"+
		"\2\2\u008f\u0090\3\2\2\2\u0090\u0091\3\2\2\2\u0091\u0092\7_\2\2\u0092"+
		",\3\2\2\2\u0093\u0094\4c|\2\u0094.\3\2\2\2\u0095\u0096\7V\2\2\u0096\u0097"+
		"\7t\2\2\u0097\u0098\7w\2\2\u0098\u009e\7g\2\2\u0099\u009a\7V\2\2\u009a"+
		"\u009b\7T\2\2\u009b\u009c\7W\2\2\u009c\u009e\7G\2\2\u009d\u0095\3\2\2"+
		"\2\u009d\u0099\3\2\2\2\u009e\60\3\2\2\2\u009f\u00a0\7H\2\2\u00a0\u00a1"+
		"\7c\2\2\u00a1\u00a2\7n\2\2\u00a2\u00a3\7u\2\2\u00a3\u00aa\7g\2\2\u00a4"+
		"\u00a5\7H\2\2\u00a5\u00a6\7C\2\2\u00a6\u00a7\7N\2\2\u00a7\u00a8\7U\2\2"+
		"\u00a8\u00aa\7G\2\2\u00a9\u009f\3\2\2\2\u00a9\u00a4\3\2\2\2\u00aa\62\3"+
		"\2\2\2\u00ab\u00ac\7G\2\2\u00ac\64\3\2\2\2\u00ad\u00ae\7K\2\2\u00ae\66"+
		"\3\2\2\2\u00af\u00b0\7K\2\2\u00b0\u00b1\7P\2\2\u00b1\u00b2\7H\2\2\u00b2"+
		"\u00b3\7K\2\2\u00b3\u00b4\7P\2\2\u00b4\u00b5\7K\2\2\u00b5\u00b6\7V\2\2"+
		"\u00b6\u00b7\7[\2\2\u00b78\3\2\2\2\u00b8\u00b9\7R\2\2\u00b9\u00ba\7K\2"+
		"\2\u00ba:\3\2\2\2\u00bb\u00bf\t\5\2\2\u00bc\u00be\t\6\2\2\u00bd\u00bc"+
		"\3\2\2\2\u00be\u00c1\3\2\2\2\u00bf\u00bd\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0"+
		"<\3\2\2\2\u00c1\u00bf\3\2\2\2\u00c2\u00c3\7.\2\2\u00c3>\3\2\2\2\u00c4"+
		"\u00c5\7=\2\2\u00c5@\3\2\2\2\u00c6\u00c8\t\4\2\2\u00c7\u00c6\3\2\2\2\u00c8"+
		"\u00c9\3\2\2\2\u00c9\u00c7\3\2\2\2\u00c9\u00ca\3\2\2\2\u00caB\3\2\2\2"+
		"\u00cb\u00cd\t\4\2\2\u00cc\u00cb\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00cc"+
		"\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0\u00d2\7\60\2\2"+
		"\u00d1\u00d3\t\4\2\2\u00d2\u00d1\3\2\2\2\u00d3\u00d4\3\2\2\2\u00d4\u00d2"+
		"\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d5D\3\2\2\2\u00d6\u00da\7)\2\2\u00d7\u00d9"+
		"\13\2\2\2\u00d8\u00d7\3\2\2\2\u00d9\u00dc\3\2\2\2\u00da\u00db\3\2\2\2"+
		"\u00da\u00d8\3\2\2\2\u00db\u00dd\3\2\2\2\u00dc\u00da\3\2\2\2\u00dd\u00de"+
		"\7)\2\2\u00deF\3\2\2\2\u00df\u00e0\t\7\2\2\u00e0\u00e1\3\2\2\2\u00e1\u00e2"+
		"\b$\2\2\u00e2H\3\2\2\2\r\2l\u0081\u008f\u009d\u00a9\u00bf\u00c9\u00ce"+
		"\u00d4\u00da\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}