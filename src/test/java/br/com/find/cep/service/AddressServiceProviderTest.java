package br.com.find.cep.service;

import br.com.find.cep.exception.AddressNotFoundException;
import br.com.find.cep.resource.AddressRequest;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;


/**
 * The type Address service provider test.
 * @author Felipe Adorno (felipeadsc@gmail.com)
 */
@RunWith(JUnit4.class)
public class AddressServiceProviderTest extends TestCase {

    private AddressRequest santaRita;
    private AddressRequest sebastiaoSoares;
    private AddressRequest siqueiraCampos;
    private AddressService addressService = new AddressServiceProvider();

    @Before
    public void setUp() throws Exception {
        santaRita = new AddressRequest("Rua 1", "Fernandes", "Santa Rita do Sapucaí", "MG");
        sebastiaoSoares = new AddressRequest("Rua Prof. Sebastião Soares de Faria", "Bela Vista", "São Paulo", "SP");
        siqueiraCampos = new AddressRequest("Rua Dr. Siqueira Campos", "Liberdade", "São Paulo", "SP");
    }

    @Test(expected = AddressNotFoundException.class)
    public void testRemoveAddressByIdWithoutAddress() throws Exception {
        AddressRequest expectedAddressRequest = createAddress();
        addressService.removeAddressById(expectedAddressRequest.getId());
    }

    @Test(expected = AddressNotFoundException.class)
    public void testRemoveAddressById() throws Exception {
        AddressRequest expectedAddressRequest = createAddress();
        addressService.saveAddress(expectedAddressRequest);

        AddressRequest addressRequest = addressService.findAddressById(expectedAddressRequest.getId());
        assertThat(expectedAddressRequest, equalTo(addressRequest));

        addressService.removeAddressById(expectedAddressRequest.getId());

        addressService.findAddressById(expectedAddressRequest.getId());

    }

    @Test
    public void testUpdateAddress() throws Exception {
        AddressRequest expectedAddressRequest = createAddress();
        expectedAddressRequest.setCep("01317010");
        addressService.saveAddress(expectedAddressRequest);
        expectedAddressRequest.setCity("Santa Rita do Sapucai");

        addressService.updateAddress(expectedAddressRequest);

        AddressRequest addressRequest = addressService.findAddressById(expectedAddressRequest.getId());
        assertThat(expectedAddressRequest, equalTo(addressRequest));
    }

    @Test(expected = AddressNotFoundException.class)
    public void testSaveAddressWithInvalidCep() throws Exception {
        AddressRequest expectedAddressRequest = createAddress();
        expectedAddressRequest.setCep("12345678");
        addressService.saveAddress(expectedAddressRequest);
    }

    @Test
    public void testSaveAddress() throws Exception {
        testFindAddressById(); //is the same test because don't have repository to verify the calls
    }

    @Test
    public void testFindAddressById() throws Exception {
        AddressRequest expectedAddressRequest = createAddress();
        addressService.saveAddress(expectedAddressRequest);
        AddressRequest addressRequest = addressService.findAddressById(expectedAddressRequest.getId());

        assertThat(expectedAddressRequest, equalTo(addressRequest));
    }

    @Test(expected = AddressNotFoundException.class)
    public void findAddressByIdWithWrongId() throws Exception {
        addressService.findAddressById("123");
    }

    @Test(expected = AddressNotFoundException.class)
    public void testFindAddressWithWrongCep() throws Exception {
        addressService.findAddressByCep("01220123");
    }

    @Test
    public void testFindSiqueiraCamposAddress() throws Exception {
        AddressRequest addressRequest = addressService.findAddressByCep("01509020");
        assertThat(addressRequest, equalTo(siqueiraCampos));
    }

    @Test
    public void testFindSebastiaoSoaresAddress() throws Exception {
        AddressRequest addressRequest = addressService.findAddressByCep("01317010");
        assertThat(addressRequest, equalTo(sebastiaoSoares));
    }

    @Test
    public void testFindSantaRitaAddressWithWrongCepNoZero() throws Exception {
        AddressRequest addressRequest = addressService.findAddressByCep("37540123");
        assertThat(addressRequest, equalTo(santaRita));
    }

    @Test
    public void testFindSantaRitaAddressWithWrongCepOneZero() throws Exception {
        AddressRequest addressRequest = addressService.findAddressByCep("37540120");
        assertThat(addressRequest, equalTo(santaRita));
    }

    @Test
    public void testFindSantaRitaAddressWithWrongCepTwoZero() throws Exception {
        AddressRequest addressRequest = addressService.findAddressByCep("37540100");
        assertThat(addressRequest, equalTo(santaRita));
    }

    @Test
    public void testFindSantaRitaAddressWithWrongCepThreeZero() throws Exception {
        AddressRequest addressRequest = addressService.findAddressByCep("37540000");
        assertThat(addressRequest, equalTo(santaRita));
    }

    private AddressRequest createAddress() {
        AddressRequest expectedAddressRequest = new AddressRequest();
        expectedAddressRequest.setStreet("Rua1");
        expectedAddressRequest.setState("SP");
        expectedAddressRequest.setDistrict("Bela Vista");
        expectedAddressRequest.setCep("01317010");
        expectedAddressRequest.setCity("São Paulo");
        expectedAddressRequest.setComplement("Sem complemento");
        expectedAddressRequest.setNumber(28L);
        return expectedAddressRequest;
    }
}