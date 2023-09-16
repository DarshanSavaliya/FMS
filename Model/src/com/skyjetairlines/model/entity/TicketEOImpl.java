package com.skyjetairlines.model.entity;

import java.math.BigDecimal;

import oracle.jbo.Key;
import oracle.jbo.RowIterator;
import oracle.jbo.server.EntityDefImpl;
import oracle.jbo.server.EntityImpl;
import oracle.jbo.server.TransactionEvent;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Sat Sep 16 14:31:24 IST 2023
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class TicketEOImpl extends EntityImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    protected enum AttributesEnum {
        TicketId,
        BookingId,
        PassengerId,
        Booking,
        Passenger,
        Flight;
        private static AttributesEnum[] vals = null;
        private static final int firstIndex = 0;

        protected int index() {
            return AttributesEnum.firstIndex() + ordinal();
        }

        protected static final int firstIndex() {
            return firstIndex;
        }

        protected static int count() {
            return AttributesEnum.firstIndex() + AttributesEnum.staticValues().length;
        }

        protected static final AttributesEnum[] staticValues() {
            if (vals == null) {
                vals = AttributesEnum.values();
            }
            return vals;
        }
    }
    public static final int TICKETID = AttributesEnum.TicketId.index();
    public static final int BOOKINGID = AttributesEnum.BookingId.index();
    public static final int PASSENGERID = AttributesEnum.PassengerId.index();
    public static final int BOOKING = AttributesEnum.Booking.index();
    public static final int PASSENGER = AttributesEnum.Passenger.index();
    public static final int FLIGHT = AttributesEnum.Flight.index();

    /**
     * This is the default constructor (do not remove).
     */
    public TicketEOImpl() {
    }

    /**
     * Gets the attribute value for TicketId, using the alias name TicketId.
     * @return the value of TicketId
     */
    public BigDecimal getTicketId() {
        return (BigDecimal) getAttributeInternal(TICKETID);
    }

    /**
     * Sets <code>value</code> as the attribute value for TicketId.
     * @param value value to set the TicketId
     */
    public void setTicketId(BigDecimal value) {
        setAttributeInternal(TICKETID, value);
    }

    /**
     * Gets the attribute value for BookingId, using the alias name BookingId.
     * @return the value of BookingId
     */
    public BigDecimal getBookingId() {
        return (BigDecimal) getAttributeInternal(BOOKINGID);
    }

    /**
     * Sets <code>value</code> as the attribute value for BookingId.
     * @param value value to set the BookingId
     */
    public void setBookingId(BigDecimal value) {
        setAttributeInternal(BOOKINGID, value);
    }

    /**
     * Gets the attribute value for PassengerId, using the alias name PassengerId.
     * @return the value of PassengerId
     */
    public BigDecimal getPassengerId() {
        return (BigDecimal) getAttributeInternal(PASSENGERID);
    }

    /**
     * Sets <code>value</code> as the attribute value for PassengerId.
     * @param value value to set the PassengerId
     */
    public void setPassengerId(BigDecimal value) {
        setAttributeInternal(PASSENGERID, value);
    }

    /**
     * @return the associated entity oracle.jbo.server.EntityImpl.
     */
    public EntityImpl getBooking() {
        return (EntityImpl) getAttributeInternal(BOOKING);
    }

    /**
     * Sets <code>value</code> as the associated entity oracle.jbo.server.EntityImpl.
     */
    public void setBooking(EntityImpl value) {
        setAttributeInternal(BOOKING, value);
    }

    /**
     * @return the associated entity oracle.jbo.server.EntityImpl.
     */
    public EntityImpl getPassenger() {
        return (EntityImpl) getAttributeInternal(PASSENGER);
    }

    /**
     * Sets <code>value</code> as the associated entity oracle.jbo.server.EntityImpl.
     */
    public void setPassenger(EntityImpl value) {
        setAttributeInternal(PASSENGER, value);
    }

    /**
     * @return the associated entity oracle.jbo.RowIterator.
     */
    public RowIterator getFlight() {
        return (RowIterator) getAttributeInternal(FLIGHT);
    }

    /**
     * @param ticketId key constituent

     * @return a Key object based on given key constituents.
     */
    public static Key createPrimaryKey(BigDecimal ticketId) {
        return new Key(new Object[] { ticketId });
    }

    /**
     * @return the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        return EntityDefImpl.findDefObject("com.skyjetairlines.model.entity.TicketEO");
    }
    
    public void postChanges(TransactionEvent transactionEvent) {
        if (getPostState() == STATUS_NEW || getPostState() == STATUS_MODIFIED) {
            EntityImpl booking = getBooking();
            if (booking != null) {
                if (booking.getPostState() == STATUS_NEW) {
                    booking.postChanges(transactionEvent);
                }
            }
        }
        super.postChanges(transactionEvent);
    }
}

