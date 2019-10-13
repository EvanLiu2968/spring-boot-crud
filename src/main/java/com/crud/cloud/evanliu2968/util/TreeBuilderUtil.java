package com.crud.cloud.evanliu2968.util;

import com.crud.cloud.evanliu2968.dto.response.admin.DeptRes;
import com.crud.cloud.evanliu2968.dto.response.home.HomePageClassifyPersonRes;
import com.crud.cloud.evanliu2968.dto.response.person.ClassifyListRes;
import com.crud.cloud.evanliu2968.po.ClassifyInfoPO;

import java.util.ArrayList;
import java.util.List;

public class TreeBuilderUtil {

    /**
     * 使用递归方法建树
     * @param homePageClassifyPersonResList
     * @return
     */
    public static List<HomePageClassifyPersonRes> buildClassifyPersonCount(List<HomePageClassifyPersonRes> homePageClassifyPersonResList) {
        List<HomePageClassifyPersonRes> trees = new ArrayList<>();
        for (HomePageClassifyPersonRes classifyInfoPO : homePageClassifyPersonResList) {
            if (0 == classifyInfoPO.getParentId()) {
                trees.add(findClassifyPersonCountChildren(classifyInfoPO,homePageClassifyPersonResList));
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     * @param homePageClassifyPersonResList
     * @return
     */
    public static HomePageClassifyPersonRes findClassifyPersonCountChildren(HomePageClassifyPersonRes classifyInfoPO,List<HomePageClassifyPersonRes> homePageClassifyPersonResList) {
        classifyInfoPO.setChildren(new ArrayList<>());
        for (HomePageClassifyPersonRes it : homePageClassifyPersonResList) {
            if(classifyInfoPO.getId().equals(it.getParentId())) {
                if (classifyInfoPO.getChildren() == null) {
                    classifyInfoPO.setChildren(new ArrayList<>());
                }
                classifyInfoPO.getChildren().add(findClassifyPersonCountChildren(it,homePageClassifyPersonResList));
            }
        }
        return classifyInfoPO;
    }


    /**
     * 使用递归方法人才库建树
     * @param classifyInfoPOList
     * @return
     */
    public static List<ClassifyListRes> buildClassifyInByRecursive(List<ClassifyListRes> classifyInfoPOList) {
        List<ClassifyListRes> trees = new ArrayList<>();
        for (ClassifyListRes classifyInfoPO : classifyInfoPOList) {
            if (0 == classifyInfoPO.getParentId()) {
                trees.add(findClassifyChildren(classifyInfoPO,classifyInfoPOList));
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     * @param list
     * @return
     */
    public static ClassifyListRes findClassifyChildren(ClassifyListRes classifyInfoPO,List<ClassifyListRes> list) {
        classifyInfoPO.setChildren(new ArrayList<>());
        for (ClassifyListRes it : list) {
            if(classifyInfoPO.getId().equals(it.getParentId())) {
                if (classifyInfoPO.getChildren() == null) {
                    classifyInfoPO.setChildren(new ArrayList<>());
                }
                classifyInfoPO.getChildren().add(findClassifyChildren(it,list));
            }
        }
        return classifyInfoPO;
    }


    /**
     * 使用递归方法人才库建树
     * @param classifyInfoPOList
     * @return
     */
    public static List<ClassifyInfoPO> buildClassifyByRecursive(List<ClassifyInfoPO> classifyInfoPOList) {
        List<ClassifyInfoPO> trees = new ArrayList<>();
        for (ClassifyInfoPO classifyInfoPO : classifyInfoPOList) {
            if (0 == classifyInfoPO.getParentId()) {
                trees.add(findChildrenByClassifyInfo(classifyInfoPO,classifyInfoPOList));
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     * @param classifyInfoPOList
     * @return
     */
    public static ClassifyInfoPO findChildrenByClassifyInfo(ClassifyInfoPO classifyInfoPO,List<ClassifyInfoPO> classifyInfoPOList) {
        classifyInfoPO.setChildren(new ArrayList<>());
        for (ClassifyInfoPO it : classifyInfoPOList) {
            if(classifyInfoPO.getId().equals(it.getParentId())) {
                if (classifyInfoPO.getChildren() == null) {
                    classifyInfoPO.setChildren(new ArrayList<>());
                }
                classifyInfoPO.getChildren().add(findChildrenByClassifyInfo(it,classifyInfoPOList));
            }
        }
        return classifyInfoPO;
    }


    /**
     * 使用递归方法建树 
     * @param DeptRess
     * @return
     */
    public static List<DeptRes> buildByRecursive(List<DeptRes> DeptRess) {
        List<DeptRes> trees = new ArrayList<>();
        for (DeptRes deptRes : DeptRess) {
            if (0 == deptRes.getParentId()) {
                trees.add(findChildren(deptRes,DeptRess));
            }
        }
        return trees;
    }


    /**
     * 通过部门id使用递归方法建树
     * @param DeptRess
     * @return
     */
    public static List<DeptRes> buildByRecursive(List<DeptRes> DeptRess,Integer id) {
        List<DeptRes> trees = new ArrayList<>();
        for (DeptRes deptRes : DeptRess) {
            if(id != null && id.compareTo(0) > 0){
                if(!deptRes.getId().equals(id)){
                    continue;
                }
            }
            if (0 == deptRes.getParentId()) {
                trees.add(findChildren(deptRes,DeptRess));
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点 
     * @param DeptRess
     * @return
     */
    public static DeptRes findChildren(DeptRes deptRes,List<DeptRes> DeptRess) {
        deptRes.setChildren(new ArrayList<>());
        for (DeptRes it : DeptRess) {
            if(deptRes.getId().equals(it.getParentId())) {
                if (deptRes.getChildren() == null) {
                    deptRes.setChildren(new ArrayList<>());
                }
                deptRes.getChildren().add(findChildren(it,DeptRess));
            }
        }
        return deptRes;
    }

    public static int doCount(DeptRes root, Integer count){
        if(root.getNumber() != null){
            count += root.getNumber();
        }
        if(root != null && root.getChildren() != null){
            for (int i = 0,size = root.getChildren().size(); i < size; i++) {
                count = doCount(root.getChildren().get(i),count);
            }
        }
        return count;
    }

    public static List getChildIds(DeptRes root, List list){
        list.add(root.getId());
        if(root != null && root.getChildren() != null){
            for (int i = 0,size = root.getChildren().size(); i < size; i++) {
                list = getChildIds(root.getChildren().get(i),list);
            }
        }
        return list;
    }


    public static List getClassifyChildIds(ClassifyListRes root, List list){
        list.add(root.getId());
        if(root != null && root.getChildren() != null){
            for (int i = 0,size = root.getChildren().size(); i < size; i++) {
                list = getClassifyChildIds(root.getChildren().get(i),list);
            }
        }
        return list;
    }

    public static List getClassifyPersonCountChildIds(HomePageClassifyPersonRes root, List list){
        list.add(root.getId());
        if(root != null && root.getChildren() != null){
            for (int i = 0,size = root.getChildren().size(); i < size; i++) {
                list = getClassifyPersonCountChildIds(root.getChildren().get(i),list);
            }
        }
        return list;
    }

    public static List getChildIds(ClassifyInfoPO root, List list){
        list.add(root.getId());
        if(root != null && root.getChildren() != null){
            for (int i = 0,size = root.getChildren().size(); i < size; i++) {
                list = getChildIds(root.getChildren().get(i),list);
            }
        }
        return list;
    }

}