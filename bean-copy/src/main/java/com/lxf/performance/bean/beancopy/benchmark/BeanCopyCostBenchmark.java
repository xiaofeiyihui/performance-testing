package com.lxf.performance.bean.beancopy.benchmark;

import com.lxf.performance.bean.beancopy.model.UserSource;
import com.lxf.performance.bean.beancopy.model.UserTarget;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import net.sf.cglib.beans.BeanCopier;
import org.dozer.DozerBeanMapperBuilder;
import org.dozer.Mapper;
import org.openjdk.jmh.annotations.*;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Description: bean copy 性能对比
 * 对象包括：
 * apache BeanUtils 、
 * spring BeanUtils、
 * BeanCopier、
 * Dozer、
 * Orika，
 * 手动转换
 *
 *  以字段，类型完全一直测试
 *
 * @Author: xfyh
 * @Date: 2020/4/21 19:34
 */

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
public class BeanCopyCostBenchmark {
    private static BeanCopier cglibBeanCopier = BeanCopier.create(UserSource.class, UserTarget.class, Boolean.FALSE);
    private static org.springframework.cglib.beans.BeanCopier springBeanCopier = org.springframework.cglib.beans.BeanCopier.create(UserSource.class, UserTarget.class, Boolean.FALSE);
    private static  Mapper dozerMapper = DozerBeanMapperBuilder.buildDefault();
    private static MapperFacade orikaMapper = new DefaultMapperFactory.Builder().build().getMapperFacade();
    private static UserSource userSource =new UserSource(1,"张三",12,"男",new Date());

    /**
     * 手动get/set copy
     * @return
     */
    @Benchmark
    public UserTarget copyByHand() {
        UserTarget userTarget = new UserTarget();
        userTarget.setId(userSource.getId());
        userTarget.setName(userSource.getName());
        userTarget.setAge(userSource.getAge());
        userTarget.setSex(userSource.getSex());
        userTarget.setLoginTime(userSource.getLoginTime());
        return userTarget;
    }

    /**
     * Spring BeanUtils
     * @return
     */
    @Benchmark
    public UserTarget copyBySpringBeanUtils() {
        UserTarget userTarget = new UserTarget();
        BeanUtils.copyProperties(userSource, userTarget);
        return userTarget;
    }

    /**
     * apache BeanUtils
     * @return
     */
    @Benchmark
    public UserTarget copyByApacheBeanUtils() throws Exception{
        UserTarget userTarget = new UserTarget();
        org.apache.commons.beanutils.BeanUtils.copyProperties(userTarget, userSource);
        return userTarget;
    }

    /**
     * Cglib BeanCopier
     * 注：一旦使用Converter，BeanCopier只使用Converter定义的规则去拷贝属性，
     * 所以在convert方法中要考虑所有的属性。
     * @return
     */
    @Benchmark
    public UserTarget copyByCglibBeanCopier() {
        UserTarget userTarget = new UserTarget();
        cglibBeanCopier.copy(userSource, userTarget,null);
        return userTarget;
    }

    @Benchmark
    public UserTarget copyBySpringBeanCopier() {
        UserTarget userTarget = new UserTarget();
        springBeanCopier.copy(userSource,userTarget,null);
        return userTarget;
    }

    /**
     * Dozer copy
     * @return
     */
    @Benchmark
    public UserTarget copyByDozer() {
        UserTarget userTarget = dozerMapper.map(userSource, UserTarget.class);
        return userTarget;
    }

    /**
     * Orika copy
     * @return
     */
    @Benchmark
    public UserTarget copyByOrika() {
        UserTarget userTarget = orikaMapper.map(userSource, UserTarget.class);
        return userTarget;
    }



}
