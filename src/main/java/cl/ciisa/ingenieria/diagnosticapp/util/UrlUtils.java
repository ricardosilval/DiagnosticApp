/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.ciisa.ingenieria.diagnosticapp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * @author cokecuevas
 */
public class UrlUtils {
    
    private UrlUtils() {
    }

    public static UrlUtils getInstance() {
        return UrlUtilsHolder.INSTANCE;
    }

    private static class UrlUtilsHolder {

        private static final UrlUtils INSTANCE = new UrlUtils();
    }
    
    public List<Integer> convertStringUrlToListInteger(String elementoPorComa){
        List<Integer> listaInteger = new ArrayList<>();
        List<String> listaString = Arrays.asList(elementoPorComa.split(","));
        for(String s : listaString) listaInteger.add(Integer.valueOf(s));
        return listaInteger;
    }
    
    public List<Long> convertStringUrlToListLong(String elementoPorComa){
        List<Long> listaLong = new ArrayList<>();
        List<String> listaString = Arrays.asList(elementoPorComa.split(","));
        for(String s : listaString) listaLong.add(Long.valueOf(s));
        return listaLong;
    }
    
    public List<String> convertStringUrlToListString(String elementoPorComa){
        List<String> listaString = Arrays.asList(elementoPorComa.split(","));
        return listaString;
    }
    
    public List<Date> convertStringUrlToListDate(String elementoPorComa) throws ParseException{
        List<Date> listaDate = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<String> listaString = Arrays.asList(elementoPorComa.split(","));
        
        for(String s : listaString) 
        listaDate.add(dateFormat.parse(s));
        
        return listaDate;
    }
    
}
