/**
 * Copyright 2011 The PlayN Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package playn.android;

import android.graphics.Bitmap;

import playn.core.Canvas;
import playn.core.CanvasImage;
import playn.core.Image;
import playn.core.gl.Scale;

class AndroidCanvasImage extends AndroidImage implements CanvasImage {

  private final AndroidCanvas canvas;

  AndroidCanvasImage(AndroidGraphics gfx, float width, float height, Scale scale) {
    super(gfx.ctx, Bitmap.createBitmap(scale.scaledCeil(width), scale.scaledCeil(height),
                                       gfx.preferredBitmapConfig), scale);
    this.canvas = new AndroidCanvas(bitmap, width, height);
    this.canvas.scale(scale.factor, scale.factor);
  }

  @Override
  public Canvas canvas() {
    return canvas;
  }

  @Override
  public Image snapshot() {
    return new AndroidImage(ctx, bitmap.copy(bitmap.getConfig(), false), scale);
  }

  @Override
  public int ensureTexture() {
    // if we have a canvas, and it's dirty, force the recreation of our texture which will obtain
    // the latest canvas data
    if (canvas.dirty()) {
      canvas.clearDirty();
      clearTexture();
    }
    return super.ensureTexture();
  }

  @Override
  public void setRgb(int startX, int startY, int width, int height, int[] rgbArray, int offset,
                     int scanSize) {
    bitmap.setPixels(rgbArray, offset, scanSize, startX, startY, width, height);
  }
}
