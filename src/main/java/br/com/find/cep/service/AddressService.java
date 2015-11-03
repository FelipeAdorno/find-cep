package br.com.find.cep.service;

import br.com.find.cep.resource.AddressResponse;

/**
 * The type Address service.
 * @author Felipe Adorno (felipeadsc@gmail.com)
 */
public interface AddressService {

    /**
     * Find address by cep address response.
     *
     * @param cep the address
     * @return the address response
     */
    AddressResponse findAddressByCep(String cep);

}
