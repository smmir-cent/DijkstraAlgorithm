import java.awt.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class MinHeap {

    private Node[] heap;
    private int size;
    // id   index
    private HashMap<Integer,Integer> in;
    private static final int FRONT = 1;
    private ArrayList<Node> nodes;
    private int max;
    public MinHeap(int max) {
        this.max =max;
        size = 0;
        heap = new Node[max + 1];
        heap[0] = new Node(-1,0,0);
        heap[0].distance = Double.MIN_EXPONENT;
        in = new HashMap<>();
        nodes= new ArrayList<>();
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
        in.put(heap[second].id,second);
    }

    private void minHeapify(int index) {
        //int index = in.get(id);
        if(!leaf(index)) {
            if(heap[index].distance > heap[leftChild(index)].distance || heap[index].distance > heap[rightChild(index)].distance) {
                if(heap[rightChild(index)].distance > heap[leftChild(index)].distance) {
                    swap(index, leftChild(index));
                    minHeapify(leftChild(index));
                }
                else{
                    swap(index, rightChild(index));
                    minHeapify(rightChild(index));
                }
            }
        }
    }

    public void insert(Node value) {
        if(!nodes.contains(value)){
            nodes.add(value);
        }

        //size++;
        heap[++size] = value;
        int cur = size;
        in.put(value.id,size);
        if(size > 1){
            while(heap[cur].distance < heap[parent(cur)].distance) {
                swap(cur, parent(cur));
                cur = parent(cur);
            }
        }
    }

    public void delete(int id) {
//        int index = in.get(id);
//        heap[index].distance=Double.MIN_VALUE;
//        while(index !=0 && heap[parent(index)].distance>heap[index].distance){
//            swap(index,parent(index));
//            index=parent(index);
//        }
//        deleteMin();

//        int index = in.get(id);
//        heap[index] = heap[size--];
//        in.remove(id);
//        in.put(heap[index].id,index);
//        heap[size+1] = null;
//        minHeapify(index);

        int index = in.get(id);
        swap(index,size);
        //heap[size--]=null;
        minHeapify(index);


//        int index = in.get(id);
//        heap[index] = heap[size];
//        in.remove(heap[index].id);
//        in.put(heap[size].id,index);
//        size--;
//        minHeapify(index);
    }
    public void update(Node node) {
        double distance = node.distance;
        delete(node.id);
        node.distance = distance;
        insert(node);
    }
    public Node deleteMin() {
        Node popped = heap[FRONT];
        heap[FRONT] = heap[size--];
        //heap[size+1]=null;
        in.remove(popped.id);
        in.put(heap[FRONT].id, FRONT);
        minHeapify(FRONT);
        return popped;

//        Node min = heap[0];
//        delete(min.id);
//        return min;
    }
    public void minHeapResetting(){
        size = 0;
        heap = new Node[max + 1];
        heap[0] = new Node(-1,0,0);
        heap[0].distance = Double.MIN_EXPONENT;
        in = new HashMap<>();
        for(Node node:nodes){
            insert(node);
        }
    }




}
