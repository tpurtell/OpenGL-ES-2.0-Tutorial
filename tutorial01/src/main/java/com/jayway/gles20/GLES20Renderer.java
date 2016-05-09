/**
 * Copyright 2013 Per-Erik Bergman (per-erik.bergman@jayway.com)
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
package com.jayway.gles20;

import static android.opengl.GLES20.*;

public class GLES20Renderer extends GLRenderer {
    int width, height;
    int frame;

    @Override
    public void onCreate(int width, int height,
            boolean contextLost) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void onDrawFrame(boolean firstDraw) {
        ++frame;

        glDisable(GL_SCISSOR_TEST);
        glClearColor(0f, 0f, 0f, 1f);
        glClearDepthf(0.0f);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        int dim = Math.min(width, height);
        double t = 3 * frame * Math.PI / 180;
        float xc = (float)Math.cos(t) * dim / 4;
        float yc = (float)Math.sin(t) * dim / 4;

        xc += width / 2;
        yc += height / 2;

        glEnable(GL_SCISSOR_TEST);
        glScissor((int)xc - dim / 5, (int)yc - dim / 5, 2 * dim / 5, 2 * dim / 5);

        glClearColor((float)Math.cos(t + Math.PI), (float)Math.cos(t + 5 * Math.PI / 4), (float)Math.sin(t + 2 * Math.PI / 7), 1f);
        glClearDepthf(100f / (frame % 100));
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }
}