package server.model.players.Content;

import server.Server;
import server.model.npcs.NPC;
import server.model.players.Client;
import server.util.Misc;
/**
* Handles everything that has to do with clue scrolls or other puzzle things.
* @Author Monsterray
*/
public class ClueScroll{
	/**
	 * Holds the client that opend the ClueScroll class
	 */
	private Client c;
	/**
	 * Holds all of the easy clue scroll item IDs.
	 */
	int[] clueIdsEasy = {
		2677,2678,2679,2680,2681,2682,2683,2684,2685,2716,
		2686,2687,2688,2689,2690,2691,2692,2693,2694,2719,
		2695,2696,2697,2698,2699,2700,2701,2702,2703,
		2704,2705,2706,2707,2708,2709,2710,2711,2712,2713
	};
	/**
	 * Holds all of the easy caskets item IDs.
	 */
	int[] easyCaskets = {2714,2715,2717,2718,2720,2721};
	/**
	 * Holds all of the medium clue scroll item IDs.
	 */
	int[] clueIdsMedium = {
		2801,2803,2805,2807,2809,2811,2813,2815,2817,2819,2821,
		2823,2825,2827,2829,2831,2833,2835,2837,2839,2841,2843,
		2845,2847,2848,2849,2851,2853,2855,2856,2857,2858
	};
	/**
	 * Holds all of the medium cakset item IDs.
	 */
	int[] mediumCaskets = {
		2802,2804,2806,2808,2810,2812,2814,2816,
		2818,2820,2822,2824,2826,2828,2830
	};
	/**
	 * Holds all of the hard clue scroll item IDs.
	 */
	int[] clueIdsHard = {
		2722,2723,2725,2727,2729,2731,2733,2735,2737,2739,2741,
		2743,2745,2747,2773,2774,2776,2778,2780,2782,2783,2785,
		2786,2788,2790,2792,2793,2794,2796,2797,2799
	};
	/**
	 * Holds all of the hard casket item IDs.
	 */
	int[] hardCaskets = {
		2724,2726,2728,2730,2732,2734,2736,2738,2740,2742,2744,
		2746,2748,2775,2777,2779,2781,2784,2787,2789,2791
	};
	/**
	 * Holds all of the challenge scroll item IDs.
	 */
	int[] challengeScrollIds = {2842,2844,2846,2850,2852,2854};
	/**
	 * Holds all of the key item IDs.
	 */
	int[] keyIds = {2832,2834,2836,2838,2840};
	/**
	 * Holds all of the puzzle box item IDs.
	 */
	int[] puzzleBoxIds = {2795,2798,2800};
	/**
	 * Holds all of the sliding peice item IDs.
	 */
	int[] slidingPieceIds = {
		2749,2750,2751,2752,2753,2754,2755,2756,2757,2758,2759,2760,
		2761,2762,2763,2764,2765,2766,2767,2768,2769,2770,2771,2772
	};
	/**
	 * Holds all of the easy, medium, and hard clue scrolls text.
	 */
	String[][][] clueText = {	// {First Line, Second Line , Third Line, Fourth Line, Fifth Line , Sixth Line}
		{
			{"In the bank on the edge of the world","where people love to kill each","other lol","Kinda close to the red guy","","",""},
			{"","","Help I need the instructions on the other","side of this scroll so i can break","free from haveing to say stuff all day","",""}
		},{
			{"","","","","","",""},
			{"","","","","","",""}
		},{
			{"","","","","","",""},
			{"","","","","","",""}
		}
	};
	/**
	 * Holds all of the easy, medium, and hard answers for the clue scrolls.
	 */
	int[][][] answerIds = { //its a double array for the times when you dig for x, y
		{
			{961},
			{7833}
		},{
			{},
			{}
		},{
			{},
			{}
		}
	};
	/**
	 * Holds all of the holds the clue type for all easy, medium, and hard scrolls so they can be matched up.
	 */
	int[][] clueType = { //dig = 0, object = 1, npc = 2
		{1,2},
		{},
		{}
	};
	/**
	 * Holds all of the storys that you get when you finish a clue scroll.\n
	 * The text is split for if you want the story to be said over multiple lines in the chat bar.
	 */
	String[][] storys = {
		{"A rip in time appears before you!","And a casket flys out of it as the rip closes","Once you relax you pick it up"}
	};

	/**
	 * Constructor
	 * @param client The client that is useing the clue scroll
	 */
	public ClueScroll(Client client){
		this.c = client;
	}

	/**
	 * Selects a random clue for the appropriate  scroll level that you have.
	 * @param itemId The clue scroll being used
	 */
	public void selectRandomClue(int itemId){
		if(c.currentClue == -1){
			int clueLevel = getClueLevel(itemId);
			int randClue = Misc.random(clueText[clueLevel - 1].length - 1);
			showScroll(randClue, clueLevel);
			c.currentClue = randClue;
		}else{
			showScroll(c.currentClue, getClueLevel(itemId));
		}
	}

	/**
	 * Gets the appropriate casket item ID for the level of the clue scroll that was finished.
	 * @param itemId The ID of the item used
	 * @return Casket Level
	 */
	public int getCasketLevel(int itemId){
		for(int i = 0; i <= easyCaskets.length-1; i++){
			if(itemId == easyCaskets[i])
				return 1;
		}
		for(int i = 0; i <= mediumCaskets.length-1; i++){
			if(itemId == mediumCaskets[i])
				return 2;
		}
		for(int i = 0; i <= hardCaskets.length-1; i++){
			if(itemId == hardCaskets[i])
				return 3;
		}
		return 0;
	}

	/**
	 * Gets the appropriate clue scroll item ID for the level of the clue scroll that was used.
	 * @param itemId The ID of the item used
	 * @return Clue Scroll Level
	 */
	public int getClueLevel(int itemId){
		for(int i = 0; i <= clueIdsEasy.length-1; i++){
			if(itemId == clueIdsEasy[i])
				return 1;
		}
		for(int i = 0; i <= clueIdsMedium.length-1; i++){
			if(itemId == clueIdsMedium[i])
				return 2;
		}
		for(int i = 0; i <= clueIdsHard.length-1; i++){
			if(itemId == clueIdsHard[i])
				return 3;
		}
		return 0;
	}

	/**
	 * Gets the appropriate amount of awards for the casket level.\n
	 * This might get changed later.
	 * @param itemId The ID of the item used
	 */
	public void getReward(int itemId){
		for(int i = 0; i != getCasketLevel(itemId); i++){
			int rndReward = Misc.random(Server.fileLoader.casketDrops.length - 1);
			c.getItems().addItem(Server.fileLoader.casketDrops[rndReward][0] - 1, Server.fileLoader.casketDrops[rndReward][1]);
		}
		c.getItems().deleteItem(itemId, 1);
	}

	/**
	 * Checks to see if the choice the player made was a correct one or if they need to look more.
	 * @param clickedId This is the ID of either a {@link NPC} or an {@link Object} also will intake the X of a player for digging
	 * @param itemId The clue scroll ID that was used
	 */
	public void isCorrectChoice(int clickedId, int itemId){ //work on this
		int rndInt = Misc.random(clueIdsEasy.length - 1);
		try{
			if(clickedId == answerIds[getClueLevel(itemId)][c.currentClue][clueType[getClueLevel(itemId)][c.currentClue]]){
				if(rndInt < 4 || c.cluesCompleated >= 7){
					readStory(Misc.random(storys.length - 1));
					c.getItems().deleteItem(itemId, 1);
					c.getItems().addItem(easyCaskets[Misc.random(easyCaskets.length - 1)], 1);
					c.cluesCompleated = 0;
					c.currentClue = -1;
				}else{
					c.cluesCompleated += 1;
					c.sendMessage("You get a new clue!");
					int rndId = clueIdsEasy[rndInt];
					c.getItems().deleteItem(itemId, 1);
					c.getItems().addItem(rndId, 1);
					c.currentClue = -1;
				}
			}else{
				c.sendMessage("Good geuss but no");
			}
			if(clickedId == answerIds[getClueLevel(itemId)][c.currentClue][clueType[getClueLevel(itemId)][c.currentClue]]){
				if(rndInt < 4 || c.cluesCompleated >= 7){
					readStory(Misc.random(storys.length - 1));
					c.getItems().deleteItem(itemId, 1);
					c.getItems().addItem(easyCaskets[Misc.random(easyCaskets.length - 1)], 1);
					c.cluesCompleated = 0;
					c.currentClue = -1;
				}else{
					c.cluesCompleated += 1;
					c.sendMessage("You get a new clue!");
					int rndId = clueIdsEasy[rndInt];
					c.getItems().deleteItem(itemId, 1);
					c.getItems().addItem(rndId, 1);
					c.currentClue = -1;
				}
			}else{
				c.sendMessage("Good geuss but no");
			}
			if(clickedId == answerIds[getClueLevel(itemId)][c.currentClue][clueType[getClueLevel(itemId)][c.currentClue]]){
				if(rndInt < 4 || c.cluesCompleated >= 7){
					readStory(Misc.random(storys.length - 1));
					c.getItems().deleteItem(itemId, 1);
					c.getItems().addItem(easyCaskets[Misc.random(easyCaskets.length - 1)], 1);
					c.cluesCompleated = 0;
					c.currentClue = -1;
				}else{
					c.cluesCompleated += 1;
					c.sendMessage("You get a new clue!");
					int rndId = clueIdsEasy[rndInt];
					c.getItems().deleteItem(itemId, 1);
					c.getItems().addItem(rndId, 1);
					c.currentClue = -1;
				}
			}else{
				c.sendMessage("Good geuss but no");
			}
		}catch(Exception e){
			System.out.println("Problem with the clue scrolls");
		}
	}
	
	/**
	 * Reads the story from the {@link storys} array
	 * @param choice The story to be read
	 */
	public void readStory(int choice){
		for(int i = 0; i <= storys[choice].length - 1; i++){
			c.sendMessage(storys[choice][i]);
		}
	}

	/**
	 * This method brings up the interface and puts the clue scrolls text that was chosen on the interface.
	 * @param clue The clue scrolls ID
	 * @param level This is the level of the clue scroll used
	 */
	public void showScroll(int clue, int level){
		c.getPA().showInterface(6965);
		c.getPA().sendFrame126("@red@~ Clue Scroll ~",6968);
		c.getPA().sendFrame126("",6969);
		c.getPA().sendFrame126(clueText[level][clue][0],6970);
		c.getPA().sendFrame126(clueText[level][clue][1],6971);
		c.getPA().sendFrame126(clueText[level][clue][2],6972);
		c.getPA().sendFrame126(clueText[level][clue][3],6973);
		c.getPA().sendFrame126(clueText[level][clue][4],6974);
		c.getPA().sendFrame126(clueText[level][clue][5],6975);
	}
}