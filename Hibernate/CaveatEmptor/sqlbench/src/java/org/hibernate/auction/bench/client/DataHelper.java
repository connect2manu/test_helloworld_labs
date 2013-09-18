package org.hibernate.auction.bench.client;

import org.hibernate.auction.model.*;

import java.util.*;
import java.math.BigDecimal;

public class DataHelper {

    public static final int CATEGORY_NAMELENGTH     = 20;
    public static final int CATEGORY_TREEDEPTH      = 3;
    public static final int CATEGORY_NOOFSIBLINGS   = 5;
    public static final int USER_NAMELENGTH         = 16;
    public static final int ADDRESS_NAMELENGTH      = 15;
    public static final int BILLING_NAMELENGTH      = 15;
    public static final int ITEM_NAMELENGTH         = 25;

    private static java.util.Random rnd = new java.util.Random();

    public static Category createCategoryTree() throws Exception {
        Category root = new Category(generateRandomString(CATEGORY_NAMELENGTH));
        createCategoryLevels(root, 1);
        return root;
    }
    private static void createCategoryLevels(Category parentCategory, int level) {
        for (int i=0; i<CATEGORY_NOOFSIBLINGS; i++) {
            Category cat = new Category(generateRandomString(CATEGORY_NAMELENGTH));
            parentCategory.addChildCategory(cat);
            if (level < CATEGORY_TREEDEPTH) {
                createCategoryLevels(cat, level+1);
            }
        }
    }

    public static User createUser() {
        Address address = new Address(generateRandomString(ADDRESS_NAMELENGTH),
                                      generateRandomString(ADDRESS_NAMELENGTH),
                                      generateRandomString(ADDRESS_NAMELENGTH));
        User u = new User(generateRandomString(USER_NAMELENGTH),
                          generateRandomString(USER_NAMELENGTH),
                          generateRandomString(USER_NAMELENGTH),
                          generateRandomString(12),
                          generateRandomString(USER_NAMELENGTH));
        u.setAddress(address);

        BillingDetails creditCard =
                new CreditCard(generateRandomString(BILLING_NAMELENGTH),
                               u,
                               generateRandomString(BILLING_NAMELENGTH),
                               CreditCardType.VISA,
                               generateRandomString(2),
                               generateRandomString(4));
        u.addBillingDetails(creditCard);
        return u;
    }

    public static Item createItem(User seller) {
        Calendar inThreeDays = GregorianCalendar.getInstance();
        inThreeDays.add(Calendar.DATE, 3);

        Item i = new Item(generateRandomString(ITEM_NAMELENGTH),
                          generateRandomString(99),
                          seller,
                          new MonetaryAmount(new BigDecimal("1.00"), Currency.getInstance(Locale.US)),
                          new MonetaryAmount(new BigDecimal("100.00"), Currency.getInstance(Locale.US)),
                          new Date(),
                          inThreeDays.getTime());

        return i;
    }

    public static String generateRandomString(int length) {
        StringBuffer buf = new StringBuffer(length);
        int nextChar;
        int range = 'z' - 'a' + 1;
        for (int i = 0; i < length; i++) {
            nextChar = 'a' + rnd.nextInt(range);
            buf.append((char) nextChar);
        }
        return buf.toString();
    }
}
