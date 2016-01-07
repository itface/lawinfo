package com.lawinfo.log.util;

import com.lawinfo.log.LoggerInfo;
import org.apache.commons.lang.StringUtils;

import java.util.*;


public class Tree {

    public final static String TREE_ROOT = "TREE_ROOT";

    public static final String ALL_LOG = "ALL";

    Map<String, Node> nodeMap = new HashMap<String, Node>();

    private Node treeRoot;


    public Set<Node> getLevels(Node node) {
        Set<Node> set = new TreeSet<Node>();
        for (Node node1 : node.getChildList()) {
            set.addAll(getLevels(node1));
            set.add(node1);
        }
        return set;
    }

    public Set<Node> getLevels(String category) {
        if (ALL_LOG.equals(category)) {
            return getLevels(treeRoot);
        } else {
            if (StringUtils.isEmpty(category)) {
                category = TREE_ROOT;
            }
            Node node = nodeMap.get(category);
            return node == null || node.getChildList() == null ? new TreeSet<Node>() : node.getChildList();
        }
    }

    public synchronized void addLoggerInfo(LoggerInfo loggerInfo) {
        if (treeRoot == null) {
            treeRoot = new Node(TREE_ROOT);
            nodeMap.put(TREE_ROOT, treeRoot);
        }
        treeRoot.addLoggerInfo(loggerInfo, this);
    }

    public void setLogLevel(LoggerInfo info) {
        Node node = nodeMap.get(info.getName());
        node.setLoggerInfo(info);
    }
}
