package cn.iot.card.framework.mybatis.core.query;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.iot.card.framework.common.util.collection.ArrayUtils;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.github.yulichang.query.MPJLambdaQueryWrapper;
import com.github.yulichang.toolkit.LambdaUtils;
import com.github.yulichang.toolkit.support.ColumnCache;
import com.github.yulichang.wrapper.MPJAbstractLambdaWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.github.yulichang.wrapper.interfaces.WrapperFunction;
import com.github.yulichang.wrapper.segments.SelectCache;
import com.github.yulichang.wrapper.segments.SelectNormal;
import lombok.Getter;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @Author wlq
 * Created on 2023/5/30
 */
public class MPJLambdaWrapperX<T> extends MPJLambdaWrapper<T> {
    public <X> MPJLambdaWrapperX<T> likeIfPresent(SFunction<X, ?> column, String val) {
        if (StringUtils.hasText(val)) {
            return (MPJLambdaWrapperX<T>) super.like(column, val);
        }
        return this;
    }

    public <X> MPJLambdaWrapperX<T> likeLeftIfPresent(SFunction<X, ?> column, String val) {
        if (StringUtils.hasText(val)) {
            return (MPJLambdaWrapperX<T>) super.likeLeft(column, val);
        }
        return this;
    }

    public <X> MPJLambdaWrapperX<T> likeRightIfPresent(SFunction<X, ?> column, String val) {
        if (StringUtils.hasText(val)) {
            return (MPJLambdaWrapperX<T>) super.likeRight(column, val);
        }
        return this;
    }
    public <X> MPJLambdaWrapperX<T> inIfPresent(SFunction<X, ?> column, Collection<?> values) {
        if (!CollectionUtils.isEmpty(values)) {
            return (MPJLambdaWrapperX<T>) super.in(column, values);
        }
        return this;
    }

    public  <X> MPJLambdaWrapperX<T> inIfPresent(SFunction<X, ?> column, Object... values) {
        if (!ArrayUtil.isEmpty(values)) {
            return (MPJLambdaWrapperX<T>) super.in(column, values);
        }
        return this;
    }

    public <X> MPJLambdaWrapperX<T> eqIfPresent(SFunction<X, ?> column, Object val) {
        if (ObjectUtil.isNotEmpty(val)) {
            return (MPJLambdaWrapperX<T>) super.eq(column, val);
        }
        return this;
    }


    public <X> MPJLambdaWrapperX<T> neIfPresent(SFunction<X, ?> column, Object val) {
        if (ObjectUtil.isNotEmpty(val)) {
            return (MPJLambdaWrapperX<T>) super.ne(column, val);
        }
        return this;
    }

    public <X> MPJLambdaWrapperX<T> gtIfPresent(SFunction<X, ?> column, Object val) {
        if (val != null) {
            return (MPJLambdaWrapperX<T>) super.gt(column, val);
        }
        return this;
    }

    public <X> MPJLambdaWrapperX<T> geIfPresent(SFunction<X, ?> column, Object val) {
        if (val != null) {
            return (MPJLambdaWrapperX<T>) super.ge(column, val);
        }
        return this;
    }

    public <X> MPJLambdaWrapperX<T> ltIfPresent(SFunction<X, ?> column, Object val) {
        if (val != null) {
            return (MPJLambdaWrapperX<T>) super.lt(column, val);
        }
        return this;
    }

    public <X> MPJLambdaWrapperX<T> leIfPresent(SFunction<X, ?> column, Object val) {
        if (val != null) {
            return (MPJLambdaWrapperX<T>) super.le(column, val);
        }
        return this;
    }

    public <X> MPJLambdaWrapperX<T> betweenIfPresent(SFunction<X, ?> column, Object val1, Object val2) {
        if (val1 != null && val2 != null) {
            return (MPJLambdaWrapperX<T>) super.between(column, val1, val2);
        }
        if (val1 != null) {
            return (MPJLambdaWrapperX<T>) ge(column, val1);
        }
        if (val2 != null) {
            return (MPJLambdaWrapperX<T>) le(column, val2);
        }
        return this;
    }

    public <X> MPJLambdaWrapperX<T> betweenIfPresent(SFunction<X, ?> column, Object[] values) {
        Object val1 = ArrayUtils.get(values, 0);
        Object val2 = ArrayUtils.get(values, 1);
        return betweenIfPresent(column, val1, val2);
    }

    // ========== 重写父类方法，方便链式调用 ==========

    @Override
    public <S, X> MPJLambdaWrapperX<T> selectAs(SFunction<S, ?> column, SFunction<X, ?> alias) {
        return (MPJLambdaWrapperX<T>) super.selectAs(column, alias);
    }

    @Override
    public <E, X> MPJLambdaWrapperX<T> selectAs(String index, SFunction<E, ?> column, SFunction<X, ?> alias) {
        return (MPJLambdaWrapperX<T>)super.selectAs(index, column, alias);
    }
//    @Override
//    public <E> MPJLambdaWrapperX<T> selectAs(String column, SFunction<E, ?> alias) {
//        return (MPJLambdaWrapperX<T>)super.selectAs(column, alias);
//    }

    @Override
    public <S> MPJLambdaWrapperX<T> selectAs(SFunction<S, ?> column, String alias) {
        return (MPJLambdaWrapperX<T>)super.selectAs(column, alias);
    }

    @Override
    public <E> MPJLambdaWrapperX<T> selectAs(String column, SFunction<E, ?> alias) {
        return (MPJLambdaWrapperX<T>)super.selectAs(column, alias);
    }

    @Getter
    private boolean hasAlias;

    public <E> MPJLambdaWrapperX<T> selectX(SFunction<E, ?>... columns) {
        if (com.baomidou.mybatisplus.core.toolkit.ArrayUtils.isNotEmpty(columns)) {
            Class<?> aClass = LambdaUtils.getEntityClass(columns[0]);
            Map<String, SelectCache> cacheMap = ColumnCache.getMapField(aClass);
            for (SFunction<E, ?> s : columns) {
                SelectCache cache = cacheMap.get(LambdaUtils.getName(s));
                getSelectColum().add(new SelectNormal(cache, index, hasAlias, alias));
            }
        }
        return (MPJLambdaWrapperX<T>) typedThis;
    }

    @Override
    public MPJLambdaWrapperX<T> selectAll(Class<?> clazz) {
        return (MPJLambdaWrapperX<T>)super.selectAll(clazz);
    }

    @Override
    public <X> MPJLambdaWrapperX<T> eq(boolean condition, SFunction<X, ?> column, Object val) {
        return (MPJLambdaWrapperX<T>)super.eq(condition, column, val);
    }
    @Override
    public <T1, X> MPJLambdaWrapperX<T> innerJoin(Class<T1> clazz, SFunction<T1, ?> left, SFunction<X, ?> right) {
        return (MPJLambdaWrapperX<T>) super.innerJoin(clazz, left, right);
    }

    @Override
    public <T1> MPJLambdaWrapperX<T> innerJoin(Class<T1> clazz, WrapperFunction<MPJAbstractLambdaWrapper<T, ?>> function) {
        return (MPJLambdaWrapperX<T>)super.innerJoin(clazz, function);
    }

    @Override
    public MPJLambdaWrapperX<T> innerJoin(String joinSql) {
        return (MPJLambdaWrapperX<T>) super.innerJoin(joinSql);
    }

    @Override
    public MPJLambdaWrapperX<T> and(Consumer<MPJLambdaWrapper<T>> consumer) {
        return (MPJLambdaWrapperX<T>)super.and(consumer);
    }

    @Override
    public MPJLambdaWrapperX<T> or(Consumer<MPJLambdaWrapper<T>> consumer) {
        return (MPJLambdaWrapperX<T>)super.or(consumer);
    }

    @Override
    public MPJLambdaWrapperX<T> eq(String column, Object val) {
        return (MPJLambdaWrapperX<T>)super.eq(column, val);
    }

    @Override
    public <R, S> MPJLambdaWrapperX<T> eq(SFunction<R, ?> column, SFunction<S, ?> val) {
        return (MPJLambdaWrapperX<T>)super.eq(column, val);
    }

    @Override
    public MPJLambdaWrapperX<T> ne(String column, Object val) {
        return (MPJLambdaWrapperX<T>)super.ne(column, val);
    }

    @Override
    public MPJLambdaWrapperX<T> gt(String column, Object val) {
        return (MPJLambdaWrapperX<T>)super.gt(column, val);
    }

    @Override
    public MPJLambdaWrapperX<T> ge(String column, Object val) {
        return (MPJLambdaWrapperX<T>)super.ge(column, val);
    }

    @Override
    public MPJLambdaWrapperX<T> lt(String column, Object val) {
        return (MPJLambdaWrapperX<T>)super.lt(column, val);
    }

    @Override
    public MPJLambdaWrapperX<T> le(String column, Object val) {
        return (MPJLambdaWrapperX<T>)super.le(column, val);
    }

    @Override
    public MPJLambdaWrapperX<T> between(String column, Object val1, Object val2) {
        return (MPJLambdaWrapperX<T>)super.between(column, val1, val2);
    }

    @Override
    public MPJLambdaWrapperX<T> notBetween(String column, Object val1, Object val2) {
        return (MPJLambdaWrapperX<T>)super.notBetween(column, val1, val2);
    }

    @Override
    public MPJLambdaWrapperX<T> like(String column, Object val) {
        return (MPJLambdaWrapperX<T>)super.like(column, val);
    }

    @Override
    public MPJLambdaWrapperX<T> notLike(String column, Object val) {
        return (MPJLambdaWrapperX<T>)super.notLike(column, val);
    }

    @Override
    public MPJLambdaWrapperX<T> likeLeft(String column, Object val) {
        return (MPJLambdaWrapperX<T>)super.likeLeft(column, val);
    }

    @Override
    public MPJLambdaWrapperX<T> likeRight(String column, Object val) {
        return (MPJLambdaWrapperX<T>)super.likeRight(column, val);
    }

    @Override
    public <X> MPJLambdaWrapperX<T> likeLeft(boolean condition, SFunction<X, ?> column, Object val) {
        return (MPJLambdaWrapperX<T>)super.likeLeft(condition, column, val);
    }

    @Override
    public <R> MPJLambdaWrapperX<T> in(SFunction<R, ?> column, Collection<?> coll) {
        return (MPJLambdaWrapperX<T>)super.in(column, coll);
    }

    @Override
    public <R> MPJLambdaWrapperX<T> groupBy(SFunction<R, ?> column) {
        return (MPJLambdaWrapperX<T>)super.groupBy(column);
    }

    @Override
    public <R> MPJLambdaWrapperX<T> orderByAsc(SFunction<R, ?> column) {
        return (MPJLambdaWrapperX<T>)super.orderByAsc(column);
    }

    @Override
    public <R> MPJLambdaWrapperX<T> orderByDesc(SFunction<R, ?> column) {
        return (MPJLambdaWrapperX<T>)super.orderByDesc(column);
    }

    @Override
    public MPJLambdaWrapperX<T> in(String column, Collection<?> coll) {
        return (MPJLambdaWrapperX<T>)super.in(column, coll);
    }

    @Override
    public MPJLambdaWrapperX<T> notIn(String column, Collection<?> coll) {
        return (MPJLambdaWrapperX<T>)super.notIn(column, coll);
    }

    @Override
    public MPJLambdaWrapperX<T> or() {
        return (MPJLambdaWrapperX<T>)super.or();
    }

    @Override
    public <T1, X> MPJLambdaWrapperX<T> leftJoin(Class<T1> clazz, SFunction<T1, ?> left, SFunction<X, ?> right) {
        return (MPJLambdaWrapperX<T>)super.leftJoin(clazz, left, right);
    }

    @Override
    public <T1, X> MPJLambdaWrapperX<T> rightJoin(Class<T1> clazz, SFunction<T1, ?> left, SFunction<X, ?> right) {
        return (MPJLambdaWrapperX<T>)super.rightJoin(clazz, left, right);
    }

    @Override
    public <T1> MPJLambdaWrapperX<T> leftJoin(Class<T1> clazz, WrapperFunction<MPJAbstractLambdaWrapper<T, ?>> function) {
        return (MPJLambdaWrapperX<T>)super.leftJoin(clazz, function);
    }

    @Override
    public <T1> MPJLambdaWrapperX<T> leftJoin(Class<T1> clazz, String alias, WrapperFunction<MPJAbstractLambdaWrapper<T, ?>> function) {
        return (MPJLambdaWrapperX<T>)super.leftJoin(clazz, alias, function);
    }

    @Override
    public MPJLambdaWrapperX<T> and(boolean condition, Consumer<MPJLambdaWrapper<T>> consumer) {
        return (MPJLambdaWrapperX<T>)super.and(condition, consumer);
    }

    @Override
    public <X> MPJLambdaWrapperX<T> isNull(boolean condition, SFunction<X, ?> column) {
        return (MPJLambdaWrapperX<T>)super.isNull(condition, column);
    }

    @Override
    public <X> MPJLambdaWrapperX<T> isNotNull(boolean condition, SFunction<X, ?> column) {
        return (MPJLambdaWrapperX<T>)super.isNotNull(condition, column);
    }

    @Override
    public <R> MPJLambdaWrapperX<T> eq(SFunction<R, ?> column, Object val) {
        return (MPJLambdaWrapperX<T>)super.eq(column, val);
    }
}
