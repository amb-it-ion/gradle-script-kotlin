/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.script.lang.kotlin.support

import org.gradle.api.internal.ClassPathRegistry
import org.gradle.api.internal.artifacts.dsl.dependencies.DependencyFactory

import org.gradle.internal.classpath.ClassPath

import java.io.File

object KotlinScriptDefinitionProvider {

    val GRADLE_API_NOTATION = DependencyFactory.ClassPathNotation.GRADLE_API.name

    fun selectGradleApiJars(classPathRegistry: ClassPathRegistry): List<File> =
        gradleApi(classPathRegistry).asFiles.filter { includeInClassPath(it.name) }

    fun gradleApi(classPathRegistry: ClassPathRegistry): ClassPath =
        classPathRegistry.getClassPath(GRADLE_API_NOTATION)

    private fun includeInClassPath(name: String) =
        isKotlinJar(name)
            || name.startsWith("ant-")
            || name.startsWith("gradle-")
            || name.startsWith("groovy-all-")
}

// TODO: make the predicate more precise
fun isKotlinJar(name: String): Boolean =
    name.startsWith("kotlin-stdlib-")
        || name.startsWith("kotlin-reflect-")
        || name.startsWith("kotlin-runtime-")
