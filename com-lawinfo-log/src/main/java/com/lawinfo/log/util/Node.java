package com.lawinfo.log.util;

import com.lawinfo.log.LoggerInfo;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * User: wangyulie
 * Date: 14-12-31
 * Time: 下午5:16
 * Email: wangyulie@jd.com
 */
public class Node  implements Comparable<Node>{

    private static final String TYPE_FOLDER = "folder";
    private static final String TYPE_ITEM = "item";

    @JsonIgnore
    private Node parent;

    private String name;

    @JsonIgnore
    private LoggerInfo loggerInfo;

    @JsonIgnore
    private Set<Node> childList = new TreeSet<Node>();

    public Node(LoggerInfo loggerInfo) {
        this.loggerInfo = loggerInfo;
        this.name = loggerInfo.getName();
    }

    public Node(String name) {
        this.name = name;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Set<Node> getChildList() {
        return childList;
    }

    public void setChildList(Set<Node> childList) {
        this.childList = childList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LoggerInfo getLoggerInfo() {
        return loggerInfo;
    }

    public void setLoggerInfo(LoggerInfo loggerInfo) {
        this.loggerInfo = loggerInfo;
    }

    public String getType() {
        if (childList == null || childList.isEmpty()) {
            return TYPE_ITEM;
        }
        return TYPE_FOLDER;
    }

    public String getLevel() {
        return loggerInfo == null ? "" : loggerInfo.getLevel();
    }

    public void addLoggerInfo(LoggerInfo loggerInfo, Tree tree) {
        boolean flag = false;
        for (Node node : childList) {
            if (contains(loggerInfo.getName(), node.getName())) {
                flag = true;
                node.addLoggerInfo(loggerInfo, tree);
            }
        }
        if (!flag) {
            Node node = new Node(loggerInfo);
            childList.add(node);
            tree.nodeMap.put(node.getName(), node);
        }
    }

    private static boolean contains(String str, String searchStr) {
        String[] strSplit = StringUtils.split(str, ".");
        String[] searchSplit = StringUtils.split(searchStr, ".");
        for (int i = 0; i < searchSplit.length; i++) {
            if (!searchSplit[i].equals(strSplit[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int compareTo(Node o) {
        return this.loggerInfo.compareTo(o.loggerInfo);
    }
}
