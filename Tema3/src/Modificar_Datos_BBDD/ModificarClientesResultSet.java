package Modificar_Datos_BBDD;

import javax.swing.*;
import java.sql.*;

public class ModificarClientesResultSet {

    // Constantes de conexión
    private static final String URL = "jdbc:mysql://localhost:3306/ut3";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    public static void main(String[] args) {

        // Bloque try para la conexión
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)){
             conn.setAutoCommit(false);

            try (Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)){
                ResultSet resultSet = statement.executeQuery("SELECT DNI, APELLIDOS, CP FROM clientes");

                int filasModificadas = 0;

                while (resultSet.next()){
                    String cpActual = resultSet.getString("CP");
                    if (cpActual == null){
                        resultSet.updateString("CP", "00000");
                        resultSet.updateRow();
                        filasModificadas++;
                    }
                }
                conn.commit();
                JOptionPane.showMessageDialog(null, filasModificadas + " Filas modificadas en la tabla Clientes.", "Resultado de la modificación", JOptionPane.INFORMATION_MESSAGE);

            }catch (SQLException ex) {
                muestraErrorSQL(ex);
            }
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