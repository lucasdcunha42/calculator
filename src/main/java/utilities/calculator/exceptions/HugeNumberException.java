package utilities.calculator.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class HugeNumberException extends Exception{

    public HugeNumberException() {
        super();
    }
    public HugeNumberException(String message){
        super(message);
    }
}
