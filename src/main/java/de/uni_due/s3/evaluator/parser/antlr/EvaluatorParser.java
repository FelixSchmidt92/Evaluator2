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
		FunctionName=20, FunctionOpen=21, FunctionClose=22, ArgumentSeparator=23, 
		Quote=24, Integer=25, Float=26, Text=27, WS=28;
	public static final int
		RULE_expression = 0, RULE_integerNumber = 1, RULE_floatNumber = 2, RULE_exerciseVar = 3, 
		RULE_fillInVar = 4, RULE_unaryoperator = 5, RULE_binaryoperator = 6, RULE_nestedFunction = 7;
	public static final String[] ruleNames = {
		"expression", "integerNumber", "floatNumber", "exerciseVar", "fillInVar", 
		"unaryoperator", "binaryoperator", "nestedFunction"
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
		public UnaryoperatorContext unaryoperator() {
			return getRuleContext(UnaryoperatorContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public UnaryOperatorContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitUnaryOperator(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntegerNumContext extends ExpressionContext {
		public IntegerNumberContext integerNumber() {
			return getRuleContext(IntegerNumberContext.class,0);
		}
		public IntegerNumContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitIntegerNum(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FloatNumContext extends ExpressionContext {
		public FloatNumberContext floatNumber() {
			return getRuleContext(FloatNumberContext.class,0);
		}
		public FloatNumContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitFloatNum(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FillInVariableContext extends ExpressionContext {
		public FillInVarContext fillInVar() {
			return getRuleContext(FillInVarContext.class,0);
		}
		public FillInVariableContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitFillInVariable(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BinaryOperatorContext extends ExpressionContext {
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
		public NestedFunctionContext name;
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
	public static class ExerciseVariableContext extends ExpressionContext {
		public ExerciseVarContext exerciseVar() {
			return getRuleContext(ExerciseVarContext.class,0);
		}
		public ExerciseVariableContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitExerciseVariable(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TextContext extends ExpressionContext {
		public TerminalNode Text() { return getToken(EvaluatorParser.Text, 0); }
		public TextContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitText(this);
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
			setState(30);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				{
				_localctx = new FunctionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(17);
				((FunctionContext)_localctx).name = nestedFunction();
				}
				break;
			case 2:
				{
				_localctx = new ParenthesesContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(18);
				match(LeftParenthesis);
				setState(19);
				expression(0);
				setState(20);
				match(RightParenthesis);
				}
				break;
			case 3:
				{
				_localctx = new UnaryOperatorContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(22);
				unaryoperator();
				setState(23);
				expression(7);
				}
				break;
			case 4:
				{
				_localctx = new IntegerNumContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(25);
				integerNumber();
				}
				break;
			case 5:
				{
				_localctx = new FloatNumContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(26);
				floatNumber();
				}
				break;
			case 6:
				{
				_localctx = new ExerciseVariableContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(27);
				exerciseVar();
				}
				break;
			case 7:
				{
				_localctx = new FillInVariableContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(28);
				fillInVar();
				}
				break;
			case 8:
				{
				_localctx = new TextContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(29);
				match(Text);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(38);
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
					setState(32);
					if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
					setState(33);
					binaryoperator();
					setState(34);
					expression(7);
					}
					} 
				}
				setState(40);
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

	public static class IntegerNumberContext extends ParserRuleContext {
		public TerminalNode Integer() { return getToken(EvaluatorParser.Integer, 0); }
		public List<TerminalNode> Quote() { return getTokens(EvaluatorParser.Quote); }
		public TerminalNode Quote(int i) {
			return getToken(EvaluatorParser.Quote, i);
		}
		public IntegerNumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integerNumber; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitIntegerNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntegerNumberContext integerNumber() throws RecognitionException {
		IntegerNumberContext _localctx = new IntegerNumberContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_integerNumber);
		try {
			setState(45);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Integer:
				enterOuterAlt(_localctx, 1);
				{
				setState(41);
				match(Integer);
				}
				break;
			case Quote:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(42);
				match(Quote);
				setState(43);
				match(Integer);
				setState(44);
				match(Quote);
				}
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

	public static class FloatNumberContext extends ParserRuleContext {
		public TerminalNode Float() { return getToken(EvaluatorParser.Float, 0); }
		public List<TerminalNode> Quote() { return getTokens(EvaluatorParser.Quote); }
		public TerminalNode Quote(int i) {
			return getToken(EvaluatorParser.Quote, i);
		}
		public FloatNumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_floatNumber; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitFloatNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FloatNumberContext floatNumber() throws RecognitionException {
		FloatNumberContext _localctx = new FloatNumberContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_floatNumber);
		try {
			setState(51);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Float:
				enterOuterAlt(_localctx, 1);
				{
				setState(47);
				match(Float);
				}
				break;
			case Quote:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(48);
				match(Quote);
				setState(49);
				match(Float);
				setState(50);
				match(Quote);
				}
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

	public static class ExerciseVarContext extends ParserRuleContext {
		public TerminalNode ExerciseVariable() { return getToken(EvaluatorParser.ExerciseVariable, 0); }
		public List<TerminalNode> Quote() { return getTokens(EvaluatorParser.Quote); }
		public TerminalNode Quote(int i) {
			return getToken(EvaluatorParser.Quote, i);
		}
		public ExerciseVarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exerciseVar; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitExerciseVar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExerciseVarContext exerciseVar() throws RecognitionException {
		ExerciseVarContext _localctx = new ExerciseVarContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_exerciseVar);
		try {
			setState(57);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ExerciseVariable:
				enterOuterAlt(_localctx, 1);
				{
				setState(53);
				match(ExerciseVariable);
				}
				break;
			case Quote:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(54);
				match(Quote);
				setState(55);
				match(ExerciseVariable);
				setState(56);
				match(Quote);
				}
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

	public static class FillInVarContext extends ParserRuleContext {
		public TerminalNode FillInVariable() { return getToken(EvaluatorParser.FillInVariable, 0); }
		public List<TerminalNode> Quote() { return getTokens(EvaluatorParser.Quote); }
		public TerminalNode Quote(int i) {
			return getToken(EvaluatorParser.Quote, i);
		}
		public FillInVarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fillInVar; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitFillInVar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FillInVarContext fillInVar() throws RecognitionException {
		FillInVarContext _localctx = new FillInVarContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_fillInVar);
		try {
			setState(63);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FillInVariable:
				enterOuterAlt(_localctx, 1);
				{
				setState(59);
				match(FillInVariable);
				}
				break;
			case Quote:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(60);
				match(Quote);
				setState(61);
				match(FillInVariable);
				setState(62);
				match(Quote);
				}
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
		enterRule(_localctx, 10, RULE_unaryoperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65);
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
		enterRule(_localctx, 12, RULE_binaryoperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67);
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
		public ExpressionContext expression;
		public List<ExpressionContext> arguments = new ArrayList<ExpressionContext>();
		public TerminalNode FunctionName() { return getToken(EvaluatorParser.FunctionName, 0); }
		public TerminalNode FunctionOpen() { return getToken(EvaluatorParser.FunctionOpen, 0); }
		public TerminalNode FunctionClose() { return getToken(EvaluatorParser.FunctionClose, 0); }
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
		enterRule(_localctx, 14, RULE_nestedFunction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			match(FunctionName);
			setState(70);
			match(FunctionOpen);
			setState(79);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LeftParenthesis) | (1L << Plus) | (1L << Minus) | (1L << BooleanNot) | (1L << ExerciseVariable) | (1L << FillInVariable) | (1L << FunctionName) | (1L << Quote) | (1L << Integer) | (1L << Float) | (1L << Text))) != 0)) {
				{
				setState(71);
				((NestedFunctionContext)_localctx).expression = expression(0);
				((NestedFunctionContext)_localctx).arguments.add(((NestedFunctionContext)_localctx).expression);
				setState(76);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==ArgumentSeparator) {
					{
					{
					setState(72);
					match(ArgumentSeparator);
					setState(73);
					((NestedFunctionContext)_localctx).expression = expression(0);
					((NestedFunctionContext)_localctx).arguments.add(((NestedFunctionContext)_localctx).expression);
					}
					}
					setState(78);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(81);
			match(FunctionClose);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\36V\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2!\n\2\3\2\3\2\3\2\3\2\7\2\'\n"+
		"\2\f\2\16\2*\13\2\3\3\3\3\3\3\3\3\5\3\60\n\3\3\4\3\4\3\4\3\4\5\4\66\n"+
		"\4\3\5\3\5\3\5\3\5\5\5<\n\5\3\6\3\6\3\6\3\6\5\6B\n\6\3\7\3\7\3\b\3\b\3"+
		"\t\3\t\3\t\3\t\3\t\7\tM\n\t\f\t\16\tP\13\t\5\tR\n\t\3\t\3\t\3\t\2\3\2"+
		"\n\2\4\6\b\n\f\16\20\2\4\3\2\5\7\4\2\5\6\b\22\2[\2 \3\2\2\2\4/\3\2\2\2"+
		"\6\65\3\2\2\2\b;\3\2\2\2\nA\3\2\2\2\fC\3\2\2\2\16E\3\2\2\2\20G\3\2\2\2"+
		"\22\23\b\2\1\2\23!\5\20\t\2\24\25\7\3\2\2\25\26\5\2\2\2\26\27\7\4\2\2"+
		"\27!\3\2\2\2\30\31\5\f\7\2\31\32\5\2\2\t\32!\3\2\2\2\33!\5\4\3\2\34!\5"+
		"\6\4\2\35!\5\b\5\2\36!\5\n\6\2\37!\7\35\2\2 \22\3\2\2\2 \24\3\2\2\2 \30"+
		"\3\2\2\2 \33\3\2\2\2 \34\3\2\2\2 \35\3\2\2\2 \36\3\2\2\2 \37\3\2\2\2!"+
		"(\3\2\2\2\"#\f\b\2\2#$\5\16\b\2$%\5\2\2\t%\'\3\2\2\2&\"\3\2\2\2\'*\3\2"+
		"\2\2(&\3\2\2\2()\3\2\2\2)\3\3\2\2\2*(\3\2\2\2+\60\7\33\2\2,-\7\32\2\2"+
		"-.\7\33\2\2.\60\7\32\2\2/+\3\2\2\2/,\3\2\2\2\60\5\3\2\2\2\61\66\7\34\2"+
		"\2\62\63\7\32\2\2\63\64\7\34\2\2\64\66\7\32\2\2\65\61\3\2\2\2\65\62\3"+
		"\2\2\2\66\7\3\2\2\2\67<\7\23\2\289\7\32\2\29:\7\23\2\2:<\7\32\2\2;\67"+
		"\3\2\2\2;8\3\2\2\2<\t\3\2\2\2=B\7\24\2\2>?\7\32\2\2?@\7\24\2\2@B\7\32"+
		"\2\2A=\3\2\2\2A>\3\2\2\2B\13\3\2\2\2CD\t\2\2\2D\r\3\2\2\2EF\t\3\2\2F\17"+
		"\3\2\2\2GH\7\26\2\2HQ\7\27\2\2IN\5\2\2\2JK\7\31\2\2KM\5\2\2\2LJ\3\2\2"+
		"\2MP\3\2\2\2NL\3\2\2\2NO\3\2\2\2OR\3\2\2\2PN\3\2\2\2QI\3\2\2\2QR\3\2\2"+
		"\2RS\3\2\2\2ST\7\30\2\2T\21\3\2\2\2\n (/\65;ANQ";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}