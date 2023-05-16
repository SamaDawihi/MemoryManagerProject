class Memory{

    private static int SIZE = 1024;
    Partition[] partion;
    Approaches allocationStrategy;
    int noOfPartitions;

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
        FirstFitAllocate(PID,size);
            break;
        case BEST_FIT:
            BestFitAllocate(PID,size);
            break;
        case WORST_FIT:

        break;
        default:
            // Invalid approach.
         }
    return true;
    }
    public boolean BestFitAllocate(String PID, int Psize){
        int smallestFit = 0;
        for(int i = 1; i < noOfPartitions; i++){
            if(Psize <= partion[i].getSize() && partion[i].isFree() && PID != null){
                if(partion[i].getSize() < partion[smallestFit].getSize() || !partion[smallestFit].isFree())
                    smallestFit = i;
            }
        }

        if(partion[smallestFit].isFree()){
            partion[smallestFit].setStatus(false);
            partion[smallestFit].setProcessID(PID);
            partion[smallestFit].CalcInternalFragment(Psize);
            return true;
        }
        
        return false;
        
    }

    private void FirstFitAllocate(String PID, int Psize){
        for(int i = 0; i < noOfPartitions; i++){
            if(Psize <= partion[i].getSize() && partion[i].isFree() && PID != null){
                partion[i].setSize(Psize);
                partion[i].setStatus(false);
                partion[i].setProcessID(PID);
                partion[i].CalcInternalFragment(Psize);
            }
        }

    }

    boolean deallocate(int size){
        return true;
    }
    String status(){
        return null;
    }

}