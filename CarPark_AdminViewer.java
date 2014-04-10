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

       private static final String serverName = "127.0.0.1"; //host server ip
       private static final int port = 63400;                //host server to connect port 
       private static final int appX=1000; // width of this app
       private static final int appY=700;  //height of this app
       private static final JButton[] blockA = new JButton[8];
       private static final JButton[] blockB = new JButton[8];
       private static final JButton[] blockC = new JButton[8];
       private static final JButton[] blockD = new JButton[8];
       private static final JButton[] blockE = new JButton[8];
       private static final JButton[] blockF = new JButton[8];
       private static final JButton[] blockG = new JButton[8];
       private static final JButton[] blockH = new JButton[8];
       private static final JFrame frame = new JFrame("Lapsap Mall Level B1");
       public static JLabel emptySpaceLabel = new JLabel(Integer.toString(CarPark_Live.getEmptyNumber())); //empty parking lot, default is 64 for this layout
       public static JLabel carsInTheParkingLot = new JLabel(Integer.toString(CarPark_Sql.getCarsInTheParkingLot())); //how many cars in the parking lot,already parked or still finding parking. 
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
        carsInTheParkingLot.setSize(30,30);
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
        
        
        for(int i=0;i<8;i++)
        {
                blockA[i]=new JButton("A"+i);
                blockA[i].setLocation(0,550-(50*i));
                blockA[i].setSize(110,50);
                blockA[i].setName("A"+i);
                blockA[i].setBackground(Color.green);
                blockA[i].setEnabled(false);
                panel.add(blockA[i]);
        }
        for(int i=0;i<8;i++)
        {
                blockB[i]=new JButton("B"+i);
                blockB[i].setLocation(100+(50*i),40);
                blockB[i].setSize(50,110);
                blockB[i].setName("B"+i);
                blockB[i].setBackground(Color.green);
                blockB[i].setEnabled(false);
                panel.add(blockB[i]);
        }
        for(int i=0;i<8;i++)
        {
                blockC[i]=new JButton("C"+i);
                blockC[i].setLocation(500+(50*i),40);
                blockC[i].setSize(50,110);
                blockC[i].setName("C"+i);
                blockC[i].setBackground(Color.green);
                blockC[i].setEnabled(false);
                panel.add(blockC[i]);
        }
        for(int i=0;i<8;i++)
        {
                blockD[i]=new JButton("D"+i);
                blockD[i].setLocation(appX-110,550-(50*i));
                blockD[i].setSize(110,50);
                blockD[i].setName("D"+i);
                blockD[i].setBackground(Color.green);
                blockD[i].setEnabled(false);
                panel.add(blockD[i]);
        }
        for(int i=0;i<8;i++)
        {
                blockE[i]=new JButton("E"+i);
                blockE[i].setLocation(appX-320,550-(50*i));
                blockE[i].setSize(110,50);
                blockE[i].setName("E"+i);
                blockE[i].setBackground(Color.green);
                blockE[i].setEnabled(false);
                panel.add(blockE[i]);
        }
         for(int i=0;i<8;i++)
        {
                blockF[i]=new JButton("F"+i);
                blockF[i].setLocation(appX-430,550-(50*i));
                blockF[i].setSize(110,50);
                blockF[i].setName("F"+i);
                blockF[i].setBackground(Color.green);
                blockF[i].setEnabled(false);
                panel.add(blockF[i]);
        } 
          for(int i=0;i<8;i++)
        {
                blockG[i]=new JButton("G"+i);
                blockG[i].setLocation(appX-640,550-(50*i));
                blockG[i].setSize(110,50);
                blockG[i].setName("G"+i);
                blockG[i].setBackground(Color.green);
                blockG[i].setEnabled(false);
                panel.add(blockG[i]);
        } 
           for(int i=0;i<8;i++)
        {
                blockH[i]=new JButton("H"+i);
                blockH[i].setLocation(appX-750,550-(50*i));
                blockH[i].setSize(110,50);
                blockH[i].setName("H"+i);
                blockH[i].setBackground(Color.green);
                blockH[i].setEnabled(false);
                panel.add(blockH[i]);
        } 
    
        
 
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
         int[] statusA = new int[8];
         for(int i=0;i<8;i++)   statusA[i]=Integer.parseInt(input0[i+1]);
         String[] input1 = input[1].split("");
         int[] statusB = new int[8];
         for(int i=0;i<8;i++)   statusB[i]=Integer.parseInt(input1[i+1]);
         String[] input2 = input[2].split("");
         int[] statusC = new int[8];
         for(int i=0;i<8;i++)   statusC[i]=Integer.parseInt(input2[i+1]);
         String[] input3 = input[3].split("");
         int[] statusD = new int[8];
         for(int i=0;i<8;i++)   statusD[i]=Integer.parseInt(input3[i+1]);
         String[] input4 = input[4].split("");
         int[] statusE = new int[8];
         for(int i=0;i<8;i++)   statusE[i]=Integer.parseInt(input4[i+1]);
         String[] input5 = input[5].split("");
         int[] statusF = new int[8];
         for(int i=0;i<8;i++)   statusF[i]=Integer.parseInt(input5[i+1]);
         String[] input6 = input[6].split("");
         int[] statusG = new int[8];
         for(int i=0;i<8;i++)   statusG[i]=Integer.parseInt(input6[i+1]);
         String[] input7 = input[7].split("");
         int[] statusH = new int[8];
         for(int i=0;i<8;i++)   statusH[i]=Integer.parseInt(input7[i+1]);
         
         emptySpaceLabel.setText(input[8]);         //update the display numbers
         carsInTheParkingLot.setText(input[9]);
         
         //set the buttons to green or red
         for(int i=0;i<8;i++)
         {
             if(statusA[i]==0) 
             {  
                 blockA[i].setBackground(Color.green);   
             }else{
                 blockA[i].setBackground(Color.red); 
             }
         } 
         for(int i=0;i<8;i++)
         {
             if(statusB[i]==0) 
             {  
                 blockB[i].setBackground(Color.green);   
             }else{
                 blockB[i].setBackground(Color.red); 
             }
         } 
         for(int i=0;i<8;i++)
         {
             if(statusC[i]==0) 
             {  
                 blockC[i].setBackground(Color.green);   
             }else{
                 blockC[i].setBackground(Color.red); 
             }
         } 
         for(int i=0;i<8;i++)
         {
             if(statusD[i]==0) 
             {  
                 blockD[i].setBackground(Color.green);   
             }else{
                 blockD[i].setBackground(Color.red); 
             }
         } 
         for(int i=0;i<8;i++)
         {
             if(statusE[i]==0) 
             {  
                 blockE[i].setBackground(Color.green);   
             }else{
                 blockE[i].setBackground(Color.red); 
             }
         } 
         for(int i=0;i<8;i++)
         {
             if(statusF[i]==0) 
             {  
                 blockF[i].setBackground(Color.green);   
             }else{
                 blockF[i].setBackground(Color.red); 
             }
         } 
         for(int i=0;i<8;i++)
         {
             if(statusG[i]==0) 
             {  
                 blockG[i].setBackground(Color.green);   
             }else{
                 blockG[i].setBackground(Color.red); 
             }
         } 
         for(int i=0;i<8;i++)
         {
             if(statusH[i]==0) 
             {  
                 blockH[i].setBackground(Color.green);   
             }else{
                 blockH[i].setBackground(Color.red); 
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
         processRequest(in.readUTF().split(" ")); //Split input into 8 bytes
         client.close();
      }catch(IOException e)
      {
         e.printStackTrace();
      }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        CarPark_AdminViewer form = new CarPark_AdminViewer();
        form.makeGUI();
        sendRequest();
        
        timer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run() {
                sendRequest();
            }
            
        },10000,10000); //10milie seconds
        
    }   
    
  public static BufferedImage getScreenShot(
    Component component) {

    BufferedImage image = new BufferedImage(
      component.getWidth(),
      component.getHeight(),
      BufferedImage.TYPE_INT_RGB
      );
    // call the Component's paint method, using
    // the Graphics object of the image.
    component.paint( image.getGraphics() );
    return image;
  }
  
    @Override
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
        
        //testing
         BufferedImage img = getScreenShot(
                frame.getContentPane() );
           
              try {
                // write the image as a PNG
                ImageIO.write(
                  img,
                  "png",
                  new File("screenshot.png"));
              } catch(Exception errr) {
                System.out.println(errr);
              }
        
    }
}
