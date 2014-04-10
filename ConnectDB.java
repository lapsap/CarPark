package carpark;

import java.sql.*;

/**
 *
 * @author chris
 */
public class ConnectDB 
{
    public static Connection connect()
    {
        try
        {
                Class.forName("com.mysql.jdbc.Driver"); // install mysql driver ( need sql.connect.jar for this)
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/lapsapcarpark?characterEncoding=utf8", "root", ""); //Connect to database 'lapsapjava'
                return conn;
        }catch(Exception errrr){}
       return null;
    }
}
