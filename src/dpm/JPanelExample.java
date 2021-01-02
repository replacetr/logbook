package dpm;
import java.awt.*;
import java.awt.Dialog.ModalityType;
import java.awt.image.*;
import java.io.*;
import javax.swing.*;
import javax.imageio.ImageIO;
 
public class JPanelExample {
 
  @SuppressWarnings("deprecation")
public static void main(String[] arguments) throws IOException {
 
    JPanel panel = new JPanel();
 
    BufferedImage image = ImageIO.read(new File("./java.jpg"));
    JLabel label = new JLabel(new ImageIcon(image));
    panel.add(label);
 
    // main window
    JDialog frame = new JDialog ();
    frame.setModal (true);
    frame.setAlwaysOnTop (true);
    frame.setModalityType (ModalityType.APPLICATION_MODAL);
//    JFrame frame = new JFrame("JPanel Example");
//    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
// 
//    // add the Jpanel to the main window
    frame.getContentPane().add(panel); 
//    
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int width = (int) screenSize.getWidth();
    int height = (int) screenSize.getHeight();
// 
//    frame.pack();
    frame.setVisible(true);
    frame.resize(width,height)); 
    frame.setUndecorated(true);
  }
}
 