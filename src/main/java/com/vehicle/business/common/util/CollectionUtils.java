package com.vehicle.business.common.util;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;

import java.util.Iterator;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Maps.newLinkedHashMap;

/**
 * @author HALOXIAO
 **/
public final class CollectionUtils {
    private CollectionUtils() {
    }

    public static <K, V, Y> ImmutableMap<K, V> toMap(Iterator<Y> keys, Function<? super Y, K> keyFunction, Function<? super Y, V> valueFunction) {
        checkNotNull(valueFunction);
        checkNotNull(keyFunction);
        Map<K, V> builder = newLinkedHashMap();
        while (keys.hasNext()) {
            Y key = keys.next();
            builder.put(keyFunction.apply(key), valueFunction.apply(key));
        }
        return ImmutableMap.copyOf(builder);
    }


}
