package com.example.domain.service;

public class Calc1ServiceImpl implements Calc1Service {

	@Override
	public double calculate(int deck, int target, int draw, int want) {
		//目標枚数以上引けない場合の確率を求める
		double failureProbability = 0;
		int j = 0;
		while(j < want) {
			failureProbability += combination(target, j) * combination(deck - target, draw - j) / combination(deck, draw);
			j++;
		}
		double successProbability = 1 - failureProbability;
		//%表記にしてreturn
		return successProbability * 100;
	}

	@Override
	public int combination(int n, int r) {
		int result = 1;
		while(r > 0) {
			result *=  n / r;
			n--;
			r--;
		}
		return result;
	}
}
