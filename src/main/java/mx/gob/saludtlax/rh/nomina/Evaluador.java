
package mx.gob.saludtlax.rh.nomina;

import java.util.Stack;
import java.util.StringTokenizer;

import org.jboss.logging.Logger;

public class Evaluador {

    private static final Logger LOGGER = Logger.getLogger(Evaluador.class.getName());

    private static final int EOL = 0;
    private static final int VALUE = 1;
    private static final int OPAREN = 2;
    private static final int CPAREN = 3;
    private static final int EXP = 4;
    private static final int MULT = 5;
    private static final int DIV = 6;
    private static final int PLUS = 7;
    private static final int MINUS = 8;

    // PrecTable matches order of Token enumeration
    private static Precedence[] precTable = new Precedence[] { new Precedence(0, -1), // EOL
            new Precedence(0, 0), // VALUE
            new Precedence(100, 0), // OPAREN
            new Precedence(0, 99), // CPAREN
            new Precedence(6, 5), // EXP
            new Precedence(3, 4), // MULT
            new Precedence(3, 4), // DIV
            new Precedence(1, 2), // PLUS
            new Precedence(1, 2) // MINUS
    };

    private Stack<Integer> opStack; // Operator stack for conversion
    private Stack<Double> postfixStack; // Stack for postfix machine
    private StringTokenizer str; // StringTokenizer stream

    /**
     * Construct an evaluator object.
     *
     * @param s
     *            the string containing the expression.
     */
    public Evaluador(String s) {
        opStack = new Stack<>();
        postfixStack = new Stack<>();

        str = new StringTokenizer(s, "+*-/^() ", true);

        opStack.push(EOL);
    }

    // The only publicly visible routine
    /**
     * Public routine that performs the evaluation. Examine the postfix machine
     * to see if a single result is left and if so, return it; otherwise print
     * error.
     *
     * @return the result.
     */
    public double getResult() {
        EvalTokenizer tok = new EvalTokenizer(str);
        Token lastToken;

        do {
            lastToken = tok.getToken();
            processToken(lastToken);
        } while (lastToken.getType() != EOL);

        if (postfixStack.isEmpty()) {
            LOGGER.error("Missing operand!");
            return 0;
        }

        double theResult = postfixStack.pop();
        if (!postfixStack.isEmpty()) {
            LOGGER.error("Warning: missing operators!");
        }

        return theResult;
    }

    public static String evaluar(String str) {
        LOGGER.debug("Read: " + str + "\n");
        Evaluador ev = new Evaluador(str);
        LOGGER.debug(ev.getResult());
        return String.valueOf(ev.getResult());
    }

    /**
     * After a token is read, use operator precedence parsing algorithm to
     * process it; missing opening parentheses are detected here.
     */
    private void processToken(Token lastToken) {
        int topOp;
        int lastType = lastToken.getType();

        switch (lastType) {
            case VALUE:
                postfixStack.push(lastToken.getValue());
                return;

            case CPAREN:
                while ((topOp = opStack.peek()) != OPAREN && topOp != EOL) {
                    binaryOp(topOp);
                }
                if (topOp == OPAREN) {
                    opStack.pop(); // Get rid of opening parentheseis
                } else {
                    LOGGER.error("Missing open parenthesis");
                }
                break;

            default: // General operator case
                while (precTable[lastType].inputSymbol <= precTable[topOp = opStack.peek()].topOfStack) {
                    binaryOp(topOp);
                }
                if (lastType != EOL) {
                    opStack.push(lastType);
                }
                break;
        }
    }

    /*
     * topAndPop the postfix machine stack; return the result. If the stack is
     * empty, print an error message.
     */
    private double postfixPop() {
        if (postfixStack.isEmpty()) {
            LOGGER.error("Missing operand");
            return 0;
        }
        return postfixStack.pop();
    }

    /**
     * Internal routine to compute x^n.
     */
    private static double pow(double x, double n) {
        if (x == 0) {
            if (n == 0) {
                LOGGER.error("0^0 is undefined");
            }
            return 0;
        }
        if (n < 0) {
            LOGGER.error("Negative exponent");
            return 0;
        }
        if (n == 0) {
            return 1;
        }
        if (n % 2 == 0) {
            return pow(x * x, n / 2);
        } else {
            return x * pow(x, n - 1);
        }
    }

    /**
     * Process an operator by taking two items off the postfix stack, applying
     * the operator, and pushing the result. Print error if missing closing
     * parenthesis or division by 0.
     */
    private void binaryOp(int topOp) {
        if (topOp == OPAREN) {
            LOGGER.error("Unbalanced parentheses");
            opStack.pop();
            return;
        }
        double rhs = postfixPop();
        double lhs = postfixPop();

        switch (topOp) {
            case EXP:
                postfixStack.push(pow(lhs, rhs));
                break;
            case PLUS:
                postfixStack.push(lhs + rhs);
                break;
            case MINUS:
                postfixStack.push(lhs - rhs);
                break;
            case MULT:
                postfixStack.push(lhs * rhs);
                break;
            case DIV:
                if (rhs != 0) {
                    postfixStack.push(lhs / rhs);
                } else {
                    LOGGER.error("Division by zero");
                    postfixStack.push(lhs);
                }
                break;
            default:
                break;
        }
        opStack.pop();
    }

    private static class EvalTokenizer {

        public EvalTokenizer(StringTokenizer is) {
            str = is;
        }

        /**
         * Find the next token, skipping blanks, and return it. For VALUE token,
         * place the processed value in currentValue. Print error message if
         * input is unrecognized.
         */
        public Token getToken() {
            double theValue;

            if (!str.hasMoreTokens()) {
                return new Token();
            }

            String s = str.nextToken();

            if (s != null) {
                switch (s) {
                    case ".":
                        return getToken();
                    case "^":
                        return new Token(EXP);
                    case "/":
                        return new Token(DIV);
                    case "*":
                        return new Token(MULT);
                    case "(":
                        return new Token(OPAREN);
                    case ")":
                        return new Token(CPAREN);
                    case "+":
                        return new Token(PLUS);
                    case "-":
                        return new Token(MINUS);
                }
            }

            try {
                theValue = Double.parseDouble(s);
            } catch (NumberFormatException e) {
                LOGGER.error("Parse error");
                return new Token();
            }

            return new Token(VALUE, theValue);
        }

        private final StringTokenizer str;

    }

    private static class Token {

        public Token() {
            this(EOL);
        }

        public Token(int t) {
            this(t, 0);
        }

        public Token(int t, double v) {
            type = t;
            value = v;
        }

        public int getType() {
            return type;
        }

        public double getValue() {
            return value;
        }

        private int type = EOL;
        private double value = 0;
    }

    private static class Precedence {

        public int inputSymbol;
        public int topOfStack;

        public Precedence(int inSymbol, int topSymbol) {
            inputSymbol = inSymbol;
            topOfStack = topSymbol;
        }
    }

}
