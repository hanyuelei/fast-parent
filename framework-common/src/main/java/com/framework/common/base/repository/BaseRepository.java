package com.framework.common.base.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 基础Repository接口，其它自定义Repository接口不要（没写错字）继承此接口
 * spring boot data jpa有自己规定好的自定义非基础Repository的方式
 *
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
	
	public <S extends T> S save(S entity);
    /**
     * 更新指定类型T的对象实体
     *
     * @param entity
     */
    void update(T entity);

    /**
     * 根据ID数组删除实体对象
     *
     * @param ids
     */
    void delete(ID[] ids);

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
     * 通过jpql和查询条件获取数量
     *
     * @param jpql
     * @param paramList
     * @return
     */
    Long getTotalCountByJpql(String jpql, List paramList);

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
     * @return int
     */
    int excuteUpdateBySql(String sql, List paramList);

    /**
     * 根据原生sql查询数据
     *
     * @param sql
     * @return
     */
    List excuteFindBySql(String sql);

    /**
     * 根据原生sql查询数据
     *
     * @param sql
     * @param paramList
     * @return
     */
    List excuteFindBySql(String sql, List paramList);

}
