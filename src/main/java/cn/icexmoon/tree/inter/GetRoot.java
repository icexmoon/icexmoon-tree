package cn.icexmoon.tree.inter;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : icexmoon-tree
 * @Package : cn.icexmoon.tree.functions
 * @ClassName : .java
 * @createTime : 2025/5/24 下午12:12
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description : 获取根 value 的匿名函数
 */
@FunctionalInterface
public interface GetRoot<V> {
    public V get();
}
