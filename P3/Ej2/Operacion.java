
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Operacion extends Thread {
    private Socket socket;

    public Operacion(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            while (true) {
                String operacion= ois.readUTF();
                if (operacion.equals("exit")) {
                    break;
                }
                int x = ois.readInt();
                int y = ois.readInt();

                int resultado = 0;
                String error = null;

                switch (operacion) {
                    case "+":
                        resultado = x + y;
                        break;
                    case "-":
                        resultado = x - y;
                        break;
                    case "*":
                        resultado = x * y;
                        break;
                    case "/":
                        if (y == 0) {
                            error = "No se puede dividir ente cero";
                        } else {
                            resultado = x / y;
                        }
                        break;
                    default:
                        error = "Operacion Invalida";
                        break;
                }

                if (error != null) {
                    oos.writeUTF(error);
                } else {
                    oos.writeUTF("Resultado: " + resultado);
                }
                oos.flush();
            }

            ois.close();
            oos.close();
            socket.close();
            System.out.println("Cliente desconcectado: " + socket.getInetAddress().getHostName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


