/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package air3il.gui.javafx;

import air3il.commun.service.IManagerService;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Amblard
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        configurerLogging();

        try {
            IManagerService managerService
                    = new air3il.emb.service.test.ManagerService();
            ManagerGui managerGui
                    = new ManagerGui(primaryStage, managerService);
            managerGui.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private static void configurerLogging() {
        try (InputStream in = Main.class.getResourceAsStream("/META-INF/logging.properties")) {
            LogManager logManager = LogManager.getLogManager();
            logManager.readConfiguration(in);
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }

    }

}
