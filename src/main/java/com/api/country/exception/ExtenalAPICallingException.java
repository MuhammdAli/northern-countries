package com.api.country.exception;

public class ExtenalAPICallingException extends BusinessException {

    /**
     *
     */
    private static final long serialVersionUID = 72527627404713079L;

    public ExtenalAPICallingException() {
        super();
    }

    public ExtenalAPICallingException(String paramString, Throwable paramThrowable, boolean paramBoolean1,
                                      boolean paramBoolean2) {
        super(paramString, paramThrowable, paramBoolean1, paramBoolean2);
    }

    public ExtenalAPICallingException(String paramString, Throwable paramThrowable) {
        super(paramString, paramThrowable);
    }

    public ExtenalAPICallingException(String paramString) {
        super(paramString);
    }

    public ExtenalAPICallingException(Throwable paramThrowable) {
        super(paramThrowable);
    }

}
