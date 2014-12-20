package com.example.hellolibgdx;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class MyGame implements ApplicationListener {

	Texture animationTexture;
	TextureRegion temp[][];
	TextureRegion walkRegion[];
	TextureRegion keyFrameRegion;
	
	
	public static final int ROWS = 5;//这个图片的行数
	public static final int COLS = 6;//这个图片的列数
	
	SpriteBatch batch;
	
	
	Animation walkAnimation;
	
	float stateTime;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		
		animationTexture = new Texture(Gdx.files.internal("data/animation.png"));
		
		temp = TextureRegion.split(animationTexture, animationTexture.getWidth()/COLS, animationTexture.getHeight()/ROWS);
		
		walkRegion = new TextureRegion[ROWS*COLS];
		
		int i;
		int j;
		int index = 0;
		for(i = 0 ; i < ROWS ; ++i){
			for(j = 0 ; j < COLS ; ++j){
				walkRegion[index++] = temp[i][j];
			}
		}
		
		walkAnimation = new Animation(0.025f, walkRegion);
		/**
		 * normal:正常模式.从头到尾播一遍
		 * REVERSED:倒播模式。从尾到头播放一次
		 * LOOP:正常的循环方式。不断地从头到尾的循环
		 * LOOP_REVERSED: 倒播的循环方式.不断地从尾到头的循环
		 * LOOP_PINGPONG: 也是不断循环.但是是向前播几帧然后向后播几帧
		 */
		walkAnimation.setPlayMode(walkAnimation.LOOP);//设置这个动画的播放模式
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}
	
	
	
	
	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);// 设置背景为白色
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);// 清屏
		
		stateTime += Gdx.graphics.getDeltaTime();//获取一个状态下的持续时间
		
//		keyFrameRegion = walkAnimation.getKeyFrame(stateTime, false);//获取当前帧
		keyFrameRegion = walkAnimation.getKeyFrame(stateTime, true);//当playMode为LOOP_REVERSED的时候,这个时候需要设成true
		
		batch.begin();
		
		batch.draw(keyFrameRegion,240,400);//把当前帧画出来
		
		batch.end();
		
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

}
