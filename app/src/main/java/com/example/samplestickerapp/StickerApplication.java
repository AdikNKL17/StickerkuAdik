/*
 * Copyright (c) WhatsApp Inc. and its affiliates.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.example.samplestickerapp;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

public class StickerApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        Fresco.initialize(this);
    }
}
