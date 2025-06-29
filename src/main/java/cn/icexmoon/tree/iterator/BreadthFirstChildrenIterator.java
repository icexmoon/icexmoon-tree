package cn.icexmoon.tree.iterator;

import cn.icexmoon.tree.Node;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * @ClassName ChildrenIterator
 * @Description 遍历器，对节点和子节点进行遍历（广度优先遍历）
 * @Author icexmoon@qq.com
 * @Date 2025/6/29 上午11:26
 * @Version 1.0
 */
public class BreadthFirstChildrenIterator<V> implements Iterator<Node<V>> {
    // 带遍历的节点
    private final Stack<Node<V>> preTraversalNodes = new Stack<>();
    // 自身已经遍历过，但子节点还没有遍历过的节点
    private final Stack<Node<V>> preChildrenTraversalNode = new Stack<>();

    /**
     * 构造器
     *
     * @param root          用于遍历的根节点
     * @param traversalSelf 是否遍历根节点自己
     */
    public BreadthFirstChildrenIterator(Node<V> root, boolean traversalSelf) {
        if (root == null) {
            // 根节点为 null，不进行遍历
            return;
        }
        if (!traversalSelf) {
            //不遍历根节点，直接将根节点的子节点加入待遍历堆栈
            addChildren2PreTraversalNodes(root);
        } else {
            preTraversalNodes.push(root);
        }
    }

    public BreadthFirstChildrenIterator(Node<V> root) {
        this(root, true);
    }

    @Override
    public boolean hasNext() {
        return !preTraversalNodes.isEmpty();
    }

    @Override
    public Node<V> next() {
        Node<V> node = preTraversalNodes.pop();
        if (node == null) {
            throw new RuntimeException("堆栈中不应该存在 null");
        }
        // 如果当前节点不是叶子节点，放入历史堆栈
        if (!node.isLeaf()) {
            preChildrenTraversalNode.push(node);
        }
        // 如果待遍历节点集合为空，从历史记录中获取新的待遍历节点
        if (preTraversalNodes.isEmpty() && !preChildrenTraversalNode.isEmpty()) {
            Node<V> parentNode = preChildrenTraversalNode.pop();
            // 添加历史节点的子节点作为待遍历节点
            addChildren2PreTraversalNodes(parentNode);
        }
        return node;
    }

    private void addChildren2PreTraversalNodes(Node<V> node) {
        if (!node.isLeaf()) {
            List<Node<V>> children = node.getChildren();
            for (int i = children.size() - 1; i >= 0; i--) {
                preTraversalNodes.push(children.get(i));
            }
        }
    }
}
