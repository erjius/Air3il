/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package air3il.gui.javafx.place;

import air3il.commun.dto.DtoPlace;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Transform;

/**
 *
 * @author antoine
 */
public final class Avion extends Region {

    private Image background;
    private final Canvas canvas = new Canvas();
    private double imgWidth;
    private double imgHeight;
    private double factor = 1.;
    private SelectionModel<DtoPlace> selectionModel;
    private ObservableList<DtoPlace> items = new SimpleListProperty<>();
    private ListChangeListener<DtoPlace> listChangeListener;
    private final ArrayList<Point2D> seats = new ArrayList<>();
    private final TreeMap<Double, TreeMap<Double, Integer>> posMap = new TreeMap<>();

    public Avion() {
        super();
        init();
    }

    public Avion(Image img, List<Point2D> seats) {
        super();
        setData(img, seats);
        init();
    }

    public Avion(String url, List<Point2D> seats) {
        super();
        setData(url, seats);
        init();
    }

    @Override
    public void resize(double width, double height) {
        super.resize(width, height);
        draw();
    }

    public void setData(String url, List<Point2D> pos) {
        setData(new Image(url), pos);
    }

    public void setData(Image background, List<Point2D> pos) {
        this.background = background;
        imgWidth = background.getWidth();
        imgHeight = background.getHeight();
        for (int i = 0, len = pos.size(); i < len; i++) {
            Point2D p = pos.get(i);
            TreeMap<Double, Integer> m = posMap.get(p.getX());
            if (m == null) {
                m = new TreeMap<>();
                posMap.put(p.getX(), m);
            }
            m.put(p.getY(), i);
        }
        seats.addAll(pos);

    }

    private void init() {
        super.getChildren().add(canvas);
        listChangeListener = c -> {
            c.next();
            draw();
        };
        items = FXCollections.observableArrayList();
        selectionModel = new SingleSelectionModel<DtoPlace>() {
            @Override
            protected DtoPlace getModelItem(int index) {
                if (index < 0) {
                    return null;
                }
                return getItems().get(index);
            }

            @Override
            protected int getItemCount() {
                return getItems().size();
            }
        };
        selectionModel.selectedItemProperty().addListener((ObservableValue<? extends DtoPlace> observable, DtoPlace oldValue, DtoPlace newValue) -> {
            draw();
        });
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED,
                (MouseEvent event) -> {
                    double x = event.getX() / factor;
                    double y = event.getY() / factor;
                    if (x < posMap.firstKey()) {
                        return;
                    }
                    TreeMap<Double, Integer> mapY = posMap.floorEntry(x).getValue();
                    if (y < mapY.firstKey()) {
                        return;
                    }
                    int index = mapY.floorEntry(y).getValue();
                    Point2D p = seats.get(index);
                    if (p.distance(x - 8, y - 8) >= 8) {
                        return;
                    }

                    index = items.indexOf(new DtoPlace(index, "1A", 0));
                    if (index >= 0) {
                        if (event.isControlDown()) {
                            selectionModel.clearSelection(index);
                        } else {
                            selectionModel.select(index);
                        }
                    }
                });
    }

    public void setItems(ObservableList<DtoPlace> items) {
        this.items.removeListener(listChangeListener);
        items.addListener(listChangeListener);
        this.items = items;
        draw();
    }

    public ObservableList<DtoPlace> getItems() {
        return items;
    }

    public SelectionModel<DtoPlace> getSelectionModel() {
        return selectionModel;
    }

    private void draw() {
        if (background == null) {
            return;
        }
        double ratio = background.getWidth() / background.getHeight();
        double width = Math.min(getWidth(), getHeight() * ratio);
        double height = Math.min(getWidth(), getWidth() / ratio);
        factor = width / imgWidth;
        canvas.setWidth(width);
        canvas.setHeight(height);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setTransform(new Affine(Transform.translate(0, 0)));
        gc.clearRect(0, 0, width, height);
        gc.scale(factor, factor);
        gc.drawImage(background, 0, 0);
        gc.setFill(Color.GRAY);
        seats.stream().forEach((p) -> {
            gc.fillOval(p.getX(), p.getY(), 16, 16);
        });
        DtoPlace selected = selectionModel.getSelectedItem();
        gc.setFill(Color.BLUE);
        items.forEach((DtoPlace place) -> {
            Point2D p = seats.get(place.getId());
            if (place == selected) {
                gc.setFill(Color.GREEN);
                gc.fillOval(p.getX() - 1, p.getY() - 1, 18, 18);
                gc.setFill(Color.BLUE);
            } else {
                gc.fillOval(p.getX(), p.getY(), 16, 16);
            }
        });
    }

}
