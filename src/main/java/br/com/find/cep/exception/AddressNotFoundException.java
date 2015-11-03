package br.com.find.cep.exception;


/**
 * The type Invalid cep exception.
 * @author Felipe Adorno (felipeadsc@gmail.com)
 */
public class AddressNotFoundException extends RuntimeException {
    public AddressNotFoundException(final String message) {
        super(message);
    }
}
