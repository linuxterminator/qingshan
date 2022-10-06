package com.hu.qingshan.interceptor;

import com.alibaba.druid.DbType;
import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor;
import com.alibaba.druid.stat.TableStat;
import com.alibaba.druid.util.JdbcConstants;
import com.hu.qingshan.annotation.LogicDel;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * mybatis拦截器
 * 查询拦截，拦截逻辑查询
 */
@Slf4j
@Intercepts({
        @Signature(type = Executor.class,method = "query",args = {MappedStatement.class,Object.class, RowBounds.class, ResultHandler.class})})
public class MybatisInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        /**
         * 只有更新操作
         */
        Object param = invocation.getArgs()[1];
        /**
         * mybatis的原生分页
         */
        RowBounds rowBounds = (RowBounds) invocation.getArgs()[2];
        ResultHandler resultHandler = (ResultHandler) invocation.getArgs()[3];

        Executor executor = (Executor)invocation.getTarget();

        /**
         * 获取返回值类型
         */
        ResultMap resultMap = mappedStatement.getResultMaps().get(0);
        Class<?> type = resultMap.getType();
        /**
         * 获取类上的注解
         */
        Annotation[] annotations = type.getAnnotations();
        /**
         * 获取方法字段上的注解logic_del
         */
        Field[] declaredFields = type.getDeclaredFields();
        /**
         * 获取sql
         */
        BoundSql boundSql = mappedStatement.getBoundSql(param);
        /**
         * 存在逻辑删除,注解增强
         */
        Arrays.stream(declaredFields).forEach(field -> {
            if (field.isAnnotationPresent(LogicDel.class)){
                /**
                 * 使用druid重新组装sql，需要避免语句中可能已经存在where条件或者存在order by条件导致出错
                 */
                parseMybatisSql(boundSql.getSql()).forEach(sqlStatement -> {
                    MySqlSchemaStatVisitor mySqlSchemaStatVisitor = new MySqlSchemaStatVisitor();
                    sqlStatement.accept(mySqlSchemaStatVisitor);
                    /**
                     * 获取条件
                     */
                    List<TableStat.Condition> conditions = mySqlSchemaStatVisitor.getConditions();
                    /**
                     * 添加条件
                     */
                    String newSql = SQLUtils.addCondition(boundSql.getSql(), "is_del = 0", JdbcConstants.MYSQL);
                    /**
                     * 反射修改boundsql
                     * 直接通过getSqlSource获取的sql然后通过反射修改sql字段会有问题，因为他每次获取的都是一个新的实例，
                     * 需要使用反射从mapperstatement就开始获取，这里使用mybatis的反射工具来获取
                     */
                    MetaObject metaObject = MetaObject.forObject(mappedStatement, new DefaultObjectFactory(), new DefaultObjectWrapperFactory(), new DefaultReflectorFactory());
                    metaObject.setValue("sqlSource.sqlSource.sql",newSql);
                });
            }
        });
        // 想要修改BoundSql中的sql需要使用反射，我也不知道为什么BoundSql没有提供代码进行修改
        return invocation.proceed();
    }

    /**
     * 解析mybatis的sql
     *
     * @return
     */
    private List<SQLStatement> parseMybatisSql(String sql){
        DbType mysql = JdbcConstants.MYSQL;
        List<SQLStatement> sqlStatements = SQLUtils.parseStatements(sql, mysql);
        return sqlStatements;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
