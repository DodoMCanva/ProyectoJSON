package conexion;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;
import org.json.JSONObject;

/**
 *
 * @author fernando
 */
public class conexion {

    public void enviarDatosPersona(String nombre, Double peso, Double altura) {
        JSONObject jsonData = new JSONObject();
        jsonData.put("nombre", nombre);
        jsonData.put("peso", peso);
        jsonData.put("altura", altura);

        String servidor = "localhost";
        int puerto = 5000;

        try (Socket socket = new Socket(servidor, puerto); OutputStream out = socket.getOutputStream(); InputStream in = socket.getInputStream()) {
            byte[] jsonBytes = jsonData.toString().getBytes();
            out.write(jsonBytes);
            out.flush();

            byte[] responseBytes = new byte[1024];
            int bytesRead = in.read(responseBytes);
            String respuesta = new String(responseBytes, 0, bytesRead);

            JSONObject jsonObject = new JSONObject(respuesta);

            String n = jsonObject.getString("nombre");
            double imc = jsonObject.getDouble("imc");
            String mensaje = jsonObject.getString("mensaje");
            JOptionPane.showMessageDialog(null, "Nombre: " + n + "\n"
                    + "IMC: " + String.format("%.2f", imc) + "\n"
                    + "Mensaje: " + mensaje);

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al conectar con el servidor");
        }
    }
}
