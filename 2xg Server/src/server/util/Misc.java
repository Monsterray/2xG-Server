package server.util;

import java.text.NumberFormat;

public class Misc {

	public static byte directionDeltaX[] = new byte[]{ 0, 1, 1, 1, 0,-1,-1,-1 };
	public static byte directionDeltaY[] = new byte[]{ 1, 1, 0,-1,-1,-1, 0, 1 };
	public static byte xlateDirectionToClient[] = new byte[]{ 1, 2, 4, 7, 6, 5, 3, 0 };
    private static char decodeBuf[] = new char[4096];
	
	public static String formatPlayerName(String s) {
		for (int i = 0; i < s.length(); i++) {
			if (i == 0) {
				s = String.format( "%s%s",
				Character.toUpperCase(s.charAt(0)), s.substring(1) );
			}
			if (!Character.isLetterOrDigit(s.charAt(i))) {
				if (i + 1 < s.length()) {
					s = String.format("%s%s%s", s.subSequence(0, i+1), Character.toUpperCase(s.charAt(i + 1)), s.substring(i+2) );
				}
			}
		}
		return s.replace("_", " ");
	}
	
	public static String insertCommas(String str) {
		if (str.length() < 4) {
			return str;
		}
		return insertCommas(str.substring(0, str.length() - 3)) + ","
				+ str.substring(str.length() - 3, str.length());
	}

	public static String longToReportPlayerName(long l) {
		int i = 0;
		final char ac[] = new char[12];
		while (l != 0L) {
			final long l1 = l;
			l /= 37L;
			ac[11 - i++] = Misc.playerNameXlateTable[(int) (l1 - l * 37L)];
		}
		return new String(ac, 12 - i, i);
	}

	public static String longToPlayerName(long l) {
		int i = 0;
		char ac[] = new char[12];
		while(l != 0L) {
			long l1 = l;
			l /= 37L;
			ac[11 - i++] = playerNameXlateTable[(int)(l1 - l * 37L)];
		}
		return new String(ac, 12 - i, i);
	}

	public static String basicEncrypt(String s) {
		String toReturn = "";
		for (int j = 0; j < s.length(); j++) {
			toReturn += (int)s.charAt(j);
		}
		//System.out.println("Encrypt: " + toReturn);
		return toReturn;	
	}
	
	public static String longToPlayerName2(long l) {
		int i = 0;
		char ac[] = new char[99];
		while(l != 0L) {
			long l1 = l;
			l /= 37L;
			ac[11 - i++] = playerNameXlateTable[(int)(l1 - l * 37L)];
		}
		return new String(ac, 12 - i, i);
	}
	
	public static final char playerNameXlateTable[] = { 
		'_', 'a', 'b', 'c',
		'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
		'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2',
		'3', '4', '5', '6', '7', '8', '9' 
	};

	public static String longToString(long l) {
		int i = 0;
		char ac[] = new char[12];
		while (l != 0L) {
			long l1 = l;
			l /= 37L;
			ac[11 - i++] = playerNameXlateTable[(int) (l1 - l * 37L)];
		}
		return new String(ac, 12 - i, i);
	}
	
	public static String format(int num) {
		return NumberFormat.getInstance().format(num);
	}
	
	public static String ucFirst(String str) {
		str = str.toLowerCase();
		if(str.length() > 1) {
			str = str.substring(0,1).toUpperCase() + str.substring(1);
		} else {
			return str.toUpperCase();
		}
		return str;
	}
	
	public static void print_debug(String str){
		System.out.print(str);				
	}
	
	public static void println_debug(String str){
		System.out.println(str);
	}
	
	public static void print(String str){
		System.out.print(str);
	}
	
	public static void println(String str){
		System.out.println(str);
	}

	public static String Hex(byte data[]){
		return Hex(data, 0, data.length);
	}
	
	public static String Hex(byte data[], int offset, int len){
		String temp = "";
		for(int cntr = 0; cntr < len; cntr++) {
			int num = data[offset+cntr] & 0xFF;
			String myStr;
			if(num < 16) myStr = "0";
			else myStr = "";
			temp += myStr + Integer.toHexString(num) + " ";
		}
		return temp.toUpperCase().trim();
	}

	public static int hexToInt(byte data[], int offset, int len){
		int temp = 0;
                int i = 1000;
		for(int cntr = 0; cntr < len; cntr++) {
			int num = (data[offset+cntr] & 0xFF) * i;
			temp += (int)num;
                        if (i > 1)
			  i=i/1000;
		}
		return temp;
	}

	public static int random2(int range) { 
		return (int)((java.lang.Math.random() * range) + 1);
	}

	public static int random(int range) {
		return (int)(java.lang.Math.random() * (range+1));
	}

	public static long playerNameToInt64(String s){
		long l = 0L;
		for(int i = 0; i < s.length() && i < 12; i++) {
			char c = s.charAt(i);
			l *= 37L;
			if(c >= 'A' && c <= 'Z') l += (1 + c) - 65;
			else if(c >= 'a' && c <= 'z') l += (1 + c) - 97;
			else if(c >= '0' && c <= '9') l += (27 + c) - 48;
		}
		while(l % 37L == 0L && l != 0L) l /= 37L;
		return l;
	}

	public static String textUnpack(byte packedData[], int size){
		int idx = 0, highNibble = -1;
		for(int i = 0; i < size*2; i++) {
			int val = packedData[i/2] >> (4-4*(i%2)) & 0xf;
			if(highNibble == -1) {
				if(val < 13) decodeBuf[idx++] = xlateTable[val];
				else highNibble = val;
			}
			else {
				decodeBuf[idx++] = xlateTable[((highNibble<<4) + val) - 195];
				highNibble = -1;
			}
		}
		return new String(decodeBuf, 0, idx);
    }

	public static String optimizeText(String text){
		char buf[] = text.toCharArray();
		boolean endMarker = true;	
		for(int i = 0; i < buf.length; i++) {
            char c = buf[i];
            if(endMarker && c >= 'a' && c <= 'z') {
				buf[i] -= 0x20;	
				endMarker = false;
			}
			if(c == '.' || c == '!' || c == '?') endMarker = true;
		}
		return new String(buf, 0, buf.length);
	}

	public static void textPack(byte packedData[], java.lang.String text){
		if(text.length() > 80) text = text.substring(0, 80);
		text = text.toLowerCase();

		int carryOverNibble = -1;
		int ofs = 0;
		for(int idx = 0; idx < text.length(); idx++) {
			char c = text.charAt(idx);
			int tableIdx = 0;
			for(int i = 0; i < xlateTable.length; i++) {
				if(c == xlateTable[i]) {
					tableIdx = i;
					break;
				}
			}
			if(tableIdx > 12) tableIdx += 195;
			if(carryOverNibble == -1) {
				if(tableIdx < 13) carryOverNibble = tableIdx;
				else packedData[ofs++] = (byte)(tableIdx);
			}
			else if(tableIdx < 13) {
				packedData[ofs++] = (byte)((carryOverNibble << 4) + tableIdx);
				carryOverNibble = -1;
			}
			else {
				packedData[ofs++] = (byte)((carryOverNibble << 4) + (tableIdx >> 4));
				carryOverNibble = tableIdx & 0xf;
			}
		}

		if(carryOverNibble != -1) packedData[ofs++] = (byte)(carryOverNibble << 4);
	}

	public static char xlateTable[] = {
		' ', 'e', 't', 'a', 'o', 'i', 'h', 'n', 's', 'r', 
		'd', 'l', 'u', 'm', 'w', 'c', 'y', 'f', 'g', 'p', 
		'b', 'v', 'k', 'x', 'j', 'q', 'z', '0', '1', '2', 
		'3', '4', '5', '6', '7', '8', '9', ' ', '!', '?', 
		'.', ',', ':', ';', '(', ')', '-', '&', '*', '\\', 
		'\'', '@', '#', '+', '=', '\243', '$', '%', '"', '[', 
		']'
	};

	public static int direction(int srcX, int srcY, int destX, int destY){
		int dx=destX-srcX, dy=destY-srcY;
		
		if(dx < 0) {
			if(dy < 0) {
				if(dx < dy) return 11;
				else if(dx > dy) return 9;
				else return 10;		
			}
			else if(dy > 0) {
				if(-dx < dy) return 15;
				else if(-dx > dy) return 13;
				else return 14;		
			}
			else {	
				return 12;
			}
		}
		else if(dx > 0) {
			if(dy < 0) {
				if(dx < -dy) return 7;
				else if(dx > -dy) return 5;
				else return 6;		
			}
			else if(dy > 0) {
				if(dx < dy) return 1;
				else if(dx > dy) return 3;
				else return 2;	
			}
			else {	
				return 4;
			}
		}
		else {		
			if(dy < 0) {
				return 8;
			}
			else if(dy > 0) {
				return 0;
			}
			else {	
				return -1;		
			}
		}
	}

}