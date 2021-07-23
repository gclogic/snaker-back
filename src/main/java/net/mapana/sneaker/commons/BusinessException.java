package net.mapana.sneaker.commons;

/**
 * @author guidocafiel
 */
@lombok.Getter
public class BusinessException extends Exception{

    private String message;

    public BusinessException(String msg){
        this.message = msg;
    }
}
