public class AppHilosOp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Operacion suma= new Operacion(10,5,"+");
        Operacion resta= new Operacion(10,5,"-");
        Operacion multi= new Operacion(10,5,"*");
        Operacion division= new Operacion(10,5,"/");
        
    
    suma.start();
    resta.start();
    multi.start();
    division.start();
    
    
    }
}

