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

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.osgi.framework.Bundle;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.exception.SystemException;
import org.talend.commons.ui.runtime.image.ImageProvider;


public class ComponentIconLoadingFromBundle extends ComponentIconLoading {

    private String bundleId;

    private String relativePath;

    public ComponentIconLoadingFromBundle(Map<String, ImageDescriptor> componentsImageRegistry, String bundleId, File folder) {
        super(componentsImageRegistry, folder);
        this.bundleId = bundleId;
        Bundle bundle = Platform.getBundle(bundleId);
        try {
            relativePath = new File(FileLocator.resolve(bundle.getEntry("/")).toURI()).getCanonicalFile().toURI()
                    .relativize(folder.toURI()).toString();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Override
    protected ImageDescriptor getImage(String name) {
        try {
            String relativePath = getRelativePath() + name;

            URL[] entries = FileLocator.findEntries(Platform.getBundle(getBundleId()), new Path(relativePath));
            if (entries == null || entries.length <= 0) {
                return ImageProvider.getImageDesc(EComponentsImage.DEFAULT_COMPONENT_ICON);
            } else {
                URL path = new URL("platform:/plugin/" + getBundleId() + "/" + relativePath);
                return ImageDescriptor.createFromURL(path);
            }
        } catch (Throwable e) {
            ExceptionHandler.process(new SystemException("Cannot load component icon " + name, e)); //$NON-NLS-1$
            return ImageProvider.getImageDesc(EComponentsImage.DEFAULT_COMPONENT_ICON);
        }
    }

    private String getRelativePath() {
        return this.relativePath;
    }

    private String getBundleId() {
        return this.bundleId;
    }

}
