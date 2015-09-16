package arithmetic;

class RPN
{
	public static void main(String[] arg)
	{
		String s[] = {"5 + ) * ( 2",
                    " 2 + { 2 * ( 10 - 4 ) / [ { 4 * 2 / ( 3 + 4 ) } + 2 ] - 9 }",
                    " 2 + ( - 3 * 5 ) ",
                    " 2 + } 2 * ( 10 – 4 ) / [ { 4 * 2 / ( 3 + 4 ) } + 2 ] – 9 { ",
                    "( ( 2 + 3 ) * 5 ) * 8",
                    "5 * 10 + ( 15 - 20 ) )  - 25",
                    " 5 + ( 5 *  10 + ( 15 - 20 )  - 25 ) * 9",
                    "9 % 4"
		             };
                /*
                unbalanced
                balanced
                balanced with negative number
                unbalanced
                balanced
                unbalanced
                balanced
                
                */
		for (int i = 0; i < s.length; i++)
		{

			Arithmetic a = new Arithmetic(s[i]);
			if (a.isBalance())
			{
                            System.out.println("Expression " + s[i] + " is balanced");
                            a.postfixExpression();
                            System.out.println("The post fixed expression is " + a.getPostfix());
                            a.evaluateRPN();
                            System.out.println("Evaluated Expression: " + a.getAnswer()+"\n");
                            
			}
			else
				System.out.println("Expression " + s[i] + " is not balanced\n");
		}
	}
}