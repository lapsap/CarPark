//a class where all the LIVE data of the parking lot is stored

package carpark;

/**
 *
 * @author chris
 */
public class CarPark_Live {
    
    final private static int[] A = new int[64]; //64 empty spaces  
    
    public static void parkedIn(String group, int number)   //a car has taken a parking space
    {
                A[number] = 1;  
    }
    public static void parkedOut(String group, int number)  //a car just left a parking space
    {
                A[number] = 0;           
    }
    public static int getEmptyNumber()  //empty car parking spaces
    {     //recount all the blocks and return number of empty spaces
        int count =0;
        for(int i=0;i<64;i++)
        {
            if(A[i]==0) count++;
        }
        return count;
    }      //count the empty spaces in the parking lot,to display 
    public static String tellInfo(Boolean sqlOn)
    {
        String toSend="";
        for(int i=0;i<64;i++) toSend+=Integer.toString(A[i]);
        toSend+=" ";
        toSend+= Integer.toString(getEmptyNumber());
            if(sqlOn==true) //check to see if the user want to use the sql functions
            {
                toSend+=" ";
                toSend+= Integer.toString(CarPark_Sql.getCarsInTheParkingLot());
            }
        
        return toSend;
    }
    public static void main(String args[]){
    
    }
}
