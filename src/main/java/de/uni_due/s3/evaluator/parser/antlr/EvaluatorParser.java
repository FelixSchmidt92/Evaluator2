package de.uni_due.s3.evaluator.parser.antlr;

// Generated from EvaluatorParser.g4 by ANTLR 4.7
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class EvaluatorParser extends Parser {
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
	public static final int
		RULE_expression = 0, RULE_unaryoperator = 1, RULE_binaryoperator = 2, 
		RULE_nestedFunction = 3;
	public static final String[] ruleNames = {
		"expression", "unaryoperator", "binaryoperator", "nestedFunction"
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

	@Override
	public String getGrammarFileName() { return "EvaluatorParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public EvaluatorParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ParenthesesContext extends ExpressionContext {
		public TerminalNode LeftParenthesis() { return getToken(EvaluatorParser.LeftParenthesis, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RightParenthesis() { return getToken(EvaluatorParser.RightParenthesis, 0); }
		public ParenthesesContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitParentheses(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UnaryOperatorContext extends ExpressionContext {
		public UnaryoperatorContext value;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public UnaryoperatorContext unaryoperator() {
			return getRuleContext(UnaryoperatorContext.class,0);
		}
		public UnaryOperatorContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitUnaryOperator(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TextValueContext extends ExpressionContext {
		public Token value;
		public TerminalNode String() { return getToken(EvaluatorParser.String, 0); }
		public TextValueContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitTextValue(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BinaryOperatorContext extends ExpressionContext {
		public BinaryoperatorContext value;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public BinaryoperatorContext binaryoperator() {
			return getRuleContext(BinaryoperatorContext.class,0);
		}
		public BinaryOperatorContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitBinaryOperator(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FunctionContext extends ExpressionContext {
		public NestedFunctionContext nestedFunction() {
			return getRuleContext(NestedFunctionContext.class,0);
		}
		public FunctionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitFunction(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FloatValueContext extends ExpressionContext {
		public Token value;
		public TerminalNode Float() { return getToken(EvaluatorParser.Float, 0); }
		public FloatValueContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitFloatValue(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExerciseVarNameContext extends ExpressionContext {
		public Token name;
		public TerminalNode ExerciseVariable() { return getToken(EvaluatorParser.ExerciseVariable, 0); }
		public ExerciseVarNameContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitExerciseVarName(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntegerValueContext extends ExpressionContext {
		public Token value;
		public TerminalNode Integer() { return getToken(EvaluatorParser.Integer, 0); }
		public IntegerValueContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitIntegerValue(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FillInVarNameContext extends ExpressionContext {
		public Token name;
		public TerminalNode FillInVariable() { return getToken(EvaluatorParser.FillInVariable, 0); }
		public FillInVarNameContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitFillInVarName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 0;
		enterRecursionRule(_localctx, 0, RULE_expression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(22);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FunctionName:
				{
				_localctx = new FunctionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(9);
				nestedFunction();
				}
				break;
			case LeftParenthesis:
				{
				_localctx = new ParenthesesContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(10);
				match(LeftParenthesis);
				setState(11);
				expression(0);
				setState(12);
				match(RightParenthesis);
				}
				break;
			case Plus:
			case Minus:
			case BooleanNot:
				{
				_localctx = new UnaryOperatorContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(14);
				((UnaryOperatorContext)_localctx).value = unaryoperator();
				setState(15);
				expression(7);
				}
				break;
			case Integer:
				{
				_localctx = new IntegerValueContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(17);
				((IntegerValueContext)_localctx).value = match(Integer);
				}
				break;
			case Float:
				{
				_localctx = new FloatValueContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(18);
				((FloatValueContext)_localctx).value = match(Float);
				}
				break;
			case ExerciseVariable:
				{
				_localctx = new ExerciseVarNameContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(19);
				((ExerciseVarNameContext)_localctx).name = match(ExerciseVariable);
				}
				break;
			case FillInVariable:
				{
				_localctx = new FillInVarNameContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(20);
				((FillInVarNameContext)_localctx).name = match(FillInVariable);
				}
				break;
			case String:
				{
				_localctx = new TextValueContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(21);
				((TextValueContext)_localctx).value = match(String);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(30);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new BinaryOperatorContext(new ExpressionContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_expression);
					setState(24);
					if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
					setState(25);
					((BinaryOperatorContext)_localctx).value = binaryoperator();
					setState(26);
					expression(7);
					}
					} 
				}
				setState(32);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
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

	public static class UnaryoperatorContext extends ParserRuleContext {
		public TerminalNode Plus() { return getToken(EvaluatorParser.Plus, 0); }
		public TerminalNode Minus() { return getToken(EvaluatorParser.Minus, 0); }
		public TerminalNode BooleanNot() { return getToken(EvaluatorParser.BooleanNot, 0); }
		public UnaryoperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryoperator; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitUnaryoperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnaryoperatorContext unaryoperator() throws RecognitionException {
		UnaryoperatorContext _localctx = new UnaryoperatorContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_unaryoperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(33);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Plus) | (1L << Minus) | (1L << BooleanNot))) != 0)) ) {
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

	public static class BinaryoperatorContext extends ParserRuleContext {
		public TerminalNode Multiplication() { return getToken(EvaluatorParser.Multiplication, 0); }
		public TerminalNode Division() { return getToken(EvaluatorParser.Division, 0); }
		public TerminalNode Modulus() { return getToken(EvaluatorParser.Modulus, 0); }
		public TerminalNode Plus() { return getToken(EvaluatorParser.Plus, 0); }
		public TerminalNode Minus() { return getToken(EvaluatorParser.Minus, 0); }
		public TerminalNode LessThan() { return getToken(EvaluatorParser.LessThan, 0); }
		public TerminalNode LessThanOrEqual() { return getToken(EvaluatorParser.LessThanOrEqual, 0); }
		public TerminalNode GreaterThan() { return getToken(EvaluatorParser.GreaterThan, 0); }
		public TerminalNode GreaterThanOrEqual() { return getToken(EvaluatorParser.GreaterThanOrEqual, 0); }
		public TerminalNode Equal() { return getToken(EvaluatorParser.Equal, 0); }
		public TerminalNode NotEqual() { return getToken(EvaluatorParser.NotEqual, 0); }
		public TerminalNode BooleanAnd() { return getToken(EvaluatorParser.BooleanAnd, 0); }
		public TerminalNode BooleanOr() { return getToken(EvaluatorParser.BooleanOr, 0); }
		public BinaryoperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binaryoperator; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitBinaryoperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BinaryoperatorContext binaryoperator() throws RecognitionException {
		BinaryoperatorContext _localctx = new BinaryoperatorContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_binaryoperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(35);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Plus) | (1L << Minus) | (1L << Multiplication) | (1L << Division) | (1L << Modulus) | (1L << LessThan) | (1L << LessThanOrEqual) | (1L << GreaterThan) | (1L << GreaterThanOrEqual) | (1L << Equal) | (1L << NotEqual) | (1L << BooleanAnd) | (1L << BooleanOr))) != 0)) ) {
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

	public static class NestedFunctionContext extends ParserRuleContext {
		public Token name;
		public ExpressionContext expression;
		public List<ExpressionContext> arguments = new ArrayList<ExpressionContext>();
		public TerminalNode LeftParenthesis() { return getToken(EvaluatorParser.LeftParenthesis, 0); }
		public TerminalNode RightParenthesis() { return getToken(EvaluatorParser.RightParenthesis, 0); }
		public TerminalNode FunctionName() { return getToken(EvaluatorParser.FunctionName, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> ArgumentSeparator() { return getTokens(EvaluatorParser.ArgumentSeparator); }
		public TerminalNode ArgumentSeparator(int i) {
			return getToken(EvaluatorParser.ArgumentSeparator, i);
		}
		public NestedFunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nestedFunction; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitNestedFunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NestedFunctionContext nestedFunction() throws RecognitionException {
		NestedFunctionContext _localctx = new NestedFunctionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_nestedFunction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(37);
			((NestedFunctionContext)_localctx).name = match(FunctionName);
			setState(38);
			match(LeftParenthesis);
			setState(47);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LeftParenthesis) | (1L << Plus) | (1L << Minus) | (1L << BooleanNot) | (1L << ExerciseVariable) | (1L << FillInVariable) | (1L << FunctionName) | (1L << Integer) | (1L << Float) | (1L << String))) != 0)) {
				{
				setState(39);
				((NestedFunctionContext)_localctx).expression = expression(0);
				((NestedFunctionContext)_localctx).arguments.add(((NestedFunctionContext)_localctx).expression);
				setState(44);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==ArgumentSeparator) {
					{
					{
					setState(40);
					match(ArgumentSeparator);
					setState(41);
					((NestedFunctionContext)_localctx).expression = expression(0);
					((NestedFunctionContext)_localctx).arguments.add(((NestedFunctionContext)_localctx).expression);
					}
					}
					setState(46);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(49);
			match(RightParenthesis);
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
		case 0:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 6);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\34\66\4\2\t\2\4\3"+
		"\t\3\4\4\t\4\4\5\t\5\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\5\2\31\n\2\3\2\3\2\3\2\3\2\7\2\37\n\2\f\2\16\2\"\13\2\3\3\3\3\3"+
		"\4\3\4\3\5\3\5\3\5\3\5\3\5\7\5-\n\5\f\5\16\5\60\13\5\5\5\62\n\5\3\5\3"+
		"\5\3\5\2\3\2\6\2\4\6\b\2\4\3\2\5\7\4\2\5\6\b\22\2;\2\30\3\2\2\2\4#\3\2"+
		"\2\2\6%\3\2\2\2\b\'\3\2\2\2\n\13\b\2\1\2\13\31\5\b\5\2\f\r\7\3\2\2\r\16"+
		"\5\2\2\2\16\17\7\4\2\2\17\31\3\2\2\2\20\21\5\4\3\2\21\22\5\2\2\t\22\31"+
		"\3\2\2\2\23\31\7\31\2\2\24\31\7\32\2\2\25\31\7\23\2\2\26\31\7\24\2\2\27"+
		"\31\7\33\2\2\30\n\3\2\2\2\30\f\3\2\2\2\30\20\3\2\2\2\30\23\3\2\2\2\30"+
		"\24\3\2\2\2\30\25\3\2\2\2\30\26\3\2\2\2\30\27\3\2\2\2\31 \3\2\2\2\32\33"+
		"\f\b\2\2\33\34\5\6\4\2\34\35\5\2\2\t\35\37\3\2\2\2\36\32\3\2\2\2\37\""+
		"\3\2\2\2 \36\3\2\2\2 !\3\2\2\2!\3\3\2\2\2\" \3\2\2\2#$\t\2\2\2$\5\3\2"+
		"\2\2%&\t\3\2\2&\7\3\2\2\2\'(\7\26\2\2(\61\7\3\2\2).\5\2\2\2*+\7\30\2\2"+
		"+-\5\2\2\2,*\3\2\2\2-\60\3\2\2\2.,\3\2\2\2./\3\2\2\2/\62\3\2\2\2\60.\3"+
		"\2\2\2\61)\3\2\2\2\61\62\3\2\2\2\62\63\3\2\2\2\63\64\7\4\2\2\64\t\3\2"+
		"\2\2\6\30 .\61";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}