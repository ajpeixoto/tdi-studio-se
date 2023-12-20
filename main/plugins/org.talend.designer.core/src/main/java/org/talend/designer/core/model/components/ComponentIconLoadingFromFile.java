// ============================================================================
//
// Copyright (C) 2006-2023 Talend Inc. - www.talend.com
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
import java.net.URL;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.exception.SystemException;
import org.talend.commons.ui.runtime.image.ImageProvider;


public class ComponentIconLoadingFromFile extends ComponentIconLoading {

    private URL folderUrl;

    public ComponentIconLoadingFromFile(Map<String, ImageDescriptor> componentsImageRegistry, File folder) {
        super(componentsImageRegistry, folder);
        try {
            this.folderUrl = folder.toURI().toURL();
        } catch (Throwable e) {
            ExceptionHandler.process(new SystemException("Cannot load component icon " + folder.getName(), e)); //$NON-NLS-1$
        }
    }

    @Override
    protected ImageDescriptor getImage(String name) {
        try {
            File imageFile = new File(getFolder(), name);
            if (imageFile.exists()) {
                return ImageDescriptor.createFromURL(imageFile.toURI().toURL());
            } else {
                return ImageProvider.getImageDesc(EComponentsImage.DEFAULT_COMPONENT_ICON);
            }
        } catch (Throwable e) {
            ExceptionHandler.process(new SystemException("Cannot load component icon " + name, e)); //$NON-NLS-1$
            return ImageProvider.getImageDesc(EComponentsImage.DEFAULT_COMPONENT_ICON);
        }
    }

}
