/**
 * @date	: 28.Mar.2009 
 * @author	: selcuk
 * @project	: RayTracer
 * @package	: com.selcukcihan.raytracer.vision
 */
package com.selcukcihan.raytracer.vision;

/**
 * @author selcuk
 *
 */
public class Color
{
	public double r = 0.0f;
	public double g = 0.0f;
	public double b = 0.0f;
	
	public Color(Color p_color)
	{
		this(p_color.r, p_color.g, p_color.b);
	}
	
	public Color(double p_value)
	{
		this(p_value, p_value, p_value);
	}
	
	public Color(double p_r, double p_g, double p_b)
	{
		if(p_r >= 0 && p_r <= 1)
			r = p_r;
		else if(p_r < 0)
			r = 0;
		else
			r = 1;
		
		if(p_g >= 0 && p_g <= 1)
			g = p_g;
		else if(p_g < 0)
			g = 0;
		else
			g = 1;
		
		
		if(p_b >= 0 && p_b <= 1)
			b = p_b;
		else if(p_b < 0)
			b = 0;
		else
			b = 1;
		
		assert(r >= 0 && g >= 0 && b >= 0) : "sýfýrdan küçük renk olamaz";
	}

	public Color add(Color p_color)
	{
		Color retVal = new Color(r + p_color.r, g + p_color.g, b + p_color.b);
		return retVal;
	}
	
	public Color add(float p_delta)
	{
		Color retVal = new Color(r + p_delta, g + p_delta, b + p_delta);
		return retVal;
	}
	
	public Color multiply(Color p_color)
	{
		Color retVal = new Color(r * p_color.r, g * p_color.g, b * p_color.b);
		return retVal;
	}
	
	public Color multiply(double p_scale)
	{
		Color retVal = new Color(r * p_scale, g * p_scale, b * p_scale);
		return retVal;
	}
}

