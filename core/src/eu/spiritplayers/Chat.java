package eu.spiritplayers;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import me.winterguardian.util.StringUtil;

import java.util.ArrayList;
import java.util.List;


public class Chat
{

	private GamePanel panel;
	
	private boolean open;
	private String content;
	private int cursor;
	private List<String> chatHistory;

	private Texture background;

	public Chat(GamePanel panel)
	{
		this.panel = panel;
		
		this.open = false;
		this.content = "";
		this.cursor = 0;

		this.chatHistory = new ArrayList<>();

		this.background = new Texture("chat.bmp");
	}

	public void render(SpriteBatch batch)
	{
		ClickBox box = getClickBox();

		int lineHeight = box.getHeight() / 6;



		batch.draw(background, box.getX(), box.getY(), box.getWidth(), box.getHeight());


		BitmapFont font = getPanel().getFont(lineHeight);
		font.setColor(Color.WHITE);

		//font.getData().setScale(lineHeight / font.getLineHeight());

		if(this.isOpen())
		{
			font.draw(batch, StringUtil.insert(content, cursor, '|'), box.getX(), box.getY() + lineHeight / 2 + font.getCapHeight() / 2);
		}

		for(int i = this.chatHistory.size() - 1; i >= this.chatHistory.size() - 5 && i >= 0; i--)
		{
			font.draw(batch, this.chatHistory.get(i), box.getX(), box.getY() + lineHeight * (this.chatHistory.size() - i) + lineHeight / 2 + font.getCapHeight() / 2);
		}
	}

	public void sendMessage(String message)
	{
		this.chatHistory.add(message);
	}

	public ClickBox getClickBox()
	{
		int width = getPanel().getWidth() / 2;
		int height = getPanel().getHeight() / 5;

		int boxX = getPanel().getBackgroundX() + getPanel().getWidth() / 2 - width / 2;
		int boxY = getPanel().getBackgroundY();

		return new ClickBox(boxX, boxY, width, height)
		{
			@Override
			public void click()
			{
				setOpen(true);
				if(Gdx.app.getType().equals(Application.ApplicationType.Android) ||
						Gdx.app.getType().equals(Application.ApplicationType.iOS))
				{
					Gdx.input.setOnscreenKeyboardVisible(true);
				}
			}
		};
	}


	public void input(int key)
	{
		if(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)
		|| Gdx.input.isKeyPressed(Input.Keys.CONTROL_RIGHT))
		{
			switch(key)
			{
				case Input.Keys.V:
					String input = StringUtil.getClipboardContent();
					content = StringUtil.insert(content, cursor, input);
					cursor += input.length();
					break;
				case Input.Keys.C:
					StringUtil.setClipboardContent(content);
					break;

				case Input.Keys.X:
					StringUtil.setClipboardContent(content);
					content = "";
					break;
			}
			cursorAutoReplace();
			return;
		}

		switch(key)
		{
			case Input.Keys.ENTER:
				if(this.content != null && this.content.length() > 0)
					panel.broadcast(panel.getLocalPlayer().getName() + " > " + content);
				close();
				break;

			case Input.Keys.ESCAPE:
				close();
				break;

			case Input.Keys.BACKSPACE:
				content = StringUtil.backspace(content, cursor);
				cursor--;
				break;

			case Input.Keys.FORWARD_DEL:
				content = StringUtil.backspace(content, cursor + 1);
				break;

			case Input.Keys.LEFT:
				cursor--;
				break;

			case Input.Keys.RIGHT:
				cursor++;
				break;

			case Input.Keys.UP:
				cursor = 0;
				break;

			case Input.Keys.DOWN:
				cursor = content.length();
				break;

			default:
				break;
		}

		cursorAutoReplace();
	}

	private boolean isValid(char character)
	{
		if(Character.isAlphabetic(character))
			return true;

		if(Character.isDigit(character))
			return true;

		if(Character.isSpaceChar(character))
			return true;

		switch(character)
		{
			case '!':
			case '?':
			case '.':
			case ',':
			case ':':
			case ';':
			case '\'':
			case '"':
			case '^':
			case '<':
			case '>':
			case '`':
			case '[':
			case ']':
			case '(':
			case ')':
			case '{':
			case '}':
			case '¨':
			case '=':
			case '+':
			case '*':
			case '/':
			case '\\':
			case '&':
			case '%':
			case '$':
			case '£':
			case '@':
			case '¢':
			case '¸':
			case '~':
			case '«':
			case '»':
			case '°':
			case '-':
			case '_':
			case '#':
				return true;
			default:
				return false;
		}

	}

	public void input(char character)
	{
		if(!isValid(character))
			return;

		this.content = StringUtil.insert(content, cursor, character);
		cursor++;
		cursorAutoReplace();
	}

	public void close()
	{
		this.content = "";
		this.open = false;
		this.cursor = 0;
	}

	private void cursorAutoReplace()
	{

		if(cursor < 0)
		{
			cursor = 0;
			return;
		}

		if(cursor > content.length())
			cursor = content.length();
	}

	public String getContent()
	{
		return content;
	}


	public void setContent(String content)
	{
		this.content = content;
	}


	public boolean isOpen()
	{
		return open;
	}

	public void setOpen(boolean open)
	{
		this.open = open;
	}

	public GamePanel getPanel()
	{
		return panel;
	}
}
