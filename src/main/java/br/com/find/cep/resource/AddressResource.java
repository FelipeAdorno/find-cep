package br.com.find.cep.resource;

import br.com.find.cep.service.AddressService;
import br.com.find.cep.validation.Cep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;

/**
 * The type Adress resource.
 * @author Felipe Adorno (felipeadsc@gmail.com)
 */
@RestController
@RequestMapping(value = "/address")
@Validated
public class AddressResource {

    @Autowired
    private AddressService addressService;

    @RequestMapping(value = "/{cep}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<AddressResponse> getAddressByCep(@Valid @Cep @PathVariable String cep) {
        AddressResponse addressResponse = addressService.findAddressByCep(cep);
        return new ResponseEntity<AddressResponse>(addressResponse, HttpStatus.OK);
    }

}
