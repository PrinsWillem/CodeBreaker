package com.codeclan.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import helpers.AppPreferences;
import scenes.*;
import scenes.level1.ConclusionMorseGame;
import scenes.IntroScreen;
import scenes.MorseGame;
import scenes.level2.FindTheCaptain;
import scenes.level3.ConclusionMorseAnswer;
import scenes.level3.MorseAnswer;

public class GameMain extends Game {

	private SpriteBatch batch;

	private LoadingScreen loadingScreen;
	private PreferencesScreen preferencesScreen;
	private MenuScreen menuScreen;
	private MainScreen mainScreen;

	private IntroScreen introMorseGame;
	private MorseGame morseGame;
	private ConclusionMorseGame conclusionMorseGame;

	private FindTheCaptain findTheCaptain;

	private MorseAnswer morseAnswer;
	private ConclusionMorseAnswer conclusionMorseAnswer;

	private EndScreen endScreen;

	public final static int MENU = 0;
	public final static int PREFERENCES = 1;
	public final static int APPLICATION = 2;

	public final static int INTRODUCTIONMORSEGAME = 3;
	public final static int MORSEGAME = 4;
	public final static int CONCLUSIONMORSEGAME = 5;

	public final static int FINDTHECAPTAIN = 6;

	public final static int MORSEANSWER = 7;
	public final static int CONCLUSIONMORSEANSWER = 8;

	public final static int ENDGAME = 9;

	private AppPreferences preferences;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		loadingScreen = new LoadingScreen(this);
		setScreen(loadingScreen);
		preferences = new AppPreferences();
	}

	@Override
	public void render () {
		super.render();
	}
	
	public SpriteBatch getBatch() {
		return this.batch;
	}

	public void changeScreen(int screen){
		switch(screen){
			case MENU:
				if(menuScreen == null) menuScreen = new MenuScreen(this);
				this.setScreen(menuScreen);
				break;
			case PREFERENCES:
				if(preferencesScreen == null) preferencesScreen = new PreferencesScreen(this);
				this.setScreen(preferencesScreen);
				break;
			case APPLICATION:
				if(mainScreen == null) mainScreen = new MainScreen(this);
				this.setScreen(mainScreen);
				break;

			case INTRODUCTIONMORSEGAME:
				if(introMorseGame == null) introMorseGame = new IntroScreen(this);
				this.setScreen(introMorseGame);
			case MORSEGAME:
				if(morseGame == null) morseGame = new MorseGame(this);
				this.setScreen(morseGame);
			case CONCLUSIONMORSEGAME:
				if(conclusionMorseGame == null) conclusionMorseGame = new ConclusionMorseGame(this);
				this.setScreen(conclusionMorseGame);

			case FINDTHECAPTAIN:
				if(findTheCaptain == null) findTheCaptain = new FindTheCaptain(this);
				this.setScreen(findTheCaptain);

			case MORSEANSWER:
				if(morseAnswer == null) morseAnswer = new MorseAnswer(this);
				this.setScreen(morseAnswer);
			case CONCLUSIONMORSEANSWER:
				if(conclusionMorseAnswer == null) conclusionMorseAnswer = new ConclusionMorseAnswer(this);
				this.setScreen(conclusionMorseAnswer);

			case ENDGAME:
				if(endScreen == null) endScreen = new EndScreen(this);
				this.setScreen(endScreen);
				break;
		}
	}

	public AppPreferences getPreferences() {
		return this.preferences;
	}

}
