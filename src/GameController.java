import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameController implements Runnable, KeyListener {

	private Grid grid;
	private GameView gameView;
	private boolean running;

	public GameController(Grid grid, GameView gameView) {
		this.grid = grid;
		this.gameView = gameView;
		this.running = true;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		 int keyCode = e.getKeyCode();
	        if (grid.isDirectionChanged == false) {
	            switch (keyCode) {
	                case KeyEvent.VK_UP :
	                    grid.changeDirection(Direction.UP);
	                    break;
	                case KeyEvent.VK_RIGHT :
	                    grid.changeDirection(Direction.RIGHT);
	                    break;
	                case KeyEvent.VK_DOWN :
	                    grid.changeDirection(Direction.DOWN);
	                    break;
	                case KeyEvent.VK_LEFT :
	                    grid.changeDirection(Direction.LEFT);
	                    break;
	                case KeyEvent.VK_SPACE :
	                    break;
	            }
	        }
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void run() {
		while(running) {
			try {
				Thread.sleep(Math.max(50, 200 - grid.getScore() / 5 * 30));   
			}catch(InterruptedException e) {
				break;
			}
			grid.isDirectionChanged = false;
			if (grid.nextRound() == true) {
				gameView.draw();
			}else {
				System.out.print("Congraduations! Your scores: "+grid.getScore());
				gameView.showGameOverMessage();
				running = false;
			}
		}
		
	}

}
