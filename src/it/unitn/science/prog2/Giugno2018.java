/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.science.prog2.regazzoni.giugno2018;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * classe che funge da main
 * il borderpane contiene i bottoni, i contatori e il centro dove avviene il gioco
 * @author crist
 */
public class Giugno2018 extends Application {
    
    Button reset; Button close; Button move; Button clear;
    Text from; Text to;
    Centro centro;
    BorderPane root;
    Scene scene2;
    Stage stage2 = new Stage();
    
    @Override
    public void start(Stage primaryStage) {
        root = new BorderPane();
        reset = new Button("Reset");
        EventHandler<ActionEvent> eventHandlerReset = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                reset();
            }
        };
        reset.addEventHandler(ActionEvent.ACTION, eventHandlerReset);
        root.setRight(reset);
        reset.setAlignment(Pos.CENTER);
        close = new Button ("Close");
        EventHandler<ActionEvent> eventHandlerClose = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                chiudere();
            }
        };
        close.addEventHandler(ActionEvent.ACTION, eventHandlerClose);
        close.setDisable(true);
        root.setLeft(close);
        close.setAlignment(Pos.CENTER);
        move = new Button ("Move");
        EventHandler<ActionEvent> eventHandlerMove = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                muovere();
            }
        };
        move.addEventHandler(ActionEvent.ACTION, eventHandlerMove);
        root.setBottom(move);
        move.setAlignment(Pos.CENTER);
        HBox hb = new HBox ();
        Label title1 = new Label ("From: ");
        from = new Text();
        Label title2 = new Label ("     To: ");
        to = new Text();
        hb.getChildren().addAll(title1, from, title2, to);
        clear = new Button ("Clear");
        EventHandler<ActionEvent> eventHandlerClear = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cancellare();
            }
        };
        clear.addEventHandler(ActionEvent.ACTION, eventHandlerClear);
        hb.getChildren().add(clear);
        root.setTop(hb);
        centro = new Centro(from, to, close, stage2);
        root.setCenter(centro);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Torre di Hanoi!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void cancellare () {
        from.setText("");
        to.setText("");
    }
    
    private void muovere () {
        if (from.getText().compareTo("")==0||to.getText().compareTo("")==0) {
            Label label = new Label ("PALI DI PARTENZA E ARRIVO NON DEFINITI");
            close.setDisable(false);
            scene2 = new Scene (label, 400, 100);
            stage2 = new Stage ();
            stage2.setScene(scene2);
            stage2.setTitle("Error");
            stage2.show();
            stage2.setX(0);
            stage2.setY(0);
        }
        else {
            if (!centro.muovi(from.getText(), to.getText())) {
                Label label = new Label ("IMPOSSIBILE APPOGGIARE UN DISCO SU UNO PIU' PICCOLO");
                close.setDisable(false);
                scene2 = new Scene (label, 400, 100);
                stage2 = new Stage();
                stage2.setScene(scene2);
                stage2.setTitle("Error");
                stage2.show();
                stage2.setX(0);
                stage2.setY(0);
            }
        }
    }
    
    private void chiudere () {
        stage2.close();
        close.setDisable(true);
    }
    
    private void reset () {
        cancellare();
        centro = new Centro(from, to, close, stage2);
        root.setCenter(centro);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
