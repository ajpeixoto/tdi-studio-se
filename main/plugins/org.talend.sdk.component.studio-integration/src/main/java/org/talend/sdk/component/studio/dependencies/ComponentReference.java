package org.talend.sdk.component.studio.dependencies;

public class ComponentReference {
    private final String family;

    private final String name;

    private final String mavenReference;

    public ComponentReference(String family, String name, String mavenReference) {
        this.family = family;
        this.name = name;
        this.mavenReference = mavenReference;
    }

    public String getFamily() {
        return family;
    }

    public String getName() {
        return name;
    }

    public String getMavenReference() {
        return mavenReference;
    }
}
