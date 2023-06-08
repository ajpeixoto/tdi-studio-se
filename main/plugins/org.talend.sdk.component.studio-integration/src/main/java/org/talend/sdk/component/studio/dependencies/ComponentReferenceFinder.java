package org.talend.sdk.component.studio.dependencies;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.talend.core.model.process.IElementParameter;
import org.talend.core.model.process.INode;
import org.talend.sdk.component.server.front.model.ComponentDetail;
import org.talend.sdk.component.studio.Lookups;
import org.talend.sdk.component.studio.model.parameter.ListPropertyNode;
import org.talend.sdk.component.studio.model.parameter.Metadatas;
import org.talend.sdk.component.studio.model.parameter.PropertyNode;

public interface ComponentReferenceFinder {

    String DEPENDENCIES_CONNECTOR_META = "dependencies::connector";

    Stream<ComponentReference> find(final PropertyNode property, final INode node);

    static ComponentReferenceFinder getFinder(final PropertyNode property) {
        if (property instanceof ListPropertyNode) {
            return new ListPropertyReferenceFinder();
        }
        return new PropertyReferenceFinder();
    }

    class PropertyReferenceFinder implements ComponentReferenceFinder {
        @Override
        public Stream<ComponentReference> find(PropertyNode property, INode node) {
            final List<PropertyNode> children = property.getChildren();
            String mavenReferences = null, name = null, family = null;
            for (PropertyNode child : children) {
                final String metaValue = child.getProperty().getMetadata().get(DEPENDENCIES_CONNECTOR_META);
                if (metaValue == null) {
                    continue;
                }

                final String path = child.getProperty().getPath();
                final IElementParameter elementParameter = node.getElementParameter(path);
                switch (metaValue) {
                    case "mavenReference":
                        mavenReferences = (String) elementParameter.getValue();
                        break;
                    case "family":
                        family = (String) elementParameter.getValue();
                        break;
                    case "name":
                        name = (String) elementParameter.getValue();
                        break;
                }
            }
            return Stream.of(new ComponentReference(family, name, mavenReferences));
        }
    }

    class ListPropertyReferenceFinder implements ComponentReferenceFinder {
        @Override
        public Stream<ComponentReference> find(PropertyNode property, INode node) {
            if (!(property instanceof ListPropertyNode)) {
                return Stream.empty();
            }
            final String path = property.getProperty().getPath();
            final IElementParameter elementParameter = node.getElementParameter(path);
            final Object values = elementParameter.getValue();
            if (!(values instanceof List)) {
                return Stream.empty();
            }

            final List<ComponentReference> details = new ArrayList<>();
            for (Object value : (List) values) {
                if (!(value instanceof Map)) {
                    continue;
                }

                Map map = (Map) value;
                String mavenReferences = null, family = null, name = null;
                for (PropertyNode column : ((ListPropertyNode) property).getColumns(Metadatas.MAIN_FORM)) {
                    final String metaValue = column.getProperty().getMetadata().get(DEPENDENCIES_CONNECTOR_META);
                    if (metaValue == null) {
                        continue;
                    }

                    switch (metaValue) {
                        case "mavenReference":
                            mavenReferences = (String) map.get(column.getProperty().getPath());
                            break;
                        case "name":
                            name = (String) map.get(column.getProperty().getPath());
                            break;
                        case "family":
                            family = (String) map.get(column.getProperty().getPath());
                            break;
                    }
                }

                details.add(new ComponentReference(family, name, mavenReferences));
            }
            return details.stream();
        }
    }
}
