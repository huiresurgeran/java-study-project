package com.jsamuel.study;

import com.jsamuel.study.api.Search;
import java.util.Iterator;
import java.util.ServiceLoader;
import org.junit.Test;

public class TestSpi {

    @Test
    public void testSpi () {
        ServiceLoader<Search> loader = ServiceLoader.load(Search.class);
        Iterator<Search> it = loader.iterator();
        while(it.hasNext()) {
            Search search = it.next();
            search.searchDoc("hello world");
        }
    }
}
