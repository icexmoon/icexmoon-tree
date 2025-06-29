package cn.icexmoon.tree.iterator;

import cn.icexmoon.tree.Node;

import java.util.Iterator;
import java.util.Stack;

/**
 * @ClassName ParentFirstParentsIterator
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/6/29 上午11:55
 * @Version 1.0
 */
public class ParentFirstParentsIterator<V> implements Iterator<Node<V>> {
    private final Stack<Node<V>> stack = new Stack<>();

    public ParentFirstParentsIterator(Node<V> currentNode) {
        this(currentNode, true);
    }

    /**
     * @param currentNode   要遍历的起始节点
     * @param traversalSelf 是否遍历起始节点自己
     */
    public ParentFirstParentsIterator(Node<V> currentNode, boolean traversalSelf) {
        Node<V> node = null;
        if (traversalSelf) {
            node = currentNode;
        } else {
            if (currentNode != null) {
                node = currentNode.getParent();
            }
        }
        while (node != null) {
            stack.push(node);
            node = node.getParent();
        }
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public Node<V> next() {
        return stack.pop();
    }
}
