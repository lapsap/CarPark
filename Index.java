// main index for this project

package carpark;

/**
 *
 * @author chris
 */
public class Index {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
      CarPark_MainServer.main(args);   //start the server,start listening on port(comand line)
      CarPark_Simulator.main(args);  // start the parking lot simulator form
      CarPark_AdminViewer.main(args);
    }
    
}
