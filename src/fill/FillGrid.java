/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fill;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author DSTIGANT
 */
public class FillGrid
{
    private Color [][] grid;
    private Random rng;
    
    public FillGrid( int r, int c )
    {
        rng = new Random();
        
        grid = new Color[r][c];
        for ( int i = 0; i < r; i++ )
        {
            for ( int j = 0; j < c; j++ )
            {
                grid[i][j] = Color.WHITE;
            }
        }
        for ( int i = 0; i < r*c/20; i++ )
        {
            fill( rng.nextInt( r ), rng.nextInt( c ), r*c/3, getColor( i ) );
        }
    }
    
    public int getNumRows() { return grid.length; }
    public int getNumCols() { return grid[0].length; }
    
    public Color getSquare( int r, int c ) { return grid[r][c]; }
    
    private Color getColor( int i )
    {
        i = i % 5;
        if ( i == 0 ) return Color.RED;
        if ( i == 1 ) return Color.BLUE;
        if ( i == 2 ) return Color.GREEN;
        if ( i == 3 ) return Color.YELLOW;
        return Color.BLACK;
    }
    
    private void fill( int r, int c, int n, Color col )
    {
        for ( int i = 0; i < n; i++ )
        {
            grid[r][c] = col;
            int dir = rng.nextInt(4);
            int dr = (dir % 2 == 0)?dir - 1:0;
            int dc = (dir % 2 == 0)?0:dir-2;
            r = Math.min( Math.max(0, r + dr), grid.length-1);
            c = Math.min( Math.max(0, c + dc), grid[0].length-1);
        }
    }
    
    // fillRegion:
    // fills the region containing l with colNew provided that l
    // is on the grid and grid[l.row][l.col] is currently colOld
    // returns the number of squares filled
    private void fillRegion( Location l, Color colNew, Color colOld )
    {
        //if this is already the right color, then do nothing
        //if this is the old color, then change it and find children
        //otherwise do nothing

        //so only if this is the old color do we need to do anything
        //  change this color.
        //  find children.
        if (grid[l.row][l.col].equals(colNew))
            return;

        System.out.println(l);
        if (grid[l.row][l.col].equals(colOld)) {
            grid[l.row][l.col] = colNew;
            for (int dir : new int[] {0, 1, 2, 3} ) {
                Location child = l.getNeighbor(dir);
                if (!notInGrid(child))
                    fillRegion(l.getNeighbor(dir), colNew, colOld);
            }
        }
    }

    // fillRegion
    // fills the region containing l with color col
    // returns the number of squares filled
    public int fillRegion( Location l, Color col )
    {
        int count = count(l);
        fillRegion(l, col, grid[l.row][l.col]);
        return count;
    }

    // count
    // counts the size of the region of color c containing location l
    private int count( Location parent, Color c, ArrayList<Location> visited )
    {
        if(visited.contains(parent))
            return 0;
        if(notInGrid(parent))
            return 0;
        if(!grid[parent.row][parent.col].equals(c))
            return 0;
        int counter = 1;
        visited.add(parent);
        for (int dir : new int[] {0, 1, 2, 3}) {
            Location child = parent.getNeighbor(dir);
            counter += count(child, c, visited);
        }
        return counter;
    }

    private boolean notInGrid(Location l) {
        return l.row < 0 || l.col < 0 || l.row > grid.length - 1 || l.col > grid[0].length - 1;
    }
    
    // count
    // counts the number of squares in the region containing l
    public int count( Location l )
    {
        return count(l, grid[l.row][l.col], new ArrayList<>());
    }
}
