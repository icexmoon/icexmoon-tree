package cn.icexmoon.tree;

import cn.icexmoon.tree.iterator.BreadthFirstChildrenIterator;
import cn.icexmoon.tree.iterator.DepthFirstChildrenIterator;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
 * @Description : 节点
 */
@Getter
@Setter
public class Node<V> implements Iterable<Node<V>>{
    public Node(V value) {
        this.value = value;
    }

    private V value;
    private Node<V> parent;
    private List<Node<V>> children = new ArrayList<>();
    // 默认使用深度优先遍历
    private TraversalType traversalType = TraversalType.DEEP_FIRST;

    /**
     * 是否叶子节点
     * @return 是否叶子节点
     */
    public boolean isLeaf(){
        if (children == null || children.isEmpty()){
            return true;
        }
        return false;
    }

    @Override
    public Iterator<Node<V>> iterator() {
        if (traversalType == TraversalType.BREADTH_FIRST){
            return new BreadthFirstChildrenIterator<>(this);
        }
        // 默认使用深度优先迭代器
        return new DepthFirstChildrenIterator<>(this);
    }
}
