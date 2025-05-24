package cn.icexmoon.tree.inter;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : icexmoon-tree
 * @Package : cn.icexmoon
 * @ClassName : .java
 * @createTime : 2025/5/24 下午12:06
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */
@FunctionalInterface
public interface GetDirectChildren<V> {
    /**
     * 用于获取直接子节点的 value
     * @param value 指定 value
     * @return 指定 value 的子节点的 value 集合
     */
    public List<V> get(V value);
}
