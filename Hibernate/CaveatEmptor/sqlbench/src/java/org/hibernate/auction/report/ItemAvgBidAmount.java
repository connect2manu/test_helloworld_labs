package org.hibernate.auction.report;

import org.hibernate.auction.model.*;

public class ItemAvgBidAmount {

    private Long itemId;
    private MonetaryAmount avgBidAmount;

    public ItemAvgBidAmount(Long itemId, MonetaryAmount avgBid) {
        this.itemId = itemId;
        this.avgBidAmount = avgBid;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public MonetaryAmount getAvgBidAmount() {
        return avgBidAmount;
    }

    public void setAvgBidAmount(MonetaryAmount avgBidAmount) {
        this.avgBidAmount = avgBidAmount;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemAvgBidAmount)) return false;

        final ItemAvgBidAmount itemAvgBidAmount = (ItemAvgBidAmount) o;

        if (!avgBidAmount.equals(itemAvgBidAmount.avgBidAmount)) return false;
        if (!itemId.equals(itemAvgBidAmount.itemId)) return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = itemId.hashCode();
        result = 29 * result + avgBidAmount.hashCode();
        return result;
    }

    public String toString() {
        return "Item Id: " + getItemId() + " Avg. Bid amount: " + getAvgBidAmount();
    }
}
