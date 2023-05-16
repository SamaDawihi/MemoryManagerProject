class Memory{

    private static int SIZE = 1024;
    Partition[] partion;
    Approaches allocationStrategy;

    Memory(int noOfPartitions, Approaches allocationStrategy){
        partion = new Partition[noOfPartitions];
        this.allocationStrategy = allocationStrategy;

    }

    void createPartition(int index, int size, int start, int end){
        partion[index] = new Partition(size, start, end);

    }
    
    public static int getSize(){
        return SIZE;
    }

    boolean allocate(String PID, int size,int PortinIndex){
        
    switch (allocationStrategy) {
        case FIRST_FIT:
        FirstFitAllocate(PID,size,PortinIndex);
            break;
        case BEST_FIT:
        
            break;
        case WORST_FIT:

        break;
        default:
            // Invalid approach.
         }
    return true;
    }

        private void FirstFitAllocate(String PID, int Psize,int PortinIndex){
        if(Psize <= Partition[PortinIndex].getSize() && Partition[PortinIndex].isFree() && PID != null){
            Partition[PortinIndex].setSize(Psize);
            Partition[PortinIndex].setStatus(false);
            Partition[PortinIndex].setProcessID(PID);
            Partition[PortinIndex].CalcInternalFragment(Psize);
        }

    }



    boolean deallocate(int size){
        return true;
    }
    String status(){
        return null;
    }

}