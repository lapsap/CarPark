// main server to recieve connections 
package carpark;

import java.net.*;
import java.io.*;

public class CarPark_MainServer extends Thread
{
   private ServerSocket serverSocket;
   final static int port = 63400;   //the port i'm opening and listening to
   public String toReply ="";

   private void handleRequest(String[] input)
   {
       switch(input[2])
       {
           case"P":
               if(input[3].equals("S"))
               {
                    toReply=CarPark_Live.tellInfo();
               }
               break;
               
           default:
               System.out.println("switch case errrr(3)");
               return;
       }
   }
   
   private void handleTelling(String[] input)
   {    
     switch(input[2])
     {  
       case "P":           //if the input is from the parking lot,info about parking space
                   if(input[3].equals("I")==true)   //check to see if the car is going in or out
                   {
                       CarPark_Live.parkedIn(input[4], Integer.parseInt(input[5]));
                       
                   }else if(input[3].equals("O")==true){
                        CarPark_Live.parkedOut(input[4], Integer.parseInt(input[5]));
                   }
                   toReply = Integer.toString(CarPark_Live.getEmptyNumber()); //tell the simulator/display parking lot empty number
                   break;   
               
           default:
               System.out.println("switcch case err(2)");
               return;
               
     }  
   }
   
   private void handleClientRawInput(String input)  
   {
       String[] splitted = input.split("");
       switch(splitted[1])  // check to see if data input is a Request or Telling us that the data has changed
       {                    //starts a spllited[1] because splitted[0] is a blank string.
           case "T":    //case telling us something
               handleTelling(splitted);
               break;
           case "R":    //case they are requesting something from us
               handleRequest(splitted);
               break;
           default:
               System.out.println("switch case error handleclientrawinput");
               return;
       }
   }
   
   public void run()
   {
       System.out.println("Server on,Listening on port:" + serverSocket.getLocalPort());  //tell the admin that the server is running
      while(true)
      {    
         try
         {       
            Socket server = serverSocket.accept();      //port start listeining for incomming connection
            DataInputStream in = new DataInputStream(server.getInputStream());
            String clientRawInput = in.readUTF();       //get msg from the client
            System.out.println(server.getRemoteSocketAddress()+ "-"+ clientRawInput); //inform admin that someone has send a msg to the server
            handleClientRawInput(clientRawInput);           //send input to a method for processing
            DataOutputStream out = new DataOutputStream(server.getOutputStream());
            out.writeUTF(toReply);     //this need to change
           
            server.close();
         }catch(SocketTimeoutException s)
         {
            System.out.println("Socket timed out!");
            break;
         }catch(IOException e)
         {
            e.printStackTrace();
            break;
         }
      }
   }
   public static void main(String [] args)
   {
      try
      {
         Thread t = new CarPark_MainServer(port);
         t.start();
      }catch(IOException e)
      {
         e.printStackTrace();
      }
   }
      public CarPark_MainServer(int port) throws IOException
   {
      serverSocket = new ServerSocket(port);
     // serverSocket.setSoTimeout(10000);
   }
}