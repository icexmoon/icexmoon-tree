package cn.icexmoon.tree.inter;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : icexmoon-tree
 * @Package : cn.icexmoon
 * @ClassName : .java
 * @createTime : 2025/5/24 下午12:02
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description : 获取节点直接父亲的匿名函数
 */
@FunctionalInterface
public interface GetDirectParent<V> {
    /**
     * 给定一个节点包含的 value，返回其父节点的 value
     * 此匿名函数用于树结构的生成
     *
     * @param value 指定节点的 value
     * @return 对应的父节点的 value
     */
    V get(V value);
}
