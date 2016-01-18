package eu.spiritplayers.pdr;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import me.winterguardian.scheduling.Scheduler;

public class PierreDuRavage extends ApplicationAdapter
{
	private Scheduler scheduler;
	private SpriteBatch batch;

	private GamePanel panel;
	private Game game;
	
	@Override
	public void create()
	{
		batch = new SpriteBatch();
		scheduler = new Scheduler();
		scheduler.start();

		panel = new GamePanel(this);

		this.game = new Demo(this);

		Gdx.input.setInputProcessor(new InputListener(this));
	}

	@Override
	public void render()
	{
		scheduler.update();

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		panel.render(batch);
		batch.end();
	}

	public void resize(int width, int height)
	{
		this.batch = new SpriteBatch();
	}

	public void dispose()
	{
		batch.dispose();
	}

	public Scheduler getScheduler()
	{
		return scheduler;
	}

	public void pause()
	{
		getScheduler().pause();
		panel.getMenu().setOpen(true);
	}

	public void resume()
	{
		getScheduler().start();
	}

	public GamePanel getPanel()
	{
		return this.panel;
	}

	public Game getGame()
	{
		return game;
	}

	public void setGame(Game game)
	{
		this.game = game;
	}
}
