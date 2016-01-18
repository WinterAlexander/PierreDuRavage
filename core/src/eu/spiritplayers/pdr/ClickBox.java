package eu.spiritplayers.pdr;

/**
 * Represents a clickable box for a button or something else
 *
 * Created by Alexander Winter on 2016-01-16.
 */
public abstract class ClickBox
{
	private int x, y, width, height;

	public ClickBox(int x, int y, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public abstract void click();

	public int getCenterX()
	{
		return getX() + getWidth() / 2;
	}

	public int getCenterY()
	{
		return getY() + getHeight() / 2;
	}

	public boolean contains(int screenX, int screenY)
	{
		if(screenX < x)
			return false;

		if(screenX > x + width)
			return false;

		if(screenY < y)
			return false;

		if(screenY > y + height)
			return false;

		return true;
	}

	public int getX()
	{
		return x;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int y)
	{
		this.y = y;
	}

	public int getWidth()
	{
		return width;
	}

	public void setWidth(int width)
	{
		this.width = width;
	}

	public int getHeight()
	{
		return height;
	}

	public void setHeight(int height)
	{
		this.height = height;
	}
}
