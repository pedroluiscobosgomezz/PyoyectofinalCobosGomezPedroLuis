package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TimeZone;

public class DBManager{

    private Connection connection;

    public Connection getConnection(){
        return this.connection;
    }
    public DBManager() throws SQLException {
        this.Connect();

    }



    public void Connect() throws SQLException{
        try {
            String url = "jdbc:mysql://localhost/bdBoletaje";//directorio de la base de datos
            connection = DriverManager.getConnection(url, "root", "");//objeto coneccion donde se declara url,usuario,contraseña

        } catch (SQLException ex) {//muestra los eerrores
            connection = null;
            ex.printStackTrace();
            System.out.println("SQLException:␣" + ex.getMessage());
            System.out.println("SQLState:␣" + ex.getSQLState());
            System.out.println("VendorError:␣" + ex.getErrorCode());
        }
    }

}