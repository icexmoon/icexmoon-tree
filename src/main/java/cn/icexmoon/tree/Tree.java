package cn.icexmoon.tree;

import cn.icexmoon.tree.inter.GetDirectChildren;
import cn.icexmoon.tree.inter.GetRoot;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : icexmoon-tree
 * @Package : cn.icexmoon
 * @ClassName : .java
 * @createTime : 2025/5/24 上午11:52
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description : 树
 */
public class Tree<V> {
    /**
     * 负责初始化树的内部类
     *
     * @param <V> 节点中包含的 value 的类型
     */
    private static class TreeGenerator<V> {
        private final GetDirectChildren<V> getDirectChildren;
        private final GetRoot<V> getRoot;

        private TreeGenerator(GetDirectChildren<V> getDirectChildren, GetRoot<V> getRoot) {
            this.getDirectChildren = getDirectChildren;
            this.getRoot = getRoot;
        }

        public Node<V> generate() {
            V rootValue = getRoot.get();
            if (rootValue == null) {
                throw new RuntimeException("根节点的 value 不能为 null");
            }
            Node<V> rootNode = new Node<>(rootValue);
            buildTree(rootNode);
            return rootNode;
        }

        private void buildTree(@NonNull Node<V> currentNode) {
            V currentValue = currentNode.getValue();
            // 获取当前 value 的子 value
            List<V> values = getDirectChildren.get(currentValue);
            if (values == null || values.isEmpty()) {
                // 没有子 value，退出树建构递归
                return;
            }
            // 创建子节点并加入
            for (V value : values) {
                Node<V> childNode = new Node<>(value);
                currentNode.getChildren().add(childNode);
                childNode.setParent(currentNode);
                // 递归调用，构建子树
                buildTree(childNode);
            }
        }
    }

    public enum TraversalType {
        // 深度优先
        DEEP_FIRST,
        // 广度优先
        BREADTH_FIRST,
    }

    private final TreeGenerator<V> treeGenerator;
    private Node<V> root;

    public Tree(GetDirectChildren<V> getDirectChildren,
                GetRoot<V> getRoot) {
        treeGenerator = new TreeGenerator<>(getDirectChildren, getRoot);
    }

    /**
     * 获取树的根节点，如果不存在树结构，将自动构建
     *
     * @return 根节点
     */
    public Node<V> getRoot() {
        if (root == null) {
            root = treeGenerator.generate();
        }
        return root;
    }

    /**
     * 遍历树，并对每个节点执行匿名函数(深度优先遍历)
     *
     * @param consumer 需要执行的匿名函数
     */
    public void traversal(Consumer<Node<V>> consumer) {
        traversal(consumer, TraversalType.DEEP_FIRST);
    }

    /**
     * 遍历树
     *
     * @param consumer      遍历时执行的匿名函数
     * @param traversalType 遍历方式，支持深度优先或广度优先
     */
    public void traversal(Consumer<Node<V>> consumer, TraversalType traversalType) {
        Node<V> root = getRoot();
        consumer.accept(root);
        switch (traversalType) {
            case DEEP_FIRST:
                traversalWithDeepFirst(consumer, root, false);
                break;
            case BREADTH_FIRST:
                traversalWithBreathFirst(consumer, root);
                break;
            default:
                throw new RuntimeException("不支持的遍历方式");
        }
    }

    /**
     * 深度优先遍历
     *
     * @param consumer     遍历节点时执行的匿名函数
     * @param currentNode  当前遍历的节点
     * @param execConsumer 是否对节点执行匿名函数
     */
    private void traversalWithDeepFirst(Consumer<Node<V>> consumer, Node<V> currentNode, boolean execConsumer) {
        if (execConsumer) {
            consumer.accept(currentNode);
        }
        for (Node<V> child : currentNode.getChildren()) {
            traversalWithDeepFirst(consumer, child, true);
        }
    }

    /**
     * 广度优先遍历
     *
     * @param consumer    遍历时执行的匿名函数
     * @param currentNode 当前节点
     */
    private void traversalWithBreathFirst(Consumer<Node<V>> consumer, Node<V> currentNode) {
        List<Node<V>> children = currentNode.getChildren();
        for (Node<V> child : children) {
            consumer.accept(child);
        }
        for (Node<V> child : children) {
            traversalWithBreathFirst(consumer, child);
        }
    }


    /**
     * 返回指定节点的所有父节点(包含自己)
     *
     * @param node 指定节点
     * @return 父节点集合
     */
    public List<Node<V>> getAllParents(@NonNull Node<V> node) {
        Stack<Node<V>> stack = new Stack<>();
        Node<V> currentNode = node;
        do {
            stack.push(currentNode);
            currentNode = currentNode.getParent();
        }
        while (currentNode != null);
        List<Node<V>> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }

    /**
     * 销毁树，如果外部树的结构发生变化，需要调用此方法销毁树，以便再次使用树时重新构建
     */
    public void destroy() {
        this.root = null;
    }

    /**
     * 按条件查找节点，返回第一个符合条件的节点
     *
     * @param predicate 匿名函数
     * @return 第一个符合条件的节点，没有返回null
     */
    public Node<V> findNode(Predicate<Node<V>> predicate) {
        Node<V> root = getRoot();
        return findNodeCircle(root, predicate);
    }

    /**
     * 递归查找匹配的节点
     *
     * @param currentNode 当前节点
     * @param predicate   匹配条件
     * @return 匹配到的节点
     */
    private Node<V> findNodeCircle(@NonNull Node<V> currentNode, Predicate<Node<V>> predicate) {
        if (predicate.test(currentNode)) {
            return currentNode;
        }
        for (Node<V> child : currentNode.getChildren()) {
            Node<V> findNode = findNodeCircle(child, predicate);
            if (findNode != null) {
                return findNode;
            }
        }
        return null;
    }
}
