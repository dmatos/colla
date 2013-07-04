package colla.appl.developer_viewer.tests;

import colla.appl.developer_viewer.ConfigException;
import colla.appl.developer_viewer.DevViewerLogin;
import colla.appl.developer_viewer.DeveloperViewerController;
import static org.junit.Assert.*;

import org.junit.Test;


public class Login {

	@Test
	public void test() {	
		DevViewerLogin login = null;
		DeveloperViewerController devViewer = null;
		try{
			login = new DevViewerLogin();
			devViewer = login.logInServer("diogo", "diogom");
			//testing if login has occurred well
			assertNotNull(devViewer);			
		}catch(ConfigException ex){
			fail("server_conf.xml not found");
		} catch(Exception ex){
                    fail("connection exception");
                }
	}

}
