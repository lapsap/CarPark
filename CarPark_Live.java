//a class where all the LIVE data of the parking lot is stored

package carpark;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author chris
 */
public class CarPark_Live {
    
    final private static int[] A = new int[8]; //parking lot A0,A1,A2,..,A7
    final private static int[] B = new int[8];
    final private static int[] C = new int[8];
    final private static int[] D = new int[8];
    final private static int[] E = new int[8];
    final private static int[] F = new int[8];
    final private static int[] G = new int[8];
    final private static int[] H = new int[8];
    
    
    public static void parkedIn(String group, int number)   //a car has taken a parking space
    {
        switch(group)
        {
            case "A":
                A[number] = 1;
                break;
            case "B":
                B[number] = 1;
                break;
            case "C":
                C[number] = 1;
                break;
            case "D":
                D[number] = 1;
                break;
            case "E":
                E[number] = 1;
                break;
            case "F":
                F[number] = 1;
                break;
            case "G":
                G[number] = 1;
                break;
            case "H":
                H[number] = 1;
                break;
                
            default:
                System.out.println("Parkingspaces default err");
                return;
        } 
    }
    public static void parkedOut(String group, int number)  //a car just left a parking space
    {
         switch(group)
        {
           case "A":
                A[number] = 0;
                break;
            case "B":
                B[number] = 0;
                break;
            case "C":
                C[number] = 0;
                break;
            case "D":
                D[number] = 0;
                break;
            case "E":
                E[number] = 0;
                break;
            case "F":
                F[number] = 0;
                break;
            case "G":
                G[number] = 0;
                break;
            case "H":
                H[number] = 0;
                break;
                
            default:
                System.out.println("default err");
                return;
        }
    }
    public static int getEmptyNumber()  //empty car parking spaces
    {     //recount all the blocks and return number of empty spaces
        int count =0;
        for(int i=0;i<8;i++)
        {
            if(A[i]==0) count++;
            if(B[i]==0) count++;
            if(C[i]==0) count++;
            if(D[i]==0) count++;
            if(E[i]==0) count++;
            if(F[i]==0) count++;
            if(G[i]==0) count++;
            if(H[i]==0) count++;
        }
        return count;
    }      //count the empty spaces in the parking lot,to display 
    public static String tellInfo()
    {
        String toSend="";
        for(int i=0;i<8;i++) toSend+=Integer.toString(A[i]);
        toSend+=" ";
        for(int i=0;i<8;i++) toSend+=Integer.toString(B[i]);
        toSend+=" ";
        for(int i=0;i<8;i++) toSend+=Integer.toString(C[i]);
        toSend+=" ";
        for(int i=0;i<8;i++) toSend+=Integer.toString(D[i]);
        toSend+=" ";
        for(int i=0;i<8;i++) toSend+=Integer.toString(E[i]);
        toSend+=" ";
        for(int i=0;i<8;i++) toSend+=Integer.toString(F[i]);
        toSend+=" ";
        for(int i=0;i<8;i++) toSend+=Integer.toString(G[i]);
        toSend+=" ";
        for(int i=0;i<8;i++) toSend+=Integer.toString(H[i]);
        toSend+=" ";
        toSend+= Integer.toString(getEmptyNumber());
        toSend+=" ";
        toSend+= Integer.toString(CarPark_Sql.getCarsInTheParkingLot());
        
        return toSend;
    }
    public static void main(String args[]){
     //   System.out.println(tellInfo());
    }
}
