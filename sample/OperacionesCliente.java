package sample;

import sample.Cliente;
import sample.DBManager;

import java.sql.*;
import java.util.LinkedList;

public class OperacionesCliente {
    Connection connection;

    public OperacionesCliente(Connection connection) {

        this.connection = connection;
    }
    //metodo para insertar cliente
    public int insertCliente(String nom,String apell,String dire){
        int prueba=1;
        //sentencia para sql con las variables
        String query = "INSERT INTO cliente (nombre,apellidos,direccion)" +
                "VALUES ( '" + nom + "', '" + apell + "', '" + dire + "')";

        //Try y catch
        try{
            //Creacion de statement
            Statement stmt = connection.createStatement();
            //Ejecucion de variables
            int numRegs= stmt.executeUpdate(query);


            return prueba;
        }catch (SQLException ex){
            ex.printStackTrace();
            return 0;
        }


    }
    //metodo para borrar clientes
    public int deleteCliente(String apell){
        int prueba=1;
        int numRegs = 0;

//sentencia sql para borrar con las variables
        String query = "delete from cliente where apellidos = " + "'"+apell+"'";
//try y catch
        try {
            //Creacion de statement
            Statement stmt = connection.createStatement();
            //Ejecucion del statement
            numRegs = stmt.executeUpdate(query);
            return numRegs;
        }
        catch (java.sql.SQLException ex){
            ex.printStackTrace();
            return 0;
        }


    }
    //sentencia sql para actualizar cliente
    public int actualizarCliente(String clienteid, String nom, String apell, String direc){

        int numRegs=0;
//Sentencia sql para actualizar
        String query = "UPDATE cliente SET nombre = '" + nom + "', apellidos = '" + apell + "', direccion =' " + direc + "' WHERE clienteId = " + clienteid ;
        //try y catch
        try {
            //Creacion del statement
            Statement stmt = connection.createStatement();
            //Ejecucion del statement
            numRegs = stmt.executeUpdate(query);
            return numRegs;
        }
        catch (java.sql.SQLException ex){
            ex.printStackTrace();
            return 0;
        }

    }
   //Metodo para consultar nombre-----------------------
    public LinkedList<Cliente> consultaClienteNom(String nombre){
        int clienteid=0;
        //creacion de linkedlist de clientes
        LinkedList<Cliente> clien=new LinkedList<>();
        String nom = "", apel = "", direc = "";
//Sentencia para consultar de sql
        String query = "SELECT clienteId, nombre, apellidos, direccion "
                +"FROM cliente "
                +"WHERE nombre = " + "'"+nombre+"'";
        //try y catch
        try{
            //creacion del statement
            Statement stmt = connection.createStatement();
            //Ejecucion del statement
            ResultSet rs = stmt.executeQuery(query);
            //Guardado del statement
            while (rs.next()){
                clienteid = rs.getInt("clienteId");
                nom = rs.getString("nombre");
                apel =rs.getString("apellidos");
                direc = rs.getString("direccion");
                Cliente nexclie;
                nexclie = new Cliente(clienteid, nom, apel, direc);
                clien.add(nexclie);
            }
            //regresar linkedlist
            return clien;
        }catch (java.sql.SQLException ex){
            ex.printStackTrace();
            return null;
        }

    }
    ////////////Metodo para consultar apellido
    public LinkedList<Cliente> consultaCliente(String apellido){
        int clienteid=0;
        //creacion de linkedlist de clientes
        LinkedList<Cliente> clien=new LinkedList<>();
        String nom = "", apel = "", direc = "";
//Sentencia para consultar de sql
        String query = "SELECT clienteId, nombre, apellidos, direccion "
                +"FROM cliente "
                +"WHERE apellidos = " + "'"+apellido+"'";
        //try y catch
        try{
            //creacion del statement
            Statement stmt = connection.createStatement();
            //Ejecucion del statement
            ResultSet rs = stmt.executeQuery(query);
            //Guardado del statement
            while (rs.next()){
                clienteid = rs.getInt("clienteId");
                nom = rs.getString("nombre");
                apel =rs.getString("apellidos");
                direc = rs.getString("direccion");
                Cliente nexclie;
                nexclie = new Cliente(clienteid, nom, apel, direc);
                clien.add(nexclie);
            }
            //regresar linkedlist
            return clien;
        }catch (java.sql.SQLException ex){
            ex.printStackTrace();
            return null;
        }

    }
    ///////////////////////////////////////////////////////////Metodo para consultar por id
    public Cliente consultaClienteid(String id){
        int clienteid=0;
        String nombre = "", apellidos = "", direccion = "";
        //creacion de linkedlist de clientes
        String nom = "", apel = "", direc = "";
//Sentencia para consultar de sql
        String query = "SELECT clienteId, nombre, apellidos, direccion "
                +"FROM cliente "
                +"WHERE clienteId = "+ id;
        //try y catch
        try{
            //creacion del statement
            Statement stmt = connection.createStatement();
            //Ejecucion del statement
            ResultSet rs = stmt.executeQuery(query);
            //Guardado del statement
            if (rs.next()){
                clienteid = rs.getInt("clienteId");
                nom = rs.getString("nombre");
                apel =rs.getString("apellidos");
                direc = rs.getString("direccion");
                Cliente nexclie;//se crea un objettto de la clase cliente
                //aqui se guardan todos los datos obtenidos ddela base de datos
                nexclie = new Cliente(clienteid, nom, apel, direc);
                return nexclie;//retorna el cliente, manda los datos a nvCliente
            }
            //regresar linkedlist
            return null;
        }catch (java.sql.SQLException ex){
            ex.printStackTrace();
            return null;
        }

    }


}
