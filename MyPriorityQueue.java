public class MyPriorityQueue<E> {
    private Object[] arr;
    private int size;
    private Comparator<? super E> comparator;

    private static final int DEFAULT_CAPACITY=11;

    public  MyPriorityQueue(){
        this(DEFAULT_CAPACITY,null);
    }

    public MyPriorityQueue(int initialCapacity){
        this(initialCapacity,null);
    }

    public MyPriorityQueue(int initialCapacity,Comparator<? super E> co){
        if(initialCapacity<1){
            throw new IllegalArgumentException();
        }else {
            arr = new Object[initialCapacity];
            this.comparator = co;
        }
    }

    public boolean offer(E val){
        grow();
        arr[size++]=val;
        shiftUp(size-1);
        return true;
    }

    public E poll(){
        if(0==size){
            throw new IndexOutOfBoundsException();
        }
        E res=(E)arr[0];
        swap(0,size-1);
        size--;
        shiftDown(0);
        return res;
    }

    public E peek(){
        if(0==size){
            throw new IndexOutOfBoundsException();
        }
        return (E)arr[0];
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return 0==size;
    }
    //向下调整
    private void shiftDown(int parent){
        if(null==comparator){
            shiftDownUseComparable(parent);
        }else{
            shiftDownUseComparator(parent);
        }
    }

    private void shiftDownUseComparator(int parent) {
        int left=parent*2+1;
        int min=left;
        while(left<size){
            int right=parent*2+2;
            if(right<size&&comparator.compare((E)arr[left],(E)arr[right])>0){
                min=right;
            }
            if(comparator.compare((E)arr[min],(E)arr[parent])>0){
                break;
            }
            swap(parent,min);
            parent=min;
            left=parent*2+1;
        }
    }

    private void shiftDownUseComparable(int parent) {
        int left=parent*2+1;
        int min=left;
        while(left<size){
            int right=parent*2+2;
            if(right<size&&((Comparable<? super E>)arr[min]).compareTo
                    ((E)arr[right])>0){
                min=right;
            }
            if(((Comparable<? super E>)arr[min]).compareTo
                    ((E)arr[parent])>0){
                break;
            }
            swap(parent,min);
            parent=min;
            left=parent*2+1;
        }
    }
    //向上调整
    private void shiftUp(int child) {
        if(null==comparator){
            shiftUpUseComparable(child);
        }else{
            shiftUpUseComparator(child);
        }
    }

    private void shiftUpUseComparator(int child) {
        int parent=(child-1)>>>1;
        while(parent>=0){
            if(comparator.compare((E)arr[parent],(E)arr[child])<0){
                break;
            }
            swap(parent,child);
            child=parent;
            parent=(child-1)>>>1;
        }
    }

    private void shiftUpUseComparable(int child) {
        int parent=(child-1)/2;
        while(parent>=0){
            if(((Comparable<? super E>)arr[parent]).compareTo
                    ((E)arr[child])<0){
                break;
            }
            swap(parent,child);
            child=parent;
            parent=(child-1)>>1;
        }

    }
    private void swap(int i,int j){
        Object tmp=arr[i];
        arr[i]=arr[j];
        arr[j]=tmp;
    }

    private void grow() {
        int oldCapacity=arr.length;
        if(size>=arr.length){
            int newCapacity=oldCapacity+((oldCapacity<64)?(oldCapacity+2):
                    (oldCapacity>>>1));
            arr=Arrays.copyOf(arr,newCapacity);
        }
    }