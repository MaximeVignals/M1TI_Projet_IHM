/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lnh_manager;

import java.net.URL;
import java.util.ResourceBundle;
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

    Runnable chrono = new Chrono();
    Thread tChrono = new Thread(chrono);
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
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ChronoStarted = false;
        ChronoPaused = false;
    }    

    @FXML
    private int chronoPlayPause(MouseEvent event) {
        if(!ChronoStarted){
            ChronoStarted = true;
            tChrono.start();
            return 0;
        }
        if(ChronoPaused){
            tChrono.resume();
            ChronoPaused = false;
        }else{
            tChrono.suspend();
            ChronoPaused = true;
        }
        return 0;
        
    }
    
}
