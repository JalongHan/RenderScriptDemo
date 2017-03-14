/*
 * Copyright (C) 2009 The Android Open Source Project
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
package android.renderScript.demo;

import android.annotation.TargetApi;
import android.content.Context;
import android.renderscript.RSSurfaceView;
import android.renderscript.RenderScriptGL;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

class WaterWaveView extends RSSurfaceView {
    private static final String TAG = "WaterWaveView";
	private WaterWaveRS mRender;
    private RenderScriptGL mRS;
    private int mWidth;
    private int mHeight;
    
    @TargetApi(11)
	public WaterWaveView(Context context) {
        super(context);
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    @TargetApi(11)
	private void ensureRenderScript(int width ,int height){
    	Log.d(TAG,"__Width = "+width+",Height = "+height);
    	if(mRender == null){
            mRender = new WaterWaveRS(width, height);
            mRender.init(mRS, getResources());
    	}else{
//    		mRender.resize(width, height);
    	}
    	mRender.start();
    }
    
    @TargetApi(11)
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		super.surfaceCreated(holder);
		if(mRS == null){
			mRS = createRenderScriptGL(new RenderScriptGL.SurfaceConfig());
		}
		setRenderScriptGL(mRS);
	}

	@TargetApi(11)
	public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        super.surfaceChanged(holder, format, w, h);
        ensureRenderScript(w, h);
    }

    @TargetApi(11)
	@Override
    protected void onDetachedFromWindow() {
        // Handle the system event and clean up
        mRender = null;
        if (mRS != null) {
            mRS = null;
            destroyRenderScriptGL();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        /*switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            //case MotionEvent.ACTION_MOVE:
                mRender.addDrop(event.getX(), event.getY());
                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    // Ignore
                }
                break;
        }*/
    	Log.d(TAG,"_____MotionEvent action = "+event.getAction());
        if(event.getAction() == MotionEvent.ACTION_MOVE){
        	
        	mRender.addDrop(event.getX(),event.getY());
        	try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                // Ignore
            }
//        	return true;
        }
        return true;
    }
}