/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diagnosticapp.model.responses;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author ricardo
 */
public class DashboardResponse {

    private Long lotesRegistrados;
    private Long documentosEmitidos;
    private Long comprobantesPendientes;
    private List<MainChartResponse> mainChart;

    public DashboardResponse(Long lotesRegistrados, Long documentosEmitidos, Long comprobantesPendientes, List<MainChartResponse> mainChart) {
        this.lotesRegistrados = lotesRegistrados;
        this.documentosEmitidos = documentosEmitidos;
        this.comprobantesPendientes = comprobantesPendientes;
        this.mainChart = mainChart;
    }

    public Long getLotesRegistrados() {
        return lotesRegistrados;
    }

    public Long getDocumentosEmitidos() {
        return documentosEmitidos;
    }

    public Long getComprobantesPendientes() {
        return comprobantesPendientes;
    }

    public List<MainChartResponse> getMainChart() {
        return mainChart;
    }

}
