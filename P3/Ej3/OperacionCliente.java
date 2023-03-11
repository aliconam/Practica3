import java.io.*;
import java.net.*;
import java.util.Scanner;

public class OperacionCliente {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String IP1 = "192.168.1.86";
	String IP2 = "192.168.1.107";
        int puerto = 4444;
        int puerto_resultados = 4445; // nuevo puerto para enviar resultados
        System.out.print("Presione Enter");
        scanner.nextLine();
        while (true) {
            Socket socket = new Socket(IP1, puerto);
            Socket socket_resultados = new Socket(IP2, puerto_resultados); // nuevo socket para enviar resultados
            OutputStream os = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            InputStream is = socket.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);

            System.out.println("Servidor: " + IP1 + ":" + puerto);
	    System.out.println("Enviando resultados a: " + IP2 + ":" + puerto_resultados);
            System.out.print("Elija una operacion (+,-,*,/, exit): ");
            String op = scanner.nextLine();
            if (op.equals("exit")) {
                oos.writeUTF(op);
                oos.flush();
                ois.close();
                oos.close();
                socket.close();
                break;
            }
            System.out.print("Primer numero: ");
            int x = scanner.nextInt();
            System.out.print("Segundo numero: ");
            int y = scanner.nextInt();
            oos.writeUTF(op);
            oos.writeInt(x);
            oos.writeInt(y);
            oos.flush();

            String resultado = ois.readUTF();

            // enviar resultados al servidor adicional
            ObjectOutputStream oos_resultados = new ObjectOutputStream						(socket_resultados.getOutputStream());
            oos_resultados.writeUTF(resultado);
            oos_resultados.flush();
            oos_resultados.close();
            socket_resultados.close();

            ois.close();
            oos.close();
            socket.close();
            scanner.nextLine();
        }
        scanner.close();
    }
}




