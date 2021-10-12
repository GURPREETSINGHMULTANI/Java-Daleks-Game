
import java.awt.Color;


/** This class models a Delek in the game. A Delek has
 *  a position and can advance towards the Doctor.
 */
public class Dalek {

    private int row, col;
    private boolean hasCrashed;

    /**
     * Initializes the variables for a Dalek.
     *
     * @param theRow The row this Dalek starts at.
     * @param theCol The column this Dalek starts at.
     */
    public Dalek(int theRow, int theCol) {
        
        //Sets dalek into a new row and col that is provided by the method
        this.row = theRow;
        this.col = theCol;

        
    }

    /**
     * Attempts to move the Dalek towards the Doctor by the most direct route,
     * moving up, down, right, left or diagonally. For example, if the Doctor is
     * above and to the right of a Dalek, it will move diagonally. If the Doctor
     * is directly below a Dalek, it will move down.
     *
     * @param doc The Doctor to move towards.
     */
    public void advanceTowards(Doctor doc) {
        //Creates two variables for to store the col and row of the doctor
        int row = doc.getRow();
        int col = doc.getCol();
        
        //Checks where the dalek is relative to the doctor and chooses one scenario
        //to set the new positions of the dalek so the daleks move towards the doctor
        //using the shortest route
        //Each scenario or position of the dalek relative to the doctor is set in the
        //order such that the most efficient path to the doctor is chosen each time
        if(this.row-row == 0 && this.col-col == 0)
        {
            
        }
        else if(row-this.row == this.col-col && row-this.row >= 0 )
        {
            this.row++;
            this.col--;
        }
        else if(this.row-row == col-this.col && this.row-row >= 0)
        {
            this.row--;
            this.col++;
        }
        else if(row-this.row == col-this.col && row-this.row >= 0)
        {
            this.row++;
            this.col++;
        }
        else if(this.row-row == this.col-col && this.row-row >= 0)
        {
            this.row--;
            this.col--;
        }        
        else if(this.row-row == 0 && this.col-col < 0)
        {
            this.col++;
        }
        else if(this.row-row == 0 && this.col-col > 0)
        {
            this.col--;
        }
        else if(this.col-col == 0 && this.row-row < 0)
        {
            this.row++;
        }
        else if(this.col-col == 0 && this.row-row > 0)
        {
            this.row--;
        }
        else if(this.row - row > 0 && this.col - col > 0)
        {
            this.row--;
            this.col--;
        }
        else if(this.row - row < 0 && this.col - col < 0)
        {
            this.row++;
            this.col++;
        }
        else if(this.row - row < 0 && this.col - col > 0)
        {
            this.row++;
            this.col--;
        }
        else if(this.row - row > 0 && this.col - col < 0)
        {
            this.row--;
            this.col++;
        }
    }

    /**
     * Returns the row of this Dalek.
     *
     * @return This Dalek's row.
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Returns the column of this Dalek.
     *
     * @return This Dalek's column.
     */
    public int getCol() {
        return this.col;
    }

    /**
     * Sets the Dalek to be in a crashed state.
     */
    public void crash() {
        this.hasCrashed = true;
        
        
    }

    /**
     * Returns whether or not this Dalek has crashed.
     *
     * @return true if this Dalek has crashed, false otherwise
     */
    public boolean hasCrashed() {
        if(hasCrashed)
        {
            return true;
        }
        
        return false;
    }

}
