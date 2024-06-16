package tenisclubtest;

import java.awt.Color;
import java.awt.Rectangle;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.UIManager;
import model.Booking;
import model.Member;
import model.Club;
import model.ClubDAOException;
import model.Court;

/**
 *
 * @author jsoler
 */
public class TCDataGenerator extends Application{

   

    /**
     * This program clean de file club.db and generate ramdom bookings 
     * in the next 5 days
     * 
     * @param args the command line arguments
     */
    
    public static Stage stage;
    public static Club club;
    public static void main(String[] args) throws ClubDAOException, IOException, Exception {

        club= Club.getInstance(); 
        //==================================
        //Clean the file club.db
        //club.setInitialData();
        
        //===================================
        // club data:
        System.out.println("Club name: "+ club.getName());
        for (Court court : club.getCourts()) {
            System.out.println("court:" + court.getName());
        }
        //===================================
        // add simple data:
        club.addSimpleData();
        
        //===================================
        // users        
        for (Member member : club.getMembers()) {
            System.out.println("member:" + member.getName()+ ", "+ member.getNickName());
        }
        
        //===================================
        // bookings now + 2 days
        System.out.println("Bookings after 2 days");
        List<Booking> forDayBookings = club.getForDayBookings(LocalDate.now().plusDays(2));
        for (Booking booking : forDayBookings) {
              System.out.println("booking:" + booking.getMember().getNickName()+
                      ", " + booking.getCourt().getName()+ ", "+
                      booking.getMadeForDay().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)) +
                      ", "+booking.getFromTime().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)));
        }   
        
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        
        this.stage= stage;
        Parent root = FXMLLoader.load(InicioController.class.getResource("/tenisclubtest/Inicio.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("GreenBall");
        stage.show();
    }
    
}