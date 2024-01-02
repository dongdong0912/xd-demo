package com.example.xddemo.demo;

import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.select.OrderByElement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectExpressionItem;

import java.util.List;

/**
 * Author: xuedong
 * Date: 2024/1/2
 */
public class SqlDemo {

    public static void main(String[] args) throws Exception {
        String sql = "SELECT id, name , sum(age) age FROM users WHERE age > 18 group by id , name ORDER BY name ASC , age DESC";
        Select select = (Select) CCJSqlParserUtil.parse(sql);
        PlainSelect selectBody = (PlainSelect) select.getSelectBody();
        List<OrderByElement> orderElements = selectBody.getOrderByElements();
        System.out.println(orderElements);
        // 移除排序部分
        selectBody.setOrderByElements(null);
        selectBody.setSelectItems(null);


        //  移除分组部分
        /*plainSelect.setGroupByElement(null);*/



        // 创建 COUNT(*) 函数表达式
        Function countFunction = new Function();
        countFunction.setName("COUNT");
        countFunction.setAllColumns(true);

        // 创建 SelectExpressionItem，并将 COUNT(*) 函数表达式设置为其表达式
        SelectExpressionItem countItem = new SelectExpressionItem(countFunction);


        selectBody.addSelectItems(countItem);


        System.out.println(select);
    }
}
