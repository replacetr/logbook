package org.sqlite;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

//from ww  w  . j  a  va  2 s .  c o m
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Main1 {
	public static JFrame frame;

	static long millis;
	


	  public static void main(String[] args) {
		  
		  
	    new Main1();
	  }

	  public void Main(long millis2) {
		  millis = millis2;
		  
		  
	     frame = new JFrame();
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setLayout(new BorderLayout());
	    frame.add(new TestPane(millis));
	    frame.pack();
	    frame.setVisible(true);
	    frame.setResizable(false);
	  }
	  
	  public static void closing() {
//		  frame.setVisible(false);
		  frame.dispose();
	  }
	 
	

}

	class TestPane extends JPanel {
	  /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
	JLabel label;
	  Timer timer;
	  int count;
	  public long millis;
	  
	  public TestPane(long milliin) {
		  millis = milliin;
		  long stopcount = milliin / 1000;
	    label = new JLabel("...");
	    label.setFont(new Font(label.getFont().getName(), Font.PLAIN, 80));
	    setLayout(new GridBagLayout());
	    add(label);
	    timer = new Timer(1000, new ActionListener() {
	      @Override
	      public void actionPerformed(ActionEvent e) {
	        count++;
	        System.out.println(count+ "<- count : stop " + stopcount);
	        
	        if (count < stopcount) {
	          
	          
	          
	          String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
	                  TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
	                  TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
	          System.out.println(hms);
	          
	          millis = millis - 1000;
	          label.setText(hms);
	          DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	          Date date = new Date();
	          System.out.println(dateFormat.format(date));
	          
	        } else {
	          ((Timer) (e.getSource())).stop();
	        }
	      }
	    });
	    timer.setInitialDelay(0);
	    timer.start();
	    
	   
	  }
	  @Override
	  public Dimension getPreferredSize() 
	  {
		  
	    return new Dimension(400, 150);
	  }
	  
	  
	 
	  

	}
  


