package com.cgu.benchmark.test;


import static com.jayway.restassured.RestAssured.given;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;


@Measurement(iterations = 5)
@Fork(warmups = 1, value = 1)
@Warmup(iterations = 2)
public class EndpointBenchmark {

    @State(Scope.Benchmark)
    public static class ExecutionPlan {
        @Param({ "5", "10", "20" })
        public int iterations;
    }


    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void benchmarkGoogle(ExecutionPlan plan) {
        for (int i = plan.iterations; i > 0; i--) {
            given()
                    .baseUri("https://www.google.de")
                    .get();
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void benchmarkAmazon(ExecutionPlan plan) {
        for (int i = plan.iterations; i > 0; i--) {
            given()
                    .baseUri("https://www.amazon.de")
                    .get();
        }
    }
}