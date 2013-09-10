package org.hibernate.ce.auction.test.junit.hibernate;

import org.hibernate.ce.auction.dao.ShipmentDAO;
import org.hibernate.ce.auction.model.*;

public class ShipmentTest extends TestCaseWithData {

    public void testShipmentCreation() {

        ShipmentDAO dao = DAOFACTORY.getShipmentDAO();

        // User 1 buys from User 2
        Shipment newShipment = new Shipment(u1.getShippingAddress(), u1, u2, 5);

        dao.makePersistent(newShipment);

        // Execute
        sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().clear();

        Shipment loadedShipment = dao.findById(newShipment.getId(), false);

        assertEquals(loadedShipment.getBuyer(), u1);
    }

    public void testShipmentCreationForAuction() {

        ShipmentDAO dao = DAOFACTORY.getShipmentDAO();

        // User 1 buys from User 2
        Shipment newShipment = new Shipment(u1.getShippingAddress(), u1, u2, 5);
        newShipment.setAuction(auctionOne);

        dao.makePersistent(newShipment);

        // Execute
        sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().clear();

        Shipment loadedShipment = dao.findById(newShipment.getId(), false);

        assertEquals(loadedShipment.getBuyer(), u1);
        assertEquals(loadedShipment.getAuction(), auctionOne);

    }

}
