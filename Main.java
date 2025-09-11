import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.IOException;
public class Main {
    public static void main(String[] args) throws Exception {

        Socket salida = new Socket("localhost", 8080);

        PrintWriter escritor = new PrintWriter(salida.getOutputStream(), true);
        BufferedReader lectorServidor = new BufferedReader(new InputStreamReader(salida.getInputStream()));
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

        String mensajeServidor;
        String mensajeUsuario;

        Thread hiloLector = new Thread(() -> {
            try {
                String linea;
                while ((linea = lectorServidor.readLine()) != null) {
                    System.out.println("Servidor: " + linea);

                    if (linea.contains("Cerrando sesión")) {
                        System.exit(0);
                    }
                }
            } catch (IOException e) {
                System.out.println("Conexión perdida con el servidor.");
            }
        });
        hiloLector.start();


        while (true) {
            mensajeUsuario = teclado.readLine();
            escritor.println(mensajeUsuario);
            if (mensajeUsuario.equalsIgnoreCase("FIN")) {
                break;
            }
        }

        salida.close();
        System.exit(0);
    }

}