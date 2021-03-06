package model.plateau;

import model.entity.Entity;
import model.factory.TextureFactory;

public class SecretPassage extends Effect {
	private int destX;
	private int destY;
	public SecretPassage(int x, int y){
		super();
		destX=x;
		destY=y;
		texture = TextureFactory.getTexPortal();
	}

	@Override
	public void trigger(Entity h, Square s) {
		Square destination = s.getMap().getSquare(destX, destY);
		if(destination.getEntity()==null){
			h.setPos(destination);
			destination.setEntity(h);
			s.setEntity(null);
		}
	}
	@Override
	public int getType() {
		return 2;
	}
	public int getPosXSortie(){
		return destX;
	}
	public int getPosYSortie(){
		return destY;
	}

}
