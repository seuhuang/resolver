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
package org.jboss.shrinkwrap.resolver.impl.maven.filter;

import java.util.List;

import org.jboss.shrinkwrap.resolver.api.maven.MavenResolutionFilter;
import org.jboss.shrinkwrap.resolver.api.maven.coordinate.MavenDependency;

/**
 * SPI for all {@link MavenResolutionFilter} implementations used by the Maven Resolver System
 *
 * @author <a href="mailto:alr@jboss.org">Andrew Lee Rubinger</a>
 */
public interface MavenResolutionFilterInternalView extends MavenResolutionFilter {

    /**
     * Sets the {@link MavenDependency} elements defined by the user (does not include those obtained via
     * transitive resolution), returning <code>this</code> reference.
     *
     * @param dependencies
     * @return
     */
    MavenResolutionFilterInternalView setDefinedDependencies(final List<MavenDependency> dependencies);

    /**
     * Sets the {@link MavenDependency} elements in <code>dependencyManagement</code> (ie. versioning metadata)
     * defined by the user, returning <code>this</code> reference.
     *
     * @param dependencies
     * @return
     */
    MavenResolutionFilterInternalView setDefinedDependencyManagement(
        final List<MavenDependency> dependencyManagement);
}