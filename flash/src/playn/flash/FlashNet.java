/**
 * Copyright 2010 The PlayN Authors
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
package playn.flash;

import playn.core.NetImpl;
import playn.core.PlayN;
import playn.core.util.Callback;

public class FlashNet extends NetImpl {

  public FlashNet(FlashPlatform platform) {
    super(platform);
  }

  @Override
  public void get(String url, Callback<String> callback) {
    PlayN.assets().getText(url, callback);
  }

  @Override
  public native void post(String url, String data, final Callback<String> callback) /*-{
      var loader  = new flash.net.URLLoader();
      var request = new flash.net.URLRequest(url);

      request.method = flash.net.URLRequestMethod.POST;
      request.data = data;
      request.contentType = "text/xml; charset=UTF-8";
      loader.addEventListener(Event.COMPLETE, function() {
        callback.@playn.core.util.Callback::onSuccess(Ljava/lang/Object;)(loader.data);
      });
      loader.load(request);
  }-*/;
}
