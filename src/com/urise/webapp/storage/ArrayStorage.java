package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size;

    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
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
        if (resume == null) throw new IllegalArgumentException("Resume is not null");
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
        if (resume == null) throw new IllegalArgumentException("Resume is not null");
        // проверка массива на заполненность
        if (storage.length == size) {
            System.out.println("Storage is full");
            return;
        }
        // проверка на уникальность резюме
        if (get(resume.getUuid()) != null) {
            System.out.println("Resume " + resume + " is already exist in storage");
        } else {
            storage[size] = resume;
            size++;
            System.out.println("Resume " + resume + " is successfully added to storage");
        }
    }

    public Resume get(String uuid) {
        if (uuid == null) throw new IllegalArgumentException("Uuid is not null");
        // проверить что резюме есть в базе
        int index = getIndex(uuid);
        if (index >= 0) {
            System.out.println("Resume " + uuid + " is exist in storage");
            return storage[index];

        }
        return null;
    }

    public void delete(String uuid) {
        if (uuid == null) throw new IllegalArgumentException("Uuid not null");
        // проверить что резюме есть в базе
        int index = getIndex(uuid);
        if (index >= 0) {
            storage[index] = storage[--size];
            storage[size] = null;
            System.out.println("Resume " + uuid + " is successfully deleted from storage");
        }
        else System.out.println("Resume " + uuid + " is not exist in storage");
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] resumes = new Resume[size];
        System.arraycopy(storage, 0, resumes, 0, size);
        return resumes;
    }

    public int size() {
        return size;
    }

    public Resume[] getStorage() {
        return storage;
    }

    public void setStorage(Resume[] storage) {
        this.storage = storage;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}



