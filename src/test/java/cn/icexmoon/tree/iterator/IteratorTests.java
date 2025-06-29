package cn.icexmoon.tree.iterator;

import cn.icexmoon.tree.Department;
import cn.icexmoon.tree.Node;
import cn.icexmoon.tree.TestData;
import cn.icexmoon.tree.Tree;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

/**
 * @ClassName DepthFirstChildrenIteratorTests
 * @Description 迭代器相关测试用例
 * @Author icexmoon@qq.com
 * @Date 2025/6/29 上午11:18
 * @Version 1.0
 */
public class IteratorTests {
    @Test
    public void testDepthFirstIterator() {
        Tree<Department> tree = TestData.tree;
        DepthFirstChildrenIterator<Department> iterator = new DepthFirstChildrenIterator<>(tree.getRoot());
        traversal(iterator);
    }

    @Test
    public void testDepthFirstIterator2() {
        Tree<Department> tree = TestData.tree;
        DepthFirstChildrenIterator<Department> iterator = new DepthFirstChildrenIterator<>(tree.getRoot(), false);
        traversal(iterator);
    }

    private static void traversal(Iterator<Node<Department>> iterator) {
        while (iterator.hasNext()){
            Node<Department> node = iterator.next();
            System.out.println(node.getValue());
        }
    }

    @Test
    public void testBreadthFirstIterator() {
        BreadthFirstChildrenIterator<Department> iterator = new BreadthFirstChildrenIterator(TestData.tree.getRoot());
        traversal(iterator);
    }

    @Test
    public void testBreadthFirstIterator2() {
        BreadthFirstChildrenIterator<Department> iterator = new BreadthFirstChildrenIterator(TestData.tree.getRoot(), false);
        traversal(iterator);
    }

    @Test
    public void testChildFirstIterator() {
        Node<Department> childNode = TestData.tree.findNode(node -> node.getValue().getId() == 6);
        ChildFirstParentsIterator<Department> iterator = new ChildFirstParentsIterator<>(childNode);
        traversal(iterator);
    }

    @Test
    public void testChildFirstIterator2() {
        Node<Department> childNode = TestData.tree.findNode(node -> node.getValue().getId() == 6);
        ChildFirstParentsIterator<Department> iterator = new ChildFirstParentsIterator<>(childNode, false);
        traversal(iterator);
    }


    @Test
    public void testParentFirstIterator() {
        Node<Department> childNode = TestData.tree.findNode(node -> node.getValue().getId() == 6);
        ParentFirstParentsIterator<Department> iterator = new ParentFirstParentsIterator<>(childNode);
        traversal(iterator);
    }

    @Test
    public void testParentFirstIterator2() {
        Node<Department> childNode = TestData.tree.findNode(node -> node.getValue().getId() == 6);
        ParentFirstParentsIterator<Department> iterator = new ParentFirstParentsIterator<>(childNode, false);
        traversal(iterator);
    }
}
