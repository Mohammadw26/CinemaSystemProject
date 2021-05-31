package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.util.Pair;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class LayoutManager {

    private static LayoutManager instance;

    private LayoutManager() {
    }

    public static LayoutManager getInstance() {
        if (instance == null) {
            instance = new LayoutManager();
        }
        return instance;
    }

    public Pair<Parent, Object> getFXML(String name) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(LayoutManager.class.getResource(name + ".fxml"));
        loader.setControllerFactory(c -> {
            if (c == DisplayListController.class) {
                return new DisplayListController();
            }
            Object controller;
            try {
                controller = c.getConstructor().newInstance();
                return controller;
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

            return null;
        });
        Parent ret = loader.load();
        Object controller = loader.getController();
        return new Pair<>(ret, controller);
    }
}
