/*
 * Copyright (c) 2011-2022, baomidou (jobob@qq.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package top.aenlly.acg.core.mybatis.type;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.EnumUtil;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.extension.handlers.AbstractJsonTypeHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import top.aenlly.acg.common.pojo.ACL;
import top.aenlly.acg.common.pojo.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Jackson 实现 JSON 字段类型处理器
 * <p>
 * 对File的map、list做了处理
 * </p>
 *
 * @author hubin
 * @since 2019-08-25
 */
@Slf4j
@MappedTypes({Object.class})
@MappedJdbcTypes(JdbcType.VARCHAR)
public class JacksonFileTypeHandler extends AbstractJsonTypeHandler<Object> {

    public static final String ACL_KEY = "acl";
    private static ObjectMapper OBJECT_MAPPER;
    private final Class<?> type;

    public JacksonFileTypeHandler(Class<?> type) {
        if (log.isTraceEnabled()) {
            log.trace("JacksonTypeHandler(" + type + ")");
        }
        Assert.notNull(type, "Type argument cannot be null");
        this.type = type;
    }

    @Override
    protected Object parse(String json) {
        try {
            Object o = getObjectMapper().readValue(json, this.type);
            if (o instanceof Map) {
                Map<Object, Object> map = new HashMap<>(8);
                ((Map<?, ?>) o).forEach((k, v) -> {
                    if (v instanceof List) {
                        Object list = this.handleList(v);
                        map.put(k, list);
                    }
                });
                return map;
            }
            if (o instanceof List) {
                Object list = this.handleList(o);
                return list;
            }
            return o;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected String toJson(Object obj) {
        try {
            return getObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static ObjectMapper getObjectMapper() {
        if (null == OBJECT_MAPPER) {
            OBJECT_MAPPER = new ObjectMapper();
        }
        return OBJECT_MAPPER;
    }

    private Object handleList(Object o) {
        List<File> list = new ArrayList<>(8);
        List ol = (List) o;
        if (ol.size() > 0 && !(((Map) ol.get(0)).containsKey(ACL_KEY))) {
            return o;
        }
        ol.forEach(f -> {
            ACL acl = EnumUtil.getBy(ACL.class, e -> e.getValue().equals(((Map) f).get(ACL_KEY)));
            File file = BeanUtil.copyProperties(f, File.class, ACL_KEY);
            file.setAcl(acl);
            list.add(file);
        });
        return list;
    }

    public static void setObjectMapper(ObjectMapper objectMapper) {
        Assert.notNull(objectMapper, "ObjectMapper should not be null");
        JacksonFileTypeHandler.OBJECT_MAPPER = objectMapper;
    }
}
