package com.example.hellolibgdx;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class MyGame implements ApplicationListener {

	
	SpriteBatch batch;
	
	
	Animation walkAnimation;//用于实现自定义动画的相应逻辑
	
	Texture walkTexture;
	TextureRegion temp[][];
	TextureRegion walkRegions[];
	TextureRegion keyFrameRegion;
	
	float stateTime;
	
	TextureAtlas atlas;
	TextureRegion bgRegion;//背景图片
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		
		walkTexture = new Texture(Gdx.files.internal("data/animation.png"));
		
		/**
		 * split(Texture texture, int tileWidth, int tileHeight)
		 * 
		 * texture:需要分割的纹理信息
		 * tileWisth: 分割后,每一小块的宽度
		 * tileHeight: 分割后,每一小块的高度
		 */
		temp = TextureRegion.split(walkTexture, walkTexture.getWidth()/6, walkTexture.getHeight()/5);//分割texture
		
		walkRegions = new TextureRegion[5*6];
		
		/**
		 * 把分割后的二维的TextureRegion[][]转化成一维的TextureRegion[]
		 */
		int i;
		int j;
		int index = 0;
		for(i = 0 ; i < 5 ; ++i){
			for(j = 0 ; j < 6 ; ++j){
				walkRegions[index++] = temp[i][j];
			}
		}
		
		
		/**
		 * Animation(float frameDuration, TextureRegion... keyFrames)
		 * frameDuration: 每一帧的持续时间
		 * keyFrames: 用于循环播放的图片集
		 */
		walkAnimation = new Animation(0.025f, walkRegions);
		
		/**
		 * normal:正常模式.从头到尾播一遍
		 * REVERSED:倒播模式。从尾到头播放一次
		 * LOOP:正常的循环方式。不断地从头到尾的循环
		 * LOOP_REVERSED: 倒播的循环方式.不断地从尾到头的循环
		 * LOOP_PINGPONG: 也是不断循环.但是是向前播几帧然后向后播几帧
		 */
		walkAnimation.setPlayMode(walkAnimation.LOOP_REVERSED);
		
		atlas = new TextureAtlas(Gdx.files.internal("data/xiaorenbg.atlas"));
		bgRegion = atlas.findRegion("bg");
		
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
		
		stateTime += Gdx.graphics.getDeltaTime();//获取每一帧的持续时间
		
//		keyFrameRegion = walkAnimation.getKeyFrame(stateTime, false);//获取当前帧
		keyFrameRegion = walkAnimation.getKeyFrame(stateTime, true);//获取当前帧

		
		batch.begin();
		batch.draw(bgRegion, 0, 0, 480, 800);
		batch.draw(keyFrameRegion,240,400);
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
