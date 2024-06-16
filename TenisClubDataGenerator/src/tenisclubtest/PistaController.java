/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tenisclubtest;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Cell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Booking;
import model.ClubDAOException;
import model.Court;
import model.Member;
import static tenisclubtest.TCDataGenerator.club;

/**
 * FXML Controller class
 *
 * @author MVEGICAS
 */
public class PistaController implements Initializable {

    
    
    
    
    @FXML
    private TextField nombre;
    @FXML
    private TextField telefono;
    @FXML
    private TextField apellido;
    @FXML
    private TextField tarjeta;
    @FXML
    private TextField usuario;
    @FXML
    private TextField cvc;
    @FXML
    private PasswordField contraseña;
    @FXML
    private Button modificarDatosBoton;
    @FXML
    private Button modificarAvatarBoton;
    private Member miembro;
    @FXML
    private ImageView imagen;
    @FXML
    private ListView<Booking> reservas1;
    @FXML
    private DatePicker calendario1;
    @FXML
    private Button reservar1Boton;
    @FXML
    private ListView<Booking> reservas2;
    @FXML
    private DatePicker calendario2;
    @FXML
    private Button reservar2Boton;
    @FXML
    private ListView<Booking> reservas3;
    @FXML
    private DatePicker calendario3;
    @FXML
    private Button reservar3Boton;
    @FXML
    private ListView<Booking> reservas4;
    @FXML
    private DatePicker calendario4;
    @FXML
    private Button reservar4Boton;
    @FXML
    private ListView<Booking> reservas5;
    @FXML
    private DatePicker calendario5;
    @FXML
    private Button reservar5Boton;
    @FXML
    private ListView<Booking> reservas6;
    @FXML
    private DatePicker calendario6;
    @FXML
    private Button reservar6Boton;
    
    @FXML
    private Button anularBoton;
    private ObservableList<Booking> datos = null;
    private List<Booking> lista;
    private ArrayList<Booking> listaTot;
    
    @FXML
    private Text textoNoReservas;
    
    
    private LocalDate hoy;
    private LocalDate hoy1;
    private LocalDate hoy2;
    private LocalDate hoy3;
    private LocalDate hoy4;
    private LocalDate hoy5;
    private LocalDate hoy6;
    private LocalDateTime ahora;
    private LocalDate diaReserva;
    private LocalTime horaReserva;
    
    
    @FXML
    private TableView<Booking> tablaMisReservas;
    @FXML
    private TableColumn<Booking,LocalDate> dia;
    @FXML
    private TableColumn<Booking,LocalTime> hora;
    @FXML
    private TableColumn<Booking,Court> pista;
    @FXML
    private TableColumn<Booking,Boolean> pagado;
    private ObservableList<Booking> misdatos = null;
    
    
    
    
    
    @FXML
    private Button cerrarSesion1Boton;
    @FXML
    private Button cerrarSesion2Boton;
    @FXML
    private Text textooPrueba;
    @FXML
    private ImageView ojito;
    @FXML
    private TextField contraseñaMostrada;
    
    
    
   
   
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        textoNoReservas.setText("NO HAS RESERVADO NINGUNA PISTA");
        
        hoy = LocalDate.now();
        hoy1 = LocalDate.now();
        hoy2 = LocalDate.now();
        hoy3 = LocalDate.now();
        hoy4 = LocalDate.now();
        hoy5 = LocalDate.now();
        hoy6 = LocalDate.now();
        
        
        calendario1.setValue(hoy);
        calendario2.setValue(hoy);
        calendario3.setValue(hoy);
        calendario4.setValue(hoy);
        calendario5.setValue(hoy);
        calendario6.setValue(hoy);
        

        
        mostrarListView("Pista 1", hoy );
        mostrarListView("Pista 2", hoy );
        mostrarListView("Pista 3", hoy );
        mostrarListView("Pista 4", hoy );
        mostrarListView("Pista 5", hoy );
        mostrarListView("Pista 6", hoy );
        
        

        reservar1Boton.disableProperty().bind(Bindings.equal(-1,reservas1.getSelectionModel().selectedIndexProperty()));
        reservar2Boton.disableProperty().bind(Bindings.equal(-1,reservas2.getSelectionModel().selectedIndexProperty()));
        reservar3Boton.disableProperty().bind(Bindings.equal(-1,reservas3.getSelectionModel().selectedIndexProperty()));    
        reservar4Boton.disableProperty().bind(Bindings.equal(-1,reservas4.getSelectionModel().selectedIndexProperty()));
        reservar5Boton.disableProperty().bind(Bindings.equal(-1,reservas5.getSelectionModel().selectedIndexProperty()));
        reservar6Boton.disableProperty().bind(Bindings.equal(-1,reservas6.getSelectionModel().selectedIndexProperty()));
        
    }    
    
    public void introducirMiembro(Member m){  //MODIFICAMOS SEGUN LA ENTRADA DE MIEMBRO
        miembro = m;
        nombre.setText(miembro.getName());
        apellido.setText(miembro.getSurname());
        telefono.setText(miembro.getTelephone());
        usuario.setText(miembro.getNickName());
        imagen.setImage(miembro.getImage());
        contraseña.setText(miembro.getPassword());
        //contraseña.setVisible(true);
        
        mostrarTableView();
        
        if(club.hasCreditCard(miembro.getNickName())){
            tarjeta.setText(miembro.getCreditCard());
            cvc.setText(Integer.toString(miembro.getSvc()));
        } else {
            tarjeta.setPromptText("NO SE HA REGISTRADO TARJETA");
            cvc.setPromptText("NO TARJETA");
            
        }
    }

    @FXML
    private void modificarDatos(ActionEvent event) {
        
        
        
        if(!usuario.getText().equals(miembro.getNickName())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("ERROR EN USUARIO");
            alert.setContentText("No se puede modificar el usuario");
            alert.showAndWait();
            usuario.setText(miembro.getNickName());
        }
        
        miembro.setName(nombre.getText());
        miembro.setSurname(apellido.getText());
        
        miembro.setImage(imagen.getImage());
        usuario.setText(miembro.getNickName());
        
        if(contraseña.getText().length()<7  || !( contraseña.getText()!= null && contraseña.getText().matches("[0-9a-zA-Z]+") )){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("ERROR EN CONTRASEÑA");
            alert.setContentText("La contraseña debe contener mas de 6 numeros/letras");
            alert.showAndWait();
            contraseña.setText(miembro.getPassword());
                    
        }else{
            miembro.setPassword(contraseña.getText());
        }
        
        if(telefono.getText().length()!=9  || !( telefono.getText()!= null && telefono.getText().matches("[0-9]+") )){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("ERROR EN TELEFONO");
            alert.setContentText("El teléfono debe contener 9 dígitos");
            alert.showAndWait();
            telefono.setText(miembro.getTelephone());
                    
        }else{
            miembro.setTelephone(telefono.getText());
        }
        
        if(!club.hasCreditCard(miembro.getNickName())){
            
            if((!tarjeta.getText().equals(""))&&(!cvc.getText().equals(""))){
                if(tarjeta.getText().length()==19 && ( tarjeta.getText()!= null && tarjeta.getText().matches("[0-9 ]+") )
                        && cvc.getText().length()==3 && ( cvc.getText()!= null && cvc.getText().matches("[0-9]+") ) ) {
                
                    miembro.setCreditCard(tarjeta.getText());
                    miembro.setSvc(Integer.parseInt(cvc.getText()));
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("INFORMACIÓN");
                    alert.setHeaderText("TARJETA AÑADIDA");
                    alert.setContentText("Ha añadido una tarjeta de credito");
                    alert.showAndWait();
                    
                    
                    
                }else if(tarjeta.getText().length()!=19 || ! (tarjeta.getText()!= null && tarjeta.getText().matches("[0-9 ]+")) ){
                    
                    
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText("ERROR EN EL Nº DE LA TARJETA");
                    alert.setContentText("Formato correcto: XXXX XXXX XXXX XXXX (siendo X un numero)");
                    alert.showAndWait();
                    tarjeta.clear();
                    tarjeta.setPromptText("NO SE HA REGISTRADO TARJETA");
                    cvc.clear();
                    cvc.setPromptText("NO TARJETA");
               }else if(cvc.getText().length()!=3 || !( cvc.getText()!= null && cvc.getText().matches("[0-9]+") )){ 
                   
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText("ERROR EN EL CVC");
                    alert.setContentText("Formato correcto: XXX (siendo X un numero)");
                    alert.showAndWait();
                    tarjeta.clear();
                    tarjeta.setPromptText("NO SE HA REGISTRADO TARJETA");
                    cvc.clear();
                    cvc.setPromptText("NO TARJETA");
               }
            }else if((!tarjeta.getText().isEmpty())||(!cvc.getText().isEmpty())){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("ERROR AL AÑADIR TARJETA");
                alert.setContentText("Añadir Nº tarjeta y el CVC a la vez");
                alert.showAndWait();
                tarjeta.clear();
                tarjeta.setPromptText("NO SE HA REGISTRADO TARJETA");
                cvc.clear();
                cvc.setPromptText("NO TARJETA");
            }
            
           
        } else {
            
            
            
            if(tarjeta.getText().length()!=19 || !( tarjeta.getText()!= null && tarjeta.getText().matches("[0-9 ]+") ) ) {
                
                
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("ERROR EN EL Nº DE LA TARJETA");
                alert.setContentText("Formato correcto: XXXX XXXX XXXX XXXX (siendo X un numero)");
                alert.showAndWait();
                tarjeta.setText(miembro.getCreditCard());
            }else{ 
                miembro.setCreditCard(tarjeta.getText());
            }
            
            if(cvc.getText().length()!=3 || !( cvc.getText()!= null && cvc.getText().matches("[0-9]+") ) ) {
                
                
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("ERROR EN EL CVC");
                alert.setContentText("Formato correcto: XXX (siendo X un numero)");
                alert.showAndWait();
                cvc.setText(Integer.toString(miembro.getSvc()));
            }else{ 
                miembro.setSvc(Integer.parseInt(cvc.getText()));
            }
            
        }
        
        Alert cambios = new Alert(Alert.AlertType.INFORMATION);
        cambios.setTitle("INFORMACIÓN");
        cambios.setHeaderText("CAMBIOS GUARDADOS");
        cambios.setContentText("Han quedado registrados los cambios");
        cambios.showAndWait();
        
  
    }
    
    @FXML
    private void modificarAvatar(ActionEvent event) throws IOException {
        
        
         FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/tenisclubtest/Imagen.fxml"));
        Parent root = miCargador.load();
        ImagenController controladorImagen = miCargador.getController();

        // acceso al controlador de datos persona
        controladorImagen.introducirImagen(imagen.getImage());
        Scene scene = new Scene(root);
        Stage stage = new Stage();    //(Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Selecciona tu avatar");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        if (controladorImagen.isOKPressed()) {
            imagen.setImage(controladorImagen.getImagen());
            miembro.setImage(controladorImagen.getImagen());
        }
        

    }

    @FXML
    private void reservar1(ActionEvent event) throws ClubDAOException {
        
        ListView<Booking> aux = reservas1;
        DatePicker cal = calendario1;
        Booking reserva = aux.getSelectionModel().getSelectedItem();
        int indice = aux.getSelectionModel().getSelectedIndex();
  
        if(reserva == null){
            
            if((indice >= 2 && aux.getItems().get(indice-1)!=null && aux.getItems().get(indice-1).getMember().equals(miembro)
                    && aux.getItems().get(indice-2)!=null && aux.getItems().get(indice-2).getMember().equals(miembro))||(indice <= 10 && aux.getItems().get(indice+1)!=null && aux.getItems().get(indice+1).getMember().equals(miembro)
                    && aux.getItems().get(indice+2)!=null && aux.getItems().get(indice+2).getMember().equals(miembro)))
            {
                
                mostrarError2Reservadas(); 
            }
            else{
            ahora = LocalDateTime.now(); 
            diaReserva = cal.getValue();
            horaReserva = LocalTime.of(9,0).plusHours(aux.getSelectionModel().getSelectedIndex());
            boolean pagado = miembro.checkHasCreditInfo();

            reserva = club.registerBooking(ahora, diaReserva, horaReserva, pagado, club.getCourt("Pista 1"), miembro);
            mostrarListView("Pista 1", diaReserva);
            mostrarTableView ();
            }
            
        }else{
            mostrarErrorYaReservada();

        }
        
    }

    @FXML
    private void reservar2(ActionEvent event) throws ClubDAOException {
        
        ListView<Booking> aux = reservas2;
        DatePicker cal = calendario2;
        Booking reserva = aux.getSelectionModel().getSelectedItem();
        int indice = aux.getSelectionModel().getSelectedIndex();
  
        if(reserva == null){
            
            if(indice >= 2 && aux.getItems().get(indice-1)!=null && aux.getItems().get(indice-1).getMember().equals(miembro)
                    && aux.getItems().get(indice-2)!=null && aux.getItems().get(indice-2).getMember().equals(miembro))
            {
                
                mostrarError2Reservadas(); 
            }
            else{
            ahora = LocalDateTime.now(); 
            diaReserva = cal.getValue();
            horaReserva = LocalTime.of(9,0).plusHours(aux.getSelectionModel().getSelectedIndex());
            boolean pagado = miembro.checkHasCreditInfo();

            reserva = club.registerBooking(ahora, diaReserva, horaReserva, pagado, club.getCourt("Pista 2"), miembro);
            mostrarListView("Pista 2", diaReserva);
            mostrarTableView ();
            }
            
        }else{
            mostrarErrorYaReservada();

        }
    }
    
    
    
     @FXML
    private void reservar3(ActionEvent event) throws ClubDAOException {
        
        ListView<Booking> aux = reservas3;
        DatePicker cal = calendario3;
        Booking reserva = aux.getSelectionModel().getSelectedItem();
        int indice = aux.getSelectionModel().getSelectedIndex();
  
        if(reserva == null){
            
            if(indice >= 2 && aux.getItems().get(indice-1)!=null && aux.getItems().get(indice-1).getMember().equals(miembro)
                    && aux.getItems().get(indice-2)!=null && aux.getItems().get(indice-2).getMember().equals(miembro))
            {
                
                mostrarError2Reservadas(); 
            }
            else{
            ahora = LocalDateTime.now(); 
            diaReserva = cal.getValue();
            horaReserva = LocalTime.of(9,0).plusHours(aux.getSelectionModel().getSelectedIndex());
            boolean pagado = miembro.checkHasCreditInfo();

            reserva = club.registerBooking(ahora, diaReserva, horaReserva, pagado, club.getCourt("Pista 3"), miembro);
            mostrarListView("Pista 3", diaReserva);
            mostrarTableView ();
            }
            
        }else{
            mostrarErrorYaReservada();

        }
    }

    @FXML
    private void reservar4(ActionEvent event) throws ClubDAOException {
        
        ListView<Booking> aux = reservas4;
        DatePicker cal = calendario4;
        Booking reserva = aux.getSelectionModel().getSelectedItem();
        int indice = aux.getSelectionModel().getSelectedIndex();
  
        if(reserva == null){
            
            if(indice >= 2 && aux.getItems().get(indice-1)!=null && aux.getItems().get(indice-1).getMember().equals(miembro)
                    && aux.getItems().get(indice-2)!=null && aux.getItems().get(indice-2).getMember().equals(miembro))
            {
                
                mostrarError2Reservadas(); 
            }
            else{
            ahora = LocalDateTime.now(); 
            diaReserva = cal.getValue();
            horaReserva = LocalTime.of(9,0).plusHours(aux.getSelectionModel().getSelectedIndex());
            boolean pagado = miembro.checkHasCreditInfo();

            reserva = club.registerBooking(ahora, diaReserva, horaReserva, pagado, club.getCourt("Pista 4"), miembro);
            mostrarListView("Pista 4", diaReserva);
            mostrarTableView ();
            }
            
        }else{
            mostrarErrorYaReservada();

        }
    }

    @FXML
    private void reservar5(ActionEvent event) throws ClubDAOException {
        
        ListView<Booking> aux = reservas5;
        DatePicker cal = calendario5;
        Booking reserva = aux.getSelectionModel().getSelectedItem();
        int indice = aux.getSelectionModel().getSelectedIndex();
  
        if(reserva == null){
            
            if(indice >= 2 && aux.getItems().get(indice-1)!=null && aux.getItems().get(indice-1).getMember().equals(miembro)
                    && aux.getItems().get(indice-2)!=null && aux.getItems().get(indice-2).getMember().equals(miembro))
            {
                
                mostrarError2Reservadas(); 
            }
            else{
            ahora = LocalDateTime.now(); 
            diaReserva = cal.getValue();
            horaReserva = LocalTime.of(9,0).plusHours(aux.getSelectionModel().getSelectedIndex());
            boolean pagado = miembro.checkHasCreditInfo();

            reserva = club.registerBooking(ahora, diaReserva, horaReserva, pagado, club.getCourt("Pista 5"), miembro);
            mostrarListView("Pista 5", diaReserva);
            mostrarTableView ();
            }
            
        }else{
            mostrarErrorYaReservada();

        }
    }

    @FXML
    private void reservar6(ActionEvent event) throws ClubDAOException {
        
        ListView<Booking> aux = reservas6;
        DatePicker cal = calendario6;
        Booking reserva = aux.getSelectionModel().getSelectedItem();
        int indice = aux.getSelectionModel().getSelectedIndex();
  
        if(reserva == null){
            
            if(indice >= 2 && aux.getItems().get(indice-1)!=null && aux.getItems().get(indice-1).getMember().equals(miembro)
                    && aux.getItems().get(indice-2)!=null && aux.getItems().get(indice-2).getMember().equals(miembro))
            {
                
                mostrarError2Reservadas(); 
            }
            else{
            ahora = LocalDateTime.now(); 
            diaReserva = cal.getValue();
            horaReserva = LocalTime.of(9,0).plusHours(aux.getSelectionModel().getSelectedIndex());
            boolean pagado = miembro.checkHasCreditInfo();

            reserva = club.registerBooking(ahora, diaReserva, horaReserva, pagado, club.getCourt("Pista 6"), miembro);
            mostrarListView("Pista 6", diaReserva);
            mostrarTableView ();
            }
            
            
            
        }else{
            mostrarErrorYaReservada();

        }
    }
    
    
    
    @FXML
    private void cambiarFecha1(ActionEvent event) {
        
        hoy1 = calendario1.getValue();
        mostrarListView("Pista 1",hoy1);
    }

    @FXML
    private void cambiarFecha2(ActionEvent event) {
        
        hoy2 = calendario2.getValue();
        mostrarListView("Pista 2",hoy2);
    }

    @FXML
    private void cambiarFecha3(ActionEvent event) {
        
        hoy3 = calendario3.getValue();
        mostrarListView("Pista 3",hoy3);
    }

    @FXML
    private void cambiarFecha4(ActionEvent event) {
        
        hoy4 = calendario4.getValue();
        mostrarListView("Pista 4",hoy4);
    }
    
    @FXML
    private void cambiarFecha5(ActionEvent event) {
        
        hoy5 = calendario5.getValue();
        mostrarListView("Pista 5",hoy5);
    }

    @FXML
    private void cambiarFecha6(ActionEvent event) {
        
        hoy6 = calendario6.getValue();
        mostrarListView("Pista 6",hoy6);
    }

    @FXML
    private void soltarOjito(MouseEvent event) {
        contraseñaMostrada.clear();
        contraseñaMostrada.setVisible(false);
        contraseña.setVisible(true);
    }

    @FXML
    private void presionarOjito(MouseEvent event) {
        
        contraseña.setVisible(false);
        contraseñaMostrada.setVisible(true);
        contraseñaMostrada.setText(contraseña.getText());
    }

    

    

   

    
    
    
    
    private  class reservarListCell extends ListCell<Booking> {

        @Override
        protected void updateItem(Booking t, boolean bln)
        { super.updateItem(t, bln);
        
        if(bln ) setText("");
        else if (t==null ){
            
            setText("LIBRE");
            
            String css = this.getClass().getResource("/tenisclubtest/celda_libre.css").toExternalForm();
            getStylesheets().add(css);
            
            
            
        }
        else {
            setText("RESERVADA POR: "+ t.getMember().getNickName());
            String css = this.getClass().getResource("/tenisclubtest/celda_ocupada.css").toExternalForm();
            getStylesheets().add(css);
        }
        }
    }
    
    private  void mostrarListView(String nomPista, LocalDate dia){
        
        
        lista = club.getCourtBookings(nomPista, dia);
        Iterator<Booking> it = lista.iterator();
        
        String prueba = "";
        while(it.hasNext()){
            prueba = prueba + it.next().getFromTime().toString();
        }
        
        
        it = lista.iterator();
        listaTot= new ArrayList<Booking>(13);
        
        
        LocalTime p = null;
        Booking b = null;
        
        if(it.hasNext()){
            b = it.next();
            p = b.getFromTime();
        } 
        
        
        
        for(int i=0; i<13;i++){
            
            LocalTime aux = LocalTime.of(9,0).plusHours(i);
            if(b==null){ listaTot.add(null);}
            else if(p.equals(aux)){
                listaTot.add(b);
                if(it.hasNext()){
                      b = it.next();
                      p=b.getFromTime();
                 }
            }
            else listaTot.add(null);
        }
        
        datos = FXCollections.observableArrayList(listaTot);
        
        switch(nomPista){
            case "Pista 1": reservas1.setItems(datos);
                    reservas1.setCellFactory(c-> new reservarListCell()); break;
            case "Pista 2": reservas2.setItems(datos); 
                    reservas2.setCellFactory(c-> new reservarListCell()); break;
            case "Pista 3": reservas3.setItems(datos);
                    reservas3.setCellFactory(c-> new reservarListCell()); break;
            case "Pista 4": reservas4.setItems(datos);
                    reservas4.setCellFactory(c-> new reservarListCell()); break;
            case "Pista 5": reservas5.setItems(datos);
                    reservas5.setCellFactory(c-> new reservarListCell()); break;
            case "Pista 6": reservas6.setItems(datos);
                    reservas6.setCellFactory(c-> new reservarListCell()); break;
            
                
        }
        
    }
    
    private void mostrarErrorYaReservada(){
        
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText("PISTA YA RESERVADA");
        alert.setContentText("LA PISTA YA ESTÁ RESERVADA PARA ESA HORA");
        alert.showAndWait();
    }
    
    private void mostrarError2Reservadas(){
        
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText("SUPERADO TOPE");
        alert.setContentText("Solo se pueden reservar dos horas seguidas.");
        alert.showAndWait();
    }
        
    
    private  void mostrarTableView (){
        
        //try{
        
        textoNoReservas.setText("NO HAS RESERVADO NINGUNA PISTA");
        List<Booking> misReser =club.getUserBookings(miembro.getNickName());
        
        Iterator<Booking> it = misReser.iterator();
        ArrayList<Booking> misReservas = new ArrayList(10);
        
        if(it.hasNext()) textoNoReservas.setText("");
        for(int i=0; i<10; i++){
                if(it.hasNext()) misReservas.add(it.next());
        }
        
        misdatos = FXCollections.observableArrayList(misReservas);
        
        dia.setCellValueFactory(new PropertyValueFactory<Booking,LocalDate>("madeForDay"));
        hora.setCellValueFactory(new PropertyValueFactory<Booking,LocalTime>("fromTime"));
        pista.setCellValueFactory(new PropertyValueFactory<Booking,Court>("court"));
        pagado.setCellValueFactory(new PropertyValueFactory<Booking,Boolean>("paid"));
        //pagado.setCellValueFactory(Fila->new SimpleBooleanProperty(Fila.getValue().getPaid()));
        
        tablaMisReservas.setItems(misdatos);
        
        pista.setCellFactory(c-> new reservarTableCellPista());
        pagado.setCellFactory(c-> new reservarTableCellPagado());
        
        
        //}catch(Exception e){ textoNoReservas.setText("NO HAS RESERVADO NINGUNA PISTA");}
    }
    
    
    private  class reservarTableCellPista extends TableCell<Booking,Court> {

        @Override
        protected void updateItem(Court c, boolean bln)
        { super.updateItem(c, bln);
        
            if(bln || c==null) setText("");
            else {
                setText(c.getName());
                
            }
        }
    }
    
    
    private  class reservarTableCellPagado extends TableCell<Booking,Boolean> {

        @Override
        protected void updateItem(Boolean p, boolean bln) {
            super.updateItem(p, bln); //To change body of generated methods, choose Tools | Templates.
        
        
            if(bln || p== null){
                setText("");
                
                
            }

            else if(p){
                setText("Pagado");
                //NO VA 
                
            }
            
            else{
                setText("No Pagado");
                
                //NO VA
                
            }

        }
    }
    
    @FXML
    private void anular(ActionEvent event) throws ClubDAOException {
        
        
       
        Booking reserva = tablaMisReservas.getSelectionModel().getSelectedItem();
        
        LocalDateTime horaReserva = LocalDateTime.of(reserva.getMadeForDay(), reserva.getFromTime());
        
       
        
        if(horaReserva.isBefore(LocalDateTime.now().plusDays(1))){
            
            
            
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR ANULACIÓN");
        alert.setHeaderText("SUPERADO TOPE DE ANULACIÓN SUPERADO");
        alert.setContentText("Solo se puede anular con 24h de antelación.");
        alert.showAndWait();
            
        }else{
        String pista = reserva.getCourt().getName();
        club.removeBooking(reserva);
        mostrarTableView();
        
        switch(pista){
            case "Pista 1": mostrarListView(pista,hoy1); break;
            case "Pista 2": mostrarListView(pista,hoy2); break;
            case "Pista 3": mostrarListView(pista,hoy3); break;
            case "Pista 4": mostrarListView(pista,hoy4); break;
            case "Pista 5": mostrarListView(pista,hoy5); break;
            case "Pista 6": mostrarListView(pista,hoy6); break;
        }
        mostrarListView(pista, hoy);
        }
    }
    
    @FXML
    private void cerrarSesion1(ActionEvent event) throws IOException {
        
        cerrarSesion(event);
        
    }

    @FXML
    private void cerrarSesion2(ActionEvent event) throws IOException {
        
        cerrarSesion(event);
    }
    
    private void cerrarSesion(ActionEvent event) throws IOException{
        
        FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/tenisclubtest/Inicio.fxml"));
            
            
            Parent root = miCargador.load();
            Scene scene = new Scene(root);
            Stage stage =  (Stage) ((Node)event.getSource()).getScene().getWindow(); 
            stage.setScene(scene);
            stage.setTitle("GreeBall");
        
    }
    
        
    
  
}
