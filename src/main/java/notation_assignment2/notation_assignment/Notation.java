package notation_assignment2.notation_assignment;

public class Notation {
    /**
     * Performs arithmetic calculation between two variables
     * @author Ricardo Abuabara
     * @param first string variable to be parsed as double for calculation
     * @param second string variable to be parsed as double for calculation
     * @param op operator to use in calculation
     * @return result of calculation
     */
    private static double operation(String first, String second,char op) {
        double result = 0,e1,e2;
        switch(op) {
            case '+':
                e1=Double.parseDouble(first);
                e2=Double.parseDouble(second);
                result=e1+e2;
                break;

            case '-':
                e1=Double.parseDouble(first);
                e2=Double.parseDouble(second);
                result=e1-e2;
                break;

            case '*':
                e1=Double.parseDouble(first);
                e2=Double.parseDouble(second);
                result=e1*e2;
                break;

            case '/':
                e1=Double.parseDouble(first);
                e2=Double.parseDouble(second);
                result=e1/e2;
                break;

            default:
                System.out.println("Unknown operator");
        }
        return result;
    }

    /**
     * convert a postfix expression to an infix expression
     * @param postfix expression to convert to infix
     * @return converted infix expression
     * @throws InvalidNotationFormatException
     */
    public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException {
        String infix;
        String top;
        MyStack<String> infixStack=new MyStack<String>(10);
        char[] temp=postfix.toCharArray();

        try {
            for(int i=0;i<temp.length;i++) {
                if(temp[i]==' ') {
                    continue;
                }
                if(Character.isDigit(temp[i])) {
                    infixStack.push(String.valueOf(temp[i]));
                }
                if(temp[i]=='+'||temp[i]=='-'||temp[i]=='/'||temp[i]=='*') {
                    if(infixStack.size()<2) {
                        throw new InvalidNotationFormatException("Invalid Notation Exception");
                    }
                    else {
                        top=infixStack.pop();
                        infix="("+infixStack.pop()+temp[i]+top+")";
                        infixStack.push(infix);
                    }
                }
            }
            if(infixStack.size()>1) {
                throw new InvalidNotationFormatException("Invalid Notation Exception");
            }
        }
        catch(StackOverflowException d) {
            d.printStackTrace();
        }
        catch(StackUnderflowException s) {
            s.printStackTrace();
        }
        return infixStack.toString();
    }

    /**
     * converts an infix expression to a postfix expression
     * @param infix expression to convert
     * @return converted postfix expression
     * @throws InvalidNotationFormatException
     */
    public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException {

        MyStack<String> postfixStack=new MyStack<String>(20);
        MyQueue<String> postfixQueue=new MyQueue<String>(20);
        char[] temp=infix.toCharArray();

        try {
            for(int i=0;i<temp.length;i++) {
                if(temp[i]==' ') {
                    continue;
                }
                if(Character.isDigit(temp[i])) {
                    postfixQueue.enqueue(String.valueOf(temp[i]));
                }
                if(temp[i]=='(') {

                    postfixStack.push(String.valueOf(temp[i]));
                }

                if(temp[i]=='+') {
                    if(!postfixStack.isEmpty()) {
                        while(postfixStack.top().equals("+")||postfixStack.top().equals("-")||postfixStack.top().equals("*")||postfixStack.top().equals("/")) {
                            postfixQueue.enqueue(postfixStack.pop());
                        }
                    }
                    postfixStack.push(String.valueOf(temp[i]));
                }
                if(temp[i]=='-') {
                    if(!postfixStack.isEmpty()) {
                        while(postfixStack.top().equals("+")||postfixStack.top().equals("-")||postfixStack.top().equals("*")||postfixStack.top().equals("/")) {
                            postfixQueue.enqueue(postfixStack.pop());
                        }
                    }
                    postfixStack.push(String.valueOf(temp[i]));
                }
                if(temp[i]=='/') {
                    if(!postfixStack.isEmpty()) {
                        while(postfixStack.top().equals("*")||postfixStack.top().equals("/")) {
                            postfixQueue.enqueue(postfixStack.pop());
                        }
                    }
                    postfixStack.push(String.valueOf(temp[i]));
                }
                if(temp[i]=='*') {
                    if(!postfixStack.isEmpty()) {
                        while(postfixStack.top().equals("*")||postfixStack.top().equals("/")) {
                            postfixQueue.enqueue(postfixStack.pop());
                        }
                    }
                    postfixStack.push(String.valueOf(temp[i]));
                }
                if(temp[i]==')') {
                    while(!postfixStack.isEmpty()&&!postfixStack.top().equals("(")) {
                        postfixQueue.enqueue(postfixStack.pop());
                    }
                    if(postfixStack.isEmpty()||!postfixStack.top().equals("(")) {
                        throw new InvalidNotationFormatException("Invalid Notation Exception");
                    }

                    if(!postfixStack.isEmpty()&&postfixStack.top().equals("(")){
                        postfixStack.pop();
                    }
                }
            }
            while(!postfixStack.isEmpty()&&!postfixStack.top().equals("(")) {
                postfixQueue.enqueue(postfixStack.pop());
            }
        }
        catch(QueueOverflowException e) {
            e.printStackTrace();
        }
        catch(StackOverflowException d) {
            d.printStackTrace();
        }
        catch(StackUnderflowException s) {
            s.printStackTrace();
        }
        return postfixQueue.toString();
    }

    /**
     * performs calculation of postfix expression
     * @param postfixExpr postfix expression to evaluate
     * @return result of evaluation
     * @throws InvalidNotationFormatException
     */
    public static double evaluatePostfixExpression(String postfixExpr) throws InvalidNotationFormatException {
        String first, second;
        double result=0;
        MyStack<String> postfixStack=new MyStack<String>(10);
        char[] temp=postfixExpr.toCharArray();

        try {
            for(int i=0;i<temp.length;i++) {
                if(temp[i]==' ') {
                    continue;
                }
                if(Character.isDigit(temp[i])|| temp[i]=='(') {
                    postfixStack.push(String.valueOf(temp[i]));
                }
                else {
                    if(postfixStack.size()<2) {
                        throw new InvalidNotationFormatException("Invalid Notation Exception");
                    }
                    else {
                        second=postfixStack.pop();
                        first=postfixStack.pop();
                        result=operation(first,second,temp[i]);
                        postfixStack.push(Double.toString(result));

                    }
                }
            }
            if(postfixStack.size()>1) {
                throw new InvalidNotationFormatException("Invalid Notation Exception");
            }
        }
        catch(StackOverflowException d) {
            d.printStackTrace();
        }
        catch(StackUnderflowException s) {
            s.printStackTrace();
        }


        return result;
    }

    /**
     * performs calculations of infix expression
     * @param infixExpr infix expression to evaluate
     * @return result of evaluation
     * @throws InvalidNotationFormatException
     */
    public static double evaluateInfixExpression(String infixExpr) throws InvalidNotationFormatException {
        double result=0;
        result = evaluatePostfixExpression(convertInfixToPostfix(infixExpr));

        return result;
    }

    /**
     * checks the operators used in the string
     * @param c
     * @return true if the string contains operators false if not
     */
    private static boolean isOperator(String c) {
        switch (c) {
            case "+":
            case "-":
            case "*":
            case "/":
                return true;
            default:
                return false;
        }
    }

    /**
     * checks for the String to contain integers
     * @param c
     * @return true if it finds one, false if it not
     */
    private static boolean isInteger(String c) {
        try {
            Integer.parseInt(c);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * checks precedence in operators
     * @param a
     * @param b
     * @return if operator a >= b in precedence
     */
    private static boolean isHigherEqualPrecedence(String a, String b) {
        int a1;
        int b1;

        a1 = a=="*" || a=="/" ? 1 : 0;
        b1 = b=="*" || b=="/" ? 1 : 0;

        return a1>=b1;
    }

    /**
     * Performs arithmetic calculation between two variables
     * @param op string variable to be parsed as double for calculation
     * @param strA string variable to be parsed as double for calculation
     * @param strB operator to use in calculation
     * @return result of calculation
     */
    private static int eval(String op, String strA, String strB) {
        int a = Integer.parseInt(strA);
        int b = Integer.parseInt(strB);
        switch(op) {
            case "+":
                return a+b;
            case "-":
                return a-b;
            case "*":
                return a*b;
            case "/":
                return a/b;
            default:
        }
        return 0;
    }
}
