package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {


    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        int index = Arrays.binarySearch(storage, 0, size, searchKey);
        return index;
    }

    @Override
    protected void addToArray(Resume resume, int index) {
        if(Math.abs(index) > size) {
            storage[size] = resume;
        } else {
            for (int i = size - 1; i >= Math.abs(index) - 1; i--) {
                storage[i + 1] = storage[i];
            }
            storage[Math.abs(index) - 1] = resume;
        }
        size++;

    }

    @Override
    protected void deleteFromArray(int index) {
        for (int i = index; i < size; i++) {
            storage[i] = storage[i + 1];
        }
        storage[size - 1] = null;
        size--;

    }


}
