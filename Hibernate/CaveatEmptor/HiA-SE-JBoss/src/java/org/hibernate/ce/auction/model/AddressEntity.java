package org.hibernate.ce.auction.model;

import javax.persistence.*;

@Entity(access = AccessType.FIELD)
@Table(name = "ADDRESS")
public class AddressEntity {

    @Id
    @Column(name = "ADDRESS_ID")
    private Long id = null;

    @Version
    private int version = 0;

    @Column(length = 255, nullable = false)
    private String street;

    @Column(length = 16, nullable = false)
    private String zipcode;

    @Column(length = 255, nullable = false)
    private String city;

    @OneToOne
    @PrimaryKeyJoinColumn
    private User user;

    /**
     * No-arg constructor for JavaBean tools.
     */
    AddressEntity() {}

    /**
     * Full constructor.
     */
    public AddressEntity(String street, String zipcode, String city) {
        this.street = street;
        this.zipcode = zipcode;
        this.city = city;
    }

    // ********************** Accessor Methods ********************** //

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public int getVersion() { return version; }

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public String getZipcode() { return zipcode; }
    public void setZipcode(String zipcode) { this.zipcode = zipcode; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    // ********************** Common Methods ********************** //

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddressEntity)) return false;

        final AddressEntity address = (AddressEntity) o;

        if (!city.equals(address.city)) return false;
        if (!street.equals(address.street)) return false;
        if (!zipcode.equals(address.zipcode)) return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = street.hashCode();
        result = 29 * result + zipcode.hashCode();
        result = 29 * result + city.hashCode();
        return result;
    }

    public String toString() {
        return  "Street: '" + getStreet() + "', " +
                "Zipcode: '" + getZipcode() + "', " +
                "City: '" + getCity() + "'";
    }

    // ********************** Business Methods ********************** //

}
