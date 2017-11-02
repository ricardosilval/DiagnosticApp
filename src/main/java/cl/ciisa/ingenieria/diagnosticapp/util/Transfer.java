/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.ciisa.ingenieria.diagnosticapp.util;

import cl.ciisa.ingenieria.diagnosticapp.handlers.BaseException;
import cl.ciisa.ingenieria.diagnosticapp.handlers.Messages;
import cl.ciisa.ingenieria.diagnosticapp.model.BaseModel;
import cl.ciisa.ingenieria.diagnosticapp.dto.BaseRequest;
import java.lang.reflect.Field;

/**
 *
 * @author cokecuevas
 */
public class Transfer<T extends BaseRequest, K extends BaseModel> {

    final Class<T> r;
    final Class<K> m;

    public Transfer(Class<T> request, Class<K> model) {
        this.r = request;
        this.m = model;

    }

    public K transfer(BaseRequest requestObj, BaseModel modelObj) {
        K model = (K) modelObj;
        T request = (T) requestObj;
        
        Field[] camposRequest = request.getClass().getDeclaredFields();
        Field[] camposModel = model.getClass().getDeclaredFields();

        for (Field fieldRequest : camposRequest) {
            for (Field fieldModel : camposModel) {

                if (fieldRequest.getName().equals(fieldModel.getName())) {
                    fieldModel.setAccessible(true);
                    Class<?> type = fieldModel.getType();
                    
                    try {
                        
                        fieldModel.set(model, fieldRequest.get(request));
                           
                    } catch (Exception e) {
                        throw new BaseException (Messages.Errores.UNKNOWN_ERROR)
                                .set("claseRequest", request.getClass().getName())
                                .set("claseModel", model.getClass().getName())
                                
                                ;
                    } finally {

                    }

                }
            }

        }
        
        return (K) model;

    }

    public T transfer( BaseModel modelObj, BaseRequest requestObj) {
        K model = (K) modelObj;
        T request = (T) requestObj;
        
        Field[] camposRequest = request.getClass().getDeclaredFields();
        Field[] camposModel = model.getClass().getDeclaredFields();

        for (Field fieldModel : camposModel) {
            for (Field fieldRequest : camposRequest) {

                if (fieldModel.getName().equals(fieldRequest.getName())) {
                    fieldRequest.setAccessible(true);
                    Class<?> type = fieldRequest.getType();
                    
                    try {
                        
                        fieldRequest.set(request, fieldModel.get(model));
                           
                    } catch (Exception e) {
                        throw new BaseException (Messages.Errores.UNKNOWN_ERROR)
                                .set("claseRequest", request.getClass().getName())
                                .set("claseModel", model.getClass().getName())
                                
                                ;
                    } finally {

                    }

                }
            }

        }
        
        return request;

    }
}
