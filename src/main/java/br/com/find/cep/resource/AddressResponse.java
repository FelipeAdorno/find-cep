package br.com.find.cep.resource;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The type Address response.
 * @author Felipe Adorno (felipeadsc@gmail.com)
 */
@XmlRootElement
public class AddressResponse {

    private String street;

    private String district;

    private String city;

    private String state;

    public AddressResponse() {
    }

    public AddressResponse(final String street, final String district, final String city, final String state) {
        this.street = street;
        this.district = district;
        this.city = city;
        this.state = state;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(Object objectToCompare) {
        return EqualsBuilder.reflectionEquals(this, objectToCompare);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
