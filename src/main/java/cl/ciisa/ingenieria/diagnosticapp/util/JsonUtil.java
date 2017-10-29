package cl.ciisa.ingenieria.diagnosticapp.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Utilitario para manipular objetos y string JSON.
 * @author Michel Munoz <michel@febos.cl>
 */
public class JsonUtil {

    private JsonUtil() {
    }

    public static JsonUtil getInstance() {
        return JsonUtilHolder.INSTANCE;
    }

    private static class JsonUtilHolder {

        private static final JsonUtil INSTANCE = new JsonUtil();
    }

    public String toJson(Object obj) {
        Gson g = new GsonBuilder().setDateFormat("yyyy-mm-dd hh:mm:ss").create();
        return g.toJson(obj);
    }

    /**
     * Mutila un Json, dejando solo los campos especificados.
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
