//package com.example.opentelemetery.api;
//
//
//import io.opentelemetry.exporter.zipkin.ZipkinSpanExporter;
//import io.opentelemetry.sdk.OpenTelemetrySdk;
//import io.opentelemetry.sdk.trace.export.SimpleSpanProcessor;
//
//public class OpenTelemetryAppMain {
//    public static void main(String[] args) {
//        OpenTelemetryAppMain app = new OpenTelemetryAppMain();
//        app.run();
//    }
//
//    private void run() {
//        ZipkinSpanExporter zipkinSpanExporter = ZipkinSpanExporter.builder()
//                .setServiceName(zipkinSpanExporterProperties.getServiceName())
//                .setEndpoint(zipkinSpanExporterProperties.getEndpoint())
//                .build();
//
////        SimpleSpanProcessor zipkinSpanProcessor = SimpleSpanProcessor.builder(zipkinSpanExporter).build();
//        OpenTelemetrySdk.getGlobalTracerManagement().addSpanProcessor(zipkinSpanProcessor);
//
//        InstrumentedFoo ifoo = new InstrumentedFoo();
//
//        ifoo.startEndSpan();
//        log.info("\n");
//
//        ifoo.startEndSpan_attach();
//        log.info("\n");
//
//        ifoo.foo();
//        log.info("\n");
//
//        OpenTelemetrySdk.getTracerProvider().forceFlush();
//    }
//}
