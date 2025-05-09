package core;

// Design Dynamic array
class DynamicArray {

    int[] arr;
    int capacity;
    int end;


    public DynamicArray(int capacity) {
        this.capacity = capacity;
        end = -1;
        arr = new int[capacity];
    }

    public int get(int i) {
        return arr[i];
    }

    public void set(int i, int n) {
        if(i>end) end = i;
        arr[i] = n;
    }

    public void pushback(int n) {
        if(capacity == end+1){
            resize();
        }
        end++;
        arr[end] = n;
    }

    public int popback() {
        int endElement = arr[end];
        end--;
        return endElement;
    }

    private void resize() {
        int[] tmp = arr;
        capacity *= 2;
        arr = new int[capacity];

        for (int i=0; i<tmp.length; i++){
            arr[i] = tmp[i];
        }
    }

    public int getSize() {
        return end+1;
    }

    public int getCapacity() {
        return capacity;
    }

}
