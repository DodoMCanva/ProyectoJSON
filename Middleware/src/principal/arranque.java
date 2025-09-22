package principal;

import conexion.conexion;

/**
 *
 * @author dodo
 */
public class arranque {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        conexion c = new conexion();
        c.iniciar();
    }
    
}
