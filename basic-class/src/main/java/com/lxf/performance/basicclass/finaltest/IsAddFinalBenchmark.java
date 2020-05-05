package com.lxf.performance.basicclass.finaltest;

import com.lxf.performance.basicclass.finaltest.modle.User;
import org.openjdk.jmh.annotations.*;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @Description:  变量前添加final,对性能是否有提升
 *
 * 分两组比对，基本数据类型，包装类型
 * @Author: xfyh
 * @Date: 2020/4/20 16:08
 */

@BenchmarkMode(Mode.AverageTime) // 测试方法平均执行时间
@OutputTimeUnit(TimeUnit.MICROSECONDS) // 输出结果的时间粒度为微秒
@State(Scope.Thread) // 每个测试线程一个实例
public class IsAddFinalBenchmark {

    @Benchmark
    public int intFinalSum() {
        final int a=34;
        final int b=30;
        return a+b;
    }

    @Benchmark
    public int intSum() {
        int a=34;
        int b=30;
        return a+b;
    }

    @Benchmark
    public User getFinalUser() {
        final User user = new User();
        user.setAge(12);
        user.setName("测试");
        user.setId(new Random().nextInt());
        return user;
    }
    @Benchmark
    public User getUser() {
        User user = new User();
        user.setAge(12);
        user.setName("测试");
        user.setId(new Random().nextInt());
        return user;
    }
}
