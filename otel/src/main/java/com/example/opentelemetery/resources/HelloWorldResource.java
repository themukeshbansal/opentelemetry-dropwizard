package com.example.opentelemetery.resources;

import com.codahale.metrics.annotation.Timed;
import com.example.opentelemetery.api.Saying;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.api.trace.TracerProvider;
import io.opentelemetry.context.propagation.TextMapPropagator;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Path("/hello")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {
    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    public HelloWorldResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    public Saying sayHello(@QueryParam("name") Optional<String> name,
                           @Context HttpServletRequest httpRequest) {

//        TracerProvider tracerProvider = OpenTelemetry.getGlobalTracerProvider();
//        Tracer tracer = tracerProvider.get("instrumentation-library-name","1.0.0");
//        TextMapPropagator.Getter<HttpServletRequest> getter =
//                new TextMapPropagator.Getter<HttpServletRequest>() {
//                    @Override
//                    public Iterable<String> keys(HttpServletRequest httpServletRequest) {
//                        return null;
//                    }
//
//                    @Override
//                    public String get(HttpServletRequest carrier, String key) {
//                        return carrier.getHeader(key);
//                    }
//                };
//
//        io.opentelemetry.context.Context extractedContext = OpenTelemetry.getGlobalPropagators().getTextMapPropagator().extract(
//                io.opentelemetry.context.Context.current(),
//                httpRequest,
//                getter);
        final String value = String.format(template, name.orElse(defaultName));
        return new Saying(counter.incrementAndGet(), value);
    }
}
