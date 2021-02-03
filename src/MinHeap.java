import java.util.HashMap;

public class MinHeap {

    Node[] heap;
    int size;
    // id   index
    HashMap<Integer,Integer> in;

    public MinHeap(int max) {
        size = 0;
        heap = new Node[max + 1];
        heap[0] = new Node(-1,0,0);
        heap[0].distance = Double.MIN_VALUE;
        in = new HashMap<>();
    }

    private int parent(int index) {
        return index / 2;
    }
    private int leftChild(int index) {
        return index * 2;
    }
    private int rightChild(int index) {
        return (index * 2) + 1;
    }
    private boolean leaf(int index) {
        return index <= size && index >= (size / 2);
    }
    public void swap(int first, int second) {
        Node temp = heap[first];
        heap[first] = heap[second];
        in.put(heap[first].id,first);
        heap[second] = temp;
        in.put(temp.id,second);
    }

    private void minHeapify(int id) {
        int index = in.get(id);
        if(!leaf(index)) {
            if(heap[index].distance > heap[leftChild(index)].distance || heap[index].distance > heap[rightChild(index)].distance) {
                if(heap[index].distance > heap[leftChild(index)].distance) {
                    swap(index, leftChild(index));
                    minHeapify(leftChild(index));
                }
                if(heap[index].distance > heap[rightChild(index)].distance) {
                    swap(index, rightChild(index));
                    minHeapify(rightChild(index));
                }
            }
        }
    }
    private void maxHeapify(int id) {
        int index = in.get(id);
        if(!leaf(index)) {
            if(heap[index].distance < heap[leftChild(index)].distance || heap[index].distance < heap[rightChild(index)].distance) {
                if(heap[index].distance < heap[leftChild(index)].distance) {
                    swap(index, leftChild(index));
                    maxHeapify(leftChild(index));
                }
                if(heap[index].distance < heap[rightChild(index)].distance) {
                    swap(index, rightChild(index));
                    maxHeapify(rightChild(index));
                }
            }
        }
    }
    public void insert(Node value) {
        size++;
        heap[size] = value;
        int cur = size;
        in.put(value.id,size);
        if(size > 1){
            while(heap[cur].distance < heap[parent(cur)].distance) {
                swap(cur, parent(cur));
                cur = parent(cur);
            }
        }
    }
    private void createMaxHeap() {
        for(int pos = (size / 2); pos >= 1; pos--) {
            maxHeapify(pos);
        }
    }
    public void delete(int id) {
        int index = in.get(id);
        heap[index] = heap[size];
        in.remove(heap[index]);
        in.put(heap[size].id,index);
        size--;
        minHeapify(index);
    }
    private void heapSort() {
        for(int x = size; x >= 2 ; x--) {
            swap(1, x);
            maxHeapify(1);
            size--;
        }
    }
    public void update(Node node) {
        delete(node.id);
        insert(node);
    }
    public Node deleteMin() {
        Node min = heap[1];
        delete(min.id);
        return min;
    }
}
