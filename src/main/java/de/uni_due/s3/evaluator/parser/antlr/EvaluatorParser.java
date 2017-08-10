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
		LeftParenthesis=1, RightParenthesis=2, SetOpen=3, SetClose=4, Circumflex=5, 
		Plus=6, Minus=7, BooleanNot=8, Multiplication=9, Division=10, Modulus=11, 
		LessThan=12, LessThanOrEqual=13, GreaterThan=14, GreaterThanOrEqual=15, 
		Equal=16, NotEqual=17, BooleanAnd=18, BooleanOr=19, ExerciseVariable=20, 
		FillInVariable=21, Variable=22, FunctionName=23, ArgumentSeparator=24, 
		SetArgumentSeparator=25, Integer=26, Float=27, String=28, WS=29;
	public static final int
		RULE_expression = 0, RULE_unaryOperatorForExpression = 1, RULE_binaryOperatorBoolean = 2, 
		RULE_binaryOperatorRelational = 3, RULE_binaryOperatorArithLine = 4, RULE_binaryOperatorArithPoint = 5, 
		RULE_set = 6;
	public static final String[] ruleNames = {
		"expression", "unaryOperatorForExpression", "binaryOperatorBoolean", "binaryOperatorRelational", 
		"binaryOperatorArithLine", "binaryOperatorArithPoint", "set"
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
	public static class BinaryCircumflexContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode Circumflex() { return getToken(EvaluatorParser.Circumflex, 0); }
		public BinaryCircumflexContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitBinaryCircumflex(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BinaryRelationalContext extends ExpressionContext {
		public BinaryOperatorRelationalContext operator;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public BinaryOperatorRelationalContext binaryOperatorRelational() {
			return getRuleContext(BinaryOperatorRelationalContext.class,0);
		}
		public BinaryRelationalContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitBinaryRelational(this);
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
	public static class NestedFunctionInExpressionContext extends ExpressionContext {
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
		public NestedFunctionInExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitNestedFunctionInExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UnaryContext extends ExpressionContext {
		public UnaryOperatorForExpressionContext operator;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public UnaryOperatorForExpressionContext unaryOperatorForExpression() {
			return getRuleContext(UnaryOperatorForExpressionContext.class,0);
		}
		public UnaryContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitUnary(this);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitParenthesis(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SetInExpressionContext extends ExpressionContext {
		public SetContext set() {
			return getRuleContext(SetContext.class,0);
		}
		public SetInExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitSetInExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class VariableContext extends ExpressionContext {
		public Token name;
		public TerminalNode Variable() { return getToken(EvaluatorParser.Variable, 0); }
		public VariableContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitVariable(this);
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
	public static class BinaryArithPointContext extends ExpressionContext {
		public BinaryOperatorArithPointContext operator;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public BinaryOperatorArithPointContext binaryOperatorArithPoint() {
			return getRuleContext(BinaryOperatorArithPointContext.class,0);
		}
		public BinaryArithPointContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitBinaryArithPoint(this);
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
	public static class BinaryArithLineContext extends ExpressionContext {
		public BinaryOperatorArithLineContext operator;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public BinaryOperatorArithLineContext binaryOperatorArithLine() {
			return getRuleContext(BinaryOperatorArithLineContext.class,0);
		}
		public BinaryArithLineContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitBinaryArithLine(this);
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
	public static class BinaryBooleanContext extends ExpressionContext {
		public BinaryOperatorBooleanContext operator;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public BinaryOperatorBooleanContext binaryOperatorBoolean() {
			return getRuleContext(BinaryOperatorBooleanContext.class,0);
		}
		public BinaryBooleanContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitBinaryBoolean(this);
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
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(42);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Variable:
				{
				_localctx = new VariableContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(15);
				((VariableContext)_localctx).name = match(Variable);
				}
				break;
			case FunctionName:
				{
				_localctx = new NestedFunctionInExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				{
				setState(16);
				((NestedFunctionInExpressionContext)_localctx).name = match(FunctionName);
				setState(17);
				match(LeftParenthesis);
				setState(26);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LeftParenthesis) | (1L << SetOpen) | (1L << Plus) | (1L << Minus) | (1L << BooleanNot) | (1L << ExerciseVariable) | (1L << FillInVariable) | (1L << Variable) | (1L << FunctionName) | (1L << Integer) | (1L << Float) | (1L << String))) != 0)) {
					{
					setState(18);
					((NestedFunctionInExpressionContext)_localctx).expression = expression(0);
					((NestedFunctionInExpressionContext)_localctx).arguments.add(((NestedFunctionInExpressionContext)_localctx).expression);
					setState(23);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==ArgumentSeparator) {
						{
						{
						setState(19);
						match(ArgumentSeparator);
						setState(20);
						((NestedFunctionInExpressionContext)_localctx).expression = expression(0);
						((NestedFunctionInExpressionContext)_localctx).arguments.add(((NestedFunctionInExpressionContext)_localctx).expression);
						}
						}
						setState(25);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(28);
				match(RightParenthesis);
				}
				}
				break;
			case LeftParenthesis:
				{
				_localctx = new ParenthesisContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(29);
				match(LeftParenthesis);
				setState(30);
				expression(0);
				setState(31);
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
				setState(33);
				((UnaryContext)_localctx).operator = unaryOperatorForExpression();
				setState(34);
				expression(12);
				}
				break;
			case SetOpen:
				{
				_localctx = new SetInExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(36);
				set();
				}
				break;
			case Integer:
				{
				_localctx = new IntegerValueContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(37);
				((IntegerValueContext)_localctx).value = match(Integer);
				}
				break;
			case Float:
				{
				_localctx = new FloatValueContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(38);
				((FloatValueContext)_localctx).value = match(Float);
				}
				break;
			case ExerciseVariable:
				{
				_localctx = new ExerciseVarNameContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(39);
				((ExerciseVarNameContext)_localctx).name = match(ExerciseVariable);
				}
				break;
			case FillInVariable:
				{
				_localctx = new FillInVarNameContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(40);
				((FillInVarNameContext)_localctx).name = match(FillInVariable);
				}
				break;
			case String:
				{
				_localctx = new TextValueContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(41);
				((TextValueContext)_localctx).value = match(String);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(65);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(63);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
					case 1:
						{
						_localctx = new BinaryCircumflexContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(44);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(45);
						match(Circumflex);
						setState(46);
						expression(12);
						}
						break;
					case 2:
						{
						_localctx = new BinaryArithPointContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(47);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(48);
						((BinaryArithPointContext)_localctx).operator = binaryOperatorArithPoint();
						setState(49);
						expression(11);
						}
						break;
					case 3:
						{
						_localctx = new BinaryArithLineContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(51);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(52);
						((BinaryArithLineContext)_localctx).operator = binaryOperatorArithLine();
						setState(53);
						expression(10);
						}
						break;
					case 4:
						{
						_localctx = new BinaryRelationalContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(55);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(56);
						((BinaryRelationalContext)_localctx).operator = binaryOperatorRelational();
						setState(57);
						expression(9);
						}
						break;
					case 5:
						{
						_localctx = new BinaryBooleanContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(59);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(60);
						((BinaryBooleanContext)_localctx).operator = binaryOperatorBoolean();
						setState(61);
						expression(8);
						}
						break;
					}
					} 
				}
				setState(67);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
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
			setState(68);
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

	public static class BinaryOperatorBooleanContext extends ParserRuleContext {
		public Token operator;
		public TerminalNode BooleanAnd() { return getToken(EvaluatorParser.BooleanAnd, 0); }
		public TerminalNode BooleanOr() { return getToken(EvaluatorParser.BooleanOr, 0); }
		public BinaryOperatorBooleanContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binaryOperatorBoolean; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitBinaryOperatorBoolean(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BinaryOperatorBooleanContext binaryOperatorBoolean() throws RecognitionException {
		BinaryOperatorBooleanContext _localctx = new BinaryOperatorBooleanContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_binaryOperatorBoolean);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			((BinaryOperatorBooleanContext)_localctx).operator = _input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==BooleanAnd || _la==BooleanOr) ) {
				((BinaryOperatorBooleanContext)_localctx).operator = (Token)_errHandler.recoverInline(this);
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

	public static class BinaryOperatorRelationalContext extends ParserRuleContext {
		public Token operator;
		public TerminalNode Equal() { return getToken(EvaluatorParser.Equal, 0); }
		public TerminalNode NotEqual() { return getToken(EvaluatorParser.NotEqual, 0); }
		public TerminalNode LessThan() { return getToken(EvaluatorParser.LessThan, 0); }
		public TerminalNode LessThanOrEqual() { return getToken(EvaluatorParser.LessThanOrEqual, 0); }
		public TerminalNode GreaterThan() { return getToken(EvaluatorParser.GreaterThan, 0); }
		public TerminalNode GreaterThanOrEqual() { return getToken(EvaluatorParser.GreaterThanOrEqual, 0); }
		public BinaryOperatorRelationalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binaryOperatorRelational; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitBinaryOperatorRelational(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BinaryOperatorRelationalContext binaryOperatorRelational() throws RecognitionException {
		BinaryOperatorRelationalContext _localctx = new BinaryOperatorRelationalContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_binaryOperatorRelational);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			((BinaryOperatorRelationalContext)_localctx).operator = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LessThan) | (1L << LessThanOrEqual) | (1L << GreaterThan) | (1L << GreaterThanOrEqual) | (1L << Equal) | (1L << NotEqual))) != 0)) ) {
				((BinaryOperatorRelationalContext)_localctx).operator = (Token)_errHandler.recoverInline(this);
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

	public static class BinaryOperatorArithLineContext extends ParserRuleContext {
		public Token operator;
		public TerminalNode Plus() { return getToken(EvaluatorParser.Plus, 0); }
		public TerminalNode Minus() { return getToken(EvaluatorParser.Minus, 0); }
		public BinaryOperatorArithLineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binaryOperatorArithLine; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitBinaryOperatorArithLine(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BinaryOperatorArithLineContext binaryOperatorArithLine() throws RecognitionException {
		BinaryOperatorArithLineContext _localctx = new BinaryOperatorArithLineContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_binaryOperatorArithLine);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			((BinaryOperatorArithLineContext)_localctx).operator = _input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==Plus || _la==Minus) ) {
				((BinaryOperatorArithLineContext)_localctx).operator = (Token)_errHandler.recoverInline(this);
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

	public static class BinaryOperatorArithPointContext extends ParserRuleContext {
		public Token operator;
		public TerminalNode Multiplication() { return getToken(EvaluatorParser.Multiplication, 0); }
		public TerminalNode Division() { return getToken(EvaluatorParser.Division, 0); }
		public TerminalNode Modulus() { return getToken(EvaluatorParser.Modulus, 0); }
		public BinaryOperatorArithPointContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binaryOperatorArithPoint; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitBinaryOperatorArithPoint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BinaryOperatorArithPointContext binaryOperatorArithPoint() throws RecognitionException {
		BinaryOperatorArithPointContext _localctx = new BinaryOperatorArithPointContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_binaryOperatorArithPoint);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
			((BinaryOperatorArithPointContext)_localctx).operator = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Multiplication) | (1L << Division) | (1L << Modulus))) != 0)) ) {
				((BinaryOperatorArithPointContext)_localctx).operator = (Token)_errHandler.recoverInline(this);
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
		enterRule(_localctx, 12, RULE_set);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(78);
			match(SetOpen);
			setState(87);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LeftParenthesis) | (1L << SetOpen) | (1L << Plus) | (1L << Minus) | (1L << BooleanNot) | (1L << ExerciseVariable) | (1L << FillInVariable) | (1L << Variable) | (1L << FunctionName) | (1L << Integer) | (1L << Float) | (1L << String))) != 0)) {
				{
				setState(79);
				((SetContext)_localctx).expression = expression(0);
				((SetContext)_localctx).arguments.add(((SetContext)_localctx).expression);
				setState(84);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SetArgumentSeparator) {
					{
					{
					setState(80);
					match(SetArgumentSeparator);
					setState(81);
					((SetContext)_localctx).expression = expression(0);
					((SetContext)_localctx).arguments.add(((SetContext)_localctx).expression);
					}
					}
					setState(86);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(89);
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
			return precpred(_ctx, 11);
		case 1:
			return precpred(_ctx, 10);
		case 2:
			return precpred(_ctx, 9);
		case 3:
			return precpred(_ctx, 8);
		case 4:
			return precpred(_ctx, 7);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\37^\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\7\2\30\n\2\f\2\16\2\33\13\2\5\2\35\n\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2-\n\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\7\2B\n\2\f\2\16\2E\13\2\3\3"+
		"\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\b\7\bU\n\b\f\b\16\b"+
		"X\13\b\5\bZ\n\b\3\b\3\b\3\b\2\3\2\t\2\4\6\b\n\f\16\2\7\3\2\b\n\3\2\24"+
		"\25\3\2\16\23\3\2\b\t\3\2\13\r\2h\2,\3\2\2\2\4F\3\2\2\2\6H\3\2\2\2\bJ"+
		"\3\2\2\2\nL\3\2\2\2\fN\3\2\2\2\16P\3\2\2\2\20\21\b\2\1\2\21-\7\30\2\2"+
		"\22\23\7\31\2\2\23\34\7\3\2\2\24\31\5\2\2\2\25\26\7\32\2\2\26\30\5\2\2"+
		"\2\27\25\3\2\2\2\30\33\3\2\2\2\31\27\3\2\2\2\31\32\3\2\2\2\32\35\3\2\2"+
		"\2\33\31\3\2\2\2\34\24\3\2\2\2\34\35\3\2\2\2\35\36\3\2\2\2\36-\7\4\2\2"+
		"\37 \7\3\2\2 !\5\2\2\2!\"\7\4\2\2\"-\3\2\2\2#$\5\4\3\2$%\5\2\2\16%-\3"+
		"\2\2\2&-\5\16\b\2\'-\7\34\2\2(-\7\35\2\2)-\7\26\2\2*-\7\27\2\2+-\7\36"+
		"\2\2,\20\3\2\2\2,\22\3\2\2\2,\37\3\2\2\2,#\3\2\2\2,&\3\2\2\2,\'\3\2\2"+
		"\2,(\3\2\2\2,)\3\2\2\2,*\3\2\2\2,+\3\2\2\2-C\3\2\2\2./\f\r\2\2/\60\7\7"+
		"\2\2\60B\5\2\2\16\61\62\f\f\2\2\62\63\5\f\7\2\63\64\5\2\2\r\64B\3\2\2"+
		"\2\65\66\f\13\2\2\66\67\5\n\6\2\678\5\2\2\f8B\3\2\2\29:\f\n\2\2:;\5\b"+
		"\5\2;<\5\2\2\13<B\3\2\2\2=>\f\t\2\2>?\5\6\4\2?@\5\2\2\n@B\3\2\2\2A.\3"+
		"\2\2\2A\61\3\2\2\2A\65\3\2\2\2A9\3\2\2\2A=\3\2\2\2BE\3\2\2\2CA\3\2\2\2"+
		"CD\3\2\2\2D\3\3\2\2\2EC\3\2\2\2FG\t\2\2\2G\5\3\2\2\2HI\t\3\2\2I\7\3\2"+
		"\2\2JK\t\4\2\2K\t\3\2\2\2LM\t\5\2\2M\13\3\2\2\2NO\t\6\2\2O\r\3\2\2\2P"+
		"Y\7\5\2\2QV\5\2\2\2RS\7\33\2\2SU\5\2\2\2TR\3\2\2\2UX\3\2\2\2VT\3\2\2\2"+
		"VW\3\2\2\2WZ\3\2\2\2XV\3\2\2\2YQ\3\2\2\2YZ\3\2\2\2Z[\3\2\2\2[\\\7\6\2"+
		"\2\\\17\3\2\2\2\t\31\34,ACVY";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}