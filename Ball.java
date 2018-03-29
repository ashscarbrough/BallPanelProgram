
// Create a ball to bounce around a JPanel.
import javax.swing.JFrame;
import java.awt.Color;
import java.util.Random;

public class Ball implements Runnable
{
   private boolean xUp; // controls whether ball is moving up or down
   private boolean yUp; // controls whether ball is moving left or right
   private int x; // horizontal position of ball
   private int y; // vertical position of ball
   private int xDx; // change in horizontal position of ball
   private int yDy; // change in vertical position of ball
   private final int MAX_X = 200; // horizontal edge of JPanel
   private final int MAX_Y = 200; // vertical edge of JPanel
   private JFrame parent; // parent window of ball
   private Random generator = new Random();
   private Color color;
	  
   public Ball( JFrame window, int xPos, int yPos )
   {
	  Random random = new Random(); // seed Random
	  int red = (int)(random.nextDouble() * 256);
	  int green = (int)(random.nextDouble() * 256);
	  int blue = (int)(random.nextDouble() * 256);
	   
      xUp = false; // set ball to moving to left
      yUp = false; // set ball to moving up
      xDx = 1; // set ball to move one pixel horizontally at a time
      yDy = 1; // set ball to move one pixel vertically at a time
      x = xPos; // set ball to horizontal position of mouse press
      y = yPos; // set ball to vertical position of mouse press
      parent = window; // set parent window of ball
      color = new Color(red, green, blue);  // set new random color
   } // end Ball constructor
   
   // set ball attribute: color
   public void setBallColor (Color color)
   {	
	   this.color = color;
   }
   
   // return the ball color
   public Color getColor()
   {
	   return color;
   }
   
   // bounces ball perpetually until window is closed
   public void run()
   {
      while ( true ) // infinite loop
      {
         try 
         {
            Thread.sleep( 20 ); // sleep for 20 milliseconds
         } // end try
         // process InterruptedException during sleep
         catch ( InterruptedException exception ) 
         {
            exception.printStackTrace();
         } // end catch

         // determine new x position
         if ( xUp == true )
            x += xDx; // move ball right
         else
            x -= xDx; // move ball left

         // determine new y position
         if ( yUp == true )
            y += yDy; // move ball down
         else
            y -= yDy; // move ball up

         if ( y <= 0 ) // if bounce off top of JPanel
         {
            yUp = true; // ball is moving down
            yDy = generator.nextInt( 5 ) + 2; // change vertical move
         } // end if
         else if ( y >= MAX_Y - 10 ) // if bounce off bottom of JPanel
         {
            yUp = false; // ball is moving up
            yDy = generator.nextInt( 5 ) + 2; // change vertical move
         } // end else if

         if ( x <= 0 ) // if bounce off right of JPanel
         {
            xUp = true; // ball is moving right
            xDx = generator.nextInt( 5 ) + 2; // change horizontal move
         } // end if
         else if ( x >= MAX_X - 10 ) // if bounce off left of JPanel
         {
            xUp = false; // ball is moving left 
            xDx = generator.nextInt( 5 ) + 2; // change horizontal move
         } // end else if

         parent.repaint(); // repaint window
      }  // end while
   } // end method run

   // get horizontal position of ball
   public int getX()
   {
      return x; // return x value
   } // end method getX

   // get vertical position of ball
   public int getY()
   {
      return y; // return y value
   } // end method getY
}  // end class Ball

