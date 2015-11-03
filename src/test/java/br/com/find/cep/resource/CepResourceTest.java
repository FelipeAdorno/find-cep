package br.com.find.cep.resource;

import br.com.find.cep.aspect.Message;
import br.com.find.cep.aspect.MessageType;
import br.com.find.cep.run.FindPostalCodeProject;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * The type Cep resource test.
 * @author Felipe Adorno (felipeadsc@gmail.com)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = FindPostalCodeProject.class)
@WebAppConfiguration
@IntegrationTest({ "server.port=8000", "management.port=0" })
@DirtiesContext
public class CepResourceTest extends TestCase {

    private static final String CEP_RESOURCE = "http://localhost:8000/cep-api/cep/";

    @Test
    public void testFindAddressWithWrongCep() throws Exception {
        Message expectedMessage = new Message(MessageType.Business_Logic_Error, "Endereço não encontrado!");
        ResponseEntity<Message> response = callAPItoGetError("12345678");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(expectedMessage, response.getBody());
    }

    @Test
    public void testFindSiqueiraCamposAddress() throws Exception {
        AddressRequest siqueiraCampos = new AddressRequest("Rua Dr. Siqueira Campos", "Liberdade", "São Paulo", "SP");

        ResponseEntity<AddressRequest> response = callAPItoGetAddress("01509020");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(siqueiraCampos, response.getBody());
    }

    @Test
    public void testFindSebastiaoSoaresAddress() throws Exception {
        AddressRequest sebastiaoSoares = new AddressRequest(
                "Rua Prof. Sebastião Soares de Faria", "Bela Vista", "São Paulo", "SP");

        ResponseEntity<AddressRequest> response = callAPItoGetAddress("01317010");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(sebastiaoSoares, response.getBody());
    }

    @Test
    public void testFindSantaRitaAddressWithWrongCepNoZero() throws Exception {
        AddressRequest santaRita = new AddressRequest("Rua 1", "Fernandes", "Santa Rita do Sapucaí", "MG");

        ResponseEntity<AddressRequest> response = callAPItoGetAddress("37540123");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(santaRita, response.getBody());
    }

    @Test
    public void testFindSantaRitaAddressWithWrongCepOneZero() throws Exception {
        AddressRequest santaRita = new AddressRequest("Rua 1", "Fernandes", "Santa Rita do Sapucaí", "MG");

        ResponseEntity<AddressRequest> response = callAPItoGetAddress("37540120");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(santaRita, response.getBody());
    }

    @Test
    public void testFindSantaRitaAddressWithWrongCepTwoZero() throws Exception {
        AddressRequest santaRita = new AddressRequest("Rua 1", "Fernandes", "Santa Rita do Sapucaí", "MG");

        ResponseEntity<AddressRequest> response = callAPItoGetAddress("37540100");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(santaRita, response.getBody());
    }

    @Test
    public void testFindSantaRitaAddressWithWrongCepThreeZero() throws Exception {
        AddressRequest santaRita = new AddressRequest("Rua 1", "Fernandes", "Santa Rita do Sapucaí", "MG");

        ResponseEntity<AddressRequest> response = callAPItoGetAddress("37540100");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(santaRita, response.getBody());
    }

    @Test
    public void testGetAddressByCepWihtInvalidCep() throws Exception {
        Message expectedMessage = new Message(MessageType.Parameter_Error, "Erro de validação");
        expectedMessage.addNotification("CEP inválido");

        ResponseEntity<Message> response = callAPItoGetError("375400008");
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(expectedMessage, response.getBody());
    }

    private ResponseEntity<Message> callAPItoGetError(final String cep) {
        return new TestRestTemplate().getForEntity(CEP_RESOURCE + cep, Message.class);
    }

    private ResponseEntity<AddressRequest> callAPItoGetAddress(final String cep) {
        return new TestRestTemplate().getForEntity(CEP_RESOURCE + cep, AddressRequest.class);
    }
}