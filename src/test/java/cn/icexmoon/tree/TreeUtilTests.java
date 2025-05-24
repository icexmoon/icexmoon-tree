package cn.icexmoon.tree;

import com.alibaba.fastjson2.JSON;
import org.junit.jupiter.api.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : icexmoon-tree
 * @Package : cn.icexmoon.tree
 * @ClassName : .java
 * @createTime : 2025/5/24 下午2:37
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */
public class TreeUtilTests {
    @Test
    public void test() {
        Department rootDept = TreeUtil.getSimpleTreeCopy(TestData.tree, Department.class, false);
        String jsonString = JSON.toJSONString(rootDept);
        System.out.println(jsonString);
    }
}
