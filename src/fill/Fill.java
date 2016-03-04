/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fill;

import apcscvm.CVMProgram;

/**
 *
 * @author DSTIGANT
 */
public class Fill
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        FillGridView v = new FillGridView();
        FillGrid g = new FillGrid( 30, 30 );
        new CVMProgram( "Fill", 800, 600, v, v, g ).start();
    }
    
}
