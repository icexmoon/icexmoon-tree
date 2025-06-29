package cn.icexmoon.tree.iterator;

import cn.icexmoon.tree.Node;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * @ClassName ChildrenIterator
 * @Description 对节点及其子节点遍历的迭代器（深度优先遍历）
 * @Author icexmoon@qq.com
 * @Date 2025/6/29 上午10:58
 * @Version 1.0
 */
public class DepthFirstChildrenIterator<V> implements Iterator<Node<V>> {
    private Stack<Node<V>> stack = new Stack<>();

    public DepthFirstChildrenIterator(Node<V> root) {
        this(root, true);
    }

    /**
     * @param root          遍历的根节点
     * @param traversalSelf 是否遍历根节点自己
     */
    public DepthFirstChildrenIterator(Node<V> root, boolean traversalSelf) {
        // 如果根节点为 null，不进行任何遍历
        if (root == null) {
            return;
        }
        if (traversalSelf) {
            stack.push(root);
        } else {
            // 不遍历根节点，直接将子节点加入堆栈
            addChildren2Stack(root);
        }
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public Node<V> next() {
        Node<V> node = stack.pop();
        if (node == null) {
            throw new RuntimeException("队列中不应该存在 null");
        }
        addChildren2Stack(node);
        return node;
    }

    private void addChildren2Stack(Node<V> node) {
        if (!node.isLeaf()) {
            List<Node<V>> children = node.getChildren();
            for (int i = children.size() - 1; i >= 0; i--) {
                Node<V> childNode = children.get(i);
                if (childNode != null) {
                    stack.push(childNode);
                }
            }
        }
    }
}
