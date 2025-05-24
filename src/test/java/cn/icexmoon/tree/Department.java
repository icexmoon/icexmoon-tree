package cn.icexmoon.tree;

import cn.icexmoon.tree.inter.Nodeable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : icexmoon-tree
 * @Package : cn.icexmoon.tree
 * @ClassName : .java
 * @createTime : 2025/5/24 下午1:24
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Department implements Nodeable<Department> {
    @EqualsAndHashCode.Include
    private Long id;
    private String name;
    private Long parentId;
    private Department parent;
    private List<Department> children;

    public Department(Long id, String name, Long parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }
}
