/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lnh_manager;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author mvignals
 */
public class FXMLDocumentController implements Initializable {

    Chrono chrono = new Chrono();
    boolean ChronoStarted;
    boolean ChronoPaused;
    
    @FXML
    private AnchorPane pane_homeTeam;
    @FXML
    private AnchorPane pane_home_header;
    @FXML
    private TableView<?> table_home_team;
    @FXML
    private TableColumn<?, ?> col_Nb_home;
    @FXML
    private TableColumn<?, ?> col_Nom_home;
    @FXML
    private TableColumn<?, ?> col_Prenom_home;
    @FXML
    private AnchorPane pane_center;
    @FXML
    private Button btn_startPause;
    @FXML
    private Button btn_reinitialiser;
    @FXML
    private Button btn_tpsMort;
    @FXML
    private Label label_score_home;
    @FXML
    private Label label_score_visitor;
    @FXML
    private AnchorPane pane_visitorTeam;
    @FXML
    private AnchorPane pane_visitor_header;
    @FXML
    private TableView<?> table_visitor_team;
    @FXML
    private TableColumn<?, ?> col_Nb_visitor;
    @FXML
    private TableColumn<?, ?> col_Nom_visitor;
    @FXML
    private TableColumn<?, ?> col_Prenom_visitor;
    @FXML
    private Label labelTemps;
    @FXML
    private TableView<?> table_home_team_out;
    @FXML
    private TableColumn<?, ?> col_Nb_home_out;
    @FXML
    private TableColumn<?, ?> col_Nom_home_out;
    @FXML
    private TableColumn<?, ?> col_Prenom_home_out;
    @FXML
    private TableView<?> table_visitor_team_out;
    @FXML
    private TableColumn<?, ?> col_Nb_visitor_out;
    @FXML
    private TableColumn<?, ?> col_Nom_visitor_out;
    @FXML
    private TableColumn<?, ?> col_Prenom_visitor_out;
    
    
    AnimationTimer timer = new AnimationTimer() {
    private long timestamp;
    private long time = 0;
    private long fraction = 0;

    @Override
    public void start() {
        // current time adjusted by remaining time from last run
        timestamp = System.currentTimeMillis() - fraction;
        super.start();
    }

    @Override
    public void stop() {
        super.stop();
        // save leftover time not handled with the last update
        fraction = System.currentTimeMillis() - timestamp;
    }

    @Override
    public void handle(long now) {
        long newTime = System.currentTimeMillis();
        if (timestamp + 1000 <= newTime) {
            long deltaT = (newTime - timestamp) / 1000;
            time += deltaT;
            timestamp += 1000 * deltaT;
            
            long sec = time % 60;
            long min = time / 60;
            String temps="";
            
            if(min<10){
                temps += "0";
            }
            temps += Long.toString(min);
            temps += ":";
            if(sec<10){
                temps += "0";
            }
            temps += Long.toString(sec);
            
            labelTemps.setText(temps);
        }
    }
};
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ChronoStarted = false;
        ChronoPaused = false;
    }    

    
    @FXML
    private void chronoPlayPause(MouseEvent event) {
        if(!ChronoStarted){
            ChronoStarted = true;
            timer.start();
        }else{
            if(ChronoPaused){
                timer.start();
                ChronoPaused = false;
            }else{
                timer.stop();
                ChronoPaused = true;
            }
        }
        
    }

    
}
