package id.ac.umm.strukdat;


public class RiwayatStack<T> {


    private Object[] data;
    private int top;


    private static final int KAPASITAS_AWAL = 10;


    public RiwayatStack() {
        data = new Object[KAPASITAS_AWAL];
        top = -1;
    }


    private void resize() {
        Object[] dataBaru = new Object[data.length * 2];


        for (int i = 0; i <= top; i++) {
            dataBaru[i] = data[i];
        }


        data = dataBaru;
    }


    // Menambahkan data ke bagian paling atas
    public void push(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Data tidak boleh null");
        }


        if (top + 1 == data.length) {
            resize();
        }


        top++;
        data[top] = item;
    }


    // Mengambil sekaligus menghapus data paling atas
    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack kosong");
        }


        T item = (T) data[top];
        data[top] = null;
        top--;


        return item;
    }


    // Melihat data paling atas tanpa menghapusnya
    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack kosong");
        }


        return (T) data[top];
    }


    public int size() {
        return top + 1;
    }


    public boolean isEmpty() {
        return top == -1;
    }


    public void clear() {
        while (!isEmpty()) {
            data[top] = null;
            top--;
        }
    }


    public boolean validateInvariant() {
        if (top < -1 || top >= data.length) {
            return false;
        }


        for (int i = 0; i <= top; i++) {
            if (data[i] == null) {
                return false;
            }
        }


        for (int i = top + 1; i < data.length; i++) {
            if (data[i] != null) {
                return false;
            }
        }


        return true;
    }
}