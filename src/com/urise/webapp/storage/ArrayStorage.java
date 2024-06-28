package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final int STORAGE_LIMIT = 10000;
    private Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size - 1, null);
        size = 0;
    }

    // getIndex() проверяет  наличие резюме по uuid в базе и
    // возвращает  его индекс, либо -1 если его нет.
    public int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid()))
                return i;
        }
        return -1;
    }

    public void update(Resume resume) {
        // проверить что резюме есть в базе
        int index = getIndex(resume.getUuid());
        if (index == -1) {
            System.out.println("Resume " + resume + " is not exist in storage");
        } else {
            storage[index] = resume;
            System.out.println("Resume " + resume + " is successfully updated in storage");
        }
    }

    public void save(Resume resume) {
        // проверка массива на заполненность
        if (storage.length == size) {
            System.out.println("Storage is full");
            //return;
        } else if (getIndex(resume.getUuid()) != -1) {
            System.out.println("Resume " + resume + " is already exist in storage");
        } else {
            storage[size] = resume;
            size++;
            System.out.println("Resume " + resume + " is successfully added to storage");
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume " + uuid + " is not exist in storage");
        } else {
            System.out.println("Resume " + uuid + " is exist in storage");
            return storage[index];
        }
        return null;
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume " + uuid + " is not exist in storage");
        } else {
            storage[index] = storage[--size];
            storage[size] = null;
            System.out.println("Resume " + uuid + " is successfully deleted from storage");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] resumes = Arrays.copyOf(storage, size);
        return resumes;
    }

    public int size() {
        return size;
    }
}



