/*
 * Copyright (C) 2015 Sebastian Daschner, sebastian-daschner.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sebastian_daschner.jaxrs_analyzer.builder;

import com.sebastian_daschner.jaxrs_analyzer.model.rest.MethodParameter;
import com.sebastian_daschner.jaxrs_analyzer.model.rest.ParameterType;
import com.sebastian_daschner.jaxrs_analyzer.model.rest.TypeIdentifier;
import com.sebastian_daschner.jaxrs_analyzer.model.results.ClassResult;
import com.sebastian_daschner.jaxrs_analyzer.model.results.MethodResult;

import java.util.Arrays;
import java.util.stream.Stream;

public class ClassResultBuilder {

    private final ClassResult classResult = new ClassResult();

    private ClassResultBuilder() {
        // prevent other instances
    }

    public static ClassResultBuilder withApplicationPath(final String applicationPath) {
        final ClassResultBuilder classResultBuilder = new ClassResultBuilder();
        classResultBuilder.classResult.setApplicationPath(applicationPath);
        return classResultBuilder;
    }

    public static ClassResultBuilder withResourcePath(final String resourcePath) {
        final ClassResultBuilder classResultBuilder = new ClassResultBuilder();
        classResultBuilder.classResult.setResourcePath(resourcePath);
        return classResultBuilder;
    }

    public ClassResultBuilder andMethods(final MethodResult... methodResults) {
        Stream.of(methodResults).forEach(classResult::add);
        return this;
    }

    public ClassResultBuilder andAcceptMediaTypes(final String... mediaType) {
        classResult.getRequestMediaTypes().addAll(Arrays.asList(mediaType));
        return this;
    }

    public ClassResultBuilder andResponseMediaTypes(final String... mediaType) {
        classResult.getResponseMediaTypes().addAll(Arrays.asList(mediaType));
        return this;
    }

    public ClassResultBuilder andMatrixParam(final String name, final String type) {
        andParam(ParameterType.MATRIX, name, type, null);
        return this;
    }

    public ClassResultBuilder andMatrixParam(final String name, final String type, final String defaultValue) {
        andParam(ParameterType.MATRIX, name, type, defaultValue);
        return this;
    }

    public ClassResultBuilder andQueryParam(final String name, final String type) {
        andParam(ParameterType.QUERY, name, type, null);
        return this;
    }

    public ClassResultBuilder andQueryParam(final String name, final String type, final String defaultValue) {
        andParam(ParameterType.QUERY, name, type, defaultValue);
        return this;
    }

    public ClassResultBuilder andPathParam(final String name, final String type) {
        andParam(ParameterType.PATH, name, type, null);
        return this;
    }

    public ClassResultBuilder andPathParam(final String name, final String type, final String defaultValue) {
        andParam(ParameterType.PATH, name, type, defaultValue);
        return this;
    }

    public ClassResultBuilder andCookieParam(final String name, final String type) {
        andParam(ParameterType.COOKIE, name, type, null);
        return this;
    }

    public ClassResultBuilder andCookieParam(final String name, final String type, final String defaultValue) {
        andParam(ParameterType.COOKIE, name, type, defaultValue);
        return this;
    }

    public ClassResultBuilder andHeaderParam(final String name, final String type) {
        andParam(ParameterType.HEADER, name, type, null);
        return this;
    }

    public ClassResultBuilder andHeaderParam(final String name, final String type, final String defaultValue) {
        andParam(ParameterType.HEADER, name, type, defaultValue);
        return this;
    }

    public ClassResultBuilder andFormParam(final String name, final String type) {
        andParam(ParameterType.FORM, name, type, null);
        return this;
    }

    public ClassResultBuilder andFormParam(final String name, final String type, final String defaultValue) {
        andParam(ParameterType.FORM, name, type, defaultValue);
        return this;
    }

    private ClassResultBuilder andParam(final ParameterType parameterType, final String name, final String type, final String defaultValue) {
        final MethodParameter methodParameter = new MethodParameter(TypeIdentifier.ofType(type), parameterType);
        methodParameter.setName(name);
        methodParameter.setDefaultValue(defaultValue);
        classResult.getClassFields().add(methodParameter);
        return this;
    }

    public ClassResult build() {
        return classResult;
    }

}
