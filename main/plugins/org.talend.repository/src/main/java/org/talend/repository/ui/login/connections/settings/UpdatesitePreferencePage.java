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
package org.talend.repository.ui.login.connections.settings;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.ui.runtime.exception.ExceptionMessageDialog;
import org.talend.commons.ui.runtime.image.EImage;
import org.talend.commons.ui.runtime.image.ImageProvider;
import org.talend.core.service.IStudioLiteP2Service;
import org.talend.core.service.IStudioLiteP2Service.UpdateSiteConfig;
import org.talend.core.ui.branding.IBrandingService;
import org.talend.repository.i18n.Messages;

/**
 * DOC cmeng class global comment. Detailled comment
 */
public class UpdatesitePreferencePage extends PreferencePage {

    IStudioLiteP2Service p2Service = IStudioLiteP2Service.get();

    private Text releaseUriText;

    private Text updateUriText;

    private Composite warningPanel;

    @Override
    protected Control createContents(Composite parent) {
        Composite panel = new Composite(parent, SWT.NONE);
        GridData data = new GridData(GridData.FILL_BOTH);
        data.horizontalSpan = 2;
        panel.setLayoutData(data);

        FillLayout layout = new FillLayout();
        layout.marginHeight = 5;
        layout.marginWidth = 10;
        panel.setLayout(layout);

        Composite composite = new Composite(panel, SWT.NONE);
        GridLayout compLayout = new GridLayout(2, false);
        compLayout.marginHeight = 0;
        compLayout.marginWidth = 0;
        composite.setLayout(compLayout);

        Label releaseLabel = new Label(composite, SWT.NONE);
        releaseLabel.setText(Messages.getString("UpdatesitePreferencePage.baseUrl"));
        GridData gd = new GridData(SWT.LEFT, SWT.CENTER, false, false);
        releaseLabel.setLayoutData(gd);

        releaseUriText = new Text(composite, SWT.BORDER);
        gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
        releaseUriText.setLayoutData(gd);

        Label updateLabel = new Label(composite, SWT.NONE);
        updateLabel.setText(Messages.getString("UpdatesitePreferencePage.updateUrl"));
        gd = new GridData(SWT.LEFT, SWT.CENTER, false, false);
        updateLabel.setLayoutData(gd);

        updateUriText = new Text(composite, SWT.BORDER);
        gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
        updateUriText.setLayoutData(gd);

        Label placeHolder = new Label(composite, SWT.None);
        gd = new GridData(SWT.LEFT, SWT.TOP, false, false);
        placeHolder.setLayoutData(gd);

        warningPanel = new Composite(composite, SWT.None);
        gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
        warningPanel.setLayoutData(gd);
        warningPanel.setLayout(new GridLayout(2, false));

        Label warningImg = new Label(warningPanel, SWT.None);
        warningImg.setImage(ImageProvider.getImage(EImage.WARNING_ICON));
        Label warningDesc = new Label(warningPanel, SWT.None);
        warningDesc.setText(Messages.getString("UpdatesitePreferencePage.warn.onPremUpdateSetup"));
        warningDesc.setFont(JFaceResources.getFontRegistry().getBold(JFaceResources.DEFAULT_FONT));

        init();
        addListener();
        return parent;
    }

    private void init() {
        try {
            warningPanel.setVisible(false);
            IProgressMonitor monitor = new NullProgressMonitor();
            UpdateSiteConfig config = p2Service.getUpdateSiteConfig(new NullProgressMonitor());
            URI release = config.getRelease(monitor);
            releaseUriText.setText(release == null ? "" : release.toString());
            releaseUriText.setEditable(config.isReleaseEditable());
            if (!config.isReleaseEditable()) {
                releaseUriText.setToolTipText(Messages.getString("UpdatesitePreferencePage.tooltip.cantEdit"));
            }
            Collection<URI> updates = config.getUpdates(monitor);
            StringBuilder updateStr = new StringBuilder();
            if (updates != null && !updates.isEmpty()) {
                for (String uri : updates.stream().map(uri -> uri.toString()).collect(Collectors.toList())) {
                    if (0 < updateStr.length()) {
                        updateStr.append(",");
                    }
                    updateStr.append(uri);
                }
            }
            updateUriText.setText(updateStr.toString());
            updateUriText.setEditable(config.isUpdateEditable());
            if (!config.isUpdateEditable()) {
                updateUriText.setToolTipText(Messages.getString("UpdatesitePreferencePage.tooltip.cantEdit"));
            }
        } catch (Exception e) {
            ExceptionHandler.process(e);
        }
    }

    private void addListener() {
        releaseUriText.addModifyListener(new ModifyListener() {

            @Override
            public void modifyText(ModifyEvent e) {
                refresh();
            }
        });
        updateUriText.addModifyListener(new ModifyListener() {

            @Override
            public void modifyText(ModifyEvent e) {
                refresh();
            }
        });
    }

    @Override
    public boolean performOk() {
        if (this.isControlCreated()) {
            try {
                IProgressMonitor monitor = new NullProgressMonitor();
                UpdateSiteConfig config = p2Service.getUpdateSiteConfig(new NullProgressMonitor());
                if (config.isReleaseEditable()) {
                    String release = releaseUriText.getText();
                    config.setRelease(monitor, StringUtils.isBlank(release) ? null : p2Service.toURI(release.trim()));
                }
                if (config.isUpdateEditable()) {
                    String update = updateUriText.getText();
                    if (StringUtils.isBlank(update)) {
                        config.setUpdates(monitor, null);
                    } else {
                        Collection<URI> updates = new ArrayList<>();
                        String[] splits = update.split(",");
                        for (String split : splits) {
                            updates.add(p2Service.toURI(split.trim()));
                        }
                        config.setUpdates(monitor, updates);
                    }
                }
            } catch (Exception e) {
                ExceptionMessageDialog.openError(null, Messages.getString("UpdatesitePreferencePage.err.title"),
                        e.getLocalizedMessage(), e);
            }
        }
        return super.performOk();
    }

    @Override
    protected void performDefaults() {
        if (this.isControlCreated()) {
            try {
                UpdateSiteConfig config = p2Service.getUpdateSiteConfig(new NullProgressMonitor());
                if (config.isReleaseEditable()) {
                    NullProgressMonitor monitor = new NullProgressMonitor();
                    config.resetToDefault(monitor);
                    URI release = config.getRelease(monitor);
                    releaseUriText.setText(release == null ? "" : release.toString());
                    Collection<URI> updates = config.getUpdates(monitor);
                    StringBuilder updateStr = new StringBuilder();
                    if (updates != null && !updates.isEmpty()) {
                        updateStr.append(
                                String.join(",", updates.stream().map(uri -> uri.toString()).collect(Collectors.toList())));
                    }
                    updateUriText.setText(updateStr.toString());
                } else {
                    // normally it should be a dead code
                    throw new Exception("Can't reset to default values. since they are readonly.");
                }
            } catch (Exception e) {
                ExceptionMessageDialog.openError(null, Messages.getString("UpdatesitePreferencePage.err.title"),
                        e.getLocalizedMessage(), e);
            }
        }
        super.performDefaults();
    }

    private void refresh() {
        this.updateApplyButton();
        this.getContainer().updateButtons();
    }

    private boolean validate() {
        this.setErrorMessage(null);
        checkUpdateUriSettingsForOnPrem();
        String releaseUriStr = releaseUriText.getText().trim();
        if (StringUtils.isBlank(releaseUriStr)) {
            this.setErrorMessage(Messages.getString("UpdatesitePreferencePage.err.baseCantEmpty"));
            return false;
        } else if (StringUtils.equals(releaseUriStr, updateUriText.getText().trim())) {
            this.setErrorMessage(Messages.getString("UpdatesitePreferencePage.err.baseAndUpdateShouldBeDiff"));
            return false;
        } else {
            return true;
        }
    }

    private void checkUpdateUriSettingsForOnPrem() {
        if (IBrandingService.get().isCloudLicense()) {
            return;
        }
        String updateUriStr = updateUriText.getText().trim();
        if (StringUtils.isBlank(updateUriStr)) {
            warningPanel.setVisible(true);
        } else {
            warningPanel.setVisible(false);
        }
    }

    @Override
    public boolean isValid() {
        return super.isValid() && (this.isControlCreated() && validate());
    }

}
