package carpark;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.*;
import java.net.Socket;
import javax.swing.*;

/**
 *
 * @author chris
 */
public class CarPark_Simulator implements ActionListener{

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
       public static JLabel emptySpaceLabel = new JLabel(Integer.toString(CarPark_Live.getEmptyNumber())); //empty parking lot, default is 64 for this layout
       public static JLabel carsInTheParkingLot = new JLabel(Integer.toString(CarPark_Sql.getCarsInTheParkingLot())); //how many cars in the parking lot,already parked or still finding parking. 
       public String serverReplied = "";
       
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
        button1.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e) {
               CarPark_Sql.ticketIn();
               carsInTheParkingLot.setText(Integer.toString(CarPark_Sql.getCarsInTheParkingLot()));    //needs to change later on
           }
    
        });
        panel.add(button1); 
        
        JTextPane ticketExitId = new JTextPane();
        ticketExitId.setText("Ticket ID Please");
        ticketExitId.setSize(110,20);
        ticketExitId.setLocation(appX-110,appY-100);
        ticketExitId.setBackground(Color.gray);
        ticketExitId.addFocusListener(new FocusListener(){
           @Override
           public void focusGained(FocusEvent e) {
               ticketExitId.setText("");
           }

           @Override
           public void focusLost(FocusEvent e) {
               //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
           }           
        });
        panel.add(ticketExitId);
        
        JButton button2 = new JButton("Exit");
        button2.setLocation(appX-110,appY-80);
        button2.setSize(110,80);
        button2.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e) {
               if( ticketExitId.getText().equals("")==false && ticketExitId.getText().equals("Ticket ID Please")==false )
               {
                    CarPark_Sql.ticketOut(ticketExitId.getText());
               }else{
                   JOptionPane.showMessageDialog(null, "Please type your ticket number.");
               }
               // delete below code soon
               carsInTheParkingLot.setText(Integer.toString(CarPark_Sql.getCarsInTheParkingLot()));
                       
           }
    
        });
        panel.add(button2); 
        
        //
        for(int i=0;i<8;i++)
        {
                blockA[i]=new JButton("A"+i);
                blockA[i].setLocation(0,550-(50*i));
                blockA[i].setSize(110,50);
                blockA[i].setName("A"+i);
                blockA[i].setBackground(Color.green);
                blockA[i].addActionListener(this);
                panel.add(blockA[i]);
        }
        for(int i=0;i<8;i++)
        {
                blockB[i]=new JButton("B"+i);
                blockB[i].setLocation(100+(50*i),40);
                blockB[i].setSize(50,110);
                blockB[i].setName("B"+i);
                blockB[i].setBackground(Color.green);
                blockB[i].addActionListener(this);
                panel.add(blockB[i]);
        }
        for(int i=0;i<8;i++)
        {
                blockC[i]=new JButton("C"+i);
                blockC[i].setLocation(500+(50*i),40);
                blockC[i].setSize(50,110);
                blockC[i].setName("C"+i);
                blockC[i].setBackground(Color.green);
                blockC[i].addActionListener(this);
                panel.add(blockC[i]);
        }
        for(int i=0;i<8;i++)
        {
                blockD[i]=new JButton("D"+i);
                blockD[i].setLocation(appX-110,550-(50*i));
                blockD[i].setSize(110,50);
                blockD[i].setName("D"+i);
                blockD[i].setBackground(Color.green);
                blockD[i].addActionListener(this);
                panel.add(blockD[i]);
        }
        for(int i=0;i<8;i++)
        {
                blockE[i]=new JButton("E"+i);
                blockE[i].setLocation(appX-320,550-(50*i));
                blockE[i].setSize(110,50);
                blockE[i].setName("E"+i);
                blockE[i].setBackground(Color.green);
                blockE[i].addActionListener(this);
                panel.add(blockE[i]);
        }
         for(int i=0;i<8;i++)
        {
                blockF[i]=new JButton("F"+i);
                blockF[i].setLocation(appX-430,550-(50*i));
                blockF[i].setSize(110,50);
                blockF[i].setName("F"+i);
                blockF[i].setBackground(Color.green);
                blockF[i].addActionListener(this);
                panel.add(blockF[i]);
        } 
          for(int i=0;i<8;i++)
        {
                blockG[i]=new JButton("G"+i);
                blockG[i].setLocation(appX-640,550-(50*i));
                blockG[i].setSize(110,50);
                blockG[i].setName("G"+i);
                blockG[i].setBackground(Color.green);
                blockG[i].addActionListener(this);
                panel.add(blockG[i]);
        } 
           for(int i=0;i<8;i++)
        {
                blockH[i]=new JButton("H"+i);
                blockH[i].setLocation(appX-750,550-(50*i));
                blockH[i].setSize(110,50);
                blockH[i].setName("H"+i);
                blockH[i].setBackground(Color.green);
                blockH[i].addActionListener(this);
                panel.add(blockH[i]);
        } 
        //
        
 
        return totalGUI;
}
   
    private void makeGUI(){
            JFrame frame = new JFrame("Lapsap Mall Level B1");
                   frame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);    
                   frame.setContentPane(this.createContentPane()); //create all the buttons and stuff
                   frame.setResizable(false);
                    
                     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //get clients screen size
                frame.setLocation((screenSize.width-appX)/2,(screenSize.height-appY)/2); // set the frame to the middle of the client's screen
                frame.setSize(appX+20,appY+40);    //add 40 to Y,so that the panel fits into the frame. the scroll
                frame.setVisible(true);
        
              
    }
    private void sendData(String dataToSend)
    {
        try
      {
         Socket client = new Socket(serverName, port);
         OutputStream outToServer = client.getOutputStream();
         DataOutputStream out = new DataOutputStream(outToServer);

        out.writeUTF(dataToSend); 
         InputStream inFromServer = client.getInputStream();
         
         DataInputStream in = new DataInputStream(inFromServer);
         serverReplied =(in.readUTF());
         client.close();
      }catch(IOException e)
      {
         e.printStackTrace();
      }
    }
    public static void main(String[] args) {
        // TODO code application logic here
        CarPark_Simulator form = new CarPark_Simulator();
        form.makeGUI();
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = ((JComponent) e.getSource()).getName();
        
       if( (  (JComponent) e.getSource() ).getBackground()==Color.green)
       {
           ((JComponent) e.getSource()).setBackground(Color.red);
           sendData("TPI"+name);    //tell the server a car is parking in
       }else{
           ((JComponent) e.getSource()).setBackground(Color.green);
            sendData("TPO"+name);
       }
        
       emptySpaceLabel.setText(serverReplied);  //set to display what the server told us
       
    }
    
}
