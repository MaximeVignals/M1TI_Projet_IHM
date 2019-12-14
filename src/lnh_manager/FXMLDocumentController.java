/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lnh_manager;

import java.io.IOException;
import lnh_manager.Players.Player;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import lnh_manager.Events.Event;
import lnh_manager.Events.Faute;
import lnh_manager.Events.Passe;
import lnh_manager.Events.Tir;
import lnh_manager.Players.Equipe;

/**
 *
 * @author mvignals
 */
public class FXMLDocumentController implements Initializable {

    boolean ChronoPaused;
    boolean tempsMort;
    long tempsMortDuration;
    int tempsMortRestantHome = 2;
    int tempsMortRestantVisitor = 2;
    int shootingTeam;
    int scoreHome;
    int scoreVisitor;
    int equipeSanction;
    Match match;
    ArrayList <Event> events = new ArrayList<>();
    ArrayList <Player> twominList = new ArrayList<>();

    
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
    @FXML
    private AnchorPane pane_Timer;
    @FXML
    private AnchorPane pane_Start;
    @FXML
    private Button btn_StartMatch;
    @FXML
    private TextField textbox_Nom_Equipe_Home;
    @FXML
    private TextField textbox_Entraineur_Home;
    @FXML
    private TextField textbox_Nom_Equipe_Visitor;
    @FXML
    private TextField textbox_Entraineur_Visitor;
    @FXML
    private Button btn_tpsMort_home;
    @FXML
    private Button btn_tpsMort_visitor;
 
    @FXML
    private Button btn_entre_home;
    @FXML
    private Button btn_sortie_home;
    @FXML
    private Button btn_faute_home;
    @FXML
    private Button btn_faute_visitor;
    @FXML
    private Button btn_2min;
    @FXML
    private Button btn_carton_rouge;
    @FXML
    private Button btn_carton_jaune;
    @FXML
    private Button btn_carton_bleu;
    @FXML
    private AnchorPane pane_Tir;
    @FXML
    private ToggleGroup cadre;
    @FXML
    private ToggleGroup Position;
    @FXML
    private Button btn_entree_visitor;
    @FXML
    private Button btn_sortie_visitor;
    

    @FXML
    private ScrollPane scrollEvent;
    @FXML
    private TextArea eventTextArea;
    @FXML
    private TableColumn<Player, String> col_Jaune_Home;
    @FXML
    private TableColumn<Player, String> col_Rouge_Home;
    @FXML
    private TableColumn<Player, String> col_Jaune_Home_Out;
    @FXML
    private TableColumn<Player, String> col_Rouge_Home_Out;
    @FXML
    private TableColumn<Player, String> col_Jaune_Visitor;
    @FXML
    private TableColumn<Player, String> col_Rouge_Visitor;
    @FXML
    private TableColumn<Player, String> col_Jaune_Visitor_Out;
    @FXML
    private TableColumn<Player, String> col_Rouge_Visitor_out;
    @FXML
    private Button btn_Tir_Home;
    @FXML
    private Button btn_Passe_Home;
    @FXML
    private Button btn_Tir_Visitor;
    @FXML
    private Button btn_Passe_Visitor;
    @FXML
    private Button btn_Sanction_Home;
    @FXML
    private Button btn_Sanction_Visitor;
    @FXML
    private TableColumn<Player, String> col_twomins_home;
    @FXML
    private TableColumn<Player, String> col_twomins_visitor;
    
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
                    if(tempsMortDuration >=5){
                        if(tempsMortRestantHome!=0){
                            btn_tpsMort_home.setDisable(false);
                        }
                        if(tempsMortRestantVisitor!=0){
                            btn_tpsMort_visitor.setDisable(false);
                        }
                        tempsMortDuration = 0;
                        tempsMort = false;
                        btn_startPause.setDisable(false);
                        toggleEvents();
                    }
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
                    if(time == 1800){
                        this.stop();
                        toggleEvents();
                        ChronoPaused = true;
                    }
                    
                    for(Player p : twominList){
                        if(p.getTempsDeuxMin()>0){
                            p.setTempsDeuxMin(p.getTempsDeuxMin() -1 );
                            table_home_team_out.refresh();
                            table_visitor_team_out.refresh();
                        }
                    }
                }
                
            }
        }
    };



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        toggleEvents();
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
        col_Jaune_Home.setCellValueFactory(new PropertyValueFactory<>("nbJaune"));
        col_Jaune_Home.setEditable(false);
        col_Rouge_Home.setCellValueFactory(new PropertyValueFactory<>("nbRouge"));
        col_Rouge_Home.setEditable(false);
        
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
        
        col_Jaune_Visitor.setCellValueFactory(new PropertyValueFactory<>("nbJaune"));
        col_Jaune_Visitor.setEditable(false);
        col_Rouge_Visitor.setCellValueFactory(new PropertyValueFactory<>("nbRouge"));
        col_Rouge_Visitor.setEditable(false);
        
        col_Nb_visitor.setCellFactory(TextFieldTableCell.forTableColumn());
        col_Nb_visitor.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setId(e.getNewValue());
        });
        
        col_Nom_visitor.setCellFactory(TextFieldTableCell.forTableColumn());
        col_Nom_visitor.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setNom(e.getNewValue());
        });
        
        //Equipe home - Equipe sur le banc
        
        col_Jaune_Home_Out.setCellValueFactory(new PropertyValueFactory<>("nbJaune"));
        col_Jaune_Home_Out.setEditable(false);
        col_Rouge_Home_Out.setCellValueFactory(new PropertyValueFactory<>("nbRouge"));
        col_Rouge_Home_Out.setEditable(false);
        col_twomins_home.setCellValueFactory(new PropertyValueFactory<>("tempsDeuxMin"));
        col_twomins_home.setEditable(false);
        
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
        
        col_Jaune_Visitor_Out.setCellValueFactory(new PropertyValueFactory<>("nbJaune"));
        col_Jaune_Visitor_Out.setEditable(false);
        col_Rouge_Visitor_out.setCellValueFactory(new PropertyValueFactory<>("nbRouge"));
        col_Rouge_Visitor_out.setEditable(false);
        
        col_twomins_visitor.setCellValueFactory(new PropertyValueFactory<>("tempsDeuxMin"));
        col_twomins_visitor.setEditable(false);
        
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
        ObservableList<Player> initDataHome = FXCollections.observableArrayList();
        ObservableList<Player> initDataVisitor = FXCollections.observableArrayList();
        
        for(int i = 0; i<7;i++){
            initDataHome.add(new Player("Nom",Integer.toString(i+1)));
            initDataVisitor.add(new Player("Nom",Integer.toString(i+1)));
        }
        table_home_team.setItems(initDataHome);
        table_visitor_team.setItems(initDataVisitor);
        
       initDataHome = FXCollections.observableArrayList();
       initDataVisitor = FXCollections.observableArrayList();
       for(int i = 0; i<5;i++){
           initDataHome.add(new Player("Nom",Integer.toString(i+8)));
           initDataVisitor.add(new Player("Nom",Integer.toString(i+8)));
       }
       table_home_team_out.setItems(initDataHome);
       table_visitor_team_out.setItems(initDataVisitor);
    }
    
    @FXML
    private void chronoPlayPause(MouseEvent event) {
        if(ChronoPaused){
            toggleEvents();
            timer.start();
            ChronoPaused = false;
        }else{
            toggleEvents();
            timer.stop();
            ChronoPaused = true;
        }
        
    }

    @FXML
    private void startMatch(MouseEvent event) {
        pane_Start.setDisable(true);
        pane_Start.setOpacity(0);
        pane_Timer.setDisable(false);
        pane_Timer.setOpacity(1);
        
        table_home_team.setEditable(false);
        table_home_team_out.setEditable(false);
        table_visitor_team.setEditable(false);
        table_visitor_team_out.setEditable(false);
        
        textbox_Entraineur_Home.setEditable(false);
        textbox_Entraineur_Visitor.setEditable(false);
        textbox_Nom_Equipe_Home.setEditable(false);
        textbox_Nom_Equipe_Visitor.setEditable(false);
        
        registerMatch();
        scoreHome = 0;
        scoreVisitor = 0;
    }
    
    private void registerMatch(){
        Equipe homeTeam = new Equipe(textbox_Entraineur_Home.getText(), textbox_Nom_Equipe_Home.getText());
        Equipe visitorTeam = new Equipe(textbox_Entraineur_Visitor.getText(), textbox_Nom_Equipe_Visitor.getText());
        
        textbox_Entraineur_Home.setEditable(false);
        textbox_Nom_Equipe_Home.setEditable(false);
        textbox_Entraineur_Visitor.setEditable(false);
        textbox_Nom_Equipe_Visitor.setEditable(false);
        
        for(int i = 0; i<7;i++){
            homeTeam.addTeamIn(table_home_team.getItems().get(i));
            visitorTeam.addTeamIn(table_visitor_team.getItems().get(i));
        }
        for(int i = 0; i<5;i++){
            homeTeam.addTeamOut(table_home_team_out.getItems().get(i));
            visitorTeam.addTeamOut(table_visitor_team_out.getItems().get(i));
        }
        
        match = new Match(homeTeam, visitorTeam);
    }

    @FXML
    private void tempsMortHome(MouseEvent event) {
        if(tempsMortRestantHome !=0){
            toggleEvents();
            tempsMort = true;
            btn_tpsMort_home.setDisable(true);
            btn_tpsMort_visitor.setDisable(true);
            tempsMortRestantHome --;
            btn_startPause.setDisable(true);
        }
    }

    @FXML
    private void tempsMortVisitor(MouseEvent event) {
        if(tempsMortRestantVisitor !=0){
            toggleEvents();
            tempsMort = true;
            btn_tpsMort_home.setDisable(true);
            btn_tpsMort_visitor.setDisable(true);
            tempsMortRestantVisitor --;
            btn_startPause.setDisable(true);
        }

    }

    @FXML
    private void tirHome(MouseEvent event) throws IOException {
        if(!table_home_team.getSelectionModel().isEmpty()){
            pane_Tir.toFront();
            pane_Tir.setOpacity(1);
            pane_Tir.setDisable(false);
            shootingTeam = 1;
        }
    }

    @FXML
    private void tirVisitor(MouseEvent event) throws IOException {
        if(!table_visitor_team.getSelectionModel().isEmpty()){
            pane_Tir.toFront();
            pane_Tir.setOpacity(1);
            pane_Tir.setDisable(false);
            shootingTeam = 2;           
        }
    }

    @FXML
    private void passeHome(MouseEvent event) {
        if(!table_home_team.getSelectionModel().isEmpty()){
            Player p = table_home_team.getSelectionModel().getSelectedItem();
            Passe passe = new Passe(labelTemps.getText(), p);
            events.add(passe);
            eventTextArea.appendText(passe.toString());
            eventTextArea.appendText("\n");

            table_home_team.getSelectionModel().clearSelection();
            System.out.println(passe.toString());
        }
    }

    @FXML
    private void passeVisitor(MouseEvent event) {
        if(!table_visitor_team.getSelectionModel().isEmpty()){
            Player p = table_visitor_team.getSelectionModel().getSelectedItem();
            Passe passe = new Passe(labelTemps.getText(), p);
            events.add(passe);
            eventTextArea.appendText(passe.toString());
            eventTextArea.appendText("\n");
            table_visitor_team.getSelectionModel().clearSelection();
            System.out.println(passe.toString());      
        }
    }
    
    public void gestionTir(int outcome, int position,int cadrage){
        Player p = new Player();
        switch(shootingTeam){
            case 1:
                p = table_home_team.getSelectionModel().getSelectedItem();
                if(outcome == 0){
                    scoreHome ++;
                    label_score_home.setText(Integer.toString(scoreHome));
                }
                break;
            case 2:
                p = table_visitor_team.getSelectionModel().getSelectedItem();
               if(outcome == 0){
                    scoreVisitor ++;
                    label_score_visitor.setText(Integer.toString(scoreVisitor));
                }
                break;
        }
        
        Tir t = new Tir(outcome, position, cadrage, labelTemps.getText(), p);
        
        events.add(t);
        eventTextArea.appendText(t.toString());
        eventTextArea.appendText("\n");
        System.out.println(t.toString());
    }

    @FXML
    private void tirBut(MouseEvent event) {
        int iCadre = cadre.getToggles().indexOf(cadre.getSelectedToggle());
        int iPosition = Position.getToggles().indexOf(Position.getSelectedToggle());
        
        gestionTir(0,iPosition,iCadre);
        pane_Tir.toBack();
        pane_Tir.setOpacity(0);
        pane_Tir.setDisable(true);
    }

    @FXML
    private void tirArret(MouseEvent event) {
        int iCadre = cadre.getToggles().indexOf(cadre.getSelectedToggle())+1;
        int iPosition = Position.getToggles().indexOf(Position.getSelectedToggle())+1;
        gestionTir(1,iPosition,iCadre);
        pane_Tir.toBack();
        pane_Tir.setOpacity(0);
        pane_Tir.setDisable(true);
    }

    @FXML
    private void tirPoteau(MouseEvent event) {
        int iCadre = cadre.getToggles().indexOf(cadre.getSelectedToggle())+1;
        int iPosition = Position.getToggles().indexOf(Position.getSelectedToggle())+1;
        gestionTir(2,iPosition,iCadre);
        pane_Tir.toBack();
        pane_Tir.setOpacity(0);
        pane_Tir.setDisable(true);
    }

    @FXML
    private void tirRate(MouseEvent event) {
        int iCadre = cadre.getToggles().indexOf(cadre.getSelectedToggle())+1;
        int iPosition = Position.getToggles().indexOf(Position.getSelectedToggle())+1;
        gestionTir(3,iPosition,iCadre);
        pane_Tir.toBack();
        pane_Tir.setOpacity(0);
        pane_Tir.setDisable(true);
    }

    @FXML
    private void entreeHome(MouseEvent event) {
        if(!table_home_team_out.getSelectionModel().isEmpty()){
            Player p = table_home_team_out.getSelectionModel().getSelectedItem();
            if(Integer.parseInt(p.getNbRouge()) != 1 && p.getTempsDeuxMin() == 0 && table_home_team.getItems().size() <7){
                table_home_team_out.getItems().remove(p);
                table_home_team.getItems().add(p);
            }
            table_home_team_out.getSelectionModel().clearSelection();

        }
    }

    @FXML
    private void sortieHome(MouseEvent event) {
        if(!table_home_team.getSelectionModel().isEmpty()){
            Player p = table_home_team.getSelectionModel().getSelectedItem();
            table_home_team.getSelectionModel().clearSelection();
            table_home_team.getItems().remove(p);
            table_home_team_out.getItems().add(p);
        }
    }

    @FXML
    private void entreeVisitor(MouseEvent event) {
        if(!table_visitor_team_out.getSelectionModel().isEmpty()){
            Player p = table_visitor_team_out.getSelectionModel().getSelectedItem();
            if(Integer.parseInt(p.getNbRouge()) !=  1 && p.getTempsDeuxMin() == 0 && table_visitor_team.getItems().size()<7){
                table_visitor_team_out.getItems().remove(p);
                table_visitor_team.getItems().add(p);
            }
            table_visitor_team_out.getSelectionModel().clearSelection();
        }
    }

    @FXML
    private void sortieVisitor(MouseEvent event) {
        if(!table_visitor_team.getSelectionModel().isEmpty()){
            Player p = table_visitor_team.getSelectionModel().getSelectedItem();
            table_visitor_team.getSelectionModel().clearSelection();
            table_visitor_team.getItems().remove(p);
            table_visitor_team_out.getItems().add(p);            
        }

    }

    @FXML
    private void sanctionHome(MouseEvent event) {
        if(!table_home_team.getSelectionModel().isEmpty()){
            equipeSanction = 1;
            gestionSanction();
        }
    }

    @FXML
    private void sanctionVisitor(MouseEvent event) {
        if(!table_visitor_team.getSelectionModel().isEmpty()){
            equipeSanction = 2;
            gestionSanction();          
        }
    }
    
    private void gestionSanction(){
        toggleEvents();
        btn_2min.setDisable(false);
        btn_carton_bleu.setDisable(false);
        btn_carton_jaune.setDisable(false);
        btn_carton_rouge.setDisable(false);
    }

    @FXML
    private void deuxMin(MouseEvent event) {
        Player p;
        Faute f;
        switch(equipeSanction){
            case 1:
                p = table_home_team.getSelectionModel().getSelectedItem();
                p.setTempsDeuxMin(120);
                f = new Faute(labelTemps.getText(), p, 4);
                twominList.add(p);
                sortieHome(event);
                events.add(f);
                eventTextArea.appendText(f.toString());
                eventTextArea.appendText("\n");
                break;
            case 2:
                p = table_visitor_team.getSelectionModel().getSelectedItem();
                p.setTempsDeuxMin(120);
                f = new Faute(labelTemps.getText(), p, 4);
                twominList.add(p);
                sortieVisitor(event);
                events.add(f);
                eventTextArea.appendText(f.toString());
                eventTextArea.appendText("\n");
                break;
        }
        toggleEvents();
        btn_2min.setDisable(true);
        btn_carton_bleu.setDisable(true);
        btn_carton_jaune.setDisable(true);
        btn_carton_rouge.setDisable(true);
    }

    @FXML
    private void cartonRouge(MouseEvent event) {
        int nb;
        Faute f;
        Player p;
        switch(equipeSanction){
            case 1:
                p = table_home_team.getSelectionModel().getSelectedItem();
                nb = Integer.parseInt(p.getNbRouge());
                nb++;
                p.setNbRouge(Integer.toString(nb));
                table_home_team.refresh();
                f = new Faute(labelTemps.getText(), p, 2);
                sortieHome(event);
                events.add(f);
                eventTextArea.appendText(f.toString());
                eventTextArea.appendText("\n");
                break;
            case 2:
                p = table_visitor_team.getSelectionModel().getSelectedItem();
                nb = Integer.parseInt(p.getNbRouge());
                nb++;
                p.setNbRouge(Integer.toString(nb));
                table_visitor_team.refresh();
                f = new Faute(labelTemps.getText(), p, 2);
                sortieVisitor(event);
                events.add(f);
                eventTextArea.appendText(f.toString());
                eventTextArea.appendText("\n");
                break;
        }

        toggleEvents();
        btn_2min.setDisable(true);
        btn_carton_bleu.setDisable(true);
        btn_carton_jaune.setDisable(true);
        btn_carton_rouge.setDisable(true);
    }

    @FXML
    private void cartonJaune(MouseEvent event) {
        int nb;
        Faute f;
        Player p;
        switch(equipeSanction){
            case 1:
                p = table_home_team.getSelectionModel().getSelectedItem();
                nb = Integer.parseInt(p.getNbJaune());
                nb++;
                p.setNbJaune(Integer.toString(nb));
                table_home_team.refresh(); 
                f = new Faute(labelTemps.getText(), p, 1);
                events.add(f);
                eventTextArea.appendText(f.toString());
                eventTextArea.appendText("\n");
                break;
            case 2:
                 p = table_visitor_team.getSelectionModel().getSelectedItem();
                nb = Integer.parseInt(p.getNbJaune());
                nb++;
                p.setNbJaune(Integer.toString(nb));
                table_visitor_team.refresh();
                f = new Faute(labelTemps.getText(), p, 1);  
                events.add(f);
                eventTextArea.appendText(f.toString());
                eventTextArea.appendText("\n");
                break;
        }
        toggleEvents();
        btn_2min.setDisable(true);
        btn_carton_bleu.setDisable(true);
        btn_carton_jaune.setDisable(true);
        btn_carton_rouge.setDisable(true);
    }

    @FXML
    private void cartonBleu(MouseEvent event) {
        int nb;
        Faute f;
        Player p;
        switch(equipeSanction){
            case 1:
                p = table_home_team.getSelectionModel().getSelectedItem();
                f = new Faute(labelTemps.getText(), p, 3);
                events.add(f);
                eventTextArea.appendText(f.toString());
                eventTextArea.appendText("\n");
                break;
            case 2:
                p = table_visitor_team.getSelectionModel().getSelectedItem();
                f = new Faute(labelTemps.getText(), p, 3);  
                events.add(f);
                eventTextArea.appendText(f.toString());
                eventTextArea.appendText("\n");
                break;
        }
        toggleEvents();
        btn_2min.setDisable(true);
        btn_carton_bleu.setDisable(true);
        btn_carton_jaune.setDisable(true);
        btn_carton_rouge.setDisable(true);
    }
   
    @FXML
    private void Faute_Home(MouseEvent event) {
        if(!table_home_team.getSelectionModel().isEmpty()){
            Player p = table_home_team.getSelectionModel().getSelectedItem();
            table_home_team.getSelectionModel().clearSelection();
            Faute f = new Faute(labelTemps.getText(), p, 0);
            events.add(f);
            eventTextArea.appendText(f.toString());
            eventTextArea.appendText("\n"); 
        }
    }

    @FXML
    private void Faute_Visitor(MouseEvent event) {
        if(!table_visitor_team.getSelectionModel().isEmpty()){
            Player p = table_visitor_team.getSelectionModel().getSelectedItem();
            table_visitor_team.getSelectionModel().clearSelection();
            Faute f = new Faute(labelTemps.getText(), p, 0);
            events.add(f);
            eventTextArea.appendText(f.toString());
            eventTextArea.appendText("\n"); 
        }
    }
    
    private void toggleEvents(){
        btn_Tir_Home.setDisable(!btn_Tir_Home.isDisable());
        btn_Tir_Visitor.setDisable(!btn_Tir_Visitor.isDisable());
        btn_Passe_Home.setDisable(!btn_Passe_Home.isDisable());
        btn_Passe_Visitor.setDisable(!btn_Passe_Visitor.isDisable());
        btn_faute_home.setDisable(!btn_faute_home.isDisable());
        btn_faute_visitor.setDisable(!btn_faute_visitor.isDisable());
        btn_Sanction_Home.setDisable(!btn_Sanction_Home.isDisable());
        btn_Sanction_Visitor.setDisable(!btn_Sanction_Visitor.isDisable());
    }
    
}
