package com.jat.safer.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jat.safer.R;
import com.jat.safer.biz.Const;
import com.jat.safer.biz.LoginHelper;
import com.jat.safer.biz.SaferPreference;
import com.jat.safer.biz.ViewHelper;
/**splash界面作用：
 * 1、展示logo。提高公司形象
 * 2、初始化数据（拷贝数据到sd）
 * 3、提高用户体验
 * 4、连接服务器，是否有新的版本
 * @author jat
 *
 */
public class SplashActivity extends Activity {
	private SplashActivity TAG = SplashActivity.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        
        AlphaAnimation anim = new AlphaAnimation(0f, 1.0f);
        anim.setDuration(2000);
        
        RelativeLayout rl_splash = (RelativeLayout) findViewById(R.id.rl_splash);
        rl_splash.startAnimation(anim);
        
        //版本号
        TextView tv_splash_version = (TextView) findViewById(R.id.tv_splash_version);
        tv_splash_version.setText("版本号："+ViewHelper.getVersion(this));
        
        if(SaferPreference.getBoolean(this, Const.ISUPDATE)){
        	//登陆处理
            LoginHelper.getInstance(this).loginConnect();
        }else{
        	new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						//如果在ui线程，会造成ui阻塞
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					SplashActivity.this.runOnUiThread(new Runnable() {
						@Override
						public void run() {
							Intent intent = new Intent(TAG, MainActivity.class);
							TAG.startActivity(intent);
							TAG.finish();
						}
					});
				}
			}).start();
        	
        }
        
    
    
    }

	@Override
	protected void onDestroy() {
		LoginHelper.getInstance(this).destroy();
		super.onDestroy();
	}
    
    
    
}
