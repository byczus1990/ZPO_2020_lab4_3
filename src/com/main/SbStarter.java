package com.main;

public class SbStarter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SbLearingProcces learningProc = new SbLearingProcces();
		learningProc.initializeWordList();
		learningProc.learningProccess(10, 5);
	}

}
