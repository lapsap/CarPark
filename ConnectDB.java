package carpark;

import java.sql.*;

/**
 *
 * @author chris
 */
public class ConnectDB 
{
    //all the config stuff
    public static final String serverName = "127.0.0.1"; //host server ip
    public static final int port = 4848;                //host server to connect port 
    public static final int appX = 1000;            //width size of app
    public static final int appY = 700;             // height size of app
    public static final String DBname ="lapsapcarpark"; //name of the database
    public static final String DBid ="root";        //MySQL database id
    public static final String DBpass ="";          //MySQL database password
    public static Boolean sqlOn=true;
    //
    public static Connection connect()
    {
        try
        {
          Class.forName("com.mysql.jdbc.Driver"); // install mysql driver ( need sql.connect.jar for this)
          Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/"+DBname+"?characterEncoding=utf8", ""+DBid+"", ""+DBpass+""); //Connect to database 
          return conn;
        }catch(Exception e)
        {
        //    e.printStackTrace();
            sqlOn=false;
            System.out.println("MySqlServer Off-Line");
        }
       return null;
    }
}
