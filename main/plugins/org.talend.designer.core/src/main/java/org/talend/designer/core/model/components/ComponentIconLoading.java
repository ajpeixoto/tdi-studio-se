// ============================================================================
//
// Copyright (C) 2006-2021 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.designer.core.model.components;

import java.io.File;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.talend.commons.CommonsPlugin;
import org.talend.commons.ui.runtime.image.EImage;
import org.talend.commons.ui.runtime.image.ImageProvider;

/**
 * DOC smallet class global comment. Detailled comment <br/>
 *
 * $Id: talend.epf 1 2006-09-29 17:06:40 +0000 (ven., 29 sept. 2006) nrousseau $
 *
 */
public abstract class ComponentIconLoading {

    private File folder;

    private Map<String, ImageDescriptor> registry;

    /**
     * DOC smallet ComponentIconLoading constructor comment.
     *
     * @param componentsImageRegistry
     *
     * @param folder
     */
    public ComponentIconLoading(Map<String, ImageDescriptor> componentsImageRegistry, File folder) {
        super();
        this.folder = folder;
        this.registry = componentsImageRegistry;
    }

    protected File getFolder() {
        return this.folder;
    }

    public ImageDescriptor getImage32() {
        ImageDescriptor image32 = getImage(ComponentFilesNaming.getInstance().getIcon32FileName(folder.getName()));
        registry.put(folder.getName() + "_32", image32);
        return image32;
    }

    public ImageDescriptor getImage24() {
        ImageDescriptor image24 = null;
        if (!CommonsPlugin.isHeadless()) {
            ImageDescriptor image32 = registry.get(folder.getName() + "_32");
            File file24 = new File(folder, ComponentFilesNaming.getInstance().getIcon24FileName(folder.getName()));
            if (file24.exists()) {
                image24 = getImage(ComponentFilesNaming.getInstance().getIcon24FileName(folder.getName()));
            } else if (image32 != null && image32.getImageData() != null) {
                try {
                    image24 = ImageDescriptor.createFromImageData(image32.getImageData().scaledTo(24, 24));
                } catch (NullPointerException e) {
                    image24 = image32;
                }
            }
        } else {
            image24 = ImageProvider.getImageDesc(EImage.COMPONENT_MISSING);
        }
        registry.put(folder.getName() + "_24", image24);

        return image24;
    }

    public ImageDescriptor getImage16() {
        ImageDescriptor image16 = null;
        if (!CommonsPlugin.isHeadless()) {
            ImageDescriptor image32 = registry.get(folder.getName() + "_32");
            if (image16 == null) {
                File file16 = new File(folder, ComponentFilesNaming.getInstance().getIcon16FileName(folder.getName()));
                if (file16.exists()) {
                    image16 = getImage(ComponentFilesNaming.getInstance().getIcon16FileName(folder.getName()));
                } else if (image32 != null && image32.getImageData() != null) {
                    image16 = ImageDescriptor.createFromImageData(image32.getImageData().scaledTo(16, 16));
                }
            }
        } else {
            image16 = ImageProvider.getImageDesc(EImage.COMPONENT_MISSING);
        }
        registry.put(folder.getName() + "_16", image16);
        return image16;
    }

    abstract protected ImageDescriptor getImage(String name);

}
