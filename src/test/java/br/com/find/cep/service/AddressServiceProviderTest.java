package br.com.find.cep.service;

import br.com.find.cep.exception.AddressNotFoundException;
import br.com.find.cep.resource.AddressResponse;
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

    private AddressResponse santaRita;
    private AddressResponse sebastiaoSoares;
    private AddressResponse siqueiraCampos;
    private AddressService addressService = new AddressServiceProvider();

    @Before
    public void setUp() throws Exception {
        santaRita = new AddressResponse("Rua 1", "Fernandes", "Santa Rita do Sapucaí", "MG");
        sebastiaoSoares = new AddressResponse("Rua Prof. Sebastião Soares de Faria", "Bela Vista", "São Paulo", "SP");
        siqueiraCampos = new AddressResponse("Rua Dr. Siqueira Campos", "Liberdade", "São Paulo", "SP");
    }

    @Test(expected = AddressNotFoundException.class)
    public void testFindAddressWithWrongCep() throws Exception {
        addressService.findAddressByCep("01220123");
    }

    @Test
    public void testFindSiqueiraCamposAddress() throws Exception {
        AddressResponse addressResponse = addressService.findAddressByCep("01509020");
        assertThat(addressResponse, equalTo(siqueiraCampos));
    }

    @Test
    public void testFindSebastiaoSoaresAddress() throws Exception {
        AddressResponse addressResponse = addressService.findAddressByCep("01317010");
        assertThat(addressResponse, equalTo(sebastiaoSoares));
    }

    @Test
    public void testFindSantaRitaAddressWithWrongCepNoZero() throws Exception {
        AddressResponse addressResponse = addressService.findAddressByCep("37540123");
        assertThat(addressResponse, equalTo(santaRita));
    }

    @Test
    public void testFindSantaRitaAddressWithWrongCepOneZero() throws Exception {
        AddressResponse addressResponse = addressService.findAddressByCep("37540120");
        assertThat(addressResponse, equalTo(santaRita));
    }

    @Test
    public void testFindSantaRitaAddressWithWrongCepTwoZero() throws Exception {
        AddressResponse addressResponse = addressService.findAddressByCep("37540100");
        assertThat(addressResponse, equalTo(santaRita));
    }

    @Test
    public void testFindSantaRitaAddressWithWrongCepThreeZero() throws Exception {
        AddressResponse addressResponse = addressService.findAddressByCep("37540000");
        assertThat(addressResponse, equalTo(santaRita));
    }
}