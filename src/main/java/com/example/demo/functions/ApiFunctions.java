package com.example.demo.functions;

import java.util.ArrayList;
import java.util.List;

public class ApiFunctions {

    public static List getNumObjectsByNumPage(int size, int currentPage, List list) {
        int sum = (size * currentPage);
        int listSize = list.size();

        List copyList = new ArrayList<>();

        for (int i = (sum - size); i < sum; i++) {
            if(i >= listSize){
                break;
            }
            copyList.add(list.get(i));
        }
        return copyList;
    }
//pagesCount
    public static int pageSize(int objectSize, List list){
        if(list.size() % objectSize == 0){
            return list.size() / objectSize;
        }
        return (list.size() / objectSize) + 1;
    }

}
