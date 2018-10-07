/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spingame;

/**
 *
 * @author angela
 */
public class Game {
    
    private int numSpinners;
    private String selectSpinner;
    
    // constructor
    public Game(int n, String s) {
        this.numSpinners = n;
        this.selectSpinner = s;
    }

    /**
     * @return the numSpinners
     */
    public int getNumSpinners() {
        return numSpinners;
    }

    /**
     * @param numSpinners the numSpinners to set
     */
    public void setNumSpinners(int numSpinners) {
        this.numSpinners = numSpinners;
    }

    /**
     * @return the selectSpinner
     */
    public String getSelectSpinner() {
        return selectSpinner;
    }

    /**
     * @param selectSpinner the selectSpinner to set
     */
    public void setSelectSpinner(String selectSpinner) {
        this.selectSpinner = selectSpinner;
    }
    
    public String toString() {
        return this.numSpinners + "\n" + this.selectSpinner;
    }
}
