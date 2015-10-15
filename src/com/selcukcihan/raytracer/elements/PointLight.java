/**
 * @date	: 30.Mar.2009 
 * @author	: selcuk
 * @project	: RayTracer
 * @package	: com.selcukcihan.raytracer.elements
 */
package com.selcukcihan.raytracer.elements;

import com.selcukcihan.raytracer.math.Point3;
import com.selcukcihan.raytracer.vision.Color;

/**
 * @author selcuk
 *
 */
public class PointLight extends Element
{
	public final Color Color;
	
	public PointLight()
	{
		super();
		Color = new Color(1, 1, 1);
	}
	
	public PointLight(Color p_color)
	{
		super();
		
		Color = new Color(p_color);
	}

	public Point3 position()
	{
		Point3 retVal = new Point3(0, 0, 0);
		m_xform.transform(retVal);
		return retVal;
	}

}
