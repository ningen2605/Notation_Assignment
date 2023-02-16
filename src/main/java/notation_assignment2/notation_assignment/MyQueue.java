package notation_assignment2.notation_assignment;

import java.util.ArrayList;

/** Queue Structure for Notation
 * @author Ricardo Abuabara
 * @param <T>
 */

public class MyQueue<T> implements QueueInterface<T> {

    private int sizeOfQueue;
    private ArrayList<T>queue;
    int head, tail,items=0;

    public MyQueue(){
        sizeOfQueue=100;
        queue=new ArrayList<T> (sizeOfQueue);
    }

    public MyQueue(int size) {
        sizeOfQueue=size;
        queue=new ArrayList<T> (sizeOfQueue);
    }

    /**
     * Determines if Queue is empty
     * @return true if Queue is empty, false if not
     */
    @Override
    public boolean isEmpty() {
        if (items!=0)
            return false;
        else
            return true;
    }

    /**
     * Determines of the Queue is full
     * @return true if Queue is full, false is not
     */
    @Override
    public boolean isFull() {
        if (items==sizeOfQueue)
            return true;
        else
            return false;
    }

    /**
     * Deletes and returns the element at the head of the Queue
     * @return the element at the head of the Queue
     */
    @Override
    public T dequeue() throws QueueUnderflowException {
        T dequeueElement;
        if(items==0) {
            throw new QueueUnderflowException("Stack Over Flow Exception Thrown");
        }
        else {
            dequeueElement=queue.get(head);
            head++;
            items--;
        }
        return dequeueElement;
    }

    /**
     * Number of elements in the Queue
     * @return the number of elements in the Queue
     */
    @Override
    public int size() {
        return items;
    }

    /**
     * Adds an element to the tail of the Queue
     * @param e the element to add to the tail of the Queue
     * @return true if the add was successful, false if not
     */
    @Override
    public boolean enqueue(T e) throws QueueOverflowException {
        if(items==sizeOfQueue) {
            throw new QueueOverflowException("Stack Over Flow Exception Thrown");
        }
        else {
            queue.add(tail,e);
            tail++;
            items++;
            return true;
        }
    }

    /**
     * Returns the string representation of the elements in the Queue,
     * the beginning of the string is the head of the queue
     * @return string representation of the Queue with elements
     */
    @Override
    public String toString() {
        String queueStr="";
        for(T i:queue ) {
            queueStr+=i;
        }
        return queueStr;
    }

    /**
     * Returns the string representation of the elements in the Queue, the beginning of the string is the head of the queue
     * Place the delimiter between all elements of the Queue
     * @return string representation of the Queue with elements separated with the delimiter
     */
    @Override
    public String toString(String delimiter) {
        String queueStr="";

        for(int i=0;i<queue.size();i++ ){
            if(i!=queue.size()-1) {
                queueStr+=queue.get(i)+delimiter;
            }
            else {
                queueStr+=queue.get(i);
            }
        }
        return queueStr;
    }

    /**
     * Fills the Queue with the elements of the ArrayList, First element in the ArrayList
     * is the first element in the Queue
     * @param list elements to be added to the Queue
     */
    @Override
    public void fill(ArrayList<T> list) {
        ArrayList<T> copy=new ArrayList<T>(list);
        queue.addAll(copy);
        items=queue.size();

    }

}

