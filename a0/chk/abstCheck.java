package chk;

public abstract class abstCheck implements IFCheck{
    String errMsg;
    public abstCheck(String errMsg){
        this.errMsg=errMsg;
    }
    public String getErrMsg(){
        return errMsg;
    }
}