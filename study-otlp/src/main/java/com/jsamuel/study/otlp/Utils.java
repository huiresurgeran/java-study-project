package com.jsamuel.study.otlp;

import io.opentelemetry.exporter.logging.otlp.OtlpJsonLoggingLogExporter;
import io.opentelemetry.exporter.logging.otlp.OtlpJsonLoggingMetricExporter;
import io.opentelemetry.exporter.logging.otlp.OtlpJsonLoggingSpanExporter;
import io.opentelemetry.exporter.otlp.http.logs.OtlpHttpLogExporter;
import io.opentelemetry.exporter.otlp.logs.OtlpGrpcLogExporter;
import io.opentelemetry.exporter.otlp.metrics.OtlpGrpcMetricExporter;
import io.opentelemetry.exporter.otlp.trace.OtlpGrpcSpanExporter;
import io.opentelemetry.sdk.logs.LogProcessor;
import io.opentelemetry.sdk.logs.export.BatchLogProcessor;
import io.opentelemetry.sdk.logs.export.LogExporter;
import io.opentelemetry.sdk.logs.export.SimpleLogProcessor;
import io.opentelemetry.sdk.metrics.export.AggregationTemporalitySelector;
import io.opentelemetry.sdk.metrics.export.MetricExporter;
import io.opentelemetry.sdk.resources.Resource;
import io.opentelemetry.sdk.trace.export.SpanExporter;
import io.opentelemetry.semconv.resource.attributes.ResourceAttributes;
import java.util.concurrent.TimeUnit;

public class Utils {

    public static String tpsTenantId = "1670#sdk-4cf8666c611d87e1#6606_40558___apm";
    public static String serviceName = "java-sdk-demo";

    public static Resource getResource() {
        // default resource + custom resource
        // including : telemetry.sdk.name + telemetry.sdk.language + telemetry.sdk.version
        Resource resource = Resource.getDefault().merge(
                Resource.builder()
                        // 密钥
                        .put("tps.tenant.id", tpsTenantId)
                        // 应用名
                        .put(ResourceAttributes.SERVICE_NAME, serviceName)
                        .build());

        // custom resource
        Resource customResource = Resource.builder()
                // 密钥
                .put("tps.tenant.id", tpsTenantId)
                // 应用名
                .put(ResourceAttributes.SERVICE_NAME, serviceName)
                .build();

        return resource;
    }

    public static SpanExporter getLocalSpanExporter() {
        // local exporter, 打印日志到本地
        SpanExporter spanExporter = OtlpJsonLoggingSpanExporter.create();
        return spanExporter;
    }

    public static SpanExporter getRemoteSpanExporter() {
        // exporter
        OtlpGrpcSpanExporter spanExporter = OtlpGrpcSpanExporter.builder()
                // 接入点
                .setEndpoint("http://trace.zhiyan.tencent-cloud.net:4317")
                .build();
        return spanExporter;
    }

    public static MetricExporter getLocalMetricExporter() {
        // local exporter, 打印日志到本地
        MetricExporter metricExporter = OtlpJsonLoggingMetricExporter.create();
        return metricExporter;
    }

    public static MetricExporter getRemoteMetricExporter() {
        OtlpGrpcMetricExporter metricExporter = OtlpGrpcMetricExporter.builder()
                .setEndpoint("http://9.134.77.101:4317")
                .setTimeout(5_000, TimeUnit.MILLISECONDS)
                .setAggregationTemporalitySelector(
                        AggregationTemporalitySelector.deltaPreferred())
                .build();
        return metricExporter;
    }

    public static LogExporter getLocalLogExporter() {
        // log exporter
        // Analog of SpanExporter
        // Allows to implement protocol-specific exporters so that they can be plugged into OpenTelemetry SDK and support sending of log data.

        // logging to file
        // local exporter, 打印日志到本地
        LogExporter logExporter = OtlpJsonLoggingLogExporter.create();
        return logExporter;
    }

    public static LogExporter getRemoteGrpcLogExporter() {
        // Logging Directly to OTLP Network Destination
        // OTLP/gRPC
        LogExporter logExporter = OtlpGrpcLogExporter.builder()
                .setEndpoint("http://trace.zhiyan.tencent-cloud.net:4317")
                .build();
        return logExporter;
    }

    public static LogExporter getRemoteHttpLogExporter() {
        // Logging Directly to OTLP Network Destination
        // OTLP/HTT
        LogExporter logExporter = OtlpHttpLogExporter.builder()
                .setEndpoint("http://trace.zhiyan.tencent-cloud.net:4317")
                .build();
        return logExporter;
    }

    public static LogProcessor getSimpleLogProcessor() {
        // log processor
        // Analog of SpanProcessor
        // Interface to hook the log record emitting action.

        LogProcessor logProcessor = SimpleLogProcessor.create(getRemoteGrpcLogExporter());
        return logProcessor;
    }

    public static LogProcessor getBatchLogProcessor() {
        // log processor
        // Analog of SpanProcessor
        // Interface to hook the log record emitting action.

        // batch log processor
        LogProcessor batchLogProcessor = BatchLogProcessor
                .builder(getRemoteGrpcLogExporter())
                .build();
        return batchLogProcessor;
    }

    public static LogProcessor getLocalLogProcessor() {
        // log processor
        // Analog of SpanProcessor
        // Interface to hook the log record emitting action.

        // local log processor
        LogProcessor logProcessor = SimpleLogProcessor.create(getLocalLogExporter());
        return logProcessor;
    }
}
