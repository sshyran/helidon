/*
 * Copyright (c) 2021, 2022 Oracle and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package io.helidon.microprofile.lra;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ResourceInfo;

import io.helidon.common.context.Contexts;
import io.helidon.lra.coordinator.client.PropagatedHeaders;

class NoopAnnotationHandler implements AnnotationHandler {

    private final ParticipantService participantService;

    NoopAnnotationHandler(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @Override
    public void handleJaxRsBefore(ContainerRequestContext requestContext, ResourceInfo resourceInfo) {
        // Custom headers propagation
        PropagatedHeaders propagatedHeaders = participantService.prepareCustomHeaderPropagation(requestContext.getHeaders());
        String key = PropagatedHeaders.class.getName();
        requestContext.setProperty(key, propagatedHeaders);
        Contexts.context()
                .ifPresent(context -> context.register(key, propagatedHeaders));
    }

    @Override
    public void handleJaxRsAfter(final ContainerRequestContext requestContext,
                                 ContainerResponseContext responseContext,
                                 ResourceInfo resourceInfo) {
    }
}