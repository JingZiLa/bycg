package com.bycg.item.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 首页配置信息实体类
 * @className: IndexConfig
 * @Author: Mirror
 * @CreateDate: 2020/5/9 11:25
 **/
@Data
public class IndexConfig {

	/**
     * 首页配置项主键id
     */
    private Long configId;

    /**
     * 显示字符(配置搜索时不可为空，其他可为空)
     */
    private String configName;

    /**
     * 1-搜索框热搜 2-搜索下拉框热搜 3-(首页)热销商品 4-(首页)新品上线 5-(首页)为你推荐
     */
    private Byte configType;

    /**
     * 商品id 默认为0
     */
    private Long goodsId;

    /**
     * 点击后的跳转地址(默认不跳转)
     */
    private String redirectUrl;

    /**
     * 排序值(字段越大越靠前)
     */
    private Integer configRank;

    /**
     * 删除标识字段(0-未删除 1-已删除)
     */
    private Boolean isDeleted;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建者id
     */
    private Integer createUser;

    /**
     * 最新修改时间
     */
    private Date updateTime;

    /**
     * 修改者id
     */
    private Integer updateUser;

}
