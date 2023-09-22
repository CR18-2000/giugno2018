/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.science.prog2.regazzoni.giugno2018;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * gestisce il gioco centrale
 * @author crist
 */
public class Centro extends GridPane {

    Palo p1;
    Palo p2;
    Palo p3;
    Text from;
    Text to;
    Stage stage;
    Button btn;
    
    /**
     * crea tre pali e sul primo mette i 4 dischi
     * inoltre gestisce i text from e to tramite un eventHandler
     * @param frm testo che rappresenta la torre di partenza
     * @param t testo che rappresenta la torre di arrivo
     * @param close bottone che chiude i popup
     * @param stage2 stage di popup
     */

    Centro(Text frm, Text t, Button close, Stage stage2) {
        btn=close;
        stage=stage2;
        this.from = frm;
        this.to = t;
        this.setStyle("-fx-background-color: gainsboro;");
        ColumnConstraints cc = new ColumnConstraints();
        cc.setPercentWidth(100. / 3);
        this.getColumnConstraints().addAll(cc, cc, cc);
        p1 = new Palo("p1");
        p1.add(new Disco(4));
        p1.add(new Disco(3));
        p1.add(new Disco(2));
        p1.add(new Disco(1));
        this.add(p1, 0, 0);
        this.setAlignment(Pos.CENTER);
        p2 = new Palo("p2");
        this.add(p2, 1, 0);
        p3 = new Palo("p3");
        this.add(p3, 2, 0);
        EventHandler<MouseEvent> mouseEvent = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (from.getText().compareTo("") == 0) {
                    if (p1.premuto) {
                        if (p1.lista.isEmpty()) {
                            errore1();
                        } else {
                            from.setText(p1.nome);
                        }
                        p1.settato();
                    } else if (p2.premuto) {
                        if (p2.lista.isEmpty()) {
                            errore1();
                        } else {
                            from.setText(p2.nome);
                        }
                        p2.settato();
                    } else if (p3.premuto) {
                        if (p3.lista.isEmpty()) {
                            errore1();
                        } else {
                            from.setText(p3.nome);
                        }
                        p3.settato();
                    }
                } else if (to.getText().compareTo("") == 0) {
                    if (p1.premuto) {
                        if (from.getText().compareTo(p1.nome) == 0) {
                            errore2();
                        } else {
                            to.setText(p1.nome);
                        }
                        p1.settato();
                    } else if (p2.premuto) {
                        if (from.getText().compareTo(p2.nome) == 0) {
                            errore2();
                        } else {
                            to.setText(p2.nome);
                        }
                        p2.settato();
                    } else if (p3.premuto) {
                        if (from.getText().compareTo(p3.nome) == 0) {
                            errore2();
                        } else {
                            to.setText(p3.nome);
                        }
                        p3.settato();
                    }
                } else {
                    errore3();
                }
            }
        };
        this.addEventHandler(MouseEvent.MOUSE_PRESSED, mouseEvent);
    }
    
    /**
     * controlla se è possibile spostare il disco
     * se lo è lo fa, altrimenti ritorna falso e la classe principale genera un popup
     * @param partenza è il nome del palo di partenza
     * @param arrivo è il nome del palo di arrivo
     * @return ritotna falso se non può essere aggiunta alla torre il pezzo
     */
    
    public boolean muovi (String partenza, String arrivo) {
        Disco daMuovere = null;
        Palo diPartenza = null;
        switch (partenza) {
            case "p1":
                daMuovere = p1.lista.get(0);
                diPartenza=p1;
                break;
            case "p2":
                daMuovere = p2.lista.get(0);
                diPartenza=p2;
                break;
            case "p3":
                daMuovere = p3.lista.get(0);
                diPartenza=p3;
                break;
        }
        switch (arrivo) {
            case "p1":
                if (p1.canAdd(daMuovere)) {
                    diPartenza.rimuovi();
                    p1.add(daMuovere);
                }
                else return false;
                break;
            case "p2":
                if (p2.canAdd(daMuovere)) {
                    diPartenza.rimuovi();
                    p2.add(daMuovere);
                }
                else return false;
                break;
            case "p3":
                if (p3.canAdd(daMuovere)) {
                    diPartenza.rimuovi();
                    p3.add(daMuovere);
                }
                else return false;
                break;
        }
        return true;
    }
    
    private void errore1 () {
        Label label = new Label ("Il palo di partenza non può essere vuoto");
        Scene scene = new Scene (label, 400, 100);
        btn.setDisable(false);
        //stage = new Stage ();
        stage.setScene(scene);
        stage.setTitle("Error");
        stage.show();
        stage.setX(0);
        stage.setY(0);
    }
    
    private void errore2 () {
        Label label = new Label ("Il palo di partenza non può coincidere con quello finale");
        Scene scene = new Scene (label, 400, 100);
        btn.setDisable(false);
        stage.setScene(scene);
        stage.setTitle("Error");
        stage.show();
        stage.setX(0);
        stage.setY(0);
    }
    
    private void errore3 () {
        Label label = new Label ("Il palo di partenza e destinazione sono già definiti");
        Scene scene = new Scene (label, 400, 100);
        btn.setDisable(false);
        stage.setScene(scene);
        stage.setTitle("Error");
        stage.show();
        stage.setX(0);
        stage.setY(0);
    }

}
