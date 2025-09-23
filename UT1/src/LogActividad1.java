import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogActividad1 {
    public static void main(String[] args) {

        // 1. Crear directorio
        File directorio = new File("logs/seguridad");
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

        // 2. Crear el fichero de log
        File logFile = new File(directorio, "seguridad_actividad1.log");
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
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmm").format(new Date());
        File logFileRenombrar = new File(directorio, "seguridad_actividad1_" + timeStamp + ".log");

        if (logFile.renameTo(logFileRenombrar)) {
            System.out.println("Fichero renombrado a: " + logFileRenombrar.getName());
        } else {
            System.out.println("No se pudo renombrar el fichero.");
            return;
        }

        // 4. Eliminar Archivo
        if (logFileRenombrar.delete()) {
            System.out.println("Fichero eliminado correctamente (retención aplicada).");
        } else {
            System.out.println("No se pudo eliminar el fichero.");
        }
    }
}