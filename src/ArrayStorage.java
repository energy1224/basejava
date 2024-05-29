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
        if (r == null) throw new IllegalArgumentException("Resume is not null");
        // проверка массива на заполненность
        if (storage.length == size) {
            System.out.println("ArrayStorage is full");
            return;
        }
        // проверка на уникальность резюме
        if (get(r.uuid) != null) {
            System.out.println("Resume" + r + " is already exist");
            return;
        }
        storage[size] = r;
        size++;
    }

    Resume get(String uuid) {
        if (uuid == null) throw new IllegalArgumentException("Uuid is not null");
        for (int i = 0; i < size; i++) {
            if (this.storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        if (uuid == null) throw new IllegalArgumentException("Uuid not null");
        for (int i = 0; i < size; i++) {
            if (this.storage[i].uuid.equals(uuid)) {
                size--;
                storage[i] = storage[size];
                storage[size] = null;
                return;
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



