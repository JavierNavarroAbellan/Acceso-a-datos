import java.io.IOException;
import java.io.RandomAccessFile;

public class AccesoUsuarios {

    // Longitud fija de un registro (ID + espacio + nombre)
    private static final int REGISTRO_LONGITUD = 14;

    public static void main(String[] args) {
        try (RandomAccessFile raf = new RandomAccessFile("usuarios.dat", "rw")) {

            // 1. Crear archivo inicial con 5 usuarios

            String[] usuarios = {
                    "001 Javi",
                    "002 María",
                    "003 Mila",
                    "004 Abraham",
                    "005 Carlos"
            };

            // Escribir los registros iniciales
            for (String u : usuarios) {
                escribirRegistro(raf, u);
            }

            // 2. Lectura aleatoria: leer usuario con ID 003

            raf.seek(2 * REGISTRO_LONGITUD); // registro 003 está en la posición 2 (empezando en 0)
            String usuario003 = leerRegistro(raf);
            System.out.println("Lectura usuario 003: " + usuario003);

            // 3. Modificar datos: cambiar usuario 002 por Pedro

            raf.seek(1 * REGISTRO_LONGITUD); // Registro 002
            escribirRegistro(raf, "002 Pedro");

            System.out.println("Después de modificar usuario 002:");
            mostrarContenido(raf);

            // 4. Añadir un nuevo usuario 006 Ana al final

            raf.seek(raf.length()); // final del archivo
            escribirRegistro(raf, "006 Ana");

            System.out.println("Después de añadir usuario 006:");
            mostrarContenido(raf);

            // 5. Eliminar usuario 004 (sobrescribir con espacios)

            raf.seek(3 * REGISTRO_LONGITUD); // Registro 004
            escribirRegistro(raf, "004            ");

            System.out.println("Después de eliminar usuario 004:");
            mostrarContenido(raf);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Escribir registro con longitud fija
    private static void escribirRegistro(RandomAccessFile raf, String data) throws IOException {
        String id = data.substring(0, 3);
        String nombre = data.substring(4).trim();

        // Rellenar nombre a 10 caracteres
        nombre = String.format("%-10s", nombre);

        String registro = id + " " + nombre;
        raf.writeBytes(registro);
    }

    // Leer un registro completo
    private static String leerRegistro(RandomAccessFile raf) throws IOException {
        byte[] buffer = new byte[REGISTRO_LONGITUD];
        raf.readFully(buffer);
        return new String(buffer).trim();
    }

    // Mostrar todo el contenido del archivo
    private static void mostrarContenido(RandomAccessFile raf) throws IOException {
        raf.seek(0); // volver al inicio
        while (raf.getFilePointer() < raf.length()) {
            System.out.println(leerRegistro(raf));
        }
    }
}