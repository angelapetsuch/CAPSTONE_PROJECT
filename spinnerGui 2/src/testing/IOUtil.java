/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mecic
 */
public class IOUtil {
    
    public static void save(ArrayList<Spinner> arrayList) throws FileNotFoundException, IOException {
        FileOutputStream fos = new FileOutputStream("spinners.sav");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(arrayList);
        oos.close();
    }
    
    public static ArrayList<Spinner> load() {
        FileInputStream fis;
        ArrayList<Spinner> arrayList = new ArrayList();
        
        try {
            fis = new FileInputStream("spinners.sav");
            ObjectInputStream ois = new ObjectInputStream(fis);
            arrayList = (ArrayList<Spinner>) ois.readObject();
            ois.close();
        } catch (FileNotFoundException ex) {
            System.out.println("");
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(IOUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return arrayList;
    }
    
    public static void saveSIndex(ArrayList<Integer> indexes) throws FileNotFoundException, IOException {
        FileOutputStream fos = new FileOutputStream("indexes.sav");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(indexes);
        oos.close();
    }
            
    public static ArrayList<Integer> loadSIndex() {
        FileInputStream fis;
        ArrayList<Integer> arrayList = new ArrayList();
        
        try {
            fis = new FileInputStream("indexes.sav");
            ObjectInputStream ois = new ObjectInputStream(fis);
            arrayList = (ArrayList<Integer>) ois.readObject();
            ois.close();
        } catch (FileNotFoundException ex) {
            System.out.println("");
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(IOUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arrayList;
    }
}
