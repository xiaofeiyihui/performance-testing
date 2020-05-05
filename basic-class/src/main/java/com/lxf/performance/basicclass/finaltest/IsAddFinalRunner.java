package com.lxf.performance.basicclass.finaltest;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * @Description: 变量前添加final,对性能是否有提升
 *
 *  结论：整体看，差别不大(可能是测试次数不足)
 *
 *  Benchmark                         Mode  Cnt  Score    Error  Units
 *  IsAddFinalBenchmark.getFinalUser  avgt   10  0.069 ±  0.011  us/op
 *  IsAddFinalBenchmark.getUser       avgt   10  0.071 ±  0.016  us/op
 *  IsAddFinalBenchmark.intFinalSum   avgt   10  0.003 ±  0.001  us/op
 *  IsAddFinalBenchmark.intSum        avgt   10  0.003 ±  0.001  us/op
 *
 * @Author: xfyh
 * @Date: 2020/4/20 16:11
 */
public class IsAddFinalRunner {

    public static void main(String[] args) throws Exception{
        // 使用一个单独进程执行测试，执行5遍warmup，然后执行10遍测试
        Options opt = new OptionsBuilder()
                .include(IsAddFinalBenchmark.class.getSimpleName())
                .forks(1)
                .warmupIterations(5)
                .measurementIterations(10)
                .build();
        new Runner(opt).run();
    }
}
