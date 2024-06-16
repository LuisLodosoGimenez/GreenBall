/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tenisclubtest;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import model.Booking;
import static tenisclubtest.TCDataGenerator.club;

/**
 * FXML Controller class
 *
 * @author MVEGICAS
 */
public class DisponibilidadController implements Initializable {

    @FXML
    private DatePicker calendario1;
    @FXML
    private DatePicker calendario2;
    @FXML
    private DatePicker calendario3;
    @FXML
    private DatePicker calendario4;
    @FXML
    private DatePicker calendario5;
    @FXML
    private DatePicker calendario6;
    @FXML
    private Button volverBoton;
    @FXML
    private ListView<Booking> reservas1;
    @FXML
    private ListView<Booking> reservas2;
    @FXML
    private ListView<Booking> reservas3;
    @FXML
    private ListView<Booking> reservas4;
    @FXML
    private ListView<Booking> reservas5;
    @FXML
    private ListView<Booking> reservas6;
    private LocalDate hoy;
    private List<Booking> lista;
    private ArrayList<Booking> listaTot;
    private ObservableList<Booking> datos = null;
    @FXML
    private Button volverBoton1;
    @FXML
    private Button volverBoton2;
    @FXML
    private Button volverBoton3;
    @FXML
    private Button volverBoton4;
    @FXML
    private Button volverBoton5;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        hoy = LocalDate.now();
        
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
        
        

    }    

    @FXML
    private void cambiarFecha1(ActionEvent event) {
        
    }

    @FXML
    private void cambiarFecha2(ActionEvent event) {
    }

    @FXML
    private void cambiarFecha3(ActionEvent event) {
    }

    @FXML
    private void cambiarFecha4(ActionEvent event) {
    }

    @FXML
    private void cambiarFecha5(ActionEvent event) {
    }

    @FXML
    private void cambiarFecha6(ActionEvent event) {
    }
    
    @FXML
    private void volver(ActionEvent event) throws IOException {
        FXMLLoader volverInicio = new FXMLLoader(getClass().getResource("/tenisclubtest/Inicio.fxml"));
        Parent root = volverInicio.load();
        // acceso al controlador de datos persona
        Scene scene = new Scene(root);
        
        TCDataGenerator.stage.setScene(scene);
        TCDataGenerator.stage.setTitle("GreenBall");
        
        TCDataGenerator.stage.show();
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
                    reservas1.setCellFactory(c-> new DisponibilidadController.reservarListCell()); break;
            case "Pista 2": reservas2.setItems(datos); 
                    reservas2.setCellFactory(c-> new DisponibilidadController.reservarListCell()); break;
            case "Pista 3": reservas3.setItems(datos);
                    reservas3.setCellFactory(c-> new DisponibilidadController.reservarListCell()); break;
            case "Pista 4": reservas4.setItems(datos);
                    reservas4.setCellFactory(c-> new DisponibilidadController.reservarListCell()); break;
            case "Pista 5": reservas5.setItems(datos);
                    reservas5.setCellFactory(c-> new DisponibilidadController.reservarListCell()); break;
            case "Pista 6": reservas6.setItems(datos);
                    reservas6.setCellFactory(c-> new DisponibilidadController.reservarListCell()); break;
            
                
        }
        
    }
    
    private  class reservarListCell extends ListCell<Booking> {

        @Override
        protected void updateItem(Booking t, boolean bln)
        { super.updateItem(t, bln);
        
        if(bln) setText("");
        else if (t==null ){
            
            setText("LIBRE");
            
            String css = this.getClass().getResource("/tenisclubtest/celda_libre_disponibilidad.css").toExternalForm();
            getStylesheets().add(css);
            
            
            
        }
        else {
            setText("RESERVADA");
            String css = this.getClass().getResource("/tenisclubtest/celda_ocupada_disponibilidad.css").toExternalForm();
            getStylesheets().add(css);
        }
        }
    }
    
}
