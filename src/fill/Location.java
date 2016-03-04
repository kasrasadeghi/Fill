/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fill;

/**
 *
 * @author DSTIGANT
 */
public class Location
{
    public final int row, col;
    
    public Location( int r, int c )
    {
        row = r;
        col = c;
    }
    
    public boolean equals( Object o )
    {
        if ( !(o instanceof Location) )
        {
            return false;
        }
        Location l = (Location)o;
        return l.row == row && l.col == col;
    }
    
    public Location getNeighbor( int dir )
    {
        if ( dir == 0 ) return new Location( row - 1, col );
        if ( dir == 1 ) return new Location( row, col + 1 );
        if ( dir == 2 ) return new Location( row + 1, col );
        if ( dir == 3 ) return new Location( row, col - 1 );
        return this;
    }

    public String toString() {
        return "[ " + row + ", " + col + " ]";
    }
}
