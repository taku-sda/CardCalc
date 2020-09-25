package com.example.app;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.example.validation.DeckOrLess;

@DeckOrLess(property = "taget", comparingProperty = "deck",
	message = "目的のカードの枚数は山札以下にしてください")
@DeckOrLess(property = "draw", comparingProperty = "deck",
	message = "引く枚数は山札以下にしてください")
@DeckOrLess(property = "want", comparingProperty = "target",
	message = "引きたい枚数は目的のカードの枚数以下にしてください")
public class Calc1Form implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 山札の最大枚数. */
	private static final int MAX_DECK_NUMBER = 60;
	
	@NotNull(message = "枚数を入力してください")
	@Max(value = MAX_DECK_NUMBER, message = "山札は60枚以下です")
	@Min(value = 1, message = "1枚以上にしてください")
	private int deck;
	@NotNull(message = "枚数を入力してください")
	@Min(value = 1, message = "1枚以上にしてください")
	private int target;
	@NotNull(message = "枚数を入力してください")
	@Min(value = 1, message = "1枚以上にしてください")
	private int draw;
	@NotNull(message = "枚数を入力してください")
	@Min(value = 1, message = "1枚以上にしてください")
	private int want;
	
	public int getDeck() {
		return deck;
	}
	public void setDeck(int deck) {
		this.deck = deck;
	}
	public int getTarget() {
		return target;
	}
	public void setTarget(int target) {
		this.target = target;
	}
	public int getDraw() {
		return draw;
	}
	public void setDraw(int draw) {
		this.draw = draw;
	}
	public int getWant() {
		return want;
	}
	public void setWant(int want) {
		this.want = want;
	}
}
