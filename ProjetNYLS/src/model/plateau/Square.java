package model.plateau;

import model.entity.Entity;

import java.util.*;

public class Square implements Iterable<Effect> {
	
	int posx;
	int posy;
	boolean isWall;
	ArrayList<Integer> listeSuppr;
	ArrayList<Effect> effets;
	Map map;

	Entity entity;

	public Square(int x, int y,Map m){
		posx=x;
		posy=y;
		entity = null;
		effets = new ArrayList<Effect>();
		map=m;
		isWall = false;
		listeSuppr = new ArrayList<Integer>();
	}
	
	public void addEffect(Effect e){
		effets.add(e);
	}

	public Entity getEntity(){return entity;}

	public void setEntity(Entity entity){this.entity = entity;}

	public int getPosX(){return posx;}
	public int getPosY(){return posy;}
	public Map getMap(){ return map;}

	@Override
	public String toString() {
		return "Square(" + posx + "," + posy + ")";
	}

    public boolean getIsWall() {
        return isWall;
    }

    public char getChar() {
		char ret = '0';
		if (isWall == true)
			ret = '1';
		if (entity != null){
			if (entity.getType().equals("Hero"))
				ret = 'h';
		}
		return ret;
	}
    
    public void treasureTaken(){
    	map.treasureTaken();
    }
    
    public void triggerEffects(Entity h){
    	for (Effect e: effets){
    		e.trigger(h,this);
    	}
    	for (int i =0; i<effets.size();i++){
			if(listeSuppr.contains(effets.get(i).getType())){
				effets.remove(i);
				i--;
			}
		}
    	listeSuppr.clear();
    }

	public ArrayList<Entity> lookAround(){
		ArrayList<Entity> entitiesAround = new ArrayList<Entity>();
		ArrayList<Square> n = this.neighboor();
		Entity tmp;

		for(Square s : n){
			tmp = s.getEntity();
			if(tmp != null)
				entitiesAround.add(tmp);
		}

		return entitiesAround;
	}

	public ArrayList<Square> neighboor(){
		ArrayList<Square> res = new ArrayList<>();

		int x = this.getPosX();
		int y = this.getPosY();
		Map map = this.getMap();

		Square tmp;

		tmp = map.getSquare(x+1, y);
		if(tmp != null)
			res.add(tmp);

		tmp = map.getSquare(x-1, y);
		if(tmp != null)
			res.add(tmp);

		tmp = map.getSquare(x, y+1);
		if(tmp != null)
			res.add(tmp);

		tmp = map.getSquare(x, y-1);
		if(tmp != null)
			res.add(tmp);

		return res;
	}

	@Override
	public Iterator<Effect> iterator() {
		return effets.iterator();
	}
	public void addSuppr (int i){
		listeSuppr.add(i);
	}
}
