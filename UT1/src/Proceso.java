import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Descripción: Ejemplo lanzar un proceso
 * @author Javi
 * @since 30-09-2025
 */

public class Proceso {

    public static void main(String args[]) {

        try {

            //1. Creamos el proceso
            ProcessBuilder pb = new ProcessBuilder("ping", "8.8.8.8");

            //2. Inicializamos el proceso
            Process p = pb.start();

            //3. Crea,ps BufferedReader
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));

            //Leemos linea a linea
            String line;
            while ((line = br.readLine()) !=null) {
                System.out.println(line);
            }
        }
        catch (IOException e) {
            throw  new RuntimeException(e);
        }
    }
}
