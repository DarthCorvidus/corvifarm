package com.corvidus.corvifarm.game;
/**
 * Invalid actions are actions by the player which he is not able to do, like
 * using a wrong tool, being out of energy, being out of gold and so on.
 * @author hm
 */
public class InvalidActionException extends Exception {
	public InvalidActionException(String message) {
		super(message);
	}
}
