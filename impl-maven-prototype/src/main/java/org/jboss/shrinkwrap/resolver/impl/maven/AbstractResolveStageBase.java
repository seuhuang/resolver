/*
 * JBoss, Home of Professional Open Source
 * Copyright 2012, Red Hat Middleware LLC, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.shrinkwrap.resolver.impl.maven;

import org.jboss.shrinkwrap.resolver.api.maven.MavenFormatStage;
import org.jboss.shrinkwrap.resolver.api.maven.MavenResolveStageBase;
import org.jboss.shrinkwrap.resolver.api.maven.MavenStrategyStage;
import org.jboss.shrinkwrap.resolver.api.maven.dependency.DependencyDeclaration;
import org.jboss.shrinkwrap.resolver.api.maven.dependency.DependencyDeclarationBuilderBase;
import org.jboss.shrinkwrap.resolver.api.maven.dependency.exclusion.DependencyExclusionBuilderBase;
import org.jboss.shrinkwrap.resolver.impl.maven.util.Validate;

/**
 * Base implementation providing support for operations defined by {@link MavenResolveStageBase}
 *
 * @author <a href="mailto:kpiwko@redhat.com">Karel Piwko</a>
 *
 * @param <COORDINATEBUILDERTYPE>
 * @param <EXCLUSIONBUILDERTYPE>
 * @param <RESOLVESTAGETYPE>
 */
abstract class AbstractResolveStageBase<COORDINATEBUILDERTYPE extends DependencyDeclarationBuilderBase<COORDINATEBUILDERTYPE, EXCLUSIONBUILDERTYPE, RESOLVESTAGETYPE, MavenStrategyStage, MavenFormatStage>, EXCLUSIONBUILDERTYPE extends DependencyExclusionBuilderBase<EXCLUSIONBUILDERTYPE>, RESOLVESTAGETYPE extends MavenResolveStageBase<COORDINATEBUILDERTYPE, EXCLUSIONBUILDERTYPE, RESOLVESTAGETYPE, MavenStrategyStage, MavenFormatStage>>
    implements
    MavenResolveStageBase<COORDINATEBUILDERTYPE, EXCLUSIONBUILDERTYPE, RESOLVESTAGETYPE, MavenStrategyStage, MavenFormatStage>,
    MavenWorkingSessionContainer {

    protected MavenWorkingSession session;

    public AbstractResolveStageBase(final MavenWorkingSession session) {
        Validate.stateNotNull(session, "Maven Working session must not be null");
        this.session = session;
    }

    protected MavenStrategyStage resolve(final COORDINATEBUILDERTYPE builder, final String coordinate)
        throws IllegalArgumentException {
        this.clearDependenciesFromSession();
        return builder.and(coordinate).resolve();
    }

    private void clearDependenciesFromSession() {
        this.session.getDependencies().clear();
    }

    protected MavenStrategyStage resolve(final COORDINATEBUILDERTYPE builder, final String... coordinates)
        throws IllegalArgumentException {
        Validate.notNullAndNoNullValues(coordinates, "Coordinates for resolution must not be null nor empty.");
        this.clearDependenciesFromSession();
        for (final String coords : coordinates) {
            builder.and(coords);
        }
        return builder.resolve();
    }

    protected MavenStrategyStage resolve(final COORDINATEBUILDERTYPE builder, DependencyDeclaration coordinate)
        throws IllegalArgumentException {
        Validate.notNull(coordinate, "Coordinates for resolution must not be null nor empty.");
        this.clearDependenciesFromSession();
        builder.and(coordinate.getAddress());
        return builder.resolve();
    }

    protected MavenStrategyStage resolve(final COORDINATEBUILDERTYPE builder, DependencyDeclaration... coordinates)
        throws IllegalArgumentException {
        Validate.notNullAndNoNullValues(coordinates, "Coordinates for resolution must not be null nor empty.");
        this.clearDependenciesFromSession();
        for (final DependencyDeclaration coords : coordinates) {
            builder.and(coords.getAddress());
        }
        return builder.resolve();
    }

    @Override
    public MavenWorkingSession getMavenWorkingSession() {
        return session;
    }

}