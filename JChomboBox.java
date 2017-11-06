package net.juanxxiii.j23guiappframework.persistencia;
import java.awt.Dimension;
import javax.swing.JComboBox;

/**
 * Clase que extiende el JComboBox
 * 
 * @author Sergio Torres Garcia
 *
 */
public class JChomboBox extends JComboBox {

    /**
     * Constructor.
     *  
     * @param num_items
     */
    public JChomboBox( int num_items  )
    {   
        Dimension d = new Dimension(86, 20);
        this.setSize(d);
        this.setPreferredSize(d);
        //Indices para las imagenes
        for( int i=0; i<num_items; i++)
        {
            this.addItem(i);
        }    
        this.setVisible(true);
    }  
}
