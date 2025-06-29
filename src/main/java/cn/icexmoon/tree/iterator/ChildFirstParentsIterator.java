package cn.icexmoon.tree.iterator;

import cn.icexmoon.tree.Node;

import java.util.Iterator;

/**
 * @ClassName ParentsIterator
 * @Description 迭代器，遍历当前节点和其父节点（先遍历子节点）
 * @Author icexmoon@qq.com
 * @Date 2025/6/29 上午11:48
 * @Version 1.0
 */
public class ChildFirstParentsIterator<V> implements Iterator<Node<V>> {
    private Node<V> currentNode;

    public ChildFirstParentsIterator(Node<V> currentNode) {
        this(currentNode, true);
    }

    /**
     *
     * @param currentNode 要遍历的起始节点
     * @param traversalSelf 是否遍历起始节点自己
     */
    public ChildFirstParentsIterator(Node<V> currentNode, boolean traversalSelf) {
        if (traversalSelf) {
            this.currentNode = currentNode;
        }
        else{
            if (currentNode == null) {
                this.currentNode = null;
                return;
            }
            this.currentNode = currentNode.getParent();
        }
    }

    @Override
    public boolean hasNext() {
        return currentNode != null;
    }

    @Override
    public Node<V> next() {
        Node<V> node = currentNode;
        currentNode = currentNode.getParent();
        return node;
    }
}
