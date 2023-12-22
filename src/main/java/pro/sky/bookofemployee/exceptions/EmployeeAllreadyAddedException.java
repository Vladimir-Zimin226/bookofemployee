package pro.sky.bookofemployee.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmployeeAllreadyAddedException extends RuntimeException {
    public EmployeeAllreadyAddedException() {
    }

    public EmployeeAllreadyAddedException(String message) {
        super(message);
    }

    public EmployeeAllreadyAddedException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeAllreadyAddedException(Throwable cause) {
        super(cause);
    }

    public EmployeeAllreadyAddedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
