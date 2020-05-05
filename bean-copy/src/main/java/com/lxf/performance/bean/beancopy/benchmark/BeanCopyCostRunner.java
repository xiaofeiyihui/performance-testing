package com.lxf.performance.bean.beancopy.benchmark;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * @Description: bean copy 性能对比
 * 测试对象包括：apache BeanUtils 、 spring BeanUtils、 BeanCopier、 Dozer、 Orika， 手动转换
 *
 * 结论：
 *      速度：手动 > BeanCopier > SpringBeanUtils > Orika > Dozer > ApacheBeanUtils
 *      所以在不想手动转换的情况下：
 *          如果只是简单转换 首选 SpringBeanUtils、BeanCopier
 *          如果涉及到复杂转换：首选 Orika
 *  Benchmark                                     Mode  Cnt    Score    Error  Units
 *  BeanCopyCostBenchmark.copyByApacheBeanUtils   avgt   30  374.760 ± 48.500  us/op
 *  BeanCopyCostBenchmark.copyByCglibBeanCopier   avgt   30    0.010 ±  0.001  us/op
 *  BeanCopyCostBenchmark.copyByDozer             avgt   30  232.889 ± 34.298  us/op
 *  BeanCopyCostBenchmark.copyByHand              avgt   30    0.009 ±  0.001  us/op
 *  BeanCopyCostBenchmark.copyByOrika             avgt   30    0.465 ±  0.032  us/op
 *  BeanCopyCostBenchmark.copyBySpringBeanCopier  avgt   30    0.008 ±  0.001  us/op
 *  BeanCopyCostBenchmark.copyBySpringBeanUtils   avgt   30    0.383 ±  0.038  us/op
 *
 * @Author: xfyh
 * @Date: 2020/4/21 19:46
 */
public class BeanCopyCostRunner {
    public static void main(String[] args) throws Exception{
        Options opt = new OptionsBuilder()
                .include(BeanCopyCostBenchmark.class.getSimpleName())
                .forks(1)
                .warmupIterations(10)
                .measurementIterations(30)
                .build();
        new Runner(opt).run();

    }
}
