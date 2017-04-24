package com.efeiyi.exception;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/27 0027.
 */

public class EfeiyiException extends RuntimeException implements Serializable {
    private EfeiyiException innerExcepion;

    public EfeiyiException(){
        super();
    }

    public EfeiyiException(String msg,Throwable cause){
        super(msg);
        super.initCause(cause);
    }

    public void setInnerExcepion(EfeiyiException excepion) {
        innerExcepion = excepion;
    }

    public EfeiyiException(String msg) {
        super(msg);
    }

    public EfeiyiException(Throwable cause) {
        super();
        super.initCause(cause);
    }

}
