/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.ciisa.ingenieria.diagnosticapp.services.internal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

/**
 *
 * @author michel
 */
public class ServerMeter {
    final private Sigar sigar=new Sigar();
    
    private ServerMeter() {
    }
    
    public static ServerMeter getInstance() {
        //installSigarLibraries();
        return ServerMeterHolder.INSTANCE;
    }
    
    private static void installSigarLibraries(){
        String[] paths=System.getProperty("java.library.path").split(":");
        String classpath=paths[0].endsWith("/")?paths[0]:paths[0]+"/";
        System.out.println(classpath);
        String[] libs=new String[]{
            "libsigar-amd64-freebsd-6.so",
            "libsigar-amd64-linux.so",
            "libsigar-universal-macosx.dylib",
            "libsigar-universal64-macosx.dylib",
            "libsigar-x86-linux.so",
            "sigar-amb64-winnt.dll",
            "sigar-x86-winnt.dll",
            "sigar-x86-winnt.lib"
        };
        
        
        new File(classpath).mkdirs();
        for(String lib:libs){
            System.out.println("Rescantando:" +"lib/"+lib);
            InputStream resource = ServerMeter.class.getResourceAsStream("lib/"+lib);
            
            try {
                
                FileOutputStream outputStream =  new FileOutputStream(new File(classpath+lib));
                int read;
		byte[] bytes = new byte[1024];

		while ((read = resource.read(bytes)) != -1) {
			outputStream.write(bytes, 0, read);
		}
                resource.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ServerMeter.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ServerMeter.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
       
        
    }
    
    private static class ServerMeterHolder {
        
        private static final ServerMeter INSTANCE = new ServerMeter();
    }
    
    public void getInformationsAboutMemory() {
        System.out.println("**************************************");
        System.out.println("*** Informations about the Memory: ***");
        System.out.println("**************************************\n");
        
        Mem mem = null;
        try {
            mem = sigar.getMem();
        } catch (SigarException se) {
            se.printStackTrace();
        }

        System.out.println("Actual total free system memory: "
                + mem.getActualFree() / 1024 / 1024+ " MB");
        System.out.println("Actual total used system memory: "
                + mem.getActualUsed() / 1024 / 1024 + " MB");
        System.out.println("Total free system memory ......: " + mem.getFree()
                / 1024 / 1024+ " MB");
        System.out.println("System Random Access Memory....: " + mem.getRam()
                + " MB");
        System.out.println("Total system memory............: " + mem.getTotal()
                / 1024 / 1024+ " MB");
        System.out.println("Total used system memory.......: " + mem.getUsed()
                / 1024 / 1024+ " MB");

        System.out.println("\n**************************************\n");
    }
    public static void main(String[] args){
        ServerMeter.getInstance().getInformationsAboutMemory();
    }
}
