
public class Operacion extends Thread {
    int x,y;
    String signo;
    double resultado;
    String error = null;
    
    public Operacion(int x, int y, String signo)
    {
        this.x = x;
	this.y = y;
	this.signo = signo;
     }
    
    
    @Override
    public void run()
        {
        switch (signo) {
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
	System.out.println("Resultado: " + resultado);
     }
}

