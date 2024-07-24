package nttdata.demo.exception;

public class CustomException extends RuntimeException {

    private String code;
    public CustomException() {
        super();
    }

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String code,String message) {
        super(message);
        this.code = code;
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomException(Throwable cause) {
        super(cause);
    }

    public String getCode() {
        return this.code;
    }
}
