package Consulta_Select_BBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConsultaSELECT {

    // Constantes de conexión
    private static final String URL = "jdbc:mysql://localhost:3306/ut3";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    public static void main(String[] args) {
        String consultaSQL = "SELECT CODIGO_AGENTE, NOMBRE_AGENTE, FRASE_CLAVE FROM agentes";

        // Bloque que cierra automáticamente los recursos
        try (
                Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD);
                Statement sentencia = conexion.createStatement();
                ResultSet resultados = sentencia.executeQuery(consultaSQL)
        ) {
            System.out.println("Conexión exitosa. Resultados de la consulta:\n");

            // Recorremos el conjunto de resultados
            while (resultados.next()) {
                int codigo = resultados.getInt("CODIGO_AGENTE");
                String nombre = resultados.getString("NOMBRE_AGENTE");
                String frase = resultados.getString("FRASE_CLAVE");

                System.out.println("Código Agente: " + codigo
                        + ", Nombre Agente: " + nombre
                        + ", Frase Clave: " + frase);
            }

        } catch (SQLException e) {
            muestraErrorSQL(e);
        } catch (Exception e) {
            System.err.println("Error general: " + e.getMessage());
        }
    }

    // Método para mostrar detalles del error SQL
    private static void muestraErrorSQL(SQLException ex) {
        System.err.println("Error en la operación SQL:");
        System.err.println("Mensaje: " + ex.getMessage());
        System.err.println("Estado SQL: " + ex.getSQLState());
        System.err.println("Código de error: " + ex.getErrorCode());
    }
}