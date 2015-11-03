package br.com.find.cep.service;


import br.com.find.cep.exception.AddressNotFoundException;
import br.com.find.cep.resource.AddressRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Address service.
 * @author Felipe Adorno (felipeadsc@gmail.com)
 */
@Service
public class AddressServiceProvider implements AddressService {

    //MOCK CEP VALUES TO TEST
    private static final String SANTA_RITA_SAPUCAI = "37540000";
    private static final String SEBASTIAO_SOARES_DE_FARIA = "01317010";
    private static final String DOUTOR_SIQUEIRA_CAMPOS = "01509020";

    private static final Integer MAX_REPLACE_WITH_ZERO = 3;

    private Map<String, AddressRequest> addressResponses = new HashMap<>();

    @Override
    public void saveAddress(final AddressRequest addressRequest) {
        if(findCep(addressRequest.getCep()) != null) {
            addressResponses.put(addressRequest.getId(), addressRequest);
        } else {
            throw new AddressNotFoundException("O CEP digitado é invalido");
        }
    }

    @Override
    public void updateAddress(final AddressRequest addressRequest) {
        if(addressResponses.containsKey(addressRequest.getId())) {
            saveAddress(addressRequest);
        } else {
            throw new AddressNotFoundException("Endereço não encontrado!");
        }
    }

    @Override
    public AddressRequest findAddressById(final String id) {
        AddressRequest addressRequest = addressResponses.get(id);
        if(addressRequest == null) {
            throw new AddressNotFoundException("Endereço não encontrado!");
        }
        return addressRequest;
    }

    @Override
    public void removeAddressById(final String id) {
        if(addressResponses.containsKey(id)) {
            addressResponses.remove(id);
        } else {
            throw new AddressNotFoundException("Endereço não encontrado!");
        }
    }

    @Override
    public AddressRequest findAddressByCep(String cep) {
        Integer countCepFind = 0;
        AddressRequest addressRequest = findCep(cep);

        while (addressRequest == null && countCepFind <= MAX_REPLACE_WITH_ZERO) {
            countCepFind++;
            cep = replaceWithZero(cep, countCepFind);
            addressRequest = findCep(cep);
        }

        if(addressRequest == null) {
            throw new AddressNotFoundException("Endereço não encontrado!");
        }

        return addressRequest;
    }

    private String replaceWithZero(String cep, Integer countCepFind) {
        StringBuffer stringBuffer = new StringBuffer(cep);
        stringBuffer.setCharAt(cep.length()-countCepFind, '0');
        return stringBuffer.toString();
    }

    private AddressRequest findCep(final String cep) {

        //MOCK CEP TO TEST
        if(cep.equals(SANTA_RITA_SAPUCAI)) {
            return new AddressRequest("Rua 1", "Fernandes", "Santa Rita do Sapucaí", "MG");
        } else if(cep.equals(SEBASTIAO_SOARES_DE_FARIA)) {
            return new AddressRequest("Rua Prof. Sebastião Soares de Faria", "Bela Vista", "São Paulo", "SP");
        } else if(cep.equals(DOUTOR_SIQUEIRA_CAMPOS)) {
            return new AddressRequest("Rua Dr. Siqueira Campos", "Liberdade", "São Paulo", "SP");
        }

        return null;
    }
}
