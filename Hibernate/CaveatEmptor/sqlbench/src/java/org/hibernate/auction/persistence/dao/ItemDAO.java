package org.hibernate.auction.persistence.dao;

import org.hibernate.auction.model.*;
import org.hibernate.auction.exceptions.InfrastructureException;
import org.hibernate.auction.report.ItemAvgBidAmount;

import java.util.Collection;

public interface ItemDAO {
    Item getItemById(Long itemId, boolean lock)
			throws InfrastructureException;

    Bid getMaxBid(Long itemId)
			throws InfrastructureException;

    Bid getMinBid(Long itemId)
			throws InfrastructureException;

    Collection findAll()
			throws InfrastructureException;

    Collection findByExample(Item exampleItem)
			throws InfrastructureException;

    ItemAvgBidAmount[] findAverageBids(Collection items)
            throws InfrastructureException;

    void makePersistent(Item item)
			throws InfrastructureException;

    void makeTransient(Item item)
			throws InfrastructureException;
}
