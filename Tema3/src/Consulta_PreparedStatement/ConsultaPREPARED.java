package Consulta_PreparedStatement;

import java.sql.*;

public class ConsultaPREPARED {
    // Constantes de conexión
    private static final String URL = "jdbc:mysql://localhost:3306/ut3";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    public static void main(String[] args) {
        int codigoAgenteBuscar = 7;
        Connection conexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // 1. Conexión
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);

            // 2. Sentencia parametrizada
            String sql = "SELECT CODIGO_AGENTE, NOMBRE_AGENTE, FRASE_CLAVE FROM agentes WHERE CODIGO_AGENTE = ?";
            preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setInt(1, codigoAgenteBuscar);

            // 3. Ejecutar consulta
            resultSet = preparedStatement.executeQuery();

            // 4. Procesar resultados
            if (resultSet.next()) {
                int codigoAgente = resultSet.getInt("CODIGO_AGENTE");
                String nombreAgente = resultSet.getString("NOMBRE_AGENTE");
                String fraseClave = resultSet.getString("FRASE_CLAVE");
                System.out.println(
                        "Código Agente: " + codigoAgente +
                                ", Nombre Agente: " + nombreAgente +
                                ", Frase Clave: " + fraseClave
                );
            } else {
                System.out.println("No se encontró ningún agente con ese código.");
            }
        } catch (SQLException ex) {
            muestraErrorSQL(ex);
        } catch (Exception ex) {
            System.out.println("Error general: " + ex.getMessage());
        } finally {
            // 5. Liberar recursos
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (conexion != null) conexion.close();
            } catch (SQLException ex) {
                muestraErrorSQL(ex);
            }
        }
    }

    // Método para mostrar errores SQL
    private static void muestraErrorSQL(SQLException ex) {
        System.out.println("Error SQL: " + ex.getMessage());
        System.out.println("SQLState: " + ex.getSQLState());
        System.out.println("Código de Error: " + ex.getErrorCode());
    }
}
