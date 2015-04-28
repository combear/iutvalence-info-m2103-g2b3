package fr.iutvalence.info.m2103.sokoban;

import java.util.Map;

/**
 * Represents a level of Sokoban
 * @author Thibault - Mathie
 *
 */
public class Level {

	/**
	 * Default level number
	 */
	public static final int DEFAULT_MAP_SIZE = 5;

	/**
	 * Array of map elements
	 * Represents the map of the level
	 */
	private MapElement[][] map;

	/**
	 * Level number
	 */
	private int levelNumber;
	
	private Position startingPosition;

	/**
	 * Creates a new level
	 */
	public Level(){
		this.levelNumber = 1;
		this.map = new MapElement[DEFAULT_MAP_SIZE][DEFAULT_MAP_SIZE];

		for (int line = 0; line < DEFAULT_MAP_SIZE; line++) {
			for (int column = 0; column < DEFAULT_MAP_SIZE; column++) {
				if(line == 0 || line == 4)
					this.placeElement(new Position(line, column), MapElement.WALL);
				else
					this.placeElement(new Position(line, column), MapElement.FLOOR);
			}
			this.placeElement(new Position(line, 0), MapElement.WALL);
			this.placeElement(new Position(line, 4), MapElement.WALL);
		}
		this.placeElement(new Position(1, 1), MapElement.PLAYER);
		this.placeElement(new Position(1, 2), MapElement.BOX);
		this.placeElement(new Position(1, 3), MapElement.TARGET);
		
		this.startingPosition = new Position(1, 1);
	}

	/**
	 * Places a specified element at a given position
	 * @param pos The position
	 * @param elem The element
	 * @return true if the element has been placed
	 *         else false
	 */
	public boolean placeElement(Position pos, MapElement elem){
		if(!isValidPosition(pos))
			return false;
		this.map[pos.getPosX()][pos.getPosY()] = elem;
		return true;
	}
	
	/**
	 * Moves a movable map element (PLAYER, BOX, PLAYER_ON_TARGET, BOX_ON_TARGET)
	 *  from a given position to an other position.</br>
	 * If the it moves PLAYER or BOX on a TARGET,
	 *  it will place PLAYER_ON_TARGET or BOX_ON_TARGET.
	 * @param startPos The starting position
	 * @param finalPos The final position
	 * @return <tt>true</tt> if the element has been moved, <tt>false</tt> if not.
	 */
	public boolean moveElement(Position startPos, Position finalPos){
		if(!isValidPosition(startPos) || !isValidPosition(finalPos))
			return false;
		this.placeElement(finalPos, this.getMapElement(startPos));
		return true;
	}

	/**
	 * Returns the map element at the given position
	 * @param pos The given position
	 * @return true if the map element if the position is valid
	 *         else return null
	 */
	public MapElement getMapElement(Position pos){
		if(!isValidPosition(pos))
			return null;
		return this.map[pos.getPosX()][pos.getPosY()];
	}

	/**
	 * Checks if the position is valid
	 * @param pos The position to check
	 * @return true if the position is valid, else false
	 */
	private boolean isValidPosition(Position pos){
		if((pos.getPosX() < 0 || pos.getPosX() > this.map.length)
				|| (pos.getPosY() < 0 || pos.getPosY() > this.map.length)
				|| pos == null)
			return false;
		return true;
	}

	/**
	 * Returns the starting position where the player will begin
	 * @return The starting position
	 */
	public Position getStartingPosition() {
		return this.startingPosition;
	}

	/**
	 * Return an ASCII representation of the map
	 */
	@Override
	public String toString() {
		String str = "";
		for (int line = 0; line < DEFAULT_MAP_SIZE; line++) {
			for (int column = 0; column < DEFAULT_MAP_SIZE; column++) {
				str += this.map[line][column];
			}
			str += "\n";
		}
		return str;
	}

}