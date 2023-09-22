/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.science.prog2.regazzoni.giugno2018;

import java.util.ArrayList;
import java.util.Collections;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * classe palo, rappresentato da un rettangolo, su sui sovrappongo i dischi 
 * rappresentati da rettangoli in un VBox
 * @author crist
 */
public class Palo extends StackPane {

    boolean has4;
    boolean has3;
    boolean has2;
    boolean has1;
    VBox dischi;
    String nome;
    ArrayList<Disco> lista = new ArrayList<>();
    boolean premuto;
    
    /**
     * i booleani has servono a dire quali altri dischi sono già presenti nel
     * palo e che dimensione hanno
     * viene aggiunto un eventHandler per sopstare i dischi da un palo all'altro
     * @param n è il nome del palo che viene visualizzato 
     */

    Palo(String n) {
        nome = n;
        dischi = new VBox();
        has1 = false;
        has2 = false;
        has3 = false;
        has4 = false;
        //this.setWidth(100);
        //this.setHeight(80);
        Rectangle rect = new Rectangle(20, 120);
        rect.setFill(Color.BLACK);
        this.getChildren().add(rect);
        this.setAlignment(Pos.BOTTOM_CENTER);
        this.getChildren().add(dischi);
        EventHandler<MouseEvent> mouseEvent = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                premuto = true;
            }
        };
        this.addEventHandler(MouseEvent.MOUSE_PRESSED, mouseEvent);
    }
    
    /**
     * aggiunge il disco passato come parametro alla lista di dischi inseriti
     * @param d 
     */

    public void add(Disco d) {
        switch (d.dimensione) {
            case 1:
                has1 = true;
                lista.add(d);
                update();
                break;
            case 2:
                has2 = true;
                lista.add(d);
                update();
                break;
            case 3:
                has3 = true;
                lista.add(d);
                update();    
                break;
            case 4:
                has4 = true;
                lista.add(d);
                update();
                break;
        }
    }
    
    /**
     * ritorna il valore di premuto a false per non continuare a creare un premuto
     */

    public void settato() {
        premuto = false;
    }

    private void update() {
        System.out.println(this.nome);
        Collections.sort(lista);
        this.getChildren().remove(dischi);
        dischi.getChildren().clear();
        for (Disco a : lista) {
            dischi.getChildren().add(a);
            dischi.setAlignment(Pos.BOTTOM_CENTER);
            System.out.print(a.dimensione);
        }
        System.out.println();
        this.getChildren().add(dischi);
    }
    
    /**
     * controlla se è possibile aggiungere il disco al palo, 
     * ossia se non sono presenti dischi più piccoli
     * @param d
     * @return 
     */
    
    public boolean canAdd (Disco d) {
        if (lista.isEmpty()) return true;
        switch (d.dimensione) {
            case 1:
                return true;
            case 2:
                if (has1) return false;
                else return true;
            case 3:
                if (has1||has2) return false;
                else return true;
            case 4:
                if (has1||has2||has3) return false;
                else return true;
        }
        return false;
    }
    
    /**
     * rimuove il disco più in alto della torre
     */
    
    public void rimuovi () {
        Disco d = lista.get(0);
        switch (d.dimensione) {
            case 1:
                has1=false;
                break;
            case 2:
                has2=false;
                break;
            case 3:
                has3=false;
                break;
            case 4:
                has4=false;
                break;
        }
        lista.remove(0);
        update();
    }

}
