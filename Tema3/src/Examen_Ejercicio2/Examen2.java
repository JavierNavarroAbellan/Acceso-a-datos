package Examen_Ejercicio2;

import java.sql.*;

public class Examen2 {

    // Constantes de conexión
    private static final String URL = "jdbc:mysql://localhost:3306/ut3";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    public static void main(String[] args) {

        int codigoLlamadaBuscar = 1000054;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection conexion2 = null;

        String consultaSQL = "SELECT NUMERO_LLAMADO FROM LLAMADAS_EMITIDAS WHERE CODIGO_LLAMADA = 1000017";
        // Bloque que cierra automáticamente los recursos
        try (
                Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD);
                Statement sentencia = conexion.createStatement();
                ResultSet resultados = sentencia.executeQuery(consultaSQL)
        ) {
            System.out.println("Conexión exitosa. \nResultados de la consulta:\n");

            // Recorremos el conjunto de resultados
            while (resultados.next()) {
                int numero = resultados.getInt("NUMERO_LLAMADO");

                System.out.println("Número llamado: " + numero);
            }

        } catch (SQLException e) {
            muestraErrorSQL(e);
        } catch (Exception e) {
            System.err.println("Error general: " + e.getMessage());
        }

        try {

            conexion2 = DriverManager.getConnection(URL, USER, PASSWORD);

            //1. Sentencia parametrizada
            String sql = "SELECT DURACION_LLAMADA FROM LLAMADAS_EMITIDAS WHERE CODIGO_LLAMADA = ?";
            preparedStatement = conexion2.prepareStatement(sql);
            preparedStatement.setInt(1, codigoLlamadaBuscar);

            //2. Ejecutar consulta
            resultSet = preparedStatement.executeQuery();

            //3. Procesar resultados
            if (resultSet.next()) {
                int duracionLlamada = resultSet.getInt("DURACION_LLAMADA");
                System.out.println(
                        "Duracion de la Llamada: " + duracionLlamada);
            } else {
                System.out.println("No se encontró ninguna llamada con ese código.");
            }
        } catch (SQLException ex) {
            muestraErrorSQL(ex);
        } catch (Exception ex) {
            System.out.println("Error general: " + ex.getMessage());
        } finally {

            //4. Liberar recursos
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (conexion2 != null) conexion2.close();
            } catch (SQLException ex) {
                muestraErrorSQL(ex);
            }
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
