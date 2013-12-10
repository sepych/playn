/**
 * Copyright 2012 The PlayN Authors
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
package playn.ios;

import cli.MonoTouch.CoreGraphics.CGPath;

import playn.core.Path;

public class IOSPath implements Path {

  CGPath cgPath = new CGPath();

  @Override
  public Path reset() {
    cgPath.Dispose();
    cgPath = new CGPath();
    return this;
  }

  @Override
  public Path close() {
    cgPath.CloseSubpath();
    return this;
  }

  @Override
  public Path moveTo(float x, float y) {
    cgPath.MoveToPoint(x, y);
    return this;
  }

  @Override
  public Path lineTo(float x, float y) {
    cgPath.AddLineToPoint(x, y);
    return this;
  }

  @Override
  public Path quadraticCurveTo(float cpx, float cpy, float x, float y) {
    cgPath.AddQuadCurveToPoint(cpx, cpy, x, y);
    return this;
  }

  @Override
  public Path bezierTo(float c1x, float c1y, float c2x, float c2y, float x, float y) {
    cgPath.AddCurveToPoint(c1x, c1y, c2x, c2y, x, y);
    return this;
  }

  @Override
  protected void finalize() {
    cgPath.Dispose(); // meh
  }
}
