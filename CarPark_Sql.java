package carpark;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author chris
 */
public class CarPark_Sql {
    
        private String Date = new SimpleDateFormat("d-MM-yyyy").format(Calendar.getInstance().getTime()); //cant be static or else the time wont change
        private String Time = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime()); 
        private String Day = new SimpleDateFormat("EEE").format(Calendar.getInstance().getTime()); 
        final private static int hourRate = 2;
    
      private static String countRate(String oldTime)
    {
        CarPark_Sql form = new CarPark_Sql();
         String[] now = form.Time.split(":");
         String[] old = oldTime.split(":");
         
         int hours =-1;     //set 1st hour inside free
         hours += Integer.parseInt(now[0]) - Integer.parseInt(old[0]);      //(hours) now minus (hours)when entered
         if(Integer.parseInt(now[1])>Integer.parseInt(old[1])) hours++;         //see if the minutes add up to an hour

         String reply="";
         
         int fee = hours * hourRate;    //hours visted Times the price rate per hour
         if(fee>0)
         {
             reply = "RM " +Integer.toString(fee);
         }else{ //if visitor left within an hour
             reply ="FREE";
         }
         
         
         return reply;
    }
        public static int getCarsInTheParkingLot()
    {
        int count =0;
            try{
                Connection connection = ConnectDB.connect();
                Statement statement = connection.createStatement();
                String lapsapQuery ="SELECT * FROM lapsapticket WHERE exittime IS NULL";
                ResultSet lapsapSet = statement.executeQuery(lapsapQuery);
                while(lapsapSet.next())
                {
                    count++;
                }
                lapsapSet.close();
                statement.close();
            }catch(Exception err){ System.out.println("parkinglot getcarsinparkinglot err"); }
                //
        
        return count;
    }
      public static void ticketIn()
    {
      try{
            CarPark_Sql form= new CarPark_Sql();  //initialize this to use date,time,day
            //insert data into log
            Connection conn = ConnectDB.connect(); // connection to ConnectDB class       
            conn.createStatement().execute("INSERT INTO `lapsapticket` (day,date,entrytime) VALUES ('"+form.Day+"','"+form.Date+"','"+form.Time+"')"); //Insert data to table 'testone'
            conn.close();  // close mysql connection
         }
             catch (SQLException err){System.out.println("TicketIn "+err);}
    }
    public static void ticketOut(String ID)
    {
      try{
              CarPark_Sql form= new CarPark_Sql();
              //get entry time from the log
                Connection connection = ConnectDB.connect();
                Statement statement = connection.createStatement();
                String lapsapQuery ="SELECT entrytime FROM lapsapticket WHERE id ='"+ID+"'";
                ResultSet lapsapSet = statement.executeQuery(lapsapQuery);
                lapsapSet.next();
                    String oldTime = lapsapSet.getString("entrytime"); 
                lapsapSet.close();
                statement.close();
                //
              String fee = countRate(oldTime);     //calculate pay rate
              Connection conn = ConnectDB.connect(); // connection to ConnectDB class     
              String query ="UPDATE lapsapticket SET parkingfee = '"+fee+"',exittime = '"+form.Time+"' WHERE id ='"+ID+"'";
              conn.createStatement().execute(query); //Insert data to table 'testone'
              conn.close();  // close mysql connection
         }
             catch (SQLException err){System.out.println("ticketout "+err);}    
    }
    
}
