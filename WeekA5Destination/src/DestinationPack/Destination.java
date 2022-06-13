
package DestinationPack;

import java.io.Serializable;
import java.util.*;
import javafx.scene.control.Alert;

/**
 * Destination is the model class that provides basic functionality and exception handling.
 * @author ryanteixeira
 */
public class Destination implements Serializable{
    
    String destName;
    String duration;
    String airline;
    String year;
    String comment;

    /**
     * A Destination class default constructor.
     */
    public Destination(){
        
    }
    
    /**
     * A Destination class constructor that accepts five strings.
     * @param destName sets the destName.
     * @param duration sets the duration.
     * @param airline sets the airline.
     * @param year sets the year.
     * @param comment sets the comment.
     */
    public Destination(String destName, String duration, String airline, String year, String comment) {
        this.destName = destName;
        this.duration = duration;
        this.airline = airline;
        this.year = year;
        this.comment = comment;
    }

    /**
     * @return the String destName.
     */
    public String getDestName() {
        return destName;
    }

    /**
     * @param destName a String value to set destName.
     * @throws Exception is an object that wraps an error event that occurred within a method 
     * and contains: Information about the error including its type. 
     */
    public void setDestName(String destName) throws Exception {
        if (destName.isEmpty()) {
            throw new Exception("Destination name can not be empty");
        } else {
            this.destName = destName;
        }

    }

    /**
     * @return the String duration.
     */
    public String getDuration() {
        return duration;
    }

    /**
     * @param duration to set the String duration.
     * @throws Exception is an object that wraps an error event that occurred within a method 
     * and contains: Information about the error including its type. 
     */
    public void setDuration(String duration) throws Exception {
        if (duration.isEmpty()) {
            throw new Exception("Duration can not be empty");
        } else {
            this.duration = duration;
        }

    }

    /**
     * @return the String airline.
     */
    public String getAirline() {
        return airline;
    }

    /**
     * @param airline sets this.airline.
     */
    public void setAirline(String airline) {
        this.airline = airline;
    }

    /**
     * @return the String year.
     */
    public String getYear() {
        return year;
    }

    /**
     * @param year is checked to see if it is between year 2000 and 2021 and error handling to check if the input
     * as a number.
     * @throws Exception is an object that wraps an error event that occurred within a method 
     * and contains: Information about the error including its type. 
     */
    public void setYear(String year) throws Exception  {

        try{
        int yearInt = Integer.parseInt(year);
            
                System.out.println(yearInt);
        
                
                if (yearInt < 2000 || yearInt > 2021) {
                    throw new Exception();
                    
                } else {
                    this.year = year;

                }

            } catch (NumberFormatException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Year not found");
            alert.setHeaderText("Input Error Alert");
            String s = "Year field cannot be empty";

            alert.setContentText(s);
            alert.show();

            System.out.println("The year field was empty");
            this.year = "empty";

            }
        
            catch(Exception f){
                
                Alert alert = new Alert(Alert.AlertType.ERROR);
                
                alert.setTitle("Year not found");
                alert.setHeaderText("User Input Alert");
                String s = "You entered " + year + " "
                            + "but year has to be between 2000 and 2021";
                
                alert.setContentText(s);
                alert.show();
                
                
                System.out.println("You entered " + year + " "
                            + "but year has to be between 2000 and 2021");
                this.year="empty";
                
            }
        
        
    }

    /**
     * @return the String comment.
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment sets the comment value.
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
    
    
    /**
     * @return a String representation of destName, duration, airline, year and comment.
     */
    @Override
    public String toString() {
        return "" + this.getDestName() + ", "
                + this.getDuration() + ", " + this.getAirline() + ", "
                + this.getYear() + ", " + this.getComment();
    }
    
    
}
