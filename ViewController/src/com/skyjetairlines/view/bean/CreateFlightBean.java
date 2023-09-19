package com.skyjetairlines.view.bean;

import com.skyjetairlines.view.common.CommonViewUtil;

import java.math.BigDecimal;

import java.text.SimpleDateFormat;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;

import java.util.Date;
import java.sql.Timestamp;

import java.text.DateFormat;

import java.time.temporal.ChronoUnit;
import javax.faces.event.ActionEvent;
import java.time.Duration;

import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;

import java.time.ZoneId;

import java.util.Calendar;


import oracle.jbo.Row;
import oracle.jbo.domain.Number;

public class CreateFlightBean {
    private Date fromDate;
    private Date toDate;
    private Date departureTime;
    private Date arrivalTime;
    private int totalSeats;
    private long baseFare;
    private String flightNo;
    private String status = "SCHEDULED";

    public CreateFlightBean() {
        super();
    }
    
      

    public void createFlight(ActionEvent actionEvent) {
        
        LocalDate d1 = fromDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate d2 = toDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalTime d3 = departureTime.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
        LocalTime d4 = arrivalTime.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
        
        int diffDays = d2.getDayOfYear() - d1.getDayOfYear();
        
        String departureAirport = (String)CommonViewUtil.getAttributeFromIterator("DepartureAirportsLOVInstanceIterator",
                                                                  "AirportCode");
        String arrivalAirport = (String)CommonViewUtil.getAttributeFromIterator("ArrivalAirportsLOVInstanceIterator",
                                                                  "AirportCode");
        Calendar departureDateTime = Calendar.getInstance();
        Calendar arrivalDateTime = Calendar.getInstance();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("a");
        String amPm = "";

        
        for (int i = 0; i <= diffDays; i++) {
            
            CommonViewUtil.executeOperation("CreateFlight"); 
            Row createdFlight = CommonViewUtil.getIterator("FlightsInstanceIterator").getCurrentRow();
            
            // Set a specific date
            departureDateTime.set(Calendar.YEAR, d1.getYear());
            departureDateTime.set(Calendar.MONTH, d1.getMonthValue()-1); // Note: Months are zero-based (0 = January, 1 = February, ..., 8 = September)
            

            // Set a specific time
             
            departureDateTime.set(Calendar.MINUTE,d3.getMinute());
            departureDateTime.set(Calendar.SECOND,d3.getSecond());
            amPm = d3.format(formatter);
            if(amPm.equalsIgnoreCase("AM")){
                departureDateTime.set(Calendar.AM_PM,Calendar.AM);
                departureDateTime.set(Calendar.HOUR, d3.getHour());
                departureDateTime.set(Calendar.DAY_OF_MONTH,d1.getDayOfMonth());
            }
            else{
                departureDateTime.set(Calendar.AM_PM,Calendar.PM);
                departureDateTime.set(Calendar.HOUR, d3.getHour()+12);
                departureDateTime.set(Calendar.DAY_OF_MONTH,d1.getDayOfMonth()-1);
            }
            
            // Set a specific date
            arrivalDateTime.set(Calendar.YEAR, d1.getYear());
            arrivalDateTime.set(Calendar.MONTH, d1.getMonthValue()-1); // Note: Months are zero-based (0 = January, 1 = February, ..., 8 = September)
            

            // Set a specific time
            
            arrivalDateTime.set(Calendar.MINUTE, d4.getMinute());
            arrivalDateTime.set(Calendar.SECOND,d4.getSecond());
            amPm = d4.format(formatter);
            if(amPm.equalsIgnoreCase("AM")){
                arrivalDateTime.set(Calendar.AM_PM,Calendar.AM);
                arrivalDateTime.set(Calendar.HOUR, d4.getHour()); 
                arrivalDateTime.set(Calendar.DAY_OF_MONTH, d1.getDayOfMonth());
            }    
            else{
                arrivalDateTime.set(Calendar.AM_PM,Calendar.PM);
                arrivalDateTime.set(Calendar.HOUR, d4.getHour()+12); 
                arrivalDateTime.set(Calendar.DAY_OF_MONTH, d1.getDayOfMonth()-1);
            }
            
            long timeInMillis = departureDateTime.getTimeInMillis();
            long timeInMillis2 = arrivalDateTime.getTimeInMillis();

            // Create a Timestamp using the timeInMillis
            Timestamp departureTimestamp = new Timestamp(timeInMillis);
            Timestamp arrivalTimestamp = new Timestamp(timeInMillis2);
           
            createdFlight.setAttribute("Flightno",flightNo);
            createdFlight.setAttribute("ScheduledDeparture",departureTimestamp);
            createdFlight.setAttribute("ScheduledArrival",arrivalTimestamp);
            createdFlight.setAttribute("ActualDeparture",departureTimestamp);
            createdFlight.setAttribute("ActualArrival",arrivalTimestamp);
            createdFlight.setAttribute("DepartureAirport",departureAirport);
            createdFlight.setAttribute("ArrivalAirport",arrivalAirport);
            createdFlight.setAttribute("TotalSeats",totalSeats);
            createdFlight.setAttribute("AvailableSeats",totalSeats);
            createdFlight.setAttribute("Status",status);
            createdFlight.setAttribute("BaseFare",baseFare);
            
            CommonViewUtil.executeOperation("Commit"); 
            d1 = d1.plusDays(1);    
            
        }
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setBaseFare(long baseFare) {
        this.baseFare = baseFare;
    }

    public long getBaseFare() {
        return baseFare;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public String getFlightNo() {
        return flightNo;
    }
    
    public Date getMinDate() {
        Date current = new Date();
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String currentDate = formatter.format(current);
            current = formatter.parse(currentDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return current;
    }
}
