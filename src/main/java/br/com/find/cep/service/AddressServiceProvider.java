package br.com.find.cep.service;


import br.com.find.cep.exception.AddressNotFoundException;
import br.com.find.cep.resource.AddressResponse;
import org.springframework.stereotype.Service;

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

    public AddressResponse findAddressByCep(String cep) {
        Integer countCepFind = 0;
        AddressResponse addressResponse = findCep(cep);

        while (addressResponse == null && countCepFind <= MAX_REPLACE_WITH_ZERO) {
            countCepFind++;
            cep = replaceWithZero(cep, countCepFind);
            addressResponse = findCep(cep);
        }

        if(addressResponse == null) {
            throw new AddressNotFoundException();
        }

        return addressResponse;
    }

    private String replaceWithZero(String cep, Integer countCepFind) {
        StringBuffer stringBuffer = new StringBuffer(cep);
        stringBuffer.setCharAt(cep.length()-countCepFind, '0');
        return stringBuffer.toString();
    }

    private AddressResponse findCep(final String cep) {

        //MOCK CEP TO TEST
        if(cep.equals(SANTA_RITA_SAPUCAI)) {
            return new AddressResponse("Rua 1", "Fernandes", "Santa Rita do Sapucaí", "MG");
        } else if(cep.equals(SEBASTIAO_SOARES_DE_FARIA)) {
            return new AddressResponse("Rua Prof. Sebastião Soares de Faria", "Bela Vista", "São Paulo", "SP");
        } else if(cep.equals(DOUTOR_SIQUEIRA_CAMPOS)) {
            return new AddressResponse("Rua Dr. Siqueira Campos", "Liberdade", "São Paulo", "SP");
        }

        return null;
    }

}
