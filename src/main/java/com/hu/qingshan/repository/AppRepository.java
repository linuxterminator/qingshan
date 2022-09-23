package com.hu.qingshan.repository;

import java.util.List;

public interface AppRepository<T> {

    void removeByRepository(String data);

    void saveByRepository(T data);

}
