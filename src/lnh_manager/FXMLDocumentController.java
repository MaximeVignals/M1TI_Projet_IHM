/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lnh_manager;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author mvignals
 */
public class FXMLDocumentController implements Initializable {

    boolean ChronoPaused;
    boolean tempsMort;
    long tempsMortDuration;
    
    @FXML
    private AnchorPane pane_homeTeam;
    @FXML
    private AnchorPane pane_home_header;
    @FXML
    private TableView<Player> table_home_team;
    @FXML
    private TableColumn<Player, String> col_Nb_home;
    @FXML
    private TableColumn<Player, String> col_Nom_home;
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
    private TableView<Player> table_visitor_team;
    @FXML
    private TableColumn<Player, String> col_Nb_visitor;
    @FXML
    private TableColumn<Player, String> col_Nom_visitor;
    @FXML
    private Label labelTemps;
    @FXML
    private TableView<Player> table_home_team_out;
    @FXML
    private TableColumn<Player, String> col_Nb_home_out;
    @FXML
    private TableColumn<Player, String> col_Nom_home_out;
    @FXML
    private TableView<Player> table_visitor_team_out;
    @FXML
    private TableColumn<Player, String> col_Nb_visitor_out;
    @FXML
    private TableColumn<Player, String> col_Nom_visitor_out;
    @FXML
    private Rectangle rec_HomeTeam;
    
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
                timestamp += 1000 * deltaT;
                
                if(tempsMort){
                    tempsMortDuration += deltaT;
                    System.out.println(tempsMortDuration);
                }else{
                    time += deltaT;
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
        }
    };
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tempsMort = false;
        ChronoPaused = true;
        initTables();
    }       

    //Function used to initialize tables property and fill them with temp data.
    private void initTables(){
        initColumns();
        fillTables();
    }
    
    //This function's purpose is to make the tables editable by clickling directly on the name or Id of a row.
    private void initColumns(){
        
        //Equipe home - Equipe en jeu
        
        col_Nb_home.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_Nom_home.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        
        col_Nb_home.setCellFactory(TextFieldTableCell.forTableColumn());
        col_Nb_home.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setId(e.getNewValue());
        });
        
        col_Nom_home.setCellFactory(TextFieldTableCell.forTableColumn());
        col_Nom_home.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setNom(e.getNewValue());
        });
        
        //Equipe visitor - Equipe en jeu
        
        col_Nb_visitor.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_Nom_visitor.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        
        col_Nb_visitor.setCellFactory(TextFieldTableCell.forTableColumn());
        col_Nb_visitor.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setId(e.getNewValue());
        });
        
        col_Nom_visitor.setCellFactory(TextFieldTableCell.forTableColumn());
        col_Nom_visitor.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setNom(e.getNewValue());
        });
        
        //Equipe home - Equipe sur le banc
        
        col_Nb_home_out.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_Nom_home_out.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        
        col_Nb_home_out.setCellFactory(TextFieldTableCell.forTableColumn());
        col_Nb_home_out.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setId(e.getNewValue());
        });
        
        col_Nom_home_out.setCellFactory(TextFieldTableCell.forTableColumn());
        col_Nom_home_out.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setNom(e.getNewValue());
        });
        
        //Equipe visitor - Equipe sur le banc
        
        col_Nb_visitor_out.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_Nom_visitor_out.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        
        col_Nb_visitor_out.setCellFactory(TextFieldTableCell.forTableColumn());
        col_Nb_visitor_out.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setId(e.getNewValue());
        });
        
        col_Nom_visitor_out.setCellFactory(TextFieldTableCell.forTableColumn());
        col_Nom_visitor_out.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setNom(e.getNewValue());
        }); 
        
    }
    
    
    //This function is used to initialize the tables.
    private void fillTables(){
        ObservableList<Player> initData = FXCollections.observableArrayList();
        
        for(int i = 0; i<7;i++){
            initData.add(new Player("Nom","ID"));
        }
        
        table_home_team.setItems(initData);
        table_visitor_team.setItems(initData);
        
       initData.remove(5, 7);
       
       table_home_team_out.setItems(initData);
       table_visitor_team_out.setItems(initData);
    }
    
    @FXML
    private void chronoPlayPause(MouseEvent event) {
        if(ChronoPaused){
            timer.start();
            ChronoPaused = false;
        }else{
            timer.stop();
            ChronoPaused = true;
        }
        
    }

    @FXML
    private void tempsMort(MouseEvent event) {
        tempsMort ^= true;
    }

    
}
