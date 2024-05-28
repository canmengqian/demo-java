package design.model.facede.demo1;

import cn.hutool.core.collection.CollUtil;

import java.util.List;

public class Chef {
    List<String> cooking(){
        return CollUtil.newArrayList ("小炒黄牛肉","葱烧海参","佛跳墙");
    }
}
