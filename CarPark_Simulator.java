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

       private static final String serverName = ConnectDB.serverName; //host server ip
       private static final int port = ConnectDB.port;                //host server to connect port 
       private static final int appX=ConnectDB.appX; // width of this app
       private static final int appY=ConnectDB.appY;  //height of this app
       private static final JButton[] blockA = new JButton[64];
       public static JLabel emptySpaceLabel = new JLabel("64"); //empty parking lot, default is 64 for this layout
       public static JLabel carsInTheParkingLot = new JLabel("0"); //how many cars in the parking lot,already parked or still finding parking. 
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
        button1.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e)
           {
               carsInTheParkingLot.setText("ticketing function not in use");
               if(ConnectDB.sqlOn==true)//this function works only if the sql server is on
               {
                    CarPark_Sql.ticketIn();
                    carsInTheParkingLot.setText(Integer.toString(CarPark_Sql.getCarsInTheParkingLot()));    //needs to change later on
               }
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
           public void actionPerformed(ActionEvent e)
           {
               carsInTheParkingLot.setText("ticketing function not in use");
                if(ConnectDB.sqlOn==true)//function will only work if sql server is on
                {
                        if(ticketExitId.getText().equals("")==false && ticketExitId.getText().equals("Ticket ID Please")==false )
                        {
                             CarPark_Sql.ticketOut(ticketExitId.getText());      //send ticket id to the method in class carpark_sql
                             JOptionPane.showMessageDialog(null, "Done!\nYou just left.");   //inform myself tat i left lol
                             ticketExitId.setText("Ticket ID Please");           //set the text back to the default
                        }else{
                            JOptionPane.showMessageDialog(null, "Please type your ticket number.");
                        }
                        carsInTheParkingLot.setText(Integer.toString(CarPark_Sql.getCarsInTheParkingLot())); //set the display label       
                }
           }
    
        });
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
                panel.add(blockA[i+48]);
        }
       //
 
        return totalGUI;
}
   
    private void makeGUI(){
            JFrame frame = new JFrame("Lapsap Mall Car Park Level A");
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
