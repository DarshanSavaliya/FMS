package com.skyjetairlines.view.bean;

import com.skyjetairlines.view.common.CommonViewUtil;

import java.math.BigDecimal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import oracle.jbo.domain.Number;

import java.util.Date;

import javax.faces.event.ActionEvent;

import oracle.jbo.Row;

import org.apache.myfaces.trinidad.event.ReturnEvent;

public class BookingRescheduleBean {
    private Date rescheduleDate;
    private double payableAmount;
    private Date minDate;

    public BookingRescheduleBean() {
        this.minDate = new Date();
        
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String currentDate = formatter.format(minDate);
            minDate = formatter.parse(currentDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        this.rescheduleDate = minDate;
    }

    public boolean isRequirePayment() {
        return Math.round(payableAmount) != 0L;
    }

    public void setRescheduleDate(Date rescheduleDate) {
        this.rescheduleDate = rescheduleDate;
    }

    public Date getRescheduleDate() {
        return rescheduleDate;
    }

    public double getPayableAmount() {
        return payableAmount;
    }
    
    public Date getMinDate() {
        return minDate;
    }

    public void calculatePayableAmount(ActionEvent actionEvent) {
        double amountPaid =
            ((BigDecimal) CommonViewUtil.getAttributeFromIterator("BookingsInstanceForCurrentCustomerIterator",
                                                                  "TotalAmount")).doubleValue();
        int numberOfSeats =
            (int) CommonViewUtil.getIterator("TicketsInstanceForCurrentCustomerIterator").getEstimatedRowCount();
        double price =
            ((Number) CommonViewUtil.getAttributeFromIterator("FlightsInstanceIterator", "Price")).doubleValue();

        payableAmount = (price * numberOfSeats) - amountPaid;
        payableAmount = payableAmount > 0 ? payableAmount : 0;
    }

    public void confirmReschedule() {
        int numberOfSeats =
            (int) CommonViewUtil.getIterator("TicketsInstanceForCurrentCustomerIterator").getEstimatedRowCount();

        CommonViewUtil.getIterator("FlightsInstanceIterator").executeQuery();
        CommonViewUtil.getIterator("FlightsInstanceForTicketsForCurrentCustomerIterator").executeQuery();

        Row flightSelected = CommonViewUtil.getIterator("FlightsInstanceIterator").getCurrentRow();
        flightSelected.setAttribute("AvailableSeats",
                                    ((BigDecimal) flightSelected.getAttribute("AvailableSeats")).intValue() -
                                    numberOfSeats);

        Row bookedFlight =
            CommonViewUtil.getIterator("FlightsInstanceForTicketsForCurrentCustomerIterator").getCurrentRow();
        bookedFlight.setAttribute("AvailableSeats",
                                  ((BigDecimal) bookedFlight.getAttribute("AvailableSeats")).intValue() +
                                  numberOfSeats);

        CommonViewUtil.setAttributeInIterator("TicketFlightsInstanceForTicketsForBookingForCurrentCustomerIterator",
                                              "FlightId", flightSelected.getAttribute("FlightId"));
        CommonViewUtil.setAttributeInIterator("TicketFlightsInstanceForTicketsForBookingForCurrentCustomerIterator",
                                              "Amount", flightSelected.getAttribute("Price"));

        CommonViewUtil.setAttributeInIterator("BookingsInstanceForCurrentCustomerIterator", "TotalAmount",
                                              ((Number) flightSelected.getAttribute("Price")).doubleValue() *
                                              numberOfSeats);
    }

    public void paymentCreatedListener(ReturnEvent returnEvent) {
        CommonViewUtil.getIterator("PaymentMethodsInstanceForCurrentCustomerIterator").executeQuery();
    }
}
