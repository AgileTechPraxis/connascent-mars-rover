package Model;

public record Position(Coordinate coordinate,Direction direction){

public Position turnLeft(){
        return new Position(this.coordinate,this.direction.turnLeft());
        }

public Position turnRight(){
        return new Position(this.coordinate,this.direction.turnRight());
        }

public Position moveForward(){
        return switch(this.direction){
        case NORTH->new Position(this.coordinate.moveNorth(),this.direction);
        case EAST->new Position(this.coordinate.moveEast(),this.direction);
        case SOUTH->new Position(this.coordinate.moveSouth(),this.direction);
        case WEST->new Position(this.coordinate.moveWest(),this.direction);
        };
        }

@Override
public String toString(){
        return String.format("%s %s",coordinate.toString(),direction.toString());
        }
        }
