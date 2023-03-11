import java.io.*;
import java.net.*;

public class OperacionClienteResultados {
    public static void main(String[] args) throws IOException {
        int puerto = 4445;
        ServerSocket serverSocket = new ServerSocket(puerto);
        System.out.println("Inicio del servidor de resultados");
        while (true) {
            Socket socket = serverSocket.accept();
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            String resultado = ois.readUTF();
            System.out.println("Resultado recibido: " + resultado);
            ois.close();
            socket.close();
        }
    }
}



