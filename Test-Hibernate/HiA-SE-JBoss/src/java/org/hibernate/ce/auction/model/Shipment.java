package org.hibernate.ce.auction.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Escrow base class for CaveatEmptor.
 *
 * A particular shipment under supervision, from a seller to a buyer.
 * <p>
 * The process (see <tt>ShipmentState</tt>) is supposed to be:
 * <li>AGREED: Both parties agree on the deal and a Shipment is created.
 * <li>PAYED: The buyer payed the shipment.
 * <li>IN_TRANSIT: The seller sent the shipment.
 * <li>ACCEPTED: The buyer accepted the shipment in the inspection period.
 * <li>COMPLETE: The payment has been transfered to the seller.
 * <p>
 * The escrow service may be in the context of an auction, or utilized
 * by all users for deals made outside of CaveatEmptor. Hence, the
 * auction <tt>Item</tt> reference is optional and may be null.
 *
 * @author christian@hibernate.org
 */
@Entity(access = AccessType.FIELD)
@Table(name = "SHIPMENT")
@SecondaryTable(name = "ITEM_SHIPMENT")
public class Shipment {

    @Id(generate = GeneratorType.AUTO)
    @Column(name = "SHIPMENT_ID")
    private Long id = null;

    @Version
    private int version = 0;

    @ManyToOne
    @JoinColumn(name="DELIVERY_ADDRESS_ID", nullable = false, updatable = false)
    private AddressEntity deliveryAddress;

    @OneToOne
    @JoinColumn(secondaryTable = "ITEM_SHIPMENT", name = "ITEM_ID")
    private Item auction;

    @ManyToOne
    @JoinColumn(name="BUYER_ID", nullable = false, updatable = false)
    private User buyer;

    @ManyToOne
    @JoinColumn(name="SELLER_ID", nullable = false, updatable = false)
    private User seller;

    @Column(name="INSPECTION_PERIOD_DAYS", nullable = false, updatable = false)
    private int inspectionPeriodDays;

    @org.hibernate.annotations.Type(type = "shipment_state")
    @Column(name = "SHIPMENT_STATE", nullable = false)
    private ShipmentState state = ShipmentState.AGREED;

    @Column(name = "CREATED", nullable = false, updatable = false)
    private Date created = new Date();

    /**
     * No-arg constructor for JavaBean tools.
     */
    public Shipment() {}

    public Shipment(AddressEntity deliveryAddress, User buyer, User seller, int inspectionPeriodDays) {
        this.deliveryAddress = deliveryAddress;
        this.buyer = buyer;
        this.seller = seller;
        this.inspectionPeriodDays = inspectionPeriodDays;
    }

    /**
     * Full constructor.
     */

    // ********************** Accessor Methods ********************** //

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public int getVersion() { return version; }

    public AddressEntity getDeliveryAddress() { return deliveryAddress; }
    public void setDeliveryAddress(AddressEntity deliveryAddress) { this.deliveryAddress = deliveryAddress; }

    public Item getAuction() { return auction; }
    public void setAuction(Item auction) { this.auction = auction; }

    public User getSeller() { return seller; }
    public void setSeller(User seller) { this.seller = seller; }

    public User getBuyer() { return buyer; }
    public void setBuyer(User buyer) { this.buyer = buyer; }

    public int getInspectionPeriodDays() { return inspectionPeriodDays; }
    public void setInspectionPeriodDays(int inspectionPeriodDays) { this.inspectionPeriodDays = inspectionPeriodDays; }

    public ShipmentState getState() { return state; }
    public void setState(ShipmentState state) { this.state = state; }

    public Date getCreated() { return created; }

    // ********************** Common Methods ********************** //

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shipment)) return false;

        final Shipment shipment = (Shipment) o;

        if (inspectionPeriodDays != shipment.inspectionPeriodDays) return false;
        if (!buyer.getId().equals(shipment.buyer.getId())) return false;
        if (!created.equals(shipment.created)) return false;
        if (!seller.getId().equals(shipment.seller.getId())) return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = seller.getId().hashCode();
        result = 29 * result + buyer.getId().hashCode();
        result = 29 * result + inspectionPeriodDays;
        result = 29 * result + created.hashCode();
        return result;
    }

    public String toString() {
        return  "Shipment ('" + getId() + "'), " +
                "State: '" + getState() + "'";
    }

    // ********************** Business Methods ********************** //

}
