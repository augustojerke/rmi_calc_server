import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;

public class CalculadoraServer extends UnicastRemoteObject implements Calculadora {

    protected CalculadoraServer() throws RemoteException {
        super();
    }

    public double somar(double a, double b) throws RemoteException {
        return a + b;
    }

    public double subtrair(double a, double b) throws RemoteException {
        return a - b;
    }

    public double multiplicar(double a, double b) throws RemoteException {
        return a * b;
    }

    public double dividir(double a, double b) throws RemoteException {
        if (b == 0) {
            throw new ArithmeticException("Divisão por zero não permitida.");
        }
        return a / b;
    }

    public static void main(String[] args) {
        try {
            System.out.println("Iniciando servidor RMI...");
            CalculadoraServer server = new CalculadoraServer();

            LocateRegistry.createRegistry(1099);

            Naming.rebind("//localhost:1099/MeuServico", server);
            System.out.println("Servidor Calculadora pronto...");
        } catch (Exception e) {
            System.err.println("Erro no servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

