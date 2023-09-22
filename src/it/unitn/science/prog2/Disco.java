/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.science.prog2.regazzoni.giugno2018;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * classe che visualizza i dischi come rettangoli
 * @author crist
 */
public class Disco extends Rectangle implements Comparable{
    
    int dimensione;
    
    /**
     * il disco viene creato come rettangolo di dimensione dipendente dal parametro
     * e il colore viene scelto in base alla dimensione del disco
     * @param i dimensione del disco che può essere 1, 2, 3, 4
     */
    
    Disco (int i) {
        dimensione = i;
        this.setWidth(20*dimensione);
        this.setHeight(20);
        this.setStroke(Color.BLACK);
        switch (dimensione) {
            case 1:
                this.setFill(Color.GRAY);
                break;
            case 2:
                this.setFill(Color.YELLOWGREEN);
                break;
            case 3:
                this.setFill(Color.PURPLE);
                break;
            case 4:
                this.setFill(Color.RED);
                break;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.dimensione;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Disco other = (Disco) obj;
        if (this.dimensione != other.dimensione) {
            return false;
        }
        return true;
    }
    
    /**
     * compareTo per riordinare dalla dimensione minore a quella maggiore la lista
     * contenente i dischi di ogni palo
     * @param o
     * @return 
     */

    @Override
    public int compareTo(Object o) {
        if (getClass() != o.getClass()) System.exit(0);
        if (this.equals(o)) return 0;
        final Disco other = (Disco) o;
        if (this.dimensione<other.dimensione) return -1;
        else return 1;
    }
    
    
    
}
