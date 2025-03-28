import java.rmi.Naming;
import java.util.Scanner;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CalculadoraClient {
    public static void main(String[] args) {
        try {
            Registry registro = LocateRegistry.getRegistry("localhost", 1099);
            Calculadora calculadora = (Calculadora) registro.lookup("MeuServico");

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Escolha uma operação: +, -, *, / ou 'sair' para encerrar");
                String operacao = scanner.next();

                if (operacao.equalsIgnoreCase("sair")) {
                    System.out.println("Encerrando o cliente...");
                    break;
                }

                System.out.print("Digite o primeiro número: ");
                double num1 = scanner.nextDouble();

                System.out.print("Digite o segundo número: ");
                double num2 = scanner.nextDouble();

                double resultado = 0;

                switch (operacao) {
                    case "+":
                        resultado = calculadora.somar(num1, num2);
                        break;
                    case "-":
                        resultado = calculadora.subtrair(num1, num2);
                        break;
                    case "*":
                        resultado = calculadora.multiplicar(num1, num2);
                        break;
                    case "/":
                        resultado = calculadora.dividir(num1, num2);
                        break;
                    default:
                        System.out.println("Operação inválida!");
                        continue;
                }

                System.out.println("Resultado: " + resultado);
            }

            scanner.close();
        } catch (Exception e) {
            System.err.println("Erro no cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
