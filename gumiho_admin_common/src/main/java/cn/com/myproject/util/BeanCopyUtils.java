package cn.com.myproject.util;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BeanCopyUtils {


    public static<E,T> List<T> copyList(List<E> source, Class<T> target) {
        if(source == null || source.isEmpty()) {
            return null;
        }
        List<T> result = new ArrayList<>();
        for(E e:source) {
            result.add(convert(e,target));
        }
        return result;
    }

    public static<E,T> PageInfo<T> copyList(PageInfo<E> pageInfo, Class<T> target) {
        if(pageInfo == null) {
            return null;
        }
        List<E> list = pageInfo.getList();
        pageInfo.setList(null);
        PageInfo<T> pageInfo1 = new PageInfo<>();
        BeanUtils.copyProperties(pageInfo,pageInfo1);

        if(list != null && !list.isEmpty()) {
            pageInfo1.setList(copyList(list,target));
        }
        return pageInfo1;
    }

    public static<E,T> ArrayList<T> copyArrayList(ArrayList<E> source, Class<T> target) {
        if(source == null || source.isEmpty()) {
            return null;
        }
        ArrayList<T> result = new ArrayList<>();
        for(E e:source) {
            result.add(convert(e,target));
        }
        return result;
    }

    public static<E,T> LinkedList<T> copyLinkedList(LinkedList<E> source, Class<T> target) {
        if(source == null || source.isEmpty()) {
            return null;
        }
        LinkedList<T> result = new LinkedList<>();
        for(E e:source) {
            result.add(convert(e,target));
        }
        return result;
    }

    private static <T,E> T convert(E e,Class<T> target) {
        T t = null;
        try {

            t = target.newInstance();
            BeanUtils.copyProperties(e,t);
            return t;
        } catch (InstantiationException e1) {
            e1.printStackTrace();
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        }
        return null;
    }
}
