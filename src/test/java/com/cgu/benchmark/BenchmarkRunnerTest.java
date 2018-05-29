package com.cgu.benchmark;

import static org.junit.Assert.assertFalse;

import java.util.Collection;

import org.junit.Test;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import com.cgu.benchmark.test.EndpointBenchmark;
import com.cgu.benchmark.test.HateoasBenchmark;

public class BenchmarkRunnerTest {

    @Test
    public void testHateoasPerformance() throws RunnerException {
        Collection<RunResult> runResults = runBenchmark(HateoasBenchmark.class);
        assertFalse(runResults.isEmpty());
    }

    @Test
    public void testEndpointPerformance() throws RunnerException {
        Collection<RunResult> runResults = runBenchmark(EndpointBenchmark.class);
        assertFalse(runResults.isEmpty());
    }

    private Collection<RunResult> runBenchmark(Class<?> benchmarkClass) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(benchmarkClass.getSimpleName())
                .build();

        return new Runner(opt).run();
    }
}
