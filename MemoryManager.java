import java.util.*;

public class MemoryManager {

    Memory memory;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int M = 0; 
        while(true){
            try{
                System.out.println("Plesae enter the number of partitions");
                M = in.nextInt();
                break;
            }catch(InputMismatchException e){
                System.out.println("only numbers allowed");
                in.next();
            }
        }
        Memory memory=new Memory(M);
        boolean cont=false;
        int start=0;
        for(int i=0; i<M;i++){
            String s =((i+1)==1)?"st":( ((i+1)==2)?"nd":( ((i+1)==3)?"rd":"th" ) );
            int sizeOfPortion = 0;
            while(true){
                try{
                    System.out.println("Plesae enter the size of "+(i+1)+s+" partition in KB");
                    sizeOfPortion = in.nextInt();
                    break;
                }catch(InputMismatchException e){
                    System.out.println("only numbers allowed");
                    in.next();
                }
            }

            memory.createPartition(i,sizeOfPortion,start,start+sizeOfPortion);
            start+=sizeOfPortion;
        }

        
        
        //for(int i=0; i<memory.noOfPartitions;i++)
        //memory.partion[i].printo();
        String completingSen="the";
        char strategy='-';

        do{
            if(cont)
            completingSen="a valid";
            System.out.println("Plesae enter "+completingSen+" allocation strategy [First-fit (F), Best-fit (B), or Worstfit (W)]");
            String s = in.next();
            strategy = s.charAt(0);
            if( ! ( s.length()==1&&(strategy=='F'||strategy=='f'||strategy=='B'||strategy=='b'||strategy=='W'||strategy=='w') ))
            cont=true;
            else
            cont=false;
        }while(cont);

        switch(strategy){
            case 'F':case 'f':memory.setAllocationStrategy(Approaches.FIRST_FIT);break;
            case 'B':case 'b':memory.setAllocationStrategy(Approaches.BEST_FIT);break;
            case 'W':case 'w':memory.setAllocationStrategy(Approaches.WORST_FIT);break;
        }
        

       // cont=true;
        int userPick=0;
        cont=true;
        do{
            while(true){
                try{
                    System.out.println("Please select from  the following menu");
                    System.out.println("1. Allocate a block of memory.");
                    System.out.println("2. De-allocate a block of memory.");
                    System.out.println("3. Report detailed information about regions of free and allocated memory blocks.");
                    System.out.println("4. Exit the program.");
                    userPick=in.nextInt();
                    break;
                }catch(InputMismatchException e){
                    System.out.println("only numbers allowed");
                    in.next();
                }
            }
            
            switch(userPick){
                case 1:
                    completingSen="the";
                    String processIdOfBlock="";
                    boolean flag=false;
                    do{
                    if(flag)
                    completingSen="a valid";
                    System.out.println("Please enter "+completingSen+" process ID of the block you want to allocate in the form of \'PN\' where N represents the process number");
                    processIdOfBlock=in.next();
                    if( ! ( (processIdOfBlock.charAt(0)=='p'||processIdOfBlock.charAt(0)=='P')&&isNumeric(processIdOfBlock.substring(1)) ))
                    flag=true;
                    else
                    flag=false;
                    }while(flag);
                    int sizeOfBlock = 0;
                    while(true){
                        try{
                            System.out.println("Please enter the size of the block you want to allocate");
                            sizeOfBlock = in.nextInt();
                            break;
                        }catch(InputMismatchException e){
                            System.out.println("only numbers allowed");
                            in.next();
                        }
                    }
                    
                    if(!memory.allocate(processIdOfBlock,sizeOfBlock))
                    System.out.println("\nSORRY your request can not be done because there is no sufficient memory for your block to be allocated".toUpperCase()+" \n");
                    else{
                        System.out.print("\nthis is the memory state after allocation [ ");
                        for(int i=0; i<memory.noOfPartitions;i++){
                        String pid=memory.partion[i].getProcessID();
                        System.out.print(((pid==null)?"H":pid)+((i==memory.noOfPartitions-1)?"":" | "));}
                        System.out.print(" ]");
                        System.out.println("\n");
                    }
                    break;
                case 2:
                    completingSen="the";
                    String procesIdOfBlock="";
                    boolean flag1=false;
                    do{
                    if(flag1)
                    completingSen="a valid";
                    System.out.println("Please enter "+completingSen+" process ID to be released from the memory");
                    procesIdOfBlock=in.next();
                    if( ! ( (procesIdOfBlock.charAt(0)=='p'||procesIdOfBlock.charAt(0)=='P')&&isNumeric(procesIdOfBlock.substring(1)) ))
                    flag1=true;
                    else
                    flag1=false;
                    }while(flag1);
                    
                    if(!memory.deallocate(procesIdOfBlock))
                    System.out.println("\nSORRY your request can not be done because there is no such id".toUpperCase()+" \n");
                    else{
                        System.out.println("\nyour de-allocation has completed\n".toUpperCase());
                    }
                    break;
                case 3:
                    //memory.status();
                    System.out.println(memory.status());
                    break;
                case 4:
                    System.out.println("\nThank you.");
                    cont=false;
                    break;
            }
        }while(cont);

        in.close();
    }
    public static boolean isNumeric(String string) {
        int intValue;
            
        //System.out.println(String.format("Parsing string: \"%s\"", string));
            
        if(string == null || string.equals("")) {
            //System.out.println("String cannot be parsed, it is null or empty.");
            return false;
        }
        
        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            //System.out.println("Input String cannot be parsed to Integer.");
        }
        return false;
    }
}
