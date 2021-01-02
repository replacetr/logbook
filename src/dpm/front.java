package dpm;

import static java.nio.file.StandardOpenOption.*;
import java.nio.file.*;
import java.io.*;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import org.sqlite.Main1;

public class front
  extends JFrame
{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private JPanel contentPane;
  private JTextField namapelajar;
  private JTextField noic;
  private JTextField tingkatan;

  public static String configfile;
  public static String chromepath;
  private JButton btnNewButton_1;
  private JButton btnNewButton;
  private JTextField tujuan;
  public static String setTitle;
  private JButton btnNewButton_2;
  public static boolean exits;

  
  public static void main(String[] args)
  {
    File configFile = new File("config.txt");
    try
    {
      FileReader reader = new FileReader(configFile);
      Properties props = new Properties(); 
      props.load(reader);
    	
//      FileInputStream in = new FileInputStream(configFile);
//      Properties props = new Properties();
//      props.load(in);
//      in.close();
//      
      
      
      configfile = props.getProperty("database");
      chromepath = props.getProperty("chrome");
      setTitle = props.getProperty("title");
      
//      FileOutputStream out = new FileOutputStream(configFile);
//      props.setProperty("host", "tukar");
//      props.store(out, null);
//      out.close();
      
     
      reader.close();
    }
    catch (FileNotFoundException localFileNotFoundException) {
    	JOptionPane.showMessageDialog(null, "Error Config.txt Not Found, Sila hubungi Juruteknik @ DPM");
    	 exits = true;
    	
    }catch (IOException localIOException) {
    	JOptionPane.showMessageDialog(null, "Error Config.txt Not Found, Sila hubungi Juruteknik @ DPM");
	 exits = true;}
    EventQueue.invokeLater(new Runnable()
    {
      public void run()
      {
        try
        {
          front frame = new front();
          frame.setVisible(true);
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
    });
    

  }
  
 

public front()
  {
    
    setTitle(setTitle + " LogBook Beta 1.1 Build for 1.8 JRE");
    setDefaultCloseOperation(0);
    setBounds(100, 100, 431, 493);
    this.contentPane = new JPanel();
    this.contentPane.setToolTipText("Nama");
    this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(this.contentPane);
    SpringLayout sl_contentPane = new SpringLayout();
    this.contentPane.setLayout(sl_contentPane);
    
    this.namapelajar = new JTextField();
    sl_contentPane.putConstraint("East", this.namapelajar, -40, "East", this.contentPane);
    this.namapelajar.setToolTipText("Contoh : Peter");
    this.contentPane.add(this.namapelajar);
    this.namapelajar.setColumns(10);
    
    this.noic = new JTextField();
    sl_contentPane.putConstraint("West", this.noic, 0, "West", this.namapelajar);
    this.noic.setToolTipText("Contoh : 200101-12-8889");
    this.contentPane.add(this.noic);
    this.noic.setColumns(10);
    
    JLabel lblNama = new JLabel("Nama Pelajar :");
    sl_contentPane.putConstraint("West", lblNama, 23, "West", this.contentPane);
    sl_contentPane.putConstraint("West", this.namapelajar, 42, "East", lblNama);
    sl_contentPane.putConstraint("North", lblNama, 3, "North", this.namapelajar);
    this.contentPane.add(lblNama);
    
    JLabel lblNoKadPengenalan = new JLabel("No Kad Pengenalan :");
    sl_contentPane.putConstraint("North", this.noic, -3, "North", lblNoKadPengenalan);
    sl_contentPane.putConstraint("North", lblNoKadPengenalan, 25, "South", lblNama);
    sl_contentPane.putConstraint("West", lblNoKadPengenalan, 0, "West", lblNama);
    this.contentPane.add(lblNoKadPengenalan);
    
    JLabel lblTingkatankelas = new JLabel("Tingkatan/Kelas :");
    sl_contentPane.putConstraint("North", lblTingkatankelas, 22, "South", lblNoKadPengenalan);
    sl_contentPane.putConstraint("West", lblTingkatankelas, 0, "West", lblNama);
    this.contentPane.add(lblTingkatankelas);
    
    this.tingkatan = new JTextField();
    sl_contentPane.putConstraint("North", this.tingkatan, -3, "North", lblTingkatankelas);
    sl_contentPane.putConstraint("West", this.tingkatan, 0, "West", this.namapelajar);
    sl_contentPane.putConstraint("East", this.tingkatan, 65364, "East", this.contentPane);
    this.tingkatan.setToolTipText("Contoh: 5 G");
    this.contentPane.add(this.tingkatan);
    this.tingkatan.setColumns(10);
    
    JLabel lblLogBookPengunaan = new JLabel("Log Book Pengunaan Komputer Pusat Akses SMK DPM ");
    sl_contentPane.putConstraint("North", this.namapelajar, 36, "South", lblLogBookPengunaan);
    sl_contentPane.putConstraint("North", lblLogBookPengunaan, 30, "North", this.contentPane);
    sl_contentPane.putConstraint("West", lblLogBookPengunaan, 64, "West", this.contentPane);
    this.contentPane.add(lblLogBookPengunaan);
    
    JLabel lblTujuan = new JLabel("Tujuan :");
    sl_contentPane.putConstraint("North", lblTujuan, 23, "South", lblTingkatankelas);
    sl_contentPane.putConstraint("West", lblTujuan, 0, "West", lblNama);
    this.contentPane.add(lblTujuan);
    
    this.btnNewButton = new JButton("Daftar");
    sl_contentPane.putConstraint("West", this.btnNewButton, 23, "West", this.contentPane);
    this.btnNewButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        boolean set1 = front.this.namapelajar.getText().isEmpty();
        boolean set2 = front.this.noic.getText().isEmpty();
        boolean set3 = front.this.tingkatan.getText().isEmpty();
        boolean set4 = front.this.tujuan.getText().isEmpty();
        if ((set1) || (set2) || (set3) || (set4)) {
          JOptionPane.showMessageDialog(null, "Sila isi Maklumat Anda Di ruang Kosong");
        } else {
          try
          {
            front.this.addData();
          }
          catch (IOException e1)
          {
            e1.printStackTrace();
          }
        }
      }
    });
    this.contentPane.add(this.btnNewButton);
    
    JLabel lblsilaIsiRuang = new JLabel("*Sila isi untuk tujuan rekod. Terima Kasih.");
    sl_contentPane.putConstraint("North", this.btnNewButton, 79, "South", lblsilaIsiRuang);
    sl_contentPane.putConstraint("West", lblsilaIsiRuang, 0, "West", lblNama);
    this.contentPane.add(lblsilaIsiRuang);
    
    this.btnNewButton_1 = new JButton("Chrome");
    sl_contentPane.putConstraint("East", this.btnNewButton, -34, "West", this.btnNewButton_1);
    sl_contentPane.putConstraint("South", this.btnNewButton_1, 30, "North", this.btnNewButton);
    sl_contentPane.putConstraint("North", this.btnNewButton_1, 0, "North", this.btnNewButton);
    sl_contentPane.putConstraint("West", this.btnNewButton_1, 0, "West", this.namapelajar);
    sl_contentPane.putConstraint("East", this.btnNewButton_1, -3, "East", this.noic);
    this.btnNewButton_1.addActionListener(new ActionListener()
    {
      
	public void actionPerformed(ActionEvent e)
      {
    	 
    	  chromeThread myThread = new chromeThread();
    	  long masa = 10000;
    	  myThread.setMasa(masa);
    	  myThread.start();
    	  
    	  
    	  TimingMasa clocking = new TimingMasa();
    	  clocking.setMasa(masa);
    	  clocking.start();         
        
      }
    });

    
    this.btnNewButton_1.setEnabled(false);
    this.contentPane.add(this.btnNewButton_1);
    
    this.tujuan = new JTextField();
    sl_contentPane.putConstraint("North", lblsilaIsiRuang, 36, "South", this.tujuan);
    sl_contentPane.putConstraint("North", this.tujuan, -3, "North", lblTujuan);
    sl_contentPane.putConstraint("West", this.tujuan, 0, "West", this.namapelajar);
    sl_contentPane.putConstraint("East", this.tujuan, -45, "East", this.contentPane);
    this.tujuan.setToolTipText("Contoh : Melayari Internet");
    this.contentPane.add(this.tujuan);
    this.tujuan.setColumns(10);
    
    Canvas canvas = new Canvas();
    this.contentPane.add(canvas);
    
    this.btnNewButton_2 = new JButton("Keluar");
    this.btnNewButton_2.setEnabled(true);
    sl_contentPane.putConstraint("North", this.btnNewButton_2, 0, "North", this.btnNewButton);
    sl_contentPane.putConstraint("West", this.btnNewButton_2, 20, "East", this.btnNewButton_1);
    sl_contentPane.putConstraint("East", this.btnNewButton_2, -38, "East", this.contentPane);
    
    //kluar ok kalau tidak teda database sm config.txt
    
    if (exits) {
    	this.btnNewButton_2.setEnabled(true);
    	    }
    
    this.btnNewButton_2.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        front.this.dispose();
      }
    });
    this.contentPane.add(this.btnNewButton_2);
  }
  
  public void addData()
    throws IOException
  { 
	   
    masukdata();
  }
  
  public void masukdata()
    throws IOException
  {
	  int tanya =JOptionPane.showConfirmDialog(null,
			  "Adakah Maklumat Anda Tepat?", "Sila Isi Semula", JOptionPane.YES_NO_OPTION);
	  if(tanya == 0) {
	  
    boolean parameter = insert(this.namapelajar.getText(), this.noic.getText(), this.tingkatan.getText(), this.tujuan.getText(), setTitle);
    if (parameter)
    {

      this.btnNewButton_1.setEnabled(true);
      this.btnNewButton.setEnabled(true);
      this.btnNewButton_2.setEnabled(true);
      JOptionPane.showMessageDialog(null, "Terim Kasih " + this.namapelajar.getText() + " kerana Mendaftar, Sila tekan Butang Chrome untuk melayari internet.");
    }
    
    else {
    	this.btnNewButton_2.setEnabled(true);
        JOptionPane.showMessageDialog(null, "Pendaftaran Gagal : Sila Hubungi Juruteknik @ DPM");

    	
    }
	  }
	  else {
		  JOptionPane.showMessageDialog(null,"Sila Isi Maklumat Yang Tepat");
	  }
  }
  
  private Connection connect()
  {
    String url = "jdbc:sqlite:" + configfile;
    Connection conn = null;
    try
    {
      conn = DriverManager.getConnection(url);
    }
    catch (SQLException e)
    {
      System.out.println(e.getMessage());
    }
    return conn;
  }
  
  /* Error */
  public  boolean insert(String nama, String icno, String kelas, String tujuan, String title)
  {
	  
      String sql = "INSERT INTO logbookdpm(nama,icno,kelas,tujuan,komputer) VALUES(?,?,?,?,?)";
      
      try (Connection conn = this.connect();
              PreparedStatement pstmt = conn.prepareStatement(sql)) {
          pstmt.setString(1, nama);
          pstmt.setString(2, icno);
          pstmt.setString(3, kelas);
          pstmt.setString(4, tujuan);
          pstmt.setString(5, title);
          pstmt.executeUpdate();
          return true;       
      } catch (SQLException e) {
          System.out.println(e.getMessage());
          JOptionPane.showMessageDialog(null,e.getMessage());
          return false;
          
          
      }

 
	
  }
  
  protected JButton getBtnNewButton_1()
  {
    return this.btnNewButton_1;
  }
  
  protected JButton getBtnNewButton()
  {
    return this.btnNewButton;
  }
}

class chromeThread extends Thread {

long masa;

	public void setMasa(long e) {
		masa = e;
	}
	public void run() {
		
    try {
    // create a new process
    System.out.println("Creating Process...");
    Process p = Runtime.getRuntime().exec(front.chromepath);

    // wait 10 seconds
    System.out.println("Waiting...");
    Thread.sleep(masa);

    // kill the process
    p.destroy();
//    JOptionPane.showMessageDialog(null, "Masa Anda Tamat!");
    
    System.out.println("Process destroyed.");

 } catch (Exception ex) {
    ex.printStackTrace();
 }
 
				
			
		    
		    
		  }
		
		

public void hostnameset() {
//    // Convert the string to a
//    // byte array.
//    String s = "127.0.0.1 www.youtube.com ";
//    byte data[] = s.getBytes();
////    Path p = Paths.get("C:\\logbook\\logfile.txt");
////
////    try (OutputStream out = new BufferedOutputStream(
////      Files.newOutputStream(p, CREATE, APPEND))) {
////      out.write(data, 0, data.length);
////    } catch (IOException x) {
////      System.err.println(x);
////    }
//
//	    // Convert the string to a
//	    // byte array.
////	    String s = "Hello World! ";
////	    byte data[] = s.getBytes();
//	    Path file = Paths.get("C:\\Windows\\System32\\drivers\\etc\\hosts");
//	    
//	    
////	    
////	    boolean isRegularExecutableFile = Files.isRegularFile(file) &
////	             Files.isReadable(file) & Files.isExecutable(file); isWritable(Path)
//	    
//	    System.out.println("regular"+ Files.isRegularFile(file));
//	    System.out.println("writetable"+ Files.isWritable(file));
//	   
//	    
//	    
//
//	    try (OutputStream out = new BufferedOutputStream(
//	      Files.newOutputStream(file, CREATE, APPEND))) {
//	      out.write(data, 0, data.length);
//	    } catch (IOException x) {
//	      System.err.println(x);
//	    }
//	    

}
}




class TimingMasa extends Thread {
	long in;
	public void setMasa(long e) {
		in = e;
	}
	public void run() {
		
		  try {
			    // create a new process
			    System.out.println("Creating Process...");
		 		Main1 main =  new Main1(); 
		  		main.Main(in);;
		    		

			    // wait in seconds
			    System.out.println("Waiting...");
			    Thread.sleep(in);

			    // kill the process
			  //  Main.exiting();
			    Main1.closing();
			   
			    
			    System.out.println("Process destroyed.");

			 } catch (Exception ex) {
			    ex.printStackTrace();
			 }
			 
		
	}
}





