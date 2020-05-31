package ee.tp.interview_assignments.weather.service.cache;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.stereotype.Service;
import reactor.cache.CacheMono;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Signal;
import reactor.util.context.Context;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

@Service
public class CacheService {
    private final AtomicReference<Context> storeRef = new AtomicReference<>(Context.empty());

    @Getter
    @Builder
    @AllArgsConstructor
    static class CachedItem<T> {
        private final T data;
        private final Instant expiration;
    }

    public <T> Mono<T> get(String key, Mono<T> retrieveOnCacheMiss, Supplier<CacheConfiguration> cfgSupplier) {
        return CacheMono.lookup(
                k -> Mono.justOrEmpty(storeRef.get().<CachedItem<T>>getOrEmpty(k))
                    .filter(wrapper -> wrapper.expiration.isAfter(Instant.now()))
                    .map(Signal::next),
                key
            )
            .onCacheMissResume(retrieveOnCacheMiss.map(data -> wrap(data, cfgSupplier)))
            .andWriteWith((k, sig) -> Mono.fromRunnable(() -> storeRef.updateAndGet(ctx -> ctx.put(k, sig.get()))))
            .map(cachedItem -> cachedItem.data);
    }

    private <T> CachedItem<T> wrap(T data, Supplier<CacheConfiguration> cfgSupplier) {
        return CachedItem.<T>builder()
            .data(data)
            .expiration(Instant.now().plus(cfgSupplier.get().getTimeToLive()))
            .build();
    }
}
