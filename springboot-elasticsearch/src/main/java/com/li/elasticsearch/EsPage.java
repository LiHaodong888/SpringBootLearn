package com.li.elasticsearch;/**
 * @author lihaodong
 * @create 2018-12-20 09:30
 * @desc
 **/

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * @ClassName EsPage
 * @Author lihaodong
 * @Date 2018/12/20 09:30
 * @Mail lihaodongmail@163.com
 * @Description
 * @Version 1.0
 **/
@Getter
@Setter
public class EsPage {
    /**
     * 当前页
     */
    private int currentPage;
    /**
     * 每页显示多少条
     */
    private int pageSize;
    /**
     * 总记录数
     */
    private int recordCount;
    /**
     * 本页的数据列表
     */
    private List<Map<String, Object>> recordList;
    /**
     * 总页数
     */
    private int pageCount;
    /**
     * 页码列表的开始索引（包含）
     */
    private int beginPageIndex;
    /**
     * 页码列表的结束索引（包含）
     */
    private int endPageIndex;

    /**
     * 只接受前4个必要的属性，会自动的计算出其他3个属性的值
     * @param currentPage
     * @param pageSize
     * @param recordCount
     * @param recordList
     */
    public EsPage(int currentPage, int pageSize, int recordCount, List<Map<String, Object>> recordList) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.recordCount = recordCount;
        this.recordList = recordList;
        // 计算总页码
        pageCount = (recordCount + pageSize - 1) / pageSize;
        // 计算 beginPageIndex 和 endPageIndex
        // >> 总页数不多于10页，则全部显示
        if (pageCount <= 10) {
            beginPageIndex = 1;
            endPageIndex = pageCount;
        } // >> 总页数多于10页，则显示当前页附近的共10个页码
        else {
            // 当前页附近的共10个页码（前4个 + 当前页 + 后5个）
            beginPageIndex = currentPage - 4;
            endPageIndex = currentPage + 5;
            // 当前面的页码不足4个时，则显示前10个页码
            if (beginPageIndex < 1) {
                beginPageIndex = 1;
                endPageIndex = 10;
            }
            if (endPageIndex > pageCount) {
                endPageIndex = pageCount;
                beginPageIndex = pageCount - 10 + 1;
            }
        }
    }

}
