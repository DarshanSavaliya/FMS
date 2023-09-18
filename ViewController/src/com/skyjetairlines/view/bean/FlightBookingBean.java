package com.skyjetairlines.view.bean;

import com.skyjetairlines.view.common.CommonViewUtil;

import java.math.BigDecimal;

import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.event.DialogEvent;

import oracle.jbo.Row;
import oracle.jbo.domain.Number;

import org.apache.myfaces.trinidad.event.ReturnEvent;

public class FlightBookingBean {
    private int numberOfSeats;
    private double bookingAmount;
    private double discount;
    private boolean couponApplied;

    public FlightBookingBean() {
        discount = 0;
        couponApplied = false;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }
    
    public double getBookingAmount() {
        return bookingAmount;
    }

    public boolean isCouponApplied() {
        return couponApplied;
    }

    public void createBooking() {
        CommonViewUtil.executeOperation("CreateBooking");

        Row flightSelected = CommonViewUtil.getIterator("FlightsInstanceIterator").getCurrentRow();

        double price = ((Number) flightSelected.getAttribute("Price")).doubleValue();
        bookingAmount = price * numberOfSeats;
        CommonViewUtil.setAttributeInIterator("BookingsInstanceForCurrentCustomerIterator", "TotalAmount",
                                              bookingAmount);

        for (int i = 0; i < numberOfSeats; i++) {
            CommonViewUtil.executeOperation("CreateTicket");
            CommonViewUtil.executeOperation("CreateFlightAssociation");

            Row ticketFlightAssoc =
                CommonViewUtil.getIterator("TicketFlightsInstanceForTicketsForBookingForCurrentCustomerIterator")
                .getCurrentRow();

            ticketFlightAssoc.setAttribute("FlightId", flightSelected.getAttribute("FlightId"));
            ticketFlightAssoc.setAttribute("Amount", flightSelected.getAttribute("Price"));
        }
    }

    public void PaymentCreatedListener(ReturnEvent returnEvent) {
        CommonViewUtil.getIterator("PaymentMethodsInstanceForCurrentCustomerIterator").executeQuery();
    }

    // TODO
    public void PassengerCreatedListener(ReturnEvent returnEvent) {
        CommonViewUtil.getIterator("PassengersInstanceForCurrentCustomerIterator").executeQuery();
        CommonViewUtil.getIterator("TicketsInstanceForCurrentCustomerIterator").executeQuery();
    }

    public void confirmBooking() {
        Row flightSelected = CommonViewUtil.getIterator("FlightsInstanceIterator").getCurrentRow();
        flightSelected.refresh(Row.REFRESH_WITH_DB_FORGET_CHANGES);

        flightSelected.setAttribute("AvailableSeats",
                                    ((BigDecimal) flightSelected.getAttribute("AvailableSeats")).intValue() -
                                    numberOfSeats);
    }

    public void removeAppliedCoupon(ActionEvent actionEvent) {
        discount = 0.0;
        couponApplied = false;
        CommonViewUtil.setAttributeInIterator("BookingsInstanceForCurrentCustomerIterator", "TotalAmount",
                                              bookingAmount);
    }

    public void applyCoupon(ActionEvent actionEvent) {
        discount =
            ((BigDecimal) CommonViewUtil.getAttributeFromIterator("CouponsInstanceForFlightsIterator", "Discount"))
            .doubleValue();
        couponApplied = true;

        CommonViewUtil.setAttributeInIterator("BookingsInstanceForCurrentCustomerIterator", "TotalAmount",
                                              (bookingAmount > discount) ? (bookingAmount - discount) : 0.0);
    }
}
