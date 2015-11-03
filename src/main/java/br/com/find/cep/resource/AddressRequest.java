package br.com.find.cep.resource;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The type Address request.
 * @author Felipe Adorno (felipeadsc@gmail.com)
 */
@XmlRootElement
public class AddressRequest {

    private String id;

    @NotEmpty(message = "Preencha o campo rua!")
    private String street;

    @NotEmpty(message = "Preencha o campo CEP!")
    private String cep;

    @NotNull(message = "Preencha o campo número!")
    private Long number;

    @NotEmpty(message = "Preencha o campo cidade!")
    private String city;

    @NotEmpty(message = "Preencha o campo estado!")
    private String state;

    private String district;

    private String complement;

    public AddressRequest() {
    }

    public AddressRequest(String street, String district, String city, String state) {
        this.street = street;
        this.district = district;
        this.city = city;
        this.state = state;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
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

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
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
