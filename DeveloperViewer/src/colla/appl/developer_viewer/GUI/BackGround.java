/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.appl.developer_viewer.GUI;

/**
 *
 * @author Diogo Matos <dmatos88 at gmail.com>
 */
public enum BackGround {

    GREEN_SPIRAL("/colla/appl/developer_viewer/GUI/images/login_fundo.jpg"), 
    SOFT_GREEN("/colla/appl/developer_viewer/GUI/images/bg_softgreen.jpg"),
    INVERSE_SOFT_GREEN("/colla/appl/developer_viewer/GUI/images/bg_softgreen_inverse.jpg"),
    DENSE_GREEN("/colla/appl/developer_viewer/GUI/images/host_fundo.jpg"),
    INVERSE_DENSE_GREEN("/colla/appl/developer_viewer/GUI/images/host_fundo_invert.jpg"), 
    RADIAL_GREEN("/colla/appl/developer_viewer/GUI/images/radial_green.jpg"),
    SMOOTH_GREEN("/colla/appl/developer_viewer/GUI/images/smooth_green.jpg"),
    RADIAL_CENTER_SOFTGREEN("/colla/appl/developer_viewer/GUI/images/radial_green_center_green.jpg"),
    COLLA_LOGO("/colla/appl/developer_viewer/GUI/images/colla_logo.png"), 
    COLLA_LOGO_ICON("/colla/appl/developer_viewer/GUI/images/colla_logo_icon.jpg");    
    
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
