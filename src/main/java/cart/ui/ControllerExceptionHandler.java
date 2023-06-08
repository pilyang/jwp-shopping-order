package cart.ui;

import cart.dto.response.ExceptionResponse;
import cart.exception.AuthenticationException;
import cart.exception.CouponException;
import cart.exception.ItemException;
import cart.exception.OrderException;
import cart.exception.ProductException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Void> handlerAuthenticationException(AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @ExceptionHandler(value = {
            ItemException.IllegalMember.class,
            CouponException.IllegalMember.class,
            OrderException.IllegalMember.class
    })
    public ResponseEntity<ExceptionResponse> handleException(Exception exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ExceptionResponse(exception.getMessage()));
    }

    @ExceptionHandler(value = {
            CouponException.class,
            OrderException.class,
            ItemException.class,
            ProductException.class
    })
    public ResponseEntity<ExceptionResponse> handleWrongDiscountInputException(Exception exception) {
        return ResponseEntity.badRequest()
                .body(new ExceptionResponse(exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> validHandler(final MethodArgumentNotValidException exception) {
        final String message = exception.getAllErrors().get(0).getDefaultMessage();

        return ResponseEntity.badRequest()
                .body(new ExceptionResponse(message));
    }

}
