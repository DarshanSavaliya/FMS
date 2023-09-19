package com.skyjetairlines.view.bean;

import com.skyjetairlines.view.common.CommonViewUtil;

import java.math.BigDecimal;

import java.text.SimpleDateFormat;

import java.util.Date;

import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.event.DialogEvent;

import oracle.adf.view.rich.event.PopupCanceledEvent;
import oracle.adf.view.rich.event.PopupFetchEvent;

import oracle.jbo.Row;
import java.text.ParseException;
public class ManageFlightBean {
    public ManageFlightBean() {
    }

    public void deleteAndCommit(DialogEvent dialogEvent) {
        Row selectedFlight = CommonViewUtil.getIterator("FlightsInstanceIterator").getCurrentRow();
        String aarv= selectedFlight.getAttribute("ActualArrival").toString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        try{
            Date arv = dateFormat.parse(aarv),today=new Date();
            if(today.getTime()-arv.getTime()<=0) {
                CommonViewUtil.showErrorMessage("You cannot delte a flight that has not arrived");
            }
            else{
                CommonViewUtil.executeOperation("DeleteFlight");
                CommonViewUtil.executeOperation("Commit");
            }
        }
        catch(ParseException e){
            e.printStackTrace();
        }
        
        CommonViewUtil.executeOperation("DeleteFlight");
        CommonViewUtil.executeOperation("Commit");
    }

    public void commitAddFlight(DialogEvent dialogEvent) {
        Row selectedFlight = CommonViewUtil.getIterator("FlightsInstanceIterator").getCurrentRow();
        String dpt= selectedFlight.getAttribute("ScheduledDeparture").toString();
        String arv= selectedFlight.getAttribute("ScheduledArrival").toString();
        Date arrival,departure,today = new Date();
        BigDecimal numofSeats = new BigDecimal(selectedFlight.getAttribute("TotalSeats").toString());
        BigDecimal basefare = new BigDecimal(selectedFlight.getAttribute("BaseFare").toString());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        try{
            arrival = dateFormat.parse(arv);
            departure = dateFormat.parse(dpt);
            long timeDifferenceMillis = arrival.getTime() - departure.getTime();
            if(arrival.getTime()-today.getTime()<90000) {
                CommonViewUtil.showErrorMessage("You should select the Arrival time 1-2 minute after current time");
            }
            if(timeDifferenceMillis < 3600000L) {
                CommonViewUtil.showErrorMessage("Departure time and arrival time should have the positive difference of 1 or more hour");
            }
            else if(timeDifferenceMillis > 57600000L) {
                CommonViewUtil.showErrorMessage("A Flight cannot have gap of more than 16 hours between departure and arrival");
            }
            else if(numofSeats.compareTo(new BigDecimal(0))==-1){
                CommonViewUtil.showErrorMessage("Number of Seats Cannot have a negative Value");
            }
            else {
                selectedFlight.setAttribute("ActualDeparture", selectedFlight.getAttribute("ScheduledDeparture"));
                selectedFlight.setAttribute("ActualArrival",selectedFlight.getAttribute("ScheduledArrival"));
                selectedFlight.setAttribute("AvailableSeats", selectedFlight.getAttribute("TotalSeats"));
                CommonViewUtil.showSuccessfulMessage("Successfully Added the flight");
                CommonViewUtil.executeOperation("Commit");
            }
        }
        catch(ParseException e) {
            e.printStackTrace();
        }
        
    }

    public void createinsertflight(PopupFetchEvent popupFetchEvent) {
        CommonViewUtil.executeOperation("CreateInsertFlight");
    }

    public void rollbackpopup(PopupCanceledEvent popupCanceledEvent) {
        CommonViewUtil.executeOperation("Rollback");
    }

    public void editFlight(DialogEvent dialogEvent) {
        Row selectedFlight = CommonViewUtil.getIterator("FlightsInstanceIterator").getCurrentRow();
        String status = selectedFlight.getAttribute("Status").toString();
        if(status.equals("CANCELLED")) {
            CommonViewUtil.showErrorMessage("You cannot edit cancelled flight");
            CommonViewUtil.executeOperation("Rollback");
            return;
        }
        String adpt= selectedFlight.getAttribute("ActualDeparture").toString();
        String aarv= selectedFlight.getAttribute("ActualArrival").toString();
        String sdpt= selectedFlight.getAttribute("ScheduledDeparture").toString();
        String sarv= selectedFlight.getAttribute("ScheduledArrival").toString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        try{
            Date add,aad,sdd,sad;
            add=dateFormat.parse(adpt);
            aad=dateFormat.parse(aarv);
            sdd=dateFormat.parse(sdpt);
            sad=dateFormat.parse(sarv);
            long diff = sad.getTime()-sdd.getTime();
            long adiff = aad.getTime()-add.getTime();
            if(aad.getTime()-add.getTime()<1800000) {
                CommonViewUtil.showErrorMessage("actual arrival cannot be early by 30 minutes from actual departure");
                CommonViewUtil.executeOperation("Rollback");
            }
            if(adiff < diff- 1800000) {
                CommonViewUtil.showErrorMessage("actual dept cannot be early by 30 minutes from scheduled flight duration");
                CommonViewUtil.executeOperation("Rollback");
            }
            else if(adiff > diff + 1800000){
                CommonViewUtil.showErrorMessage("actual dept cannot be late by 30 minutes from scheduled flight duration");
                CommonViewUtil.executeOperation("Rollback");
            }
            else {
                selectedFlight.setAttribute("Status", "DELAYED");
                CommonViewUtil.executeOperation("Commit");
            }
        }
        catch(ParseException e){
            e.printStackTrace();
        }
    }

    public void cancelFlight(ActionEvent actionEvent) {
        Row selectedFlight = CommonViewUtil.getIterator("FlightsInstanceIterator").getCurrentRow();
        String adpt= selectedFlight.getAttribute("ActualDeparture").toString();
        String aarv= selectedFlight.getAttribute("ActualArrival").toString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        try{
            Date dpt,arv,today= new Date();
            dpt=dateFormat.parse(adpt);
            arv=dateFormat.parse(aarv);
            if(today.getTime()-dpt.getTime()>=0 && arv.getTime()-today.getTime() >=0){
                CommonViewUtil.showErrorMessage("You cannot Cancel the ongoing flight");
            }
            else {
                selectedFlight.setAttribute("Status", "CANCELLED");
            }
            
        }
        catch(ParseException e){
            e.printStackTrace();
        }
    }
}
