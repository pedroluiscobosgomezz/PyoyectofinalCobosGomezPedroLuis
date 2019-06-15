package sample;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;


public class Main extends Application {
    @Override
//creacion de primer stage

    public void start(Stage primaryStage) throws Exception {
//creacion de tabla
        TableView table=new TableView();
//crea las columnas de la tablas
        TableColumn idColumna = new TableColumn("Id");
        idColumna.setCellValueFactory(new PropertyValueFactory("clienteId"));
        TableColumn nombreColumna = new TableColumn("Nombre");
        nombreColumna.setCellValueFactory(new PropertyValueFactory("nombre"));
        TableColumn apellidosColumna = new TableColumn("Apellidos");
        apellidosColumna.setCellValueFactory(new PropertyValueFactory("apellidos"));
        TableColumn direccionColumna = new TableColumn("Direccion");
        direccionColumna.setCellValueFactory(new PropertyValueFactory("direccion"));

        table.getColumns().setAll(idColumna, nombreColumna,apellidosColumna,direccionColumna);
        table.setPrefWidth(350);
        table.setPrefHeight(300);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);



        DBManager accesoDB = new DBManager();
        OperacionesCliente opCliente = new OperacionesCliente(accesoDB.getConnection());
//creacion de segundo stage
        Stage second= new Stage();
//creacin de botones basicos
        Button agre = new Button ("Agregar");
        Button eli = new Button ("Eliminar");
        Button actu = new Button ("Actualizar");
        Button consu = new Button ("Consultar");
//establecer borde de la pantalla
        BorderPane principal = new BorderPane();
        BorderPane funcionBotones = new BorderPane();
//tamaño de la principal
        primaryStage.setScene (new Scene(principal,850,800));
        //segunda ventana por botones
        second.setScene (new Scene(funcionBotones,850,800));
        primaryStage.setTitle("Proyecto final");
        second.setTitle("Proyecto final");

        Label suo = new Label("Seleccione una opción ");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(40, 40, 20, 40));
//cordenadas del texto
        grid.add(suo, 15, 2);
        grid.add(agre, 15, 4);
        grid.add(eli, 15, 6);
        grid.add(actu, 15, 8);
        grid.add(consu, 15, 10);

        grid.setStyle("-fx-background-color: #B88DAE;");

//creacion de textos de interuaccion

        Label agresub = new Label(" AGREGAR ");
        Label nom = new Label("Nombre");
        Label ape = new Label("Apellido ");
        Label direc = new Label("Dirección");


//creacion de cajas de texto
        TextField cajanombre = new TextField();
        cajanombre.setPrefWidth(70);
        TextField cajaapellido = new TextField();
        cajaapellido.setPrefWidth(90);
        TextField cajadireccion = new TextField();
        cajadireccion.setPrefWidth(110);
        //cordenadas de cajas de texto
//creacion de botones de guardar y salir
        Button guar = new Button ("Guardar");
        Button sali = new Button ("Salir");
        Button borra = new Button ("Borrar");
        Button consultar = new Button ("Consultar_Apellido");
        Button consultarid = new Button ("Consultar_Id");
        Button consultarname= new Button ("Consultar_Nombre");

//accediendo al boton agregar
        agre.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //creando un grid-vacio
                GridPane grid2 =new GridPane();

                cajanombre.clear();
                cajaapellido.clear();
                cajadireccion.clear();

                //llamado del metodo addGrid2
                grid2=addGrid2(cajanombre,cajaapellido,cajadireccion,guar,sali,agresub,nom,ape,direc);
                funcionBotones.setCenter(grid2);
                //llamar segunda ventana
                second.show();
            }
        });



        //creacion de textos de interuaccion

        Label consusub = new Label(" CONSULTAR");
        Label id = new Label("Id");






//creacion de cajas de texto
        TextField cajaid= new TextField();
        cajaid.setPrefWidth(70);

        Button actua= new Button ("Actualizar");


        consu.setOnAction(new EventHandler<ActionEvent>() {

            @Override//para mostrar ecena
            public void handle(ActionEvent event) {
                GridPane grid3 =new GridPane();

                cajaid.clear();
                cajanombre.clear();
                cajaapellido.clear();
                cajadireccion.clear();

                grid3=addGrid(nom,ape,direc,actua,cajanombre,cajaapellido,cajadireccion,guar,sali,borra,consultar,consultarid,consultarname,consusub,id,cajaid,table);
                funcionBotones.setCenter(grid3);

                second.show();


            }
        });
        eli.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                GridPane grid3 =new GridPane();

                //limpieza de pantalla
                cajaid.clear();
                cajanombre.clear();
                cajaapellido.clear();
                cajadireccion.clear();

                grid3=addGrid(nom,ape,direc,actua,cajanombre,cajaapellido,cajadireccion,guar,sali,borra,consultar,consultarid,consultarname,consusub,id,cajaid,table);
                funcionBotones.setCenter(grid3);

                second.show();


            }
        });
        actu.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                GridPane grid3 =new GridPane();

                //limpieza de pantalla
                cajaid.clear();
                cajanombre.clear();
                cajaapellido.clear();
                cajadireccion.clear();

                grid3=addGrid(nom,ape,direc,actua,cajanombre,cajaapellido,cajadireccion,guar,sali,borra,consultar,consultarid,consultarname,consusub,id,cajaid,table);
                funcionBotones.setCenter(grid3);

                second.show();


            }
        });
        sali.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {


                second.hide();


            }
        });

        guar.setOnAction(new EventHandler<ActionEvent>() { //inicio de evento

            @Override
            public void handle(ActionEvent event) {

                //compara valores = 0, y determina si manda una alerta o continua
                if(cajanombre.getText().length()==0&cajaapellido.getText().length()==0&cajadireccion.getText().length()==0){
                    Alert alerta = new Alert(AlertType.ERROR);
                    alerta.setTitle("Operación fallida");
                    alerta.setHeaderText("No tiene todas las cajas llenas");
                    alerta.setContentText(null);
                    alerta.showAndWait();
                }else{
                    String nom =cajanombre.getText();//tooma los valors de text
                    String apell=cajaapellido.getText();
                    String direcc=cajadireccion.getText();
                    int prueba=opCliente.insertCliente(nom,apell,direcc);//manda llamar al metodo insertcliente de la clase operecionescliente
                    //alertas
                    if(prueba==1){
                        Alert alerta = new Alert(AlertType.INFORMATION);
                        alerta.setTitle("Operación exitosa");//
                        alerta.setHeaderText("Se logro el guardar nuevo cliente");
                        alerta.setContentText(null);
                        alerta.showAndWait();
                    }else{
                        Alert alerta = new Alert(AlertType.ERROR);
                        alerta.setTitle("Operación fallida");
                        alerta.setHeaderText("No se logro guardar al cliente");
                        alerta.setContentText(null);
                        alerta.showAndWait();
                    }
                    //limpieza de pantalla
                    cajaid.clear();
                    cajanombre.clear();
                    cajaapellido.clear();
                    cajadireccion.clear();
                }

            }
        });
//borrar por apellido
        borra.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (cajaapellido.getText().length()==0){
                    Alert alerta = new Alert(AlertType.ERROR);
                    alerta.setTitle("Operación fallida");
                    alerta.setHeaderText("Caja de apellido vacia");
                    alerta.setContentText(null);
                    alerta.showAndWait();
                }else{
                    String apell=cajaapellido.getText();

                    int prueba=opCliente.deleteCliente(apell);
                    if(prueba!=0){
                        Alert alerta = new Alert(AlertType.INFORMATION);
                        alerta.setTitle("Operación exitosa");
                        alerta.setHeaderText("Se logro el borrar al cliente");
                        alerta.setContentText(null);
                        alerta.showAndWait();
                    }else{
                        Alert alerta = new Alert(AlertType.ERROR);
                        alerta.setTitle("Operación fallida");
                        alerta.setHeaderText("No se logro borrar al cliente");
                        alerta.setContentText(null);
                        alerta.showAndWait();
                    }
                }

            }
        }); //cuando sepreciona un botton consulta

        consultar.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                int prueba=1;
                if (cajaapellido.getText().length()==0){
                    Alert alerta = new Alert(AlertType.ERROR);
                    alerta.setTitle("Operación fallida");
                    alerta.setHeaderText("Caja de apellido vacia");
                    alerta.setContentText(null);
                    alerta.showAndWait();
                }else{
                    String apell=cajaapellido.getText();
                    //Cliente prcliente=aaaa();
                    LinkedList<Cliente> aaa=new LinkedList<Cliente>();
                    aaa=opCliente.consultaCliente(apell);

                    ObservableList data = FXCollections.observableList(aaa);
                    table.setItems(data);

                    Iterator<Cliente> iterador=aaa.iterator();
                    try {
                        Cliente consultanCliente=iterador.next();
                        cajaid.setText(String.valueOf(consultanCliente.getClienteId()));
                        cajanombre.setText(consultanCliente.getNombre());
                        cajaapellido.setText(consultanCliente.getApellidos());
                        cajadireccion.setText(consultanCliente.getDireccion());
                        prueba=1;
                    }catch (Exception ex){
                        prueba=0;
                    }
                    //limpiarcajas de texto
                    cajanombre.clear();
                    cajaid.clear();
                    cajadireccion.clear();

                    if(prueba==1){
                        Alert alerta = new Alert(AlertType.INFORMATION);
                        alerta.setTitle("Operación exitosa");
                        alerta.setHeaderText("Se logro encontrar clientes");
                        alerta.setContentText(null);
                        alerta.showAndWait();
                    }else{
                        Alert alerta = new Alert(AlertType.ERROR);
                        alerta.setTitle("Operación fallida");
                        alerta.setHeaderText("No se logro encontrar cliente");
                        alerta.setContentText(null);
                        alerta.showAndWait();
                    }
                }


            }
        });
//Consulta por nombre
        consultarname.setOnAction(new EventHandler<ActionEvent>() {

            @Override//declarar evento
            public void handle(ActionEvent event) {
                int prueba=1;
                //si cajanombre =0 entonces imprimir Operación fallida & Caja de nombre vacia
                if (cajanombre.getText().length()==0){//
                    Alert alerta = new Alert(AlertType.ERROR);
                    alerta.setTitle("Operación fallida");
                    alerta.setHeaderText("Caja de nombre vacia");
                    alerta.setContentText(null);
                    alerta.showAndWait();
                }else{
                    String apell=cajanombre.getText();//la variable tipo string obtiene el valor almacenado en la caja nombre
                    //Cliente prcliente=aaaa();
                    LinkedList<Cliente> aaa=new LinkedList<Cliente>();
                    aaa=opCliente.consultaClienteNom(apell);

                    ObservableList data = FXCollections.observableList(aaa);
                    table.setItems(data);

                    Iterator<Cliente> iterador=aaa.iterator();
                    try {
                        Cliente consultanCliente=iterador.next();
                        cajaid.setText(String.valueOf(consultanCliente.getClienteId()));
                        cajanombre.setText(consultanCliente.getNombre());
                        cajaapellido.setText(consultanCliente.getApellidos());
                        cajadireccion.setText(consultanCliente.getDireccion());
                        prueba=1;
                    }catch (Exception ex){
                        prueba=0;
                    }

                    //limpiarcajas de texto
                    cajanombre.clear();
                    cajaid.clear();
                    cajadireccion.clear();

                    if(prueba==1){
                        Alert alerta = new Alert(AlertType.INFORMATION);
                        alerta.setTitle("Operación exitosa");
                        alerta.setHeaderText("Se logro encontrar clientes");
                        alerta.setContentText(null);
                        alerta.showAndWait();
                    }else{
                        Alert alerta = new Alert(AlertType.ERROR);
                        alerta.setTitle("Operación fallida");
                        alerta.setHeaderText("No se logro encontrar cliente");
                        alerta.setContentText(null);
                        alerta.showAndWait();
                    }
                }


            }
        });

        /////////////Consultta id, al precionar el boton enra
        consultarid.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Cliente nvCliente;
                //si cajaid=0 alertar no existentte
                if (cajaid.getText().length()==0){
                    Alert alerta = new Alert(AlertType.ERROR);
                    alerta.setTitle("Operación fallida");
                    alerta.setHeaderText("No tienes la caja de id llena");
                    alerta.setContentText(null);
                    alerta.showAndWait();
                }else{//si cajaid = 1 + entonces, hagarra la informacion de texfil
                    String id =cajaid.getText();
                    nvCliente=opCliente.consultaClienteid(id);//hace llamada al metodo consulta iden la clase operaciones cliente
                    if(nvCliente!=null){//el metodo regresa diferente a nulo llena los texfil con la informacion del cliente que recibio
                        cajaid.setText(String.valueOf(nvCliente.getClienteId()));
                        cajanombre.setText(nvCliente.getNombre());
                        cajaapellido.setText(nvCliente.getApellidos());
                        cajadireccion.setText(nvCliente.getDireccion());
                    }else{//de otra forma alerta que no encontro cliente con ese numero de Id
                        Alert alerta = new Alert(AlertType.ERROR);
                        alerta.setTitle("Operación fallida");
                        alerta.setHeaderText("No se logro el encontrar al cliente");
                        alerta.setContentText(null);
                        alerta.showAndWait();
                    }
                }

            }
        });
        actua.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (cajaid.getText().length()==0&cajanombre.getText().length()==0&cajaapellido.getText().length()==0&cajadireccion.getText().length()==0){
                    Alert alerta = new Alert(AlertType.ERROR);
                    alerta.setTitle("Operación fallida");
                    alerta.setHeaderText("No tiene todas las cajas llenas");
                    alerta.setContentText(null);
                    alerta.showAndWait();
                }else{
                    String id =cajaid.getText();
                    String nom =cajanombre.getText();
                    String apell=cajaapellido.getText();
                    String direcc=cajadireccion.getText();
                    int prueba=opCliente.actualizarCliente(id,nom,apell,direcc);
                    if(prueba!=0){
                        Alert alerta = new Alert(AlertType.INFORMATION);
                        alerta.setTitle("Operación exitosa");
                        alerta.setHeaderText("Se logro el actualizar al cliente");
                        alerta.setContentText(null);
                        alerta.showAndWait();
                    }else{
                        Alert alerta = new Alert(AlertType.ERROR);
                        alerta.setTitle("Operación fallida");
                        alerta.setHeaderText("No se logro el actualizar al cliente");
                        alerta.setContentText(null);
                        alerta.showAndWait();
                    }
                }

            }
        });

        principal.setCenter(grid);
        primaryStage.show();

    }



    public GridPane addGrid(Label nom,Label ape,Label direc,Button actua,TextField cajanombre,TextField cajaapellido,TextField cajadireccion,Button guar,Button sali,Button borra,Button consultar,Button consultarid,Button consultarname,Label consusub,Label id,TextField cajaid,TableView table){
        GridPane grid =new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(60, 60, 40, 60));
        grid.setStyle("-fx-background-color: #B88DAE;");
        //textos
        grid.add(id, 5, 7);
        grid.add(nom, 5, 10);
        grid.add(ape, 5, 13);
        grid.add(direc, 5, 16);
        //cajas
        grid.add(cajanombre, 10,10);
        grid.add(cajaapellido, 10,13);
        grid.add(cajadireccion, 10,16);
        grid.add(cajaid, 10, 7);
        //botones
        grid.add(consusub, 10, 2);
        grid.add(actua, 12, 10);
        grid.add(guar, 11, 26);
        grid.add(sali, 10, 26);
        grid.add(borra, 12, 13);
        grid.add(consultar, 12, 16);
        grid.add(consultarname,12,23);
        grid.add(consultarid, 12, 7);
        grid.add(table,10,20);
        return grid;
    }
    public GridPane addGrid2(TextField cajanombre,TextField cajaapellido,TextField cajadireccion,Button guar,Button sali,Label agresub,Label nom,Label ape,Label direc){
        GridPane grid =new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(40, 40, 20, 40));
        grid.setStyle("-fx-background-color: #B88DAE;");
        //cajas de texto
        grid.add(cajanombre, 10,10);
        grid.add(cajaapellido, 10,13);
        grid.add(cajadireccion, 10,17);

        //coordenadas
        grid.add(guar, 10, 20);
        grid.add(sali, 10, 23);
        grid.add(agresub, 10, 2);
        grid.add(nom, 5, 10);
        grid.add(ape, 5, 13);
        grid.add(direc, 5, 16);
        return grid;
    }
    public static void main(String[] args) {
        launch(args);
    }
}