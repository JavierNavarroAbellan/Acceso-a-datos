package Importacion_consultas_procesos;

import java.sql.*;
import javax.swing.JOptionPane;

public class PrecipitacionAlgemesi {

    // Constantes de conexión
    private static final String URL = "jdbc:mysql://localhost:3306/ut3";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    public static void main(String[] args) {

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {

            // 1. Crear la tabla
            String crearTabla = "CREATE TABLE IF NOT EXISTS datos_climaticos (" +
                    "Provincia VARCHAR(50)," +
                    "Estacion VARCHAR(50)," +
                    "Fecha VARCHAR(20)," +
                    "Temperatura DOUBLE," +
                    "Humedad DOUBLE," +
                    "Precipitacion DOUBLE" +
                    ")";
            try (Statement stmt = conn.createStatement()) {
                stmt.executeUpdate(crearTabla);
                System.out.println("Tabla 'datos_climaticos' creada o ya existente.");
            }

            // 2. Consulta de la precipitación media en octubre
            String consultaMedia = "SELECT AVG(Precipitacion) AS Media " +
                    "FROM datos_climaticos " +
                    "WHERE Estacion = 'Algemesi'";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(consultaMedia)) {
                if (rs.next()) {
                    double media = rs.getDouble("Media");
                    JOptionPane.showMessageDialog(null, "Precipitación media en octubre en Algemesí: "
                            + media + " mm");
                    System.out.println("Precipitación media: " + media + " mm");
                }
            }

            // 3. Crear el procedimiento almacenado
            String crearProcedimiento =
                    "CREATE PROCEDURE CalcularPrecipitacionTotal(IN estacionNombre VARCHAR(50), OUT totalPrecipitacion DOUBLE) " +
                            "BEGIN " +
                            "    SELECT SUM(Precipitacion) INTO totalPrecipitacion " +
                            "    FROM datos_climaticos " +
                            "    WHERE Estacion = estacionNombre; " +
                            "END";
            try (Statement stmt = conn.createStatement()) {
                stmt.execute("DROP PROCEDURE IF EXISTS CalcularPrecipitacionTotal");
                stmt.execute(crearProcedimiento);
                System.out.println("Procedimiento almacenado creado o actualizado.");
            } catch (SQLException e) {
            }

            // 4. Llamada al procedimiento almacenado usando CallableStatement
            CallableStatement cs = conn.prepareCall("{call CalcularPrecipitacionTotal(?, ?)}");
            cs.setString(1, "Algemesi");
            cs.registerOutParameter(2, Types.DOUBLE);
            cs.execute();
            double total = cs.getDouble(2);
            JOptionPane.showMessageDialog(null, "Precipitación total en octubre Algemesí: "
                    + total + " mm");
            System.out.println("Precipitación total: " + total + " mm");

        } catch (SQLException ex) {
            muestraErrorSQL(ex);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public static void muestraErrorSQL(SQLException ex) {
        JOptionPane.showMessageDialog(null,
                "SQL Error: " + ex.getMessage() +
                        "\nSQL State: " + ex.getSQLState() +
                        "\nError Code: " + ex.getErrorCode());
        System.out.println("SQL Error: " + ex.getMessage());
        System.out.println("SQL State: " + ex.getSQLState());
        System.out.println("Error Code: " + ex.getErrorCode());
    }
}