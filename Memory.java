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
        int smallestFit = -1;

        if(PID == null) return false;

        for(int i=0; i<noOfPartitions; i++; ){
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

        int worst = -1;

        if(PID == null) return false;

        for(int i=0; i<noOfPartitions; i++; ){
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

    boolean deallocate(int size){
        return true;
    }
    String status(){
        return null;
    }

}