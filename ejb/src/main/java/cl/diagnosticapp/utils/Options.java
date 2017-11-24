/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diagnosticapp.utils;

/**
 *
 * @author ricardo
 */
public class Options {
    public int dpi;
    public String pageSize;
    public int marginTop;
    public int marginBottom;
    public int marginLeft;
    public int marginRight;
    
    public  Options(){
        this.dpi=300;
        this.pageSize="letter";
        this.marginRight=0;
        this.marginBottom=0;
        this.marginLeft=0;
        this.marginTop=0;

    }
}