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
		RULE_expression = 0, RULE_unaryOperatorForExpression = 1, RULE_binaryOperatorForExpression = 2, 
		RULE_set = 3, RULE_nestedFunction = 4;
	public static final String[] ruleNames = {
		"expression", "unaryOperatorForExpression", "binaryOperatorForExpression", 
		"set", "nestedFunction"
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
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class TextValueContext extends ExpressionContext {
		public Token value;
		public TerminalNode String() { return getToken(EvaluatorParser.String, 0); }
		public TextValueContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EvaluatorParserListener ) ((EvaluatorParserListener)listener).enterTextValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EvaluatorParserListener ) ((EvaluatorParserListener)listener).exitTextValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitTextValue(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SetInExpressionContext extends ExpressionContext {
		public SetContext set() {
			return getRuleContext(SetContext.class,0);
		}
		public SetInExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EvaluatorParserListener ) ((EvaluatorParserListener)listener).enterSetInExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EvaluatorParserListener ) ((EvaluatorParserListener)listener).exitSetInExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitSetInExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BinaryContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public BinaryOperatorForExpressionContext binaryOperatorForExpression() {
			return getRuleContext(BinaryOperatorForExpressionContext.class,0);
		}
		public BinaryContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EvaluatorParserListener ) ((EvaluatorParserListener)listener).enterBinary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EvaluatorParserListener ) ((EvaluatorParserListener)listener).exitBinary(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitBinary(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FloatValueContext extends ExpressionContext {
		public Token value;
		public TerminalNode Float() { return getToken(EvaluatorParser.Float, 0); }
		public FloatValueContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EvaluatorParserListener ) ((EvaluatorParserListener)listener).enterFloatValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EvaluatorParserListener ) ((EvaluatorParserListener)listener).exitFloatValue(this);
		}
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EvaluatorParserListener ) ((EvaluatorParserListener)listener).enterExerciseVarName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EvaluatorParserListener ) ((EvaluatorParserListener)listener).exitExerciseVarName(this);
		}
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EvaluatorParserListener ) ((EvaluatorParserListener)listener).enterIntegerValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EvaluatorParserListener ) ((EvaluatorParserListener)listener).exitIntegerValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitIntegerValue(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NestedFunctionInExpressionContext extends ExpressionContext {
		public NestedFunctionContext nestedFunction() {
			return getRuleContext(NestedFunctionContext.class,0);
		}
		public NestedFunctionInExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EvaluatorParserListener ) ((EvaluatorParserListener)listener).enterNestedFunctionInExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EvaluatorParserListener ) ((EvaluatorParserListener)listener).exitNestedFunctionInExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitNestedFunctionInExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UnaryContext extends ExpressionContext {
		public UnaryOperatorForExpressionContext unaryOperatorForExpression() {
			return getRuleContext(UnaryOperatorForExpressionContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public UnaryContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EvaluatorParserListener ) ((EvaluatorParserListener)listener).enterUnary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EvaluatorParserListener ) ((EvaluatorParserListener)listener).exitUnary(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitUnary(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FillInVarNameContext extends ExpressionContext {
		public Token name;
		public TerminalNode FillInVariable() { return getToken(EvaluatorParser.FillInVariable, 0); }
		public FillInVarNameContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EvaluatorParserListener ) ((EvaluatorParserListener)listener).enterFillInVarName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EvaluatorParserListener ) ((EvaluatorParserListener)listener).exitFillInVarName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitFillInVarName(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParenthesisContext extends ExpressionContext {
		public TerminalNode LeftParenthesis() { return getToken(EvaluatorParser.LeftParenthesis, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RightParenthesis() { return getToken(EvaluatorParser.RightParenthesis, 0); }
		public ParenthesisContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EvaluatorParserListener ) ((EvaluatorParserListener)listener).enterParenthesis(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EvaluatorParserListener ) ((EvaluatorParserListener)listener).exitParenthesis(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitParenthesis(this);
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
			setState(25);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FunctionName:
				{
				_localctx = new NestedFunctionInExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(11);
				nestedFunction();
				}
				break;
			case LeftParenthesis:
				{
				_localctx = new ParenthesisContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(12);
				match(LeftParenthesis);
				setState(13);
				expression(0);
				setState(14);
				match(RightParenthesis);
				}
				break;
			case Plus:
			case Minus:
			case BooleanNot:
				{
				_localctx = new UnaryContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(16);
				unaryOperatorForExpression();
				setState(17);
				expression(8);
				}
				break;
			case SetOpen:
				{
				_localctx = new SetInExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(19);
				set();
				}
				break;
			case Integer:
				{
				_localctx = new IntegerValueContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(20);
				((IntegerValueContext)_localctx).value = match(Integer);
				}
				break;
			case Float:
				{
				_localctx = new FloatValueContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(21);
				((FloatValueContext)_localctx).value = match(Float);
				}
				break;
			case ExerciseVariable:
				{
				_localctx = new ExerciseVarNameContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(22);
				((ExerciseVarNameContext)_localctx).name = match(ExerciseVariable);
				}
				break;
			case FillInVariable:
				{
				_localctx = new FillInVarNameContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(23);
				((FillInVarNameContext)_localctx).name = match(FillInVariable);
				}
				break;
			case String:
				{
				_localctx = new TextValueContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(24);
				((TextValueContext)_localctx).value = match(String);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(33);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new BinaryContext(new ExpressionContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_expression);
					setState(27);
					if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
					setState(28);
					binaryOperatorForExpression();
					setState(29);
					expression(8);
					}
					} 
				}
				setState(35);
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

	public static class UnaryOperatorForExpressionContext extends ParserRuleContext {
		public Token operator;
		public TerminalNode Plus() { return getToken(EvaluatorParser.Plus, 0); }
		public TerminalNode Minus() { return getToken(EvaluatorParser.Minus, 0); }
		public TerminalNode BooleanNot() { return getToken(EvaluatorParser.BooleanNot, 0); }
		public UnaryOperatorForExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryOperatorForExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EvaluatorParserListener ) ((EvaluatorParserListener)listener).enterUnaryOperatorForExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EvaluatorParserListener ) ((EvaluatorParserListener)listener).exitUnaryOperatorForExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitUnaryOperatorForExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnaryOperatorForExpressionContext unaryOperatorForExpression() throws RecognitionException {
		UnaryOperatorForExpressionContext _localctx = new UnaryOperatorForExpressionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_unaryOperatorForExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(36);
			((UnaryOperatorForExpressionContext)_localctx).operator = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Plus) | (1L << Minus) | (1L << BooleanNot))) != 0)) ) {
				((UnaryOperatorForExpressionContext)_localctx).operator = (Token)_errHandler.recoverInline(this);
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

	public static class BinaryOperatorForExpressionContext extends ParserRuleContext {
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
		public BinaryOperatorForExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binaryOperatorForExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EvaluatorParserListener ) ((EvaluatorParserListener)listener).enterBinaryOperatorForExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EvaluatorParserListener ) ((EvaluatorParserListener)listener).exitBinaryOperatorForExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitBinaryOperatorForExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BinaryOperatorForExpressionContext binaryOperatorForExpression() throws RecognitionException {
		BinaryOperatorForExpressionContext _localctx = new BinaryOperatorForExpressionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_binaryOperatorForExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38);
			((BinaryOperatorForExpressionContext)_localctx).operator = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Plus) | (1L << Minus) | (1L << Multiplication) | (1L << Division) | (1L << Modulus) | (1L << LessThan) | (1L << LessThanOrEqual) | (1L << GreaterThan) | (1L << GreaterThanOrEqual) | (1L << Equal) | (1L << NotEqual) | (1L << BooleanAnd) | (1L << BooleanOr))) != 0)) ) {
				((BinaryOperatorForExpressionContext)_localctx).operator = (Token)_errHandler.recoverInline(this);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EvaluatorParserListener ) ((EvaluatorParserListener)listener).enterSet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EvaluatorParserListener ) ((EvaluatorParserListener)listener).exitSet(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitSet(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SetContext set() throws RecognitionException {
		SetContext _localctx = new SetContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_set);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(40);
			match(SetOpen);
			setState(49);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LeftParenthesis) | (1L << SetOpen) | (1L << Plus) | (1L << Minus) | (1L << BooleanNot) | (1L << ExerciseVariable) | (1L << FillInVariable) | (1L << FunctionName) | (1L << Integer) | (1L << Float) | (1L << String))) != 0)) {
				{
				setState(41);
				((SetContext)_localctx).expression = expression(0);
				((SetContext)_localctx).arguments.add(((SetContext)_localctx).expression);
				setState(46);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SetArgumentSeparator) {
					{
					{
					setState(42);
					match(SetArgumentSeparator);
					setState(43);
					((SetContext)_localctx).expression = expression(0);
					((SetContext)_localctx).arguments.add(((SetContext)_localctx).expression);
					}
					}
					setState(48);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(51);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EvaluatorParserListener ) ((EvaluatorParserListener)listener).enterNestedFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EvaluatorParserListener ) ((EvaluatorParserListener)listener).exitNestedFunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitNestedFunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NestedFunctionContext nestedFunction() throws RecognitionException {
		NestedFunctionContext _localctx = new NestedFunctionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_nestedFunction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53);
			((NestedFunctionContext)_localctx).name = match(FunctionName);
			setState(54);
			match(LeftParenthesis);
			setState(63);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LeftParenthesis) | (1L << SetOpen) | (1L << Plus) | (1L << Minus) | (1L << BooleanNot) | (1L << ExerciseVariable) | (1L << FillInVariable) | (1L << FunctionName) | (1L << Integer) | (1L << Float) | (1L << String))) != 0)) {
				{
				setState(55);
				((NestedFunctionContext)_localctx).expression = expression(0);
				((NestedFunctionContext)_localctx).arguments.add(((NestedFunctionContext)_localctx).expression);
				setState(60);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==ArgumentSeparator) {
					{
					{
					setState(56);
					match(ArgumentSeparator);
					setState(57);
					((NestedFunctionContext)_localctx).expression = expression(0);
					((NestedFunctionContext)_localctx).arguments.add(((NestedFunctionContext)_localctx).expression);
					}
					}
					setState(62);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(65);
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
			return precpred(_ctx, 7);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\35F\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\5\2\34\n\2\3\2\3\2\3\2\3\2\7\2\"\n\2\f\2\16\2%\13\2\3"+
		"\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5\7\5/\n\5\f\5\16\5\62\13\5\5\5\64\n\5\3"+
		"\5\3\5\3\6\3\6\3\6\3\6\3\6\7\6=\n\6\f\6\16\6@\13\6\5\6B\n\6\3\6\3\6\3"+
		"\6\2\3\2\7\2\4\6\b\n\2\4\3\2\7\t\4\2\7\b\n\24\2M\2\33\3\2\2\2\4&\3\2\2"+
		"\2\6(\3\2\2\2\b*\3\2\2\2\n\67\3\2\2\2\f\r\b\2\1\2\r\34\5\n\6\2\16\17\7"+
		"\3\2\2\17\20\5\2\2\2\20\21\7\4\2\2\21\34\3\2\2\2\22\23\5\4\3\2\23\24\5"+
		"\2\2\n\24\34\3\2\2\2\25\34\5\b\5\2\26\34\7\32\2\2\27\34\7\33\2\2\30\34"+
		"\7\25\2\2\31\34\7\26\2\2\32\34\7\34\2\2\33\f\3\2\2\2\33\16\3\2\2\2\33"+
		"\22\3\2\2\2\33\25\3\2\2\2\33\26\3\2\2\2\33\27\3\2\2\2\33\30\3\2\2\2\33"+
		"\31\3\2\2\2\33\32\3\2\2\2\34#\3\2\2\2\35\36\f\t\2\2\36\37\5\6\4\2\37 "+
		"\5\2\2\n \"\3\2\2\2!\35\3\2\2\2\"%\3\2\2\2#!\3\2\2\2#$\3\2\2\2$\3\3\2"+
		"\2\2%#\3\2\2\2&\'\t\2\2\2\'\5\3\2\2\2()\t\3\2\2)\7\3\2\2\2*\63\7\5\2\2"+
		"+\60\5\2\2\2,-\7\31\2\2-/\5\2\2\2.,\3\2\2\2/\62\3\2\2\2\60.\3\2\2\2\60"+
		"\61\3\2\2\2\61\64\3\2\2\2\62\60\3\2\2\2\63+\3\2\2\2\63\64\3\2\2\2\64\65"+
		"\3\2\2\2\65\66\7\6\2\2\66\t\3\2\2\2\678\7\27\2\28A\7\3\2\29>\5\2\2\2:"+
		";\7\30\2\2;=\5\2\2\2<:\3\2\2\2=@\3\2\2\2><\3\2\2\2>?\3\2\2\2?B\3\2\2\2"+
		"@>\3\2\2\2A9\3\2\2\2AB\3\2\2\2BC\3\2\2\2CD\7\4\2\2D\13\3\2\2\2\b\33#\60"+
		"\63>A";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}