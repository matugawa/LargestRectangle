import java.util.*;

class Rect{
    int h=0;
    int index=0;
    Rect(int h, int index){
        this.h=h;
        this.index=index;
    }
    public int rect(int p){
        return this.h*(p-this.index);
    }
    public void view(){
        System.out.println("h is "+this.h);
        System.out.println("index is "+this.index);
    }
}
public class LargestRectangle{
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
    public static int getRect(int[] line){
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
            }
        }
        return max;
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
                if(bag[j][i].equals("#")){
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
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int h=sc.nextInt();
        int w=sc.nextInt();
        int max=0;
        String[][] bag=new String[h][w];
        for(int i=0; i<h; i++){
            String tmp=sc.next();
            bag[i]=tmp.split("");
        }
        int[][] hsg=createHsg(bag);
        //viewInt(hsg);
        for(int i=0; i<hsg.length; i++){
            int rect=getRect(hsg[i]);
            if(max<rect) max=rect;
        }
        System.out.println(max);




  

    }
}
