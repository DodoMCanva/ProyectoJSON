package inicio;

import conexion.conexion;
import vista.frmIMC;

/**
 *
 * @author dodo
 */
public class arranque {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        frmIMC vista = new frmIMC(new conexion());
        vista.setVisible(true);
    }
}

