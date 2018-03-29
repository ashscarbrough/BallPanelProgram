
// JPanel that creates a ball when the mouse is pressed.  Ball bounces
// around the JPanel.
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class BallPanel extends JPanel
{  //  * Top 3 variables: Ball was: private Ball blueBall; 
	private int MAX_BALL_NUMBER = 20; // Maximum number of balls permitted
	private Ball [] ballList; // Array of pointers for balls
	private int ballCount = 0;  // Keeps count of number of balls in play
	private ExecutorService threadExecutor; // for running Ball runnable
	private JFrame parent; // parent window of JPanel
	private final int MAX_X = 200; // horizontal edge of JPanel
	private final int MAX_Y = 200; // vertical edge of JPanel

	public BallPanel( JFrame window )
	{
		parent = window; // set parent window of JPanel

		// create ExecutorService for running ball runnable
		threadExecutor = Executors.newCachedThreadPool();

		// let BallPanel be its own MouseListener
		addMouseListener( 
         new MouseAdapter() 
         {
            public void mousePressed( MouseEvent event ) 
            {
               createBall( event ); // delegate call to ball starter
            } // end method mousePressed
         } // end anonymous inner class
      ); // end call to addMouseListener
	} // end BallPanel constructor

	// create a ball and set it in motion if no ball exists
	private void createBall( MouseEvent event )
	{
		// If ballList array has not been created yet, create it
		if ( ballList == null ) // if no ball exists	
			ballList = new Ball[MAX_BALL_NUMBER];
			
		// If the maximum ballCount has not been reached, create new ball
		if (ballCount < MAX_BALL_NUMBER)
		{
				int x = event.getX(); // get x position of mouse press
				int y = event.getY(); // get y position of mouse press
				ballList[ballCount] = new Ball( parent, x, y ); // create new ball
				threadExecutor.execute( ballList[ballCount] ); // set ball in motion
				ballCount++;
		}
	} // end method createBall

	// return minimum size of animation
	public Dimension getMinimumSize()
	{ 
		return getPreferredSize(); 
	} // end method getMinimumSize

	// return preferred size of animation
	public Dimension getPreferredSize()
	{
		return new Dimension( MAX_X, MAX_Y );
	} // end method getPreferredSize

	// draw ball at current position
	public void paintComponent( Graphics g )
	{
		super.paintComponent( g );
		
		for (int i = 0; i < ballCount; i++)
		{	
			//Set graphics color to ball Color
			g.setColor(ballList[i].getColor()); // set color to blue
			// draw ball
			g.fillOval( ballList[i].getX(), ballList[i].getY(), 10, 10 );
		}// end for
	} // end method paintComponent
} // end class BallPanel
