package top.jasonkayzk.ttmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.jasonkayzk.ttmall.mapper.TbItemMapper;
import top.jasonkayzk.ttmall.pojo.TbItem;
import top.jasonkayzk.ttmall.pojo.TbItemExample;

import java.util.List;

@Ignore
public class PageHelperTest {

    @Test
    public void testPageHelper() {
        //创建一个spring容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");

        //从spring容器中获得Mapper的代理对象
        TbItemMapper mapper = applicationContext.getBean(TbItemMapper.class);

        //执行查询，并分页
        TbItemExample example = new TbItemExample();

        //分页处理
        PageHelper.startPage(2, 5);
        List<TbItem> list = mapper.selectByExample(example);

        //取商品列表
        for (TbItem tbItem : list) {
            System.out.println(tbItem.getTitle());
        }

        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        System.out.println("商品总计: " + pageInfo.getTotal());
    }

}
