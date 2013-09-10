@TypeDefs({
    @TypeDef(
        name="monetary_amount_usd",
        typeClass = MonetaryAmountType.class,
        parameters = { @Parameter(name="convertTo", value="USD") }
    ),
    @TypeDef(
        name="monetary_amount_eur",
        typeClass = MonetaryAmountType.class,
        parameters = { @Parameter(name="convertTo", value="EUR") }
    ),
    @TypeDef(
        name="item_state",
        typeClass = StringEnumUserType.class,
        parameters = { @Parameter(name="enumClassname", value="org.hibernate.ce.auction.model.ItemState") }
    ),

    @TypeDef(
        name="credit_card_type",
        typeClass = StringEnumUserType.class,
        parameters = { @Parameter(name="enumClassname", value="org.hibernate.ce.auction.model.CreditCardType") }
    ),
    @TypeDef(
        name="rating",
        typeClass = StringEnumUserType.class,
        parameters = { @Parameter(name="enumClassname", value="org.hibernate.ce.auction.model.Rating") }
    ),
    @TypeDef(
        name="shipment_state",
        typeClass = StringEnumUserType.class,
        parameters = { @Parameter(name="enumClassname", value="org.hibernate.ce.auction.model.ShipmentState") }
    )
})

package org.hibernate.ce.auction.persistence;

import org.hibernate.annotations.*;
