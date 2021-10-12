
import java.awt.Color;


/** This class manages the interactions between the different pieces of
 *  the game: the Board, the Daleks, and the Doctor. It determines when
 *  the game is over and whether the Doctor won or lost.
 */
public class CatchGame {

    /**
     * Instance variables go up here
     * Make sure to create a Board, 3 Daleks, and a Doctor
     */
    public static Board board;  
    private Dalek dalek [];
    private Doctor doctor;
    
    

    /**
     * The constructor for the game. 
     * Use it to initialize your game variables.
     * (create people, set positions, etc.)
     */
    public CatchGame(){
        
        //Intializes a board, three daleks, and three rows and columns to set
        //the intial positions before the game starts
        //The positions of the daleks and the doctors should be different at the start
        this.board = new Board(12,12);
        this.dalek = new Dalek[3];
        int [] row = new int[3];
        int [] col = new int[3];
        
        //Uses two for loops to check if positions of two daleks are the same
        //and keeps going until the postions are different using a do while loop
        //Uses math random to set the intial positions of the daleks
        for(int i = 0; i < row.length ;i++)
        {
            boolean sameCoordinates = false;
            do{
               int oneValueHolder = (int)(Math.random()*11 + 0);
               int twoValueHolder = (int)(Math.random()*11 + 0);
                                     
               row [i] = oneValueHolder;
               col [i] = twoValueHolder;
               for(int c = 0; c < row.length; c++)
               {
                   if(row[i] == row[c] && col[i] == col[c])
                   {
                       if(i != c)
                       {
                           sameCoordinates = true;
                       }
                   }
               }
            }while(sameCoordinates == true);
        } 
        
        //Once the two arrays named "row" and "col" with length 3 have been set
        //This for loop transfers the coordinates to the instances of the daleks 
        //and sets the points onto the board 
        for(int i = 0; i < row.length; i++)
        {
            this.dalek[i] = new Dalek(row[i],col[i]);
            board.putPeg(Color.YELLOW,this.dalek[i].getRow(),this.dalek[i].getCol());

        }
        
        //This block of code makes sure the doctor doesn't spawn at the same postion
        //as one of the daleks at the start of the game. If the doctor has the same
        //coordinates as one of the daleks, then two new points are chosen 
        int oneValueHolder = (int)(Math.random()*11 + 0);
        int twoValueHolder = (int)(Math.random()*11 + 0);
        boolean spaceOccupied = true;
        while(spaceOccupied)
        {
            oneValueHolder = (int)(Math.random()*11 + 0);
            twoValueHolder = (int)(Math.random()*11 + 0);
            spaceOccupied = false;
            for(int i = 0; i < row.length; i++)
            {
                if(row[i] == oneValueHolder && col[i] == twoValueHolder)
                   {
                       spaceOccupied = true;
                   }
            }
        }
        
        //Sends the two coordinates to the doctor and pins the doctor onto the board
        this.doctor = new Doctor(oneValueHolder,twoValueHolder);
        this.board.putPeg(Color.BLUE, this.doctor.getRow(),this.doctor.getCol());
         
        playGame();
    }
    
    /**
     * The playGame method begins and controls a game: deals with when the user
     * selects a square, when the Daleks move, when the game is won/lost.
     */
    public void playGame() {
        
        //The while loop keeps executing until the doctor has crashed and the game is lost
        while(doctor.hasCrashed() == false)
        {
            //Gets the coordinate of the click from the user and sets it into two variables
            Coordinate click = CatchGame.board.getClick();
            int row = click.getRow();
            int col = click.getCol();

            //If the "row" or the "col" within the 8 squares around the doctor is clicked by the user
            //Then the doctor will move there and that will be its new position
            //If the user clicks somewhere else on the board, then a random integer is chosen
            //between 0 and 11 and that is the new coordinate of the doctor
            if(row == this.doctor.getRow()-1 || row == this.doctor.getRow()+1 || row == this.doctor.getRow())
            {
                if(col == this.doctor.getCol()-1 || col == this.doctor.getCol()+1 || col == this.doctor.getCol())
                {
                    this.board.removePeg(this.doctor.getRow(),this.doctor.getCol());
                    doctor.move(row,col);
                    this.board.putPeg(Color.BLUE,this.doctor.getRow(),this.doctor.getCol());
                }
                else
                {
                int random1 = (int)(Math.random()*11);
                int random2 = (int)(Math.random()*11);
                this.board.removePeg(this.doctor.getRow(),this.doctor.getCol());
                this.doctor.move(random1,random2);
                this.board.putPeg(Color.BLUE,random1,random2);
                }
            }
            else
            {
                int random1 = (int)(Math.random()*11);
                int random2 = (int)(Math.random()*11);
                this.board.removePeg(this.doctor.getRow(),this.doctor.getCol());
                this.doctor.move(random1,random2);
                this.board.putPeg(Color.BLUE,random1,random2);

            }
            
            
            //This block of code consistently checks if two daleks have crashed together,
            //if they have the same coordinates as each other, then a red peg is placed there 
            //and the two daleks are set to the "crashed state"
            for(int i = 0; i < this.dalek.length;i++)
            {
                if(this.dalek[i].hasCrashed() == false)
                {
                    this.board.removePeg(this.dalek[i].getRow(),this.dalek[i].getCol());
                    this.dalek[i].advanceTowards(this.doctor);
                }
                else
                {
                    this.board.putPeg(Color.RED,this.dalek[i].getRow(),this.dalek[i].getCol());
                }
            }
            
            
            //This block of code executes if the daleks haven't crashed and sets their new postions
            //after the method named "advancedToward" is called which will change the postion of the dalek
            //to move towards the doctor using the shortest move
            for(int i = 0; i < this.dalek.length;i++)
            {
                if(this.dalek[i].hasCrashed() == false)
                {
                    this.board.putPeg(Color.YELLOW,this.dalek[i].getRow(),this.dalek[i].getCol());
                }
            }
            
            //This method sets two daleks into a crashed state if they occupy the same postion
            //It checks if the coordinates are the same using getrow and getcol
            for(int i = 0; i < this.dalek.length; i++)
            {
                for(int c = 0; c < this.dalek.length; c++)
                {
                    if(i != c)
                    {
                        if(this.dalek[i].getRow() == this.dalek[c].getRow() && this.dalek[i].getCol() == dalek[c].getCol())
                        {
                            this.dalek[i].crash();
                            this.dalek[c].crash();
                        }
                    }
                }
            }
            
            //If the dalek has crashed, puts a red peg at the spot 
            for(int i = 0; i < this.dalek.length;i++)
            {
                if(this.dalek[i].hasCrashed() == true)
                {
                    this.board.putPeg(Color.RED,this.dalek[i].getRow(),this.dalek[i].getCol());
                }
            }
            
            //This checks for the win condition, if three daleks have crashed, then the user has won the game
            if(this.dalek[0].hasCrashed() == true && this.dalek[1].hasCrashed() == true && this.dalek[2].hasCrashed() == true)
            {
                this.board.displayMessage("Won");
                this.doctor.crash();
            }
            
            //This checks if the doctor is in the crash state in which case it will say lost
            //This block of code is executed after the check for win statement because there is 
            //scenario when the doctor lands on the spot but all other three daleks have also crashed
            for(int i = 0; i < this.dalek.length; i++)
            {
                if(this.doctor.getRow() == this.dalek[i].getRow() && this.doctor.getCol() == this.dalek[i].getCol())
                {
                    this.dalek[i].crash();
                    this.board.putPeg(Color.RED,this.doctor.getRow(),this.doctor.getCol());
                    this.doctor.crash();
                    this.board.displayMessage("Lost");
                }
            }
            
        }
         
        
    }

}
