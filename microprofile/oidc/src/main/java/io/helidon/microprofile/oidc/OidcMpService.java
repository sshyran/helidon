/*
 * Copyright (c) 2018, 2020 Oracle and/or its affiliates.
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
 */

package io.helidon.microprofile.oidc;

import io.helidon.microprofile.server.spi.MpService;
import io.helidon.microprofile.server.spi.MpServiceContext;
import io.helidon.security.providers.oidc.OidcSupport;

/**
 * Microprofile extension that brings support for Open ID Connect.
 */
public final class OidcMpService implements MpService {
    @Override
    public void configure(MpServiceContext mpServiceContext) {
        // only configure if security is enabled
        if (mpServiceContext.helidonConfig()
                .get("security.enabled")
                .asBoolean()
                .orElse(true)) {

            mpServiceContext.serverRoutingBuilder()
                    .register(OidcSupport.create(mpServiceContext.helidonConfig()));
        }

    }
}