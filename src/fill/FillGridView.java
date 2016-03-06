/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fill;

import apcscvm.DefaultControl;
import apcscvm.View;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 *
 * @author DSTIGANT
 */
public class FillGridView extends DefaultControl<FillGrid> implements View<FillGrid>
{
    private int width, height;
    private Color fill;
    
    public FillGridView()
    {
        fill = null;
    }
    
    @Override
    public void paint(FillGrid m, Graphics g, int w, int h)
    {
        width = w;
        height = h;
        int nr = m.getNumRows();
        int nc = m.getNumCols();
        double sw = w*1.0/nc;
        double sh = h*1.0/nr;
        
        for ( int i = 0; i < m.getNumRows(); i++ )
        {
            for ( int j = 0; j < m.getNumCols(); j++ )
            {
                g.setColor( m.getSquare(i,j) );
                g.fillRect( (int)(j*sw), (int)(i*sh), (int)(sw), (int)(sh));
                g.setColor( Color.GRAY );
                g.drawRect((int)(j*sw), (int)(i*sh), (int)(sw), (int)(sh));
            }
        }
    }

    public void handleMouseClick( FillGrid m, int ea, MouseEvent me )
    {
        int row = (int)(me.getY() * m.getNumRows() / height);
        int col = (int)(me.getX() * m.getNumCols() / width );
        Location l = new Location( row, col );
//        System.out.println("handling: " + l);
        if ( fill != null )
        {            
            System.out.println( m.fillRegion( l, fill ) );
        }
        else
        {
            System.out.println( m.count( l ));
        }
    }
    
    public void handleKeyPress( FillGrid m, int ea, KeyEvent ke )
    {
        if ( ke.getKeyCode() == KeyEvent.VK_C )
        {
            fill = null;
        }
        else if ( ke.getKeyCode() == KeyEvent.VK_1 )
        {
            fill = Color.RED;
        }
        else if ( ke.getKeyCode() == KeyEvent.VK_2 )
        {
            fill = Color.BLUE;
        }
        else if ( ke.getKeyCode() == KeyEvent.VK_3 )
        {
            fill = Color.GREEN;
        }
        else if ( ke.getKeyCode() == KeyEvent.VK_4 )
        {
            fill = Color.YELLOW;
        }
        else if ( ke.getKeyCode() == KeyEvent.VK_5 )
        {
            fill = Color.BLACK;
        }
        else if ( ke.getKeyCode() == KeyEvent.VK_6 )
        {
            fill = Color.PINK;
        }
        else if ( ke.getKeyCode() == KeyEvent.VK_7 )
        {
            fill = Color.WHITE;
        }
    }
}
