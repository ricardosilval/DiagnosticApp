/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diagnosticapp.model.responses;

/**
 *
 * @author ricardo
 */
public class MainChartResponse {

    private String aduanaLoteadora;
    private Long cantidadPreFacturas;

    public MainChartResponse(String aduanaLoteadora, Long cantidadPreFacturas) {
        this.aduanaLoteadora = aduanaLoteadora;
        this.cantidadPreFacturas = cantidadPreFacturas;
    }


    public String getAduanaLoteadora() {
        return aduanaLoteadora;
    }

    public Long getCantidadPreFacturas() {
        return cantidadPreFacturas;
    }

}
