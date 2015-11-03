package br.com.find.cep.resource;


import br.com.find.cep.service.AddressService;
import br.com.find.cep.validation.Cep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * The type Cep resource.
 * @author Felipe Adorno (felipeadsc@gmail.com)
 */
@RestController
@RequestMapping(value = "/cep")
@Validated
public class CepResource {

    @Autowired
    private AddressService addressService;

    @RequestMapping(value = "/{cep}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<AddressRequest> getAddressByCep(@Valid @Cep @PathVariable String cep) {
        AddressRequest addressResponse = addressService.findAddressByCep(cep);
        return new ResponseEntity<AddressRequest>(addressResponse, HttpStatus.OK);
    }


}
