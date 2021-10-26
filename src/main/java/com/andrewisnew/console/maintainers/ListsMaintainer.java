package com.andrewisnew.console.maintainers;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListsMaintainer {
    private Map<String, List<String>> lists = new HashMap<>();

    public boolean addList(@Nonnull String name) {
        if (lists.containsKey(name)) {
            return false;
        }
        lists.put(name, new ArrayList<>());
        return true;
    }

    @Nullable
    public List<String> getList(@Nonnull String name) {
        return lists.get(name);
    }
}
