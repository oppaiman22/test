package tictactoe.model;

public class WinnerChekker {
    private int[][] fields;
    
    public WinnerChekker(int[][] fields){
        this.fields = fields;
    }
    
    public int checking(){
        for(int player = 1; player <= 2; player++) {

            if(checkHorizontalWin(player))
                return player;

            if(checkVerticalWin(player))
                return player;

            if(checkDiagonalWin(player))
                return player;
            
        }
        return 0;
    }
    
    private boolean checkHorizontalWin(int player){
        for(int y = 0; y < 3; y++) {
            int playerCount = 0;
            for(int x = 0; x < 3; x++) {
                if(fields[x][y] == player) {
                    playerCount++;
                }
            }
            if(playerCount == 3)
                return true;
        }
        return false;
    }

    private boolean checkVerticalWin(int player){
        for(int x = 0; x < 3; x++) {
            int playerCount = 0;
            for(int y = 0; y < 3; y++) {
                if(fields[x][y] == player) {
                    playerCount++;
                }
            }
            if(playerCount == 3)
                return true;
        }
        return false;
    }

    private boolean checkDiagonalWin(int player){
        int playerCount = 0;
        
        for(int coordinate = 0; coordinate < 3; coordinate++){
            if(fields[coordinate][coordinate] == player){
                playerCount++;
            }
        } 
            
        if(playerCount == 3)
            return true;
        
        playerCount = 0;
        
        for(int coordinate = 0; coordinate < 3; coordinate++) {
            if(fields[2-coordinate][coordinate] == player){
                playerCount++;
            }
        }

        if(playerCount == 3){
            return true;
        }
        
        return false;
    }
}
