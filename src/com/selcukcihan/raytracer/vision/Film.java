/**
 * @date	: 28.Mar.2009 
 * @author	: selcuk
 * @project	: RayTracer
 * @package	: com.selcukcihan.raytracer.vision
 */
package com.selcukcihan.raytracer.vision;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.selcukcihan.raytracer.vision.sampling.Sample;

/**
 * @author selcuk
 *
 */
public class Film
{
	BufferedImage m_img = null;
	public Film(int p_width, int p_height)
	{
		m_img = new BufferedImage(p_width, p_height, BufferedImage.TYPE_INT_RGB);
	}
	/**
	 * @param p_sample
	 * @param p_ray
	 * @param p_color
	 */
	public void addSample(Sample p_sample, Ray p_ray, Color p_color)
	{
		/*
		 * p_ray'e gerek yok ya sanýrým
		 */
		Graphics graphics = m_img.createGraphics();
		int r = (int) (p_color.r * 255);
		int g = (int) (p_color.g * 255);
		int b = (int) (p_color.b * 255);
		
		if(r > 255)
			r = 255;
		else if(r < 0)
			r = 0;
		if(g > 255)
			g = 250;
		else if(g < 0)
			g = 0;
		if(b > 255)
			b = 255;
		else if(b < 0)
			b = 0;
		
		graphics.setColor(new java.awt.Color(r, g, b));
        
		graphics.drawLine(p_sample.imgX, p_sample.imgY, p_sample.imgX+1, p_sample.imgY+1);
	}
	
	public void toFile(String p_fileName)
	{
		try
		{
			ImageIO.write(m_img,"png",new File(p_fileName + ".png"));
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public BufferedImage getImage() { return m_img; }

}
