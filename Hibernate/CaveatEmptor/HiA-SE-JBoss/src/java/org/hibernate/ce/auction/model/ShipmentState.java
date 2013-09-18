package org.hibernate.ce.auction.model;

/**
 * State of a shipment under escrow.
 *
 * @author christian@hibernate.org
 */
public enum ShipmentState {

    AGREED, PAYED, IN_TRANSIT, ACCEPTED, COMPLETE;

}