import java.io.*;
import java.net.*;
import java.util.Scanner;

public class OperacionCliente {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String IP= "192.168.1.86";
        int puerto= 4444;
        System.out.print("Presione Enter");
        scanner.nextLine();
        while (true) {
            Socket socket = new Socket(IP,puerto);
            OutputStream os = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            InputStream is = socket.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            System.out.println("Servidor: " + IP + ":" + puerto);
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
            System.out.println(resultado);
            ois.close();
            oos.close();
            socket.close();
	    scanner.nextLine();
        }
        scanner.close();
    }
}

