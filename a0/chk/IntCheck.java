package chk;

public class IntCheck extends abstCheck{
    private static final String errMsg="input is only Int";
    public IntCheck(){
        super(errMsg);
    }
    public boolean check(String data){
        try{
            Integer.parseInt(data);
        }catch(NumberFormatException e){
            return false;
        }
        return true;
    }
}