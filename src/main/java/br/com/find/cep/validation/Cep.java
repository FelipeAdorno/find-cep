package br.com.find.cep.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Pattern;
import java.lang.annotation.*;

/**
 * The interface Cep.
 * @author Felipe Adorno (felipeadsc@gmail.com)
 */
@Pattern(regexp="[0-9]{8}")
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {})
@ReportAsSingleViolation
public @interface Cep {

  String message() default "CEP inválido";

  Class<?>[] groups() default { };

  Class<? extends Payload>[] payload() default { };
 
}