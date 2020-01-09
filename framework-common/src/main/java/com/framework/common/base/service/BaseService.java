package com.framework.common.base.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 基础Service接口，其它Service接口需要继承此接口
 *
 * @param <T>
 * @param <ID>
 */
public interface BaseService<T, ID extends Serializable> {
    /**
     * 根据ID获取指定类型T的对象实体
     *
     * @param id
     * @return
     */
    T get(ID id);
    /**
     * 根据ID获取指定类型T的对象实体,允许查询不到数据
     *
     * @param id
     * @return
     */
    T findById(ID id);

    /**
     * 根据ID获取指定类型T的对象实体
     *
     * @param id
     * @return
     */
    T load(ID id);

    /**
     * 获取指定类型T的所有对象实体
     *
     * @return
     */
    List<T> getAllList();

    /**
     * 获取指定类型T的所有对象实体（按倒序排序）
     *
     * @return
     */
    List<T> getAllListDesc();

    /**
     * 获取指定类型T的所有对象实体的数量
     *
     * @return
     */
    Long getTotalCount();

    /**
     * 保存指定类型T的对象实体
     *
     * @param entity
     * @return
     */
    T save(T entity);

    /**
     * 更新指定类型T的对象实体
     *
     * @param
     */
    void update(T entity);

    /**
     * 删除指定类型T的对象实体
     *
     * @param entity
     * @return
     */
    void delete(T entity);

    /**
     * 根据ID删除对象实体
     *
     * @param
     */
    void delete(ID id);

    /**
     * 根据ID数组删除实体对象
     *
     * @param ids
     */
    void delete(ID[] ids);

    /**
     * 刷新session
     */
    void flush();

    /**
     * 清除对象
     *
     * @param object
     */
    void evict(Object object);

    /**
     * 清除Session
     */
    void clear();
    /**
     * 按指定返回分页
     *
     * @param pageable
     * @return
     */
    Page<T> getPage(Pageable pageable);
    /**
     * 按指定类型T的分页（按倒序排序）
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    Page<T> getPageDesc(int pageNo, int pageSize);

    /**
     * 通过jpql获取指定类型T的分页
     *
     * @param jpql
     * @param paramList
     * @param pageable
     * @return
     */
    Page<T> getPageByJpql(String jpql, List paramList, Pageable pageable);

    /**
     * 通过jpql获取指定类型T的分页
     *
     * @param countJpql
     * @param jpql
     * @param paramList
     * @param pageable
     * @return
     */
    Page<T> getPageByJpql(String countJpql, String jpql, List paramList, Pageable pageable);

    /**
     * 通过jpql获取指定类型T的List
     *
     * @param jpql
     * @param paramList
     * @return
     */
    List<T> queryByJpql(String jpql, List paramList);

    /**
     * 保存指定类型T的对象实体List
     *
     * @param entitys
     */
    void save(List<T> entitys);

    /**
     * 更新指定类型T的对象实体List
     *
     * @param entitys
     */
    void update(List<T> entitys);

    /**
     * 根据原生sql更新数据，返回更新条数
     *
     * @param sql
     * @return
     */
    int excuteUpdateBySql(String sql);

    /**
     * 根据原生sql更新数据，返回更新条数
     *
     * @param sql
     * @param paramList
     * @return
     */
    int excuteUpdateBySql(String sql, List paramList);

    /**
     * 根据原生sql查询数据
     *
     * @param sql
     * @return List
     */
    List excuteFindBySql(String sql);

    /**
     * 根据原生sql查询数据
     *
     * @param sql
     * @param paramList
     * @return List
     */
    List excuteFindBySql(String sql, List paramList);
}