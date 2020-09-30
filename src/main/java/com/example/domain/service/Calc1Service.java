package com.example.domain.service;

public interface Calc1Service {
  /**
   * 目的のカードを任意の枚数以上引ける確率の計算を行う.
   * 
   * @param deck   山札の枚数
   * @param target 山札中の目的のカードの枚数
   * @param draw   山札から引く枚数
   * @param want   目的のカードを引きたい枚数
   * @return 引ける確率(%)
   */
  double calculate(int deck, int target, int draw, int want);

  /**
   * 組み合わせの計算を行う.
   * 
   * @param n 要素の個数
   * @param r 取り出す個数
   * @return 組み合わせの数
   */
  double combination(int all, int pick);
}
