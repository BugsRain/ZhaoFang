package cn.ichengxi.fang.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

/**
 * author：created by Snail.江
 * time: 11/7/2016 12:23
 * email：409962004@qq.com
 * TODO: 阻尼效果的滚动试图
 */
public class DampingScrollView extends ScrollView {

    private View inner;// 子View

    private float y;// 点击时y坐标
    private float y1;// 点击时y坐标
    private float y2;// 抬起时y坐标

    private Rect normal = new Rect();// 矩形(这里只是个形式，只是用于判断是否需要动画.)

    private boolean isCount = false;// 是否开始计算

    private boolean isMoveing = false;// 是否开始移动.

    private int initTop, initbottom;// 初始高度
    private int top, bottom;// 拖动时时高度。

    private int mTouchSlop;

    public DampingScrollView(Context context) {
        super(context);
    }

    public DampingScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DampingScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    /***
     * 根据 XML 生成视图工作完成.该函数在生成视图的最后调用，在所有子视图添加完之后. 即使子类覆盖了 onFinishInflate
     * 方法，也应该调用父类的方法，使该方法得以执行.
     */
    @Override
    protected void onFinishInflate() {
        if (getChildCount() > 0) {
            inner = getChildAt(0);
        }
    }


    int dy;
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        int action = ev.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            dy = (int) ev.getY();
        } else if (action == MotionEvent.ACTION_MOVE) {
            dy -= ev.getY();
            if(Math.abs(dy) > mTouchSlop){
                return true;
            }
        } else {
            dy = 0;
        }

        return super.onInterceptTouchEvent(ev);
    }

    /**
     * touch 事件处理
     **/
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (inner != null) {
            commOnTouchEvent(ev);
        }
        return super.onTouchEvent(ev);
    }

    /***
     * 触摸事件
     *
     * @param ev
     */
    public void commOnTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                y1 = ev.getY();
                top = initTop;
                bottom = initbottom;
                break;

            case MotionEvent.ACTION_UP:
                y2 = ev.getY();

                //y2-y1>0表示下拉动作
                if (isMoveing && (y2 - y1 > 0)) {
                    //这里可以实现下拉监听回调
                }

                isMoveing = false;
                // 手指松开.
                if (isNeedAnimation()) {
                    animation();
                }
                break;
            /***
             * 排除出第一次移动计算，因为第一次无法得知y坐标， 在MotionEvent.ACTION_DOWN中获取不到，
             * 因为此时是ScrollView的touch事件传递到到了ListView的子item上面.所以从第二次计算开始.
             * 然而我们也要进行初始化，就是第一次移动的时候让滑动距离归0. 之后记录准确了就正常执行.
             */
            case MotionEvent.ACTION_MOVE:

                final float preY = y;// 按下时的y坐标
                float nowY = ev.getY();// 时时y坐标
                int deltaY = (int) (nowY - preY);// 滑动距离
                if (!isCount) {
                    deltaY = 0; // 在这里要归0.
                }

                if (deltaY < 0 && top <= initTop)
                    return;

                // 当滚动到最上或者最下时就不会再滚动，这时移动布局
                isNeedMove();

                if (isMoveing) {
                    // 初始化头部矩形
                    if (normal.isEmpty()) {
                        // 保存正常的布局位置
                        normal.set(inner.getLeft(), inner.getTop(), inner.getRight(), inner.getBottom());
                    }

                    // 移动布局
                    inner.layout(inner.getLeft(), inner.getTop() + deltaY / 3, inner.getRight(), inner.getBottom() + deltaY / 3);

                    top += (deltaY / 6);
                    bottom += (deltaY / 6);
                }

                isCount = true;
                y = nowY;
                break;

            default:
                break;

        }
    }

    /***
     * 回缩动画
     */
    public void animation() {
        // 开启移动动画
        TranslateAnimation ta = new TranslateAnimation(0, 0, inner.getTop(),
                normal.top);
        ta.setDuration(200);
        inner.startAnimation(ta);
        // 设置回到正常的布局位置
        inner.layout(normal.left, normal.top, normal.right, normal.bottom);
        normal.setEmpty();

        isCount = false;
        y = 0;// 手指松开要归0.

    }

    // 是否需要开启动画
    public boolean isNeedAnimation() {
        return !normal.isEmpty();
    }

    /***
     * 是否需要移动布局 inner.getMeasuredHeight():获取的是控件的总高度
     * <p>
     * getHeight()：获取的是屏幕的高度
     *
     * @return
     */
    public void isNeedMove() {
        int offset = inner.getMeasuredHeight() - getHeight();
        int scrollY = getScrollY();
        // 0是顶部，后面那个是底部
//		if (scrollY == 0 || scrollY == offset) {
//			isMoveing = true;
//		}
        if (scrollY == 0) {
            isMoveing = true;
        }
    }
}
