package br.com.find.cep.aspect;

import br.com.find.cep.exception.AddressNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 * The type Rest error handler.
 * @author Felipe Adorno (felipeadsc@gmail.com)
 */
@ControllerAdvice
public class RestErrorHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<Message> handlerValidationException(ConstraintViolationException ex) {
        Message message = new Message(MessageType.Parameter_Error, "Erro de validação");
        for (ConstraintViolation violation : ex.getConstraintViolations()) {
            message.addNotification(violation.getMessage());
        }
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "application/json; charset=utf-8");

        return new ResponseEntity<>(message, responseHeaders, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AddressNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<Message> handlerAddressNotFoundException(AddressNotFoundException ex) {
        Message message = new Message(MessageType.Business_Logic_Error, ex.getMessage());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<>(message, responseHeaders, HttpStatus.NOT_FOUND);
    }
}