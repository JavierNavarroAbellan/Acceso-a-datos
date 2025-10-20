package seguridad;

import java.io.File;
import java.io.IOException;


public class seguridad {
    public static void main(String[] args) {

        String archivo = "accesos_navarro";
        String copia = "accesos_navarro_copia.txt";

        // 1. Crear directorio
        File directorio = new File("accesos/seguridad");
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println("Directorio creado: " + directorio.getPath());
            } else {
                System.out.println("No se pudo crear el directorio.");
                return;
            }
        } else {
            System.out.println("El directorio ya existe: " + directorio.getPath());
        }

        // 2. Crear el fichero .txt
        File logFile = new File(directorio, "accesos.txt");
        try {
            if (logFile.createNewFile()) {
                System.out.println("Fichero creado: " + logFile.getName());
            } else {
                System.out.println("El fichero ya existía: " + logFile.getName());
            }
        } catch (IOException e) {
            System.out.println("Error al crear el fichero: " + e.getMessage());
            return;
        }

        // 3. Renombrar archivo
        File logFileRenombrar = new File(directorio, "accesos_Navarro");
        if (logFile.renameTo(logFileRenombrar)) {
            System.out.println("Archivo renombrado a: " + logFileRenombrar.getName());
        } else {
            System.out.println("No se pudo renombrar el archivo.");
            return;
        }

        // 5. Eliminar el archivo renombrado
        if (logFileRenombrar.delete()) {
            System.out.println("Archivo eliminado correctamente.");
        } else {
            System.out.println("No se pudo eliminar el archivo.");
        }
    }
}