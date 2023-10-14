package common.apache.commons.collections;

import org.apache.commons.collections4.CollectionUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CollectionsUtils {
    public static void main(String[] args) {

        //null 元素不能加进去
        List<String> arrayList1 = new ArrayList<>();
        arrayList1.add("a");
        CollectionUtils.addIgnoreNull(arrayList1, null);
        System.out.println(arrayList1.size());

        //排好序的集合，合并后还是排序的
        List<String> arrayList2 = new ArrayList<>();
        arrayList2.add("a");
        arrayList2.add("b");

        List<String> arrayList3 = new ArrayList<>();
        arrayList3.add("c");
        arrayList3.add("d");
        System.out.println("arrayList3：" + arrayList3);

        List<String> arrayList4 = CollectionUtils.collate(arrayList2, arrayList3);
        System.out.println("arrayList4:" + arrayList4);

        //交集
        Collection<String> strings = CollectionUtils.retainAll(arrayList4, arrayList3);
        System.out.println("arrayList3和arrayList4的交集：" + strings);

        //并集
        Collection<String> union = CollectionUtils.union(arrayList4, arrayList3);
        System.out.println("arrayList3和arrayList4的并集：" + union);

        //差集
        Collection<String> subtract = CollectionUtils.subtract(arrayList4, arrayList3);
        System.out.println("arrayList3和arrayList4的差集：" + subtract);

        // 过滤，只保留 b
        CollectionUtils.filter(arrayList4, s -> s.equals("b"));
        System.out.println(arrayList4);
    }

}
