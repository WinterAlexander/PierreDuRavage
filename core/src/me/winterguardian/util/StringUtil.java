package me.winterguardian.util;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;

/**
 *
 * Created by Alexander Winter on 2016-01-12.
 */
public class StringUtil
{
	private StringUtil() {}

	public static String backspace(String string, int index)
	{
		if(index <= 0 || index > string.length())
			return string;

		if(index == 1)
			return string.substring(index);

		if(index == string.length())
			return string.substring(0, index - 1);

		return string.substring(0, index - 1) + string.substring(index);
	}

	public static String insert(String string, int index, char input)
	{
		return insert(string, index, input + "");
	}

	public static String insert(String string, int index, String input)
	{
		if(index <= 0)
			return input + string;

		if(index >= string.length())
			return string + input;

		return string.substring(0, index) + input + string.substring(index);
	}

	public static String getClipboardContent()
	{
		try
		{
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			return clipboard.getContents(null) == null ? "" : clipboard.getContents(null).getTransferData(DataFlavor.stringFlavor).toString();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}

	public static void setClipboardContent(String content)
	{
		try
		{
			StringSelection stringSelection = new StringSelection(content);
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(stringSelection, null);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
