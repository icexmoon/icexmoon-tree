package cn.icexmoon.tree;

import cn.hutool.core.bean.BeanUtil;
import cn.icexmoon.tree.inter.Nodeable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : icexmoon-tree
 * @Package : cn.icexmoon.tree
 * @ClassName : .java
 * @createTime : 2025/5/24 下午2:14
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */
public class TreeUtil {
    /**
     * 获取一个树的简单拷贝
     *
     * @param <V>        value 的类型
     * @param tree       树
     * @param linkParent 是否连接父节点（生成 JSON 串时候通常不能连接）
     * @return 简单拷贝的根节点
     */
    public static <V extends Nodeable<V>> V getSimpleTreeCopy(Tree<V> tree, Class<V> cls, boolean linkParent) {
        Node<V> root = tree.getRoot();
        V rootValue = BeanUtil.copyProperties(root.getValue(), cls, "parent", "children");
        // 遍历树
        traversal(root, rootValue, cls, linkParent);
        return rootValue;
    }

    private static <V extends Nodeable<V>> void traversal(Node<V> currentNode, V currentValue, Class<V> cls, boolean linkParent) {
        // 获取子节点
        List<Node<V>> children = currentNode.getChildren();
        if (children.isEmpty()) {
            return;
        }
        for (Node<V> child : children) {
            // 创建子节点 value 的拷贝
            V childValue = child.getValue();
            V childValueCopy = BeanUtil.copyProperties(childValue, cls, "parent", "children");
            // 将 value 拷贝按照节点关系进行连接
            if (currentValue.getChildren() == null){
                currentValue.setChildren(new ArrayList<>());
            }
            currentValue.getChildren().add(childValueCopy);
            if (linkParent){
                childValueCopy.setParent(currentValue);
            }
            traversal(child, childValueCopy, cls, linkParent);
        }
    }
}
