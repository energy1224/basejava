import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size;

    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume r) {
        if (r == null) throw new IllegalArgumentException("resume not null");
        storage[size] = r;
        size++;
    }

    Resume get(String uuid) {
        if (uuid == null) throw new IllegalArgumentException("uuid not null");
        for (int i = 0; i < size; i++) {
            if (this.storage[i].uuid == uuid)
                return storage[i];
        }
        return null;
    }

    void delete(String uuid) {
        if (uuid == null) throw new IllegalArgumentException("uuid not null");
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                size--;
                storage[i] = storage[size];
                storage[size] = null; //обнулениe ячейки с последним резюме после удаления
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resumes = new Resume[size];
        for (int i = 0; i < size; i++) {
            resumes[i] = storage[i];
        }
        return resumes;
    }

    int size() {
        return size;
    }

}

