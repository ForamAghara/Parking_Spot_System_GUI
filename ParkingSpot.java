
/**
 * ParkingSpot Class
 * This class stores the data for each parking spot 
 * @author - Foram Aghara
 * @version - 2.0.0
 */

public class ParkingSpot
{
    // Declaring the variables for slotid and the car and also to determine whether owner is staff member or not
    private String parking_slotID;
    private Boolean parking_isStaff;
    private Car parking_car;
    
    // initialising the class variables using constructors
    public ParkingSpot(String slot, String staff, Car cr)
    {
        this.parking_slotID = slot;
        if(staff.equals("yes"))
        {
            this.parking_isStaff = true;
        }
        else if(staff.equals("no"))
        {
            this.parking_isStaff = false;
        }
        this.parking_car = cr;
    }


    
   
}
