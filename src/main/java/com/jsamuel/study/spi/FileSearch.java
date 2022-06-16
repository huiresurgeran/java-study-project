package com.jsamuel.study.spi;

import com.jsamuel.study.api.Search;
import java.util.List;

public class FileSearch implements Search {

    @Override
    public List<String> searchDoc(String keyword) {
        System.out.println("search by doc, keyword: " + keyword);
        return null;
    }
}
