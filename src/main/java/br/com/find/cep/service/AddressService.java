package br.com.find.cep.service;

import br.com.find.cep.resource.AddressRequest;

/**
 * The type Address service.
 *
 * @author Felipe Adorno (felipeadsc@gmail.com)
 */
public interface AddressService {

    /**
     * Remove address by id.
     *
     * @param id the id
     */
    void removeAddressById(final String id);

    /**
     * Save address.
     *
     * @param addressRequest the address request
     */
    void saveAddress(final AddressRequest addressRequest);

    /**
     * Update address.
     *
     * @param addressRequest the address request
     */
    void updateAddress(final AddressRequest addressRequest);

    /**
     * Find address by id address response.
     *
     * @param id the id
     * @return the address response
     */
    AddressRequest findAddressById(final String id);

    /**
     * Find address by cep address response.
     *
     * @param cep the address
     * @return the address response
     */
    AddressRequest findAddressByCep(String cep);

}
