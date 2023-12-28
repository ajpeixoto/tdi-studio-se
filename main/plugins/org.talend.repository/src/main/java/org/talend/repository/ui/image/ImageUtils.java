package org.talend.repository.ui.image;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Resource;
import org.eclipse.swt.graphics.Transform;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

// do as eclipse =>
// https://github.com/eclipse-platform/eclipse.platform.ui/pull/1355/files

// NOTE : if any code change needed .also need to modify in class org.talend.license.gui.ui.v2.LoginLicenseDialogV2
public class ImageUtils {

    private static Logger log = Logger.getLogger(ImageUtils.class);
    public static Image flipImage(Display display, Image srcImage) {
        Image newImage;
        try {
            Rectangle bounds = srcImage.getBounds();
            newImage = new Image(display, bounds.width, bounds.height);

            GC gc = new GC(newImage);
            gc.setAdvanced(true);
            gc.setAntialias(SWT.ON);
            gc.setInterpolation(SWT.HIGH);

            Transform transform = new Transform(display);
            transform.setElements(1, 0, 0, -1, 0, 0);
            transform.translate(0, -bounds.height);
            gc.setTransform(transform);

            gc.drawImage(srcImage, 0, 0, bounds.width, bounds.height, 0, 0, bounds.width, bounds.height);

            gc.dispose();
            transform.dispose();
        } catch (Exception e) {
            log.error("flip image failed : " + e.getMessage());
            return srcImage;
        }

        return newImage;
    }

    public static boolean isSonoma() {
        return Platform.OS_MACOSX.equals(Platform.getOS()) && System.getProperty("os.version", "0").startsWith("14");
    }

    public static void addResourceDisposeListener(final Control parent, final Resource res) {
        if (parent != null) {
            parent.addDisposeListener(new DisposeListener() {

                public void widgetDisposed(DisposeEvent e) {
                    if (res != null && !res.isDisposed()) {
                        res.dispose();
                    }
                    parent.removeDisposeListener(this);
                }
            });
        }

    }

}
