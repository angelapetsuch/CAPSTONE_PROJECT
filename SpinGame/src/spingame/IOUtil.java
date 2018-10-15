/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spingame;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User Name
 */
public class IOUtil {
    
    public static void save(ArrayList<Spinner> arrayList) throws FileNotFoundException, IOException {
        FileOutputStream fos = new FileOutputStream("spinners.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(arrayList);
        oos.close();
    }
    
    public static ArrayList<Spinner> load() {
        FileInputStream fis;
        ArrayList<Spinner> arrayList = new ArrayList();
        
        try {
            fis = new FileInputStream("spinners.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            arrayList = (ArrayList<Spinner>) ois.readObject();
            ois.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(IOUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(IOUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return arrayList;
    }
}
