import java.io.*;
import java.nio.file.*;
import java.util.*;

public class AnalisisFich {
    public static void main(String[] args) {
        String archivo = "seguridad.txt";
        String copia = "seguridad_copia.txt";

        try {
            // 1. Leer y mostrar el contenido original
            System.out.println("Contenido inicial de seguridad.txt");
            mostrarContenido(archivo);

            // 2. Añadir nuevas líneas al archivo
            List<String> nuevasLineas = Arrays.asList(
                    "[INFO] Nuevo intento de inicio de sesión.",
                    "[WARNING] Contraseña incorrecta introducida tres veces."
            );
            Files.write(Paths.get(archivo), nuevasLineas, StandardOpenOption.APPEND);

            // 3. Mostrar de nuevo el archivo
            System.out.println("Contenido después de añadir líneas");
            mostrarContenido(archivo);

            // 4. Contar líneas, palabras y caracteres
            contarElementos(archivo);

            // 5. Copiar archivo a seguridad_copia.txt
            Files.copy(Paths.get(archivo), Paths.get(copia), StandardCopyOption.REPLACE_EXISTING);

            // 6. Eliminar palabra "contraseña" en la copia
            limpiarPalabra(copia, "contraseña");

            // 7. Mostrar contenido final de la copia
            System.out.println("Contenido final de seguridad_copia.txt");
            mostrarContenido(copia);

        } catch (IOException e) {
            System.out.println("Error al trabajar con archivos: " + e.getMessage());
        }
    }

    // Mostrar contenido de un archivo
    public static void mostrarContenido(String archivo) throws IOException {
        List<String> lineas = Files.readAllLines(Paths.get(archivo));
        for (String linea : lineas) {
            System.out.println(linea);
        }
    }

    // Contar líneas, palabras y caracteres
    public static void contarElementos(String archivo) throws IOException {
        List<String> lineas = Files.readAllLines(Paths.get(archivo));
        int numLineas = lineas.size();
        int numPalabras = 0;
        int numCaracteres = 0;

        for (String linea : lineas) {
            numPalabras += linea.split("\\s+").length;
            numCaracteres += linea.length();
        }

        System.out.println("Estadísticas del archivo");
        System.out.println("Líneas: " + numLineas);
        System.out.println("Palabras: " + numPalabras);
        System.out.println("Caracteres: " + numCaracteres);
    }

    // Eliminar todas las apariciones de una palabra
    public static void limpiarPalabra(String archivo, String palabra) throws IOException {
        List<String> lineas = Files.readAllLines(Paths.get(archivo));
        List<String> nuevasLineas = new ArrayList<>();

        for (String linea : lineas) {
            nuevasLineas.add(linea.replaceAll("(?i)" + palabra, "")); // (?i) le da igual mayúsculas o minúsculas
        }

        Files.write(Paths.get(archivo), nuevasLineas, StandardOpenOption.TRUNCATE_EXISTING);
    }
}