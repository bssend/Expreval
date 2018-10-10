package bssend.expreval.compiler;

import bssend.expreval.ast.Ast;
import bssend.expreval.exception.ParseException;
import bssend.expreval.ast.node.IExpressionNode;
import bssend.expreval.ast.node.binary.*;
import bssend.expreval.ast.node.literal.*;
import bssend.expreval.ast.node.functioncall.FunctionCallExpressionNode;
import bssend.expreval.compiler.util.ITokenSequence;
import bssend.expreval.compiler.util.StringSequence;
import bssend.expreval.ast.node.unary.MinusExpressionNode;
import bssend.expreval.ast.node.unary.NotExpressionNode;
import bssend.expreval.ast.node.unary.PlusExpressionNode;
import bssend.expreval.ast.node.variable.VariableExpressionNode;
import lombok.NonNull;
import lombok.var;

import java.util.ArrayList;
import java.util.Arrays;

import static bssend.expreval.compiler.TokenType.*;

public class Parser implements IParser {

    private final IScanner scanner = new Scanner();

    private String source;

    @Override
    public Ast parse(@NonNull final String s) {
        this.source = s;
        return new Ast(andNode(scanner.scan(new StringSequence(s))));
    }

    /**
     * literal =
     *     string |
     *     integer |
     *     decimal |
     *     boolean
     * @param tokens
     * @return
     */
    private IExpressionNode literalNode(@NonNull final ITokenSequence tokens) {

        var literalToken = tokens.next();
        switch (literalToken.getType()) {
            case STRING:
                return new StringNode(literalToken);

            case INTEGER:
                return new IntegerNode(literalToken);

            case NUMBER:
                return new NumberNode(literalToken);

            case BOOLEAN:
                return new BooleanNode(literalToken);

        }

        throw new ParseException(literalToken, "Undefined literal token.");
    }

    /**
     * variable
     *      = IDENTIFIER
     * @param tokens
     * @return
     */
    private IExpressionNode variableNode(@NonNull final ITokenSequence tokens) {

        if (tokens.isEnd() || tokens.peek().getType() != IDENTIFIER)
            throw new IllegalArgumentException("first token must be IDENTIFIER.");

        var variableToken = tokens.next();

        return new VariableExpressionNode(variableToken);
    }

    /**
     * unary
     * @param tokens
     * @return
     */
    private IExpressionNode unaryNode(@NonNull final ITokenSequence tokens) {

        var operatorToken = tokens.next();
        var node = exprNode(tokens);

        switch (operatorToken.getType()) {
            case BANG:
                return new NotExpressionNode(node, operatorToken);

            case PLUS:
                return new PlusExpressionNode(node, operatorToken);

            case MINUS:
                return new MinusExpressionNode(node, operatorToken);
        }

        throw new ParseException(operatorToken, "Undefined unary expression token.");
    }

    /**
     * logical =
     *     compare
     *     compare "&&" compare
     *     compare "||" compare
     *
     * @param tokens
     * @return
     */
    private IExpressionNode andNode(@NonNull final ITokenSequence tokens) {
        var returnNode = orNode(tokens);

        while (!tokens.isEnd()) {

            if (AMPERSAND_AMPERSAND != tokens.peek().getType())
                break;

            var operatorToken = tokens.next();
            var rightNode = orNode(tokens);

            returnNode = new AndAlsoExpressionNode(returnNode, rightNode, operatorToken);
        }

        return returnNode;
    }
    /**
     * logical =
     *     compare
     *     compare "&&" compare
     *     compare "||" compare
     *
     * @param tokens
     * @return
     */
    private IExpressionNode orNode(@NonNull final ITokenSequence tokens) {
        var returnNode = compareNode(tokens);

        while (!tokens.isEnd()) {

            if (PIPE_PIPE != tokens.peek().getType())
                break;

            var operatorToken = tokens.next();
            var rightNode = compareNode(tokens);

            returnNode = new OrElseExpressionNode(returnNode, rightNode, operatorToken);
        }

        return returnNode;
    }

    /**
     * compare =
     *     expr
     *     expr "==" expr
     *     expr "!=" expr
     *     expr ">" expr
     *     expr "<" expr
     *     expr ">=" expr
     *     expr "<=" expr
     * @param tokens
     * @return
     */
    private IExpressionNode compareNode(@NonNull final ITokenSequence tokens) {
        var returnNode = exprNode(tokens);

        while (!tokens.isEnd()) {

            if (!Arrays.asList(EQUAL_EQUAL, BANG_EQUAL, OPEN_ANGLE, OPEN_ANGLE_EQUAL, CLOSE_ANGLE, CLOSE_ANGLE_EQUAL)
                    .contains(tokens.peek().getType()))
                break;

            var opToken = tokens.next();
            var rightNode = exprNode(tokens);

            switch (opToken.getType()) {
                case EQUAL_EQUAL:
                    returnNode = new EqualExpressionNode(returnNode, rightNode, opToken);
                    break;
                case BANG_EQUAL:
                    returnNode = new NotEqualExpressionNode(returnNode, rightNode, opToken);
                    break;
                case OPEN_ANGLE:
                    returnNode = new LessThanExpressionNode(returnNode, rightNode, opToken);
                    break;
                case OPEN_ANGLE_EQUAL:
                    returnNode = new LessThanOrEqualExpressionNode(returnNode, rightNode, opToken);
                    break;
                case CLOSE_ANGLE:
                    returnNode = new GreaterThanExpressionNode(returnNode, rightNode, opToken);
                    break;
                case CLOSE_ANGLE_EQUAL:
                    returnNode = new GreaterThanOrEqualExpressionNode(returnNode, rightNode, opToken);
                    break;
            }
        }

        return returnNode;
    }


    /**
     * expr =
     *     term
     *     term + term
     *     term - term
     * @param tokens
     * @return
     */
    private IExpressionNode exprNode(@NonNull final ITokenSequence tokens) {
        var returnNode = termNode(tokens);

        while (!tokens.isEnd()) {

            if (!Arrays.asList(PLUS, MINUS)
                    .contains(tokens.peek().getType()))
                break;

            var operatorToken = tokens.next();
            var rightNode = termNode(tokens);

            switch (operatorToken.getType()) {
                case PLUS:
                    returnNode = new AddExpressionNode(returnNode, rightNode, operatorToken);
                    break;
                case MINUS:
                    returnNode = new SubtractExpressionNode(returnNode, rightNode, operatorToken);
                    break;
            }
        }

        return returnNode;
    }

    /**
     * term =
     *     factor
     *     factor * factor
     *     factor / factor
     *     factor % factor
     *
     * @param tokens
     * @return
     */
    private IExpressionNode termNode(@NonNull final ITokenSequence tokens) {
        var returnNode = factorNode(tokens);

        while (!tokens.isEnd()) {

            if (!Arrays.asList(STAR, SLASH, PERCENT)
                    .contains(tokens.peek().getType()))
                break;

            var operatorToken = tokens.next();
            var rightNode = factorNode(tokens);

            switch (operatorToken.getType()) {
                case STAR:
                    returnNode = new MultiplyExpressionNode(returnNode, rightNode, operatorToken);
                    break;
                case SLASH:
                    returnNode = new DivideExpressionNode(returnNode, rightNode, operatorToken);
                    break;
                case PERCENT:
                    returnNode = new ModuloExpressionNode(returnNode, rightNode, operatorToken);
                    break;
            }
        }

        return returnNode;
    }


    /**
     * factor =
     *     literal |
     *     variable |
     *     function_call |
     *     [OPEN_PAREN] expr [CLOSE_PAREN]
     * @param tokens
     * @return
     */
    private IExpressionNode factorNode(@NonNull final ITokenSequence tokens) {

        if (tokens.isEnd())
            throw new IllegalArgumentException("Tokens must not be terminated.");

        switch (tokens.peek().getType()) {
            case BANG:
            case PLUS:
            case MINUS:
                return unaryNode(tokens);

            case IDENTIFIER:
                if (!tokens.isNextEnd() && tokens.peekNext().getType() == OPEN_PAREN)
                    return functionCallNode(tokens);

                return variableNode(tokens);

            case STRING:
            case INTEGER:
            case NUMBER:
            case BOOLEAN:
                return literalNode(tokens);

            case OPEN_PAREN:
                var openParenToken = tokens.next(); // OPEN_PAREN

                var exprNode = exprNode(tokens);

                if (tokens.isEnd())
                    throw new ParseException(
                            "CLOSE_PAREN is required at the end of factor.");

                var closeParenToken = tokens.next(); // CLOSE_PAREN

                if (closeParenToken.getType() != CLOSE_PAREN)
                    throw new ParseException(closeParenToken ,
                            "CLOSE_PAREN is required at the end of factor.");

                return exprNode;
            default:
                break;
        }
        throw new IllegalArgumentException(".");
    }

    /**
     * <pre>
     * function_call =
     *     [IDENTIFIER] [OPEN_PAREN] factor *([COMMA]factor) [CLOSE_PAREN]
     * </pre>
     * @param tokens
     * @return
     */
    private IExpressionNode functionCallNode(@NonNull final ITokenSequence tokens) {

        if (tokens.isEnd() || tokens.peek().getType() != IDENTIFIER)
            throw new IllegalArgumentException("first token must be IDENTIFIER.");

        var functionNameToken = tokens.next();

        if (tokens.isEnd() || tokens.next().getType() != OPEN_PAREN)
            throw new ParseException(
                    "OPEN_PAREN is required at the beginning of function call.");

        var arguments = new ArrayList<IExpressionNode>();

        while (!tokens.isEnd() && tokens.peek().getType() != CLOSE_PAREN) {

            if (arguments.size() > 0 && tokens.next().getType() != COMMA)
                throw new ParseException(
                    "COMMA is required to delimit arguments of function call.");

            var argument = factorNode(tokens);
            arguments.add(argument);
        }

        if (tokens.isEnd() || tokens.next().getType() != CLOSE_PAREN)
            throw new ParseException(
                    "CLOSE_PAREN is required at the end of function call.");

        return new FunctionCallExpressionNode(functionNameToken, arguments);
    }
}
