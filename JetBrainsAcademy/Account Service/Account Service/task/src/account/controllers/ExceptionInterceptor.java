package account.controllers;

import account.contracts.response.ErrorPayloadCM;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ExceptionInterceptor {

    @ExceptionHandler({ConstraintViolationException.class})
    ResponseEntity<ErrorPayloadCM> handleConstraintViolationException(ConstraintViolationException e,
                                                                     HttpServletRequest request) {
        /* Note: Throwing an exception seems to negate the exception handler,
         * and the original error is thrown instead. Must return a proper response.
         */
        // throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid request");
        ErrorPayloadCM response = new ErrorPayloadCM(HttpStatus.BAD_REQUEST, request.getServletPath());
        response.setMessage(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
