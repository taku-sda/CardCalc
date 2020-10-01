package com.example.app;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.example.validation.DeckOrLess;

import lombok.Data;

@Data
@DeckOrLess(property = "target", comparingProperty = "deck", message = "目的のカードの枚数は山札以下にしてください")
@DeckOrLess(property = "draw", comparingProperty = "deck", message = "引く枚数は山札以下にしてください")
@DeckOrLess(property = "want", comparingProperty = "target", message = "引きたい枚数は目的のカードの枚数以下にしてください")
@DeckOrLess(property = "want", comparingProperty = "draw", message = "引きたい枚数は引く枚数以下にしてください")
public class Calc1Form implements Serializable {
  private static final long serialVersionUID = 1L;
  /** 山札の最大枚数. */
  private static final int MAX_DECK_NUMBER = 60;

  @Max(value = MAX_DECK_NUMBER, message = "山札は60枚以下です")
  @Min(value = 1, message = "1枚以上にしてください")
  private int deck;
  @Min(value = 1, message = "1枚以上にしてください")
  private int target;
  @Min(value = 1, message = "1枚以上にしてください")
  private int draw;
  @Min(value = 1, message = "1枚以上にしてください")
  private int want;
}
