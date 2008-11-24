package com.g4g.helper;

/**
 * Enumerator for RSS.
 * 
 * @author Jigar Mistry
 */

public enum RSSLines {
	DATE { int eval() { return 0; } },
	PLAYERLIST { int eval() { return 2; } },
	GAME { int eval() { return 3; } },
	GAMERTAG { int eval() { return 5; } };
	
	abstract int eval();
}
