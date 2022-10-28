import java.util.*;
import chk.IFCheck;
import chk.IntCheck;

class Rect{
    int h=-1;
    int index=-1;
    int max=-1;
    int i=-1;
    Rect(int h, int index){
        this.h=h;
        this.index=index;
    }
    Rect(){}
    public int rect(int p){
        return this.h*(p-this.index);
    }
    public int getH(){
        return this.h;
    }
    public void setI(int i){
        this.i=i;
    }
    public int getI(){
        return this.i;
    }
    public int getIndex(){
        return this.index;
    }
    public void setMax(int i){
        this.max=i;
    }
    public int getMax(){
        return this.max;
    }
    public void view(){
        System.out.println("h is "+this.h);
        System.out.println("index is "+this.index);
    }
}
public class createMapAndShowLRect{
    public static void viewHsgFromIntArray(int[] line){
        int top=Arrays.stream(line)
            .max()
            .getAsInt();
        for(int i=top; i>=1; i--){
            for(int j=0; j<line.length; j++){
                if(line[j]>=i){
                    System.out.print("*");
                }else{
                    System.out.print(" ");
                }
                if(j==line.length-1){
                    System.out.println();
                }
            }
        }
    }
    public static Rect getRectCoor(int[] line){
        Rect resultRect=new Rect();
        Deque<Rect> deq=new ArrayDeque<>();
        int max=0;
        for(int i=0; i<line.length; i++){
            if(deq.size()==0){    
                deq.push(new Rect(line[i], i));
            }else if(deq.peek().h<line[i]){
                deq.push(new Rect(line[i], i));
            }else if(deq.peek().h>line[i]){
                int p=0;
                while(deq.size()!=0 &&deq.peek().h>=line[i]){
                    Rect rect=deq.pop();
                    if(max<rect.rect(i)){
                        max=rect.rect(i);
                        resultRect=rect;
                        resultRect.setI(i);
                        resultRect.setMax(max);
                    }
                    p=rect.index;
                }
                deq.push(new Rect(line[i], p));
            }
        }
        while(deq.size()!=0){
            Rect rect=deq.pop();
            if(max<rect.rect(line.length)){
                max=rect.rect(line.length);
                resultRect=rect;
                resultRect.setMax(max);
                resultRect.setI(line.length);
            }
        }
        return resultRect;
    }
    public static String[] convToStrArrFromIntArr(int[] line){
        String tmp="";
        for(int i=0; i<line.length; i++){
            tmp+=String.valueOf(line[i]);
        }
        System.out.println(tmp);
        String[] ret=tmp.split("0");
        return ret;
    } 
    public static int[][] createHsg(String[][] bag){
        int[][] ret=new int[bag.length][bag[0].length];
        for(int i=0; i<bag[0].length; i++){
            boolean fl=false;
            for(int j=0; j<bag.length; j++){
                if(bag[j][i].equals("X")){
                    fl=true;
                    continue;
                }
                if(fl || j==0){
                    ret[j][i]=1;
                    fl=false;
                }else{
                    ret[j][i]=ret[j-1][i]+1;
                }
            }
        }
        return ret;
    }
    public static void viewInt(int[][] bag){
        for(int i=0; i<bag.length; i++){
            for(int j=0; j<bag[i].length; j++){
                System.out.print(bag[i][j]);
                if(j<bag[i].length-1){
                    System.out.print(" ");
                }else{
                    System.out.println();
                }
            }
        }
    }
    public static void viewBag(String[][] bag){
                for(int i=0; i<bag.length; i++){
            for(int j=0; j<bag[i].length; j++){
                System.out.print(bag[i][j]);
                if(j<bag[i].length-1){
                    System.out.print(" ");
                }else{
                    System.out.println();
                }
            }
        }
    }
    public static void viewResultBag(String[][] bag
    , int stY, int stX, int endY, int endX){
        for(int i=0; i<bag.length; i++){
            for(int j=0; j<bag[i].length; j++){
                if(stY<=i &&i<=endY &&stX<=j &&j<=endX){
                    bag[i][j]="O";
                }
                System.out.print(bag[i][j]);
                if(j<bag[i].length-1){
                    System.out.print(" ");
                }else{
                    System.out.println();
                }
            }
        }
    }
    public static int inp(String msg){
        Scanner sc=new Scanner(System.in);
        String inputted=null;
        IFCheck chk=new IntCheck();
        while(true){
            System.out.print(msg);
            inputted=sc.next();
            if(inputted.equals("q")) System.exit(0);
            if(!chk.check(inputted)){
                System.out.println(chk.getErrMsg()
                +"\nif quit enter q");
            }else{
                break;
            }
        }
        return Integer.parseInt(inputted);
    }
    public static void main(String[] args){
        System.out.println("create &show randam map");
        int h=inp("enter height: ");
        int w=inp("enter weight: ");
        int max=0;
        String[][] bag=new String[h][w];
        for(int i=0; i<h; i++){
            for(int j=0; j<w; j++){
                String tp=null;
                int tmp=(int)(Math.random()*5);
                if(tmp>=0 &&tmp<=3){
                    tp="_";
                }else if(tmp==4){
                    tp="X";
                }
                bag[i][j]=tp;
            }
        }
        viewBag(bag);
        System.out.println("<< Largest Reactangle >>");
        int[][] hsg=createHsg(bag);
        //viewInt(hsg);
        Rect maxRect=new Rect();
        int lv=0;
        for(int i=0; i<hsg.length; i++){
            Rect resultRect=getRectCoor(hsg[i]);
            if(max<resultRect.getMax()){
                max=resultRect.getMax();
                maxRect=resultRect;
                lv=i;
            }
        }
        int CooXX=maxRect.getI()-1;
        int CooYY=lv;
        int CooY=lv-(maxRect.getH()-1);
        int CooX=CooXX-((max/maxRect.getH())-1);
        viewResultBag(bag, CooY, CooX, CooYY, CooXX);
        System.out.println("Rectangle: "+max);
    }
}
