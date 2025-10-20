package Analisi;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class AnalisisAlertas {
    public static void main(String[] args) {
        String archivo = "alertas.txt";
        String copia = "alertas_limpio.txt";

        try {

            // 1. Leer y mostrar el contenido original
            System.out.println("Contenido inicial de alertas.txt");
            mostrarContenido(archivo);


            // 2. Contar líneas, palabras y caracteres
            contarCaracteres(archivo);

            // 3. Copiar archivo
            Files.copy(Paths.get(archivo), Paths.get(copia),
                    StandardCopyOption.REPLACE_EXISTING);

            // 4. Eliminar palabra "virus" en la copia
            limpiarPalabra(copia, "virus");

            // 7. Mostrar contenido final de la copia
            System.out.println("Contenido final de alertas_limpio.txt");
            mostrarContenido(copia);

        } catch (IOException e) {
            System.out.println("Error al trabajar con archivos: " + e.getMessage());
        }
    }
        // Mostrar contenido de un archivo
        public static void mostrarContenido (String archivo) throws IOException {
            List<String> lineas = Files.readAllLines(Paths.get(archivo));
            for (String linea : lineas) {
                System.out.println(linea);
            }
        }

        // Contar Caracteres
        public static void contarCaracteres (String archivo) throws IOException {
            List<String> lineas = Files.readAllLines(Paths.get(archivo));
            int numCaracteres = 0;
            for (String linea : lineas) {
                numCaracteres += linea.length();
            }
            System.out.println("Caracteres: " + numCaracteres);
        }

        // Eliminar todas las apariciones de una palabra
    public static void limpiarPalabra(String archivo, String palabra) throws
            IOException {
        List<String> lineas = Files.readAllLines(Paths.get(archivo));
        List<String> nuevasLineas = new ArrayList<>();
        for (String linea : lineas) {
            nuevasLineas.add(linea.replaceAll("(?i)" + palabra, "")); // (?i) le da igual mayúsculas o minúsculas
        }
        Files.write(Paths.get(archivo), nuevasLineas,
                StandardOpenOption.TRUNCATE_EXISTING);
    }
}
