package src.components;

import javax.swing.*;
import javax.swing.plaf.metal.MetalSliderUI;
import java.awt.event.MouseEvent;

public class MetalSlider extends MetalSliderUI {

    public TrackListener createTrackListener(JSlider slider) {
        return new TrackListener() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (UIManager.getBoolean("Slider.onlyLeftMouseButtonDrag") && SwingUtilities.isLeftMouseButton(e)) {
                    JSlider slider = (JSlider) e.getComponent();
                    slider.setValue(valueForXPosition(e.getX()));
                    super.mouseDragged(e);
                }
                super.mousePressed(e); // isDragging = true;
            }
        };
    }
}