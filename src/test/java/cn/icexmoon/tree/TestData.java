package cn.icexmoon.tree;

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
 * @createTime : 2025/5/24 下午2:38
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */
public class TestData {
    public static final List<Department> departments = List.of(
            new Department(1L, "宇宙科技有限公司", null),
            new Department(2L, "人事部", 1L),
            new Department(3L, "系统开发部", 1L),
            new Department(4L, "开发一部", 3L),
            new Department(5L, "开发二部", 3L),
            new Department(6L, "OA项目组", 4L));
    // 声明树
    public static final Tree<Department> tree = new Tree<>(dept -> {
        Long id = dept.getId();
        List<Department> children = new ArrayList<>();
        for (Department department : departments) {
            if (department.getParentId() != null && department.getParentId().equals(id)) {
                children.add(department);
            }
        }
        return children;
    }, departments::getFirst);
}
