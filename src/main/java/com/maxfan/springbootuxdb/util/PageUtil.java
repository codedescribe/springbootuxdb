package com.maxfan.springbootuxdb.util;

import java.util.List;

/**
 * 自定义List分页工具
 */
public class PageUtil {
 
    /**
     * 开始分页
     * @param <T>
     *
     * @param list
     * @param pageNum  页码
     * @param pageSize 每页多少条数据
     * @return
     */
    public static <T> List<T> startPage(List<T> list, Integer pageNum, Integer pageSize) {
        if(list == null){
            return null;
        }
        if(list.size() == 0){
            return null;
        }
 
        Integer count = list.size(); //记录总数
        Integer pageCount = 0; //页数
        if (count % pageSize == 0) {
            pageCount = count / pageSize;
        } else {
            pageCount = count / pageSize + 1;
        }
 
        int fromIndex = 0; //开始索引
        int toIndex = 0; //结束索引
 
        if(pageNum > pageCount){//限制最后一页，如果超出
            pageNum = pageCount;
        }
        if (!pageNum.equals(pageCount)) {
            fromIndex = (pageNum - 1) * pageSize;
            toIndex = fromIndex + pageSize;
        } else {
            fromIndex = (pageNum - 1) * pageSize;
            toIndex = count;
        }
 
        List pageList = list.subList(fromIndex, toIndex);
 
        return pageList;
    }
    
    public static <T> int getPageNum(List<T> list,int pageSize) {
    	   Integer count = list.size(); //记录总数
           Integer pageCount = 0; //页数
           if (count % pageSize == 0) {
               pageCount = count / pageSize;
           } else {
               pageCount = count / pageSize + 1;
           }
           return pageCount;
    }
}
