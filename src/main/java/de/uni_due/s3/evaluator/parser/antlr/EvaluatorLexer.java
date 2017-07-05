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
		LeftParenthesis=1, RightParenthesis=2, SetOpen=3, SetClose=4, Plus=5, 
		Minus=6, BooleanNot=7, Multiplication=8, Division=9, Modulus=10, LessThan=11, 
		LessThanOrEqual=12, GreaterThan=13, GreaterThanOrEqual=14, Equal=15, NotEqual=16, 
		BooleanAnd=17, BooleanOr=18, ExerciseVariable=19, FillInVariable=20, FunctionName=21, 
		ArgumentSeparator=22, SetArgumentSeparator=23, Integer=24, Float=25, String=26, 
		WS=27;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"LeftParenthesis", "RightParenthesis", "SetOpen", "SetClose", "Plus", 
		"Minus", "BooleanNot", "Multiplication", "Division", "Modulus", "LessThan", 
		"LessThanOrEqual", "GreaterThan", "GreaterThanOrEqual", "Equal", "NotEqual", 
		"BooleanAnd", "BooleanOr", "ExerciseVariable", "FillInVariable", "FunctionName", 
		"ArgumentSeparator", "SetArgumentSeparator", "Integer", "Float", "String", 
		"WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "'{'", "'}'", "'+'", "'-'", "'!'", "'*'", "'/'", "'%'", 
		"'<'", "'<='", "'>'", "'>='", null, "'!='", "'&&'", "'||'", null, null, 
		null, "','", "';'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "LeftParenthesis", "RightParenthesis", "SetOpen", "SetClose", "Plus", 
		"Minus", "BooleanNot", "Multiplication", "Division", "Modulus", "LessThan", 
		"LessThanOrEqual", "GreaterThan", "GreaterThanOrEqual", "Equal", "NotEqual", 
		"BooleanAnd", "BooleanOr", "ExerciseVariable", "FillInVariable", "FunctionName", 
		"ArgumentSeparator", "SetArgumentSeparator", "Integer", "Float", "String", 
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\35\u00e1\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3"+
		"\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\r\3"+
		"\16\3\16\3\17\3\17\3\17\3\20\3\20\3\20\5\20[\n\20\3\21\3\21\3\21\3\22"+
		"\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\7\24"+
		"n\n\24\f\24\16\24q\13\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\7\24}\n\24\f\24\16\24\u0080\13\24\3\24\3\24\5\24\u0084\n\24\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\6\25\u008d\n\25\r\25\16\25\u008e\3\25\3"+
		"\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\6\25\u009a\n\25\r\25\16\25\u009b"+
		"\3\25\3\25\5\25\u00a0\n\25\3\26\3\26\7\26\u00a4\n\26\f\26\16\26\u00a7"+
		"\13\26\3\27\3\27\3\30\3\30\3\31\6\31\u00ae\n\31\r\31\16\31\u00af\3\31"+
		"\3\31\6\31\u00b4\n\31\r\31\16\31\u00b5\3\31\5\31\u00b9\n\31\3\32\6\32"+
		"\u00bc\n\32\r\32\16\32\u00bd\3\32\3\32\6\32\u00c2\n\32\r\32\16\32\u00c3"+
		"\3\32\3\32\6\32\u00c8\n\32\r\32\16\32\u00c9\3\32\3\32\6\32\u00ce\n\32"+
		"\r\32\16\32\u00cf\3\32\5\32\u00d3\n\32\3\33\3\33\7\33\u00d7\n\33\f\33"+
		"\16\33\u00da\13\33\3\33\3\33\3\34\3\34\3\34\3\34\3\u00d8\2\35\3\3\5\4"+
		"\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22"+
		"#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\35\3\2\b\13\2C\\aa"+
		"c|\u00c6\u00c6\u00d8\u00d8\u00de\u00de\u00e6\u00e6\u00f8\u00f8\u00fe\u00fe"+
		"\f\2\62;C\\aac|\u00c6\u00c6\u00d8\u00d8\u00de\u00de\u00e6\u00e6\u00f8"+
		"\u00f8\u00fe\u00fe\3\2\62;\4\2C\\c|\5\2\62;C\\c|\5\2\13\f\17\17\"\"\2"+
		"\u00f1\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2"+
		"\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3"+
		"\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2"+
		"\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2"+
		"/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\39\3\2\2"+
		"\2\5;\3\2\2\2\7=\3\2\2\2\t?\3\2\2\2\13A\3\2\2\2\rC\3\2\2\2\17E\3\2\2\2"+
		"\21G\3\2\2\2\23I\3\2\2\2\25K\3\2\2\2\27M\3\2\2\2\31O\3\2\2\2\33R\3\2\2"+
		"\2\35T\3\2\2\2\37Z\3\2\2\2!\\\3\2\2\2#_\3\2\2\2%b\3\2\2\2\'\u0083\3\2"+
		"\2\2)\u009f\3\2\2\2+\u00a1\3\2\2\2-\u00a8\3\2\2\2/\u00aa\3\2\2\2\61\u00b8"+
		"\3\2\2\2\63\u00d2\3\2\2\2\65\u00d4\3\2\2\2\67\u00dd\3\2\2\29:\7*\2\2:"+
		"\4\3\2\2\2;<\7+\2\2<\6\3\2\2\2=>\7}\2\2>\b\3\2\2\2?@\7\177\2\2@\n\3\2"+
		"\2\2AB\7-\2\2B\f\3\2\2\2CD\7/\2\2D\16\3\2\2\2EF\7#\2\2F\20\3\2\2\2GH\7"+
		",\2\2H\22\3\2\2\2IJ\7\61\2\2J\24\3\2\2\2KL\7\'\2\2L\26\3\2\2\2MN\7>\2"+
		"\2N\30\3\2\2\2OP\7>\2\2PQ\7?\2\2Q\32\3\2\2\2RS\7@\2\2S\34\3\2\2\2TU\7"+
		"@\2\2UV\7?\2\2V\36\3\2\2\2WX\7?\2\2X[\7?\2\2Y[\7?\2\2ZW\3\2\2\2ZY\3\2"+
		"\2\2[ \3\2\2\2\\]\7#\2\2]^\7?\2\2^\"\3\2\2\2_`\7(\2\2`a\7(\2\2a$\3\2\2"+
		"\2bc\7~\2\2cd\7~\2\2d&\3\2\2\2ef\7]\2\2fg\7x\2\2gh\7c\2\2hi\7t\2\2ij\7"+
		"?\2\2jk\3\2\2\2ko\t\2\2\2ln\t\3\2\2ml\3\2\2\2nq\3\2\2\2om\3\2\2\2op\3"+
		"\2\2\2pr\3\2\2\2qo\3\2\2\2r\u0084\7_\2\2st\7)\2\2tu\7]\2\2uv\7x\2\2vw"+
		"\7c\2\2wx\7t\2\2xy\7?\2\2yz\3\2\2\2z~\t\2\2\2{}\t\3\2\2|{\3\2\2\2}\u0080"+
		"\3\2\2\2~|\3\2\2\2~\177\3\2\2\2\177\u0081\3\2\2\2\u0080~\3\2\2\2\u0081"+
		"\u0082\7_\2\2\u0082\u0084\7)\2\2\u0083e\3\2\2\2\u0083s\3\2\2\2\u0084("+
		"\3\2\2\2\u0085\u0086\7]\2\2\u0086\u0087\7r\2\2\u0087\u0088\7q\2\2\u0088"+
		"\u0089\7u\2\2\u0089\u008a\7?\2\2\u008a\u008c\3\2\2\2\u008b\u008d\t\4\2"+
		"\2\u008c\u008b\3\2\2\2\u008d\u008e\3\2\2\2\u008e\u008c\3\2\2\2\u008e\u008f"+
		"\3\2\2\2\u008f\u0090\3\2\2\2\u0090\u00a0\7_\2\2\u0091\u0092\7)\2\2\u0092"+
		"\u0093\7]\2\2\u0093\u0094\7r\2\2\u0094\u0095\7q\2\2\u0095\u0096\7u\2\2"+
		"\u0096\u0097\7?\2\2\u0097\u0099\3\2\2\2\u0098\u009a\t\4\2\2\u0099\u0098"+
		"\3\2\2\2\u009a\u009b\3\2\2\2\u009b\u0099\3\2\2\2\u009b\u009c\3\2\2\2\u009c"+
		"\u009d\3\2\2\2\u009d\u009e\7_\2\2\u009e\u00a0\7)\2\2\u009f\u0085\3\2\2"+
		"\2\u009f\u0091\3\2\2\2\u00a0*\3\2\2\2\u00a1\u00a5\t\5\2\2\u00a2\u00a4"+
		"\t\6\2\2\u00a3\u00a2\3\2\2\2\u00a4\u00a7\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a5"+
		"\u00a6\3\2\2\2\u00a6,\3\2\2\2\u00a7\u00a5\3\2\2\2\u00a8\u00a9\7.\2\2\u00a9"+
		".\3\2\2\2\u00aa\u00ab\7=\2\2\u00ab\60\3\2\2\2\u00ac\u00ae\t\4\2\2\u00ad"+
		"\u00ac\3\2\2\2\u00ae\u00af\3\2\2\2\u00af\u00ad\3\2\2\2\u00af\u00b0\3\2"+
		"\2\2\u00b0\u00b9\3\2\2\2\u00b1\u00b3\7)\2\2\u00b2\u00b4\t\4\2\2\u00b3"+
		"\u00b2\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b5\u00b6\3\2"+
		"\2\2\u00b6\u00b7\3\2\2\2\u00b7\u00b9\7)\2\2\u00b8\u00ad\3\2\2\2\u00b8"+
		"\u00b1\3\2\2\2\u00b9\62\3\2\2\2\u00ba\u00bc\t\4\2\2\u00bb\u00ba\3\2\2"+
		"\2\u00bc\u00bd\3\2\2\2\u00bd\u00bb\3\2\2\2\u00bd\u00be\3\2\2\2\u00be\u00bf"+
		"\3\2\2\2\u00bf\u00c1\7\60\2\2\u00c0\u00c2\t\4\2\2\u00c1\u00c0\3\2\2\2"+
		"\u00c2\u00c3\3\2\2\2\u00c3\u00c1\3\2\2\2\u00c3\u00c4\3\2\2\2\u00c4\u00d3"+
		"\3\2\2\2\u00c5\u00c7\7)\2\2\u00c6\u00c8\t\4\2\2\u00c7\u00c6\3\2\2\2\u00c8"+
		"\u00c9\3\2\2\2\u00c9\u00c7\3\2\2\2\u00c9\u00ca\3\2\2\2\u00ca\u00cb\3\2"+
		"\2\2\u00cb\u00cd\7\60\2\2\u00cc\u00ce\t\4\2\2\u00cd\u00cc\3\2\2\2\u00ce"+
		"\u00cf\3\2\2\2\u00cf\u00cd\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0\u00d1\3\2"+
		"\2\2\u00d1\u00d3\7)\2\2\u00d2\u00bb\3\2\2\2\u00d2\u00c5\3\2\2\2\u00d3"+
		"\64\3\2\2\2\u00d4\u00d8\7)\2\2\u00d5\u00d7\13\2\2\2\u00d6\u00d5\3\2\2"+
		"\2\u00d7\u00da\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d8\u00d6\3\2\2\2\u00d9\u00db"+
		"\3\2\2\2\u00da\u00d8\3\2\2\2\u00db\u00dc\7)\2\2\u00dc\66\3\2\2\2\u00dd"+
		"\u00de\t\7\2\2\u00de\u00df\3\2\2\2\u00df\u00e0\b\34\2\2\u00e08\3\2\2\2"+
		"\24\2Zo~\u0083\u008e\u009b\u009f\u00a5\u00af\u00b5\u00b8\u00bd\u00c3\u00c9"+
		"\u00cf\u00d2\u00d8\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}