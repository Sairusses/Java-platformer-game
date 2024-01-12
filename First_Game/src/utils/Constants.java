package utils;

public class Constants {
	
	public static class Directions{
		public static final int LEFT = 0;
		public static final int UP = 1;
		public static final int RIGHT = 3;
		public static final int DOWN = 4;
	}
	public static class PlayerConstants{
		public static final int IDLE = 0;
		public static final int RUN = 1;
		public static final int JUMP = 2;
		public static final int ATK1 = 3;
		public static final int HURT = 4;
		public static final int FALL = 5;
		public static final int ATK4 = 6;
		public static final int DEAD = 7;
		
		public static int GetSpriteAmount(int action) {
			switch(action) {
			case IDLE:
				return 4;
			case RUN:
				return 6;
			case JUMP:
				return 5;
			case ATK1:
				return 5;
			case HURT:
				return 3;
			case FALL:
				return 6;
			case ATK4:
				return 9;
			case DEAD:
				return 6;
			default:
				return 5;
			}
		}
	}
	public static class EnemyConstants{
		public static final int MUSHROOM = 0;
		public static final int GOBLIN = 1;
		public static final int EYE = 2;
		
		public static final int IDLE = 0;
		public static final int RUN = 1;
		public static final int ATTACK = 2;
		public static final int DAMAGE = 3;
		public static final int DEAD = 4;
		
		public static final int XY = 150;
		public static int GetSpriteAmount(int type, int action) {
			switch(type) {
			case MUSHROOM:
				switch(action) {
				case IDLE:
					return 4;
				case RUN:
					return 8;
				case ATTACK:
					return 8;
				case DAMAGE:
					return 4;
				case DEAD:
					return 4;
				}
				
			}
			return 10;
		}
		
	}
	
	
	
	
}
