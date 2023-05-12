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

    boolean allocate(int size){
        
    switch (allocationStrategy) {
        case FIRST_FIT:
        
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
    boolean deallocate(int size){
        return true;
    }
    String status(){
        return null;
    }

}