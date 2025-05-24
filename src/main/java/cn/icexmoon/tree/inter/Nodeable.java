package cn.icexmoon.tree.inter;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : icexmoon-tree
 * @Package : cn.icexmoon.tree.inter
 * @ClassName : .java
 * @createTime : 2025/5/24 下午2:09
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description : 可以节点化的接口
 */
public interface Nodeable<V> {
    void setParent(V parent);

    void setChildren(List<V> children);

    V getParent();

    List<V> getChildren();
}
