package org.hibernate.ce.auction.seam.pojo;

import org.jboss.seam.annotations.*;
import org.hibernate.ce.auction.model.Category;
import org.hibernate.Session;

import javax.faces.model.ListDataModel;
import java.util.List;

@Name("browseCategories")
@LoggedIn
public class BrowseCategories {

    @In(create=true)
    private Session caveatemptorDatabase;

    List<Category> categories;
    ListDataModel model;
    int index;

    public ListDataModel getCategories() {
        return model;
    }

    @Out(required=false)
    Category selectedCategory;


    public String findRootCategories() {
        /*
        categories = em.createQuery("from Order o where o.customer = :customer")
                   .setParameter("customer", customer)
                   .getResultList();
        model = new ListDataModel(orders);
        order = null;
        */

        return "showorders";
    }
}
