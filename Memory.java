class Memory{

    private static int SIZE = 1024;
    Partition[] partion;
    Approaches allocationStrategy;
    int noOfPartitions;

    Memory(int noOfPartitions){
        partion = new Partition[noOfPartitions];
        this.noOfPartitions = noOfPartitions;
        for(int i=0;i<this.noOfPartitions;i++)
        partion[i]=new Partition();

    }
    void setAllocationStrategy(Approaches allocationStrategy){
        this.allocationStrategy = allocationStrategy;
    }
    Memory(int noOfPartitions, Approaches allocationStrategy){
        partion = new Partition[noOfPartitions];
        this.allocationStrategy = allocationStrategy;
        this.noOfPartitions = noOfPartitions;

    }

    void createPartition(int index, int size, int start, int end){
        partion[index] = new Partition(size, start, end);

    }
    
    public static int getSize(){
        return SIZE;
    }

    boolean allocate(String PID, int size){
        
    switch (allocationStrategy) {
        case FIRST_FIT:
            return FirstFitAllocate(PID,size);
        case BEST_FIT:
            return BestFitAllocate(PID,size);
        case WORST_FIT:
            return WorstFit(PID, size);
        default:
            return false;
        }
    }
    public boolean BestFitAllocate(String PID, int Psize){
        if(PID == null) return false;

        int smallestFit = -1;

        for(int i = 0; i < noOfPartitions; i++){
          if(partion[i].isFree() && partion[i].getSize() >= Psize ){
            smallestFit = i;
          
            for(int j = i+1 ; j<noOfPartitions ; j++){
                if(partion[j].isFree() && partion[j].getSize() >= Psize && partion[j].getSize() < partion[smallestFit].getSize() ){
                    smallestFit = j;
                }
            }
            break;
          }
        } // end of outer loop

        if(smallestFit != -1){
            partion[smallestFit].setStatus(false);
            partion[smallestFit].setProcessID(PID);
            partion[smallestFit].CalcInternalFragment(Psize);
            return true;
        }
       return false;
    }

    private boolean FirstFitAllocate(String PID, int Psize){
        if(PID == null) return false;

        for(int i = 0; i < noOfPartitions; i++){
            if(Psize <= partion[i].getSize() && partion[i].isFree()){


                partion[i].setStatus(false);
                partion[i].setProcessID(PID);
                partion[i].CalcInternalFragment(Psize);
                return true;
            }
        }
        return false;
    }

        private boolean WorstFit(String PID, int Psize){
            if(PID == null) return false;
            int worst = -1;

            for(int i=0; i<noOfPartitions; i++ ){
                if(partion[i].isFree() && partion[i].getSize() >= Psize ){
                    worst = i;
                
                    for(int j = i+1 ; j<noOfPartitions ; j++){
                        if(partion[j].isFree() && partion[j].getSize() >= Psize && partion[j].getSize() > partion[worst].getSize() ){
                            worst = j;
                        }
                    }
                    break;
                }
            } // end of outer loop
        if(worst != -1){
            partion[worst].setStatus(false);
            partion[worst].setProcessID(PID);
            partion[worst].CalcInternalFragment(Psize);
            return true;
        }
       return false;

    }

    boolean deallocate(String PID){
        return true;
    }
    String status(){
        return null;
    }

}