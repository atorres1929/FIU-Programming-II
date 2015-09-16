package arithmetic;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util. Stack;
import java.util.EmptyStackException;
import java.util.Scanner;

class Arithmetic
{
	Stack stk;
	String expression,postfix;
        BigDecimal answer;
	int length;

	Arithmetic(String s)
	{
            expression = s;
            postfix ="";
            length = expression.length();
            stk = new Stack();
	}

	// Validate the expression - make sure parentheses are balanced
	boolean isBalance()
	{
		stk.clear(); 
                Scanner scan = new Scanner(expression);
                char current;
                while (scan.hasNext()){
                    current = scan.next().charAt(0);
                    if (isParentheses(current)){
                        if(current == Constants.LEFT_NORMAL)
                        {
                            stk.push(current); 
                        }
                        else if (current == Constants.RIGHT_NORMAL)
                        {
                            try
                            {
                                stk.pop();
                            }
                            catch(EmptyStackException e)
                            {
                                return false;
                            }
                        }
                    }
                    else if (isCurly(current)){
                        if(current == Constants.LEFT_SECONDARY)
                        {
                            stk.push(current); 
                        }
                        else if (current == Constants.RIGHT_SECONDARY)
                        {
                            try
                            {
                                stk.pop();
                            }
                            catch(EmptyStackException e)
                            {
                                return false;
                            }
                        }
                    }
                    else if (isSquare(current)){
                        if(current == Constants.LEFT_TERTIARY)
                        {
                            stk.push(current); 
                        }
                        else if (current == Constants.RIGHT_TERTIARY)
                        {
                            try
                            {
                                stk.pop();
                            }
                            catch(EmptyStackException e)
                            {
                                return false;
                            }
                        }
                        
                    }
                }
                return stk.isEmpty();
                
	}

	// Convert expression to postfix notation
	void postfixExpression()
	{
            stk.clear(); // Re-using the stack object       
            for (int i = 0; i < expression.length(); i++){
                expression = expression.replace('[', '(');
                expression = expression.replace(']', ')');
                expression = expression.replace('{', '(');
                expression = expression.replace('}', ')');
            }
            Scanner scan = new Scanner(expression);
            char current;
            // The algorithm for doing the conversion.... Follow the bullets
            while (scan.hasNext())
            {
                String token = scan.next();

                if (isNumber(token)) // Bullet # 1
                        postfix = postfix + token + " ";
                else
                {
                    current = token.charAt(0);

                    if (isParentheses(current)) // Bullet # 2 begins
                    {
                        if(stk.empty() || current == Constants.LEFT_NORMAL)
                        {
                            stk.push(current);
                        }
                        else if (current == Constants.RIGHT_NORMAL)
                        {
                            try
                            {
                               /* Some details ... whatever is popped from the
                                * stack is an object, hence you must cast this
                                * object to its proper type, then extract its
                                * primitive data (type) value.
                                */
                                char top = (Character)stk.pop();

                                while (top != Constants.LEFT_NORMAL)
                                {
                                    postfix += top+" ";
                                    top = (Character) stk.pop();

                                }
                            }
                            catch(EmptyStackException e)
                            {
                            }
                        }
                    }
                    
                    else // Bullet # 3 begins
                    {
                        if(stk.empty())
                                stk.push(current);
                        else
                        {
                            try
                            {
                                // Remember the method peek simply looks at the top
                               // element on the stack, but does not remove it.

                               char top = (Character)stk.peek();
                               boolean higher = hasHigherPrecedence(top, current);

                               while(top != Constants.LEFT_NORMAL && higher)
                               {
                                       postfix = postfix + stk.pop() + " ";
                                       top = (Character)stk.peek();
                               }
                               
                               stk.push(current);
                            }
                            catch(EmptyStackException e)
                            {
                            }
                        }//2 2 10 – 4 * 4 2 * 3 4 + / 2 + – 9 / +
                    }// Bullet # 3 ends
                }
            } // Outer loop ends

            try
            {
                while(!stk.empty()){// Bullet # 4
                    postfix = postfix + stk.pop() + " ";
                }   
            }
            catch(EmptyStackException e)
            {

            }
	}

	boolean isNumber(String str)
	{
            try {
                Integer.parseInt(str);
                return true;
            } catch (NumberFormatException e){}
                return false;
	}

	boolean isParentheses(char c)
	{
            return c == '(' || c == ')';
	}
        boolean isCurly(char c){
            return c == '{' || c == '}';
        }
        boolean isSquare(char c){
            return c == '[' || c == ']';
        }

	boolean isOperator(char ch)
        {
            return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '%';
	}

	boolean hasHigherPrecedence(char top, char current)
	{
                int t,c;
		switch(top){
                    case '%':
                        t = Constants.MULT;
                        break;
                    case '*':
                        t = Constants.MULT;
                        break;
                    case '/':
                        t = Constants.DIV;
                        break;
                    case '+':
                        t = Constants.ADD;
                        break;
                    case '-':
                        t = Constants.SUB;
                        break;
                    default:
                        t = -100;
                        break;
                }
                switch(current){
                    case '%':
                        c = Constants.MULT;
                        break;
                    case '*':
                        c = Constants.MULT;
                        break;
                    case '/':
                        c = Constants.DIV;
                        break;
                    case '+':
                        c = Constants.ADD;
                        break;
                    case '-':
                        c = Constants.SUB;
                        break;
                    default:
                        c = -100;
                        break;
                }
                return (t-c) >= 0;
	}

	String getPostfix()
	{
            return postfix;
	}

    void evaluateRPN() {
        stk.clear();
        Scanner scan = new Scanner(postfix);
        while (scan.hasNext()){
            String curr = scan.next();
            if (isNumber(curr))
                stk.push(curr);
            else if (isOperator(curr.charAt(0))){
                try{
                    char operator = curr.charAt(0);
                    BigDecimal t2 = new BigDecimal((String) stk.pop().toString());
                    BigDecimal t1 = new BigDecimal((String) stk.pop().toString());
                    switch (operator){
                        case '*':
                            stk.push(t1.multiply(t2));
                            break;
                        case '/':
                            
                            stk.push(t1.divide(t2, 3, RoundingMode.HALF_EVEN));
                            break;
                        case '+':
                            stk.push(t1.add(t2));
                            break;
                        case '-':
                            stk.push(t1.subtract(t2));
                            break;
                        case '%':
                            stk.push(t1.subtract(t1.divide(t2, BigDecimal.ROUND_DOWN).multiply(t2)));
                        default:
                            break;

                    }
                }//2 2 10 – 4 * 4 2 * 3 4 + / 2 + – 9 / +
                catch (EmptyStackException e){
                    System.out.println("Malformed postfix expression was created! Evalutation Terminated!");
                    return;
                }
            }
        }
        try{
        answer = (BigDecimal) stk.pop();
        }
        catch(EmptyStackException e){
            
        }
    }
    BigDecimal getAnswer(){
        return answer;
    }
}

final class Constants{
    static final char LEFT_NORMAL = '(';
    static final char RIGHT_NORMAL = ')';
    
    static final char LEFT_SECONDARY = '{';
    static final char RIGHT_SECONDARY = '}';
    
    static final char LEFT_TERTIARY = '[';
    static final char RIGHT_TERTIARY = ']';
    
    static final int MULT = 4;
    static final int DIV = 3;
    static final int ADD = 2;
    static final int SUB = 1;
}