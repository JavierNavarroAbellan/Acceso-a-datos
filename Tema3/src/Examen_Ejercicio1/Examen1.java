package Examen_Ejercicio1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Examen1 {

    // Variables de conexión
    private static final String URL = "jdbc:mysql://localhost:3306/ut3";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    public static void main(String[] args) {
        String sql = """
            CREATE TABLE examen (
                DNI CHAR(9) NOT NULL PRIMARY KEY,
                APELLIDOS VARCHAR(32) NOT NULL,
                NOTAS DECIMAL(5,2) NOT NULL)
            """;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(sql);
            System.out.println("Tabla examen creada correctamente.");

        } catch (SQLException ex) {
            muestraErrorSQL(ex);
        } catch (Exception ex) {
            System.out.println("Error general: " + ex.getMessage());
        }

        String sql2 = """
                INSERT INTO examen (
                DNI ,APELLIDOS,NOTAS)
                Values ('48832205D','Navarro','8')
            """;
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(sql2);
            System.out.println("Datos introducidos correctamente.");

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
