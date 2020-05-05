package com.lxf.performance.basicclass.string;

import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * @Description:  string字符串不同拼接方式性能对比:吞吐量
 * 结论：StringBuffer > StringBuilder > StringConcat > StringAdd  > StringFormat
 * Benchmark                                  Mode  Cnt          Score         Error  Units
 * StringConnectBenchmark.testStringAdd      thrpt   30   24378926.539 ± 1781219.978  ops/s
 * StringConnectBenchmark.testStringBuffer   thrpt   30  102683042.919 ± 7246985.360  ops/s
 * StringConnectBenchmark.testStringBuilder  thrpt   30   99184277.849 ± 6338261.805  ops/s
 * StringConnectBenchmark.testStringConcat   thrpt   30   31328421.035 ± 3922514.206  ops/s
 * StringConnectBenchmark.testStringFormat   thrpt   30     814593.244 ±   44671.482  ops/s

 * @Author: xfyh
 * @Date: 2020/4/20 15:16
 */
public class StringConnectRunner {
    public static void main(String[] args) throws Exception{
        Options opt = new OptionsBuilder()
                // 导入要测试的类
                .include(StringConnectBenchmark.class.getSimpleName())
                // 预热5轮
                .warmupIterations(5)
                // 度量10轮
                .measurementIterations(10)
                .mode(Mode.Throughput)
                .forks(3)
                .build();

        new Runner(opt).run();
    }
}
