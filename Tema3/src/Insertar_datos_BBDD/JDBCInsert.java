package Insertar_datos_BBDD;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCInsert {

    // Constantes de conexión
    private static final String URL = "jdbc:mysql://localhost:3306/ut3";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    public static void main(String[] args) {

        int filas;
        String sql = """
            
                INSERT INTO clientes (
                DNI ,APELLIDOS,CP)
                Values ('78901234W','BLEDA','44006')
            """;

        // Bloque try para la conexión
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {

            filas = stmt.executeUpdate(sql);
            System.out.println("Fila insertada correctamente.");
        JOptionPane.showMessageDialog(null, filas + " Filas insertadas correctamente.", "Resultado de la Inserción", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            muestraErrorSQL(ex);
        } catch (Exception ex) {
            System.out.println("Error general: " + ex.getMessage());
        }
    }

    public static void muestraErrorSQL(SQLException ex) {
        System.out.println("Mensaje de error: " + ex.getMessage());
        System.out.println("Estado SQL: " + ex.getSQLState());
        System.out.println("Código de error: " + ex.getErrorCode());
    }
}