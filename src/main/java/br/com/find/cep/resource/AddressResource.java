package br.com.find.cep.resource;

import br.com.find.cep.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

/**
 * The type Address resource.
 * @author Felipe Adorno (felipeadsc@gmail.com)
 */
@RestController
@RequestMapping(value = "/address")
@Validated
public class AddressResource {

    @Autowired
    private AddressService addressService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<AddressRequest> getAddressById(@PathVariable String id) {
        AddressRequest addressRequest = addressService.findAddressById(id);
        return new ResponseEntity<>(addressRequest, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity deleteAddressById(@PathVariable String id) {
        addressService.removeAddressById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<AddressRequest> saveAddress(@Valid @RequestBody AddressRequest addressRequest) {
        addressRequest.setId(UUID.randomUUID().toString());
        addressService.saveAddress(addressRequest);
        return new ResponseEntity<>(addressRequest, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<AddressRequest> editAddress(@Valid @RequestBody AddressRequest addressRequest) {
        addressService.saveAddress(addressRequest);
        return new ResponseEntity<>(addressRequest, HttpStatus.OK);
    }

}
