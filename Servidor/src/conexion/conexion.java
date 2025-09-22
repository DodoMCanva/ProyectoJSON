package conexion;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import org.json.JSONObject;
import inicio.imc;

/**
 *
 * @author fernando
 */
public class conexion {

    public void iniciar() {
        int puerto = 1221;
        imc c = new imc();
        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            System.out.println("Servidor en espera de conexión...");

            while (true) {
                try (Socket socket = serverSocket.accept(); InputStream in = socket.getInputStream(); OutputStream out = socket.getOutputStream()) {
                    byte[] inputBytes = new byte[1024];
                    int bytesRead = in.read(inputBytes);
                    String jsonData = new String(inputBytes, 0, bytesRead);
                    JSONObject jsonObject = new JSONObject(jsonData);
                    double peso = jsonObject.getDouble("peso");
                    double altura = jsonObject.getDouble("altura");
                    double imc = c.calcularIMC(peso, altura);
                    JSONObject responseJson = new JSONObject();
                    responseJson.put("nombre", jsonObject.getString("nombre"));
                    responseJson.put("imc", imc);
                    responseJson.put("mensaje", c.getMensajeIMC(imc));
                    out.write(responseJson.toString().getBytes());
                    out.flush();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
