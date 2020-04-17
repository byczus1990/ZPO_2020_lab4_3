package com.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class SbLearingProcces {

	private List<String> wordsList = new ArrayList<String>();
	private List<String> forgetableList = new ArrayList<String>();
	private List<String> learnedWordsList = new ArrayList<String>();
	private Map<Integer,List<String>> learnedWordsByDaysMap = new HashMap<Integer, List<String>>(); 
	private Random r = new Random();
	private String learnedWord1, learnedWord2, forgottenWord1, forgottenWord2;

	public void initializeWordList()
	{
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("dane2.txt"));
			String line = reader.readLine();
			
			while (line != null) {
				wordsList.add(line);
				// read next line
				line = reader.readLine();
			}
			reader.close();
//			System.out.print(wordsList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void learningProccess(int numberOfLearningDays, int numberOfDayToForgetWords )
	{
		// day 1
		for(int i = 0; i < 2; i++)
		{
			int random = r.nextInt(wordsList.size());
				if(i == 0)
					{
					learnedWord1 = wordsList.get(random);
					learnedWordsList.add(learnedWord1);
					}else
					{
						learnedWord2 = wordsList.get(random);
						learnedWordsList.add(learnedWord2);
					}			
			wordsList.remove(random);
		}
		
		learnedWordsByDaysMap.put(0, learnedWordsList);

		System.out.println("Day 1");
		System.out.println("New words:\t" + learnedWord1 + " " + learnedWord2);
		System.out.println("Forgotten words:\t---");
		System.out.println(learnedWordsList);

		// rest days
		for(int i = 1; i < numberOfLearningDays; i++)
		{
			forgetableList.clear();
			//Generate List<String> of available to forget words
			
			for (Integer key : learnedWordsByDaysMap.keySet()) {
			    if(key <= numberOfDayToForgetWords)
			    {
			    	forgetableList.addAll(learnedWordsByDaysMap.get(key));
			    }
			}
			
			// choose 2 words from list above
			List<String> twoWordsList = new ArrayList<String>();
			
			if(forgetableList.size() == 1)
			{
				twoWordsList.add(forgetableList.get(0));
			}else if(forgetableList.size() > 1)
			{
				int o = 0;
				while(o < 2)
				{
					int randomNumber1 = r.nextInt(forgetableList.size());				
					twoWordsList.add(forgetableList.get(randomNumber1));
					forgetableList.remove(randomNumber1);
					o++;
				}
			}
			
			
			forgottenWord1 = "";
			forgottenWord2 = "";
			
			//roll chances to forget
			int internalIterator = 0;

			for(String string: twoWordsList)
			{
				// roll chances for each word to be forgotten
				for(int k = 0; k < 1; k++)
				{
					int lrand = r.nextInt(3);
					if(internalIterator == 0 && lrand < 2)
					{
						forgottenWord1 = string;
						learnedWordsList.remove(forgottenWord1);
						wordsList.add(forgottenWord1);
						internalIterator++;
						
					}else if (internalIterator == 1 && lrand > 1) 
					{
						forgottenWord2 = string;
						learnedWordsList.remove(forgottenWord2);
						wordsList.add(forgottenWord2);
						internalIterator++;
					}					
					
				}
			}
			
			//learn 2 new words
			
			for(int l = 0; l < 2; l++)
			{
				int randomL = r.nextInt(wordsList.size());
					if(l == 0)
						{
						learnedWord1 = wordsList.get(randomL);
						learnedWordsList.add(learnedWord1);
						}else
						{
							learnedWord2 = wordsList.get(randomL);
							learnedWordsList.add(learnedWord2);
						}			
				wordsList.remove(randomL);
			}
			
			learnedWordsByDaysMap.put(0, learnedWordsList);
			
			System.out.println("Day " + (i + 1));
			System.out.println("New words:\t" + learnedWord1 + " " + learnedWord2);
			System.out.println("Forgotten words:\t" + forgottenWord1 + " " + forgottenWord2);
			System.out.println(learnedWordsList);
		}
	}
}
