package com.jsamuel.study.spi;

import com.jsamuel.study.api.Search;
import java.util.List;

public class DbSearch implements Search {

    @Override
    public List<String> searchDoc(String keyword) {
        System.out.println("search by DB, keyword: " + keyword);
        return null;
    }
}
