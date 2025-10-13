package Escritura_Lectura_archivos;

import java.io.*;

public class LogActividad3 {
    public static void main(String[] args) {

// Nombre del archivo
        String nombreArchivo = "seguridad_actividad3.log";
        try {

// 1. Crear y escribir en el archivo con FileWriter
            FileWriter escritor = new FileWriter(nombreArchivo);
            escritor.write("Intento de acceso fallido\n");
            escritor.write("Usuario autenticado correctamente\n");
            escritor.close(); // Cerrar archivo después de escribir

// 2. Leer el archivo con FileReader y BufferedReader
            FileReader lector = new FileReader(nombreArchivo);
            BufferedReader buffer = new BufferedReader(lector);
            String linea;
            while ((linea = buffer.readLine()) != null) {
                System.out.println(linea);
            }
            buffer.close(); // Cerrar archivo después de leer
        } catch (IOException e) {
            System.out.println("Ocurrió un error: " + e.getMessage());
        }
    }
}