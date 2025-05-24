package cn.icexmoon.tree;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Node<V> {
    public Node(V value) {
        this.value = value;
    }

    @EqualsAndHashCode.Include
    private V value;
    private Node<V> parent;
    private List<Node<V>> children = new ArrayList<>();
}
