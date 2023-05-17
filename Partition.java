public class Partition {
    private int size;//IN KB
    private boolean isFree;
    private int addressStart;
    private int addressEnd;
    private String processID; //PN --> P1
    private int internalFragmentation;

    void printo(){
        System.out.println("processID"+processID+" size"+size+" isFree"+isFree+" addressStart"+addressStart
        +" addressEnd"+addressEnd+" internalFragmentation"+internalFragmentation);
    }
    
    Partition(int size,int addressStart,int addressEnd) {
        this.size = size;
        this.isFree = true;
        this.addressStart = addressStart;
        this.addressEnd = addressEnd;
        this.processID = null;
        this.internalFragmentation = -1;
    }
    Partition() {
    }

    public void CalcInternalFragment(int Psize){
         this.internalFragmentation = size - Psize; 
    }
    

    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public boolean isFree() {
        return isFree;
    }
    public String getStatus(){
        return (isFree)?"free":"allocated";
    }
    public void setStatus(Boolean isFree) {
        this.isFree = isFree;
    }
    public int getAddressStart() {
        return addressStart;
    }
    public void setAddressStart(int addressStart) {
        this.addressStart = addressStart;
    }
    public int getAddressEnd() {
        return addressEnd;
    }
    public void setAddressEnd(int addressEnd) {
        this.addressEnd = addressEnd;
    }
    public String getProcessID() {
        return processID;
    }
    public void setProcessID(String processID) {
        this.processID = processID;
    }
    public int getInternalFragmentation() {
        return internalFragmentation;
    }
    
    public void setInternalFragmentation(int internalFragmentation) {
        this.internalFragmentation = internalFragmentation;
    }
}
