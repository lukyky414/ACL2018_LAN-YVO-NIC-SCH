package model.plateau;

public class Map {
	Square[][] cases;
	
	public Map(){
		
	}

	
	public Map(int x, int y){
		setSize(x,y);
	}
	
	public void setSize(int x, int y){
		cases = new Square[x][y];
	}
	
	public int getWidth(){
		return cases[0].length;
	}
	public int getHeigth(){
		return cases.length;
	}
	
	public void setTileType(int x,int y ,String s){
		switch (s){
		case "Wall":
			cases[x][y]= new Wall(x,y);
			break;
		case "Basic":
			cases[x][y]= new Square(x,y);
		}
	}
}
