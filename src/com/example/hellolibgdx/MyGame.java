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
	
	
	Animation walkAnimation;//����ʵ���Զ��嶯������Ӧ�߼�
	
	Texture walkTexture;
	TextureRegion temp[][];
	TextureRegion walkRegions[];
	TextureRegion keyFrameRegion;
	
	float stateTime;
	
	TextureAtlas atlas;
	TextureRegion bgRegion;//����ͼƬ
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		
		walkTexture = new Texture(Gdx.files.internal("data/animation.png"));
		
		/**
		 * split(Texture texture, int tileWidth, int tileHeight)
		 * 
		 * texture:��Ҫ�ָ��������Ϣ
		 * tileWisth: �ָ��,ÿһС��Ŀ��
		 * tileHeight: �ָ��,ÿһС��ĸ߶�
		 */
		temp = TextureRegion.split(walkTexture, walkTexture.getWidth()/6, walkTexture.getHeight()/5);//�ָ�texture
		
		walkRegions = new TextureRegion[5*6];
		
		/**
		 * �ѷָ��Ķ�ά��TextureRegion[][]ת����һά��TextureRegion[]
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
		 * frameDuration: ÿһ֡�ĳ���ʱ��
		 * keyFrames: ����ѭ�����ŵ�ͼƬ��
		 */
		walkAnimation = new Animation(0.025f, walkRegions);
		
		/**
		 * normal:����ģʽ.��ͷ��β��һ��
		 * REVERSED:����ģʽ����β��ͷ����һ��
		 * LOOP:������ѭ����ʽ�����ϵش�ͷ��β��ѭ��
		 * LOOP_REVERSED: ������ѭ����ʽ.���ϵش�β��ͷ��ѭ��
		 * LOOP_PINGPONG: Ҳ�ǲ���ѭ��.��������ǰ����֡Ȼ����󲥼�֡
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
		
		Gdx.gl.glClearColor(1, 1, 1, 1);// ���ñ���Ϊ��ɫ
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);// ����
		
		stateTime += Gdx.graphics.getDeltaTime();//��ȡÿһ֡�ĳ���ʱ��
		
//		keyFrameRegion = walkAnimation.getKeyFrame(stateTime, false);//��ȡ��ǰ֡
		keyFrameRegion = walkAnimation.getKeyFrame(stateTime, true);//��ȡ��ǰ֡

		
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
