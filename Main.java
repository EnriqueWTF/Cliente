import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Main {
    public static void main(String[] args)throws Exception {

        Socket salida = new Socket("Localhost",8080); 


       PrintWriter escritor = new PrintWriter(salida.getOutputStream(),true);
       BufferedReader lector = new BufferedReader(new InputStreamReader(salida.getInputStream()));
       BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
        String mensaje;
       String cadena = teclado.readLine();
       
    while (!cadena.equalsIgnoreCase("FIN")){
           escritor.println(cadena);
           mensaje = lector.readLine();
           System.out.println(mensaje);
           cadena= teclado.readLine();  
           
       }
      salida.close();
    }
}

    


    

