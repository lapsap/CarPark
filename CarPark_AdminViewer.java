package carpark;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;
import javax.imageio.ImageIO;

/**
 *
 * @author chris
 */
public class CarPark_AdminViewer implements ActionListener{

       private static final String serverName = ConnectDB.serverName; //host server ip
       private static final int port = ConnectDB.port;                //host server to connect port 
       private static final int appX= ConnectDB.appX; // width of this app
       private static final int appY= ConnectDB.appY;  //height of this app
       private static final JButton[] blockA = new JButton[64];
       private static final JFrame frame = new JFrame("Lapsap Mall Car Park Level A");
       public static JLabel emptySpaceLabel = new JLabel(Integer.toString(CarPark_Live.getEmptyNumber())); //empty parking lot, default is 64 for this layout
       public static JLabel carsInTheParkingLot = new JLabel("0"); //how many cars in the parking lot,already parked or still finding parking. 
       private static Timer timer = new Timer();  //request an update every few second
               
      private JPanel createContentPane ()  // create the GUI
{    
       JPanel totalGUI = new JPanel();  // panel to fit all my buttons,forms inside
         totalGUI.setLayout(null);
         
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setLocation(0, 0);
        panel.setSize(1000,700);
        panel.setBackground(Color.white);
         totalGUI.add(panel);
         
        
        JLabel label00 = new JLabel("Cars in the ParkingLot : ");
        label00.setLocation(300,0);
        label00.setSize(200,30);
        panel.add(label00);
        carsInTheParkingLot.setLocation(440,0);
        carsInTheParkingLot.setSize(230,30);
        panel.add(carsInTheParkingLot);
        JLabel label0 = new JLabel("Parking Spaces Avialible : ");
        label0.setLocation(0,0);
        label0.setSize(150,30);
        panel.add(label0);
        emptySpaceLabel.setLocation(150,0);
        emptySpaceLabel.setSize(30,30);
        panel.add(emptySpaceLabel);
        JLabel label1 = new JLabel("<html>Way to Mall.<br> AutoPay Machine.</html>");
        label1.setLocation(0,100);
        label1.setSize(170,50);
        panel.add(label1);   
         JLabel label2 = new JLabel("<html>   Way to Mall.<br> AutoPay Machine.</html>");
        label2.setLocation(appX-100,120);
        label2.setSize(170,50);
        panel.add(label2);  
        JLabel label3 = new JLabel("<html>Way to Mall.</html>");
        label3.setLocation(appX/2,appY-50);
        label3.setSize(170,50);
        panel.add(label3);  
        //
        JButton button1 = new JButton("<html>Entrance<br>to<br>Parking Lot");
        button1.setLocation(0,appY-100);
        button1.setSize(110,100);
        button1.setEnabled(false);
        panel.add(button1); 
        
       
        JButton button2 = new JButton("Exit");
        button2.setLocation(appX-110,appY-80);
        button2.setSize(110,80);
        button2.setEnabled(false);
        panel.add(button2); 
        
        
          //make the buttons GUI
        for(int i=0;i<8;i++)
        {
                blockA[i]=new JButton("A"+i);
                blockA[i].setLocation(0,550-(50*i));
                blockA[i].setSize(110,50);
                blockA[i].setName("A"+i);
                blockA[i].setBackground(Color.green);
                blockA[i].addActionListener(this);
                blockA[i].setEnabled(false);
                panel.add(blockA[i]);
        }
         for(int i=0;i<8;i++)
        {
                blockA[i+8]=new JButton("A"+(i+8));
                blockA[i+8].setLocation(appX-750,550-(50*i));
                blockA[i+8].setSize(110,50);
                blockA[i+8].setName("A"+(i+8));
                blockA[i+8].setBackground(Color.green);
                blockA[i+8].addActionListener(this);
                blockA[i+8].setEnabled(false);
                panel.add(blockA[i+8]);
        } 
         for(int i=0;i<8;i++)
        {
                blockA[i+16]=new JButton("A"+(i+16));
                blockA[i+16].setLocation(appX-640,550-(50*i));
                blockA[i+16].setSize(110,50);
                blockA[i+16].setName("A"+(i+16));
                blockA[i+16].setBackground(Color.green);
                blockA[i+16].addActionListener(this);
                blockA[i+16].setEnabled(false);
                panel.add(blockA[i+16]);
        }
          for(int i=0;i<8;i++)
        {
                blockA[i+24]=new JButton("A"+(i+24));
                blockA[i+24].setLocation(appX-430,550-(50*i));
                blockA[i+24].setSize(110,50);
                blockA[i+24].setName("A"+(i+24));
                blockA[i+24].setBackground(Color.green);
                blockA[i+24].addActionListener(this);
                blockA[i+24].setEnabled(false);
                panel.add(blockA[i+24]);
        } 
        for(int i=0;i<8;i++)
        {
                blockA[i+32]=new JButton("A"+(i+32));
                blockA[i+32].setLocation(appX-320,550-(50*i));
                blockA[i+32].setSize(110,50);
                blockA[i+32].setName("A"+(i+32));
                blockA[i+32].setBackground(Color.green);
                blockA[i+32].addActionListener(this);
                blockA[i+32].setEnabled(false);
                panel.add(blockA[i+32]);
        }  
           for(int i=0;i<8;i++)
        {
                blockA[i+40]=new JButton("A"+(i+40));
                blockA[i+40].setLocation(appX-110,550-(50*i));
                blockA[i+40].setSize(110,50);
                blockA[i+40].setName("A"+(i+40));
                blockA[i+40].setBackground(Color.green);
                blockA[i+40].addActionListener(this);
                blockA[i+40].setEnabled(false);
                panel.add(blockA[i+40]);
        }
        for(int i=0;i<16;i++)
        {
                blockA[i+48]=new JButton("<html>A<br>"+(i+48)+"</html>");
                blockA[i+48].setLocation(100+(50*i),40);
                blockA[i+48].setSize(50,110);
                blockA[i+48].setName("A"+(i+48));
                blockA[i+48].setBackground(Color.green);
                blockA[i+48].addActionListener(this);
                blockA[i+48].setEnabled(false);
                panel.add(blockA[i+48]);
        }
       //
    
        
 
        return totalGUI;
}
   
    private void makeGUI(){
                   frame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);    
                   frame.setContentPane(this.createContentPane()); //create all the buttons and stuff
                   frame.setResizable(false);
                   
              //make jmenu              
        JMenuBar menu = new JMenuBar();
        frame.setJMenuBar(menu);
        JMenu file = new JMenu("Update Rate");
        menu.add(file);
        JMenuItem oneSecond = new JMenuItem("1s ");
        oneSecond.setName("1000");
        oneSecond.addActionListener(this);
        file.add(oneSecond);
        JMenuItem fiveSecond = new JMenuItem("5s ");
        fiveSecond.setName("5000");
        fiveSecond.addActionListener(this);
        file.add(fiveSecond);
        JMenuItem tenSecond = new JMenuItem("10s ");
        tenSecond.setName("10000");
        tenSecond.addActionListener(this);
        file.add(tenSecond);
        JMenuItem thirtySecond = new JMenuItem("30s ");
        thirtySecond.setName("30000");
        thirtySecond.addActionListener(this);
        file.add(thirtySecond);
        JMenuItem oneMinute = new JMenuItem("1m ");
        oneMinute.setName("60000");
        oneMinute.addActionListener(this);
        file.add(oneMinute);
        JMenuItem tenMinute = new JMenuItem("10m ");
        tenMinute.setName("600000");
        tenMinute.addActionListener(this);
        file.add(tenMinute);
        //
                     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //get clients screen size
                frame.setLocation((screenSize.width-appX)/2,(screenSize.height-appY)/2); // set the frame to the middle of the client's screen
                frame.setSize(appX+20,appY+40);    //add 40 to Y,so that the panel fits into the frame. the scroll
                frame.setVisible(true);
        
              
    }
     private static void processRequest(String[] input)
     {
         String[] input0 = input[0].split("");  //split it into 8 bits
         int[] statusA = new int[64];
         for(int i=0;i<64;i++)   statusA[i]=Integer.parseInt(input0[i+1]);
      
         
         emptySpaceLabel.setText(input[1]);         //update the display numbers
         carsInTheParkingLot.setText("ticketing function not in use");
         if(ConnectDB.sqlOn==true)carsInTheParkingLot.setText(input[2]);   //only if the server is online
         
         
         //set the buttons to green or red
         for(int i=0;i<64;i++)
         {
             if(statusA[i]==0) 
             {  
                 blockA[i].setBackground(Color.green);   
             }else{
                 blockA[i].setBackground(Color.red); 
             }
         } 
    
     }
     private static void sendRequest()
    {
        try
      {
         Socket client = new Socket(serverName, port);
         OutputStream outToServer = client.getOutputStream();
         DataOutputStream out = new DataOutputStream(outToServer);

        out.writeUTF("RPS"); 
         InputStream inFromServer = client.getInputStream();
         
         DataInputStream in = new DataInputStream(inFromServer);
         processRequest(in.readUTF().split(" ")); //Split input into 3
         client.close();
      }catch(IOException e)
      {
        System.out.println("CarPark_AdminViewer error :"+e);
      }
    }
    
    public static void main(String[] args) {
        CarPark_AdminViewer form = new CarPark_AdminViewer();
        form.makeGUI();
        sendRequest();
        
        timer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run() {
                sendRequest();
                takePic();
            }
            
        },10000,10000); //10milie seconds
        
    }   
  private static void takePic()
  {
              //testing take pic
         BufferedImage img = getScreenShot(
                frame.getContentPane() );
           
              try {
                // write the image as a PNG
                ImageIO.write(
                  img,
                  "png",
                  new File("screenshot.png"));
              } catch(IOException errr) {
                System.out.println(errr);
              }
  }
  private static BufferedImage getScreenShot(    //for take pic
    Component component) {

    BufferedImage image = new BufferedImage(
      component.getWidth(),
      component.getHeight(),
      BufferedImage.TYPE_INT_RGB
      );
    component.paint( image.getGraphics() );
    return image;
  }
  
    @Override     // JMenu clicked
    public void actionPerformed(ActionEvent e) {      
       String name = ((JComponent) e.getSource()).getName();
      int updateRate = Integer.parseInt(name);  //get the time rate the user clicked
       
       timer.cancel();      //cancel the old timer
      timer = new Timer();  //set a new timer, but still under the old timer's name
        timer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run() {
                sendRequest();  //send request to server
            }           
        },updateRate,updateRate);   
        
    }       
}
