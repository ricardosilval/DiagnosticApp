//
//package cl.diagnosticapp.model.responses;
//
//import cl.diagnosticapp.model.Model;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// *
// * @author ricardo
// */
//public class ModelListResponse {
//
//    private List<ModelResponse> modelos;
//    private long total;
//    private long paginaActual;
//    private long filas;
//
//    public ModelListResponse(List<Model> modelos) {
//        this.modelos = new ArrayList<>();
//        modelos.forEach((r) -> this.modelos.add(new ModelResponse(r)));
//    }
//    public ModelListResponse(List<Model> modelos, long total, long paginaActual, long filas) {
//        this(modelos);
//        this.total = total;
//        this.paginaActual = paginaActual;
//        this.filas = filas;
//    }
//
//    public List<ModelResponse> getModelos() {
//        return modelos;
//    }
//
//    public long getTotal() {
//        return total;
//    }
//
//    public long getPaginaActual() {
//        return paginaActual;
//    }
//
//    public long getFilas() {
//        return filas;
//    }
//    
//
//}
