/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.sigati.util;

import java.util.Comparator;
import org.primefaces.model.SortOrder;
import py.com.sigati.entities.Tarea;

/**
 *
 * @author Juanhi
 */
public class LazySorter implements Comparator<Tarea> {
 
    private String sortField;
     
    private SortOrder sortOrder;
     
    public LazySorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }
 
    public int compare(Tarea tarea1, Tarea tarea2) {
        try {
            Object value1 = Tarea.class.getField(this.sortField).get(tarea1);
            Object value2 = Tarea.class.getField(this.sortField).get(tarea2);
 
            int value = ((Comparable)value1).compareTo(value2);
             
            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        }
        catch(Exception e) {
            throw new RuntimeException();
        }
    }
}
