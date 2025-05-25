# icexmoon-tree
一个轻量级的 Java 工具库，提供树形结构操作功能。

# 安装

```xml
<dependency>
    <groupId>cn.icexmoon</groupId>
    <artifactId>icexmoon-tree</artifactId>
    <version>1.0.0</version>
</dependency>
```

# 使用

## 构建树

假设需要用树构建的数据类型如下：

```java
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
```

> 这里实现 Nodeable 接口并非必须，getter\setter\equals\hashcode 方法的实现也不是必须。

假设用 Department 构建了一些有树层级关系的测试数据：

```java
public static final List<Department> departments = List.of(
    new Department(1L, "宇宙科技有限公司", null),
    new Department(2L, "人事部", 1L),
    new Department(3L, "系统开发部", 1L),
    new Department(4L, "开发一部", 3L),
    new Department(5L, "开发二部", 3L),
    new Department(6L, "OA项目组", 4L));
```

这里 Department 实例的父子关系由 parentId 属性指定，当然这也不是必须的，树构建时关联关系依赖于用户提供的匿名函数，只要用户将怎么关联父子信息通过匿名函数告诉树即可。

通过构造函数创建树实例：

```java
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
```

`Tree`的构造器需要提供两个参数，都是匿名函数：

- 匿名函数 [GetDirectChildren](https://github.com/icexmoon/icexmoon-tree/blob/main/src/main/java/cn/icexmoon/tree/inter/GetDirectChildren.java)：提供一个要包含在树节点内的值（Value），返回其所有的子值（Child Value）。
- 匿名函数 [GetRoot](https://github.com/icexmoon/icexmoon-tree/blob/main/src/main/java/cn/icexmoon/tree/inter/GetRoot.java)：返回根节点的值。

## 销毁树

树的节点信息是保存在内存中的（Tree 对象的 root 字段），因此如果树创建时依赖的数据的父子关系发生变化，比如 department 表中新增了一个子部门或者删除了一个子部门。需要调用 API 销毁内存中的树结构，以便再次访问树时能重新构建树的父子关系。如果不这么做就可能导致一些 BUG。

销毁树：

```java
tree.destroy();
```

## 遍历树

遍历树是一个常见操作，本项目支持通过一个匿名函数 Consumer 在遍历树时对节点执行一些操作：

```java
tree.traversal(node -> {
    List<Node<Department>> allParents = tree.getAllParents(node);
    List<String> names = allParents.stream().map(n -> n.getValue().getName()).toList();
    String fullNames = String.join("/", names);
    System.out.println(fullNames);
});
```

> - `getAllParents`方法会返回指定节点的所有父节点，具体可以参考源码。
> - 默认采用深度优先遍历，提供其他 API 可以指定广度遍历或深度遍历。

## 查找节点

有时候需要根据指定条件查找树上的某个节点，可以：

```java
Node<Department> findNode = tree.findNode(node -> node.getChildren() != null && node.getValue().getId() == 5);
System.out.println(findNode.getValue());
```

就像示例展示的，`findNode`方法接收一个 Predicate 类型的匿名函数作为匹配条件。

## 简单树拷贝

做前后端分离开发的时候，前端获取到的树不希望是类似：

```
{
	value: {
		id: 1,
		name: "宇宙科技有限公司",
		parentId: 0,
		...
	},
	children[
		{
			value: {
				id: 2,
				name: "人事部",
				parentId: 1,
				...
			},
			children:[...]
		}
	]
}
```

当然，直接使用是没有任何问题的，但如果前端一定要使用类似下面的“简单树”（不包含 Value 这一层）结构：

```
{
    id: 1,
    name: "宇宙科技有限公司",
    parentId: 0,
    ...
	children[
		{
            id: 2,
            name: "人事部",
            parentId: 1,
            ...
			children:[...]
		}
	]
}
```

提供一个 API 可以方便获取一个类似的简单树的拷贝：

```java
Department rootDept = TreeUtil.getSimpleTreeCopy(TestData.tree, Department.class, false);
String jsonString = JSON.toJSONString(rootDept);
System.out.println(jsonString);
```

> - 因为涉及对象拷贝，所以需要提供一个 Class 对象用于反射。
> - 第三个参数可以控制返回的简单树中是否关联父节点（parent字段），一般来说需要 JSON 后返回给前端的话必须是`false`，否则会导致 JSON 格式化工具循环遍历进而堆栈溢出。
> - 要操作的数据类型必须有 parent 和 children 属性以及对应的 Getter/Setter 方法，这里引入一个 [Nodeable 接口](https://github.com/icexmoon/icexmoon-tree/blob/main/src/main/java/cn/icexmoon/tree/inter/Nodeable.java)进行限制，也就是说只有实现了该接口才能使用上面的 API。

## 其它操作

其它相关的操作和 API 可以直接查看源码。

# 反馈&&建议

项目地址为 [icexmoon/icexmoon-tree](https://github.com/icexmoon/icexmoon-tree)，你可以从这里查看源码或提交建议。

The End.

