package com.jsamuel.study.otlp;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.common.AttributeKey;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.metrics.LongCounter;
import io.opentelemetry.api.metrics.LongHistogram;
import io.opentelemetry.api.metrics.Meter;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.StatusCode;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.api.trace.propagation.W3CTraceContextPropagator;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.propagation.ContextPropagators;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.common.CompletableResultCode;
import io.opentelemetry.sdk.logs.LogBuilder;
import io.opentelemetry.sdk.logs.SdkLogEmitterProvider;
import io.opentelemetry.sdk.logs.data.Severity;
import io.opentelemetry.sdk.metrics.SdkMeterProvider;
import io.opentelemetry.sdk.metrics.export.PeriodicMetricReader;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.SpanProcessor;
import io.opentelemetry.sdk.trace.export.SimpleSpanProcessor;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static String scopeName = "instrumentation-library-name";
    public static String version = "1.0.0";

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        // init OTel SDK
        OpenTelemetrySdk openTelemetrySdk = initOpenTelemetrySdk();

        // get tracer
        Tracer tracer = getTracer(openTelemetrySdk, scopeName, version);

        // get meter
        Meter meter = getMeter(openTelemetrySdk, scopeName, version);

        // get log builder
        LogBuilder logBuilder = getLogBuilder(openTelemetrySdk.getSdkLogEmitterProvider(), scopeName, version);

        // add req counter
        LongCounter reqNumCounter = addLongCounter(meter, "request_number", "Request number.", "1");
        // add costTime histogram
        LongHistogram costTimeHistogram = addHistogram(meter, "cost_time", "Reuest cost time.", "ms");

        // start report task
        executorService.execute(() -> otelTask(tracer, reqNumCounter, costTimeHistogram, logBuilder));
    }

    public static void otelTask(Tracer tracer, LongCounter reqNumCounter, LongHistogram costTimeHistogram,
            LogBuilder logBuilder) {
        while (true) {
            Span span = addSpan(tracer, "entry");

            reportCounterMetric(reqNumCounter);
            reportHisMetric(costTimeHistogram);

            reportLog(logBuilder, span);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void reportCounterMetric(LongCounter longCounter) {
        long value = RandomUtils.nextLong(1, 200);
        longCounter.add(value, getAttributes());
        logger.info("Export metric data successfully: {}", value);
    }

    public static void reportHisMetric(LongHistogram longHistogram) {
        long value = RandomUtils.nextLong(1, 200);
        longHistogram.record(value, getAttributes());
        logger.info("Export metric data successfully: {}", value);
    }

    public static OpenTelemetrySdk initOpenTelemetrySdk() {
        // ------- tracer --------

        // spanProcessor
        final SpanProcessor spanProcessor = SimpleSpanProcessor.create(Utils.getRemoteSpanExporter());

        // 创建tracer provider
        final SdkTracerProvider sdkTracerProvider = SdkTracerProvider.builder()
                .setResource(Utils.getResource())
                .addSpanProcessor(spanProcessor)
                .build();

        // ------- metric --------

        // reader
        PeriodicMetricReader periodicMetricReader = PeriodicMetricReader
                .builder(Utils.getRemoteMetricExporter())
                // period time
                .setInterval(Duration.ofMillis(10_000))
                .build();
        // IntervalMetricReader intervalMetricReader

        // 创建metric provider
        // entry point of the api, provides access to Meters
        SdkMeterProvider sdkMeterProvider = SdkMeterProvider.builder()
                .setResource(Utils.getResource())
                // reader
                .registerMetricReader(periodicMetricReader)
                // .setClock()
                // .registerView(instrumentSelector, view)
                .build();

        // ------- log --------

        // 创建logging provider：https://opentelemetry.io/docs/reference/specification/logs/logging-library-sdk/#specification
        // LogEmitterProvider can be configured at startup time
        // to be associated with a Resource and with LogProcessor/LogExporter pipeline.
        SdkLogEmitterProvider sdkLogEmitterProvider = SdkLogEmitterProvider.builder()
                .setResource(Utils.getResource())
                .addLogProcessor(Utils.getSimpleLogProcessor())
                .addLogProcessor(Utils.getLocalLogProcessor())
                // .setLogLimits()
                // .setClock()
                .build();

        OpenTelemetrySdk openTelemetrySdk = OpenTelemetrySdk.builder()
                .setTracerProvider(sdkTracerProvider)
                .setMeterProvider(sdkMeterProvider)
                .setLogEmitterProvider(sdkLogEmitterProvider)
                // W3C传播器，协议说明 https://www.w3.org/TR/trace-context-1/#problem-statement
                .setPropagators(ContextPropagators.create(W3CTraceContextPropagator.getInstance()))
                .buildAndRegisterGlobal();

        Runtime.getRuntime()
                .addShutdownHook(new Thread(openTelemetrySdk.getSdkTracerProvider()::shutdown));
        Runtime.getRuntime()
                .addShutdownHook(new Thread(openTelemetrySdk.getSdkMeterProvider()::shutdown));
        Runtime.getRuntime()
                .addShutdownHook(new Thread(openTelemetrySdk.getSdkLogEmitterProvider()::shutdown));

        return openTelemetrySdk;
    }

    /**
     * meter support
     * 1. Counter
     * 2. Asynchronous Counter
     * 3. Histogram
     * 4. Asynchronous Gauge
     * 5. UpDownCounter
     * 6. Asynchronous UpDownCouter
     *
     * @param openTelemetrySdk
     * @return
     */
    public static Meter getMeter(OpenTelemetrySdk openTelemetrySdk, String scopeName, String version) {
        // meter
        // responsible for creating Instruments
        Meter meter = openTelemetrySdk
                // name，This name SHOULD uniquely identify the instrumentation scope
                .meterBuilder(scopeName)
                // version， Specifies the version of the instrumentation scope if the scope has a version
                .setInstrumentationVersion(version)
                .build();

        return meter;
    }

    public static Meter getMeter(OpenTelemetry openTelemetry, String scopeName) {
        // meter
        // responsible for creating Instruments
        // name，This name SHOULD uniquely identify the instrumentation scope
        Meter meter = openTelemetry.getMeter(scopeName);
        return meter;
    }

    public static Meter getMeter(SdkMeterProvider sdkMeterProvider, String scopeName) {
        // meter
        // responsible for creating Instruments
        Meter meter = sdkMeterProvider.get(scopeName);
        return meter;
    }

    /**
     * instrument: response for reporting Measurements
     * fields:
     * 1. name
     * 2. kind ( Counter / Histogram / ... ）
     * 3. unit
     * 4. description
     *
     * @param meter
     * @return
     */
    public static LongCounter addLongCounter(Meter meter, String name, String desc, String unit) {
        // longCounter
        LongCounter longCounter = meter
                .counterBuilder(name)
                .setDescription(desc)
                .setUnit(unit)
                .build();

        return longCounter;
    }

    public static LongHistogram addHistogram(Meter meter, String name, String desc, String unit) {
        // histogram
        LongHistogram longHistogram = meter
                .histogramBuilder(name)
                .setDescription(desc)
                .setUnit(unit)
                .ofLongs()
                .build();

        return longHistogram;
    }

    public static Tracer getTracer(OpenTelemetrySdk openTelemetrySdk, String scopeName, String version) {
        // 创建trace
        final Tracer tracer = openTelemetrySdk.getTracer(scopeName, version);
        return tracer;
    }

    public static Span addSpan(Tracer tracer, String spanName) {
        // 创建span
        final Span span = tracer.spanBuilder(spanName).startSpan();

        Attributes attributes = getAttributes();
        for (AttributeKey attributeKey : attributes.asMap().keySet()) {
            span.setAttribute(attributeKey, attributes.get(attributeKey));
        }

        try {
            // 业务操作
            doStuff();
            // 可选，设置span状态
            span.setStatus(StatusCode.OK);
        } catch (InterruptedException e) {
            // 可选，设置span的异常状态
            span.setStatus(StatusCode.ERROR);
            // 可选，记录异常到span中
            span.recordException(e);
        } finally {
            // 结束span，如果不结束可能会导致内存泄漏且无法上报
            span.end();
        }

        return span;
    }

    // TODO ??
    public static LogBuilder getLogBuilder(SdkLogEmitterProvider sdkLogEmitterProvider, String scopeName,
            String version) {
        // Logging Builder

        // 1.
        LogBuilder logBuilder = sdkLogEmitterProvider
                .logEmitterBuilder(scopeName)
                .setInstrumentationVersion(version)
                .build()
                .logBuilder();

        // 2.
        //
        // LogEmitter logEmitter = sdkLogEmitterProvider.get(scopeName);
        // LogBuilder logBuilder = logEmitter.logBuilder();

        return logBuilder;
    }

    public static void reportLog(LogBuilder logBuilder, Span span) {
        Severity level = getLogLevel();

        // logs data model
        // https://opentelemetry.io/docs/reference/specification/logs/data-model/
        logBuilder
                // Timestamp：Time when the event occurred. uint64 nanoseconds since Unix epoch
                // ObservedTimestamp：Time when the event was observed. uint64 nanoseconds since Unix epoch.
                .setEpoch(Instant.now())
                // 日志内容，Body：The body of the log record.
                .setBody(RandomStringUtils.randomAlphabetic(20))
                // 日志等级，SeverityNumber：Numerical value of the severity.
                .setSeverity(level)
                // 日志等级的可读描述，SeverityText：The severity text (also known as log level).
                .setSeverityText(level.toString())
                // 额外关联属性，Attributes：Additional information about the event.
                .setAttributes(getAttributes())
                // span context
                // SpanId：Request span id.
                // TraceFlags：W3C trace flag
                // TraceId：Request trace id.
                .setContext(Context.current().with(Span.wrap(span.getSpanContext())))
                // Resource：Describes the source of the log, aka resource. device resources.
                // InstrumentationScope：Describes the scope that emitted the log. including name and version.
                // Name SHOULD be specified if version is specified, otherwise Name is optional.
                // Version is optional.
                // 上报
                .emit();
    }

    private static void shutdownGracefully(SpanProcessor spanProcessor) {
        if (spanProcessor != null) {
            final CompletableResultCode shutdown = spanProcessor.shutdown();
            System.out.println("shutdown span processor, and wait for finish");
            shutdown.join(5L, TimeUnit.SECONDS);
            System.out.printf("processor shutdown result, isDone: %s, isSuccess: %s\n", shutdown.isDone(),
                    shutdown.isSuccess());
        }
    }

    public static Attributes getAttributes() {
        return Attributes.builder()
                .put("server", "server")
                .put("service", "service")
                .put("ip", "ip")
                .build();
    }

    private static void doStuff() throws InterruptedException {
        System.out.println("do stuff...about 1 second");
        TimeUnit.SECONDS.sleep(1L);
        System.out.println("finish stuff");
    }

    private static Severity getLogLevel() {
        Severity[] levels = new Severity[]{Severity.TRACE, Severity.DEBUG, Severity.INFO, Severity.WARN,
                Severity.ERROR};

        int value = RandomUtils.nextInt(1, 20);
        return levels[(int) value % levels.length];
    }
}
