package top.aenlly.acg.common.pojo;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author Aenlly||tnw
 * @create 2022/10/26 11:29
 * @since 1.0.0
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class KeyValuePair implements Serializable {
    private Object key;
    private Object value;

    public static <T> KeyValuePair build(T t, Function<T, ?> fn1, Function<T, ?> fn2) {
        return KeyValuePair.builder().key(fn1.apply(t)).value(fn2.apply(t)).build();
    }

    public static <T> List<KeyValuePair> buildList(List<T> source, Function<T, ?> fn1, Function<T, ?> fn2) {
        List<KeyValuePair> list = new ArrayList<>();//Collections.emptyList() 报异常
        source.forEach(o -> list.add(build(o, fn1, fn2)));
        return list;
    }
}
