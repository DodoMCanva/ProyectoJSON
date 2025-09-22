package conexion;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author dodo
 */
public class conexion {

    public void iniciar() {
        int puertoCliente = 5000; 
        String servidor = "localhost";
        int puertoServidor = 1221; 

        try (ServerSocket middlewareSocket = new ServerSocket(puertoCliente)) {
            System.out.println("Middleware en espera de conexión del cliente...");
            while (true) {
                try (Socket clienteSocket = middlewareSocket.accept(); InputStream inCliente = clienteSocket.getInputStream(); OutputStream outCliente = clienteSocket.getOutputStream()) {
                    byte[] buffer = new byte[1024];
                    int bytesRead = inCliente.read(buffer);
                    try (Socket servidorSocket = new Socket(servidor, puertoServidor); OutputStream outServidor = servidorSocket.getOutputStream(); InputStream inServidor = servidorSocket.getInputStream()) {
                        outServidor.write(buffer, 0, bytesRead);
                        outServidor.flush();

                        byte[] respuestaBuffer = new byte[1024];
                        int respuestaBytes = inServidor.read(respuestaBuffer);

                        outCliente.write(respuestaBuffer, 0, respuestaBytes);
                        outCliente.flush();
                    }

                } catch (IOException e) {
                    System.out.println("Error al comunicar con el servidor: " + e.getMessage());
                }
            }

        } catch (IOException e) {
            System.out.println("Error en middleware: " + e.getMessage());
        }

    }
}
