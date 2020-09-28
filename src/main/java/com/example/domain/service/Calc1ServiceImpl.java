package com.example.domain.service;

import org.springframework.stereotype.Service;

@Service
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
	public double combination(int n, int r) {
		double numerator = 1;
		double denominator = 1;
		while(r > 0) {
			numerator *= n;
			denominator *= r;
			n--;
			r--;
		}
		return numerator / denominator;
	}
}
