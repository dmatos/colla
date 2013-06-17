/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.appl.server.GUI;

/**
 *
 * @author Diogo Matos <dmatos88 at gmail.com>
 */
public enum BackGround {
    
    SOFT_GREEN("/colla/appl/server/GUI/images/bg_softgreen.jpg"),
    INVERSE_SOFT_GREEN("/colla/appl/server/GUI/images/bg_softgreen_inverse.jpg"),
    DENSE_GREEN("/colla/appl/developer_viewer/GUI/images/host_fundo.jpg"),
    INVERSE_DENSE_GREEN("/colla/appl/server/GUI/images/host_fundo_invert.jpg"),
    SMOOTH_GREEN("/colla/appl/server/GUI/images/smooth_green.jpg"),
    RADIAL_CENTER_SOFTGREEN("/colla/appl/server/GUI/images/radial_green_center_green.jpg");    
    
    private final String bg_path;

    BackGround(String path) {
        this.bg_path = path;
    }

    /**
     * 
     * @return the pathname to the background
     */
    public String getPath() {
        return this.bg_path;
    }
}
