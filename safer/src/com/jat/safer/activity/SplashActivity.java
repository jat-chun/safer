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
/**splash�������ã�
 * 1��չʾlogo����߹�˾����
 * 2����ʼ�����ݣ��������ݵ�sd��
 * 3������û�����
 * 4�����ӷ��������Ƿ����µİ汾
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
        
        //�汾��
        TextView tv_splash_version = (TextView) findViewById(R.id.tv_splash_version);
        tv_splash_version.setText("�汾�ţ�"+ViewHelper.getVersion(this));
        
        if(SaferPreference.getBoolean(this, Const.ISUPDATE)){
        	//��½����
            LoginHelper.getInstance(this).loginConnect();
        }else{
        	new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						//�����ui�̣߳������ui����
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
