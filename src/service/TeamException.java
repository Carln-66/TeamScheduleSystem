package service;

/**
 * @Auther: Carl
 * @Date: 2021/02/04/14:45
 * @Description: 自定义异常类
 */
public class TeamException extends Exception{
    static final long serialVersionUID = -33875169912429948L;

    public TeamException(){

    }
    public TeamException(String msg){
        super(msg);
    }
}
