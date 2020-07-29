package tda.darkarmy.redditclone.exception;

public class SpringRedditException extends Exception {
    public SpringRedditException() {
    }

    public SpringRedditException(String message) {
        super(message);
    }

    public SpringRedditException(String message, Throwable cause) {
        super(message, cause);
    }

    public SpringRedditException(Throwable cause) {
        super(cause);
    }

    public SpringRedditException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
