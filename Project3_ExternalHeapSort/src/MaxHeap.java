import java.io.IOException;

/**
 * Class that builds a MaxHeap using data from given Buffer Pool
 * 
 * @author Cuong Ngo (ngoct)
 * @author Yongjae Lim (yongjae)
 * 
 * @version 08/05/2020
 */
public class MaxHeap {

    private BufferPool dataPool; // Pointer to the heap array
    private int maxSize; // Maximum size of the heap
    private int currSize; // Number of things now in heap


    /**
     * Constructor that initializes a MaxHeap
     * 
     * @param h
     *            the array of Records to be made into MaxHeap
     * @param num
     *            the current size of the array of Records
     * @param max
     *            the max size of the array of Records
     * @throws IOException
     */
    public MaxHeap(BufferPool h, int num, int max) throws IOException {
        dataPool = h;
        currSize = num;
        maxSize = max;
        buildheap();

    }


    /**
     * Getter for current Heap size
     * 
     * @return Current size of the heap
     */
    public int heapsize() {
        return currSize;
    }


    /**
     * Determine if a position in the array of the MaxHeap is a leaf
     * 
     * @param pos
     *            the position to check
     * @return true if pos a leaf position, false otherwise
     */
    public boolean isLeaf(int pos) {
        return (pos >= currSize / 2) && (pos < currSize);
    }


    /**
     * Get the position of left child of specified Node in the MaxHeap
     * 
     * @param pos
     *            the position to find the left child for
     * @return array position for left child of pos
     */
    public int leftchild(int pos) {
        if (pos >= currSize / 2) {
            return -1;
        }
        return 2 * pos + 1;
    }


    /**
     * Get the position of right child of specified Node in the MaxHeap
     * 
     * @param pos
     *            the position to find the right child for
     * @return array position for right child of pos
     */
    public int rightchild(int pos) {
        if (pos >= (currSize - 1) / 2) {
            return -1;
        }
        return 2 * pos + 2;
    }


    /**
     * Get the position of parent of specified Node in the MaxHeap
     * 
     * @param pos
     *            the position to find the parent for
     * @return array position for parent of pos
     */
    public int parent(int pos) {
        if (pos <= 0) {
            return -1;
        }
        return (pos - 1) / 2;
    }


    /**
     * Insert method that inserts a new Element into the Heap if it's not full
     * and will rearrange Heap if needed to maintain MaxHeap property
     * 
     * @param rec
     *            the new element to be inserted
     * @throws IOException
     */
    public void insert(Record rec) throws IOException {

        // If Heap is full
        if (currSize >= maxSize) {
            System.out.println("Heap is full");
            return;
        }

        // Otherwise, insert element and sift to correct position, if needed
        int curr = currSize++;
        dataPool.insert(rec, curr);

        // Now sift up until curr's parent's key > curr's key
        while ((curr != 0) && (dataPool.get(curr).compareTo(dataPool.get(parent(
            curr))) > 0)) {
            swap(curr, parent(curr));
            curr = parent(curr);
        }
    }


    /**
     * Helper method that heapify contents of MaxHeap
     * 
     * @throws IOException
     */
    public void buildheap() throws IOException {
        for (int i = currSize / 2 - 1; i >= 0; i--) {
            siftdown(i);
        }
    }


    /**
     * Method that sifts an element down to its correct place
     * 
     * @param pos
     *            the initial position of the element
     * @throws IOException
     */
    public void siftdown(int pos) throws IOException {

        // If position is invalid
        if ((pos < 0) || (pos >= currSize)) {
            return;
        }

        // Sift down until is at correct position
        while (!isLeaf(pos)) {
            int j = leftchild(pos);

            if ((j < (currSize - 1)) && (dataPool.get(j).compareTo(dataPool.get(
                j + 1)) < 0)) {
                j++; // j is now index of child with greater value
            }
            if (dataPool.get(pos).compareTo(dataPool.get(j)) >= 0) {
                return;
            }

            swap(pos, j);
            pos = j; // Move down
        }
    }


    /**
     * Remove and return the root, which contains maximum value in Heap
     * 
     * @return the current root of the Heap
     * @throws IOException
     */
    public Record removemax() throws IOException {
        if (currSize == 0) {
            return null; // Removing from empty heap
        }
        swap(0, --currSize); // Swap maximum with last value
        siftdown(0); // Put new heap root val in correct place

        return dataPool.get(currSize);
    }


    /**
     * Remove and return element at specified position
     * 
     * @param pos
     *            the specified position to remove
     * @return the element at that position
     * @throws IOException
     */
    public Record remove(int pos) throws IOException {
        if ((pos < 0) || (pos >= currSize)) {
            return null; // Illegal heap position
        }

        if (pos == (currSize - 1)) {
            currSize--; // Last element, no work to be done
        }
        else {
            swap(pos, --currSize); // Swap with last value
            update(pos);
        }

        return dataPool.get(currSize);
    }


    /**
     * Modify the value at the given position in Heap and if needed, calls other
     * methods to maintain MaxHeap property
     * 
     * @param pos
     *            the position to be modified
     * @param newVal
     *            the new element to insert into that position
     * @throws IOException
     */
    public void modify(int pos, Record newVal) throws IOException {
        if ((pos < 0) || (pos >= currSize)) {
            return; // Illegal heap position
        }

        dataPool.insert(newVal, pos);
        update(pos);
    }


    /**
     * When the value at the specified pos has been changed, this method will
     * restore the heap property
     * 
     * @param pos
     *            the position to restore Heap Property
     * @throws IOException
     */
    public void update(int pos) throws IOException {
        // If it is a big value, push it up
        while ((pos > 0) && (dataPool.get(pos).compareTo(dataPool.get(parent(
            pos))) > 0)) {
            swap(pos, parent(pos));
            pos = parent(pos);
        }
        siftdown(pos); // If it is little, push down
    }


    /**
     * Helper method that swaps positions of 2 elements in array
     * 
     * @param posA
     *            position of first element to be swapped
     * @param posB
     *            position of second element to be swapped
     * @throws IOException
     */
    private void swap(int posA, int posB) throws IOException {
        // Need to get Deep Copy instead of alias because both data at posA and
        // posB are being modified
        Record temp = dataPool.get(posA).getDeepCopy();
        dataPool.insert(dataPool.get(posB), posA);
        dataPool.insert(temp, posB);
    }

}
