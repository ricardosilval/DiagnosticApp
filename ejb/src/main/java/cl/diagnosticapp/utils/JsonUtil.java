
package cl.diagnosticapp.utils;

import cl.diagnosticapp.log.BaseLogger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class JsonUtil {

    private static final BaseLogger LOG = new BaseLogger();

    private JsonUtil() {
    }

    public static JsonUtil getInstance() {
        return JsonUtilHolder.INSTANCE;
    }

    private static class JsonUtilHolder {

        private static final JsonUtil INSTANCE = new JsonUtil();
    }

    public String toJson(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException ex) {
            LOG.error("Error convirtiendo a JSON", ex);
            return "";
        }
    }

    /**
     * Mutila un Json, dejando solo los campos especificados.
     *
     * @param obj
     * @param fields
     * @return
     * @throws JsonProcessingException
     */
    public String filterObject(Object obj, String... fields) throws JsonProcessingException {
        Set<String> filterProperties = new HashSet<>();
        filterProperties.addAll(Arrays.asList(fields));

        ObjectMapper mapper = new ObjectMapper();
        FilterProvider filters = new SimpleFilterProvider().addFilter("filtro", SimpleBeanPropertyFilter.filterOutAllExcept(filterProperties));

        String json = mapper.writer(filters).writeValueAsString(obj);
        return json;

    }
}
