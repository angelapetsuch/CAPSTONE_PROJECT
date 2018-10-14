/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spingame;

import java.util.ArrayList;

/**
 *
 * @author angela
 */
public class Spinner {
    
    private String spinnerName;
    private int numFields;
    private ArrayList<String> fields;
    
   // constructor
    public Spinner(String s, int f) {
        this.spinnerName = s;
        this.numFields = f;
    }
    
    public Spinner() {
    }
    
    public String getField(int index) {
        return this.fields.get(index);
    }
    
    public void setField(ArrayList<String> fields) {
        this.fields = fields;
    }

    /**
     * @return the spinnerName
     */
    public String getSpinnerName() {
        return spinnerName;
    }

    /**
     * @param spinnerName the spinnerName to set
     */
    public void setSpinnerName(String spinnerName) {
        this.spinnerName = spinnerName;
    }

    /**
     * @return the numFields
     */
    public int getNumFields() {
        return numFields;
    }

    /**
     * @param numFields the numFields to set
     */
    public void setNumFields(int numFields) {
        this.numFields = numFields;
    }
    
    @Override
    public String toString() {
        return "Spinner - " + this.spinnerName + ": " + this.numFields + " field";
    }
}
