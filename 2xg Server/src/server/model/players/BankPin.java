package server.model.players;


import server.util.Misc;

/**
 * Handles the BankPin on Bank's
 * @author 
 */

public class BankPin {
	
	private int bankPins[] = {
		0, 1, 2, 3, 4, 5, 6, 7, 8, 9
	};
	private int stringIds[] = { 
		14883, 14884, 14885, 14886, 
		14887, 14888, 14889, 14890, 
		14891, 14892
	};
	private int matchingButtons[] =	{
		0, 1, 2, 3, 4, 5, 6, 7, 8, 9
	};
	private int actionButtons[] = { 
		58025, 58026, 58027, 58028, 
		58029, 58030, 58031, 58032, 
		58033, 58034
	};
	private Client client;
	public int allowTimer = 2000000;
	
	public BankPin(Client client) {
		this.client = client;
	}	
	
	public void timeCountDown() {
		if(allowTimer > 0 && allowTimer <= 300000) {
			allowTimer -= 100;
		}
		if(allowTimer == 0) {
			client.attemptsRemaining = 3;
			allowTimer = 2000000;
		}
	}
	
	public void closeBankPin() {
		firstPin = 0;
		secondPin = 0;
		thirdPin = 0;
		fourthPin = 0;
		client.playerBankPin = 0;
		client.fourthPinEnter = false;
		client.thirdPinEnter = false;
		client.secondPinEnter = false;
		client.firstPinEnter = false;
		client.getPA().removeAllWindows();
	}
	
	public void resetBankPin() {
		firstPin = 0;
		secondPin = 0;
		thirdPin = 0;
		fourthPin = 0;
		client.playerBankPin = 0;
		client.fourthPinEnter = false;
		client.thirdPinEnter = false;
		client.secondPinEnter = false;
		client.firstPinEnter = false;
		client.hasBankPin = false;
		client.firstPin = 0;
		client.secondPin = 0;
		client.thirdPin = 0;
		client.fourthPin = 0;
		client.attemptsRemaining = 3;
		client.lastPinSettings = -1;
		client.deletePinDate = -1;
	}
	
	public void bankPinEnter(int button) {
		if(allowTimer > 0 && allowTimer <= 300000) {
			int time = allowTimer/6000;
			if(time >= 2) {
				client.sendMessage("Please wait "+ time +" minutes before attempting your bank pin again.");
			} else if(time == 1) {
				client.sendMessage("Please wait "+ time +" minute before attempting your bank pin again.");
			} else if(time <= 0) {
				client.sendMessage("Please wait less " +"than a minute before attempting your bank pin again.");
			}
			return;
		}
		sendPins();
		if(!client.firstPinEnter)
			handleButtonOne(button);
		else if(!client.secondPinEnter)
			handleButtonTwo(button);
		else if(!client.thirdPinEnter)
			handleButtonThree(button);
		else if(!client.fourthPinEnter)
			handleButtonFour(button);
	}
	
	public void openPin() {
		if (client.enterdBankpin){
			client.getPA().openUpBank();
		return;
		}
		randomizeNumbers();
		client.getPA().sendFrame126("First click the FIRST digit", 15313);
		client.getPA().sendFrame126("", 14923);
		client.getPA().sendFrame126("?", 14913);
		client.getPA().sendFrame126("?", 14914);
		client.getPA().sendFrame126("?", 14915);
		client.getPA().sendFrame126("?", 14916);
		client.getPA().showInterface(7424);
		sendPins();
	}
	
	private void sendPins() {
		if (client.enterdBankpin){
			client.getPA().openUpBank();
		return;
		}
		for(int i = 0; i < getBankPins().length; i++) {
			client.getPA().sendFrame126(""+getBankPins()[i], stringIds[i]);
		}
	}
	
	private void handleButtonOne(int button) {
		client.getPA().sendFrame126("Now click the SECOND digit", 15313);
		client.getPA().sendFrame126("*", 14913);
		for(int i = 0; i < getActionButtons().length; i++) {
			if(getActionButtons()[i] == button) {
				firstPin = getBankPins()[i];
			}
		}
		client.firstPinEnter = true;
		randomizeNumbers();
	}
	
	private void handleButtonTwo(int button) {
		client.getPA().sendFrame126("Now click the THIRD digit", 15313);
		client.getPA().sendFrame126("*", 14914);
		for(int i = 0; i < getActionButtons().length; i++) {
			if(getActionButtons()[i] == button) {
				secondPin = getBankPins()[i];
			}
		}
		client.secondPinEnter = true;
		randomizeNumbers();
	}
	
	private void handleButtonThree(int button) {
		client.getPA().sendFrame126("Now click the LAST digit", 15313);
		client.getPA().sendFrame126("*", 14915);
		for(int i = 0; i < getActionButtons().length; i++) {
			if(getActionButtons()[i] == button) {
				thirdPin = getBankPins()[i];
			}
		}
		client.thirdPinEnter = true;
		randomizeNumbers();
	}
	
	private void handleButtonFour(int button) {
		if (client.enterdBankpin){
			client.getPA().openUpBank();
		return;
		}
		client.getPA().sendFrame126("*", 14916);
		for(int i = 0; i < getActionButtons().length; i++) {
			if(getActionButtons()[i] == button) {
				fourthPin = getBankPins()[i];
			}
		}
		client.fourthPinEnter = true;
		if(!client.hasBankPin) {
			client.firstPin = firstPin;
			client.bankPin1 = firstPin;
			client.secondPin = secondPin;
			client.bankPin2 = secondPin;
			client.thirdPin = thirdPin;
			client.bankPin3 = thirdPin;
			client.fourthPin = fourthPin;
			client.bankPin4 = fourthPin;
			client.hasBankPin = true;
			client.enterdBankpin = true;
			client.sendMessage("You have just created a bank pin.");
			client.sendMessage("Your new pin is(in order): "+firstPin+ " - "+secondPin+" - "+thirdPin+" - "+fourthPin);
			client.sendMessage("Please write down this because you cannot change it.");
			client.saveCharacter = true;
		}

		int one = firstPin, two = secondPin, three = thirdPin, four = fourthPin;
		if(client.bankPin1 == one && client.bankPin2 == two && client.bankPin3 == three && client.bankPin4 == four) {
			client.getPA().openUpBank();
			client.fourthPinEnter = false;
			client.thirdPinEnter = false;
			client.secondPinEnter = false;
			client.firstPinEnter = false;
			client.enterdBankpin = true;
			client.playerBankPin = 15000;
			client.getPA().openUpBank();
		} else {
			client.attemptsRemaining --;
			if(client.attemptsRemaining <= 0) {
				allowTimer = 30000;
			}
			if(client.attemptsRemaining == -1) {
				client.attemptsRemaining = 3;
				allowTimer = 2000000;
			}
			if(client.attemptsRemaining > 1) {
				client.sendMessage("Invalid pin. You have "+client.attemptsRemaining+" attempts remaining.");
			} else if(client.attemptsRemaining == 1) {
				client.sendMessage("Invalid pin. You have "+client.attemptsRemaining+" attempt remaining.");
			} else if(client.attemptsRemaining <= 0) {
				client.sendMessage("Invalid pin. You must wait 5 minutes before attempting again.");
			}
			client.getPA().removeAllWindows();
			client.fourthPinEnter = false;
			client.thirdPinEnter = false;
			client.secondPinEnter = false;
			client.firstPinEnter = false;
		}
	}
	
	public void randomizeNumbers() {
		int i = Misc.random(5);
		if(i == client.lastPinSettings) {
			i = (client.lastPinSettings == 0 ? client.lastPinSettings++ : client.lastPinSettings--);
		}
		switch(i) {
		case 0:
			bankPins[0] = 1;
			bankPins[1] = 7;
			bankPins[2] = 0;
			bankPins[3] = 8;
			bankPins[4] = 4;
			bankPins[5] = 6;
			bankPins[6] = 5;
			bankPins[7] = 9;
			bankPins[8] = 3;
			bankPins[9] = 2;
			break;
		
		case 1:
			bankPins[0] = 5;
			bankPins[1] = 4;
			bankPins[2] = 3;
			bankPins[3] = 7;
			bankPins[4] = 8;
			bankPins[5] = 6;
			bankPins[6] = 9;
			bankPins[7] = 2;
			bankPins[8] = 1;
			bankPins[9] = 0;
			break;
			
		case 2:
			bankPins[0] = 4;
			bankPins[1] = 7;
			bankPins[2] = 6;
			bankPins[3] = 5;
			bankPins[4] = 2;
			bankPins[5] = 3;
			bankPins[6] = 1;
			bankPins[7] = 8;
			bankPins[8] = 9;
			bankPins[9] = 0;
			break;
			
		case 3:
			bankPins[0] = 9;
			bankPins[1] = 4;
			bankPins[2] = 2;
			bankPins[3] = 7;
			bankPins[4] = 8;
			bankPins[5] = 6;
			bankPins[6] = 0;
			bankPins[7] = 3;
			bankPins[8] = 1;
			bankPins[9] = 5;
			break;
			
		case 4:
			bankPins[0] = 8;
			bankPins[1] = 7;
			bankPins[2] = 6;
			bankPins[3] = 2;
			bankPins[4] = 5;
			bankPins[5] = 4;
			bankPins[6] = 1;
			bankPins[7] = 0;
			bankPins[8] = 3;
			bankPins[9] = 9;
			break;
		}
		client.lastPinSettings = i;
		sendPins();
	}

	public void setBankPins(int bankPins[]) {
		this.bankPins = bankPins;
	}

	public int[] getBankPins() {
		return bankPins;
	}

	public int[] getActionButtons() {
		return actionButtons;
	}

	public int[] getMatchingButtons() {
		return matchingButtons;
	}
	
	public void setFirstPin(int firstPin) {
		this.firstPin = firstPin;
	}

	public int getFirstPin() {
		return firstPin;
	}

	public void setTotalPin(int totalPin) {
		this.totalPin = totalPin;
	}

	public int getTotalPin() {
		return totalPin;
	}

	public void setSecondPin(int secondPin) {
		this.secondPin = secondPin;
	}

	public int getSecondPin() {
		return secondPin;
	}

	public void setThirdPin(int thirdPin) {
		this.thirdPin = thirdPin;
	}

	public int getThirdPin() {
		return thirdPin;
	}

	public void setFourthPin(int fourthPin) {
		this.fourthPin = fourthPin;
	}

	public int getFourthPin() {
		return fourthPin;
	}

	private int firstPin;
	private int secondPin;
	private int thirdPin;
	private int fourthPin;
	private int totalPin = firstPin+secondPin+thirdPin+fourthPin;
}