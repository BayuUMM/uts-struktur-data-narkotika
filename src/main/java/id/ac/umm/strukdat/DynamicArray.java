
package id.ac.umm.strukdat;

public class DynamicArray<T> implements StrukturData<T> {

    private Object[] data;
    private int size;
    private int langkahTerakhir;

    private static final int KAPASITAS_AWAL = 10;

    public DynamicArray() {
        data = new Object[KAPASITAS_AWAL];
        size = 0;
        langkahTerakhir = 0;
    }

    private void resize() {
        Object[] dataBaru = new Object[data.length * 2];

        for (int i = 0; i < size; i++) {
            dataBaru[i] = data[i];
        }

        data = dataBaru;
    }

    @Override
    public void add(T item) {
        if (size == data.length) {
            resize();
        }

        data[size] = item;
        size++;
    }

    public void insert(int index, T item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index tidak valid");
        }

        if (size == data.length) {
            resize();
        }

        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }

        data[index] = item;
        size++;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        periksaIndex(index);
        return (T) data[index];
    }

    @SuppressWarnings("unchecked")
    public T set(int index, T item) {
        periksaIndex(index);

        T nilaiLama = (T) data[index];
        data[index] = item;

        return nilaiLama;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        periksaIndex(index);

        T nilaiDihapus = (T) data[index];

        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }

        size--;
        data[size] = null;

        return nilaiDihapus;
    }

    public int search(T item) {
        langkahTerakhir = 0;

        for (int i = 0; i < size; i++) {
            langkahTerakhir++;

            if (item == null ? data[i] == null : item.equals(data[i])) {
                return i;
            }
        }

        return -1;
    }

    public int getLangkahTerakhir() {
        return langkahTerakhir;
    }

    private void periksaIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index tidak valid");
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(T item) {
        return search(item) != -1;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }

        size = 0;
        langkahTerakhir = 0;
    }

    public boolean validateInvariant() {
        if (size < 0 || size > data.length) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (data[i] == null) {
                return false;
            }
        }

        for (int i = size; i < data.length; i++) {
            if (data[i] != null) {
                return false;
            }
        }

        return true;
    }
}
