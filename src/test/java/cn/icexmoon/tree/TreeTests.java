package cn.icexmoon.tree;

import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : icexmoon-tree
 * @Package : cn.icexmoon.tree
 * @ClassName : .java
 * @createTime : 2025/5/24 下午1:19
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */
public class TreeTests {
    // 准备测试数据
    private final List<Department> departments = TestData.departments;
    // 声明树
    private final Tree<Department> tree = TestData.tree;

    @Test
    public void testTraversal() {
        // 遍历树
        tree.traversal(node -> {
            List<Node<Department>> allParents = tree.getAllParents(node);
            List<String> names = allParents.stream().map(n -> n.getValue().getName()).toList();
            String fullNames = String.join("/", names);
            System.out.println(fullNames);
        });
    }

    @Test
    public void testFindNode() {
        Node<Department> findNode = tree.findNode(node -> node.getChildren() != null && node.getValue().getId() == 5);
        System.out.println(findNode.getValue());
    }

    @Test
    public void testDestroy(){
        tree.destroy();
    }

    @Test
    public void testIterator(){
        for (Node<Department> departmentNode : tree.getRoot()) {
            System.out.println(departmentNode.getValue());
        }
    }

    @Test
    public void testIterator2(){
        tree.getRoot().setTraversalType(TraversalType.BREADTH_FIRST);
        for (Node<Department> departmentNode : tree.getRoot()) {
            System.out.println(departmentNode.getValue());
        }
    }
}
