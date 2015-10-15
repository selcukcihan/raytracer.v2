/**
 * @date	: 31.Mar.2009 
 * @author	: selcuk
 * @project	: RayTracer
 * @package	: com.selcukcihan.raytracer.vision
 */
package com.selcukcihan.raytracer.vision;

import com.selcukcihan.raytracer.elements.Drawable;
import com.selcukcihan.raytracer.math.Point3;
import com.selcukcihan.raytracer.math.Vector3;
import com.selcukcihan.raytracer.vision.Ray.RayType;

/**
 * @author selcuk
 *
 */
public class Intersection
{
	private Point3 m_point = null; /* kesiþim noktasý */
	private Vector3 m_normal = null; /* kesiþim noktasýndaki differential yüzeyin normali */
	private Drawable m_drawable = null; /* kesiþtiði drawable objesi */
	
	public Intersection(Point3 p_point, Vector3 p_normal, Drawable p_drawable)
	{
		m_point = new Point3(p_point);
		
		m_normal = ((p_normal == null) ? null : (new Vector3(p_normal))); /* normal null ise new yapamayiz o yuzden bu gerekli */
		
		m_drawable = p_drawable;
	}
	
	public Point3 point() { return new Point3(m_point); }
	public Vector3 normal() { return new Vector3(m_normal); }
	public Drawable object() { return m_drawable; }

	public boolean sameAs(Intersection p_isect)
	{
		/* this ile p_isect acaba ayný yerdeki intersectler mi */
		if(m_drawable == p_isect.object())
		{
			return (m_point.epsilonEquals(p_isect.point(), Ray.EPSILON));
		}
		return false;
	}
	
	public Ray reflectedRay(Ray p_incoming)
	{ /* compute reflection of the ray which arrives at this intersection */
		return m_drawable.reflectedRay(p_incoming, this);
	}
	
	public Vector3 reflectedRay(Vector3 p_incoming)
	{
		return m_drawable.reflectedRay(p_incoming, this);
	}

	public Ray refractedRay(Ray p_incoming)
	{
		return m_drawable.refractedRay(p_incoming, this);
	}

}
