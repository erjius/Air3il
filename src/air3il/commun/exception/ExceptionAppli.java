package air3il.commun.exception;

@SuppressWarnings("serial")
public class ExceptionAppli extends Exception {

    public ExceptionAppli() {
        super();
    }

    public ExceptionAppli(String message, Throwable cause) {
        super(message, cause);
    }

    public ExceptionAppli(String message) {
        super(message);
    }

    public ExceptionAppli(Throwable cause) {
        super(cause);
    }

}
