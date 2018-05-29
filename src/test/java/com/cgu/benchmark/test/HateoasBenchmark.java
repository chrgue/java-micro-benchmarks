package com.cgu.benchmark.test;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.springframework.hateoas.UriTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cgu.benchmark.controller.BenchmarkController;


@Measurement(iterations = 5)
@Fork(warmups = 1, value = 1)
@Warmup(iterations = 2)
public class HateoasBenchmark {

    {
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));
    }

    @State(Scope.Benchmark)
    public static class ExecutionPlan {
        @Param({ "100" })
        public int iterations;
    }


    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void expandOnLink(ExecutionPlan plan) {
        for (int i = plan.iterations; i > 0; i--) {
            String url = linkTo(methodOn(BenchmarkController.class).endpoint(1L)).withSelfRel().expand().getHref();
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void expandOnTemplate(ExecutionPlan plan) {
        UriTemplate template = linkTo(methodOn(BenchmarkController.class).endpoint(1L)).withSelfRel().getTemplate();
        for (int i = plan.iterations; i > 0; i--) {
            String url = template.expand().toString();
        }
    }
}