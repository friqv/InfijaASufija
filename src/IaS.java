import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class IaS {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("Expresión:");
            String expresionInfija = input.nextLine();

            if (expresionInfija.equals("quit")) {
                break;
            }

            String expresionSufija = convertirAExpresionSufija(expresionInfija);
            System.out.println("Expresión Sufija: " + expresionSufija);
        }
    }

    private static int obtenerPrecedencia(char operador) {
        switch (operador) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return 0; // Operador no válido
        }
    }

    private static String convertirAExpresionSufija(String expresionInfija) {
        StringBuilder expresionSufija = new StringBuilder();
        Stack<Character> pila = new Stack<>();

        StringTokenizer st = new StringTokenizer(expresionInfija, "{}[]()+-*/^", true);

        while (st.hasMoreElements()) {
            String token = st.nextToken();
            if (token.length() == 1 && "+-*/^".contains(token)) {
                char operador = token.charAt(0);

                while (!pila.isEmpty() && obtenerPrecedencia(operador) <= obtenerPrecedencia(pila.peek())) {
                    expresionSufija.append(pila.pop());
                }

                pila.push(operador);
            } else if (token.equals("(")) {
                pila.push('(');
            } else if (token.equals(")")) {
                while (!pila.isEmpty() && pila.peek() != '(') {
                    expresionSufija.append(pila.pop());
                }
                pila.pop(); // Eliminar '(' de la pila
            } else {
                expresionSufija.append(token); // Operandos directamente a la salida
            }
        }

        while (!pila.isEmpty()) {
            expresionSufija.append(pila.pop());
        }

        return expresionSufija.toString();
    }
}

