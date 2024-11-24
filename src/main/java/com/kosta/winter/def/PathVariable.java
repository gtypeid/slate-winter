package com.kosta.winter.def;

import java.util.ArrayList;

public class PathVariable {
    public ArrayList<String> path = new ArrayList<>();
    public ArrayList<String> value = new ArrayList<>();

    @Override
    public String toString() {
        return "PathVariable{" +
                "path=" + path +
                ", value=" + value +
                '}';
    }
}
