package simonova.rent.rentofpremises.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoAppException extends RuntimeException{

    public NoAppException() {
        super();
    }

    public NoAppException(String message) {
        super(message);
    }

    public NoAppException(String message, Throwable cause) {
        super(message, cause);
    }
}
