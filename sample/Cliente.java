package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Cliente {
    //creacion de cliente donde se esta creando las propiedades
    private IntegerProperty clienteId = new SimpleIntegerProperty();
    private StringProperty nombre = new SimpleStringProperty();
    private StringProperty Apellidos = new SimpleStringProperty();
    private StringProperty direccion = new SimpleStringProperty();
    //metodo contructor de cliente
    public Cliente(int clienteid, String nom, String apel, String direc){
        this.clienteId = new SimpleIntegerProperty(clienteid);
        this.nombre = new SimpleStringProperty(nom);
        this.Apellidos = new SimpleStringProperty(apel);
        this.direccion = new SimpleStringProperty(direc);
    }

    //gettery setter de variables
    public IntegerProperty getClienteIdProperty(){
        return clienteId;
    }


    public String getNombre() {
        return nombre.get();
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public String getApellidos() {
        return Apellidos.get();
    }

    public StringProperty apellidosProperty() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        this.Apellidos.set(apellidos);
    }

    public String getDireccion() {
        return direccion.get();
    }

    public StringProperty direccionProperty() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion.set(direccion);
    }


    public int getClienteId() {
        return clienteId.get();
    }

    public IntegerProperty clienteIdProperty() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId.set(clienteId);
    }
    @Override
    public String toString(){
        return "Cliente{" +
                "clienteID="+clienteId.get()+"," +
                "nombre="+nombre.get()+"," +
                "apellidos="+Apellidos.get()+"," +
                "direccion="+direccion.get()+"}";
    }
}
