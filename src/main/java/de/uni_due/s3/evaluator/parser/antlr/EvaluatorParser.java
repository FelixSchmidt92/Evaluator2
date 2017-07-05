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
		LeftParenthesis=1, RightParenthesis=2, SetOpen=3, SetClose=4, Plus=5, 
		Minus=6, BooleanNot=7, Multiplication=8, Division=9, Modulus=10, LessThan=11, 
		LessThanOrEqual=12, GreaterThan=13, GreaterThanOrEqual=14, Equal=15, NotEqual=16, 
		BooleanAnd=17, BooleanOr=18, ExerciseVariable=19, FillInVariable=20, FunctionName=21, 
		ArgumentSeparator=22, SetArgumentSeparator=23, Integer=24, Float=25, String=26, 
		WS=27;
	public static final int
		RULE_expression = 0, RULE_terminal = 1, RULE_unaryoperator = 2, RULE_binaryoperator = 3, 
		RULE_set = 4, RULE_nestedFunction = 5;
	public static final String[] ruleNames = {
		"expression", "terminal", "unaryoperator", "binaryoperator", "set", "nestedFunction"
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
		public NestedFunctionContext nestedFunction() {
			return getRuleContext(NestedFunctionContext.class,0);
		}
		public TerminalNode LeftParenthesis() { return getToken(EvaluatorParser.LeftParenthesis, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode RightParenthesis() { return getToken(EvaluatorParser.RightParenthesis, 0); }
		public UnaryoperatorContext unaryoperator() {
			return getRuleContext(UnaryoperatorContext.class,0);
		}
		public SetContext set() {
			return getRuleContext(SetContext.class,0);
		}
		public TerminalContext terminal() {
			return getRuleContext(TerminalContext.class,0);
		}
		public BinaryoperatorContext binaryoperator() {
			return getRuleContext(BinaryoperatorContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitExpression(this);
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
			setState(23);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FunctionName:
				{
				setState(13);
				nestedFunction();
				}
				break;
			case LeftParenthesis:
				{
				setState(14);
				match(LeftParenthesis);
				setState(15);
				expression(0);
				setState(16);
				match(RightParenthesis);
				}
				break;
			case Plus:
			case Minus:
			case BooleanNot:
				{
				setState(18);
				unaryoperator();
				setState(19);
				expression(4);
				}
				break;
			case SetOpen:
				{
				setState(21);
				set();
				}
				break;
			case ExerciseVariable:
			case FillInVariable:
			case Integer:
			case Float:
			case String:
				{
				setState(22);
				terminal();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(31);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ExpressionContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_expression);
					setState(25);
					if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
					setState(26);
					binaryoperator();
					setState(27);
					expression(4);
					}
					} 
				}
				setState(33);
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

	public static class TerminalContext extends ParserRuleContext {
		public TerminalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_terminal; }
	 
		public TerminalContext() { }
		public void copyFrom(TerminalContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class TextValueContext extends TerminalContext {
		public Token value;
		public TerminalNode String() { return getToken(EvaluatorParser.String, 0); }
		public TextValueContext(TerminalContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitTextValue(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FloatValueContext extends TerminalContext {
		public Token value;
		public TerminalNode Float() { return getToken(EvaluatorParser.Float, 0); }
		public FloatValueContext(TerminalContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitFloatValue(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExerciseVarNameContext extends TerminalContext {
		public Token name;
		public TerminalNode ExerciseVariable() { return getToken(EvaluatorParser.ExerciseVariable, 0); }
		public ExerciseVarNameContext(TerminalContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitExerciseVarName(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntegerValueContext extends TerminalContext {
		public Token value;
		public TerminalNode Integer() { return getToken(EvaluatorParser.Integer, 0); }
		public IntegerValueContext(TerminalContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitIntegerValue(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FillInVarNameContext extends TerminalContext {
		public Token name;
		public TerminalNode FillInVariable() { return getToken(EvaluatorParser.FillInVariable, 0); }
		public FillInVarNameContext(TerminalContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitFillInVarName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TerminalContext terminal() throws RecognitionException {
		TerminalContext _localctx = new TerminalContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_terminal);
		try {
			setState(39);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Integer:
				_localctx = new IntegerValueContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(34);
				((IntegerValueContext)_localctx).value = match(Integer);
				}
				break;
			case Float:
				_localctx = new FloatValueContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(35);
				((FloatValueContext)_localctx).value = match(Float);
				}
				break;
			case ExerciseVariable:
				_localctx = new ExerciseVarNameContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(36);
				((ExerciseVarNameContext)_localctx).name = match(ExerciseVariable);
				}
				break;
			case FillInVariable:
				_localctx = new FillInVarNameContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(37);
				((FillInVarNameContext)_localctx).name = match(FillInVariable);
				}
				break;
			case String:
				_localctx = new TextValueContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(38);
				((TextValueContext)_localctx).value = match(String);
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

	public static class UnaryoperatorContext extends ParserRuleContext {
		public Token operator;
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
		enterRule(_localctx, 4, RULE_unaryoperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(41);
			((UnaryoperatorContext)_localctx).operator = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Plus) | (1L << Minus) | (1L << BooleanNot))) != 0)) ) {
				((UnaryoperatorContext)_localctx).operator = (Token)_errHandler.recoverInline(this);
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
		public Token operator;
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
		enterRule(_localctx, 6, RULE_binaryoperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43);
			((BinaryoperatorContext)_localctx).operator = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Plus) | (1L << Minus) | (1L << Multiplication) | (1L << Division) | (1L << Modulus) | (1L << LessThan) | (1L << LessThanOrEqual) | (1L << GreaterThan) | (1L << GreaterThanOrEqual) | (1L << Equal) | (1L << NotEqual) | (1L << BooleanAnd) | (1L << BooleanOr))) != 0)) ) {
				((BinaryoperatorContext)_localctx).operator = (Token)_errHandler.recoverInline(this);
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

	public static class SetContext extends ParserRuleContext {
		public ExpressionContext expression;
		public List<ExpressionContext> arguments = new ArrayList<ExpressionContext>();
		public TerminalNode SetOpen() { return getToken(EvaluatorParser.SetOpen, 0); }
		public TerminalNode SetClose() { return getToken(EvaluatorParser.SetClose, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> SetArgumentSeparator() { return getTokens(EvaluatorParser.SetArgumentSeparator); }
		public TerminalNode SetArgumentSeparator(int i) {
			return getToken(EvaluatorParser.SetArgumentSeparator, i);
		}
		public SetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_set; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitSet(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SetContext set() throws RecognitionException {
		SetContext _localctx = new SetContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_set);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45);
			match(SetOpen);
			setState(54);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LeftParenthesis) | (1L << SetOpen) | (1L << Plus) | (1L << Minus) | (1L << BooleanNot) | (1L << ExerciseVariable) | (1L << FillInVariable) | (1L << FunctionName) | (1L << Integer) | (1L << Float) | (1L << String))) != 0)) {
				{
				setState(46);
				((SetContext)_localctx).expression = expression(0);
				((SetContext)_localctx).arguments.add(((SetContext)_localctx).expression);
				setState(51);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SetArgumentSeparator) {
					{
					{
					setState(47);
					match(SetArgumentSeparator);
					setState(48);
					((SetContext)_localctx).expression = expression(0);
					((SetContext)_localctx).arguments.add(((SetContext)_localctx).expression);
					}
					}
					setState(53);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(56);
			match(SetClose);
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
		enterRule(_localctx, 10, RULE_nestedFunction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			((NestedFunctionContext)_localctx).name = match(FunctionName);
			setState(59);
			match(LeftParenthesis);
			setState(68);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LeftParenthesis) | (1L << SetOpen) | (1L << Plus) | (1L << Minus) | (1L << BooleanNot) | (1L << ExerciseVariable) | (1L << FillInVariable) | (1L << FunctionName) | (1L << Integer) | (1L << Float) | (1L << String))) != 0)) {
				{
				setState(60);
				((NestedFunctionContext)_localctx).expression = expression(0);
				((NestedFunctionContext)_localctx).arguments.add(((NestedFunctionContext)_localctx).expression);
				setState(65);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==ArgumentSeparator) {
					{
					{
					setState(61);
					match(ArgumentSeparator);
					setState(62);
					((NestedFunctionContext)_localctx).expression = expression(0);
					((NestedFunctionContext)_localctx).arguments.add(((NestedFunctionContext)_localctx).expression);
					}
					}
					setState(67);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(70);
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
			return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\35K\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\5\2\32\n\2\3\2\3\2\3\2\3\2\7\2 \n\2\f\2\16\2#\13\2\3\3\3\3\3"+
		"\3\3\3\3\3\5\3*\n\3\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\6\7\6\64\n\6\f\6\16"+
		"\6\67\13\6\5\69\n\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\7\7B\n\7\f\7\16\7E\13"+
		"\7\5\7G\n\7\3\7\3\7\3\7\2\3\2\b\2\4\6\b\n\f\2\4\3\2\7\t\4\2\7\b\n\24\2"+
		"Q\2\31\3\2\2\2\4)\3\2\2\2\6+\3\2\2\2\b-\3\2\2\2\n/\3\2\2\2\f<\3\2\2\2"+
		"\16\17\b\2\1\2\17\32\5\f\7\2\20\21\7\3\2\2\21\22\5\2\2\2\22\23\7\4\2\2"+
		"\23\32\3\2\2\2\24\25\5\6\4\2\25\26\5\2\2\6\26\32\3\2\2\2\27\32\5\n\6\2"+
		"\30\32\5\4\3\2\31\16\3\2\2\2\31\20\3\2\2\2\31\24\3\2\2\2\31\27\3\2\2\2"+
		"\31\30\3\2\2\2\32!\3\2\2\2\33\34\f\5\2\2\34\35\5\b\5\2\35\36\5\2\2\6\36"+
		" \3\2\2\2\37\33\3\2\2\2 #\3\2\2\2!\37\3\2\2\2!\"\3\2\2\2\"\3\3\2\2\2#"+
		"!\3\2\2\2$*\7\32\2\2%*\7\33\2\2&*\7\25\2\2\'*\7\26\2\2(*\7\34\2\2)$\3"+
		"\2\2\2)%\3\2\2\2)&\3\2\2\2)\'\3\2\2\2)(\3\2\2\2*\5\3\2\2\2+,\t\2\2\2,"+
		"\7\3\2\2\2-.\t\3\2\2.\t\3\2\2\2/8\7\5\2\2\60\65\5\2\2\2\61\62\7\31\2\2"+
		"\62\64\5\2\2\2\63\61\3\2\2\2\64\67\3\2\2\2\65\63\3\2\2\2\65\66\3\2\2\2"+
		"\669\3\2\2\2\67\65\3\2\2\28\60\3\2\2\289\3\2\2\29:\3\2\2\2:;\7\6\2\2;"+
		"\13\3\2\2\2<=\7\27\2\2=F\7\3\2\2>C\5\2\2\2?@\7\30\2\2@B\5\2\2\2A?\3\2"+
		"\2\2BE\3\2\2\2CA\3\2\2\2CD\3\2\2\2DG\3\2\2\2EC\3\2\2\2F>\3\2\2\2FG\3\2"+
		"\2\2GH\3\2\2\2HI\7\4\2\2I\r\3\2\2\2\t\31!)\658CF";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}