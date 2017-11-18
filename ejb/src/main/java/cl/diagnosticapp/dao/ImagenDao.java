/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diagnosticapp.dao;

import cl.diagnosticapp.model.Imagen;

/**
 *
 * @author ricardo
 */
public class ImagenDao extends BaseDao<Imagen> {

    public ImagenDao() {
        super(Imagen.class);
    }

    public static ImagenDao getInstance() {
        return ImagenDaoHolder.INSTANCE;
    }

    private static class ImagenDaoHolder {

        private static final ImagenDao INSTANCE = new ImagenDao();
    }

}
