import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;



public class UIUtils {
     
    public static Context getContext() {
         return MyApplication.getApplication();
    }
	  
    public static Thread getMainThread() {
         return MyApplication.getMainThread();
    }
 
    public static long getMainThreadId() {
         return MyApplication.getMainThreadId();
    }
 
     /** dipת��px */
    public static int dip2px(int dip) {
         final float scale = getContext().getResources().getDisplayMetrics().density;
         return (int) (dip * scale + 0.5f); 37     }

     /** pxzת��dip */
     public static int px2dip(int px) {
         final float scale = getContext().getResources().getDisplayMetrics().density;
         return (int) (px / scale + 0.5f);
     } 
     /** ��ȡ���̵߳�handler */
     public static Handler getHandler() {
         return MyApplication.getMainThreadHandler(); 48     }
 
     /** ��ʱ�����߳�ִ��runnable */
     public static boolean postDelayed(Runnable runnable, long delayMillis) {
         return getHandler().postDelayed(runnable, delayMillis);
     }
       /** �����߳�ִ��runnable */       
	public static boolean post(Runnable runnable) {
		return getHandler().post(runnable);
     }
  
     /** �����߳�looper�����Ƴ�runnable */
     public static void removeCallbacks(Runnable runnable) { 
		getHandler().removeCallbacks(runnable);
     }
 
      public static View inflate(int resId){
         return LayoutInflater.from(getContext()).inflate(resId,null);
     }
 
     /** ��ȡ��Դ */
     public static Resources getResources() {
         
         return getContext().getResources();
     }
  
     /** ��ȡ���� */
     public static String getString(int resId) {
         return getResources().getString(resId);
     }
 
     /** ��ȡ�������� */
     public static String[] getStringArray(int resId) {
         return getResources().getStringArray(resId);
     }
 
     /** ��ȡdimen */
      public static int getDimens(int resId) {
         return getResources().getDimensionPixelSize(resId);
     }
  
     /** ��ȡdrawable */
     public static Drawable getDrawable(int resId) {
         return getResources().getDrawable(resId);
     }
  
     /** ��ȡ��ɫ */
      public static int getColor(int resId) {
         return getResources().getColor(resId);
     }
 
     /** ��ȡ��ɫѡ���� */
     public static ColorStateList getColorStateList(int resId) {
         return getResources().getColorStateList(resId);
     }
     //�жϵ�ǰ���߳��ǲ��������߳� 
     public static boolean isRunInMainThread() {
         return android.os.Process.myTid() == getMainThreadId();
     }
     
     public static void runInMainThread(Runnable runnable) {
         if (isRunInMainThread()) {
             runnable.run();
         } else {
             post(runnable);
         }
     }
 
     public static void startActivity(Intent intent){
        BaseActivity activity = BaseActivity.getForegroundActivity();
        if(activity != null){
            activity.startActivity(intent);
        }else{
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getContext().startActivity(intent);
        }
     }
 
     /** ��toast�ļ��׷�װ���̰߳�ȫ�������ڷ�UI�̵߳��á� */
     public static void showToastSafe(final int resId) {
         showToastSafe(getString(resId));
     }
 
     /** ��toast�ļ��׷�װ���̰߳�ȫ�������ڷ�UI�̵߳��á� */
     public static void showToastSafe(final String str) {
         if (isRunInMainThread()) {
             showToast(str);
         } else {
             post(new Runnable() {
                 @Override
                 public void run() {
                     showToast(str);
                 }
             });
         }
     }
 
     private static void showToast(String str) {
        BaseActivity frontActivity = BaseActivity.getForegroundActivity();
        if (frontActivity != null) {
            Toast.makeText(frontActivity, str, Toast.LENGTH_LONG).show();
        }
     }
 }