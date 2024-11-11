package com.stfn2.ggas.tools;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ToolCSV {

    public static String mapToCsv(List<HashMap<String, Object>> list) {

        Set<String> allKeys = new HashSet<>();
        list.forEach(m -> {
            for (String key : m.keySet()) {
                Object ob = m.get(key);
                if (ob instanceof List) {
                    continue;
                }
                allKeys.add(key);
            }
        });

        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (String key : allKeys) {

            sb.append(key);
            if (i < allKeys.size()) {
                sb.append(";");
            }
            i++;
        }
        sb.append("\n");

        list.forEach(m -> {
            int j = 1;
            for (String k : allKeys) {
                Object ob = m.get(k);
                if (ob instanceof List) {
                    continue;
                } else if (ob instanceof BaseEntity obj) {
                    sb.append(obj.getId());
                } else {
                    sb.append(ob);
                }

                if (j < m.keySet().size()) {
                    sb.append(";");
                }
                j++;
            }
            sb.append("\n");
        });

        return sb.toString();
    }
}
