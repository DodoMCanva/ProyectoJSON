package inicio;

/**
 *
 * @author fernando
 */
public class imc {

    public double calcularIMC(double peso, double altura) {
        return peso / (altura * altura);
    }

    public String getMensajeIMC(double imc) {
        if (imc < 18.5) {
            return "Bajo peso";
        }
        if (imc >= 18.5 && imc <= 24.9) {
            return "Peso normal";
        }
        if (imc >= 25 && imc <= 29.9) {
            return "Sobrepeso";
        }
        return "Obesidad";
    }
}
